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
tddGuidance:
  generatedAt: '2025-12-14T09:39:35.140Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/AssetModuleStructureTest.java
    e2e: []
  unitTestCases:
    - name: should have valid pom.xml with required dependencies
      assertions:
        - assertThat(pomFile).exists()
        - >-
          assertThat(pomContent).contains('<artifactId>ruoyi-asset</artifactId>')
        - >-
          assertThat(pomContent).contains('<artifactId>ruoyi-common</artifactId>')
    - name: should have correct package structure with all required packages
      assertions:
        - assertThat(configPackage).exists()
        - assertThat(domainPackage).exists()
        - assertThat(mapperPackage).exists()
        - assertThat(servicePackage).exists()
        - assertThat(controllerPackage).exists()
        - assertThat(utilPackage).exists()
        - assertThat(constantPackage).exists()
    - name: should have correct sub-package structure under domain and service
      assertions:
        - assertThat(entityPackage).exists()
        - assertThat(voPackage).exists()
        - assertThat(enumsPackage).exists()
        - assertThat(serviceImplPackage).exists()
        - assertThat(serviceRulePackage).exists()
    - name: should configure MyBatis mapper scanning annotation
      assertions:
        - assertThat(assetConfigClass).hasAnnotation(MapperScan.class)
        - >-
          assertThat(mapperScanAnnotation.basePackages()).contains('com.ruoyi.asset.mapper')
    - name: should be declared as module dependency in parent pom.xml
      assertions:
        - >-
          assertThat(parentPomContent).contains('<module>ruoyi-modules/ruoyi-asset</module>')
    - name: should have AssetConfig configuration class with proper annotations
      assertions:
        - assertThat(assetConfigClass).exists()
        - assertThat(assetConfigClass).hasAnnotation(Configuration.class)
        - assertThat(assetConfigClass).isInPackage('com.ruoyi.asset.config')
    - name: should compile successfully without errors
      assertions:
        - assertThat(mavenBuild.execute('compile')).isSuccessful()
        - assertThat(compilationErrors).isEmpty()
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
