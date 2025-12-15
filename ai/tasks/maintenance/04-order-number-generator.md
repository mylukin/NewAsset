---
id: maintenance.order-number-generator
module: maintenance
priority: 704
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - maintenance.database-schema
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/maintenance/**/*.test.*"
---
# Implement Work Order Number Generator

## Context

Date-based work order number generator with format WO-YYYYMMDD-NNN.

## Acceptance Criteria

1. Create `MaintOrderNoGenerator` service:
   - generateOrderNo() returns next work order number
   - Format: WO-{YYYYMMDD}-{3-digit-seq}
   - Example: WO-20240115-001, WO-20240115-002

2. Implement optimistic locking for concurrent generation:
   - Read current_seq and version from t_asset_maint_order_seq
   - If date_key doesn't exist, insert with seq=1
   - Increment seq, update with version check
   - Retry on version conflict (max 3 retries)

3. Handle date rollover:
   - New date resets sequence to 001
   - Old date rows can be archived/cleaned up

4. Add error handling:
   - MaxRetryException if optimistic lock fails 3 times
   - Proper logging for debugging

5. Unit tests:
   - Single thread generation
   - Date rollover
   - Mock concurrent access

## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (Date-based WO number)
- Similar pattern to core.code-generator but date-based
