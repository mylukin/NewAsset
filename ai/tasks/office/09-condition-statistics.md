---
id: office.condition-statistics
module: office
priority: 56
status: failing
version: 10
origin: manual
dependsOn:
  - office.service
supersedes: []
tags:
  - statistics
  - p1
testRequirements:
  unit:
    required: false
    pattern: tests/office/**/*.test.*
---
# Implement Office Asset Condition Statistics (P1)

## Context

Track office asset condition rates by department and project.

## Acceptance Criteria

1. Add mapper method: `selectOfficeConditionStats(Long projectId, Long deptId)`
2. Add service method: `getOfficeConditionStatistics(Long projectId, Long deptId)`
3. Add controller endpoint: `GET /asset/office/statistics/condition`
4. Return statistics VO:
   - totalCount (总资产数)
   - normalCount (正常资产数)
   - faultCount (故障/维修中资产数)
   - conditionRate (完好率)
5. Support filter by project and department
6. Group by department for comparison

## Technical Notes

- Reference: PRD Section 6.6.2 Point 4
- Pattern: Aggregation query
- Calculation: conditionRate = normalCount / totalCount * 100
