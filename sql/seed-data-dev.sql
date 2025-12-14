-- ============================================
-- Asset Management System - Development Seed Data
-- SQLite compatible syntax
-- Safe to run multiple times (idempotent)
-- ============================================

-- Clean existing seed data (optional - comment out to preserve data)
-- DELETE FROM t_asset_maint_log;
-- DELETE FROM t_asset_maint_order;
-- DELETE FROM t_asset_attachment;
-- DELETE FROM t_asset_house;
-- DELETE FROM t_asset_parking;
-- DELETE FROM t_asset_facility;
-- DELETE FROM t_asset_venue;
-- DELETE FROM t_asset_office;
-- DELETE FROM t_asset;
-- DELETE FROM sys_project;

-- -------------------------------------------
-- Sample Projects (3 projects with different characteristics)
-- -------------------------------------------
INSERT OR IGNORE INTO sys_project (project_id, project_name, project_code, status, create_by, remark)
VALUES
(1, '阳光花园小区', 'YGHY', '0', 'admin', '高端住宅小区，包含住宅、商铺、车位'),
(2, '科技园A区', 'KJYA', '0', 'admin', '科技产业园区，包含办公楼、会议室、设施'),
(3, '滨河商业广场', 'BHSY', '0', 'admin', '大型商业综合体，包含商铺、场馆、设施');

-- -------------------------------------------
-- User-Project Relations (for data permission)
-- -------------------------------------------
INSERT OR IGNORE INTO sys_user_project (user_id, project_id)
VALUES
(1, 1), (1, 2), (1, 3),  -- Admin can access all projects
(2, 1),                   -- User 2 can only access project 1
(3, 2), (3, 3);           -- User 3 can access project 2 and 3

-- -------------------------------------------
-- Asset Code Rules (one for each asset type)
-- -------------------------------------------
INSERT OR IGNORE INTO t_asset_code_rule (id, asset_type, rule_name, pattern, separator, seq_length, reset_scope, enabled, create_by)
VALUES
(1, 'HOUSE', '房屋资产编码规则', '{PROJECT}{TYPE}{SEQ}', '-', 4, 'BY_PROJECT_YEAR', 1, 'admin'),
(2, 'PARKING', '车位资产编码规则', '{PROJECT}{TYPE}{SEQ}', '-', 4, 'BY_PROJECT_YEAR', 1, 'admin'),
(3, 'FACILITY', '设施资产编码规则', '{PROJECT}{TYPE}{SEQ}', '-', 4, 'BY_PROJECT_YEAR', 1, 'admin'),
(4, 'VENUE', '场馆资产编码规则', '{PROJECT}{TYPE}{SEQ}', '-', 4, 'BY_PROJECT_YEAR', 1, 'admin'),
(5, 'OFFICE', '办公资产编码规则', '{PROJECT}{TYPE}{SEQ}', '-', 4, 'BY_PROJECT_YEAR', 1, 'admin');

-- -------------------------------------------
-- Sequence Counters for Projects
-- -------------------------------------------
INSERT OR IGNORE INTO t_asset_code_seq (id, rule_id, project_id, year, current_seq, version)
VALUES
(1, 1, 1, 2024, 5, 0),   -- House rule for project 1
(2, 1, 2, 2024, 3, 0),   -- House rule for project 2
(3, 2, 1, 2024, 10, 0),  -- Parking rule for project 1
(4, 3, 1, 2024, 5, 0),   -- Facility rule for project 1
(5, 4, 3, 2024, 3, 0),   -- Venue rule for project 3
(6, 5, 2, 2024, 8, 0);   -- Office rule for project 2

-- -------------------------------------------
-- House Assets (Project 1: 5 houses)
-- -------------------------------------------
INSERT OR IGNORE INTO t_asset (id, asset_code, asset_name, asset_type, project_id, building, floor, room_no, location_desc, ownership_type, status, original_value, create_by)
VALUES
(1, 'YGHY-HOUSE-0001', '1号楼101室', 'HOUSE', 1, '1号楼', '1', '101', '1号楼1层101室', 'OWNED', 'AVAILABLE_RENT', 1200000.00, 'admin'),
(2, 'YGHY-HOUSE-0002', '1号楼201室', 'HOUSE', 1, '1号楼', '2', '201', '1号楼2层201室', 'OWNED', 'AVAILABLE_SELF', 1300000.00, 'admin'),
(3, 'YGHY-HOUSE-0003', '2号楼301室', 'HOUSE', 1, '2号楼', '3', '301', '2号楼3层301室', 'OWNED', 'AVAILABLE_IDLE', 1500000.00, 'admin'),
(4, 'YGHY-HOUSE-0004', '2号楼501室', 'HOUSE', 1, '2号楼', '5', '501', '2号楼5层501室', 'MANAGED', 'AVAILABLE_RENT', 1800000.00, 'admin'),
(5, 'YGHY-HOUSE-0005', '商铺A-01', 'HOUSE', 1, '商业区A', '1', 'A-01', '商业区A区1层01号', 'OWNED', 'AVAILABLE_RENT', 2500000.00, 'admin');

