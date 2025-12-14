---
id: house.frontend-api
module: house
priority: 5
status: failing
version: 1
origin: manual
dependsOn: [house.controller]
supersedes: []
tags: [frontend, api, P0]
testRequirements:
  unit:
    required: false
    pattern: ""
---
# Create House Asset Frontend API Module

## Context

Frontend API module encapsulates all axios calls for house asset operations.

## Acceptance Criteria

1. Create `/src/api/asset/house.js`:

2. Implement API functions:
   ```javascript
   // List houses with pagination and filters
   export function listHouse(query) {
     return request({
       url: '/asset/house/list',
       method: 'get',
       params: query
     })
   }

   // Get house detail by ID
   export function getHouse(id) {
     return request({
       url: '/asset/house/' + id,
       method: 'get'
     })
   }

   // Create new house asset
   export function addHouse(data) {
     return request({
       url: '/asset/house',
       method: 'post',
       data: data
     })
   }

   // Update house asset
   export function updateHouse(data) {
     return request({
       url: '/asset/house',
       method: 'put',
       data: data
     })
   }

   // Delete house assets
   export function delHouse(ids) {
     return request({
       url: '/asset/house/' + ids,
       method: 'delete'
     })
   }

   // Export to Excel
   export function exportHouse(query) {
     return request({
       url: '/asset/house/export',
       method: 'post',
       params: query,
       responseType: 'blob'
     })
   }

   // Get statistics
   export function getHouseStatistics(projectId) {
     return request({
       url: '/asset/house/statistics',
       method: 'get',
       params: { projectId }
     })
   }

   // Copy house asset
   export function copyHouse(id) {
     return request({
       url: '/asset/house/' + id + '/copy',
       method: 'post'
     })
   }
   ```

3. Import RuoYi's request utility

## Technical Notes

- Reference: TECH.md section 7.3
- Follow RuoYi-Vue API conventions
