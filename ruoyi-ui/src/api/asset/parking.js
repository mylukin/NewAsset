import request from '@/utils/request'

/**
 * Query parking asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function getParkingAssetList(query) {
  return request({
    url: '/asset/parking/list',
    method: 'get',
    params: query
  })
}

/**
 * Get parking asset detail by ID
 * @param {number} id - Asset ID
 * @returns {Promise}
 */
export function getParkingAsset(id) {
  return request({
    url: '/asset/parking/' + id,
    method: 'get'
  })
}

/**
 * Add new parking asset
 * @param {Object} data - Parking asset data
 * @returns {Promise}
 */
export function addParkingAsset(data) {
  return request({
    url: '/asset/parking',
    method: 'post',
    data: data
  })
}

/**
 * Update parking asset
 * @param {Object} data - Parking asset data
 * @returns {Promise}
 */
export function updateParkingAsset(data) {
  return request({
    url: '/asset/parking',
    method: 'put',
    data: data
  })
}

/**
 * Delete parking asset(s)
 * @param {number|string} id - Asset ID(s), comma separated for batch delete
 * @returns {Promise}
 */
export function deleteParkingAsset(id) {
  return request({
    url: '/asset/parking/' + id,
    method: 'delete'
  })
}

/**
 * Change parking asset status
 * @param {number} id - Asset ID
 * @param {string} status - New status code
 * @param {string} reason - Reason for change (optional)
 * @returns {Promise}
 */
export function changeParkingAssetStatus(id, status, reason) {
  return request({
    url: '/asset/parking/status/' + id,
    method: 'put',
    params: { status, reason }
  })
}

/**
 * Export parking asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function exportParkingAsset(query) {
  return request({
    url: '/asset/parking/export',
    method: 'post',
    params: query
  })
}

/**
 * Get parking asset statistics
 * @param {number} projectId - Project ID filter (optional)
 * @param {string} parkingZone - Parking zone filter (optional)
 * @returns {Promise}
 */
export function getParkingStatistics(projectId, parkingZone) {
  return request({
    url: '/asset/parking/statistics',
    method: 'get',
    params: { projectId, parkingZone }
  })
}
