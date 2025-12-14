---
id: parking.frontend-detail
module: parking
priority: 37
status: passing
version: 24
origin: manual
dependsOn:
  - parking.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/parking/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:04:38.933Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: d817babe35cb0b01e13aafeaf6708b3b065935b8
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:04:30.559Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/parking/frontend-detail.test.ts
    e2e:
      - e2e/parking/frontend-detail.spec.ts
  unitTestCases:
    - name: should render detail drawer component
      assertions:
        - expect(drawer).toBeDefined()
        - expect(drawer.isVisible()).toBe(true)
    - name: should display tabbed layout with correct tabs
      assertions:
        - expect(tabs).toHaveLength(expectedTabCount)
        - expect(tabNames).toContain('Basic Info')
        - expect(tabNames).toContain('History')
    - name: should render Edit and Initiate Maintenance action buttons
      assertions:
        - expect(editButton).toBeDefined()
        - expect(maintenanceButton).toBeDefined()
        - expect(editButton.textContent).toBe('Edit')
        - expect(maintenanceButton.textContent).toBe('Initiate Maintenance')
    - name: should display usage info with plate number and current user prominently
      assertions:
        - expect(usageSection).toBeDefined()
        - expect(plateNoElement.textContent).toBe(mockAsset.plateNo)
        - expect(currentUserElement.textContent).toBe(mockAsset.currentUser)
  e2eScenarios:
    - name: user opens parking asset detail drawer
      steps:
        - navigate to parking asset list
        - click on asset row
        - verify drawer opens with asset details
    - name: user navigates between tabs in detail drawer
      steps:
        - open detail drawer
        - click on each tab
        - verify tab content changes accordingly
    - name: user clicks Edit button in detail drawer
      steps:
        - open detail drawer
        - click Edit button
        - verify edit mode is activated or edit dialog opens
    - name: user clicks Initiate Maintenance button
      steps:
        - open detail drawer
        - click Initiate Maintenance button
        - verify maintenance workflow is triggered
    - name: user sees usage information prominently displayed
      steps:
        - open detail drawer
        - verify plate number is visible
        - verify current user is displayed prominently
  frameworkHint: vitest
---
# Create Parking Asset Detail Drawer

## Context

Detail view for parking assets with maintenance records.

## Acceptance Criteria

1. Create detail drawer component
2. Tabbed layout:
3. Action buttons: Edit, Initiate Maintenance
4. Display usage info prominently (plate no, current user)
## Technical Notes

- Reference: PRD Section 6.4.2 Point 3
- Pattern: el-drawer with el-tabs
