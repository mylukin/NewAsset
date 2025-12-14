-- ============================================
-- Asset Management System - MySQL Schema
-- For production environment
-- ============================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- -------------------------------------------
-- Table: t_asset (Base Asset Table)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset`;
CREATE TABLE `t_asset` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `asset_code` VARCHAR(64) NOT NULL COMMENT 'Asset code (unique)',
    `asset_name` VARCHAR(200) NOT NULL COMMENT 'Asset name',
    `asset_type` VARCHAR(32) NOT NULL COMMENT 'Asset type: HOUSE/PARKING/FACILITY/VENUE/OFFICE',
    `project_id` BIGINT NOT NULL COMMENT 'Project ID',
    `building` VARCHAR(64) DEFAULT NULL COMMENT 'Building/zone code',
    `floor` VARCHAR(32) DEFAULT NULL COMMENT 'Floor',
    `room_no` VARCHAR(64) DEFAULT NULL COMMENT 'Room/space number',
    `location_desc` VARCHAR(500) DEFAULT NULL COMMENT 'Location description',
    `ownership_type` VARCHAR(32) DEFAULT NULL COMMENT 'Ownership type',
    `owner_org` VARCHAR(200) DEFAULT NULL COMMENT 'Owner organization',
    `use_dept_id` BIGINT DEFAULT NULL COMMENT 'Using department ID',
    `duty_user_id` BIGINT DEFAULT NULL COMMENT 'Responsible user ID',
    `status` VARCHAR(32) NOT NULL DEFAULT 'AVAILABLE_IDLE' COMMENT 'Asset status',
    `purchase_date` DATE DEFAULT NULL COMMENT 'Purchase date',
    `start_use_date` DATE DEFAULT NULL COMMENT 'Start use date',
    `original_value` DECIMAL(18,2) DEFAULT NULL COMMENT 'Original value',
    `depreciation_method` VARCHAR(32) DEFAULT NULL COMMENT 'Depreciation method',
    `depreciation_amount` DECIMAL(18,2) DEFAULT NULL COMMENT 'Accumulated depreciation',
    `net_value` DECIMAL(18,2) DEFAULT NULL COMMENT 'Net value',
    `brand` VARCHAR(100) DEFAULT NULL COMMENT 'Brand',
    `model` VARCHAR(100) DEFAULT NULL COMMENT 'Model',
    `supplier` VARCHAR(200) DEFAULT NULL COMMENT 'Supplier',
    `warranty_expire_date` DATE DEFAULT NULL COMMENT 'Warranty expiration date',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT 'Remark',
    `create_by` VARCHAR(64) DEFAULT NULL COMMENT 'Creator',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_by` VARCHAR(64) DEFAULT NULL COMMENT 'Updater',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    `del_flag` CHAR(1) DEFAULT '0' COMMENT 'Delete flag: 0=normal, 2=deleted',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_asset_code` (`asset_code`),
    KEY `idx_project_type_status` (`project_id`, `asset_type`, `status`),
    KEY `idx_use_dept` (`use_dept_id`),
    KEY `idx_duty_user` (`duty_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Asset base table';

