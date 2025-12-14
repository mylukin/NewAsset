---
id: core.dev-seed-data
module: core
priority: 10
status: failing
version: 3
origin: manual
dependsOn:
  - core.database-schema
  - core.dictionary-setup
supersedes: []
tags:
  - infrastructure
  - database
  - development
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
---
# Create Development Seed Data

## Context

Development environment requires sample data for immediate testing and development. This includes sample projects, assets of each type, and related reference data to enable developers to work without manual data entry.

## Acceptance Criteria

### Seed Data Files
1. Create `sql/seed-data-dev.sql` with sample data for development
2. Create `sql/seed-data-mysql.sql` (if MySQL syntax differs significantly)
3. Seed data should be idempotent (safe to run multiple times)

### Sample Projects
4. Create 2-3 sample projects with different characteristics:
   - Project A: Large mixed-use property (all asset types)
   - Project B: Residential complex (houses, parking, facilities)
   - Project C: Office building (offices, venues)

### Sample Assets (per project)
5. Create sample house assets (3-5 per project):
   - Various statuses (available, rented, under maintenance)
   - Different house types and usages
6. Create sample parking assets (5-10 per project):
   - Mix of fixed and temporary parking
   - Some with assigned users
7. Create sample facility assets (3-5 per project):
   - Elevators, fire equipment, HVAC systems
   - Include some with warranty expiring soon
8. Create sample venue assets (2-3 per project):
   - Meeting rooms, gyms, function halls
   - Different booking modes
9. Create sample office assets (5-10 per project):
   - Desks, computers, printers
   - Mix of assigned and unassigned

### Maintenance Sample Data
10. Create sample maintenance orders in various statuses:
    - Pending assignment
    - In progress
    - Completed
11. Create sample maintenance logs for completed orders

### Code Generation Rules
12. Create default asset code generation rules for each asset type
13. Initialize sequence counters for sample projects

### Reference Data
14. Ensure dictionary data is included or referenced:
    - Asset types, statuses, house usages, parking types, etc.

## Technical Notes

- Use INSERT statements with explicit column names
- Include DELETE statements at the top for idempotency (or use INSERT OR REPLACE for SQLite)
- Match foreign key references to RuoYi system tables (sys_dept, sys_user)
- Use realistic Chinese names and descriptions for better UX testing
- Date values should be relative or use recent dates

## Sample Data Volume

| Entity | Count | Notes |
|--------|-------|-------|
| Projects | 3 | Different property types |
| Houses | 10 | Across projects |
| Parking | 20 | Various types |
| Facilities | 15 | Include warranty scenarios |
| Venues | 8 | Different capacities |
| Offices | 25 | Mix of equipment types |
| Maintenance Orders | 10 | Various statuses |
| Attachments | 5 | Sample file references |
