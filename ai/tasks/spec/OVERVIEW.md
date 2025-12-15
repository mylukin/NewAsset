# Project Overview

## Requirement

Asset Management System (MVP) - A unified asset lifecycle management platform for property/facility management operations, managing 5 types of assets (House, Parking, Facility, Venue, Office) across 6 business modules with unified asset coding, 9-state status machine, and maintenance work order workflow.

> 资产管理系统（MVP）- 一个统一的资产全生命周期管理平台，服务于物业/资产运营工作，管理5类资产（房源、车库、配套、场馆、办公），跨6个业务模块，配备统一资产编码、9态状态机和维护工单流程。

---

## Product Manager Summary

### Goals

**Business Goals:**
- Establish single source of truth for asset data across 5 asset categories
- Enable accurate asset inventory and reconciliation
- Reduce time to generate operational reports from days to minutes
- Create audit trail for asset changes and maintenance activities
- Build foundation for future advanced analytics and integrations

**User Goals:**
- Asset Managers: Quickly register new assets with auto-generated codes
- Maintenance Staff: Receive clear work assignments with full asset context
- Operations: Access real-time vacancy rates without requesting reports
- Management: View key metrics on demand

### Scope

**MVP (P0):**
- Asset Coding: Configurable templates, auto-generation, uniqueness guarantee
- State Machine: 9 unified states, status change logging, work order linkage
- Work Order Management: Create/assign/accept/process/verify/close workflow
- 5 Asset Modules: House, Parking, Facility, Venue, Office (List/CRUD/Detail/Statistics)
- Dashboard: Role-based homepages with key metrics
- Attachments: Multi-file upload/preview/download
- Data Import: Hybrid (manual entry + Excel import)

**Out of Scope:**
- Contract management system (only store contract numbers)
- Booking/reservation system for venues and parking
- Mobile app and QR code scanning
- BI dashboards and advanced analytics
- Financial module integration

### Target Users

| Role | Primary Tasks | Access Level |
|------|---------------|--------------|
| System Admin | Configure system, manage users/roles/dictionaries | Full |
| Asset Manager | CRUD assets, create/verify work orders | Full (project-scoped) |
| Maintenance Staff | Accept/process work orders | Work orders + related assets |
| Operations Staff | View availability, occupancy rates | Read-only |
| Management | Dashboard view | Dashboard only |

---

## Architecture Summary

### Tech Stack

- **Backend:** Java 8+, Spring Boot 2.2.x, Spring Security + JWT, MyBatis 3.5.x, MySQL
- **Frontend:** Vue 2.6.x, Vue Router, Axios, Element UI 2.15.x
- **Platform:** RuoYi-Vue (authentication, authorization, dictionary, logging)
- **Testing:** JUnit 5, TestContainers, Playwright

### Module Structure

```
com.ruoyi.asset
├── core        # Base asset entity, code generator, status machine
├── house       # House asset extension
├── parking     # Parking asset extension
├── facility    # Facility asset extension
├── venue       # Venue asset extension
├── office      # Office asset extension
├── maintenance # Work order management
├── import      # Excel import/export
└── dashboard   # Statistics and KPIs
```

### Database Design

- `t_asset` - Base asset table (common fields)
- `t_asset_{type}` - Extension tables (1:1 with t_asset)
- `t_asset_attachment` - File attachments
- `t_asset_location` - 3-level location hierarchy
- `t_asset_maint_order` - Work orders
- `t_asset_maint_log` - Work order status log
- `t_asset_code_seq` - Sequence management

---

## Key Decisions

| Question | Decision | Rationale |
|----------|----------|-----------|
| Data Import Strategy | Hybrid (manual + Excel) | Different asset types have varying data availability |
| Work Order Assignment | Manual by manager | Simpler for MVP, no org structure mapping needed |
| Asset Approval | No approval (immediate active) | Reduce complexity for MVP |
| Project Management | Single project per user | Simpler UX, fixed context after login |
| Data Scale | Small (<5K assets) | Basic performance optimization sufficient |
| Asset Code Format | Simple Sequential (HA-000001) | Easy to read and communicate |
| Asset Code Scope | Global sequence | True uniqueness across all projects |
| Work Order Number | Date-based (WO-20240115-001) | Easy to identify creation date |
| Upload Limits | 10 files, 5MB each | Balanced usability and storage |
| Location Hierarchy | 3 levels (Building → Floor → Unit) | Sufficient for property management |
| Dashboard Refresh | Manual only | Simpler implementation for MVP |
| Status Linkage | Optional checkbox (default checked) | Flexible with encouraged tracking |
| Delete Rule | Block if open work orders | Maintains data integrity |
| Location Data Source | Custom hierarchy table | Proper hierarchy enforcement |
| Test Database | TestContainers | Production-like MySQL with CI/CD compatibility |

---

## Success Metrics

| Metric | Baseline | Target (6 months) |
|--------|----------|-------------------|
| Asset registration time | ~15 min/asset | < 3 min/asset |
| Time to locate asset info | ~30 min | < 10 seconds |
| Work order response time | Unknown | < 4 hours (tracked) |
| Report generation time | 2-3 days | Real-time |
| Asset data accuracy | Unknown | > 95% verified |
| User adoption rate | N/A | > 80% active |
