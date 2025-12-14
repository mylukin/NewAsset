---
id: house.export
module: house
priority: 7
status: failing
version: 1
origin: manual
dependsOn: [house.list]
supersedes: []
tags: [feature, export, backend]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Implement House Asset Excel Export

## Context

Users need to export house asset data to Excel for reporting and offline analysis. The export respects current search filters and data permissions.

## Acceptance Criteria

1. Backend API `POST /asset/house/export`:
   - Request body: same query params as list
   - Permission: @PreAuthorize("asset:house:export")
   - Returns Excel file download
2. Export implementation:
   - Use RuoYi's @Excel annotation on VO fields
   - Call ExcelUtil.exportExcel()
   - Include all visible columns
   - Apply same filters as list query
   - Data permission enforced
3. Excel columns:
   - Asset code, name, project
   - Building, floor, room number
   - Building area, inner area
   - House type, usage, current usage
   - Current user, contract number
   - Status, ownership type
   - Responsible person, department
   - Purchase date, original value
4. File naming: `house_assets_yyyyMMddHHmmss.xlsx`
5. Frontend:
   - Export button in toolbar
   - Shows loading during export
   - Auto-download on completion
   - Error message on failure

## Technical Notes

- RuoYi's ExcelUtil handles streaming
- Large exports use SXSSFWorkbook
- Consider async export for >10000 records
