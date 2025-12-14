import request from '@/utils/request'

// ==================== CRUD Functions ====================

/**
 * Query maintenance plan list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function getMaintPlanList(query) {
  return request({
    url: '/asset/maint/plan/list',
    method: 'get',
    params: query
  })
}

/**
 * Get maintenance plan detail by ID
 * @param {number} id - Plan ID
 * @returns {Promise}
 */
export function getMaintPlan(id) {
  return request({
    url: '/asset/maint/plan/' + id,
    method: 'get'
  })
}

/**
 * Create new maintenance plan
 * @param {Object} data - Plan data with assetIds
 * @returns {Promise}
 */
export function addMaintPlan(data) {
  return request({
    url: '/asset/maint/plan',
    method: 'post',
    data: data
  })
}

/**
 * Update maintenance plan
 * @param {Object} data - Plan data with assetIds
 * @returns {Promise}
 */
export function updateMaintPlan(data) {
  return request({
    url: '/asset/maint/plan',
    method: 'put',
    data: data
  })
}

/**
 * Delete maintenance plan(s)
 * @param {number|string} id - Plan ID(s), comma separated for batch delete
 * @returns {Promise}
 */
export function deleteMaintPlan(id) {
  return request({
    url: '/asset/maint/plan/' + id,
    method: 'delete'
  })
}

// ==================== Status Functions ====================

/**
 * Enable maintenance plan
 * @param {number} id - Plan ID
 * @returns {Promise}
 */
export function enableMaintPlan(id) {
  return request({
    url: '/asset/maint/plan/enable/' + id,
    method: 'put'
  })
}

/**
 * Disable maintenance plan
 * @param {number} id - Plan ID
 * @returns {Promise}
 */
export function disableMaintPlan(id) {
  return request({
    url: '/asset/maint/plan/disable/' + id,
    method: 'put'
  })
}

/**
 * Manually execute scheduled plans (admin only)
 * @returns {Promise}
 */
export function executeScheduledPlans() {
  return request({
    url: '/asset/maint/plan/execute',
    method: 'post'
  })
}

/**
 * Export maintenance plan list
 * @param {Object} query - Query parameters
 * @returns {Promise}
 */
export function exportMaintPlan(query) {
  return request({
    url: '/asset/maint/plan/export',
    method: 'post',
    params: query
  })
}
