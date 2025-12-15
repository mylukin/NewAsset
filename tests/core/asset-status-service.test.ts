import { describe, it, expect, beforeEach } from 'vitest';

// Mock AssetStatusService
class AssetStatusService {
  private assets: Record<number, any> = {};
  private logs: any[] = [];

  async changeStatus(assetId: number, newStatus: any, reason: string, operatorId: number): Promise<any> {
    const asset = this.assets[assetId];
    if (!asset) {
      throw new Error('Asset not found');
    }

    const currentStatus = asset.status;

    // Validate transition
    if (!currentStatus.canTransitionTo(newStatus)) {
      throw new Error(`Invalid status transition: ${currentStatus.code} cannot change to ${newStatus.code}`);
    }

    // Update status
    asset.status = newStatus;
    asset.updateBy = operatorId.toString();
    asset.updateTime = new Date();

    // Log change
    this.logs.push({
      assetId,
      fromStatus: currentStatus.code,
      toStatus: newStatus.code,
      operatorId,
      reason,
      timestamp: new Date()
    });

    return asset;
  }
}

// Mock statuses
const AVAILABLE_IDLE = { code: 'available_idle', canTransitionTo: (target: any) => ['temp_closed', 'fault', 'maintaining', 'to_be_scrapped'].includes(target.code) };
const TEMP_CLOSED = { code: 'temp_closed', canTransitionTo: (target: any) => ['available_self', 'available_rent', 'available_idle'].includes(target.code) };
const FAULT = { code: 'fault', canTransitionTo: (target: any) => ['available_self', 'available_rent', 'available_idle', 'to_be_scrapped'].includes(target.code) };
const SCRAPPED = { code: 'scrapped', canTransitionTo: () => false };

describe('AssetStatusService', () => {
  let service: AssetStatusService;

  beforeEach(() => {
    service = new AssetStatusService();
    service['assets'] = {
      1: { id: 1, name: 'Test Asset', status: AVAILABLE_IDLE, updateBy: null, updateTime: null }
    };
    service['logs'] = [];
  });

  it('should change status when valid', async () => {
    const result = await service.changeStatus(1, TEMP_CLOSED, 'Maintenance scheduled', 100);

    expect(result.status).toBe(TEMP_CLOSED);
    expect(service['logs'].length).toBe(1);
    expect(service['logs'][0].fromStatus).toBe('available_idle');
    expect(service['logs'][0].toStatus).toBe('temp_closed');
    expect(service['logs'][0].operatorId).toBe(100);
  });

  it('should reject invalid transition', async () => {
    service['assets'][1].status = SCRAPPED;

    await expect(service.changeStatus(1, AVAILABLE_IDLE, 'Test', 100))
      .rejects.toThrow('Invalid status transition');
  });

  it('should log status change', async () => {
    await service.changeStatus(1, FAULT, 'Broken', 200);

    expect(service['logs'].length).toBe(1);
    const log = service['logs'][0];
    expect(log.assetId).toBe(1);
    expect(log.reason).toBe('Broken');
    expect(log.operatorId).toBe(200);
    expect(log.timestamp).toBeInstanceOf(Date);
  });

  it('should handle asset not found', async () => {
    await expect(service.changeStatus(999, TEMP_CLOSED, 'Test', 100))
      .rejects.toThrow('Asset not found');
  });
});
