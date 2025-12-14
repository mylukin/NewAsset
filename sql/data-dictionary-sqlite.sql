-- ============================================
-- Asset Management System - Dictionary Data (SQLite)
-- Initialize system dictionaries for asset module
-- ============================================

-- Create dictionary tables if not exist
CREATE TABLE IF NOT EXISTS sys_dict_type (
    dict_id INTEGER PRIMARY KEY,
    dict_name TEXT NOT NULL,
    dict_type TEXT NOT NULL UNIQUE,
    status TEXT DEFAULT '0',
    create_by TEXT,
    create_time TEXT DEFAULT (datetime('now')),
    update_by TEXT,
    update_time TEXT DEFAULT (datetime('now')),
    remark TEXT
);

CREATE TABLE IF NOT EXISTS sys_dict_data (
    dict_code INTEGER PRIMARY KEY,
    dict_sort INTEGER DEFAULT 0,
    dict_label TEXT NOT NULL,
    dict_value TEXT NOT NULL,
    dict_type TEXT NOT NULL,
    css_class TEXT,
    list_class TEXT,
    is_default TEXT DEFAULT 'N',
    status TEXT DEFAULT '0',
    create_by TEXT,
    create_time TEXT DEFAULT (datetime('now')),
    update_by TEXT,
    update_time TEXT DEFAULT (datetime('now')),
    remark TEXT
);

-- -------------------------------------------
-- Dictionary Type: asset_type (资产类型)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('资产类型', 'asset_type', '0', 'admin', '资产分类类型');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '房屋', 'HOUSE', 'asset_type', '0', 'admin', '房屋资产'),
(2, '车位', 'PARKING', 'asset_type', '0', 'admin', '车位资产'),
(3, '设施设备', 'FACILITY', 'asset_type', '0', 'admin', '公共设施设备'),
(4, '场馆', 'VENUE', 'asset_type', '0', 'admin', '场馆设施'),
(5, '办公资产', 'OFFICE', 'asset_type', '0', 'admin', '办公用品及IT设备');

-- -------------------------------------------
-- Dictionary Type: asset_status (资产状态)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('资产状态', 'asset_status', '0', 'admin', '资产使用状态');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, status, create_by, remark) VALUES
(1, '建设中', 'UNDER_CONSTRUCTION', 'asset_status', 'info', '0', 'admin', '资产建设中'),
(2, '自用中', 'AVAILABLE_SELF', 'asset_status', 'success', '0', 'admin', '自持使用'),
(3, '出租中', 'AVAILABLE_RENT', 'asset_status', 'success', '0', 'admin', '对外出租'),
(4, '闲置', 'AVAILABLE_IDLE', 'asset_status', 'warning', '0', 'admin', '闲置待用'),
(5, '临时关闭', 'TEMP_CLOSED', 'asset_status', 'warning', '0', 'admin', '临时停用'),
(6, '故障', 'FAULT', 'asset_status', 'danger', '0', 'admin', '故障待修'),
(7, '维修中', 'MAINTAINING', 'asset_status', 'warning', '0', 'admin', '维护保养中'),
(8, '待报废', 'TO_BE_SCRAPPED', 'asset_status', 'danger', '0', 'admin', '等待报废审批'),
(9, '已报废', 'SCRAPPED', 'asset_status', 'info', '0', 'admin', '已报废处置');

-- -------------------------------------------
-- Dictionary Type: ownership_type (产权类型)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('产权类型', 'ownership_type', '0', 'admin', '资产产权归属类型');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '自有', 'OWNED', 'ownership_type', '0', 'admin', '自有产权'),
(2, '租入', 'RENTED', 'ownership_type', '0', 'admin', '租赁使用'),
(3, '托管', 'MANAGED', 'ownership_type', '0', 'admin', '受托管理');

