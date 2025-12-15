---
id: core.location-service
module: core
priority: 106
status: passing
version: 2
origin: spec-workflow
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
tddGuidance:
  generatedAt: '2025-12-15T15:25:37.032Z'
  generatedBy: claude
  forVersion: 2
  suggestedTestFiles:
    unit:
      - tests/core/location-service.test.ts
    e2e: []
  unitTestCases:
    - name: >-
        should create AssetLocation entity with required fields (id, name,
        parentId, level, path)
      assertions:
        - expect(location.id).toBeDefined()
        - expect(location.name).toBe('Building A')
        - expect(location.parentId).toBeNull()
        - expect(location.level).toBe(0)
    - name: should map AssetLocation to database via AssetLocationMapper
      assertions:
        - expect(mapper.insert).toBeDefined()
        - expect(mapper.selectById).toBeDefined()
        - expect(mapper.selectByParentId).toBeDefined()
        - expect(mapper.selectAll).toBeDefined()
    - name: should provide CRUD operations via AssetLocationService
      assertions:
        - expect(service.create(location)).resolves.toBeDefined()
        - 'expect(service.getById(1)).resolves.toMatchObject({ id: 1 })'
        - expect(service.update(location)).resolves.toBe(true)
        - expect(service.delete(1)).resolves.toBe(true)
    - name: should return locations as tree structure with nested children
      assertions:
        - expect(tree).toHaveLength(1)
        - 'expect(tree[0].children).toBeDefined()'
        - 'expect(tree[0].children[0].parentId).toBe(tree[0].id)'
        - 'expect(tree[0].children[0].level).toBe(tree[0].level + 1)'
    - name: >-
        should validate location name is not empty and parentId references
        existing location
      assertions:
        - >-
          expect(() => service.create({ name: '' })).rejects.toThrow('Name is
          required')
        - >-
          expect(() => service.create({ parentId: 999
          })).rejects.toThrow('Parent location not found')
        - >-
          expect(() => service.create({ name: 'Valid', parentId: null
          })).resolves.toBeDefined()
  e2eScenarios: []
  frameworkHint: vitest
---
# Implement Location Hierarchy Service

## Context

3-level location hierarchy (Building → Floor → Unit) management. Supports the cascader component in frontend forms.

## Acceptance Criteria

1. Create `AssetLocation` entity in `com.ruoyi.asset.domain.entity`:
2. Create `AssetLocationMapper` interface:
3. Create `AssetLocationService` in `com.ruoyi.asset.service`:
4. Tree structure response format:
5. Validation:
## Technical Notes

- Reference: ai/tasks/spec/OVERVIEW.md (3-level hierarchy decision)
- UX: ai/tasks/spec/UX-DESIGN.md (Cascader component)

## Notes

Verification failed: Implementation complete - AssetLocation entity/mapper/service created with 3-level hierarchy support and tree structure
