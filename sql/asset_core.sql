-- =============================================================================
-- Asset Management System - Core Database Schema
-- =============================================================================
-- Description: Foundation tables for the asset management system
-- Author: AI Agent
-- Date: 2025-12-15
-- =============================================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------------------------------------------------------------
-- Table: t_asset_location
-- Description: 3-level location hierarchy (Building -> Floor -> Unit)
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_asset_location`;
CREATE TABLE `t_asset_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `parent_id` bigint(20) DEFAULT 0 COMMENT 'Parent location ID (0 for root)',
  `name` varchar(100) NOT NULL COMMENT 'Location name',
  `code` varchar(50) DEFAULT NULL COMMENT 'Location code',
  `level` tinyint(4) NOT NULL COMMENT 'Hierarchy level (1=Building, 2=Floor, 3=Unit)',
  `sort_order` int(11) DEFAULT 0 COMMENT 'Display sort order',
  `project_id` bigint(20) NOT NULL COMMENT 'Project ID',
  `status` char(1) DEFAULT '0' COMMENT 'Status (0=normal, 1=disabled)',
  `del_flag` char(1) DEFAULT '0' COMMENT 'Delete flag (0=exists, 2=deleted)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'Creator',
  `create_time` datetime DEFAULT NULL COMMENT 'Creation time',
  `update_by` varchar(64) DEFAULT '' COMMENT 'Updater',
  `update_time` datetime DEFAULT NULL COMMENT 'Update time',
  `remark` varchar(500) DEFAULT NULL COMMENT 'Remark',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_project_level` (`project_id`, `level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Asset Location Hierarchy';

-- -----------------------------------------------------------------------------
-- Table: t_asset_code_seq
-- Description: Sequence management for asset code generation
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_asset_code_seq`;
CREATE TABLE `t_asset_code_seq` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `project_id` bigint(20) NOT NULL COMMENT 'Project ID',
  `asset_type` varchar(20) NOT NULL COMMENT 'Asset type (HOUSE, PARKING, FACILITY, VENUE, OFFICE)',
  `prefix` varchar(10) NOT NULL COMMENT 'Code prefix (HA, PA, FA, VE, OF)',
  `current_seq` bigint(20) NOT NULL DEFAULT 0 COMMENT 'Current sequence number',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT 'Optimistic lock version',
  `create_by` varchar(64) DEFAULT '' COMMENT 'Creator',
  `create_time` datetime DEFAULT NULL COMMENT 'Creation time',
  `update_by` varchar(64) DEFAULT '' COMMENT 'Updater',
  `update_time` datetime DEFAULT NULL COMMENT 'Update time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_project_type` (`project_id`, `asset_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Asset Code Sequence';

-- -----------------------------------------------------------------------------
-- Table: t_asset
-- Description: Base asset table with common fields for all asset types
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_asset`;
CREATE TABLE `t_asset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `asset_code` varchar(50) NOT NULL COMMENT 'Asset code (unique, auto-generated)',
  `name` varchar(200) NOT NULL COMMENT 'Asset name',
  `asset_type` varchar(20) NOT NULL COMMENT 'Asset type (HOUSE, PARKING, FACILITY, VENUE, OFFICE)',
  `status` varchar(30) NOT NULL DEFAULT 'AVAILABLE_IDLE' COMMENT 'Asset status (9-state machine)',
  `project_id` bigint(20) NOT NULL COMMENT 'Project ID',
  `building_id` bigint(20) DEFAULT NULL COMMENT 'Building ID (level 1 location)',
  `floor_id` bigint(20) DEFAULT NULL COMMENT 'Floor ID (level 2 location)',
  `unit_id` bigint(20) DEFAULT NULL COMMENT 'Unit ID (level 3 location)',
  `location_desc` varchar(500) DEFAULT NULL COMMENT 'Location description text',
  `responsible_person` varchar(100) DEFAULT NULL COMMENT 'Responsible person name',
  `responsible_phone` varchar(20) DEFAULT NULL COMMENT 'Responsible person phone',
  `purchase_date` date DEFAULT NULL COMMENT 'Purchase/acquisition date',
  `purchase_price` decimal(12,2) DEFAULT NULL COMMENT 'Purchase price',
  `warranty_expire_date` date DEFAULT NULL COMMENT 'Warranty expiration date',
  `description` text COMMENT 'Asset description',
  `status_changed_at` datetime DEFAULT NULL COMMENT 'Last status change time',
  `status_changed_by` varchar(64) DEFAULT NULL COMMENT 'Last status change operator',
  `del_flag` char(1) DEFAULT '0' COMMENT 'Delete flag (0=exists, 2=deleted)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'Creator',
  `create_time` datetime DEFAULT NULL COMMENT 'Creation time',
  `update_by` varchar(64) DEFAULT '' COMMENT 'Updater',
  `update_time` datetime DEFAULT NULL COMMENT 'Update time',
  `remark` varchar(500) DEFAULT NULL COMMENT 'Remark',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_asset_code` (`asset_code`),
  KEY `idx_project_type_status` (`project_id`, `asset_type`, `status`),
  KEY `idx_building_id` (`building_id`),
  KEY `idx_floor_id` (`floor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Base Asset Table';

-- -----------------------------------------------------------------------------
-- Table: t_asset_attachment
-- Description: File attachments for assets
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_asset_attachment`;
CREATE TABLE `t_asset_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `asset_id` bigint(20) NOT NULL COMMENT 'Asset ID',
  `file_name` varchar(200) NOT NULL COMMENT 'Original file name',
  `file_path` varchar(500) NOT NULL COMMENT 'Storage file path',
  `file_size` bigint(20) DEFAULT NULL COMMENT 'File size in bytes',
  `file_type` varchar(50) DEFAULT NULL COMMENT 'File MIME type',
  `sort_order` int(11) DEFAULT 0 COMMENT 'Display sort order',
  `del_flag` char(1) DEFAULT '0' COMMENT 'Delete flag (0=exists, 2=deleted)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'Creator',
  `create_time` datetime DEFAULT NULL COMMENT 'Creation time',
  `update_by` varchar(64) DEFAULT '' COMMENT 'Updater',
  `update_time` datetime DEFAULT NULL COMMENT 'Update time',
  PRIMARY KEY (`id`),
  KEY `idx_asset_id` (`asset_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Asset Attachments';

SET FOREIGN_KEY_CHECKS = 1;

-- =============================================================================
-- Initial Data: Asset Code Sequence Initialization
-- Note: Insert sequences when project is created
-- =============================================================================

-- Example: Initialize sequences for a project (project_id = 1)
-- INSERT INTO `t_asset_code_seq` (`project_id`, `asset_type`, `prefix`, `current_seq`, `create_time`) VALUES
-- (1, 'HOUSE', 'HA', 0, NOW()),
-- (1, 'PARKING', 'PA', 0, NOW()),
-- (1, 'FACILITY', 'FA', 0, NOW()),
-- (1, 'VENUE', 'VE', 0, NOW()),
-- (1, 'OFFICE', 'OF', 0, NOW());
