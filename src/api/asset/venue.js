import request from '@/utils/request'

// Venue Asset API

export function listVenue(query) {
  return request({
    url: '/asset/venue/list',
    method: 'get',
    params: query
  })
}

export function getVenue(id) {
  return request({
    url: `/asset/venue/${id}`,
    method: 'get'
  })
}

export function addVenue(data) {
  return request({
    url: '/asset/venue',
    method: 'post',
    data: data
  })
}

export function updateVenue(data) {
  return request({
    url: '/asset/venue',
    method: 'put',
    data: data
  })
}

export function delVenue(ids) {
  return request({
    url: `/asset/venue/${ids}`,
    method: 'delete'
  })
}

export function exportVenue(query) {
  return request({
    url: '/asset/venue/export',
    method: 'post',
    params: query
  })
}

export default {
  listVenue,
  getVenue,
  addVenue,
  updateVenue,
  delVenue,
  exportVenue
}
