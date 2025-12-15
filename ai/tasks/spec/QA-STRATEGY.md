# QA Strategy

## Risk Assessment

| Risk | Likelihood | Impact | Mitigation | Testing |
|------|------------|--------|------------|---------|
| Duplicate asset codes under concurrency | Medium | Critical | Optimistic lock + unique index | Load test 50 concurrent creates |
| Invalid state transitions | Medium | High | State machine validation | Unit tests all transitions |
| Status linkage fails | Medium | High | Transactional updates | Integration tests lifecycle |
| Data leakage between projects | Low | Critical | Project-scoped filters | Security tests cross-project |
| Excel import corrupts data | Medium | Medium | Batch processing + rollback | Import tests malformed data |
| Performance at 5K assets | Low | Medium | Pagination + indexes | Load test 5K+ records |
| File upload bypass | Low | Low | Server-side validation | Boundary tests limits |

---

## Test Strategy

### Unit Tests

**Coverage Target:** 90% core modules, 80% CRUD operations

**Framework:** JUnit 5 + Mockito

**Key Test Classes:**
```
tests/asset/
├── core/
│   ├── AssetCodeGeneratorTest.java
│   ├── AssetStatusServiceTest.java
│   └── AssetStatusEnumTransitionTest.java
├── maintenance/
│   ├── MaintOrderStatusMachineTest.java
│   ├── MaintOrderServiceTest.java
│   └── StatusLinkageServiceTest.java
├── house/AssetHouseServiceTest.java
├── parking/AssetParkingServiceTest.java
├── facility/AssetFacilityServiceTest.java
├── venue/AssetVenueServiceTest.java
├── office/AssetOfficeServiceTest.java
└── import/ExcelImportServiceTest.java
```

**Critical Test Cases:**

| Component | Test Case | Expected |
|-----------|-----------|----------|
| AssetCodeGenerator | Valid inputs | Returns HA-000001 format |
| AssetCodeGenerator | No project | Throws ServiceException |
| AssetCodeGenerator | Concurrent (mock) | No duplicates |
| AssetStatusService | AVAILABLE_IDLE -> MAINTAINING | Success |
| AssetStatusService | SCRAPPED -> AVAILABLE_SELF | Exception |
| MaintOrderService | Create with linkage | Asset status -> FAULT |
| MaintOrderService | Close order | Asset status restored |
| MaintOrderService | Delete asset with open WO | Blocked |

### Integration Tests

**Framework:** Spring Boot Test + TestContainers (MySQL)

**Test Scenarios:**

| Flow | Scenario | Modules |
|------|----------|---------|
| Asset Creation | Create -> Verify code -> Verify DB | core, house |
| Work Order Lifecycle | Create -> Assign -> Accept -> Complete -> Verify | maintenance, core |
| Status Linkage | Create order -> Close -> Verify restore | maintenance <-> core |
| Excel Import | Import 1000 rows -> Verify codes unique | import, core |
| Data Permission | Query as different roles -> Verify isolation | All |

### E2E Tests

**Framework:** Playwright (Vue2 + Element UI compatible)

**Test Structure:**
```
e2e/
├── auth/login.spec.ts
├── asset/
│   ├── house-crud.spec.ts
│   ├── parking-crud.spec.ts
│   └── asset-search.spec.ts
├── maintenance/
│   ├── work-order-lifecycle.spec.ts
│   └── status-linkage.spec.ts
├── import/excel-import.spec.ts
├── dashboard/stats-display.spec.ts
└── permissions/role-restrictions.spec.ts
```

### Performance Tests

**Targets:**
- List API: < 3s for 5K records
- Code generation: < 500ms under 50 concurrent
- Excel import: 1000 rows < 30s
- Dashboard stats: < 2s

**Scenarios:**

| Scenario | Concurrent | Duration | Success Criteria |
|----------|------------|----------|------------------|
| Asset List Query | 20 | 5 min | P95 < 3s, 0% errors |
| Code Generation | 50 | 2 min | No duplicates, P95 < 500ms |
| Work Order Creation | 30 | 5 min | P95 < 2s |
| Dashboard Refresh | 10 | 5 min | P95 < 2s |
| Excel Import (1000) | 5 | 3 min | < 30s each |

