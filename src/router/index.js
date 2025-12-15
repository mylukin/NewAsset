import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: () => import('@/views/Layout.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/Dashboard.vue'),
        meta: { title: 'Dashboard', icon: 'dashboard' }
      },
      {
        path: 'asset/house',
        name: 'AssetHouse',
        component: () => import('@/views/asset/house/index.vue'),
        meta: { title: 'House Assets', icon: 'el-icon-office-building' }
      },
      {
        path: 'asset/parking',
        name: 'AssetParking',
        component: () => import('@/views/asset/parking/index.vue'),
        meta: { title: 'Parking Assets', icon: 'el-icon-truck' }
      },
      {
        path: 'asset/facility',
        name: 'AssetFacility',
        component: () => import('@/views/asset/facility/index.vue'),
        meta: { title: 'Facility Assets', icon: 'el-icon-box' }
      },
      {
        path: 'asset/venue',
        name: 'AssetVenue',
        component: () => import('@/views/asset/venue/index.vue'),
        meta: { title: 'Venue Assets', icon: 'el-icon-soccer-field' }
      },
      {
        path: 'asset/office',
        name: 'AssetOffice',
        component: () => import('@/views/asset/office/index.vue'),
        meta: { title: 'Office Assets', icon: 'el-icon-monitor' }
      },
      {
        path: 'maintenance',
        name: 'Maintenance',
        component: () => import('@/views/maintenance/index.vue'),
        meta: { title: 'Maintenance', icon: 'el-icon-tools' }
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  }
];

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
});

export default router;
