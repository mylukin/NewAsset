---
id: office.crud
module: office
priority: 2
status: failing
version: 1
origin: manual
dependsOn: [office.entity, core.code-generator, core.data-permission]
supersedes: []
tags: [feature, crud, frontend, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement Office Asset CRUD Operations

## Context

Full CRUD operations for office assets. Focuses on department ownership, user assignment, and IT equipment details.

## Acceptance Criteria

1. Backend APIs:
   - `GET /asset/office/list` - paginated list
   - `GET /asset/office/{id}` - detail
   - `POST /asset/office` - create
   - `PUT /asset/office` - update
   - `DELETE /asset/office/{ids}` - soft delete
   - `POST /asset/office/export` - Excel export
2. List filters:
   - projectId, useDeptId (department)
   - officeType, status
   - dutyUserId (responsible person)
   - useUser, serialNo search
3. Create/Edit form:
   - Project, department
   - Office type, workplace number
   - Assigned user
   - Serial number (for IT equipment)
   - OS and configuration (IT equipment)
   - Common asset fields
4. Frontend page `views/asset/office/index.vue`:
   - Department-centric view
   - Office type filter
   - User assignment column
5. Detail view:
   - IT details section (conditional)
   - Maintenance history
   - Quick maintenance initiation

## Technical Notes

- Department is primary grouping
- IT equipment has additional fields
- Consider bulk import from IT inventory
