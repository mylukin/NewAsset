---
id: core.database-schema
module: core
priority: 1
status: failing
version: 1
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - database
  - sql
testRequirements:
  unit:
    required: false
    pattern: ''
---
# Create Database Schema for All Asset Tables

## Acceptance Criteria

1. Create `t_asset` base table with all common fields:
2. Create extension tables:
3. Create `t_asset_attachment` for file attachments
4. Create maintenance tables:
5. Create code generation tables:
6. Create appropriate indexes for query performance:
