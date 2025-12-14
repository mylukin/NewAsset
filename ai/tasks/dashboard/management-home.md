---
id: dashboard.management-home
module: dashboard
priority: 1
status: passing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: []
tddGuidance:
  generatedAt: '2025-12-14T04:56:16.890Z'
  generatedBy: codex
  forVersion: 1
  suggestedTestFiles:
    unit:
      - tests/dashboard/management-home.test.ts
    e2e:
      - e2e/dashboard/management-home.spec.ts
  unitTestCases:
    - name: should transform raw dashboard metrics into KPI card view models
      assertions:
        - expect(viewModel.kpis).toHaveLength(4)
        - >-
          expect(viewModel.kpis.find(k => k.id ===
          'totalAssets')?.label).toBe('Total Assets')
        - >-
          expect(viewModel.kpis.find(k => k.id ===
          'totalAssets')?.value).toBe(123)
        - >-
          expect(viewModel.kpis.find(k => k.id ===
          'assetsInUse')?.value).toBe(45)
    - name: should generate chart options that match the provided trend dataset
      assertions:
        - >-
          expect(options.xAxis.data).toEqual(['2025-12-01', '2025-12-02',
          '2025-12-03'])
        - expect(options.series).toHaveLength(1)
        - 'expect(options.series[0].name).toBe(''Assets Added'')'
        - 'expect(options.series[0].data).toEqual([3, 8, 2])'
    - name: >-
        should compute the correct query params when a date range filter is
        applied
      assertions:
        - >-
          expect(params).toEqual({ from: '2025-12-01', to: '2025-12-31',
          timezone: 'Asia/Shanghai' })
        - >-
          expect(serializeQuery(params)).toBe('from=2025-12-01&to=2025-12-31&timezone=Asia%2FShanghai')
    - name: 'should derive UI state for loading, success, and error cases'
      assertions:
        - >-
          expect(deriveManagementHomeState({ data: null, loading: true, error:
          null })).toBe('loading')
        - >-
          expect(deriveManagementHomeState({ data: { kpis: [], charts: [] },
          loading: false, error: null })).toBe('ready')
        - >-
          expect(deriveManagementHomeState({ data: null, loading: false, error:
          { message: 'Network error' } })).toBe('error')
  e2eScenarios:
    - name: user views the management dashboard and sees KPI cards and charts
      steps:
        - navigate to /dashboard/management-home
        - wait for KPI cards to finish loading
        - >-
          verify KPI card labeled 'Total Assets' is visible and shows a numeric
          value
        - verify chart container labeled 'Assets Trend' is visible
        - >-
          verify at least one chart series/legend item is visible (e.g., 'Assets
          Added')
    - name: user changes the date range filter and the charts update
      steps:
        - navigate to /dashboard/management-home
        - open the date range filter
        - select 'Last 30 days' and apply
        - verify the chart shows updated x-axis labels for the new range
        - >-
          verify KPI values refresh (at least one KPI value changes or a refresh
          timestamp updates)
    - name: user encounters an API error and can retry to recover
      steps:
        - >-
          navigate to /dashboard/management-home with the metrics API failing
          (mock 500)
        - >-
          verify an error banner/message is displayed (e.g., 'Failed to load
          dashboard')
        - click the 'Retry' button
        - mock the next metrics API call as success
        - verify KPI cards and charts render after retry
  frameworkHint: vitest
---
# Implement management dashboard with key metrics and charts
