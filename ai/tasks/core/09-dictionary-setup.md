---
id: core.dictionary-setup
module: core
priority: 9
status: passing
version: 20
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
verification:
  verifiedAt: '2025-12-14T12:08:28.610Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 9e5318e56a81e04d9800e434b807ab57e3550c32
  summary: 13/13 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:06:50.961Z'
  generatedBy: claude
  forVersion: 12
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/dictionary/DictionarySetupTest.java
    e2e: []
  unitTestCases:
    - name: >-
        should create asset_type dictionary with all required values (HOUSE,
        PARKING, FACILITY, VENUE, OFFICE)
      assertions:
        - assertThat(assetTypeDict).isNotNull()
        - assertThat(assetTypeDict.getItems()).hasSize(5)
        - >-
          assertThat(assetTypeDict.getItems()).extracting("code").containsExactlyInAnyOrder("HOUSE",
          "PARKING", "FACILITY", "VENUE", "OFFICE")
    - name: >-
        should create asset_status dictionary with all status enum values and
        Chinese labels
      assertions:
        - assertThat(assetStatusDict).isNotNull()
        - assertThat(assetStatusDict.getItems()).hasSize(9)
        - >-
          assertThat(assetStatusDict.getItems()).extracting("code").contains("UNDER_CONSTRUCTION",
          "AVAILABLE_SELF", "AVAILABLE_RENT", "AVAILABLE_IDLE", "TEMP_CLOSED",
          "FAULT", "MAINTAINING", "TO_BE_SCRAPPED", "SCRAPPED")
    - name: 'should create ownership_type dictionary with values (自有, 租入, 托管)'
      assertions:
        - assertThat(ownershipTypeDict).isNotNull()
        - assertThat(ownershipTypeDict.getItems()).hasSize(3)
        - >-
          assertThat(ownershipTypeDict.getItems()).extracting("label").containsExactlyInAnyOrder("自有",
          "租入", "托管")
    - name: should create depreciation_method dictionary including 平均年限法
      assertions:
        - assertThat(depreciationMethodDict).isNotNull()
        - >-
          assertThat(depreciationMethodDict.getItems()).extracting("label").contains("平均年限法")
    - name: >-
        should create house_type dictionary with bedroom configurations (一居, 两居,
        三居, etc.)
      assertions:
        - assertThat(houseTypeDict).isNotNull()
        - >-
          assertThat(houseTypeDict.getItems()).extracting("label").contains("一居",
          "两居", "三居")
    - name: 'should create house_usage dictionary with values (住宅, 商用, 公寓)'
      assertions:
        - assertThat(houseUsageDict).isNotNull()
        - assertThat(houseUsageDict.getItems()).hasSize(3)
        - >-
          assertThat(houseUsageDict.getItems()).extracting("label").containsExactlyInAnyOrder("住宅",
          "商用", "公寓")
    - name: 'should create parking_type dictionary with values (固定, 临停)'
      assertions:
        - assertThat(parkingTypeDict).isNotNull()
        - assertThat(parkingTypeDict.getItems()).hasSize(2)
        - >-
          assertThat(parkingTypeDict.getItems()).extracting("label").containsExactlyInAnyOrder("固定",
          "临停")
    - name: 'should create facility_type dictionary with values (电梯, 门禁, 公共照明, etc.)'
      assertions:
        - assertThat(facilityTypeDict).isNotNull()
        - >-
          assertThat(facilityTypeDict.getItems()).extracting("label").contains("电梯",
          "门禁", "公共照明")
    - name: 'should create venue_type dictionary with values (篮球场, 羽毛球馆, 会议室, etc.)'
      assertions:
        - assertThat(venueTypeDict).isNotNull()
        - >-
          assertThat(venueTypeDict.getItems()).extracting("label").contains("篮球场",
          "羽毛球馆", "会议室")
    - name: 'should create office_asset_type dictionary with values (家具, IT设备, 其他)'
      assertions:
        - assertThat(officeAssetTypeDict).isNotNull()
        - assertThat(officeAssetTypeDict.getItems()).hasSize(3)
        - >-
          assertThat(officeAssetTypeDict.getItems()).extracting("label").containsExactlyInAnyOrder("家具",
          "IT设备", "其他")
    - name: >-
        should create maint_order_status dictionary with all maintenance
        statuses (待派单, 待接单, 处理中, etc.)
      assertions:
        - assertThat(maintOrderStatusDict).isNotNull()
        - assertThat(maintOrderStatusDict.getItems()).hasSize(6)
        - >-
          assertThat(maintOrderStatusDict.getItems()).extracting("code").contains("WAIT_ASSIGN",
          "WAIT_ACCEPT", "PROCESSING", "WAIT_CONFIRM", "CLOSED", "CANCELED")
    - name: 'should create maint_priority dictionary with values (一般, 重要, 紧急)'
      assertions:
        - assertThat(maintPriorityDict).isNotNull()
        - assertThat(maintPriorityDict.getItems()).hasSize(3)
        - >-
          assertThat(maintPriorityDict.getItems()).extracting("label").containsExactlyInAnyOrder("一般",
          "重要", "紧急")
    - name: should create fault_type dictionary with common fault categories
      assertions:
        - assertThat(faultTypeDict).isNotNull()
        - assertThat(faultTypeDict.getItems()).isNotEmpty()
  e2eScenarios: []
  frameworkHint: junit5-assertj
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
