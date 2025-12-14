---
id: house.update
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn: [house.entity, core.state-machine]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement House Asset Update

## Context

Updating house assets includes modifying properties and changing status. Key field changes are audited and status changes go through the state machine.

## Acceptance Criteria

1. Backend API `PUT /asset/house`:
   - Request body: AssetHouseUpdateDTO
   - Permission: @PreAuthorize("asset:house:edit")
   - Different permissions for status change
2. Backend service `AssetHouseService.updateHouse()`:
   - Compare old vs new values for change log
   - If status changed, delegate to AssetStatusService
   - Update both t_asset and t_asset_house
   - Record changes in t_asset_change_log
   - @Log annotation for operation audit
3. Key fields requiring change log:
   - status, projectId, useDeptId, dutyUserId
   - originalValue, ownershipType, ownerOrg
4. Frontend edit form:
   - Pre-filled with current values
   - Asset code is read-only
   - Status dropdown shows only valid transitions
   - Reason field required for status changes
5. Validation:
   - Prevent invalid status transitions
   - Area must remain positive
   - Cannot change to project without access
6. Soft delete `DELETE /asset/house/{ids}`:
   - Set del_flag = '2'
   - Permission: @PreAuthorize("asset:house:remove")

## Technical Notes

- Use BeanUtils or MapStruct for DTO conversion
- State machine validates before update
- Consider optimistic locking for concurrent edits
