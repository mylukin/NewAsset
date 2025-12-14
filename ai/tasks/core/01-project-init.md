---
id: core.project-init
module: core
priority: 1
status: failing
version: 2
origin: manual
dependsOn: []
supersedes: []
tags:
  - infrastructure
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-14T09:39:05.366Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: af2ce4aaa7d95b4305d3f08eaac2597d47b5ef1d
  summary: 7/7 criteria satisfied
---
# Initialize ruoyi-asset Module Structure

## Context

The Asset Management System requires a new module `ruoyi-asset` within the RuoYi-Vue framework. This module will house all asset-related business logic following the established package structure.

## Acceptance Criteria

1. Create `ruoyi-modules/ruoyi-asset` Maven module with proper pom.xml dependencies
2. Set up package structure: `com.ruoyi.asset.{config,domain,mapper,service,controller,util,constant}`
3. Create sub-packages: `domain.entity`, `domain.vo`, `domain.enums`, `service.impl`, `service.rule`
4. Configure MyBatis mapper scanning for the asset module
5. Add module dependency to parent pom.xml
6. Create basic module configuration class `AssetConfig.java`
7. Verify module compiles and starts with RuoYi application

## Technical Notes

- Reference: RuoYi existing module structure (`ruoyi-system`)
- Pattern: Standard Spring Boot module configuration
- File: `ruoyi-modules/ruoyi-asset/pom.xml`