INSERT OR IGNORE INTO t_asset_house (asset_id, building_area, inner_area, house_type, house_usage, current_usage, current_user, rent_unit_price, rent_total)
VALUES
(1, 89.50, 72.30, '2BR', 'RESIDENTIAL', 'rent', '张三', 45.00, 4027.50),
(2, 120.00, 98.50, '3BR', 'RESIDENTIAL', 'self', NULL, NULL, NULL),
(3, 150.00, 125.00, '4BR_PLUS', 'RESIDENTIAL', 'idle', NULL, NULL, NULL),
(4, 180.00, 155.00, 'VILLA', 'RESIDENTIAL', 'rent', '李四家庭', 65.00, 11700.00),
(5, 200.00, 180.00, 'SHOP', 'COMMERCIAL', 'rent', '便利店有限公司', 120.00, 24000.00);

-- House Assets (Project 2: 3 houses - office buildings)
INSERT OR IGNORE INTO t_asset (id, asset_code, asset_name, asset_type, project_id, building, floor, room_no, location_desc, ownership_type, status, original_value, create_by)
VALUES
(6, 'KJYA-HOUSE-0001', 'A栋101办公室', 'HOUSE', 2, 'A栋', '1', '101', 'A栋1层101办公室', 'OWNED', 'AVAILABLE_RENT', 800000.00, 'admin'),
(7, 'KJYA-HOUSE-0002', 'A栋201办公室', 'HOUSE', 2, 'A栋', '2', '201', 'A栋2层201办公室', 'OWNED', 'AVAILABLE_RENT', 850000.00, 'admin'),
(8, 'KJYA-HOUSE-0003', 'B栋整层', 'HOUSE', 2, 'B栋', '3', 'F3', 'B栋3层整层', 'OWNED', 'AVAILABLE_SELF', 5000000.00, 'admin');

INSERT OR IGNORE INTO t_asset_house (asset_id, building_area, inner_area, house_type, house_usage, current_usage, current_user, rent_unit_price, rent_total)
VALUES
(6, 150.00, 130.00, 'OTHER', 'OFFICE', 'rent', '创业公司A', 80.00, 12000.00),
(7, 180.00, 160.00, 'OTHER', 'OFFICE', 'rent', '创业公司B', 85.00, 15300.00),
(8, 800.00, 720.00, 'OTHER', 'OFFICE', 'self', NULL, NULL, NULL);

-- -------------------------------------------
-- Parking Assets (Project 1: 10 parking spaces)
-- -------------------------------------------
INSERT OR IGNORE INTO t_asset (id, asset_code, asset_name, asset_type, project_id, building, floor, room_no, location_desc, ownership_type, status, original_value, create_by)
VALUES
(10, 'YGHY-PARK-0001', '地下车库A-001', 'PARKING', 1, '地下车库', 'B1', 'A-001', '地下一层A区001号', 'OWNED', 'AVAILABLE_RENT', 150000.00, 'admin'),
(11, 'YGHY-PARK-0002', '地下车库A-002', 'PARKING', 1, '地下车库', 'B1', 'A-002', '地下一层A区002号', 'OWNED', 'AVAILABLE_RENT', 150000.00, 'admin'),
(12, 'YGHY-PARK-0003', '地下车库A-003', 'PARKING', 1, '地下车库', 'B1', 'A-003', '地下一层A区003号', 'OWNED', 'AVAILABLE_IDLE', 150000.00, 'admin'),
(13, 'YGHY-PARK-0004', '地下车库B-001', 'PARKING', 1, '地下车库', 'B2', 'B-001', '地下二层B区001号', 'OWNED', 'AVAILABLE_RENT', 120000.00, 'admin'),
(14, 'YGHY-PARK-0005', '地下车库B-002', 'PARKING', 1, '地下车库', 'B2', 'B-002', '地下二层B区002号', 'OWNED', 'AVAILABLE_IDLE', 120000.00, 'admin'),
(15, 'YGHY-PARK-0006', '地面车位G-01', 'PARKING', 1, '地面', 'G', 'G-01', '地面车位区01号', 'OWNED', 'AVAILABLE_RENT', 80000.00, 'admin'),
(16, 'YGHY-PARK-0007', '地面车位G-02', 'PARKING', 1, '地面', 'G', 'G-02', '地面车位区02号', 'OWNED', 'AVAILABLE_RENT', 80000.00, 'admin'),
(17, 'YGHY-PARK-0008', '地面车位G-03', 'PARKING', 1, '地面', 'G', 'G-03', '地面车位区03号', 'OWNED', 'AVAILABLE_IDLE', 80000.00, 'admin'),
(18, 'YGHY-PARK-0009', '访客临停T-01', 'PARKING', 1, '地面', 'G', 'T-01', '临时停车区01号', 'OWNED', 'AVAILABLE_IDLE', 0.00, 'admin'),
(19, 'YGHY-PARK-0010', '访客临停T-02', 'PARKING', 1, '地面', 'G', 'T-02', '临时停车区02号', 'OWNED', 'AVAILABLE_IDLE', 0.00, 'admin');

