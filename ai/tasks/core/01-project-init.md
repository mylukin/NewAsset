---
id: core.project-init
module: core
priority: 1
status: passing
version: 17
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
  verifiedAt: '2025-12-14T11:56:02.273Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: a179388d1d4be6771706f150d0abfad46184d010
  summary: 7/7 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T11:55:38.868Z'
  generatedBy: claude
  forVersion: 4
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/AssetModuleStructureTest.java
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/config/AssetConfigTest.java
    e2e: []
  unitTestCases:
    - name: shouldHaveValidPomXmlWithRequiredDependencies
      assertions:
        - assertThat(pomFile).exists()
        - >-
          assertThat(pomContent).contains('<artifactId>ruoyi-asset</artifactId>')
        - assertThat(pomContent).contains('<parent>')
    - name: shouldHaveBasePackageStructure
      assertions:
        - assertThat(configPackage).exists()
        - assertThat(domainPackage).exists()
        - assertThat(mapperPackage).exists()
        - assertThat(servicePackage).exists()
        - assertThat(controllerPackage).exists()
        - assertThat(utilPackage).exists()
        - assertThat(constantPackage).exists()
    - name: shouldHaveDomainSubPackages
      assertions:
        - assertThat(entityPackage).exists()
        - assertThat(voPackage).exists()
        - assertThat(enumsPackage).exists()
    - name: shouldHaveServiceSubPackages
      assertions:
        - assertThat(serviceImplPackage).exists()
        - assertThat(serviceRulePackage).exists()
    - name: shouldConfigureMyBatisMapperScanning
      assertions:
        - assertThat(assetConfigClass).hasAnnotation(MapperScan.class)
        - >-
          assertThat(mapperScanAnnotation.basePackages()).contains('com.ruoyi.asset.mapper')
    - name: shouldBeIncludedInParentPom
      assertions:
        - >-
          assertThat(parentPomContent).contains('<module>ruoyi-modules/ruoyi-asset</module>')
    - name: shouldHaveAssetConfigurationClass
      assertions:
        - assertThat(assetConfigClass).exists()
        - assertThat(assetConfigClass).hasAnnotation(Configuration.class)
    - name: shouldCompileSuccessfully
      assertions:
        - assertThat(mavenBuildResult.getExitCode()).isEqualTo(0)
  e2eScenarios: []
  frameworkHint: junit5-surefire
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
