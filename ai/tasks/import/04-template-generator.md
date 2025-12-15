---
id: import.template-generator
module: import
priority: 804
status: failing
version: 1
origin: spec-workflow
dependsOn:
  - import.excel-parser-service
tags:
  - backend
  - service
testRequirements:
  unit:
    required: false
    pattern: "tests/import/**/*.test.*"
---
# Implement Template Generator Service

## Context

Service for generating Excel import templates for each asset type.

## Acceptance Criteria

1. Create `TemplateGeneratorService`:
   - generateTemplate(AssetType type) - returns ByteArrayOutputStream
   - Templates pre-configured with headers and sample data

2. Create template row classes:
   - `HouseImportTemplate` - house-specific columns
   - `ParkingImportTemplate` - parking-specific columns
   - `FacilityImportTemplate` - facility-specific columns
   - `VenueImportTemplate` - venue-specific columns
   - `OfficeImportTemplate` - office-specific columns

3. Common template columns:
   - Asset Name (required)
   - Project Code
   - Building / Floor / Unit (location)
   - Status
   - Responsible Person
   - Purchase Date
   - Original Value
   - Remarks

4. Type-specific columns added per asset type:
   - House: building_area, inner_area, house_type, current_usage, current_user, rent_price
   - Parking: zone, parking_no, parking_type, area, current_user, plate_no
   - Facility: facility_type, device_code, install_location, maintenance_org, warranty_date
   - Venue: venue_type, capacity, usage_mode, open_time_desc
   - Office: office_type, serial_number, configuration, use_department

5. Include sample rows (1-2) showing valid data format

6. Add data validation dropdowns for dictionary fields (if supported)

## Technical Notes

- Use EasyExcel write API for template generation
- Chinese column headers with English mapping
