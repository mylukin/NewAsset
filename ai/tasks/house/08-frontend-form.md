---
id: house.frontend-form
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn: [house.frontend-api]
supersedes: []
tags: [frontend, vue, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create House Asset Form Component

## Context

The form component handles both create and edit operations for house assets with proper validation.

## Acceptance Criteria

1. Create `/src/views/asset/house/components/HouseForm.vue`:

2. Implement form with all fields:

   **Basic Asset Info Section:**
   - Project (required, dropdown)
   - Asset Name (required)
   - Building (required)
   - Floor
   - Room No (required)
   - Location Description
   - Ownership Type (dropdown)
   - Owner Organization
   - Use Department (dropdown)
   - Duty Person (user selector)
   - Status (dropdown, disabled on create - auto set)

   **House Specific Section:**
   - Building Area (required, number)
   - Inner Area (number, <= building area)
   - House Type (dropdown)
   - House Usage (dropdown)
   - Current Usage (dropdown)
   - Current User
   - Contract No
   - Rent Unit Price
   - Rent Total

   **Value Section:**
   - Purchase Date (date picker)
   - Start Use Date (date picker)
   - Original Value
   - Depreciation Method (dropdown)

   **Other Section:**
   - Brand/Model
   - Supplier
   - Warranty Expire Date
   - Remark (textarea)

3. Implement validation rules:
   - Required fields validation
   - Inner area <= building area
   - Dates in valid range

4. Support edit mode - load existing data

5. Display generated asset code after save

6. Emit save/cancel events to parent

## Technical Notes

- Reference: PRD section 6.2.2 point 2
- Use Element UI form with el-form-item
