<template>
  <el-container class="layout-container">
    <!-- Sidebar -->
    <el-aside :width="isCollapse ? '64px' : '220px'" class="layout-aside">
      <div class="logo">
        <i class="el-icon-s-platform" style="font-size: 24px; color: #409EFF;"></i>
        <span v-show="!isCollapse">Asset Management</span>
      </div>
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :collapse-transition="false"
        router
        class="sidebar-menu"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
      >
        <el-submenu index="dashboard">
          <template slot="title">
            <i class="el-icon-s-home"></i>
            <span>Dashboards</span>
          </template>
          <el-menu-item index="/dashboard/management">Management</el-menu-item>
          <el-menu-item index="/dashboard/ops">Operations</el-menu-item>
          <el-menu-item index="/dashboard/asset-manager">Asset Manager</el-menu-item>
        </el-submenu>

        <el-submenu index="asset">
          <template slot="title">
            <i class="el-icon-office-building"></i>
            <span>Assets</span>
          </template>
          <el-menu-item index="/asset/house">Houses</el-menu-item>
          <el-menu-item index="/asset/parking">Parking</el-menu-item>
          <el-menu-item index="/asset/venue">Venues</el-menu-item>
          <el-menu-item index="/asset/office">Offices</el-menu-item>
          <el-menu-item index="/asset/facility">Facilities</el-menu-item>
        </el-submenu>

        <el-submenu index="maint">
          <template slot="title">
            <i class="el-icon-s-tools"></i>
            <span>Maintenance</span>
          </template>
          <el-menu-item index="/asset/maint/orderList">Orders</el-menu-item>
          <el-menu-item index="/asset/maint/planList">Plans</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>

    <!-- Main Content -->
    <el-container class="main-container">
      <el-header class="layout-header">
        <div class="header-left">
          <i
            :class="isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'"
            class="collapse-btn"
            @click="toggleCollapse"
          ></i>
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">Home</el-breadcrumb-item>
            <el-breadcrumb-item v-if="$route.meta.title">
              {{ $route.meta.title }}
            </el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="user-dropdown">
              <el-avatar :size="32" icon="el-icon-user-solid"></el-avatar>
              <span>Admin</span>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>Profile</el-dropdown-item>
              <el-dropdown-item divided>Logout</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>

      <el-main class="layout-main">
        <transition name="fade" mode="out-in">
          <router-view :key="$route.path" />
        </transition>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'Layout',
  data() {
    return {
      isCollapse: false
    }
  },
  computed: {
    activeMenu() {
      return this.$route.path
    }
  },
  methods: {
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    }
  }
}
</script>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;
}

.layout-aside {
  background: #304156;
  transition: width 0.3s;
  overflow: hidden;

  .logo {
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 0 16px;
    color: #fff;
    background: #2b3a4a;

    span {
      margin-left: 12px;
      font-size: 16px;
      font-weight: 600;
      white-space: nowrap;
    }
  }

  .sidebar-menu {
    border: none;
    height: calc(100vh - 60px);
    overflow-y: auto;
  }
}

.main-container {
  display: flex;
  flex-direction: column;
}

.layout-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #fff;
  border-bottom: 1px solid #e6e6e6;
  padding: 0 20px;
  height: 60px;

  .header-left {
    display: flex;
    align-items: center;

    .collapse-btn {
      font-size: 20px;
      cursor: pointer;
      margin-right: 20px;
      color: #666;

      &:hover {
        color: #409EFF;
      }
    }
  }

  .user-dropdown {
    display: flex;
    align-items: center;
    cursor: pointer;

    span {
      margin-left: 8px;
      color: #333;
    }
  }
}

.layout-main {
  background: #f0f2f5;
  padding: 20px;
  overflow-y: auto;
  flex: 1;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
