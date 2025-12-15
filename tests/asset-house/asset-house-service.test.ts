import { describe, it, expect, beforeEach } from 'vitest';

// Mock AssetHouseService
class AssetHouseService {
  private assets: Map<number, any> = new Map();
  private houseExtensions: Map<number, any> = new Map();
  private assetCounter = 1;

  async create(dto: any, operatorId: number): Promise<any> {
    // Validate required fields
    if (!dto.assetId) {
      throw new Error('Asset ID is required');
    }

    // Check if asset exists
    const asset = this.assets.get(dto.assetId);
    if (!asset) {
      throw new Error(`Asset not found: ${dto.assetId}`);
    }

    // Create house extension
    const houseExtension = {
      assetId: dto.assetId,
      buildingArea: dto.buildingArea,
      innerArea: dto.innerArea,
      houseType: dto.houseType,
      houseUsage: dto.houseUsage,
      currentUsage: dto.currentUsage,
      currentUser: dto.currentUser,
      contractNo: dto.contractNo,
      rentUnitPrice: dto.rentUnitPrice,
      rentTotal: dto.rentTotal,
      createBy: operatorId.toString(),
      createTime: new Date(),
      updateBy: operatorId.toString(),
      updateTime: new Date()
    };

    this.houseExtensions.set(dto.assetId, houseExtension);

    return this.getById(dto.assetId);
  }

  async update(dto: any, operatorId: number): Promise<any> {
    // Get existing asset
    const asset = this.assets.get(dto.assetId);
    if (!asset) {
      throw new Error(`Asset not found: ${dto.assetId}`);
    }

    // Update house extension
    const existing = this.houseExtensions.get(dto.assetId);
    if (!existing) {
      // Create if not exists
      return this.create(dto, operatorId);
    }

    const updated = {
      ...existing,
      buildingArea: dto.buildingArea,
      innerArea: dto.innerArea,
      houseType: dto.houseType,
      houseUsage: dto.houseUsage,
      currentUsage: dto.currentUsage,
      currentUser: dto.currentUser,
      contractNo: dto.contractNo,
      rentUnitPrice: dto.rentUnitPrice,
      rentTotal: dto.rentTotal,
      updateBy: operatorId.toString(),
      updateTime: new Date()
    };

    this.houseExtensions.set(dto.assetId, updated);

    return this.getById(dto.assetId);
  }

  async delete(ids: number[], operatorId: number): Promise<void> {
    for (const id of ids) {
      const asset = this.assets.get(id);
      if (asset) {
        // Delete house extension
        this.houseExtensions.delete(id);
        // Delete base asset
        this.assets.delete(id);
      }
    }
  }

  async getById(id: number): Promise<any> {
    const asset = this.assets.get(id);
    if (!asset) {
      return null;
    }

    const house = this.houseExtensions.get(id);

    return {
      assetId: id,
      buildingArea: house?.buildingArea,
      innerArea: house?.innerArea,
      houseType: house?.houseType,
      houseUsage: house?.houseUsage,
      currentUsage: house?.currentUsage,
      currentUser: house?.currentUser,
      contractNo: house?.contractNo,
      rentUnitPrice: house?.rentUnitPrice,
      rentTotal: house?.rentTotal
    };
  }

  async copy(sourceId: number, operatorId: number): Promise<any> {
    const sourceAsset = this.assets.get(sourceId);
    if (!sourceAsset) {
      throw new Error(`Source asset not found: ${sourceId}`);
    }

    // Create new asset
    const newId = this.assetCounter++;
    const newAsset = {
      ...sourceAsset,
      id: newId,
      status: 'available_idle',
      currentUser: null,
      contractNo: null,
      createBy: operatorId.toString(),
      createTime: new Date(),
      updateBy: operatorId.toString(),
      updateTime: new Date()
    };

    this.assets.set(newId, newAsset);

    // Copy house extension if exists
    const sourceHouse = this.houseExtensions.get(sourceId);
    if (sourceHouse) {
      const newHouse = {
        ...sourceHouse,
        assetId: newId,
        currentUsage: null,
        currentUser: null,
        contractNo: null,
        createBy: operatorId.toString(),
        createTime: new Date(),
        updateBy: operatorId.toString(),
        updateTime: new Date()
      };
      this.houseExtensions.set(newId, newHouse);
    }

    return this.getById(newId);
  }

  // Helper method to add test data
  addTestAsset(id: number, asset: any): void {
    this.assets.set(id, asset);
  }
}

