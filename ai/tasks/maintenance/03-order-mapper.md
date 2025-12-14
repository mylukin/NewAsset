---
id: maintenance.order-mapper
module: maintenance
priority: 59
status: passing
version: 24
origin: manual
dependsOn:
  - maintenance.order-entity
  - maintenance.order-log-entity
supersedes: []
tags:
  - mapper
  - database
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:40:23.775Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 72b7867e304805302618a1e0d327f9c54741690c
  summary: 7/7 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:38:56.044Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/example/asset/mapper/AssetMaintOrderMapperTest.java
      - src/test/java/com/example/asset/mapper/AssetMaintLogMapperTest.java
    e2e: []
  unitTestCases:
    - name: AssetMaintOrderMapper_insert_shouldInsertOrderRecord
      assertions:
        - assertNotNull(order.getId())
        - 'assertEquals(1, result)'
    - name: AssetMaintOrderMapper_selectById_shouldReturnOrderWithAssetAndUserNames
      assertions:
        - assertNotNull(order)
        - assertNotNull(order.getAssetName())
        - assertNotNull(order.getHandlerName())
    - name: AssetMaintOrderMapper_selectList_shouldFilterByProjectId
      assertions:
        - assertFalse(orders.isEmpty())
        - >-
          assertTrue(orders.stream().allMatch(o ->
          o.getProjectId().equals(projectId)))
    - name: AssetMaintOrderMapper_selectList_shouldFilterByAssetType
      assertions:
        - >-
          assertTrue(orders.stream().allMatch(o ->
          o.getAssetType().equals(assetType)))
    - name: AssetMaintOrderMapper_selectList_shouldFilterByStatus
      assertions:
        - >-
          assertTrue(orders.stream().allMatch(o ->
          o.getStatus().equals(status)))
    - name: AssetMaintOrderMapper_selectList_shouldFilterByPriority
      assertions:
        - >-
          assertTrue(orders.stream().allMatch(o ->
          o.getPriority().equals(priority)))
    - name: AssetMaintOrderMapper_selectList_shouldFilterByHandlerId
      assertions:
        - >-
          assertTrue(orders.stream().allMatch(o ->
          o.getHandlerId().equals(handlerId)))
    - name: AssetMaintOrderMapper_selectList_shouldFilterByRequesterId
      assertions:
        - >-
          assertTrue(orders.stream().allMatch(o ->
          o.getRequesterId().equals(requesterId)))
    - name: AssetMaintOrderMapper_selectList_shouldFilterByDateRange
      assertions:
        - >-
          assertTrue(orders.stream().allMatch(o ->
          !o.getCreatedAt().isBefore(startDate) &&
          !o.getCreatedAt().isAfter(endDate)))
    - name: AssetMaintOrderMapper_selectList_shouldApplyDataScopeFiltering
      assertions:
        - >-
          assertTrue(orders.stream().allMatch(o ->
          allowedProjectIds.contains(o.getProjectId())))
    - name: AssetMaintLogMapper_insert_shouldInsertLogRecord
      assertions:
        - assertNotNull(log.getId())
        - 'assertEquals(1, result)'
    - name: AssetMaintLogMapper_selectByOrderId_shouldReturnLogsForOrder
      assertions:
        - assertFalse(logs.isEmpty())
        - >-
          assertTrue(logs.stream().allMatch(l ->
          l.getOrderId().equals(orderId)))
    - name: AssetMaintLogMapper_selectList_shouldJoinWithUserTableForOperatorName
      assertions:
        - assertNotNull(log.getOperatorName())
  e2eScenarios: []
  frameworkHint: junit5-mybatis
---
# Create Maintenance Order Mapper

## Context

MyBatis mappers for maintenance order and log CRUD operations.

## Acceptance Criteria

1. Create `AssetMaintOrderMapper` interface and XML
2. Implement order methods:
3. Create `AssetMaintLogMapper` interface and XML
4. Implement log methods:
5. Support filters: projectId, assetType, status, priority, handlerId, requesterId, dateRange
6. Include data scope filtering
7. Join with asset and user tables for display names
## Technical Notes

- Reference: TECH.md Section 4.2
- Pattern: MyBatis XML mapper with joins
- Index usage: (project_id, status), (current_handler_id, status)
