import request from '@/utils/request'

/**
 * Query facility asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function getFacilityAssetList(query) {
  return request({
    url: '/asset/facility/list',
    method: 'get',
    params: query
  })
}

/**
 * Get facility asset detail by ID
 * @param {number} id - Asset ID
 * @returns {Promise}
 */
export function getFacilityAsset(id) {
  return request({
    url: '/asset/facility/' + id,
    method: 'get'
  })
}

/**
 * Add new facility asset
 * @param {Object} data - Facility asset data
 * @returns {Promise}
 */
export function addFacilityAsset(data) {
  return request({
    url: '/asset/facility',
    method: 'post',
    data: data
  })
}

/**
 * Update facility asset
 * @param {Object} data - Facility asset data
 * @returns {Promise}
 */
export function updateFacilityAsset(data) {
  return request({
    url: '/asset/facility',
    method: 'put',
    data: data
  })
}

/**
 * Delete facility asset(s)
 * @param {number|string} id - Asset ID(s), comma separated for batch delete
 * @returns {Promise}
 */
export function deleteFacilityAsset(id) {
  return request({
    url: '/asset/facility/' + id,
    method: 'delete'
  })
}

/**
 * Change facility asset status
 * @param {number} id - Asset ID
 * @param {string} status - New status code
 * @param {string} reason - Reason for change (optional)
 * @returns {Promise}
 */
export function changeFacilityAssetStatus(id, status, reason) {
  return request({
    url: '/asset/facility/status/' + id,
    method: 'put',
    params: { status, reason }
  })
}

/**
 * Export facility asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function exportFacilityAsset(query) {
  return request({
    url: '/asset/facility/export',
    method: 'post',
    params: query
  })
}
