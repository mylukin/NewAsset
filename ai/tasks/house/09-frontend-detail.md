---
id: house.frontend-detail
module: house
priority: 18
status: passing
version: 24
origin: manual
dependsOn:
  - house.frontend-api
supersedes: []
tags:
  - frontend
  - vue
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:29:05.874Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 57d8cce5571df0440a28237062aa2fe460f1519d
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:28:10.928Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/house/HouseDetailDrawer.test.js
    e2e:
      - e2e/house/house-detail.spec.js
  unitTestCases:
    - name: should render detail drawer component when opened
      assertions:
        - expect(wrapper.find('.detail-drawer').exists()).toBe(true)
        - expect(wrapper.props('visible')).toBe(true)
    - name: >-
        should implement tabbed layout with Basic Info, Usage & Rental, and
        Maintenance tabs
      assertions:
        - expect(wrapper.findAll('.el-tabs__item').length).toBe(3)
        - expect(wrapper.find('.el-tabs__item').text()).toContain('Basic Info')
    - name: should display area fields with sqm suffix formatting
      assertions:
        - >-
          expect(wrapper.find('[data-test="building-area"]').text()).toContain('sqm')
        - >-
          expect(wrapper.find('[data-test="inner-area"]').text()).toContain('sqm')
    - name: should display currency fields with ¥ prefix formatting
      assertions:
        - 'expect(wrapper.find(''[data-test="rent-total"]'').text()).toMatch(/^¥/)'
        - >-
          expect(wrapper.find('[data-test="original-value"]').text()).toMatch(/^¥/)
    - name: 'should render Edit button with asset:house:edit permission'
      assertions:
        - 'expect(wrapper.find(''[data-test="edit-btn"]'').exists()).toBe(true)'
        - >-
          expect(wrapper.find('[data-test="edit-btn"]').attributes('v-hasPermi')).toContain('asset:house:edit')
    - name: 'should render Delete button with asset:house:remove permission'
      assertions:
        - 'expect(wrapper.find(''[data-test="delete-btn"]'').exists()).toBe(true)'
        - >-
          expect(wrapper.find('[data-test="delete-btn"]').attributes('v-hasPermi')).toContain('asset:house:remove')
    - name: should display asset code prominently in header
      assertions:
        - expect(wrapper.find('.detail-header .asset-code').exists()).toBe(true)
        - >-
          expect(wrapper.find('.detail-header
          .asset-code').classes()).toContain('prominent')
    - name: should render maintenance orders link in Maintenance tab
      assertions:
        - >-
          expect(wrapper.find('[data-test="maintenance-link"]').exists()).toBe(true)
        - >-
          expect(wrapper.find('[data-test="maintenance-link"]').attributes('href')).toContain('/maintenance/')
  e2eScenarios:
    - name: user can open detail drawer and view tabbed layout
      steps:
        - navigate to house asset list page
        - click View button on first row
        - verify detail drawer opens
        - verify Basic Info tab is active
        - click Usage & Rental tab
        - verify tab content changes
        - click Maintenance tab
        - verify maintenance content displays
    - name: user can see properly formatted fields in detail drawer
      steps:
        - open detail drawer for asset with rental data
        - verify asset code is displayed prominently in header
        - verify building area shows 'sqm' suffix
        - verify rent total shows '¥' prefix
        - verify date fields are formatted correctly
    - name: user can click Edit button to open edit form
      steps:
        - open detail drawer
        - click Edit button
        - verify edit dialog opens
        - verify form is populated with asset data
    - name: user can click Delete button with confirmation
      steps:
        - open detail drawer
        - click Delete button
        - verify confirmation dialog appears
        - click confirm
        - verify drawer closes
        - verify asset removed from list
    - name: user can navigate to related maintenance orders
      steps:
        - open detail drawer for asset with maintenance records
        - click Maintenance tab
        - verify maintenance orders list displays
        - click on maintenance order link
        - verify navigation to maintenance detail page
  frameworkHint: jest
---
# Create House Asset Detail Page/Drawer

## Context

Detail view showing all house asset information, maintenance records, and attachments with action buttons.

## Acceptance Criteria

1. Create detail drawer or modal component
2. Implement tabbed layout:
3. Display fields with proper formatting:
4. Action buttons:
5. Show asset code prominently
6. Link to related maintenance orders
## Technical Notes

- Reference: PRD Section 6.2.2 Point 3
- Pattern: el-drawer with el-tabs
- Integration: Call maintenance API for records
