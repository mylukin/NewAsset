import request from '@/utils/request'

// ==================== Dashboard API Functions ====================

/**
 * Get management dashboard data
 * High-level KPIs for executives and managers
 * @param {number} projectId - Optional project filter
 * @returns {Promise}
 */
export function getManagementDashboard(projectId) {
  return request({
    url: '/asset/dashboard/management',
    method: 'get',
    params: { projectId }
  })
}

/**
 * Get operations dashboard data
 * Operational metrics for ops staff
 * @param {number} projectId - Optional project filter
 * @returns {Promise}
 */
export function getOpsDashboard(projectId) {
  return request({
    url: '/asset/dashboard/ops',
    method: 'get',
    params: { projectId }
  })
}

/**
 * Get asset manager dashboard data
 * Portfolio data for asset managers
 * @param {number} projectId - Optional project filter
 * @returns {Promise}
 */
export function getAssetManagerDashboard(projectId) {
  return request({
    url: '/asset/dashboard/manager',
    method: 'get',
    params: { projectId }
  })
}

/**
 * Get statistics for a specific asset type
 * @param {string} assetType - Asset type code (HOUSE, PARKING, VENUE, OFFICE, FACILITY)
 * @param {number} projectId - Optional project filter
 * @returns {Promise}
 */
export function getAssetTypeStats(assetType, projectId) {
  return request({
    url: '/asset/dashboard/type/' + assetType,
    method: 'get',
    params: { projectId }
  })
}

/**
 * Get quick dashboard summary
 * Optimized for dashboard widgets
 * @param {number} projectId - Optional project filter
 * @returns {Promise}
 */
export function getDashboardSummary(projectId) {
  return request({
    url: '/asset/dashboard/summary',
    method: 'get',
    params: { projectId }
  })
}

/**
 * Get house statistics
 * @param {number} projectId - Optional project filter
 * @returns {Promise}
 */
export function getHouseStats(projectId) {
  return request({
    url: '/asset/dashboard/house',
    method: 'get',
    params: { projectId }
  })
}

/**
 * Get parking statistics
 * @param {number} projectId - Optional project filter
 * @returns {Promise}
 */
export function getParkingStats(projectId) {
  return request({
    url: '/asset/dashboard/parking',
    method: 'get',
    params: { projectId }
  })
}

/**
 * Get venue statistics
 * @param {number} projectId - Optional project filter
 * @returns {Promise}
 */
export function getVenueStats(projectId) {
  return request({
    url: '/asset/dashboard/venue',
    method: 'get',
    params: { projectId }
  })
}

/**
 * Get office statistics
 * @param {number} projectId - Optional project filter
 * @returns {Promise}
 */
export function getOfficeStats(projectId) {
  return request({
    url: '/asset/dashboard/office',
    method: 'get',
    params: { projectId }
  })
}
