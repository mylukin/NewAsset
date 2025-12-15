<template>
  <el-container class="layout-container">
    <el-aside :width="isCollapse ? '64px' : '256px'" class="sidebar">
      <div class="logo">
        <img src="/logo.png" alt="Logo" v-if="!isCollapse" />
        <img src="/favicon.ico" alt="Logo" v-else />
      </div>
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :unique-opened="true"
        router
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
      >
        <el-menu-item index="/dashboard">
          <i class="el-icon-s-home"></i>
          <span slot="title">Dashboard</span>
        </el-menu-item>

        <el-submenu index="asset">
          <template slot="title">
            <i class="el-icon-box"></i>
            <span slot="title">Asset Management</span>
          </template>
          <el-menu-item index="/asset/house">
            <i class="el-icon-office-building"></i>
            <span slot="title">House Assets</span>
          </el-menu-item>
          <el-menu-item index="/asset/parking">
            <i class="el-icon-truck"></i>
            <span slot="title">Parking Assets</span>
          </el-menu-item>
          <el-menu-item index="/asset/facility">
            <i class="el-icon-box"></i>
            <span slot="title">Facility Assets</span>
          </el-menu-item>
          <el-menu-item index="/asset/venue">
            <i class="el-icon-soccer-field"></i>
            <span slot="title">Venue Assets</span>
          </el-menu-item>
          <el-menu-item index="/asset/office">
            <i class="el-icon-monitor"></i>
            <span slot="title">Office Assets</span>
          </el-menu-item>
        </el-submenu>

        <el-menu-item index="/maintenance">
          <i class="el-icon-tools"></i>
          <span slot="title">Maintenance</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header class="header">
        <div class="header-left">
          <el-button
            @click="toggleCollapse"
            type="text"
            size="medium"
            icon="el-icon-s-unfold"
            v-if="!isCollapse"
          ></el-button>
          <el-button
            @click="toggleCollapse"
            type="text"
            size="medium"
            icon="el-icon-s-fold"
            v-else
          ></el-button>
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="el-dropdown-link">
              {{ userName || 'User' }}
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>Profile</el-dropdown-item>
              <el-dropdown-item divided @click="logout">Logout</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>

      <el-main class="main-content">
        <router-view />
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
    };
  },
  computed: {
    activeMenu() {
      return this.$route.path;
    },
    userName() {
      return this.$store.getters.user?.username || 'User';
    }
  },
  methods: {
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    },
    logout() {
      this.$store.dispatch('logout');
      this.$router.push('/login');
    }
  }
};
</script>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;
}

.sidebar {
  background-color: #304156;
  overflow-x: hidden;
  transition: width 0.3s;

  .logo {
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #2b2f3a;

    img {
      height: 32px;
    }
  }
}

.header {
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;

  .header-left {
    display: flex;
    align-items: center;
  }

  .header-right {
    .el-dropdown-link {
      cursor: pointer;
      color: #606266;
    }
  }
}

.main-content {
  background-color: #f0f2f5;
  padding: 20px;
  overflow-y: auto;
}
</style>
