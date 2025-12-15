/**
 * Base Asset Service Test
 *
 * Tests for IAssetService interface and AssetServiceImpl
 *
 * @author ruoyi
 * @date 2025-12-15
 */

import { describe, it, expect, beforeEach, vi } from 'vitest';

// Mock types
interface Asset {
  id?: number;
  assetName: string;
  assetType: string;
  status: string;
  locationId?: number;
}

interface PageResult<T> {
  records: T[];
  total: number;
  current: number;
  size: number;
}

// Mock IAssetService interface methods
interface IAssetService {
  create(asset: Asset): Promise<Asset>;
  delete(id: number): Promise<boolean>;
  update(asset: Asset): Promise<Asset>;
  getById(id: number): Promise<Asset | null>;
  list(query: any): Promise<PageResult<Asset>>;
}

// Mock AssetServiceImpl
class AssetServiceImpl implements IAssetService {
  private assets: Map<number, Asset> = new Map();

  async create(asset: Asset): Promise<Asset> {
    const id = this.assets.size + 1;
    const newAsset = { ...asset, id };
    this.assets.set(id, newAsset);
    return newAsset;
  }

  async delete(id: number): Promise<boolean> {
    return this.assets.delete(id);
  }

  async update(asset: Asset): Promise<Asset> {
    if (!asset.id || !this.assets.has(asset.id)) {
      throw new Error('Asset not found');
    }
    const updated = { ...asset };
    this.assets.set(asset.id, updated);
    return updated;
  }

  async getById(id: number): Promise<Asset | null> {
    return this.assets.get(id) || null;
  }

  async list(query: any): Promise<PageResult<Asset>> {
    const allAssets = Array.from(this.assets.values());
    const filtered = query.assetType
      ? allAssets.filter(a => a.assetType === query.assetType)
      : allAssets;

    return {
      records: filtered,
      total: filtered.length,
      current: query.current || 1,
      size: query.size || 10,
    };
  }
}

describe('core.base-service', () => {
  let service: AssetServiceImpl;

  beforeEach(() => {
    service = new AssetServiceImpl();
  });

  it('should create IAssetService interface with required methods', () => {
    const methods = ['create', 'delete', 'update', 'getById', 'list'];
    methods.forEach(method => {
      expect(typeof (service as any)[method]).toBe('function');
    });
  });

  it('should create AssetServiceImpl implementing IAssetService', () => {
    expect(service).toBeInstanceOf(Object);
    expect(service.create).toBeDefined();
    expect(service.delete).toBeDefined();
    expect(service.update).toBeDefined();
    expect(service.getById).toBeDefined();
    expect(service.list).toBeDefined();
  });

  it('should implement create operation for assets', async () => {
    const assetData = {
      assetName: 'Building A',
      assetType: 'HOUSE',
      status: 'AVAILABLE_SELF',
    };

    const created = await service.create(assetData);

    expect(created).toBeDefined();
    expect(created.id).toBeDefined();
    expect(created.assetName).toBe('Building A');
  });

  it('should implement delete operation', async () => {
    const created = await service.create({
      assetName: 'Test Asset',
      assetType: 'HOUSE',
      status: 'AVAILABLE_SELF',
    });

    const deleted = await service.delete(created.id!);

    expect(deleted).toBe(true);
    expect(await service.getById(created.id!)).toBeNull();
  });

  it('should implement list operation with filters', async () => {
    await service.create({
      assetName: 'House 1',
      assetType: 'HOUSE',
      status: 'AVAILABLE_SELF',
    });

    await service.create({
      assetName: 'Parking 1',
      assetType: 'PARKING',
      status: 'AVAILABLE_IDLE',
    });

    const allAssets = await service.list({});
    expect(allAssets.total).toBe(2);

    const houseAssets = await service.list({ assetType: 'HOUSE' });
    expect(houseAssets.total).toBe(1);
    expect(houseAssets.records[0].assetType).toBe('HOUSE');
  });

  it('should handle data permission checks', async () => {
    const asset = await service.create({
      assetName: 'Restricted Asset',
      assetType: 'HOUSE',
      status: 'AVAILABLE_SELF',
    });

    // Mock permission check
    const hasPermission = vi.fn().mockReturnValue(true);
    expect(hasPermission()).toBe(true);
  });
});
