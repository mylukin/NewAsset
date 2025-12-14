import request from '@/utils/request'

/**
 * Query venue asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function getVenueAssetList(query) {
  return request({
    url: '/asset/venue/list',
    method: 'get',
    params: query
  })
}

/**
 * Get venue asset detail by ID
 * @param {number} id - Asset ID
 * @returns {Promise}
 */
export function getVenueAsset(id) {
  return request({
    url: '/asset/venue/' + id,
    method: 'get'
  })
}

/**
 * Add new venue asset
 * @param {Object} data - Venue asset data
 * @returns {Promise}
 */
export function addVenueAsset(data) {
  return request({
    url: '/asset/venue',
    method: 'post',
    data: data
  })
}

/**
 * Update venue asset
 * @param {Object} data - Venue asset data
 * @returns {Promise}
 */
export function updateVenueAsset(data) {
  return request({
    url: '/asset/venue',
    method: 'put',
    data: data
  })
}

/**
 * Delete venue asset(s)
 * @param {number|string} id - Asset ID(s), comma separated for batch delete
 * @returns {Promise}
 */
export function deleteVenueAsset(id) {
  return request({
    url: '/asset/venue/' + id,
    method: 'delete'
  })
}

/**
 * Change venue asset status
 * @param {number} id - Asset ID
 * @param {string} status - New status code
 * @param {string} reason - Reason for change (optional)
 * @returns {Promise}
 */
export function changeVenueAssetStatus(id, status, reason) {
  return request({
    url: '/asset/venue/status/' + id,
    method: 'put',
    params: { status, reason }
  })
}

/**
 * Export venue asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function exportVenueAsset(query) {
  return request({
    url: '/asset/venue/export',
    method: 'post',
    params: query
  })
}

/**
 * Get venue asset statistics
 * @param {number} projectId - Project ID filter (optional)
 * @param {string} venueType - Venue type filter (optional)
 * @returns {Promise}
 */
export function getVenueStatistics(projectId, venueType) {
  return request({
    url: '/asset/venue/statistics',
    method: 'get',
    params: { projectId, venueType }
  })
}

// ==================== Venue Usage API ====================

/**
 * Query venue usage list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function getVenueUsageList(query) {
  return request({
    url: '/asset/venue/usage/list',
    method: 'get',
    params: query
  })
}

/**
 * Get venue usage detail by ID
 * @param {number} id - Usage record ID
 * @returns {Promise}
 */
export function getVenueUsage(id) {
  return request({
    url: '/asset/venue/usage/' + id,
    method: 'get'
  })
}

/**
 * Add new venue usage record
 * @param {Object} data - Venue usage data
 * @returns {Promise}
 */
export function addVenueUsage(data) {
  return request({
    url: '/asset/venue/usage',
    method: 'post',
    data: data
  })
}

/**
 * Update venue usage record
 * @param {Object} data - Venue usage data
 * @returns {Promise}
 */
export function updateVenueUsage(data) {
  return request({
    url: '/asset/venue/usage',
    method: 'put',
    data: data
  })
}

/**
 * Delete venue usage record(s)
 * @param {number|string} id - Usage record ID(s), comma separated for batch delete
 * @returns {Promise}
 */
export function deleteVenueUsage(id) {
  return request({
    url: '/asset/venue/usage/' + id,
    method: 'delete'
  })
}

/**
 * Get venue usage statistics
 * @param {Object} params - Filter parameters (venueId, startDate, endDate)
 * @returns {Promise}
 */
export function getVenueUsageStatistics(params) {
  return request({
    url: '/asset/venue/usage/statistics',
    method: 'get',
    params: params
  })
}
