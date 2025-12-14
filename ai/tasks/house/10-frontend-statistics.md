---
id: house.frontend-statistics
module: house
priority: 19
status: passing
version: 23
origin: manual
dependsOn:
  - house.frontend-api
  - house.statistics
supersedes: []
tags:
  - frontend
  - statistics
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/house/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:31:59.780Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: 959de948ff0224198e6f84374eb063ca4df3ce83
  summary: 6/6 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:29:30.290Z'
  generatedBy: claude
  forVersion: 21
  suggestedTestFiles:
    unit:
      - tests/house/statistics.test.ts
    e2e:
      - e2e/house/statistics.spec.ts
  unitTestCases:
    - name: should render statistics component with metric cards
      assertions:
        - expect(wrapper.findAll('.metric-card')).toHaveLength(expectedCount)
        - expect(wrapper.find('.statistics-container').exists()).toBe(true)
    - name: should display all required metric values
      assertions:
        - >-
          expect(wrapper.find('[data-testid="total-houses"]').text()).toContain(mockStats.totalHouses)
        - >-
          expect(wrapper.find('[data-testid="total-value"]').text()).toContain(mockStats.totalValue)
    - name: should render project selector and emit filter event on change
      assertions:
        - >-
          expect(wrapper.find('[data-testid="project-selector"]').exists()).toBe(true)
        - expect(wrapper.emitted('projectChange')).toBeTruthy()
    - name: should render visual indicators for statistics
      assertions:
        - 'expect(wrapper.find(''.progress-bar, .pie-chart'').exists()).toBe(true)'
        - >-
          expect(wrapper.find('[data-testid="visual-indicator"]').attributes('value')).toBeDefined()
    - name: should refresh statistics data when project selection changes
      assertions:
        - expect(fetchStatsSpy).toHaveBeenCalledTimes(2)
        - expect(fetchStatsSpy).toHaveBeenLastCalledWith(newProjectId)
    - name: should display loading state while fetching data
      assertions:
        - expect(wrapper.find('.loading-spinner').exists()).toBe(true)
        - expect(wrapper.find('.metric-card').exists()).toBe(false)
    - name: should display error state when data fetch fails
      assertions:
        - expect(wrapper.find('.error-message').exists()).toBe(true)
        - >-
          expect(wrapper.find('.error-message').text()).toContain('Failed to
          load')
  e2eScenarios:
    - name: user views house statistics dashboard
      steps:
        - navigate to house statistics page
        - verify metric cards are visible
        - verify statistics values are displayed
    - name: user filters statistics by project
      steps:
        - navigate to house statistics page
        - click project selector dropdown
        - select a specific project
        - verify statistics update with filtered data
    - name: user sees visual indicators for metrics
      steps:
        - navigate to house statistics page
        - verify progress bars or pie charts are rendered
        - verify visual indicators reflect actual data percentages
    - name: user experiences loading and error states
      steps:
        - navigate to house statistics page with slow network
        - verify loading spinner appears
        - simulate network error
        - verify error message is displayed
  frameworkHint: vitest
---
# Create House Asset Statistics View

## Context

Statistics dashboard showing house asset metrics like vacancy rates and usage distribution.

## Acceptance Criteria

1. Create statistics component (can be in index.vue or separate)
2. Display metric cards:
3. Add project selector to filter statistics
4. Use visual indicators (progress bars or pie charts)
5. Auto-refresh on project change
6. Handle loading and error states
## Technical Notes

- Reference: PRD Section 6.2.2 Point 4
- Pattern: Dashboard cards with metrics
- Optional: Use ECharts for pie chart visualization
