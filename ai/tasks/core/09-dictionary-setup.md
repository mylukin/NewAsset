---
id: core.dictionary-setup
module: core
priority: 9
status: failing
version: 1
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - dictionary
  - configuration
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Configure System Dictionaries for Asset Module

## Context

The asset module requires numerous dictionary entries for dropdowns, status displays, and type classifications. These must be configured in RuoYi's dictionary system.

## Acceptance Criteria

1. Create dictionary type: `asset_type` (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
2. Create dictionary type: `asset_status` (all status enum values with Chinese labels)
3. Create dictionary type: `ownership_type` (自有, 租入, 托管)
4. Create dictionary type: `depreciation_method` (平均年限法, etc.)
5. Create dictionary type: `house_type` (一居, 两居, 三居, etc.)
6. Create dictionary type: `house_usage` (住宅, 商用, 公寓)
7. Create dictionary type: `parking_type` (固定, 临停)
8. Create dictionary type: `facility_type` (电梯, 门禁, 公共照明, etc.)
9. Create dictionary type: `venue_type` (篮球场, 羽毛球馆, 会议室, etc.)
10. Create dictionary type: `office_asset_type` (家具, IT设备, 其他)
11. Create dictionary type: `maint_order_status` (待派单, 待接单, 处理中, etc.)
12. Create dictionary type: `maint_priority` (一般, 重要, 紧急)
13. Create dictionary type: `fault_type` (common fault categories)

## Technical Notes

- Reference: PRD Sections 4.3, 4.4, 6.x
- Use RuoYi sys_dict_type and sys_dict_data tables
- Include SQL insert statements for initial data
