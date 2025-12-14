import Vue from 'vue'
import VueRouter from 'vue-router'
import NProgress from 'nprogress'

// Layout
import Layout from '@/layout/index.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard/management',
    children: [
      // Dashboard routes
      {
        path: 'dashboard/management',
        name: 'ManagementDashboard',
        component: () => import('@/views/asset/dashboard/management.vue'),
        meta: { title: 'Management Dashboard', icon: 'el-icon-data-board' }
      },
      {
        path: 'dashboard/ops',
        name: 'OpsDashboard',
        component: () => import('@/views/asset/dashboard/ops.vue'),
        meta: { title: 'Operations Dashboard', icon: 'el-icon-s-operation' }
      },
      {
        path: 'dashboard/asset-manager',
        name: 'AssetManagerDashboard',
        component: () => import('@/views/asset/dashboard/assetManager.vue'),
        meta: { title: 'Asset Manager Dashboard', icon: 'el-icon-s-data' }
      },

      // Asset management routes
      {
        path: 'asset/house',
        name: 'HouseAsset',
        component: () => import('@/views/asset/house/index.vue'),
        meta: { title: 'House Assets', icon: 'el-icon-house' }
      },
      {
        path: 'asset/parking',
        name: 'ParkingAsset',
        component: () => import('@/views/asset/parking/index.vue'),
        meta: { title: 'Parking Assets', icon: 'el-icon-truck' }
      },
      {
        path: 'asset/parking/statistics',
        name: 'ParkingStatistics',
        component: () => import('@/views/asset/parking/statistics.vue'),
        meta: { title: 'Parking Statistics', icon: 'el-icon-s-data' }
      },
      {
        path: 'asset/venue',
        name: 'VenueAsset',
        component: () => import('@/views/asset/venue/index.vue'),
        meta: { title: 'Venue Assets', icon: 'el-icon-place' }
      },
      {
        path: 'asset/office',
        name: 'OfficeAsset',
        component: () => import('@/views/asset/office/index.vue'),
        meta: { title: 'Office Assets', icon: 'el-icon-office-building' }
      },
      {
        path: 'asset/facility',
        name: 'FacilityAsset',
        component: () => import('@/views/asset/facility/index.vue'),
        meta: { title: 'Facility Assets', icon: 'el-icon-setting' }
      },

      // Maintenance routes
      {
        path: 'asset/maint/orderList',
        name: 'MaintOrderList',
        component: () => import('@/views/asset/maint/orderList.vue'),
        meta: { title: 'Maintenance Orders', icon: 'el-icon-s-tools' }
      },
      {
        path: 'asset/maint/orderDetail/:id',
        name: 'MaintOrderDetail',
        component: () => import('@/views/asset/maint/orderDetail.vue'),
        meta: { title: 'Order Detail', icon: 'el-icon-document' }
      },
      {
        path: 'asset/maint/planList',
        name: 'MaintPlanList',
        component: () => import('@/views/asset/maint/planList.vue'),
        meta: { title: 'Maintenance Plans', icon: 'el-icon-date' }
      }
    ]
  },

  // 404 route
  {
    path: '*',
    component: () => import('@/views/error/404.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: '/',
  routes,
  scrollBehavior() {
    return { x: 0, y: 0 }
  }
})

// Navigation guards
router.beforeEach((to, from, next) => {
  NProgress.start()
  document.title = to.meta.title ? `${to.meta.title} - Asset Management` : 'Asset Management'
  next()
})

router.afterEach(() => {
  NProgress.done()
})

export default router