### Security Tests

**Tools:** OWASP ZAP, manual penetration

**Checklist:**

| Check | Method | Expected |
|-------|--------|----------|
| SQL Injection | ZAP + manual | Inputs sanitized |
| XSS | ZAP on forms | No script execution |
| CSRF | Verify tokens | 403 without token |
| Auth Bypass | Direct API calls | 401 response |
| Horizontal Privilege | User A -> User B data | 403 or empty |
| Vertical Privilege | Operations -> Admin API | 403 response |
| File Upload | .exe, oversized | Rejected |
| Rate Limiting | 100 req/sec login | Throttled |

---

## Edge Cases

| Scenario | Expected | Test Approach |
|----------|----------|---------------|
| Sequence overflow (999999) | Error gracefully | Unit test max seq |
| Concurrent WO status change | One succeeds, one conflict | Integration parallel threads |
| Import with duplicate codes | Skip, report in summary | Import mixed data |
| Network failure mid-import | Rollback, no partial | Mock failure, verify |
| WO on scrapped asset | Blocked with error | Unit test business rule |
| Linkage disabled mid-flow | No status change | Config change test |
| Invalid location hierarchy | Validation error | Input validation test |
| Empty search results | Empty list, not error | UI graceful state |
| Session timeout in form | Redirect, preserve draft | E2E expired token |
| Soft-deleted in reports | Excluded from stats | Dashboard accuracy |

---

## Quality Gates

### PR Merge
- [ ] All unit tests pass (`CI=true mvn test`)
- [ ] Coverage >= 80% overall, >= 90% core
- [ ] No critical/high security vulns (Snyk)
- [ ] Checkstyle/PMD 0 errors
- [ ] No TODO/FIXME in code
- [ ] API docs updated (Swagger)

### Staging Deploy
- [ ] All integration tests pass
- [ ] E2E smoke tests pass
- [ ] Performance baseline met
- [ ] No regression

### Production Release
- [ ] All E2E tests pass
- [ ] Performance under load
- [ ] Security scan clean
- [ ] Accessibility audit (WCAG 2.1 AA)
- [ ] Data migration verified
- [ ] Rollback tested
- [ ] Monitoring configured

---

## Test Data

### Seed Data

| Entity | Count | Purpose |
|--------|-------|---------|
| Projects | 3 | Data isolation |
| Users per role | 2 each (10) | Role permissions |
| House assets | 500 | List pagination |
| Parking assets | 300 | Cross-type queries |
| Work orders | 200 (mixed) | Filtering, lifecycle |
| Attachments | 50 | File handling |

### Test Files
- `valid_import_100.xlsx` - 100 valid records
- `valid_import_1000.xlsx` - 1000 records (performance)
- `invalid_import.xlsx` - Mixed valid/invalid/duplicate
- `oversized_import.xlsx` - 10,000 records (boundary)

---

## Acceptance Verification

### PM Metrics

| Metric | Verification |
|--------|--------------|
| Code uniqueness | DB query: no duplicates |
| WO completion rate | Dashboard matches DB count |
| Data isolation | Role A query Project B = empty |
| Import accuracy | Count matches file rows |

### UX Flows

| Flow | Verification |
|------|--------------|
| Asset creation | E2E: code displayed after save |
| WO timeline | E2E: all status changes in log |
| Dashboard refresh | E2E: button triggers reload |
| Location hierarchy | Form: 3-level cascade works |
| Error states | Element UI messages inline |

### Tech APIs

| API | Verification |
|-----|--------------|
| GET /asset/house/list | < 3s with 5K, pagination works |
| POST /asset/house | 201 with generated code |
| PUT /asset/maint/order/{id}/status | State machine enforced |
| POST /asset/import/excel | Result with success/fail counts |
| GET /asset/dashboard/stats | Metrics match DB |
