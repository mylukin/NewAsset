---
id: maintenance.frontend-form
module: maintenance
priority: 65
status: passing
version: 24
origin: manual
dependsOn:
  - maintenance.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/maintenance/**/*.test.*
verification:
  verifiedAt: '2025-12-14T13:47:22.463Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 28504dc220cfb7886c0dbf937de8762b1fed8621
  summary: 5/5 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T13:47:13.722Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - src/test/java/com/example/maintenance/MaintenanceOrderFormTest.java
    e2e:
      - e2e/maintenance/order-form.spec.ts
  unitTestCases:
    - name: shouldRenderOrderCreationDialog
      assertions:
        - assertNotNull(dialog)
        - assertTrue(dialog.isVisible())
    - name: shouldContainRequiredFormFields
      assertions:
        - assertNotNull(form.getField('assetId'))
        - assertNotNull(form.getField('orderType'))
        - assertNotNull(form.getField('priority'))
        - assertNotNull(form.getField('description'))
    - name: shouldPreFillAssetWhenCreatedFromAssetDetailPage
      assertions:
        - 'assertEquals(expectedAssetId, form.getField(''assetId'').getValue())'
        - assertTrue(form.getField('assetId').isReadOnly())
    - name: shouldValidateRequiredFields
      assertions:
        - assertFalse(form.isValid())
        - assertTrue(form.getErrors().contains('assetId is required'))
        - assertTrue(form.getErrors().contains('orderType is required'))
    - name: shouldPreviewOrderNumberGeneration
      assertions:
        - assertNotNull(form.getPreviewOrderNumber())
        - 'assertTrue(form.getPreviewOrderNumber().matches(''MO-\d{8}-\d{4}''))'
  e2eScenarios:
    - name: user creates maintenance order via dialog
      steps:
        - navigate to maintenance orders page
        - click 'Create Order' button
        - verify dialog opens
        - fill in required fields
        - verify order number preview displays
        - click 'Submit' button
        - verify success message appears
    - name: user creates maintenance order from asset detail page
      steps:
        - navigate to asset detail page
        - click 'Create Maintenance Order' button
        - verify dialog opens with asset pre-filled
        - verify asset field is read-only
        - fill remaining required fields
        - submit form
        - verify order created with correct asset
    - name: user sees validation errors for missing required fields
      steps:
        - open create order dialog
        - click submit without filling fields
        - verify validation errors display for required fields
        - fill required fields
        - verify validation errors clear
        - submit successfully
  frameworkHint: junit5
---
# Create Maintenance Order Form Dialog

## Context

Vue dialog for creating maintenance orders with asset selection.

## Acceptance Criteria

1. Create order creation dialog
2. Form fields:
3. Support creation from asset detail page (pre-filled asset)
4. Validation for required fields
5. Preview order number generation
## Technical Notes

- Reference: PRD Section 6.1.2 (新建工单)
- Pattern: el-dialog with el-form
- Asset selector: Custom component with search
