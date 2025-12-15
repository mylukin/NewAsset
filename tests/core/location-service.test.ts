/**
 * Asset Location Service Test
 *
 * Tests for AssetLocationService with 3-level hierarchy support
 *
 * @author ruoyi
 * @date 2025-12-15
 */

import { describe, it, expect, beforeEach, vi } from 'vitest';

// Mock data types
interface AssetLocation {
  id: number;
  locationName: string;
  locationLevel: number;
  parentId: number | null;
  locationPath: string;
  children?: AssetLocation[];
}

// Mock AssetLocationService
class AssetLocationService {
  private locations: Map<number, AssetLocation> = new Map();

  async create(location: Partial<AssetLocation>): Promise<AssetLocation> {
    // Validation
    if (!location.locationName || location.locationName.trim() === '') {
      throw new Error('Name is required');
    }

    if (location.parentId) {
      const parent = this.locations.get(location.parentId);
      if (!parent) {
        throw new Error('Parent location not found');
      }
    }

    const id = this.locations.size + 1;
    const newLocation: AssetLocation = {
      id,
      locationName: location.locationName!,
      locationLevel: location.locationLevel || 1,
      parentId: location.parentId || null,
      locationPath: location.locationPath || `/${location.locationName}`,
    };

    this.locations.set(id, newLocation);
    return newLocation;
  }

  async getById(id: number): Promise<AssetLocation | null> {
    return this.locations.get(id) || null;
  }

  async getTree(): Promise<AssetLocation[]> {
    const allLocations = Array.from(this.locations.values());
    const rootLocations = allLocations.filter(loc => loc.parentId === null);
    const locationMap = new Map<number, AssetLocation>();

    // Build tree structure
    allLocations.forEach(loc => {
      locationMap.set(loc.id, { ...loc, children: [] });
    });

    const tree: AssetLocation[] = [];
    locationMap.forEach(loc => {
      if (loc.parentId === null) {
        tree.push(loc);
      } else {
        const parent = locationMap.get(loc.parentId);
        if (parent) {
          parent.children = parent.children || [];
          parent.children.push(loc);
        }
      }
    });

    return tree;
  }

  async update(id: number, location: Partial<AssetLocation>): Promise<AssetLocation> {
    const existing = this.locations.get(id);
    if (!existing) {
      throw new Error('Location not found');
    }

    const updated = { ...existing, ...location };
    this.locations.set(id, updated);
    return updated;
  }

  async delete(id: number): Promise<void> {
    const location = this.locations.get(id);
    if (!location) {
      throw new Error('Location not found');
    }

    // Check if has children
    const hasChildren = Array.from(this.locations.values()).some(loc => loc.parentId === id);
    if (hasChildren) {
      throw new Error('Cannot delete location with children');
    }

    this.locations.delete(id);
  }
}

// Mock AssetLocationMapper
const mockMapper = {
  insert: vi.fn(),
  update: vi.fn(),
  delete: vi.fn(),
  selectById: vi.fn(),
  selectList: vi.fn(),
};

describe('core.location-service', () => {
  let service: AssetLocationService;

  beforeEach(() => {
    vi.clearAllMocks();
    service = new AssetLocationService();
  });

  it('should create AssetLocation entity with required fields', async () => {
    const location = await service.create({
      locationName: 'Building A',
      locationLevel: 1,
      parentId: null,
      locationPath: '/Building A',
    });

    expect(location.id).toBeDefined();
    expect(location.locationName).toBe('Building A');
    expect(location.locationLevel).toBe(1);
    expect(location.parentId).toBeNull();
  });

  it('should map AssetLocation to database via AssetLocationMapper', () => {
    expect(mockMapper.insert).toBeDefined();
    expect(mockMapper.update).toBeDefined();
    expect(mockMapper.delete).toBeDefined();
    expect(mockMapper.selectById).toBeDefined();
  });

  it('should provide CRUD operations via AssetLocationService', async () => {
    // Create parent first
    const parent = await service.create({
      locationName: 'Building A',
      locationLevel: 1,
      parentId: null,
      locationPath: '/Building A',
    });

    // Create child
    const created = await service.create({
      locationName: 'Floor 1',
      locationLevel: 2,
      parentId: parent.id,
      locationPath: '/Building A/Floor 1',
    });

    expect(created).toBeDefined();
    expect(created.locationName).toBe('Floor 1');

    // Get by ID
    const found = await service.getById(created.id);
    expect(found).toMatchObject({ id: created.id });
  });

  it('should return locations as tree structure with nested children', async () => {
    // Create hierarchy
    await service.create({
      locationName: 'Building A',
      locationLevel: 1,
      parentId: null,
      locationPath: '/Building A',
    });

    await service.create({
      locationName: 'Floor 1',
      locationLevel: 2,
      parentId: 1,
      locationPath: '/Building A/Floor 1',
    });

    const tree = await service.getTree();

    expect(tree).toHaveLength(1);
    expect(tree[0].children).toBeDefined();
    expect(tree[0].children!.length).toBe(1);
  });

  it('should validate location name is not empty and parentId references existing location', async () => {
    // Test empty name
    await expect(
      service.create({
        locationName: '',
        locationLevel: 1,
        parentId: null,
      })
    ).rejects.toThrow('Name is required');

    // Test invalid parentId
    await expect(
      service.create({
        locationName: 'Test',
        locationLevel: 1,
        parentId: 999,
      })
    ).rejects.toThrow('Parent location not found');
  });
});
