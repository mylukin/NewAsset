import { describe, it, expect, beforeEach } from 'vitest';

// Mock AssetParkingService
class AssetParkingService {
  private assets: Map<number, any> = new Map();
  private parkingExtensions: Map<number, any> = new Map();

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

    // Create parking extension
    const parkingExtension = {
      assetId: dto.assetId,
      parkingNo: dto.parkingNo,
      parkingZone: dto.parkingZone,
      parkingType: dto.parkingType,
      area: dto.area,
      currentUser: dto.currentUser,
      plateNo: dto.plateNo,
      rentPrice: dto.rentPrice,
      contractNo: dto.contractNo,
      createBy: operatorId.toString(),
      createTime: new Date(),
      updateBy: operatorId.toString(),
      updateTime: new Date()
    };

    this.parkingExtensions.set(dto.assetId, parkingExtension);

    return this.getById(dto.assetId);
  }

  async update(dto: any, operatorId: number): Promise<any> {
    // Get existing asset
    const asset = this.assets.get(dto.assetId);
    if (!asset) {
      throw new Error(`Asset not found: ${dto.assetId}`);
    }

    // Update parking extension
    const existing = this.parkingExtensions.get(dto.assetId);
    if (!existing) {
      // Create if not exists
      return this.create(dto, operatorId);
    }

    const updated = {
      ...existing,
      parkingNo: dto.parkingNo,
      parkingZone: dto.parkingZone,
      parkingType: dto.parkingType,
      area: dto.area,
      currentUser: dto.currentUser,
      plateNo: dto.plateNo,
      rentPrice: dto.rentPrice,
      contractNo: dto.contractNo,
      updateBy: operatorId.toString(),
      updateTime: new Date()
    };

    this.parkingExtensions.set(dto.assetId, updated);

    return this.getById(dto.assetId);
  }

  async delete(ids: number[], operatorId: number): Promise<void> {
    for (const id of ids) {
      const asset = this.assets.get(id);
      if (asset) {
        // Delete parking extension
        this.parkingExtensions.delete(id);
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

    const parking = this.parkingExtensions.get(id);

    return {
      assetId: id,
      parkingNo: parking?.parkingNo,
      parkingZone: parking?.parkingZone,
      parkingType: parking?.parkingType,
      area: parking?.area,
      currentUser: parking?.currentUser,
      plateNo: parking?.plateNo,
      rentPrice: parking?.rentPrice,
      contractNo: parking?.contractNo
    };
  }

  // Helper method to add test data
  addTestAsset(id: number, asset: any): void {
    this.assets.set(id, asset);
  }
}

describe('AssetParkingService', () => {
  let service: AssetParkingService;

  beforeEach(() => {
    service = new AssetParkingService();
    // Add test asset
    service.addTestAsset(1, {
      id: 1,
      name: 'Test Parking Space',
      assetCode: 'PA-000001',
      assetType: 'PARKING',
      status: 'available_idle',
      projectId: 1,
      buildingId: 1
    });
  });

  describe('create', () => {
    it('should create parking asset with extension', async () => {
      const dto = {
        assetId: 1,
        parkingNo: 'P001',
        parkingZone: 'underground_1',
        parkingType: 'fixed',
        area: 12.5,
        currentUser: 'John Doe'
      };

      const result = await service.create(dto, 1);

      expect(result.assetId).toBe(1);
      expect(result.parkingNo).toBe('P001');
      expect(result.parkingZone).toBe('underground_1');
      expect(result.parkingType).toBe('fixed');
      expect(result.area).toBe(12.5);
      expect(result.currentUser).toBe('John Doe');
    });

    it('should throw error when asset ID is missing', async () => {
      const dto = {
        parkingNo: 'P001'
      };

      await expect(service.create(dto, 1)).rejects.toThrow('Asset ID is required');
    });

    it('should throw error when asset does not exist', async () => {
      const dto = {
        assetId: 999,
        parkingNo: 'P001'
      };

      await expect(service.create(dto, 1)).rejects.toThrow('Asset not found: 999');
    });
  });

  describe('update', () => {
    it('should update parking fields', async () => {
      // First create
      const createDto = {
        assetId: 1,
        parkingNo: 'P001',
        parkingZone: 'underground_1'
      };
      await service.create(createDto, 1);

      // Then update
      const updateDto = {
        assetId: 1,
        parkingNo: 'P002',
        parkingZone: 'ground',
        area: 15.0
      };

      const result = await service.update(updateDto, 1);

      expect(result.assetId).toBe(1);
      expect(result.parkingNo).toBe('P002');
      expect(result.parkingZone).toBe('ground');
      expect(result.area).toBe(15.0);
    });

    it('should create extension if it does not exist during update', async () => {
      const updateDto = {
        assetId: 1,
        parkingNo: 'P001',
        parkingType: 'fixed'
      };

      const result = await service.update(updateDto, 1);

      expect(result.assetId).toBe(1);
      expect(result.parkingNo).toBe('P001');
      expect(result.parkingType).toBe('fixed');
    });
  });

  describe('delete', () => {
    it('should delete parking asset', async () => {
      // Create with extension
      const createDto = {
        assetId: 1,
        parkingNo: 'P001'
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
        name: 'Test Parking Space 2',
        assetType: 'PARKING'
      });

      // Create extensions
      await service.create({ assetId: 1, parkingNo: 'P001' }, 1);
      await service.create({ assetId: 2, parkingNo: 'P002' }, 1);

      // Delete both
      await service.delete([1, 2], 1);

      expect(await service.getById(1)).toBeNull();
      expect(await service.getById(2)).toBeNull();
    });
  });

  describe('getById', () => {
    it('should get parking asset by ID', async () => {
      // Create with extension
      const createDto = {
        assetId: 1,
        parkingNo: 'P001',
        parkingZone: 'underground_1',
        parkingType: 'fixed',
        area: 12.5
      };
      await service.create(createDto, 1);

      const result = await service.getById(1);

      expect(result.assetId).toBe(1);
      expect(result.parkingNo).toBe('P001');
      expect(result.parkingZone).toBe('underground_1');
      expect(result.parkingType).toBe('fixed');
      expect(result.area).toBe(12.5);
    });

    it('should return null when asset does not exist', async () => {
      const result = await service.getById(999);
      expect(result).toBeNull();
    });

    it('should return asset without extension if extension does not exist', async () => {
      const result = await service.getById(1);

      expect(result.assetId).toBe(1);
      expect(result.parkingNo).toBeUndefined();
      expect(result.parkingType).toBeUndefined();
    });
  });
});
