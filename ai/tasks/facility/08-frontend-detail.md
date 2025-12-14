---
id: facility.frontend-detail
module: facility
priority: 27
status: passing
version: 23
origin: manual
dependsOn:
  - facility.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/facility/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:46:51.441Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: b8309eade399adcdca57b3f752a852e68163632d
  summary: 4/4 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:45:57.871Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/modules/facility/__tests__/FacilityAssetDetailDrawer.test.tsx
    e2e:
      - e2e/facility/facility-asset-detail.spec.ts
  unitTestCases:
    - name: should render detail drawer component when open
      assertions:
        - expect(screen.getByRole('dialog')).toBeInTheDocument()
        - expect(screen.getByTestId('facility-detail-drawer')).toBeVisible()
    - name: should display tabbed layout with correct tabs
      assertions:
        - expect(screen.getByRole('tablist')).toBeInTheDocument()
        - expect(screen.getAllByRole('tab')).toHaveLength(expectedTabCount)
        - >-
          expect(screen.getByRole('tab', { name: /basic info/i
          })).toBeInTheDocument()
    - name: should render Edit action button
      assertions:
        - >-
          expect(screen.getByRole('button', { name: /edit/i
          })).toBeInTheDocument()
        - >-
          expect(screen.getByRole('button', { name: /edit/i
          })).not.toBeDisabled()
    - name: should render Initiate Maintenance action button
      assertions:
        - >-
          expect(screen.getByRole('button', { name: /initiate maintenance/i
          })).toBeInTheDocument()
        - >-
          expect(screen.getByRole('button', { name: /initiate maintenance/i
          })).not.toBeDisabled()
    - name: should render Upload Attachment action button
      assertions:
        - >-
          expect(screen.getByRole('button', { name: /upload attachment/i
          })).toBeInTheDocument()
        - >-
          expect(screen.getByRole('button', { name: /upload attachment/i
          })).not.toBeDisabled()
    - name: should display maintenance org contact prominently
      assertions:
        - expect(screen.getByTestId('maintenance-org-contact')).toBeVisible()
        - >-
          expect(screen.getByText(mockFacility.maintenanceOrgContact)).toBeInTheDocument()
  e2eScenarios:
    - name: user opens facility detail drawer from list
      steps:
        - navigate to facility asset list page
        - click on a facility row
        - verify detail drawer opens with correct facility data
    - name: user switches between tabs in detail drawer
      steps:
        - open facility detail drawer
        - click on each tab
        - verify tab content changes appropriately
    - name: user clicks Edit button to open edit form
      steps:
        - open facility detail drawer
        - click Edit button
        - verify edit form dialog opens
    - name: user initiates maintenance from detail drawer
      steps:
        - open facility detail drawer
        - click Initiate Maintenance button
        - verify maintenance workflow is triggered
    - name: user uploads attachment from detail drawer
      steps:
        - open facility detail drawer
        - click Upload Attachment button
        - verify file upload dialog opens
    - name: user views maintenance org contact for quick access
      steps:
        - open facility detail drawer
        - verify maintenance org contact is displayed prominently
        - verify contact information is clickable/accessible
  frameworkHint: vitest
---
# Create Facility Asset Detail Drawer

## Context

Detail view for facility assets with maintenance records and attachments.

## Acceptance Criteria

1. Create detail drawer component
2. Implement tabbed layout:
3. Action buttons: Edit, Initiate Maintenance, Upload Attachment
4. Display maintenance org contact prominently for quick access
## Technical Notes

- Reference: PRD Section 6.3.2 Point 3
- Pattern: el-drawer with el-tabs
