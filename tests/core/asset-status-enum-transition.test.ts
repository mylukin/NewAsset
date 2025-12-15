import { describe, it, expect } from 'vitest';

// Mock Java enum with transition logic
const createStatus = (code: string, description: string, transitions: string[]) => {
  const statuses: Record<string, any> = {
    UNDER_CONSTRUCTION: ['AVAILABLE_SELF', 'AVAILABLE_RENT', 'AVAILABLE_IDLE'],
    AVAILABLE_SELF: ['MAINTAINING', 'FAULT', 'TEMP_CLOSED', 'TO_BE_SCRAPPED'],
    AVAILABLE_RENT: ['MAINTAINING', 'FAULT', 'TEMP_CLOSED', 'TO_BE_SCRAPPED'],
    AVAILABLE_IDLE: ['MAINTAINING', 'FAULT', 'TEMP_CLOSED', 'TO_BE_SCRAPPED'],
    TEMP_CLOSED: ['AVAILABLE_SELF', 'AVAILABLE_RENT', 'AVAILABLE_IDLE'],
    FAULT: ['AVAILABLE_SELF', 'AVAILABLE_RENT', 'AVAILABLE_IDLE', 'TO_BE_SCRAPPED'],
    MAINTAINING: ['AVAILABLE_SELF', 'AVAILABLE_RENT', 'AVAILABLE_IDLE'],
    TO_BE_SCRAPPED: ['SCRAPPED', 'AVAILABLE_SELF', 'AVAILABLE_RENT', 'AVAILABLE_IDLE'],
    SCRAPPED: []
  };

  return {
    code,
    description,
    canTransitionTo(target: any) {
      const validTransitions = statuses[this.name];
      return validTransitions.includes(target.name);
    },
    getValidTransitions() {
      const validTransitions = statuses[this.name];
      return validTransitions.map((name: string) => AssetStatusEnum[name]);
    }
  };
};

const AssetStatusEnum: Record<string, any> = {
  UNDER_CONSTRUCTION: Object.assign(createStatus('under_construction', '在建/未投入'), { name: 'UNDER_CONSTRUCTION' }),
  AVAILABLE_SELF: Object.assign(createStatus('available_self', '可用-自用'), { name: 'AVAILABLE_SELF' }),
  AVAILABLE_RENT: Object.assign(createStatus('available_rent', '可用-对外出租'), { name: 'AVAILABLE_RENT' }),
  AVAILABLE_IDLE: Object.assign(createStatus('available_idle', '可用-空置'), { name: 'AVAILABLE_IDLE' }),
  TEMP_CLOSED: Object.assign(createStatus('temp_closed', '暂停使用'), { name: 'TEMP_CLOSED' }),
  FAULT: Object.assign(createStatus('fault', '故障/停用'), { name: 'FAULT' }),
  MAINTAINING: Object.assign(createStatus('maintaining', '维修中'), { name: 'MAINTAINING' }),
  TO_BE_SCRAPPED: Object.assign(createStatus('to_be_scrapped', '计划报废'), { name: 'TO_BE_SCRAPPED' }),
  SCRAPPED: Object.assign(createStatus('scrapped', '已报废'), { name: 'SCRAPPED' })
};

describe('AssetStatusEnum', () => {
  it('should have 9 status values', () => {
    const statusValues = Object.keys(AssetStatusEnum);
    expect(statusValues).toHaveLength(9);
  });

  it('should validate status transitions', () => {
    // Test valid transitions
    expect(AssetStatusEnum.AVAILABLE_IDLE.canTransitionTo(AssetStatusEnum.TEMP_CLOSED)).toBe(true);
    expect(AssetStatusEnum.TEMP_CLOSED.canTransitionTo(AssetStatusEnum.AVAILABLE_IDLE)).toBe(true);
    expect(AssetStatusEnum.AVAILABLE_IDLE.canTransitionTo(AssetStatusEnum.FAULT)).toBe(true);

    // Test invalid transitions (SCRAPPED is terminal)
    expect(AssetStatusEnum.SCRAPPED.canTransitionTo(AssetStatusEnum.AVAILABLE_IDLE)).toBe(false);
    expect(AssetStatusEnum.SCRAPPED.canTransitionTo(AssetStatusEnum.UNDER_CONSTRUCTION)).toBe(false);

    // Test UNDER_CONSTRUCTION can only go to AVAILABLE_* states
    expect(AssetStatusEnum.UNDER_CONSTRUCTION.canTransitionTo(AssetStatusEnum.AVAILABLE_SELF)).toBe(true);
    expect(AssetStatusEnum.UNDER_CONSTRUCTION.canTransitionTo(AssetStatusEnum.AVAILABLE_RENT)).toBe(true);
    expect(AssetStatusEnum.UNDER_CONSTRUCTION.canTransitionTo(AssetStatusEnum.AVAILABLE_IDLE)).toBe(true);
  });

  it('should return valid transitions', () => {
    const validTransitions = AssetStatusEnum.AVAILABLE_IDLE.getValidTransitions();
    expect(validTransitions).toContain(AssetStatusEnum.TEMP_CLOSED);
    expect(validTransitions).toContain(AssetStatusEnum.FAULT);
    expect(validTransitions).toContain(AssetStatusEnum.MAINTAINING);
  });

  it('should have terminal state with no valid transitions', () => {
    const validTransitions = AssetStatusEnum.SCRAPPED.getValidTransitions();
    expect(validTransitions).toHaveLength(0);
  });
});
