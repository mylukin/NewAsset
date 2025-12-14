import request from '@/utils/request'

/**
 * Query house asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function getHouseAssetList(query) {
  return request({
    url: '/asset/house/list',
    method: 'get',
    params: query
  })
}

/**
 * Get house asset detail by ID
 * @param {number} id - Asset ID
 * @returns {Promise}
 */
export function getHouseAsset(id) {
  return request({
    url: '/asset/house/' + id,
    method: 'get'
  })
}

/**
 * Add new house asset
 * @param {Object} data - House asset data
 * @returns {Promise}
 */
export function addHouseAsset(data) {
  return request({
    url: '/asset/house',
    method: 'post',
    data: data
  })
}

/**
 * Update house asset
 * @param {Object} data - House asset data
 * @returns {Promise}
 */
export function updateHouseAsset(data) {
  return request({
    url: '/asset/house',
    method: 'put',
    data: data
  })
}

/**
 * Delete house asset(s)
 * @param {number|string} id - Asset ID(s), comma separated for batch delete
 * @returns {Promise}
 */
export function deleteHouseAsset(id) {
  return request({
    url: '/asset/house/' + id,
    method: 'delete'
  })
}

/**
 * Change house asset status
 * @param {number} id - Asset ID
 * @param {string} status - New status code
 * @param {string} reason - Reason for change (optional)
 * @returns {Promise}
 */
export function changeHouseAssetStatus(id, status, reason) {
  return request({
    url: '/asset/house/status/' + id,
    method: 'put',
    params: { status, reason }
  })
}

/**
 * Export house asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function exportHouseAsset(query) {
  return request({
    url: '/asset/house/export',
    method: 'post',
    params: query
  })
}

/**
 * Get house asset statistics
 * @param {number} projectId - Project ID filter (optional)
 * @returns {Promise}
 */
export function getHouseStatistics(projectId) {
  return request({
    url: '/asset/house/statistics',
    method: 'get',
    params: { projectId }
  })
}
