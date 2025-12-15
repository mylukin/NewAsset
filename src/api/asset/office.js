import request from '@/utils/request'

// Office Asset API

export function listOffice(query) {
  return request({
    url: '/asset/office/list',
    method: 'get',
    params: query
  })
}

export function getOffice(id) {
  return request({
    url: `/asset/office/${id}`,
    method: 'get'
  })
}

export function addOffice(data) {
  return request({
    url: '/asset/office',
    method: 'post',
    data: data
  })
}

export function updateOffice(data) {
  return request({
    url: '/asset/office',
    method: 'put',
    data: data
  })
}

export function delOffice(ids) {
  return request({
    url: `/asset/office/${ids}`,
    method: 'delete'
  })
}

export function exportOffice(query) {
  return request({
    url: '/asset/office/export',
    method: 'post',
    params: query
  })
}

export default {
  listOffice,
  getOffice,
  addOffice,
  updateOffice,
  delOffice,
  exportOffice
}
