---
id: maintenance.order-entity
module: maintenance
priority: 1
status: passing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: []
tddGuidance:
  generatedAt: '2025-12-14T04:48:08.249Z'
  generatedBy: codex
  forVersion: 1
  suggestedTestFiles:
    unit:
      - >-
        maintenance/src/test/java/maintenance/order/entity/MaintenanceWorkOrderEntityTest.java
      - >-
        maintenance/src/test/java/maintenance/order/entity/MaintenanceWorkOrderStatusTest.java
    e2e: []
  unitTestCases:
    - name: >-
        should create a maintenance work order entity with required fields and
        be instantiable
      assertions:
        - assertNotNull(new MaintenanceWorkOrder())
        - assertNotNull(order.getStatus())
    - name: >-
        should expose a status enum with expected values and allow
        setting/getting status
      assertions:
        - 'assertEquals(MaintenanceWorkOrderStatus.NEW, order.getStatus())'
        - >-
          order.setStatus(MaintenanceWorkOrderStatus.IN_PROGRESS);
          assertEquals(MaintenanceWorkOrderStatus.IN_PROGRESS,
          order.getStatus())
  e2eScenarios: []
  frameworkHint: junit-jupiter
---
# Create maintenance work order entity with status enum
