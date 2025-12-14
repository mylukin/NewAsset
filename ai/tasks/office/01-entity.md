---
id: office.entity
module: office
priority: 1
status: failing
version: 1
origin: manual
dependsOn: [core.asset-entity]
supersedes: []
tags: [domain, entity]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create Office Asset Entity and Extension

## Context

Office assets represent office equipment like furniture, computers, printers, and other office supplies. They have user assignment and IT-specific fields.

## Acceptance Criteria

1. `AssetOffice` entity created for `t_asset_office`:
   - assetId (PK, FK to t_asset)
   - officeType (furniture, it_equipment, other)
   - workplaceNo (desk/cubicle number)
   - useUser (assigned user name)
   - serialNo (IT asset tag/serial)
   - osInfo (operating system for computers)
   - configDesc (hardware configuration)
2. `AssetOfficeVO` for list/detail responses:
   - Combines base asset fields + office extension
   - Department name, user name
3. `AssetOfficeCreateDTO` for creation
4. `AssetOfficeUpdateDTO` for updates
5. `AssetOfficeMapper` interface with standard CRUD

## Technical Notes

- officeType from dictionary
- serialNo important for IT asset tracking
- Consider barcode/QR for identification
- Link useUser to sys_user (optional)