INSERT OR IGNORE INTO t_asset_parking (asset_id, parking_no, parking_zone, parking_type, area, current_user, plate_no, rent_price)
VALUES
(10, 'A-001', 'A区', 'FIXED', 15.00, '张三', '京A12345', 500.00),
(11, 'A-002', 'A区', 'FIXED', 15.00, '王五', '京B67890', 500.00),
(12, 'A-003', 'A区', 'FIXED', 15.00, NULL, NULL, NULL),
(13, 'B-001', 'B区', 'FIXED', 12.00, '李四', '京C11111', 400.00),
(14, 'B-002', 'B区', 'FIXED', 12.00, NULL, NULL, NULL),
(15, 'G-01', '地面', 'FIXED', 18.00, '赵六', '京D22222', 300.00),
(16, 'G-02', '地面', 'FIXED', 18.00, '孙七', '京E33333', 300.00),
(17, 'G-03', '地面', 'FIXED', 18.00, NULL, NULL, NULL),
(18, 'T-01', '临停', 'TEMPORARY', 15.00, NULL, NULL, NULL),
(19, 'T-02', '临停', 'TEMPORARY', 15.00, NULL, NULL, NULL);

-- -------------------------------------------
-- Facility Assets (Project 1: 5 facilities)
-- -------------------------------------------
INSERT OR IGNORE INTO t_asset (id, asset_code, asset_name, asset_type, project_id, building, floor, room_no, location_desc, ownership_type, status, original_value, brand, model, create_by)
VALUES
(20, 'YGHY-FACL-0001', '1号楼电梯A', 'FACILITY', 1, '1号楼', 'ALL', 'ELEV-A', '1号楼A梯', 'OWNED', 'AVAILABLE_SELF', 350000.00, '三菱', 'NEXIEZ-S', 'admin'),
(21, 'YGHY-FACL-0002', '1号楼电梯B', 'FACILITY', 1, '1号楼', 'ALL', 'ELEV-B', '1号楼B梯', 'OWNED', 'MAINTAINING', 350000.00, '三菱', 'NEXIEZ-S', 'admin'),
(22, 'YGHY-FACL-0003', '小区大门门禁', 'FACILITY', 1, '大门', '1', 'GATE-M', '小区主入口', 'OWNED', 'AVAILABLE_SELF', 50000.00, '海康威视', 'DS-K1T804', 'admin'),
(23, 'YGHY-FACL-0004', '地下车库照明', 'FACILITY', 1, '地下车库', 'B1', 'LIGHT-B1', '地下一层照明系统', 'OWNED', 'AVAILABLE_SELF', 80000.00, '飞利浦', 'BY218P', 'admin'),
(24, 'YGHY-FACL-0005', '消防控制系统', 'FACILITY', 1, '监控中心', '1', 'FIRE-CTL', '物业监控中心消防系统', 'OWNED', 'AVAILABLE_SELF', 200000.00, '海湾', 'GST5000', 'admin');

