---
id: asset-house.frontend-form
module: asset-house
priority: 208
status: passing
version: 4
origin: spec-workflow
dependsOn:
  - asset-house.frontend-list
supersedes: []
tags:
  - frontend
  - vue
testRequirements:
  e2e:
    required: false
    pattern: e2e/asset/**/*.spec.ts
    tags:
      - '@house'
verification:
  verifiedAt: '2025-12-15T13:58:04.587Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: aaf6f054158ad40c3d9313c7a6691bb2088c70ea
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T13:56:48.712Z'
  generatedBy: claude
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/asset-house/frontend-form.test.ts
    e2e:
      - e2e/asset-house/frontend-form.spec.ts
  unitTestCases:
    - name: should create form component with basic structure
      assertions:
        - expect(component.exists()).toBe(true)
        - expect(component.find('form').exists()).toBe(true)
    - name: should render all required form sections
      assertions:
        - expect(component.find('.form-section').exists()).toBe(true)
        - expect(component.findAll('.form-section').length).toBeGreaterThan(0)
    - name: should implement location cascader component
      assertions:
        - expect(component.find('LocationCascader').exists()).toBe(true)
        - expect(component.vm.locationValue).toBeDefined()
    - name: should validate form fields correctly
      assertions:
        - expect(component.vm.errors).toBeDefined()
        - expect(component.validate()).toBe(false)
        - expect(component.validate()).toBe(true)
    - name: should render footer actions with correct buttons
      assertions:
        - expect(component.find('.form-footer').exists()).toBe(true)
        - 'expect(component.find(''button[type="submit"]'').exists()).toBe(true)'
        - 'expect(component.find(''button[type="button"]'').exists()).toBe(true)'
    - name: should handle form submission correctly
      assertions:
        - expect(component.vm.onSubmit).toBeDefined()
        - await component.vm.onSubmit()
        - expect(submitMethod).toHaveBeenCalled()
  e2eScenarios:
    - name: user can fill out and submit the form
      steps:
        - navigate to form page
        - fill out all required fields
        - select location from cascader
        - click submit button
        - verify success message is displayed
    - name: user sees validation errors for invalid input
      steps:
        - navigate to form page
        - leave required fields empty
        - attempt to submit form
        - verify validation error messages appear
  frameworkHint: vitest
---
# Implement House Asset Form Component (Frontend)

## Context

Create/Edit form component for house assets. Used in drawer mode from list page.

## Acceptance Criteria

1. Create form component `src/views/asset/house/form.vue`:
2. Form sections:
3. Location cascader:
4. Form validation:
5. Footer actions:
6. On submit:
## Technical Notes

- Reference: ai/tasks/spec/UX-DESIGN.md (Asset Form)
- Uses el-form, el-cascader, el-upload components
