-- ============================================
-- Asset Management System - SQLite Schema
-- For development environment
-- ============================================

PRAGMA foreign_keys = ON;

-- -------------------------------------------
-- Table: t_asset (Base Asset Table)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset;
CREATE TABLE t_asset (
    id INTEGER PRIMARY KEY,
    asset_code TEXT NOT NULL UNIQUE,
    asset_name TEXT NOT NULL,
    asset_type TEXT NOT NULL,
    project_id INTEGER NOT NULL,
    building TEXT,
    floor TEXT,
    room_no TEXT,
    location_desc TEXT,
    ownership_type TEXT,
    owner_org TEXT,
    use_dept_id INTEGER,
    duty_user_id INTEGER,
    status TEXT NOT NULL DEFAULT 'AVAILABLE_IDLE',
    purchase_date TEXT,
    start_use_date TEXT,
    original_value REAL,
    depreciation_method TEXT,
    depreciation_amount REAL,
    net_value REAL,
    brand TEXT,
    model TEXT,
    supplier TEXT,
    warranty_expire_date TEXT,
    remark TEXT,
    create_by TEXT,
    create_time TEXT DEFAULT (datetime('now')),
    update_by TEXT,
    update_time TEXT DEFAULT (datetime('now')),
    del_flag TEXT DEFAULT '0'
);

CREATE INDEX idx_asset_project_type_status ON t_asset(project_id, asset_type, status);
CREATE INDEX idx_asset_use_dept ON t_asset(use_dept_id);
CREATE INDEX idx_asset_duty_user ON t_asset(duty_user_id);

