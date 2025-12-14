---
id: core.data-permission
module: core
priority: 8
status: passing
version: 19
origin: manual
dependsOn:
  - core.project-init
supersedes: []
tags:
  - security
  - permission
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:05:57.413Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 1a928c0b679f1058c84cfd0169cddfb599c527a9
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:04:33.437Z'
  generatedBy: claude
  forVersion: 11
  suggestedTestFiles:
    unit:
      - src/test/java/com/ruoyi/asset/datascope/DataScopeTest.java
      - src/test/java/com/ruoyi/asset/datascope/ProjectDataScopeHandlerTest.java
    e2e: []
  unitTestCases:
    - name: shouldExtendDataScopeToSupportProjectIdFiltering
      assertions:
        - assertNotNull(dataScope.getProjectId())
        - 'assertEquals(expectedProjectId, dataScope.getProjectId())'
    - name: shouldCreateSysProjectTableWithRequiredFields
      assertions:
        - assertTrue(tableExists("sys_project"))
        - assertNotNull(projectRepository.findById(projectId))
    - name: shouldDefineDataScopeSQLPatternsForAssetQueries
      assertions:
        - assertTrue(sqlPattern.contains("project_id"))
        - >-
          assertEquals(expectedSQLPattern,
          dataScopeHandler.buildSqlPattern(DataScopeType.PROJECT_ONLY))
    - name: shouldApplyDataScopeToAssetListQueriesViaPlaceholder
      assertions:
        - 'assertTrue(generatedSql.contains("${params.dataScope}"))'
        - assertNotNull(assetMapper.selectAssetList(queryParams).get(0))
    - name: shouldConfigureRoleBasedDataScopeAssignments
      assertions:
        - 'assertEquals(DataScopeType.PROJECT_ONLY, role.getDataScope())'
        - 'assertTrue(roleService.hasDataScopePermission(roleId, projectId))'
    - name: shouldDocumentDataPermissionConfigurationForAdministrators
      assertions:
        - assertTrue(documentationExists("data-permission-guide.md"))
        - assertNotNull(getDocumentationSection("project-data-scope"))
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Configure Data Permission by Project

## Context

Asset data must be filtered by project based on user's data scope. RuoYi's @DataScope annotation needs customization to support project-level filtering.

## Acceptance Criteria

1. Extend RuoYi DataScope to support `project_id` field filtering
2. Create or configure `sys_project` table if not existing (or use dept as project proxy)
3. Define data scope SQL patterns for asset queries:
4. Apply data scope to asset list queries via `${params.dataScope}` placeholder
5. Configure role-based data scope assignments:
6. Document data permission configuration for administrators
## Technical Notes

- Reference: TECH.md Section 5.4, RuoYi DataScope mechanism
- Pattern: AOP-based data filtering
- Integration: Mapper XML with dataScope placeholder
