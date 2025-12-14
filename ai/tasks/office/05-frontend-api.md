---
id: office.frontend-api
module: office
priority: 52
status: failing
version: 20
origin: manual
dependsOn:
  - office.controller
supersedes: []
tags:
  - frontend
  - api
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
---
# Create Office Asset Frontend API Module

## Context

Axios API module for office asset frontend.

## Acceptance Criteria

1. Create `/src/api/asset/office.js`
2. Implement API functions:
   - listOffice, getOffice, addOffice, updateOffice, delOffice, exportOffice
3. Use RuoYi request utility
4. Export all functions

## Technical Notes

- Reference: TECH.md Section 7.3
- File: `ruoyi-ui/src/api/asset/office.js`