INSERT OR IGNORE INTO t_asset_facility (asset_id, facility_type, install_position, equipment_no, maint_org, maint_phone)
VALUES
(20, 'ELEVATOR', '1号楼电梯间', 'ELEV-1A-2020', '三菱电梯维保', '400-888-0001'),
(21, 'ELEVATOR', '1号楼电梯间', 'ELEV-1B-2020', '三菱电梯维保', '400-888-0001'),
(22, 'ACCESS_CONTROL', '小区主入口', 'GATE-M-2022', '海康威视', '400-700-5998'),
(23, 'LIGHTING', '地下一层', 'LIGHT-B1-2021', '飞利浦照明', '400-880-0008'),
(24, 'FIRE_PROTECTION', '物业监控中心', 'FIRE-2019', '海湾安防', '400-623-1119');

-- -------------------------------------------
-- Venue Assets (Project 3: 3 venues)
-- -------------------------------------------
INSERT OR IGNORE INTO t_asset (id, asset_code, asset_name, asset_type, project_id, building, floor, room_no, location_desc, ownership_type, status, original_value, create_by)
VALUES
(30, 'BHSY-VENU-0001', '篮球馆', 'VENUE', 3, '体育中心', '1', 'BASKET', '体育中心一层篮球馆', 'OWNED', 'AVAILABLE_SELF', 800000.00, 'admin'),
(31, 'BHSY-VENU-0002', '羽毛球馆', 'VENUE', 3, '体育中心', '2', 'BADMIN', '体育中心二层羽毛球馆', 'OWNED', 'AVAILABLE_SELF', 500000.00, 'admin'),
(32, 'BHSY-VENU-0003', '多功能会议室', 'VENUE', 3, '商务中心', '3', 'CONF-A', '商务中心3层A会议室', 'OWNED', 'AVAILABLE_SELF', 200000.00, 'admin');

INSERT OR IGNORE INTO t_asset_venue (asset_id, venue_type, capacity, use_mode, open_time_desc, current_venue_status)
VALUES
(30, 'BASKETBALL', 50, 'paid', '周一至周日 8:00-22:00', 'available'),
(31, 'BADMINTON', 24, 'paid', '周一至周日 8:00-22:00', 'available'),
(32, 'MEETING_ROOM', 30, 'internal', '工作日 9:00-18:00', 'available');

-- -------------------------------------------
-- Office Assets (Project 2: 8 office assets)
-- -------------------------------------------
INSERT OR IGNORE INTO t_asset (id, asset_code, asset_name, asset_type, project_id, building, floor, room_no, location_desc, ownership_type, status, original_value, brand, model, create_by)
VALUES
(40, 'KJYA-OFFC-0001', '办公桌A-01', 'OFFICE', 2, 'A栋', '1', '101', 'A栋101室01工位', 'OWNED', 'AVAILABLE_SELF', 2000.00, '震旦', 'VIA', 'admin'),
(41, 'KJYA-OFFC-0002', '办公桌A-02', 'OFFICE', 2, 'A栋', '1', '101', 'A栋101室02工位', 'OWNED', 'AVAILABLE_SELF', 2000.00, '震旦', 'VIA', 'admin'),
(42, 'KJYA-OFFC-0003', '笔记本电脑T-01', 'OFFICE', 2, 'A栋', '1', '101', 'A栋101室01工位', 'OWNED', 'AVAILABLE_SELF', 8000.00, 'ThinkPad', 'T14s', 'admin'),
(43, 'KJYA-OFFC-0004', '笔记本电脑T-02', 'OFFICE', 2, 'A栋', '1', '101', 'A栋101室02工位', 'OWNED', 'AVAILABLE_SELF', 8500.00, 'ThinkPad', 'X1 Carbon', 'admin'),
(44, 'KJYA-OFFC-0005', '台式电脑D-01', 'OFFICE', 2, 'A栋', '2', '201', 'A栋201室财务部', 'OWNED', 'AVAILABLE_SELF', 6000.00, 'Dell', 'OptiPlex 7090', 'admin'),
(45, 'KJYA-OFFC-0006', '打印机P-01', 'OFFICE', 2, 'A栋', '1', 'PRINT', 'A栋1层打印间', 'OWNED', 'AVAILABLE_SELF', 3500.00, 'HP', 'LaserJet Pro M404dn', 'admin'),
(46, 'KJYA-OFFC-0007', '会议室投影仪', 'OFFICE', 2, 'A栋', '3', 'CONF', 'A栋3层会议室', 'OWNED', 'AVAILABLE_SELF', 15000.00, 'Epson', 'CB-X51', 'admin'),
(47, 'KJYA-OFFC-0008', '服务器SRV-01', 'OFFICE', 2, 'B栋', 'B1', 'SERVER', 'B栋地下机房', 'OWNED', 'AVAILABLE_SELF', 50000.00, 'Dell', 'PowerEdge R750', 'admin');

