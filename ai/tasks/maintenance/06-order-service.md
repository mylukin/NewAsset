---
id: maintenance.order-service
module: maintenance
priority: 706
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - maintenance.entity
  - maintenance.status-enum
  - maintenance.order-number-generator
  - maintenance.status-linkage-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/maintenance/**/*.test.*"
---
# Implement Work Order Service

## Context

Core service for work order CRUD and workflow operations.

## Acceptance Criteria

1. Create `IMaintOrderService` interface:
   - list(MaintOrderQueryDTO query) - Paginated list
   - getById(Long id) - Detail with timeline
   - create(MaintOrderCreateDTO dto) - Create with auto number
   - assign(Long id, Long handlerId) - Assign to handler
   - accept(Long id) - Handler accepts
   - complete(Long id, String resultDesc, attachments) - Handler completes
   - confirm(Long id, boolean passed, String reason) - Verify result
   - cancel(Long id, String reason) - Cancel at any stage
   - getByAssetId(Long assetId) - For asset detail tab

2. Create `MaintOrderServiceImpl`:
   - Generate order number on create
   - Validate status transitions using MaintOrderStatusMachine
   - Record all transitions in maint_log
   - Call StatusLinkageService on create/close/cancel
   - Check delete block rule (from core) - block asset delete if open WO

3. Implement filters for list:
   - projectId, assetId, status, priority
   - requesterId, handlerId
   - dateRange (created, due)
   - View tabs: All, My Created, Assigned to Me

4. Add logging and error handling:
   - BusinessException for invalid transitions
   - Audit log for all operations

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Work Order management)
- PRD: docs/PRD.md Section 6.1.2
