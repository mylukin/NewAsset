---
id: core.project-init
module: core
priority: 1
status: passing
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
verification:
  verifiedAt: '2025-12-14T06:37:48.351Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 018118ecba349b36d790e508c840fced40cb089e
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T06:31:31.347Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - src/test/java/com/ruoyi/asset/ModuleStructureTest.java
    e2e: []
  unitTestCases:
    - name: shouldHaveRuoyiAssetModuleDirectoryUnderRuoyiModules
      assertions:
        - assertTrue(moduleDir.exists())
        - assertTrue(moduleDir.isDirectory())
    - name: shouldHavePomXmlWithRuoyiCommonDependency
      assertions:
        - assertNotNull(pomDocument)
        - assertTrue(pomContent.contains("ruoyi-common"))
    - name: shouldHavePomXmlWithRuoyiFrameworkDependency
      assertions:
        - assertTrue(pomContent.contains("ruoyi-framework"))
    - name: shouldHaveCorrectPackageStructure
      assertions:
        - assertTrue(controllerDir.exists())
        - assertTrue(serviceDir.exists())
        - assertTrue(mapperDir.exists())
        - assertTrue(domainDir.exists())
    - name: shouldBeIncludedInParentPomModules
      assertions:
        - assertTrue(parentPomContent.contains("ruoyi-asset"))
    - name: shouldHaveApplicationYmlWithMyBatisMapperConfig
      assertions:
        - assertTrue(applicationYml.exists())
        - assertTrue(ymlContent.contains("mapper-locations"))
    - name: shouldCompileWithoutErrors
      assertions:
        - 'assertEquals(0, mavenBuildExitCode)'
  e2eScenarios: []
  frameworkHint: junit5
---
# Initialize ruoyi-asset Module

## Acceptance Criteria

1. Create `ruoyi-asset` Maven module under `ruoyi-modules/`
2. Configure `pom.xml` with proper dependencies (ruoyi-common, ruoyi-framework)
3. Create package structure:
4. Add module to parent `pom.xml`
5. Create `application.yml` with MyBatis mapper scanning configuration
6. Verify module compiles without errors
