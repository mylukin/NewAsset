-- =============================================================================
-- Asset Management System - Parking Asset Extension Schema
-- =============================================================================
-- Description: Parking-specific fields extension table
-- Author: AI Agent
-- Date: 2025-12-15
-- =============================================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------------------------------------------------------------
-- Table: t_asset_parking
-- Description: Parking asset extension table (1:1 with t_asset)
-- -----------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_asset_parking`;
CREATE TABLE `t_asset_parking` (
  `asset_id` bigint(20) NOT NULL COMMENT 'Asset ID (FK to t_asset.id)',
  `parking_no` varchar(20) DEFAULT NULL COMMENT '车位编号 (Parking space number)',
  `parking_zone` varchar(50) DEFAULT NULL COMMENT '车库区域 (Parking zone)',
  `parking_type` varchar(20) DEFAULT NULL COMMENT '车位类型 (fixed, temporary, reserved)',
  `area` decimal(8,2) DEFAULT NULL COMMENT '车位面积 (Parking space area in sqm)',
  `current_user` varchar(50) DEFAULT NULL COMMENT '当前使用人 (Current user)',
  `plate_no` varchar(20) DEFAULT NULL COMMENT '车牌号 (License plate number)',
  `rent_price` decimal(10,2) DEFAULT NULL COMMENT '租金/包月金额 (Rent price)',
  `contract_no` varchar(50) DEFAULT NULL COMMENT '合同编号 (Contract number)',
  `create_by` varchar(64) DEFAULT '' COMMENT 'Creator',
  `create_time` datetime DEFAULT NULL COMMENT 'Creation time',
  `update_by` varchar(64) DEFAULT '' COMMENT 'Updater',
  `update_time` datetime DEFAULT NULL COMMENT 'Update time',
  PRIMARY KEY (`asset_id`),
  CONSTRAINT `fk_asset_parking_asset_id` FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Parking Asset Extension Table';

-- -----------------------------------------------------------------------------
-- Dictionary Type: parking_type (车位类型)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('车位类型', 'parking_type', '0', 'admin', NOW(), 'admin', NOW(), '车位类型字典');

INSERT INTO `sys_dict_data` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('parking_type', '固定车位', 'fixed', 1, '', 'success', 'Y', '0', 'admin', NOW(), 'admin', NOW(), 'Fixed Parking Space'),
('parking_type', '临停车位', 'temporary', 2, '', 'warning', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Temporary Parking Space'),
('parking_type', '预留车位', 'reserved', 3, '', 'info', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Reserved Parking Space');

-- -----------------------------------------------------------------------------
-- Dictionary Type: parking_zone (车库区域)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('车库区域', 'parking_zone', '0', 'admin', NOW(), 'admin', NOW(), '车库区域字典');

INSERT INTO `sys_dict_data` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('parking_zone', '地下1层', 'underground_1', 1, '', 'primary', 'Y', '0', 'admin', NOW(), 'admin', NOW(), 'Underground Level 1'),
('parking_zone', '地下2层', 'underground_2', 2, '', 'info', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Underground Level 2'),
('parking_zone', '地面', 'ground', 3, '', 'success', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Ground Level');

SET FOREIGN_KEY_CHECKS = 1;

-- =============================================================================
-- End of Parking Asset Extension Schema
-- =============================================================================