describe('AssetHouseService', () => {
  let service: AssetHouseService;

  beforeEach(() => {
    service = new AssetHouseService();
    // Add test asset
    service.addTestAsset(1, {
      id: 1,
      name: 'Test House',
      assetCode: 'HA-000001',
      assetType: 'HOUSE',
      status: 'available_idle',
      projectId: 1,
      buildingId: 1,
      floorId: 1
    });
  });

  describe('create', () => {
    it('should create house asset with extension', async () => {
      const dto = {
        assetId: 1,
        buildingArea: 120.5,
        innerArea: 100.0,
        houseType: 'three_room',
        houseUsage: 'residential',
        currentUsage: 'self_use'
      };

      const result = await service.create(dto, 1);

      expect(result.assetId).toBe(1);
      expect(result.buildingArea).toBe(120.5);
      expect(result.innerArea).toBe(100.0);
      expect(result.houseType).toBe('three_room');
      expect(result.houseUsage).toBe('residential');
      expect(result.currentUsage).toBe('self_use');
    });

    it('should throw error when asset ID is missing', async () => {
      const dto = {
        buildingArea: 120.5
      };

      await expect(service.create(dto, 1)).rejects.toThrow('Asset ID is required');
    });

    it('should throw error when asset does not exist', async () => {
      const dto = {
        assetId: 999,
        buildingArea: 120.5
      };

      await expect(service.create(dto, 1)).rejects.toThrow('Asset not found: 999');
    });
  });

  describe('update', () => {
    it('should update house fields', async () => {
      // First create
      const createDto = {
        assetId: 1,
        buildingArea: 120.5,
        houseType: 'two_room'
      };
      await service.create(createDto, 1);

      // Then update
      const updateDto = {
        assetId: 1,
        buildingArea: 150.0,
        houseType: 'three_room',
        currentUsage: 'rented'
      };

      const result = await service.update(updateDto, 1);

      expect(result.assetId).toBe(1);
      expect(result.buildingArea).toBe(150.0);
      expect(result.houseType).toBe('three_room');
      expect(result.currentUsage).toBe('rented');
    });

    it('should create extension if it does not exist during update', async () => {
      const updateDto = {
        assetId: 1,
        buildingArea: 120.5,
        houseType: 'two_room'
      };

      const result = await service.update(updateDto, 1);

      expect(result.assetId).toBe(1);
      expect(result.buildingArea).toBe(120.5);
      expect(result.houseType).toBe('two_room');
    });
  });

  describe('copy', () => {
    it('should copy asset for same floor', async () => {
      // First create an asset with extension
      const createDto = {
        assetId: 1,
        buildingArea: 120.5,
        innerArea: 100.0,
        houseType: 'three_room',
        houseUsage: 'residential',
        currentUsage: 'self_use',
        currentUser: 'John Doe',
        contractNo: 'CN-001',
        rentUnitPrice: 3000
      };
      await service.create(createDto, 1);

      // Copy the asset
      const copied = await service.copy(1, 2);

      expect(copied.assetId).not.toBe(1);
      expect(copied.buildingArea).toBe(120.5);
      expect(copied.innerArea).toBe(100.0);
      expect(copied.houseType).toBe('three_room');
      expect(copied.houseUsage).toBe('residential');
      // These should be cleared
      expect(copied.currentUsage).toBeNull();
      expect(copied.currentUser).toBeNull();
      expect(copied.contractNo).toBeNull();
      // This should be copied
      expect(copied.rentUnitPrice).toBe(3000);
    });

    it('should throw error when source asset does not exist', async () => {
      await expect(service.copy(999, 1)).rejects.toThrow('Source asset not found: 999');
    });
  });

  describe('delete', () => {
    it('should delete house asset', async () => {
      // Create with extension
      const createDto = {
        assetId: 1,
        buildingArea: 120.5
      };
      await service.create(createDto, 1);

      // Delete
      await service.delete([1], 1);

      const result = await service.getById(1);
      expect(result).toBeNull();
    });

    it('should delete multiple assets', async () => {
      // Add second asset
      service.addTestAsset(2, {
        id: 2,
        name: 'Test House 2',
        assetType: 'HOUSE'
      });

      // Create extensions
      await service.create({ assetId: 1, buildingArea: 120.5 }, 1);
      await service.create({ assetId: 2, buildingArea: 100.0 }, 1);

      // Delete both
      await service.delete([1, 2], 1);

      expect(await service.getById(1)).toBeNull();
      expect(await service.getById(2)).toBeNull();
    });
  });

  describe('getById', () => {
    it('should get house asset by ID', async () => {
      // Create with extension
      const createDto = {
        assetId: 1,
        buildingArea: 120.5,
        innerArea: 100.0,
        houseType: 'three_room',
        houseUsage: 'residential'
      };
      await service.create(createDto, 1);

      const result = await service.getById(1);

      expect(result.assetId).toBe(1);
      expect(result.buildingArea).toBe(120.5);
      expect(result.innerArea).toBe(100.0);
      expect(result.houseType).toBe('three_room');
      expect(result.houseUsage).toBe('residential');
    });

    it('should return null when asset does not exist', async () => {
      const result = await service.getById(999);
      expect(result).toBeNull();
    });

    it('should return asset without extension if extension does not exist', async () => {
      const result = await service.getById(1);

      expect(result.assetId).toBe(1);
      expect(result.buildingArea).toBeUndefined();
      expect(result.houseType).toBeUndefined();
    });
  });
});
