---
id: facility.entity
module: facility
priority: 1
status: passing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: []
tddGuidance:
  generatedAt: '2025-12-14T04:53:51.377Z'
  generatedBy: codex
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/facility/facility.entity.test.ts
    e2e:
      - e2e/facility/facility.entity.spec.ts
  unitTestCases:
    - name: >-
        should create a facility asset entity that includes equipment and vendor
        information
      assertions:
        - expect(asset.facilityId).toBe("FAC-001")
        - expect(asset.equipment).toHaveLength(1)
        - 'expect(asset.equipment[0].name).toBe("Boiler Pump")'
        - 'expect(asset.equipment[0].serialNumber).toBe("SN-123")'
        - expect(asset.vendor.id).toBe("VEND-001")
        - expect(asset.vendor.name).toBe("Acme Industrial Supplies")
  e2eScenarios: []
  frameworkHint: vitest
---
# Create facility asset entity with equipment and vendor info
