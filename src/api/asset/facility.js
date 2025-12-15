import request from '@/utils/request'

// Facility Asset API

export function listFacility(query) {
  return request({
    url: '/asset/facility/list',
    method: 'get',
    params: query
  })
}

export function getFacility(id) {
  return request({
    url: `/asset/facility/${id}`,
    method: 'get'
  })
}

export function addFacility(data) {
  return request({
    url: '/asset/facility',
    method: 'post',
    data: data
  })
}

export function updateFacility(data) {
  return request({
    url: '/asset/facility',
    method: 'put',
    data: data
  })
}

export function delFacility(ids) {
  return request({
    url: `/asset/facility/${ids}`,
    method: 'delete'
  })
}

export function exportFacility(query) {
  return request({
    url: '/asset/facility/export',
    method: 'post',
    params: query
  })
}

export function getWarrantyAlerts(projectId) {
  return request({
    url: '/asset/facility/warranty-alerts',
    method: 'get',
    params: { projectId }
  })
}

export default {
  listFacility,
  getFacility,
  addFacility,
  updateFacility,
  delFacility,
  exportFacility,
  getWarrantyAlerts
}
