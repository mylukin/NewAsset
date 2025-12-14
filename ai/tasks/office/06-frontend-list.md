---
id: office.frontend-list
module: office
priority: 53
status: failing
version: 17
origin: manual
dependsOn:
  - office.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
---
# Create Office Asset List Page

## Context

Vue component for office asset list.

## Acceptance Criteria

1. Create `/src/views/asset/office/index.vue`
2. Implement search filters: project, useDeptId, officeType, dutyUserId, status
3. Data table columns:
   - Asset code, name, project, department
   - Office type, workplace no, use user
   - Serial no, status
   - Actions
4. Action buttons: Add, Edit, Delete, View, Initiate Maintenance, Export
5. Permission control with v-hasPermi

## Technical Notes

- Reference: PRD Section 6.6.2
- Pattern: RuoYi list page
