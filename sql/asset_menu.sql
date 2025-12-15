-- =============================================================================
-- Asset Management System - Menu and Permission Configuration
-- =============================================================================
-- Description: Menu entries and permission strings for asset management module
-- Author: AI Agent
-- Date: 2025-12-15
-- =============================================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------------------------------------------------------------
-- Parent Menu: 资产管理 (Asset Management)
-- Menu ID: 2100 (assuming RuoYi parent menus use 2xxx range)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('资产管理', '0', '21', 'asset', NULL, '', '1', '0', 'M', '0', '0', '', 'asset', 'admin', NOW(), 'admin', NOW(), '资产管理模块主菜单');

-- -----------------------------------------------------------------------------
-- Submenu: 房源资产 (House Assets)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('房源资产', '2100', '1', 'house', 'asset/house/index', '', '0', '0', 'C', '0', '0', 'asset:house:list', 'house', 'admin', NOW(), 'admin', NOW(), '房源资产管理');

-- Button permissions for 房源资产
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('房源资产查询', '2100', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:house:query', '#', 'admin', NOW(), 'admin', NOW(), ''),
('房源资产新增', '2100', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:house:add', '#', 'admin', NOW(), 'admin', NOW(), ''),
('房源资产修改', '2100', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:house:edit', '#', 'admin', NOW(), 'admin', NOW(), ''),
('房源资产删除', '2100', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:house:remove', '#', 'admin', NOW(), 'admin', NOW(), ''),
('房源资产导出', '2100', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:house:export', '#', 'admin', NOW(), 'admin', NOW(), ''),
('房源资产导入', '2100', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:house:import', '#', 'admin', NOW(), 'admin', NOW(), '');

-- -----------------------------------------------------------------------------
-- Submenu: 车库资产 (Parking Assets)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('车库资产', '2100', '2', 'parking', 'asset/parking/index', '', '0', '0', 'C', '0', '0', 'asset:parking:list', 'parking', 'admin', NOW(), 'admin', NOW(), '车库资产管理');

-- Button permissions for 车库资产
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('车库资产查询', '2100', '2', '', '', '', '0', '0', 'F', '0', '0', 'asset:parking:query', '#', 'admin', NOW(), 'admin', NOW(), ''),
('车库资产新增', '2100', '2', '', '', '', '0', '0', 'F', '0', '0', 'asset:parking:add', '#', 'admin', NOW(), 'admin', NOW(), ''),
('车库资产修改', '2100', '2', '', '', '', '0', '0', 'F', '0', '0', 'asset:parking:edit', '#', 'admin', NOW(), 'admin', NOW(), ''),
('车库资产删除', '2100', '2', '', '', '', '0', '0', 'F', '0', '0', 'asset:parking:remove', '#', 'admin', NOW(), 'admin', NOW(), ''),
('车库资产导出', '2100', '2', '', '', '', '0', '0', 'F', '0', '0', 'asset:parking:export', '#', 'admin', NOW(), 'admin', NOW(), ''),
('车库资产导入', '2100', '2', '', '', '', '0', '0', 'F', '0', '0', 'asset:parking:import', '#', 'admin', NOW(), 'admin', NOW(), '');

-- -----------------------------------------------------------------------------
-- Submenu: 配套资产 (Facility Assets)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('配套资产', '2100', '3', 'facility', 'asset/facility/index', '', '0', '0', 'C', '0', '0', 'asset:facility:list', 'tool', 'admin', NOW(), 'admin', NOW(), '配套资产管理');

-- Button permissions for 配套资产
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('配套资产查询', '2100', '3', '', '', '', '0', '0', 'F', '0', '0', 'asset:facility:query', '#', 'admin', NOW(), 'admin', NOW(), ''),
('配套资产新增', '2100', '3', '', '', '', '0', '0', 'F', '0', '0', 'asset:facility:add', '#', 'admin', NOW(), 'admin', NOW(), ''),
('配套资产修改', '2100', '3', '', '', '', '0', '0', 'F', '0', '0', 'asset:facility:edit', '#', 'admin', NOW(), 'admin', NOW(), ''),
('配套资产删除', '2100', '3', '', '', '', '0', '0', 'F', '0', '0', 'asset:facility:remove', '#', 'admin', NOW(), 'admin', NOW(), ''),
('配套资产导出', '2100', '3', '', '', '', '0', '0', 'F', '0', '0', 'asset:facility:export', '#', 'admin', NOW(), 'admin', NOW(), ''),
('配套资产导入', '2100', '3', '', '', '', '0', '0', 'F', '0', '0', 'asset:facility:import', '#', 'admin', NOW(), 'admin', NOW(), '');

-- -----------------------------------------------------------------------------
-- Submenu: 场馆资产 (Venue Assets)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('场馆资产', '2100', '4', 'venue', 'asset/venue/index', '', '0', '0', 'C', '0', '0', 'asset:venue:list', 'peoples', 'admin', NOW(), 'admin', NOW(), '场馆资产管理');

-- Button permissions for 场馆资产
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('场馆资产查询', '2100', '4', '', '', '', '0', '0', 'F', '0', '0', 'asset:venue:query', '#', 'admin', NOW(), 'admin', NOW(), ''),
('场馆资产新增', '2100', '4', '', '', '', '0', '0', 'F', '0', '0', 'asset:venue:add', '#', 'admin', NOW(), 'admin', NOW(), ''),
('场馆资产修改', '2100', '4', '', '', '', '0', '0', 'F', '0', '0', 'asset:venue:edit', '#', 'admin', NOW(), 'admin', NOW(), ''),
('场馆资产删除', '2100', '4', '', '', '', '0', '0', 'F', '0', '0', 'asset:venue:remove', '#', 'admin', NOW(), 'admin', NOW(), ''),
('场馆资产导出', '2100', '4', '', '', '', '0', '0', 'F', '0', '0', 'asset:venue:export', '#', 'admin', NOW(), 'admin', NOW(), ''),
('场馆资产导入', '2100', '4', '', '', '', '0', '0', 'F', '0', '0', 'asset:venue:import', '#', 'admin', NOW(), 'admin', NOW(), '');

-- -----------------------------------------------------------------------------
-- Submenu: 办公资产 (Office Assets)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('办公资产', '2100', '5', 'office', 'asset/office/index', '', '0', '0', 'C', '0', '0', 'asset:office:list', 'office', 'admin', NOW(), 'admin', NOW(), '办公资产管理');

-- Button permissions for 办公资产
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('办公资产查询', '2100', '5', '', '', '', '0', '0', 'F', '0', '0', 'asset:office:query', '#', 'admin', NOW(), 'admin', NOW(), ''),
('办公资产新增', '2100', '5', '', '', '', '0', '0', 'F', '0', '0', 'asset:office:add', '#', 'admin', NOW(), 'admin', NOW(), ''),
('办公资产修改', '2100', '5', '', '', '', '0', '0', 'F', '0', '0', 'asset:office:edit', '#', 'admin', NOW(), 'admin', NOW(), ''),
('办公资产删除', '2100', '5', '', '', '', '0', '0', 'F', '0', '0', 'asset:office:remove', '#', 'admin', NOW(), 'admin', NOW(), ''),
('办公资产导出', '2100', '5', '', '', '', '0', '0', 'F', '0', '0', 'asset:office:export', '#', 'admin', NOW(), 'admin', NOW(), ''),
('办公资产导入', '2100', '5', '', '', '', '0', '0', 'F', '0', '0', 'asset:office:import', '#', 'admin', NOW(), 'admin', NOW(), '');

-- -----------------------------------------------------------------------------
-- Submenu: 维护工单 (Maintenance Work Orders)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('维护工单', '2100', '6', 'maintenance', 'asset/maintenance/index', '', '0', '0', 'C', '0', '0', 'asset:maint:list', 'edit', 'admin', NOW(), 'admin', NOW(), '维护工单管理');

-- Button permissions for 维护工单
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('维护工单查询', '2100', '6', '', '', '', '0', '0', 'F', '0', '0', 'asset:maint:query', '#', 'admin', NOW(), 'admin', NOW(), ''),
('维护工单新增', '2100', '6', '', '', '', '0', '0', 'F', '0', '0', 'asset:maint:add', '#', 'admin', NOW(), 'admin', NOW(), ''),
('维护工单修改', '2100', '6', '', '', '', '0', '0', 'F', '0', '0', 'asset:maint:edit', '#', 'admin', NOW(), 'admin', NOW(), ''),
('维护工单删除', '2100', '6', '', '', '', '0', '0', 'F', '0', '0', 'asset:maint:remove', '#', 'admin', NOW(), 'admin', NOW(), ''),
('维护工单导出', '2100', '6', '', '', '', '0', '0', 'F', '0', '0', 'asset:maint:export', '#', 'admin', NOW(), 'admin', NOW(), ''),
('维护工单处理', '2100', '6', '', '', '', '0', '0', 'F', '0', '0', 'asset:maint:process', '#', 'admin', NOW(), 'admin', NOW(), '');

-- -----------------------------------------------------------------------------
-- Parent Menu: 系统配置 (System Configuration)
-- Menu ID: 3 (assuming it's under existing system menu)
-- -----------------------------------------------------------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('系统配置', '0', '3', 'system', NULL, '', '1', '0', 'M', '0', '0', '', 'system', 'admin', NOW(), 'admin', NOW(), '系统配置管理');

-- Submenu: 位置管理 (Location Management)
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('位置管理', '3', '1', 'location', 'asset/location/index', '', '0', '0', 'C', '0', '0', 'asset:location:list', 'tree-table', 'admin', NOW(), 'admin', NOW(), '位置层级管理');

-- Button permissions for 位置管理
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES
('位置管理查询', '3', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:location:query', '#', 'admin', NOW(), 'admin', NOW(), ''),
('位置管理新增', '3', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:location:add', '#', 'admin', NOW(), 'admin', NOW(), ''),
('位置管理修改', '3', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:location:edit', '#', 'admin', NOW(), 'admin', NOW(), ''),
('位置管理删除', '3', '1', '', '', '', '0', '0', 'F', '0', '0', 'asset:location:remove', '#', 'admin', NOW(), 'admin', NOW(), '');

SET FOREIGN_KEY_CHECKS = 1;

-- =============================================================================
-- End of Asset Menu Configuration
-- =============================================================================
