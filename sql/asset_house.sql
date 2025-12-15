-- =============================================================================
-- Asset Management System - House Asset Extension Schema
-- =============================================================================
-- Description: House-specific fields extension table
-- Author: AI Agent
-- Date: 2025-12-15
-- =============================================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------------------------------------------------------------
-- Table: t_asset_house
-- Description: House asset extension table (1:1 with t_asset)
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_asset_house`;
CREATE TABLE `t_asset_house` (
  `asset_id` bigint(20) NOT NULL COMMENT 'Asset ID (FK to t_asset.id)',
  `building_area` decimal(10,2) DEFAULT NULL COMMENT '建筑面积 (Building area in sqm)',
  `inner_area` decimal(10,2) DEFAULT NULL COMMENT '套内面积 (Inner usable area in sqm)',
  `house_type` varchar(20) DEFAULT NULL COMMENT '户型 (one_room, two_room, three_room, etc.)',
  `house_usage` varchar(20) DEFAULT NULL COMMENT '房屋用途 (residential, commercial, apartment)',
  `current_usage` varchar(20) DEFAULT NULL COMMENT '当前用途 (self_use, rented, idle)',
  `current_user` varchar(50) DEFAULT NULL COMMENT '当前使用人/承租人 (Current user/tenant)',
  `contract_no` varchar(50) DEFAULT NULL COMMENT '合同编号 (Contract number)',
  `rent_unit_price` decimal(10,2) DEFAULT NULL COMMENT '租金单价 (Rent unit price)',
  `rent_total` decimal(12,2) DEFAULT NULL COMMENT '租金总额 (Total rent)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'Creator',
  `create_time` datetime DEFAULT NULL COMMENT 'Creation time',
  `update_by` varchar(64) DEFAULT '' COMMENT 'Updater',
  `update_time` datetime DEFAULT NULL COMMENT 'Update time',
  PRIMARY KEY (`asset_id`),
  CONSTRAINT `fk_asset_house_asset_id` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='House Asset Extension Table';

-- -----------------------------------------------------------------------------
-- Dictionary Type: house_type (户型)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('户型', 'house_type', '0', 'admin', NOW(), 'admin', NOW(), '户型字典');

INSERT INTO `sys_dict_data` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('house_type', '一居', 'one_room', 1, '', 'primary', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'One Room'),
('house_type', '两居', 'two_room', 2, '', 'primary', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Two Rooms'),
('house_type', '三居', 'three_room', 3, '', 'primary', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Three Rooms'),
('house_type', '四居', 'four_room', 4, '', 'primary', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Four Rooms'),
('house_type', '其他', 'other', 5, '', 'info', 'Y', '0', 'admin', NOW(), 'admin', NOW(), 'Other');

-- -----------------------------------------------------------------------------
-- Dictionary Type: house_usage (房屋用途)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('房屋用途', 'house_usage', '0', 'admin', NOW(), 'admin', NOW(), '房屋用途字典');

INSERT INTO `sys_dict_data` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('house_usage', '住宅', 'residential', 1, '', 'success', 'Y', '0', 'admin', NOW(), 'admin', NOW(), 'Residential'),
('house_usage', '商用', 'commercial', 2, '', 'warning', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Commercial'),
('house_usage', '公寓', 'apartment', 3, '', 'info', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Apartment');

-- -----------------------------------------------------------------------------
-- Dictionary Type: current_usage (当前用途)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('当前用途', 'current_usage', '0', 'admin', NOW(), 'admin', NOW(), '当前用途字典');

INSERT INTO `sys_dict_data` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('current_usage', '自住', 'self_use', 1, '', 'success', 'Y', '0', 'admin', NOW(), 'admin', NOW(), 'Self-Use'),
('current_usage', '出租', 'rented', 2, '', 'warning', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Rented'),
('current_usage', '空置', 'idle', 3, '', 'danger', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Idle');

SET FOREIGN_KEY_CHECKS = 1;

-- =============================================================================
-- End of House Asset Extension Schema
-- =============================================================================
