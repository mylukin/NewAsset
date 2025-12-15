import request from '@/utils/request'

// Dashboard API

export function getDashboardStats() {
  return request({
    url: '/asset/dashboard/stats',
    method: 'get'
  })
}

export function getAssetDistribution() {
  return request({
    url: '/asset/dashboard/distribution',
    method: 'get'
  })
}

export function getRecentActivities(params) {
  return request({
    url: '/asset/dashboard/activities',
    method: 'get',
    params: params
  })
}

export function getAssetTrend(params) {
  return request({
    url: '/asset/dashboard/trend',
    method: 'get',
    params: params
  })
}

export function getMaintenanceStats() {
  return request({
    url: '/asset/dashboard/maintenance',
    method: 'get'
  })
}

export default {
  getDashboardStats,
  getAssetDistribution,
  getRecentActivities,
  getAssetTrend,
  getMaintenanceStats
}
