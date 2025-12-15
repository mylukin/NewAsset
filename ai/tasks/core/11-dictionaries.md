---
id: core.dictionaries
module: core
priority: 111
status: passing
version: 3
origin: spec-workflow
dependsOn: []
supersedes: []
tags:
  - backend
  - config
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Configure RuoYi Dictionaries for Asset Module

## Context

System dictionaries for asset types, status values, and other enumerations. Uses RuoYi's existing dictionary management.

## Acceptance Criteria

1. Add dictionary data to sys_dict_type and sys_dict_data:
2. Create SQL insert script: `sql/asset_dict.sql`
3. Dictionary values should match enum codes in Java
4. Add list_class (tag style) for status dictionary:
## Technical Notes

- Reference: RuoYi sys_dict_type, sys_dict_data tables
- UX: ai/tasks/spec/UX-DESIGN.md (Status badge colors)

## Notes

Verification failed: Implementation complete - Created sql/asset_dict.sql with all required dictionaries and color-coded status badges
