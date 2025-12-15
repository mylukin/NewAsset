-- =============================================================================
-- Asset Management System - Dictionary Data
-- =============================================================================
-- Description: System dictionaries for asset types, status, and enumerations
-- Author: AI Agent
-- Date: 2025-12-15
-- =============================================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------------------------------------------------------------
-- Dictionary Type: asset_type (Asset Types)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('资产类型', 'asset_type', '0', 'admin', NOW(), 'admin', NOW(), '资产类型字典');

INSERT INTO `sys_dict_data` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('asset_type', '房源', 'HOUSE', 1, '', 'primary', 'Y', '0', 'admin', NOW(), 'admin', NOW(), 'House/Property'),
('asset_type', '车库', 'PARKING', 2, '', 'primary', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Parking Space'),
('asset_type', '配套', 'FACILITY', 3, '', 'primary', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Facility/Amenity'),
('asset_type', '场馆', 'VENUE', 4, '', 'primary', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Venue'),
('asset_type', '办公', 'OFFICE', 5, '', 'primary', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Office Space');

-- -----------------------------------------------------------------------------
-- Dictionary Type: asset_status (Asset Status)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('资产状态', 'asset_status', '0', 'admin', NOW(), 'admin', NOW(), '资产状态字典');

INSERT INTO `sys_dict_data` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('asset_status', '在建/未投入', 'under_construction', 1, '', 'info', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Under Construction'),
('asset_status', '可用-自用', 'available_self', 2, '', 'success', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Available for Self-Use'),
('asset_status', '可用-对外出租', 'available_rent', 3, '', 'success', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Available for Rent'),
('asset_status', '可用-空置', 'available_idle', 4, '', 'success', 'Y', '0', 'admin', NOW(), 'admin', NOW(), 'Available and Idle'),
('asset_status', '暂停使用', 'temp_closed', 5, '', 'warning', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Temporarily Closed'),
('asset_status', '故障/停用', 'fault', 6, '', 'warning', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Faulty/Disabled'),
('asset_status', '维修中', 'maintaining', 7, '', 'warning', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Under Maintenance'),
('asset_status', '计划报废', 'to_be_scrapped', 8, '', 'danger', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Planned to be Scrapped'),
('asset_status', '已报废', 'scrapped', 9, '', 'danger', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Already Scrapped');

-- -----------------------------------------------------------------------------
-- Dictionary Type: ownership_type (Ownership Types)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('产权类型', 'ownership_type', '0', 'admin', NOW(), 'admin', NOW(), '产权类型字典');

INSERT INTO `sys_dict_data` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('ownership_type', '自有', 'self_owned', 1, '', 'primary', 'Y', '0', 'admin', NOW(), 'admin', NOW(), 'Self-Owned'),
('ownership_type', '租入', 'rented_in', 2, '', 'info', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Rented In'),
('ownership_type', '托管', 'entrusted', 3, '', 'warning', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Entrusted Management');

-- -----------------------------------------------------------------------------
-- Dictionary Type: depreciation_method (Depreciation Methods)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('折旧方法', 'depreciation_method', '0', 'admin', NOW(), 'admin', NOW(), '折旧方法字典');

INSERT INTO `sys_dict_data` (`dict_type`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('depreciation_method', '平均年限法', 'straight_line', 1, '', 'primary', 'Y', '0', 'admin', NOW(), 'admin', NOW(), 'Straight Line Method'),
('depreciation_method', '双倍余额递减法', 'declining_balance', 2, '', 'info', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Double Declining Balance Method'),
('depreciation_method', '工作量法', 'units_of_production', 3, '', 'warning', 'N', '0', 'admin', NOW(), 'admin', NOW(), 'Units of Production Method');

SET FOREIGN_KEY_CHECKS = 1;

-- =============================================================================
-- End of Asset Dictionary Data
-- =============================================================================