-- -------------------------------------------
-- Table: t_asset_house (House Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_house;
CREATE TABLE t_asset_house (
    asset_id INTEGER PRIMARY KEY,
    building_area REAL,
    inner_area REAL,
    house_type TEXT,
    house_usage TEXT,
    current_usage TEXT,
    current_user TEXT,
    contract_no TEXT,
    rent_unit_price REAL,
    rent_total REAL,
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
);

-- -------------------------------------------
-- Table: t_asset_parking (Parking Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_parking;
CREATE TABLE t_asset_parking (
    asset_id INTEGER PRIMARY KEY,
    parking_no TEXT,
    parking_zone TEXT,
    parking_type TEXT,
    area REAL,
    current_user TEXT,
    plate_no TEXT,
    rent_price REAL,
    contract_no TEXT,
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
);

-- -------------------------------------------
-- Table: t_asset_facility (Facility Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_facility;
CREATE TABLE t_asset_facility (
    asset_id INTEGER PRIMARY KEY,
    facility_type TEXT,
    install_position TEXT,
    equipment_no TEXT,
    maint_org TEXT,
    maint_phone TEXT,
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
);

-- -------------------------------------------
-- Table: t_asset_venue (Venue Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_venue;
CREATE TABLE t_asset_venue (
    asset_id INTEGER PRIMARY KEY,
    venue_type TEXT,
    capacity INTEGER,
    use_mode TEXT,
    open_time_desc TEXT,
    current_venue_status TEXT,
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
);

-- -------------------------------------------
-- Table: t_asset_office (Office Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_office;
CREATE TABLE t_asset_office (
    asset_id INTEGER PRIMARY KEY,
    office_type TEXT,
    workplace_no TEXT,
    use_user TEXT,
    serial_no TEXT,
    os_info TEXT,
    config_desc TEXT,
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
);

-- -------------------------------------------
-- Table: t_asset_attachment (Asset Attachments)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_attachment;
CREATE TABLE t_asset_attachment (
    id INTEGER PRIMARY KEY,
    asset_id INTEGER NOT NULL,
    file_name TEXT NOT NULL,
    file_path TEXT NOT NULL,
    file_type TEXT,
    file_size INTEGER,
    upload_by TEXT,
    upload_time TEXT DEFAULT (datetime('now')),
    remark TEXT,
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
);

CREATE INDEX idx_attachment_asset_id ON t_asset_attachment(asset_id);

-- -------------------------------------------
-- Table: t_asset_maint_order (Maintenance Order)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_maint_order;
CREATE TABLE t_asset_maint_order (
    id INTEGER PRIMARY KEY,
    order_no TEXT NOT NULL UNIQUE,
    asset_id INTEGER NOT NULL,
    project_id INTEGER NOT NULL,
    order_type TEXT NOT NULL,
    fault_type TEXT,
    title TEXT NOT NULL,
    description TEXT,
    priority TEXT DEFAULT 'NORMAL',
    require_finish_time TEXT,
    status TEXT NOT NULL DEFAULT 'WAIT_ASSIGN',
    origin_asset_status TEXT,
    current_handler_id INTEGER,
    requester_id INTEGER,
    create_by TEXT,
    create_time TEXT DEFAULT (datetime('now')),
    update_by TEXT,
    update_time TEXT DEFAULT (datetime('now')),
    FOREIGN KEY (asset_id) REFERENCES t_asset(id)
);

CREATE INDEX idx_maint_order_project_status ON t_asset_maint_order(project_id, status);
CREATE INDEX idx_maint_order_handler_status ON t_asset_maint_order(current_handler_id, status);
CREATE INDEX idx_maint_order_asset_id ON t_asset_maint_order(asset_id);

-- -------------------------------------------
-- Table: t_asset_maint_log (Maintenance Log)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_maint_log;
CREATE TABLE t_asset_maint_log (
    id INTEGER PRIMARY KEY,
    order_id INTEGER NOT NULL,
    from_status TEXT,
    to_status TEXT,
    operator_id INTEGER,
    operation_type TEXT NOT NULL,
    content TEXT,
    op_time TEXT DEFAULT (datetime('now')),
    FOREIGN KEY (order_id) REFERENCES t_asset_maint_order(id) ON DELETE CASCADE
);

CREATE INDEX idx_maint_log_order_id ON t_asset_maint_log(order_id);

-- -------------------------------------------
-- Table: t_asset_code_rule (Asset Code Rule)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_code_rule;
CREATE TABLE t_asset_code_rule (
    id INTEGER PRIMARY KEY,
    asset_type TEXT NOT NULL,
    rule_name TEXT NOT NULL,
    pattern TEXT NOT NULL,
    separator TEXT DEFAULT '-',
    seq_length INTEGER DEFAULT 4,
    reset_scope TEXT DEFAULT 'NONE',
    enabled INTEGER DEFAULT 1,
    remark TEXT,
    create_by TEXT,
    create_time TEXT DEFAULT (datetime('now')),
    update_by TEXT,
    update_time TEXT DEFAULT (datetime('now'))
);

CREATE INDEX idx_code_rule_asset_type ON t_asset_code_rule(asset_type);

-- -------------------------------------------
-- Table: t_asset_code_seq (Asset Code Sequence)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_code_seq;
CREATE TABLE t_asset_code_seq (
    id INTEGER PRIMARY KEY,
    rule_id INTEGER NOT NULL,
    project_id INTEGER,
    year INTEGER,
    current_seq INTEGER DEFAULT 0,
    version INTEGER DEFAULT 0,
    FOREIGN KEY (rule_id) REFERENCES t_asset_code_rule(id) ON DELETE CASCADE,
    UNIQUE (rule_id, project_id, year)
);

-- -------------------------------------------
-- Table: t_asset_maint_plan (Maintenance Plan - P1)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_maint_plan;
CREATE TABLE t_asset_maint_plan (
    id INTEGER PRIMARY KEY,
    plan_name TEXT NOT NULL,
    project_id INTEGER,
    asset_type TEXT,
    asset_category TEXT,
    cycle_type TEXT NOT NULL,
    next_generate_time TEXT,
    status TEXT DEFAULT 'ENABLED',
    remark TEXT,
    create_by TEXT,
    create_time TEXT DEFAULT (datetime('now')),
    update_by TEXT,
    update_time TEXT DEFAULT (datetime('now'))
);

CREATE INDEX idx_maint_plan_project_id ON t_asset_maint_plan(project_id);

-- -------------------------------------------
-- Table: t_asset_maint_plan_asset (Plan-Asset Relation)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_maint_plan_asset;
CREATE TABLE t_asset_maint_plan_asset (
    plan_id INTEGER NOT NULL,
    asset_id INTEGER NOT NULL,
    PRIMARY KEY (plan_id, asset_id),
    FOREIGN KEY (plan_id) REFERENCES t_asset_maint_plan(id) ON DELETE CASCADE,
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
);

-- -------------------------------------------
-- Table: sys_project (Project for Data Permission)
-- -------------------------------------------
DROP TABLE IF EXISTS sys_project;
CREATE TABLE sys_project (
    project_id INTEGER PRIMARY KEY,
    project_name TEXT NOT NULL,
    project_code TEXT,
    status TEXT DEFAULT '0',
    create_by TEXT,
    create_time TEXT DEFAULT (datetime('now')),
    update_by TEXT,
    update_time TEXT DEFAULT (datetime('now')),
    remark TEXT
);

-- -------------------------------------------
-- Table: sys_user_project (User-Project Relation for Data Permission)
-- -------------------------------------------
DROP TABLE IF EXISTS sys_user_project;
CREATE TABLE sys_user_project (
    user_id INTEGER NOT NULL,
    project_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, project_id)
);

-- -------------------------------------------
-- Table: t_asset_status_log (Asset Status Change Log)
-- -------------------------------------------
DROP TABLE IF EXISTS t_asset_status_log;
CREATE TABLE t_asset_status_log (
    id INTEGER PRIMARY KEY,
    asset_id INTEGER NOT NULL,
    before_status TEXT NOT NULL,
    after_status TEXT NOT NULL,
    reason TEXT,
    maint_order_id INTEGER,
    operator_id INTEGER,
    change_time TEXT DEFAULT (datetime('now')),
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
);

CREATE INDEX idx_status_log_asset ON t_asset_status_log(asset_id);
CREATE INDEX idx_status_log_maint_order ON t_asset_status_log(maint_order_id);