-- -------------------------------------------
-- Dictionary Type: depreciation_method (折旧方式)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('折旧方式', 'depreciation_method', '0', 'admin', '资产折旧计算方式');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '平均年限法', 'STRAIGHT_LINE', 'depreciation_method', '0', 'admin', '直线折旧法'),
(2, '工作量法', 'UNITS_OF_PRODUCTION', 'depreciation_method', '0', 'admin', '按工作量折旧'),
(3, '双倍余额递减法', 'DOUBLE_DECLINING', 'depreciation_method', '0', 'admin', '加速折旧法'),
(4, '年数总和法', 'SUM_OF_YEARS', 'depreciation_method', '0', 'admin', '年限总和折旧法'),
(5, '不折旧', 'NONE', 'depreciation_method', '0', 'admin', '不计提折旧');

-- -------------------------------------------
-- Dictionary Type: house_type (房屋户型)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('房屋户型', 'house_type', '0', 'admin', '房屋户型分类');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '一居', '1BR', 'house_type', '0', 'admin', '一室户型'),
(2, '两居', '2BR', 'house_type', '0', 'admin', '两室户型'),
(3, '三居', '3BR', 'house_type', '0', 'admin', '三室户型'),
(4, '四居及以上', '4BR_PLUS', 'house_type', '0', 'admin', '四室及以上'),
(5, '复式', 'DUPLEX', 'house_type', '0', 'admin', '复式户型'),
(6, '别墅', 'VILLA', 'house_type', '0', 'admin', '独栋/联排别墅'),
(7, '商铺', 'SHOP', 'house_type', '0', 'admin', '商业店铺'),
(8, '其他', 'OTHER', 'house_type', '0', 'admin', '其他类型');

-- -------------------------------------------
-- Dictionary Type: house_usage (房屋用途)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('房屋用途', 'house_usage', '0', 'admin', '房屋使用用途');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '住宅', 'RESIDENTIAL', 'house_usage', '0', 'admin', '居住用途'),
(2, '商用', 'COMMERCIAL', 'house_usage', '0', 'admin', '商业用途'),
(3, '公寓', 'APARTMENT', 'house_usage', '0', 'admin', '公寓式住宅'),
(4, '办公', 'OFFICE', 'house_usage', '0', 'admin', '办公用途'),
(5, '仓储', 'STORAGE', 'house_usage', '0', 'admin', '仓储用途');

-- -------------------------------------------
-- Dictionary Type: parking_type (车位类型)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('车位类型', 'parking_type', '0', 'admin', '停车位类型');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '固定车位', 'FIXED', 'parking_type', '0', 'admin', '固定租赁车位'),
(2, '临时车位', 'TEMPORARY', 'parking_type', '0', 'admin', '临时停放车位');

-- -------------------------------------------
-- Dictionary Type: facility_type (设施类型)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('设施类型', 'facility_type', '0', 'admin', '公共设施设备类型');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '电梯', 'ELEVATOR', 'facility_type', '0', 'admin', '电梯设备'),
(2, '门禁', 'ACCESS_CONTROL', 'facility_type', '0', 'admin', '门禁系统'),
(3, '公共照明', 'LIGHTING', 'facility_type', '0', 'admin', '公共区域照明'),
(4, '消防设施', 'FIRE_PROTECTION', 'facility_type', '0', 'admin', '消防设备'),
(5, '供暖设备', 'HEATING', 'facility_type', '0', 'admin', '供暖系统'),
(6, '空调设备', 'HVAC', 'facility_type', '0', 'admin', '暖通空调'),
(7, '监控设备', 'SURVEILLANCE', 'facility_type', '0', 'admin', '安防监控'),
(8, '给排水', 'PLUMBING', 'facility_type', '0', 'admin', '给排水系统'),
(9, '供配电', 'ELECTRICAL', 'facility_type', '0', 'admin', '供配电系统'),
(10, '其他', 'OTHER', 'facility_type', '0', 'admin', '其他设施');

