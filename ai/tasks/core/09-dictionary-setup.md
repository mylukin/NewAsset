---
id: core.dictionary-setup
module: core
priority: 1
status: failing
version: 1
origin: manual
dependsOn: [core.project-init]
supersedes: []
tags: [backend, config]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Setup System Dictionaries for Asset Module

## Context

The asset module requires various dictionaries for dropdown selections, status displays, and configuration options.

## Acceptance Criteria

1. Create dictionary entries in RuoYi's sys_dict_type and sys_dict_data:

   **Asset Type (asset_type)**:
   - HOUSE - 房源
   - PARKING - 车库/车位
   - FACILITY - 住宅配套
   - VENUE - 场馆
   - OFFICE - 办公

   **Asset Status (asset_status)**:
   - All values from AssetStatusEnum with Chinese labels

   **Ownership Type (asset_ownership_type)**:
   - SELF_OWNED - 自有
   - RENTED_IN - 租入
   - MANAGED - 托管

   **Depreciation Method (asset_depreciation_method)**:
   - STRAIGHT_LINE - 平均年限法
   - DOUBLE_DECLINING - 双倍余额递减法
   - SUM_OF_YEARS - 年数总和法

   **House Type (house_type)**:
   - ONE_ROOM - 一居
   - TWO_ROOM - 两居
   - THREE_ROOM - 三居
   - FOUR_ROOM - 四居及以上

   **House Usage (house_usage)**:
   - RESIDENTIAL - 住宅
   - COMMERCIAL - 商用
   - APARTMENT - 公寓

   **Parking Type (parking_type)**:
   - FIXED - 固定车位
   - TEMPORARY - 临停车位

   **Facility Type (facility_type)**:
   - ELEVATOR - 电梯
   - ACCESS_CONTROL - 门禁
   - LIGHTING - 公共照明
   - FITNESS - 健身设施

   **Venue Type (venue_type)**:
   - BASKETBALL - 篮球场
   - BADMINTON - 羽毛球馆
   - MEETING_ROOM - 会议室
   - ACTIVITY_ROOM - 活动室

   **Office Asset Type (office_asset_type)**:
   - FURNITURE - 家具
   - IT_EQUIPMENT - IT设备
   - OTHER - 其他

   **Maintenance Priority (maint_priority)**:
   - NORMAL - 一般
   - IMPORTANT - 重要
   - URGENT - 紧急

   **Maintenance Order Status (maint_order_status)**:
   - WAIT_ASSIGN - 待派单
   - WAIT_ACCEPT - 待接单
   - PROCESSING - 处理中
   - WAIT_CONFIRM - 待验收
   - CLOSED - 已关闭
   - CANCELED - 已取消

2. Create SQL script to insert all dictionaries
3. Ensure dictionaries are properly ordered (dict_sort)

## Technical Notes

- Reference: PRD sections for each module
- Use RuoYi's dictionary management
