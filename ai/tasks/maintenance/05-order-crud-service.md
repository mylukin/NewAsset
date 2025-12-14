---
id: maintenance.order-crud-service
module: maintenance
priority: 61
status: passing
version: 24
origin: manual
dependsOn:
  - maintenance.order-mapper
supersedes: []
tags:
  - service
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:43:08.235Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: eb4dff740b9de0ebf66ef191e62ac2cb32b9d5fa
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:42:18.690Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/example/maintenance/service/AssetMaintOrderServiceTest.java
    e2e: []
  unitTestCases:
    - name: should define IAssetMaintOrderService interface with required methods
      assertions:
        - assertNotNull(IAssetMaintOrderService.class)
        - assertTrue(IAssetMaintOrderService.class.isInterface())
    - name: >-
        should create AssetMaintOrderServiceImpl that implements
        IAssetMaintOrderService
      assertions:
        - >-
          assertInstanceOf(IAssetMaintOrderService.class,
          assetMaintOrderService)
        - assertNotNull(assetMaintOrderService)
    - name: should implement create method for maintenance orders
      assertions:
        - assertNotNull(service.createOrder(orderDTO))
        - 'assertEquals(expectedOrderId, createdOrder.getId())'
    - name: should implement read method to retrieve maintenance order by id
      assertions:
        - 'assertEquals(expectedOrder, service.getOrderById(orderId))'
        - assertNotNull(service.getOrderById(orderId))
    - name: should implement update method for maintenance orders
      assertions:
        - 'assertTrue(service.updateOrder(orderId, updateDTO))'
        - 'assertEquals(updatedValue, service.getOrderById(orderId).getField())'
    - name: should implement delete method for maintenance orders
      assertions:
        - assertTrue(service.deleteOrder(orderId))
        - assertNull(service.getOrderById(orderId))
    - name: should implement list method with pagination support
      assertions:
        - assertNotNull(service.listOrders(queryParams))
        - 'assertEquals(expectedPageSize, result.getRecords().size())'
    - name: should apply data scope filtering to queries
      assertions:
        - verify(dataScopeFilter).apply(any())
        - 'assertEquals(filteredCount, result.getTotal())'
    - name: should include order logs when retrieving detail view
      assertions:
        - assertNotNull(orderDetail.getOrderLogs())
        - assertFalse(orderDetail.getOrderLogs().isEmpty())
    - name: should support export functionality for maintenance orders
      assertions:
        - assertNotNull(service.exportOrders(exportParams))
        - assertTrue(exportResult.length > 0)
  e2eScenarios: []
  frameworkHint: junit5
---
# Create Maintenance Order CRUD Service

## Context

Service for maintenance order list, detail, and query operations (non-workflow).

## Acceptance Criteria

1. Create `IAssetMaintOrderService` interface
2. Create `AssetMaintOrderServiceImpl` implementation
3. Implement methods:
4. Apply data scope filtering
5. Include order logs in detail view
6. Support export functionality
## Technical Notes

- Reference: PRD Section 6.1.2
- Pattern: Service + Mapper pattern
- Location: `com.ruoyi.asset.service.impl.AssetMaintOrderServiceImpl`
