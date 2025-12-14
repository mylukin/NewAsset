import request from '@/utils/request'

// ==================== CRUD Functions ====================

/**
 * Query maintenance order list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function getMaintOrderList(query) {
  return request({
    url: '/asset/maint/order/list',
    method: 'get',
    params: query
  })
}

/**
 * Get maintenance order detail by ID
 * @param {number} id - Order ID
 * @returns {Promise}
 */
export function getMaintOrder(id) {
  return request({
    url: '/asset/maint/order/' + id,
    method: 'get'
  })
}

/**
 * Create new maintenance order
 * @param {Object} data - Order data
 * @returns {Promise}
 */
export function addMaintOrder(data) {
  return request({
    url: '/asset/maint/order',
    method: 'post',
    data: data
  })
}

/**
 * Delete maintenance order(s)
 * @param {number|string} id - Order ID(s), comma separated for batch delete
 * @returns {Promise}
 */
export function deleteMaintOrder(id) {
  return request({
    url: '/asset/maint/order/' + id,
    method: 'delete'
  })
}

// ==================== Workflow Functions ====================

/**
 * Assign order to handler
 * @param {number} id - Order ID
 * @param {number} handlerId - Handler user ID
 * @param {string} remark - Remark (optional)
 * @returns {Promise}
 */
export function assignMaintOrder(id, handlerId, remark) {
  return request({
    url: '/asset/maint/order/assign/' + id,
    method: 'put',
    params: { handlerId, remark }
  })
}

/**
 * Accept an assigned order
 * @param {number} id - Order ID
 * @param {string} remark - Remark (optional)
 * @returns {Promise}
 */
export function acceptMaintOrder(id, remark) {
  return request({
    url: '/asset/maint/order/accept/' + id,
    method: 'put',
    params: { remark }
  })
}

/**
 * Reject an assigned order
 * @param {number} id - Order ID
 * @param {string} reason - Rejection reason
 * @returns {Promise}
 */
export function rejectMaintOrder(id, reason) {
  return request({
    url: '/asset/maint/order/reject/' + id,
    method: 'put',
    params: { reason }
  })
}

/**
 * Complete maintenance work
 * @param {number} id - Order ID
 * @param {string} result - Completion result (optional)
 * @returns {Promise}
 */
export function completeMaintOrder(id, result) {
  return request({
    url: '/asset/maint/order/complete/' + id,
    method: 'put',
    params: { result }
  })
}

/**
 * Confirm and close order
 * @param {number} id - Order ID
 * @param {string} feedback - Feedback (optional)
 * @returns {Promise}
 */
export function confirmMaintOrder(id, feedback) {
  return request({
    url: '/asset/maint/order/confirm/' + id,
    method: 'put',
    params: { feedback }
  })
}

/**
 * Cancel order
 * @param {number} id - Order ID
 * @param {string} reason - Cancellation reason
 * @returns {Promise}
 */
export function cancelMaintOrder(id, reason) {
  return request({
    url: '/asset/maint/order/cancel/' + id,
    method: 'put',
    params: { reason }
  })
}

// ==================== Query Functions ====================

/**
 * Count orders by status
 * @param {number} projectId - Project ID (optional)
 * @param {string} status - Status code
 * @returns {Promise}
 */
export function countMaintOrderByStatus(projectId, status) {
  return request({
    url: '/asset/maint/order/count',
    method: 'get',
    params: { projectId, status }
  })
}

/**
 * Get orders for a specific asset
 * @param {number} assetId - Asset ID
 * @returns {Promise}
 */
export function getMaintOrdersByAssetId(assetId) {
  return request({
    url: '/asset/maint/order/asset/' + assetId,
    method: 'get'
  })
}

/**
 * Export maintenance order list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function exportMaintOrder(query) {
  return request({
    url: '/asset/maint/order/export',
    method: 'post',
    params: query
  })
}
