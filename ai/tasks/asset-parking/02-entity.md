---
id: asset-parking.entity
module: asset-parking
priority: 302
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - asset-parking.database-schema
  - core.asset-entity
supersedes: []
tags:
  - backend
  - entity
testRequirements:
  unit:
    required: false
    pattern: tests/asset-parking/**/*.test.*
tddGuidance:
  generatedAt: '2025-12-15T14:08:54.774Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/asset-parking/entity.test.ts
    e2e: []
  unitTestCases:
    - name: should have AssetParking entity class with required fields
      assertions:
        - expect(AssetParking).toBeDefined()
        - expect(typeof AssetParking.prototype.assetId).not.toBe('undefined')
    - name: should have AssetParkingMapper interface with CRUD methods
      assertions:
        - expect(AssetParkingMapper).toBeDefined()
        - expect(AssetParkingMapper.selectById).toBeDefined()
    - name: should have mapper XML file configured correctly
      assertions:
        - expect(mapperXmlExists).toBe(true)
        - expect(mapperNamespace).toContain('AssetParkingMapper')
    - name: should have AssetParkingDto and AssetParkingVo classes
      assertions:
        - expect(AssetParkingDto).toBeDefined()
        - expect(AssetParkingVo).toBeDefined()
    - name: should compile entity without type errors
      assertions:
        - expect(compileResult.success).toBe(true)
        - expect(compileResult.errors).toHaveLength(0)
  e2eScenarios: []
  frameworkHint: vitest
---
# Create Parking Asset Entity and Mapper

## Context

Java entity class for parking extension table. Linked to base Asset entity.

## Acceptance Criteria

1. Create `AssetParking` entity in `com.ruoyi.asset.domain.entity`:
2. Create `AssetParkingMapper` interface:
3. Create mapper XML file:
4. Create DTO/VO classes:
5. Entity compiles without errors
## Technical Notes

- Similar structure to AssetHouse entity
- Reference: ai/tasks/asset-house/02-entity.md