-- -------------------------------------------
-- Table: t_asset_house (House Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_house`;
CREATE TABLE `t_asset_house` (
    `asset_id` BIGINT NOT NULL COMMENT 'Asset ID (FK)',
    `building_area` DECIMAL(12,2) DEFAULT NULL COMMENT 'Building area (sqm)',
    `inner_area` DECIMAL(12,2) DEFAULT NULL COMMENT 'Inner area (sqm)',
    `house_type` VARCHAR(32) DEFAULT NULL COMMENT 'House type (1BR/2BR/etc)',
    `house_usage` VARCHAR(32) DEFAULT NULL COMMENT 'House usage (residential/commercial)',
    `current_usage` VARCHAR(32) DEFAULT NULL COMMENT 'Current usage (self/rent/idle)',
    `current_user` VARCHAR(100) DEFAULT NULL COMMENT 'Current user/tenant',
    `contract_no` VARCHAR(64) DEFAULT NULL COMMENT 'Contract number',
    `rent_unit_price` DECIMAL(12,2) DEFAULT NULL COMMENT 'Rent per sqm',
    `rent_total` DECIMAL(12,2) DEFAULT NULL COMMENT 'Total rent',
    PRIMARY KEY (`asset_id`),
    CONSTRAINT `fk_house_asset` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='House asset extension';

-- -------------------------------------------
-- Table: t_asset_parking (Parking Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_parking`;
CREATE TABLE `t_asset_parking` (
    `asset_id` BIGINT NOT NULL COMMENT 'Asset ID (FK)',
    `parking_no` VARCHAR(64) DEFAULT NULL COMMENT 'Parking space number',
    `parking_zone` VARCHAR(64) DEFAULT NULL COMMENT 'Parking zone',
    `parking_type` VARCHAR(32) DEFAULT NULL COMMENT 'Type: fixed/temporary',
    `area` DECIMAL(10,2) DEFAULT NULL COMMENT 'Area (sqm)',
    `current_user` VARCHAR(100) DEFAULT NULL COMMENT 'Current user',
    `plate_no` VARCHAR(32) DEFAULT NULL COMMENT 'License plate number',
    `rent_price` DECIMAL(12,2) DEFAULT NULL COMMENT 'Rent price',
    `contract_no` VARCHAR(64) DEFAULT NULL COMMENT 'Contract number',
    PRIMARY KEY (`asset_id`),
    CONSTRAINT `fk_parking_asset` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Parking asset extension';

-- -------------------------------------------
-- Table: t_asset_facility (Facility Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_facility`;
CREATE TABLE `t_asset_facility` (
    `asset_id` BIGINT NOT NULL COMMENT 'Asset ID (FK)',
    `facility_type` VARCHAR(32) DEFAULT NULL COMMENT 'Facility type',
    `install_position` VARCHAR(200) DEFAULT NULL COMMENT 'Installation position',
    `equipment_no` VARCHAR(64) DEFAULT NULL COMMENT 'Equipment number',
    `maint_org` VARCHAR(200) DEFAULT NULL COMMENT 'Maintenance organization',
    `maint_phone` VARCHAR(32) DEFAULT NULL COMMENT 'Maintenance phone',
    PRIMARY KEY (`asset_id`),
    CONSTRAINT `fk_facility_asset` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Facility asset extension';

-- -------------------------------------------
-- Table: t_asset_venue (Venue Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_venue`;
CREATE TABLE `t_asset_venue` (
    `asset_id` BIGINT NOT NULL COMMENT 'Asset ID (FK)',
    `venue_type` VARCHAR(32) DEFAULT NULL COMMENT 'Venue type',
    `capacity` INT DEFAULT NULL COMMENT 'Capacity',
    `use_mode` VARCHAR(32) DEFAULT NULL COMMENT 'Use mode: free/paid/internal',
    `open_time_desc` VARCHAR(200) DEFAULT NULL COMMENT 'Opening hours description',
    `current_venue_status` VARCHAR(32) DEFAULT NULL COMMENT 'Current status: available/occupied/maintenance',
    PRIMARY KEY (`asset_id`),
    CONSTRAINT `fk_venue_asset` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Venue asset extension';

-- -------------------------------------------
-- Table: t_asset_office (Office Extension)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_office`;
CREATE TABLE `t_asset_office` (
    `asset_id` BIGINT NOT NULL COMMENT 'Asset ID (FK)',
    `office_type` VARCHAR(32) DEFAULT NULL COMMENT 'Office type: furniture/IT/other',
    `workplace_no` VARCHAR(64) DEFAULT NULL COMMENT 'Workplace/desk number',
    `use_user` VARCHAR(100) DEFAULT NULL COMMENT 'User name',
    `serial_no` VARCHAR(100) DEFAULT NULL COMMENT 'Serial number',
    `os_info` VARCHAR(200) DEFAULT NULL COMMENT 'OS information',
    `config_desc` VARCHAR(500) DEFAULT NULL COMMENT 'Configuration description',
    PRIMARY KEY (`asset_id`),
    CONSTRAINT `fk_office_asset` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Office asset extension';

-- -------------------------------------------
-- Table: t_asset_attachment (Asset Attachments)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_attachment`;
CREATE TABLE `t_asset_attachment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `asset_id` BIGINT NOT NULL COMMENT 'Asset ID (FK)',
    `file_name` VARCHAR(200) NOT NULL COMMENT 'File name',
    `file_path` VARCHAR(500) NOT NULL COMMENT 'File path',
    `file_type` VARCHAR(32) DEFAULT NULL COMMENT 'File type',
    `file_size` BIGINT DEFAULT NULL COMMENT 'File size (bytes)',
    `upload_by` VARCHAR(64) DEFAULT NULL COMMENT 'Uploader',
    `upload_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Upload time',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT 'Remark',
    PRIMARY KEY (`id`),
    KEY `idx_asset_id` (`asset_id`),
    CONSTRAINT `fk_attachment_asset` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Asset attachment table';

-- -------------------------------------------
-- Table: t_asset_maint_order (Maintenance Order)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_maint_order`;
CREATE TABLE `t_asset_maint_order` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `order_no` VARCHAR(64) NOT NULL COMMENT 'Order number (unique)',
    `asset_id` BIGINT NOT NULL COMMENT 'Asset ID (FK)',
    `project_id` BIGINT NOT NULL COMMENT 'Project ID',
    `order_type` VARCHAR(32) NOT NULL COMMENT 'Order type: fault/repair/inspection/maintenance',
    `fault_type` VARCHAR(32) DEFAULT NULL COMMENT 'Fault type',
    `title` VARCHAR(200) NOT NULL COMMENT 'Order title',
    `description` TEXT COMMENT 'Description',
    `priority` VARCHAR(32) DEFAULT 'NORMAL' COMMENT 'Priority: NORMAL/IMPORTANT/URGENT',
    `require_finish_time` DATETIME DEFAULT NULL COMMENT 'Required finish time',
    `status` VARCHAR(32) NOT NULL DEFAULT 'WAIT_ASSIGN' COMMENT 'Status: WAIT_ASSIGN/WAIT_ACCEPT/PROCESSING/WAIT_CONFIRM/CLOSED/CANCELED',
    `origin_asset_status` VARCHAR(32) DEFAULT NULL COMMENT 'Original asset status',
    `current_handler_id` BIGINT DEFAULT NULL COMMENT 'Current handler user ID',
    `requester_id` BIGINT DEFAULT NULL COMMENT 'Requester user ID',
    `create_by` VARCHAR(64) DEFAULT NULL COMMENT 'Creator',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_by` VARCHAR(64) DEFAULT NULL COMMENT 'Updater',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_project_status` (`project_id`, `status`),
    KEY `idx_handler_status` (`current_handler_id`, `status`),
    KEY `idx_asset_id` (`asset_id`),
    CONSTRAINT `fk_maint_order_asset` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Maintenance order table';

-- -------------------------------------------
-- Table: t_asset_maint_log (Maintenance Log)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_maint_log`;
CREATE TABLE `t_asset_maint_log` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `order_id` BIGINT NOT NULL COMMENT 'Order ID (FK)',
    `from_status` VARCHAR(32) DEFAULT NULL COMMENT 'From status',
    `to_status` VARCHAR(32) DEFAULT NULL COMMENT 'To status',
    `operator_id` BIGINT DEFAULT NULL COMMENT 'Operator user ID',
    `operation_type` VARCHAR(32) NOT NULL COMMENT 'Operation type: CREATE/ASSIGN/ACCEPT/START/COMPLETE/CONFIRM/REJECT/CANCEL',
    `content` TEXT COMMENT 'Operation content/remark',
    `op_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Operation time',
    PRIMARY KEY (`id`),
    KEY `idx_order_id` (`order_id`),
    CONSTRAINT `fk_maint_log_order` FOREIGN KEY (`order_id`) REFERENCES `t_asset_maint_order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Maintenance log table';

-- -------------------------------------------
-- Table: t_asset_code_rule (Asset Code Rule)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_code_rule`;
CREATE TABLE `t_asset_code_rule` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `asset_type` VARCHAR(32) NOT NULL COMMENT 'Asset type',
    `rule_name` VARCHAR(100) NOT NULL COMMENT 'Rule name',
    `pattern` VARCHAR(200) NOT NULL COMMENT 'Code pattern template',
    `separator` VARCHAR(8) DEFAULT '-' COMMENT 'Separator',
    `seq_length` INT DEFAULT 4 COMMENT 'Sequence number length',
    `reset_scope` VARCHAR(32) DEFAULT 'NONE' COMMENT 'Reset scope: NONE/BY_PROJECT/BY_PROJECT_YEAR',
    `enabled` TINYINT(1) DEFAULT 1 COMMENT 'Enabled: 1=yes, 0=no',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT 'Remark',
    `create_by` VARCHAR(64) DEFAULT NULL COMMENT 'Creator',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_by` VARCHAR(64) DEFAULT NULL COMMENT 'Updater',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    PRIMARY KEY (`id`),
    KEY `idx_asset_type` (`asset_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Asset code rule table';

-- -------------------------------------------
-- Table: t_asset_code_seq (Asset Code Sequence)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_code_seq`;
CREATE TABLE `t_asset_code_seq` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `rule_id` BIGINT NOT NULL COMMENT 'Rule ID (FK)',
    `project_id` BIGINT DEFAULT NULL COMMENT 'Project ID (for BY_PROJECT scope)',
    `year` INT DEFAULT NULL COMMENT 'Year (for BY_PROJECT_YEAR scope)',
    `current_seq` BIGINT DEFAULT 0 COMMENT 'Current sequence number',
    `version` INT DEFAULT 0 COMMENT 'Optimistic lock version',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_rule_project_year` (`rule_id`, `project_id`, `year`),
    CONSTRAINT `fk_code_seq_rule` FOREIGN KEY (`rule_id`) REFERENCES `t_asset_code_rule` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Asset code sequence table';

-- -------------------------------------------
-- Table: t_asset_maint_plan (Maintenance Plan - P1)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_maint_plan`;
CREATE TABLE `t_asset_maint_plan` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `plan_name` VARCHAR(200) NOT NULL COMMENT 'Plan name',
    `project_id` BIGINT DEFAULT NULL COMMENT 'Project ID',
    `asset_type` VARCHAR(32) DEFAULT NULL COMMENT 'Asset type filter',
    `asset_category` VARCHAR(64) DEFAULT NULL COMMENT 'Asset category filter',
    `cycle_type` VARCHAR(32) NOT NULL COMMENT 'Cycle type: MONTHLY/QUARTERLY/YEARLY',
    `next_generate_time` DATETIME DEFAULT NULL COMMENT 'Next order generation time',
    `status` VARCHAR(32) DEFAULT 'ENABLED' COMMENT 'Status: ENABLED/DISABLED',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT 'Remark',
    `create_by` VARCHAR(64) DEFAULT NULL COMMENT 'Creator',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    `update_by` VARCHAR(64) DEFAULT NULL COMMENT 'Updater',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    PRIMARY KEY (`id`),
    KEY `idx_project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Maintenance plan table';

-- -------------------------------------------
-- Table: t_asset_maint_plan_asset (Plan-Asset Relation)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_maint_plan_asset`;
CREATE TABLE `t_asset_maint_plan_asset` (
    `plan_id` BIGINT NOT NULL COMMENT 'Plan ID (FK)',
    `asset_id` BIGINT NOT NULL COMMENT 'Asset ID (FK)',
    PRIMARY KEY (`plan_id`, `asset_id`),
    CONSTRAINT `fk_plan_asset_plan` FOREIGN KEY (`plan_id`) REFERENCES `t_asset_maint_plan` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_plan_asset_asset` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Maintenance plan asset relation';

-- -------------------------------------------
-- Table: t_asset_status_log (Asset Status Change Log)
-- -------------------------------------------
DROP TABLE IF EXISTS `t_asset_status_log`;
CREATE TABLE `t_asset_status_log` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `asset_id` BIGINT NOT NULL COMMENT 'Asset ID (FK)',
    `before_status` VARCHAR(32) NOT NULL COMMENT 'Status before change',
    `after_status` VARCHAR(32) NOT NULL COMMENT 'Status after change',
    `reason` VARCHAR(500) DEFAULT NULL COMMENT 'Change reason',
    `maint_order_id` BIGINT DEFAULT NULL COMMENT 'Related maintenance order ID',
    `operator_id` BIGINT DEFAULT NULL COMMENT 'Operator user ID',
    `change_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Change time',
    PRIMARY KEY (`id`),
    KEY `idx_status_log_asset` (`asset_id`),
    KEY `idx_status_log_maint_order` (`maint_order_id`),
    CONSTRAINT `fk_status_log_asset` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Asset status change log';

SET FOREIGN_KEY_CHECKS = 1;