-- -------------------------------------------
-- Dictionary Type: venue_type (场馆类型)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('场馆类型', 'venue_type', '0', 'admin', '场馆设施类型');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '篮球场', 'BASKETBALL', 'venue_type', '0', 'admin', '篮球场地'),
(2, '羽毛球馆', 'BADMINTON', 'venue_type', '0', 'admin', '羽毛球场馆'),
(3, '会议室', 'MEETING_ROOM', 'venue_type', '0', 'admin', '会议室'),
(4, '健身房', 'GYM', 'venue_type', '0', 'admin', '健身房'),
(5, '游泳池', 'SWIMMING_POOL', 'venue_type', '0', 'admin', '游泳池'),
(6, '网球场', 'TENNIS', 'venue_type', '0', 'admin', '网球场'),
(7, '活动室', 'ACTIVITY_ROOM', 'venue_type', '0', 'admin', '多功能活动室'),
(8, '图书馆', 'LIBRARY', 'venue_type', '0', 'admin', '图书馆/阅览室'),
(9, '儿童乐园', 'PLAYGROUND', 'venue_type', '0', 'admin', '儿童游乐区'),
(10, '其他', 'OTHER', 'venue_type', '0', 'admin', '其他场馆');

-- -------------------------------------------
-- Dictionary Type: office_asset_type (办公资产类型)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('办公资产类型', 'office_asset_type', '0', 'admin', '办公资产分类');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '家具', 'FURNITURE', 'office_asset_type', '0', 'admin', '办公家具'),
(2, 'IT设备', 'IT_EQUIPMENT', 'office_asset_type', '0', 'admin', 'IT设备(电脑等)'),
(3, '其他', 'OTHER', 'office_asset_type', '0', 'admin', '其他办公资产');

-- -------------------------------------------
-- Dictionary Type: maint_order_status (维修工单状态)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('维修工单状态', 'maint_order_status', '0', 'admin', '维修工单流程状态');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, status, create_by, remark) VALUES
(1, '待派单', 'WAIT_ASSIGN', 'maint_order_status', 'info', '0', 'admin', '等待分配处理人'),
(2, '待接单', 'WAIT_ACCEPT', 'maint_order_status', 'warning', '0', 'admin', '等待处理人接单'),
(3, '处理中', 'PROCESSING', 'maint_order_status', 'primary', '0', 'admin', '正在处理'),
(4, '待确认', 'WAIT_CONFIRM', 'maint_order_status', 'warning', '0', 'admin', '完成待确认'),
(5, '已关闭', 'CLOSED', 'maint_order_status', 'success', '0', 'admin', '工单已完成'),
(6, '已取消', 'CANCELED', 'maint_order_status', 'info', '0', 'admin', '工单已取消');

-- -------------------------------------------
-- Dictionary Type: maint_priority (维修优先级)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('维修优先级', 'maint_priority', '0', 'admin', '维修工单优先级');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, css_class, status, create_by, remark) VALUES
(1, '一般', 'NORMAL', 'maint_priority', 'info', '0', 'admin', '普通优先级'),
(2, '重要', 'IMPORTANT', 'maint_priority', 'warning', '0', 'admin', '重要工单'),
(3, '紧急', 'URGENT', 'maint_priority', 'danger', '0', 'admin', '紧急处理');

-- -------------------------------------------
-- Dictionary Type: fault_type (故障类型)
-- -------------------------------------------
INSERT INTO sys_dict_type (dict_name, dict_type, status, create_by, remark) VALUES
('故障类型', 'fault_type', '0', 'admin', '设备故障分类');

INSERT INTO sys_dict_data (dict_sort, dict_label, dict_value, dict_type, status, create_by, remark) VALUES
(1, '电气故障', 'ELECTRICAL', 'fault_type', '0', 'admin', '电气类故障'),
(2, '机械故障', 'MECHANICAL', 'fault_type', '0', 'admin', '机械类故障'),
(3, '漏水渗水', 'LEAKAGE', 'fault_type', '0', 'admin', '漏水渗水问题'),
(4, '门窗损坏', 'DOOR_WINDOW', 'fault_type', '0', 'admin', '门窗损坏'),
(5, '墙面地面', 'SURFACE', 'fault_type', '0', 'admin', '墙面地面损坏'),
(6, '管道堵塞', 'PIPE_BLOCKAGE', 'fault_type', '0', 'admin', '管道堵塞'),
(7, '设备老化', 'AGING', 'fault_type', '0', 'admin', '设备老化损耗'),
(8, '人为损坏', 'HUMAN_DAMAGE', 'fault_type', '0', 'admin', '人为损坏'),
(9, '其他', 'OTHER', 'fault_type', '0', 'admin', '其他故障');
