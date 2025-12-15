import request from '@/utils/request'

// House Asset API

/**
 * Get house asset list
 * @param query Query parameters
 * @returns {*} House asset list
 */
export function listHouse(query) {
  return request({
    url: '/asset/house/list',
    method: 'get',
    params: query
  })
}

/**
 * Get house asset detail
 * @param id Asset ID
 * @returns {*} House asset detail
 */
export function getHouse(id) {
  return request({
    url: `/asset/house/${id}`,
    method: 'get'
  })
}

/**
 * Add house asset
 * @param data Asset data
 * @returns {*} Result
 */
export function addHouse(data) {
  return request({
    url: '/asset/house',
    method: 'post',
    data: data
  })
}

/**
 * Update house asset
 * @param data Asset data
 * @returns {*} Result
 */
export function updateHouse(data) {
  return request({
    url: '/asset/house',
    method: 'put',
    data: data
  })
}

/**
 * Delete house asset
 * @param ids Asset IDs (comma-separated)
 * @returns {*} Result
 */
export function delHouse(ids) {
  return request({
    url: `/asset/house/${ids}`,
    method: 'delete'
  })
}

/**
 * Copy house asset
 * @param id Asset ID to copy
 * @returns {*} Copied asset
 */
export function copyHouse(id) {
  return request({
    url: `/asset/house/copy/${id}`,
    method: 'post'
  })
}

/**
 * Export house asset list
 * @param query Query parameters
 * @returns {*} Result
 */
export function exportHouse(query) {
  return request({
    url: '/asset/house/export',
    method: 'post',
    params: query
  })
}

/**
 * Get house asset statistics
 * @param projectId Project ID
 * @returns {*} Statistics
 */
export function getHouseStats(projectId) {
  return request({
    url: '/asset/house/stats',
    method: 'get',
    params: { projectId }
  })
}

export default {
  listHouse,
  getHouse,
  addHouse,
  updateHouse,
  delHouse,
  copyHouse,
  exportHouse,
  getHouseStats
}
