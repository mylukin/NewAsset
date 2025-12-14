---
id: maintenance.order-workflow-service
module: maintenance
priority: 60
status: passing
version: 23
origin: manual
dependsOn:
  - maintenance.order-mapper
  - core.state-machine
supersedes: []
tags:
  - service
  - workflow
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:41:55.867Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 113d1451b62e139f18de4400c4bc5213e3b67a56
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:40:48.063Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-asset/src/test/java/com/ruoyi/asset/service/rule/MaintOrderWorkflowServiceTest.java
    e2e: []
  unitTestCases:
    - name: should create MaintOrderWorkflowService in correct package
      assertions:
        - assertNotNull(service)
        - assertTrue(service instanceof MaintOrderWorkflowService)
    - name: should implement submitOrder method that transitions DRAFT to PENDING
      assertions:
        - 'assertEquals(MaintOrderStatus.PENDING, result.getStatus())'
        - assertNotNull(result.getSubmittedAt())
    - name: >-
        should implement approveOrder method that transitions PENDING to
        APPROVED
      assertions:
        - 'assertEquals(MaintOrderStatus.APPROVED, result.getStatus())'
        - assertNotNull(result.getApprovedAt())
    - name: should implement rejectOrder method that transitions PENDING to REJECTED
      assertions:
        - 'assertEquals(MaintOrderStatus.REJECTED, result.getStatus())'
        - assertNotNull(result.getRejectedAt())
        - assertNotNull(result.getRejectionReason())
    - name: >-
        should implement startWork method that transitions APPROVED to
        IN_PROGRESS
      assertions:
        - 'assertEquals(MaintOrderStatus.IN_PROGRESS, result.getStatus())'
        - assertNotNull(result.getStartedAt())
    - name: >-
        should implement completeWork method that transitions IN_PROGRESS to
        COMPLETED
      assertions:
        - 'assertEquals(MaintOrderStatus.COMPLETED, result.getStatus())'
        - assertNotNull(result.getCompletedAt())
    - name: >-
        should implement closeOrder method that transitions COMPLETED/REJECTED
        to CLOSED
      assertions:
        - 'assertEquals(MaintOrderStatus.CLOSED, result.getStatus())'
        - assertNotNull(result.getClosedAt())
    - name: should validate current status before transition
      assertions:
        - >-
          assertThrows(IllegalStateException.class, () ->
          service.submitOrder(approvedOrder))
        - >-
          assertThrows(IllegalStateException.class, () ->
          service.approveOrder(draftOrder))
    - name: should create MaintOrderLog on each state transition
      assertions:
        - 'verify(logRepository, times(1)).save(any(MaintOrderLog.class))'
        - 'assertEquals(expectedAction, capturedLog.getAction())'
    - name: should update order timestamp fields on transition
      assertions:
        - assertNotNull(result.getUpdatedAt())
        - assertTrue(result.getUpdatedAt().isAfter(originalUpdatedAt))
    - name: should throw exception for invalid state transitions
      assertions:
        - >-
          assertThrows(IllegalStateException.class, () ->
          service.completeWork(draftOrder))
        - >-
          assertThrows(IllegalStateException.class, () ->
          service.startWork(pendingOrder))
    - name: >-
        should integrate with AssetStatusService to restore asset status on
        close
      assertions:
        - 'verify(assetStatusService, times(1)).restoreStatus(eq(assetId))'
        - 'assertEquals(AssetStatus.AVAILABLE, asset.getStatus())'
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Maintenance Order Workflow Service

## Context

Service handling the state machine logic for maintenance order workflow transitions with asset status integration.

## Acceptance Criteria

1. Create `MaintOrderWorkflowService` in `com.ruoyi.asset.service.rule`
2. Implement workflow methods:
3. Each method must:
4. Define valid transitions:
5. Integrate with AssetStatusService for status restoration on close
## Technical Notes

- Reference: TECH.md Section 5.3
- Pattern: Workflow state machine
- Transaction: All transitions in single transaction
