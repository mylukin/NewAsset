---
id: core.dictionaries
module: core
priority: 111
status: failing
version: 1
origin: spec-workflow
dependsOn: []
tags:
  - backend
  - config
testRequirements:
  unit:
    required: false
    pattern: "tests/core/**/*.test.*"
---
# Configure RuoYi Dictionaries for Asset Module

## Context

System dictionaries for asset types, status values, and other enumerations. Uses RuoYi's existing dictionary management.

## Acceptance Criteria

1. Add dictionary data to sys_dict_type and sys_dict_data:

   **asset_type** - Asset Types
   - HOUSE: 房源
   - PARKING: 车库
   - FACILITY: 配套
   - VENUE: 场馆
   - OFFICE: 办公

   **asset_status** - Asset Status (9 states)
   - under_construction: 在建/未投入
   - available_self: 可用-自用
   - available_rent: 可用-对外出租
   - available_idle: 可用-空置
   - temp_closed: 暂停使用
   - fault: 故障/停用
   - maintaining: 维修中
   - to_be_scrapped: 计划报废
   - scrapped: 已报废

   **ownership_type** - Ownership Types
   - self_owned: 自有
   - rented_in: 租入
   - entrusted: 托管

   **depreciation_method** - Depreciation Methods
   - straight_line: 平均年限法
   - declining_balance: 双倍余额递减法
   - units_of_production: 工作量法

2. Create SQL insert script: `sql/asset_dict.sql`

3. Dictionary values should match enum codes in Java

4. Add list_class (tag style) for status dictionary:
   - available_*: success (green)
   - fault, maintaining: warning (orange)
   - scrapped, to_be_scrapped: danger (red)
   - others: info (gray)

## Technical Notes

- Reference: RuoYi sys_dict_type, sys_dict_data tables
- UX: ai/tasks/spec/UX-DESIGN.md (Status badge colors)
