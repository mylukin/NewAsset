import request from '@/utils/request'

// Maintenance Order API

export function listOrder(query) {
  return request({
    url: '/asset/maint/order/list',
    method: 'get',
    params: query
  })
}

export function getOrder(id) {
  return request({
    url: `/asset/maint/order/${id}`,
    method: 'get'
  })
}

export function addOrder(data) {
  return request({
    url: '/asset/maint/order',
    method: 'post',
    data: data
  })
}

export function updateOrder(data) {
  return request({
    url: '/asset/maint/order',
    method: 'put',
    data: data
  })
}

export function delOrder(ids) {
  return request({
    url: `/asset/maint/order/${ids}`,
    method: 'delete'
  })
}

export function assignOrder(id, data) {
  return request({
    url: `/asset/maint/order/${id}/assign`,
    method: 'post',
    data: data
  })
}

export function startOrder(id) {
  return request({
    url: `/asset/maint/order/${id}/start`,
    method: 'post'
  })
}

export function completeOrder(id, data) {
  return request({
    url: `/asset/maint/order/${id}/complete`,
    method: 'post',
    data: data
  })
}

export function closeOrder(id) {
  return request({
    url: `/asset/maint/order/${id}/close`,
    method: 'post'
  })
}

export function cancelOrder(id, reason) {
  return request({
    url: `/asset/maint/order/${id}/cancel`,
    method: 'post',
    data: { reason }
  })
}

export function exportOrder(query) {
  return request({
    url: '/asset/maint/order/export',
    method: 'post',
    params: query
  })
}

export default {
  listOrder,
  getOrder,
  addOrder,
  updateOrder,
  delOrder,
  assignOrder,
  startOrder,
  completeOrder,
  closeOrder,
  cancelOrder,
  exportOrder
}
