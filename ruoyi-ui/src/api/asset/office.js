import request from '@/utils/request'

/**
 * Query office asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function getOfficeAssetList(query) {
  return request({
    url: '/asset/office/list',
    method: 'get',
    params: query
  })
}

/**
 * Get office asset detail by ID
 * @param {number} id - Asset ID
 * @returns {Promise}
 */
export function getOfficeAsset(id) {
  return request({
    url: '/asset/office/' + id,
    method: 'get'
  })
}

/**
 * Add new office asset
 * @param {Object} data - Office asset data
 * @returns {Promise}
 */
export function addOfficeAsset(data) {
  return request({
    url: '/asset/office',
    method: 'post',
    data: data
  })
}

/**
 * Update office asset
 * @param {Object} data - Office asset data
 * @returns {Promise}
 */
export function updateOfficeAsset(data) {
  return request({
    url: '/asset/office',
    method: 'put',
    data: data
  })
}

/**
 * Delete office asset(s)
 * @param {number|string} id - Asset ID(s), comma separated for batch delete
 * @returns {Promise}
 */
export function deleteOfficeAsset(id) {
  return request({
    url: '/asset/office/' + id,
    method: 'delete'
  })
}

/**
 * Change office asset status
 * @param {number} id - Asset ID
 * @param {string} status - New status code
 * @param {string} reason - Reason for change (optional)
 * @returns {Promise}
 */
export function changeOfficeAssetStatus(id, status, reason) {
  return request({
    url: '/asset/office/status/' + id,
    method: 'put',
    params: { status, reason }
  })
}

/**
 * Export office asset list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function exportOfficeAsset(query) {
  return request({
    url: '/asset/office/export',
    method: 'post',
    params: query
  })
}
