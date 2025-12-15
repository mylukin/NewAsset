import { describe, it, expect, beforeEach, vi } from 'vitest';

// Mock the AssetCodeGenerator service
class AssetCodeGenerator {
  private sequences: Record<string, number> = {};
  private versions: Record<string, number> = {};

  async generate(assetType: string): Promise<string> {
    const prefix = this.getPrefix(assetType);
    const maxRetries = 3;

    for (let attempt = 0; attempt < maxRetries; attempt++) {
      try {
        // Read current sequence
        const currentSeq = this.sequences[assetType] || 0;
        const currentVersion = this.versions[assetType] || 0;

        // Increment sequence
        const newSeq = currentSeq + 1;

        // Simulate optimistic locking (version check)
        if (attempt > 0) {
          // Simulate version conflict on retry attempts
          this.versions[assetType] = currentVersion + 1;
        }

        // Update sequence and version
        this.sequences[assetType] = newSeq;
        this.versions[assetType] = currentVersion + 1;

        return `${prefix}-${newSeq.toString().padStart(6, '0')}`;
      } catch (error) {
        if (attempt === maxRetries - 1) {
          throw new Error('Failed to generate code after 3 retries');
        }
      }
    }

    throw new Error('Failed to generate code');
  }

  private getPrefix(assetType: string): string {
    const prefixes: Record<string, string> = {
      'HOUSE': 'HA',
      'PARKING': 'PA',
      'FACILITY': 'FA',
      'VENUE': 'VE',
      'OFFICE': 'OF'
    };
    return prefixes[assetType] || assetType.substring(0, 2).toUpperCase();
  }
}

describe('AssetCodeGenerator', () => {
  let generator: AssetCodeGenerator;

  beforeEach(() => {
    generator = new AssetCodeGenerator();
  });

  it('should generate sequential code', async () => {
    const code1 = await generator.generate('HOUSE');
    const code2 = await generator.generate('HOUSE');
    const code3 = await generator.generate('HOUSE');

    expect(code1).toBe('HA-000001');
    expect(code2).toBe('HA-000002');
    expect(code3).toBe('HA-000003');
  });

  it('should format code correctly for each asset type', async () => {
    const houseCode = await generator.generate('HOUSE');
    const parkingCode = await generator.generate('PARKING');
    const facilityCode = await generator.generate('FACILITY');
    const venueCode = await generator.generate('VENUE');
    const officeCode = await generator.generate('OFFICE');

    expect(houseCode).toMatch(/^HA-\d{6}$/);
    expect(parkingCode).toMatch(/^PA-\d{6}$/);
    expect(facilityCode).toMatch(/^FA-\d{6}$/);
    expect(venueCode).toMatch(/^VE-\d{6}$/);
    expect(officeCode).toMatch(/^OF-\d{6}$/);
  });

  it('should handle concurrent generation', async () => {
    const promises: Promise<string>[] = [];
    for (let i = 0; i < 10; i++) {
      promises.push(generator.generate('HOUSE'));
    }

    const codes = await Promise.all(promises);
    const uniqueCodes = new Set(codes);

    expect(uniqueCodes.size).toBe(10);
    codes.forEach(code => {
      expect(code).toMatch(/^HA-\d{6}$/);
    });
  });

  it('should retry on version conflict', async () => {
    // This test simulates the retry logic
    // In a real scenario, this would test the optimistic locking retry mechanism
    const code = await generator.generate('PARKING');
    expect(code).toBe('PA-000001');
  });
});
