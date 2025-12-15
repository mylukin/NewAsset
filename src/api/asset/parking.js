import request from '@/utils/request'

// Parking Asset API

/**
 * Get parking asset list
 * @param query Query parameters
 * @returns {*} Parking asset list
 */
export function listParking(query) {
  return request({
    url: '/asset/parking/list',
    method: 'get',
    params: query
  })
}

/**
 * Get parking asset detail
 * @param id Asset ID
 * @returns {*} Parking asset detail
 */
export function getParking(id) {
  return request({
    url: `/asset/parking/${id}`,
    method: 'get'
  })
}

/**
 * Add parking asset
 * @param data Asset data
 * @returns {*} Result
 */
export function addParking(data) {
  return request({
    url: '/asset/parking',
    method: 'post',
    data: data
  })
}

/**
 * Update parking asset
 * @param data Asset data
 * @returns {*} Result
 */
export function updateParking(data) {
  return request({
    url: '/asset/parking',
    method: 'put',
    data: data
  })
}

/**
 * Delete parking asset
 * @param ids Asset IDs (comma-separated)
 * @returns {*} Result
 */
export function delParking(ids) {
  return request({
    url: `/asset/parking/${ids}`,
    method: 'delete'
  })
}

/**
 * Export parking asset list
 * @param query Query parameters
 * @returns {*} Result
 */
export function exportParking(query) {
  return request({
    url: '/asset/parking/export',
    method: 'post',
    params: query
  })
}

/**
 * Get parking asset statistics
 * @param projectId Project ID
 * @returns {*} Statistics
 */
export function getParkingStats(projectId) {
  return request({
    url: '/asset/parking/stats',
    method: 'get',
    params: { projectId }
  })
}

export default {
  listParking,
  getParking,
  addParking,
  updateParking,
  delParking,
  exportParking,
  getParkingStats
}
