---
id: maintenance.order-create
module: maintenance
priority: 5
status: failing
version: 1
origin: manual
dependsOn: [maintenance.order-workflow]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Maintenance Order Creation

## Context

Orders can be created from asset detail pages (with asset pre-filled) or independently from the maintenance module (selecting asset).

## Acceptance Criteria

1. Backend API `POST /asset/maint/order`:
   - Request body: MaintOrderCreateDTO
   - Auto-generates orderNo
   - Permission check
   - Sets status to WAIT_ASSIGN
   - Creates initial log entry
2. Creation modes:
   - From asset: `POST /asset/house/{id}/maint` (pre-fills asset)
   - Independent: select asset in form
3. Create form fields:
   - Asset selector (search by code/name)
   - Project (auto-filled from asset)
   - Order type, fault type
   - Title, description
   - Priority (dropdown)
   - Required finish time (datetime picker)
   - Attachments (photos)
   - Option: change asset status to FAULT/MAINTAINING
4. Frontend:
   - Dialog or drawer form
   - Asset search autocomplete
   - Rich text or textarea for description
   - File upload for photos
5. Validation:
   - Asset required
   - Title required
   - Description required

## Technical Notes

- orderNo: MO-{yyyyMMdd}-{4 digit seq}
- Consider draft save feature (future)
- Mobile-friendly design for on-site reporting
