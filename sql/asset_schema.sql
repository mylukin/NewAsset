-- ============================================================
-- Asset Management System Database Schema
-- RuoYi-Vue Framework Compatible
-- ============================================================

-- ============================================================
-- Core Asset Tables
-- ============================================================

-- Base Asset Table (common fields for all asset types)
CREATE TABLE IF NOT EXISTS t_asset (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    asset_code      VARCHAR(50) NOT NULL UNIQUE COMMENT 'Unique asset code',
    asset_name      VARCHAR(200) NOT NULL COMMENT 'Asset name',
    asset_type      VARCHAR(50) NOT NULL COMMENT 'Asset type (house, office, parking, venue, facility)',
    status          VARCHAR(20) NOT NULL DEFAULT 'normal' COMMENT 'Status (normal, maintenance, disposed, transferred)',
    project_id      BIGINT COMMENT 'Project/location ID',
    project_name    VARCHAR(200) COMMENT 'Project/location name',
    owner_dept_id   BIGINT COMMENT 'Owner department ID',
    owner_dept_name VARCHAR(200) COMMENT 'Owner department name',
    manager_id      BIGINT COMMENT 'Manager user ID',
    manager_name    VARCHAR(100) COMMENT 'Manager name',
    create_by       VARCHAR(64) COMMENT 'Created by',
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_by       VARCHAR(64) COMMENT 'Updated by',
    update_time     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    remark          VARCHAR(500) COMMENT 'Remark',
    del_flag        CHAR(1) DEFAULT '0' COMMENT 'Delete flag (0=normal, 2=deleted)',
    INDEX idx_asset_code (asset_code),
    INDEX idx_asset_type (asset_type),
    INDEX idx_status (status),
    INDEX idx_project_id (project_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Base Asset Table';

-- ============================================================
-- House Asset Extension Table
-- ============================================================
CREATE TABLE IF NOT EXISTS t_asset_house (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    asset_id            BIGINT NOT NULL COMMENT 'Reference to t_asset.id',
    area                DECIMAL(10,2) COMMENT 'Area in square meters',
    house_type          VARCHAR(50) COMMENT 'House type (apartment, villa, commercial)',
    floor_number        VARCHAR(20) COMMENT 'Floor number',
    building            VARCHAR(100) COMMENT 'Building name/number',
    room_number         VARCHAR(50) COMMENT 'Room number',
    is_for_rent         TINYINT(1) DEFAULT 0 COMMENT 'Is available for rent',
    monthly_rent        DECIMAL(12,2) COMMENT 'Monthly rent amount',
    rental_deposit      DECIMAL(12,2) COMMENT 'Rental deposit amount',
    tenant_name         VARCHAR(100) COMMENT 'Current tenant name',
    tenant_contact      VARCHAR(50) COMMENT 'Tenant contact info',
    rental_start_date   DATE COMMENT 'Rental start date',
    rental_end_date     DATE COMMENT 'Rental end date',
    extension_fields    JSON COMMENT 'Extension fields (JSON)',
    create_time         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    UNIQUE KEY uk_asset_id (asset_id),
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='House Asset Extension';

-- ============================================================
-- Office Asset Extension Table
-- ============================================================
CREATE TABLE IF NOT EXISTS t_asset_office (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    asset_id            BIGINT NOT NULL COMMENT 'Reference to t_asset.id',
    asset_tag           VARCHAR(50) COMMENT 'Asset tag/barcode',
    device_type         VARCHAR(50) COMMENT 'Device type (LAPTOP, DESKTOP, MONITOR, etc.)',
    brand               VARCHAR(100) COMMENT 'Brand/Manufacturer',
    model               VARCHAR(100) COMMENT 'Model number',
    serial_number       VARCHAR(100) COMMENT 'Serial number',
    purchase_date       DATE COMMENT 'Purchase date',
    purchase_price      DECIMAL(12,2) COMMENT 'Purchase price',
    warranty_expiry     DATE COMMENT 'Warranty expiry date',
    assigned_to         VARCHAR(100) COMMENT 'Assigned user',
    assigned_dept       VARCHAR(200) COMMENT 'Assigned department',
    location            VARCHAR(200) COMMENT 'Physical location',
    extension_fields    JSON COMMENT 'Extension fields (JSON)',
    create_time         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    UNIQUE KEY uk_asset_id (asset_id),
    INDEX idx_asset_tag (asset_tag),
    INDEX idx_serial_number (serial_number),
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Office Asset Extension';

-- ============================================================
-- Parking Asset Extension Table
-- ============================================================
CREATE TABLE IF NOT EXISTS t_asset_parking (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    asset_id            BIGINT NOT NULL COMMENT 'Reference to t_asset.id',
    space_number        VARCHAR(50) COMMENT 'Parking space number',
    zone_code           VARCHAR(50) COMMENT 'Zone code',
    parking_type        VARCHAR(50) COMMENT 'Parking type (covered, open, underground, mechanical)',
    floor_level         VARCHAR(20) COMMENT 'Floor level',
    is_for_rent         TINYINT(1) DEFAULT 0 COMMENT 'Is available for rent',
    monthly_rent        DECIMAL(12,2) COMMENT 'Monthly rent amount',
    tenant_name         VARCHAR(100) COMMENT 'Current tenant name',
    tenant_contact      VARCHAR(50) COMMENT 'Tenant contact info',
    rental_start_date   DATE COMMENT 'Rental start date',
    rental_end_date     DATE COMMENT 'Rental end date',
    extension_fields    JSON COMMENT 'Extension fields (JSON)',
    create_time         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    UNIQUE KEY uk_asset_id (asset_id),
    INDEX idx_zone_code (zone_code),
    INDEX idx_space_number (space_number),
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Parking Asset Extension';

-- ============================================================
-- Venue Asset Extension Table
-- ============================================================
CREATE TABLE IF NOT EXISTS t_asset_venue (
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    asset_id                BIGINT NOT NULL COMMENT 'Reference to t_asset.id',
    venue_type              VARCHAR(50) COMMENT 'Venue type (meeting_room, basketball, conference_hall)',
    capacity                INT COMMENT 'Maximum capacity',
    current_venue_status    VARCHAR(20) DEFAULT 'available' COMMENT 'Current status (available, occupied, reserved, maintenance)',
    building                VARCHAR(100) COMMENT 'Building name',
    floor_level             VARCHAR(20) COMMENT 'Floor level',
    room_number             VARCHAR(50) COMMENT 'Room number',
    area                    DECIMAL(10,2) COMMENT 'Area in square meters',
    hourly_rate             DECIMAL(10,2) COMMENT 'Hourly rate',
    opening_time            TIME COMMENT 'Opening time',
    closing_time            TIME COMMENT 'Closing time',
    has_projector           TINYINT(1) DEFAULT 0 COMMENT 'Has projector',
    has_video_conferencing  TINYINT(1) DEFAULT 0 COMMENT 'Has video conferencing',
    has_whiteboard          TINYINT(1) DEFAULT 0 COMMENT 'Has whiteboard',
    extension_fields        JSON COMMENT 'Extension fields (JSON)',
    create_time             DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time             DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    UNIQUE KEY uk_asset_id (asset_id),
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Venue Asset Extension';

-- ============================================================
-- Facility Asset Extension Table
-- ============================================================
CREATE TABLE IF NOT EXISTS t_asset_facility (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    asset_id            BIGINT NOT NULL COMMENT 'Reference to t_asset.id',
    facility_type       VARCHAR(50) COMMENT 'Facility type (HVAC, ELEVATOR, FIRE_SYSTEM)',
    building            VARCHAR(100) COMMENT 'Building name',
    floor_level         VARCHAR(20) COMMENT 'Floor level',
    location            VARCHAR(200) COMMENT 'Physical location',
    extension_fields    JSON COMMENT 'Extension fields (JSON)',
    create_time         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    UNIQUE KEY uk_asset_id (asset_id),
    FOREIGN KEY (asset_id) REFERENCES t_asset(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Facility Asset Extension';

-- Equipment for Facility
CREATE TABLE IF NOT EXISTS t_facility_equipment (
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    facility_asset_id       BIGINT NOT NULL COMMENT 'Reference to t_asset_facility.id',
    equipment_id            VARCHAR(50) NOT NULL COMMENT 'Equipment ID',
    name                    VARCHAR(200) COMMENT 'Equipment name',
    equipment_type          VARCHAR(50) COMMENT 'Equipment type',
    model                   VARCHAR(100) COMMENT 'Model number',
    manufacturer            VARCHAR(100) COMMENT 'Manufacturer',
    installation_date       DATE COMMENT 'Installation date',
    last_maintenance_date   DATE COMMENT 'Last maintenance date',
    next_maintenance_date   DATE COMMENT 'Next scheduled maintenance',
    status                  VARCHAR(20) DEFAULT 'OPERATIONAL' COMMENT 'Status (OPERATIONAL, MAINTENANCE, FAULTY, DECOMMISSIONED)',
    purchase_price          DECIMAL(12,2) COMMENT 'Purchase price',
    create_time             DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time             DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    FOREIGN KEY (facility_asset_id) REFERENCES t_asset_facility(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Facility Equipment';

-- Vendors for Facility
CREATE TABLE IF NOT EXISTS t_facility_vendor (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    facility_asset_id   BIGINT NOT NULL COMMENT 'Reference to t_asset_facility.id',
    vendor_id           VARCHAR(50) NOT NULL COMMENT 'Vendor ID',
    name                VARCHAR(200) COMMENT 'Vendor name',
    contact_person      VARCHAR(100) COMMENT 'Contact person',
    phone               VARCHAR(50) COMMENT 'Phone number',
    email               VARCHAR(100) COMMENT 'Email',
    address             VARCHAR(500) COMMENT 'Address',
    service_type        VARCHAR(100) COMMENT 'Service type',
    create_time         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_time         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    FOREIGN KEY (facility_asset_id) REFERENCES t_asset_facility(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Facility Vendor';

-- ============================================================
-- Maintenance Work Order Tables
-- ============================================================

-- Maintenance Work Order
CREATE TABLE IF NOT EXISTS t_maintenance_order (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    order_number        VARCHAR(50) NOT NULL UNIQUE COMMENT 'Work order number',
    asset_id            BIGINT COMMENT 'Related asset ID',
    title               VARCHAR(200) NOT NULL COMMENT 'Work order title',
    description         TEXT COMMENT 'Issue description',
    status              VARCHAR(20) NOT NULL DEFAULT 'NEW' COMMENT 'Status (NEW, IN_PROGRESS, PENDING_PARTS, ON_HOLD, COMPLETED, CANCELLED)',
    priority            INT DEFAULT 2 COMMENT 'Priority (1=Low, 2=Medium, 3=High, 4=Critical)',
    assigned_to         VARCHAR(100) COMMENT 'Assigned technician',
    assigned_dept       VARCHAR(200) COMMENT 'Assigned department',
    requested_by        VARCHAR(100) COMMENT 'Requester',
    requested_at        DATETIME COMMENT 'Request date/time',
    scheduled_start     DATETIME COMMENT 'Scheduled start time',
    scheduled_end       DATETIME COMMENT 'Scheduled end time',
    actual_start        DATETIME COMMENT 'Actual start time',
    actual_end          DATETIME COMMENT 'Actual end time',
    estimated_cost      DECIMAL(12,2) COMMENT 'Estimated cost',
    actual_cost         DECIMAL(12,2) COMMENT 'Actual cost',
    resolution_notes    TEXT COMMENT 'Resolution notes',
    create_by           VARCHAR(64) COMMENT 'Created by',
    create_time         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
    update_by           VARCHAR(64) COMMENT 'Updated by',
    update_time         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    del_flag            CHAR(1) DEFAULT '0' COMMENT 'Delete flag',
    INDEX idx_order_number (order_number),
    INDEX idx_asset_id (asset_id),
    INDEX idx_status (status),
    INDEX idx_priority (priority),
    INDEX idx_assigned_to (assigned_to)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Maintenance Work Order';

-- Maintenance Order Log (Audit Trail)
CREATE TABLE IF NOT EXISTS t_maintenance_order_log (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    order_id            BIGINT NOT NULL COMMENT 'Work order ID',
    action              VARCHAR(50) NOT NULL COMMENT 'Action type (CREATE, UPDATE, STATUS_CHANGE, ASSIGN, etc.)',
    from_status         VARCHAR(20) COMMENT 'Previous status',
    to_status           VARCHAR(20) COMMENT 'New status',
    from_assignee       VARCHAR(100) COMMENT 'Previous assignee',
    to_assignee         VARCHAR(100) COMMENT 'New assignee',
    comments            TEXT COMMENT 'Comments',
    operator            VARCHAR(100) COMMENT 'Operator',
    operate_time        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Operation time',
    INDEX idx_order_id (order_id),
    FOREIGN KEY (order_id) REFERENCES t_maintenance_order(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Maintenance Order Log';

-- ============================================================
-- Asset Attachment Table
-- ============================================================
CREATE TABLE IF NOT EXISTS t_asset_attachment (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    asset_id            BIGINT NOT NULL COMMENT 'Asset ID',
    file_name           VARCHAR(200) NOT NULL COMMENT 'Original file name',
    file_path           VARCHAR(500) NOT NULL COMMENT 'Storage path',
    file_size           BIGINT COMMENT 'File size in bytes',
    file_type           VARCHAR(50) COMMENT 'File type/extension',
    mime_type           VARCHAR(100) COMMENT 'MIME type',
    upload_by           VARCHAR(100) COMMENT 'Uploaded by',
    upload_time         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Upload time',
    remark              VARCHAR(500) COMMENT 'Remark',
    del_flag            CHAR(1) DEFAULT '0' COMMENT 'Delete flag',
    INDEX idx_asset_id (asset_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Asset Attachment';

-- ============================================================
-- Asset Change Log Table
-- ============================================================
CREATE TABLE IF NOT EXISTS t_asset_change_log (
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key',
    asset_id            BIGINT NOT NULL COMMENT 'Asset ID',
    change_type         VARCHAR(50) NOT NULL COMMENT 'Change type (CREATE, UPDATE, STATUS_CHANGE, TRANSFER)',
    field_name          VARCHAR(100) COMMENT 'Changed field name',
    old_value           TEXT COMMENT 'Old value',
    new_value           TEXT COMMENT 'New value',
    operator            VARCHAR(100) COMMENT 'Operator',
    operate_time        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Operation time',
    remark              VARCHAR(500) COMMENT 'Remark',
    INDEX idx_asset_id (asset_id),
    INDEX idx_change_type (change_type),
    INDEX idx_operate_time (operate_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Asset Change Log';