INSERT OR IGNORE INTO t_asset_office (asset_id, office_type, workplace_no, use_user, serial_no, os_info, config_desc)
VALUES
(40, 'FURNITURE', 'A-101-01', '张三', NULL, NULL, '1.4m办公桌带侧柜'),
(41, 'FURNITURE', 'A-101-02', '李四', NULL, NULL, '1.4m办公桌带侧柜'),
(42, 'IT_EQUIPMENT', 'A-101-01', '张三', 'SN-T14S-001', 'Windows 11 Pro', 'i7-1165G7/16GB/512GB'),
(43, 'IT_EQUIPMENT', 'A-101-02', '李四', 'SN-X1C-002', 'Windows 11 Pro', 'i7-1185G7/16GB/1TB'),
(44, 'IT_EQUIPMENT', 'A-201-FIN', '王五', 'SN-OPT-003', 'Windows 10 Pro', 'i5-11500/16GB/256GB'),
(45, 'IT_EQUIPMENT', 'A-1F-PRINT', NULL, 'SN-HP-P01', NULL, '激光打印机/自动双面/网络'),
(46, 'IT_EQUIPMENT', 'A-3F-CONF', NULL, 'SN-EPSON-001', NULL, '5000流明/1080P'),
(47, 'IT_EQUIPMENT', 'B-B1-SRV', 'IT部门', 'SN-DELL-SRV01', 'CentOS 7.9', 'Xeon Gold 6330/128GB/4TB RAID');

-- -------------------------------------------
-- Maintenance Orders (various statuses)
-- -------------------------------------------
INSERT OR IGNORE INTO t_asset_maint_order (id, order_no, asset_id, project_id, order_type, fault_type, title, description, priority, status, origin_asset_status, current_handler_id, requester_id, create_by)
VALUES
(1, 'WO-2024-0001', 21, 1, 'maintenance', 'MECHANICAL', '1号楼电梯B定期维保', '电梯年度维保检查', 'NORMAL', 'CLOSED', 'AVAILABLE_SELF', 100, 1, 'admin'),
(2, 'WO-2024-0002', 20, 1, 'fault', 'ELECTRICAL', '1号楼电梯A异响', '电梯运行时有异常响声', 'IMPORTANT', 'PROCESSING', 'AVAILABLE_SELF', 100, 2, 'admin'),
(3, 'WO-2024-0003', 22, 1, 'fault', 'ELECTRICAL', '大门门禁故障', '门禁刷卡无反应', 'URGENT', 'WAIT_ASSIGN', 'AVAILABLE_SELF', NULL, 3, 'admin'),
(4, 'WO-2024-0004', 45, 2, 'repair', 'MECHANICAL', '打印机卡纸', '打印机频繁卡纸需检修', 'NORMAL', 'WAIT_ACCEPT', 'AVAILABLE_SELF', 101, 2, 'admin'),
(5, 'WO-2024-0005', 30, 3, 'maintenance', NULL, '篮球馆地板打蜡', '季度地板维护', 'NORMAL', 'WAIT_CONFIRM', 'AVAILABLE_SELF', 102, 1, 'admin');

-- -------------------------------------------
-- Maintenance Logs (for completed orders)
-- -------------------------------------------
INSERT OR IGNORE INTO t_asset_maint_log (id, order_id, from_status, to_status, operator_id, operation_type, content)
VALUES
-- Order 1 completed flow
(1, 1, NULL, 'WAIT_ASSIGN', 1, 'CREATE', '创建维保工单'),
(2, 1, 'WAIT_ASSIGN', 'WAIT_ACCEPT', 1, 'ASSIGN', '派单给维保人员'),
(3, 1, 'WAIT_ACCEPT', 'PROCESSING', 100, 'ACCEPT', '接单开始处理'),
(4, 1, 'PROCESSING', 'WAIT_CONFIRM', 100, 'COMPLETE', '维保完成，更换钢丝绳，检查控制系统'),
(5, 1, 'WAIT_CONFIRM', 'CLOSED', 1, 'CONFIRM', '确认维保完成'),
-- Order 2 in progress
(6, 2, NULL, 'WAIT_ASSIGN', 2, 'CREATE', '创建故障工单'),
(7, 2, 'WAIT_ASSIGN', 'WAIT_ACCEPT', 1, 'ASSIGN', '派单给电梯维保'),
(8, 2, 'WAIT_ACCEPT', 'PROCESSING', 100, 'ACCEPT', '接单，预计今日检查');
