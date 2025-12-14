---
id: core.project-init
module: core
priority: 1
status: passing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: []
verification:
  verifiedAt: '2025-12-14T04:30:44.570Z'
  verdict: needs_review
  verifiedBy: codex
  commitHash: ea8553f8ade20d0b18da9a96bc741ceba48fa61e
  summary: 0/0 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T04:27:47.734Z'
  generatedBy: codex
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/core/project-init.test.ts
    e2e: []
  unitTestCases:
    - name: should scaffold the base RuoYi-Vue project directory structure
      assertions:
        - >-
          expect(result.createdPaths).toEqual(expect.arrayContaining(['ruoyi-admin',
          'ruoyi-common', 'ruoyi-framework', 'ruoyi-modules', 'ruoyi-ui']))
        - expect(result.errors).toHaveLength(0)
    - name: should create the ruoyi-asset module skeleton under ruoyi-modules
      assertions:
        - >-
          expect(result.createdPaths).toContain('ruoyi-modules/ruoyi-asset/pom.xml')
        - >-
          expect(result.createdPaths).toEqual(expect.arrayContaining(['ruoyi-modules/ruoyi-asset/src/main/java',
          'ruoyi-modules/ruoyi-asset/src/main/resources']))
    - name: should register ruoyi-asset in the Maven module aggregation
      assertions:
        - >-
          expect(readFile('ruoyi-modules/pom.xml')).toMatch(/<module>ruoyi-asset<\/module>/)
        - >-
          expect(readFile('ruoyi-modules/ruoyi-asset/pom.xml')).toMatch(/<artifactId>ruoyi-asset<\/artifactId>/)
    - name: should include a minimal backend API skeleton for ruoyi-asset
      assertions:
        - >-
          expect(result.createdPaths).toContain('ruoyi-modules/ruoyi-asset/src/main/java')
        - >-
          expect(result.createdFileContents['ruoyi-modules/ruoyi-asset/src/main/java/**/controller/**.java']).toMatch(/@RestController/)
  e2eScenarios: []
  frameworkHint: vitest
---
# Initialize RuoYi-Vue project with ruoyi-asset module skeleton
