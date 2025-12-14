---
id: core.dictionary-setup
module: core
priority: 1
status: failing
version: 1
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - backend
  - config
testRequirements:
  unit:
    required: false
    pattern: ''
---
# Setup System Dictionaries for Asset Module

## Acceptance Criteria

1. Create dictionary entries in RuoYi's sys_dict_type and sys_dict_data:
2. Create SQL script to insert all dictionaries
3. Ensure dictionaries are properly ordered (dict_sort)
