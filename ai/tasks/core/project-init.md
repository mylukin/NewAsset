---
id: core.project-init
module: core
priority: 1
status: failing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags:
  - backend
  - setup
testRequirements:
  unit:
    required: false
    pattern: ''
---
# Initialize ruoyi-asset Module

## Acceptance Criteria

1. Create `ruoyi-asset` Maven module under `ruoyi-modules/`
2. Configure `pom.xml` with proper dependencies (ruoyi-common, ruoyi-framework)
3. Create package structure:
4. Add module to parent `pom.xml`
5. Create `application.yml` with MyBatis mapper scanning configuration
6. Verify module compiles without errors
