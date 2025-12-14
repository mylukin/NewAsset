---
id: office.frontend-detail
module: office
priority: 55
status: passing
version: 24
origin: manual
dependsOn:
  - office.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:31:25.386Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 7634d2499041b82b89adc41a6d99717b795c8e84
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:31:17.582Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - >-
        src/test/java/com/example/newasset/office/OfficeAssetDetailDrawerTest.java
    e2e:
      - e2e/office/office-asset-detail.spec.ts
  unitTestCases:
    - name: shouldCreateDetailDrawerComponent
      assertions:
        - assertNotNull(detailDrawer)
        - assertTrue(detailDrawer instanceof OfficeAssetDetailDrawer)
    - name: shouldDisplayTabbedLayout
      assertions:
        - 'assertEquals(expectedTabCount, detailDrawer.getTabs().size())'
        - assertTrue(detailDrawer.hasTab("Details"))
        - assertTrue(detailDrawer.hasTab("History"))
    - name: shouldDisplayEditAndMaintenanceActionButtons
      assertions:
        - assertTrue(detailDrawer.hasActionButton("Edit"))
        - assertTrue(detailDrawer.hasActionButton("Initiate Maintenance"))
        - 'assertEquals(2, detailDrawer.getActionButtons().size())'
    - name: shouldDisplayUserAssignmentProminently
      assertions:
        - assertNotNull(detailDrawer.getUserAssignment())
        - assertTrue(detailDrawer.isUserAssignmentProminent())
        - 'assertEquals(expectedUser, detailDrawer.getAssignedUser())'
  e2eScenarios:
    - name: user opens detail drawer and sees tabbed layout
      steps:
        - navigate to office assets list
        - click on an asset row
        - verify drawer opens
        - verify tabs are visible
    - name: user clicks Edit button in detail drawer
      steps:
        - open asset detail drawer
        - click Edit button
        - verify edit form appears
    - name: user clicks Initiate Maintenance button
      steps:
        - open asset detail drawer
        - click Initiate Maintenance button
        - verify maintenance workflow starts
    - name: user sees assigned user displayed prominently
      steps:
        - open asset detail drawer
        - verify user assignment section is visible
        - verify assigned user name is displayed prominently
  frameworkHint: junit5
---
# Create Office Asset Detail Drawer

## Context

Detail view for office assets with user info and maintenance records.

## Acceptance Criteria

1. Create detail drawer component
2. Tabbed layout:
3. Action buttons: Edit, Initiate Maintenance
4. Display user assignment prominently
## Technical Notes

- Reference: PRD Section 6.6.2 Point 3
- Pattern: el-drawer with el-tabs
