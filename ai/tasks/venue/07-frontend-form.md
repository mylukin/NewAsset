---
id: venue.frontend-form
module: venue
priority: 45
status: passing
version: 24
origin: manual
dependsOn:
  - venue.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/venue/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:16:00.984Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 58902448e11b6a48fa5c4b97c1367796dba24127
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:15:51.720Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/venue/frontend-form.test.ts
      - src/components/venue/__tests__/VenueAssetFormDialog.test.tsx
    e2e:
      - e2e/venue/asset-form.spec.ts
  unitTestCases:
    - name: should render form dialog component with proper structure
      assertions:
        - expect(dialog).toBeInTheDocument()
        - expect(dialog).toHaveRole('dialog')
    - name: 'should display all required form fields (name, type, status, location)'
      assertions:
        - expect(getByLabelText('Name')).toBeInTheDocument()
        - expect(getByLabelText('Type')).toBeInTheDocument()
        - expect(getByLabelText('Status')).toBeInTheDocument()
        - expect(getByLabelText('Location')).toBeInTheDocument()
    - name: should display auto-generated asset code as read-only
      assertions:
        - expect(assetCodeField).toHaveAttribute('readonly')
        - expect(assetCodeField.value).toMatch(/^VA-\d+$/)
    - name: should validate required fields and show error messages
      assertions:
        - expect(getByText('Name is required')).toBeInTheDocument()
        - expect(submitButton).toBeDisabled()
    - name: should support add mode with empty form fields
      assertions:
        - expect(nameField.value).toBe('')
        - expect(dialog).toHaveTextContent('Add Venue Asset')
    - name: should support edit mode with pre-populated form fields
      assertions:
        - expect(nameField.value).toBe('Existing Asset')
        - expect(dialog).toHaveTextContent('Edit Venue Asset')
  e2eScenarios:
    - name: user creates a new venue asset via form dialog
      steps:
        - navigate to venue asset list page
        - click 'Add Asset' button
        - verify dialog opens with empty fields
        - 'fill in asset name, type, status, location'
        - verify auto-generated asset code is displayed
        - click submit button
        - verify dialog closes and new asset appears in list
    - name: user edits an existing venue asset via form dialog
      steps:
        - navigate to venue asset list page
        - click edit button on existing asset
        - verify dialog opens with pre-populated fields
        - modify asset name
        - click submit button
        - verify dialog closes and updated asset appears in list
    - name: user sees validation errors when submitting invalid form
      steps:
        - navigate to venue asset list page
        - click 'Add Asset' button
        - leave required fields empty
        - click submit button
        - verify validation error messages are displayed
        - verify dialog remains open
  frameworkHint: vitest
---
# Create Venue Asset Form Dialog

## Context

Vue dialog for creating and editing venue assets.

## Acceptance Criteria

1. Create form dialog component
2. Form fields:
3. Auto-generated asset code display
4. Form validation
5. Add and edit modes
## Technical Notes

- Reference: PRD Section 6.5.2
- Pattern: el-dialog with el-form
