<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="dashboard-card">
          <div class="card-content">
            <div class="card-icon" style="background: #409EFF;">
              <i class="el-icon-office-building"></i>
            </div>
            <div class="card-info">
              <h3>Total Assets</h3>
              <p class="card-number">{{ stats.totalAssets || 0 }}</p>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card class="dashboard-card">
          <div class="card-content">
            <div class="card-icon" style="background: #67C23A;">
              <i class="el-icon-check"></i>
            </div>
            <div class="card-info">
              <h3>In Use</h3>
              <p class="card-number">{{ stats.inUse || 0 }}</p>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card class="dashboard-card">
          <div class="card-content">
            <div class="card-icon" style="background: #E6A23C;">
              <i class="el-icon-tools"></i>
            </div>
            <div class="card-info">
              <h3>Maintenance</h3>
              <p class="card-number">{{ stats.maintenance || 0 }}</p>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card class="dashboard-card">
          <div class="card-content">
            <div class="card-icon" style="background: #F56C6C;">
              <i class="el-icon-warning"></i>
            </div>
            <div class="card-info">
              <h3>Idle</h3>
              <p class="card-number">{{ stats.idle || 0 }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="16">
        <el-card class="dashboard-card">
          <div slot="header">
            <span>Asset Distribution</span>
          </div>
          <div class="chart-container" style="height: 300px; display: flex; align-items: center; justify-content: center;">
            <p class="chart-placeholder">Chart will be displayed here</p>
          </div>
        </el-card>
      </el-col>

      <el-col :span="8">
        <el-card class="dashboard-card">
          <div slot="header">
            <span>Recent Activities</span>
          </div>
          <div class="activity-list">
            <p class="activity-placeholder">No recent activities</p>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="24">
        <el-card class="dashboard-card">
          <div slot="header">
            <span>Quick Actions</span>
          </div>
          <div class="quick-actions">
            <el-button type="primary" icon="el-icon-plus" @click="$router.push('/asset/house')">
              Add House Asset
            </el-button>
            <el-button type="success" icon="el-icon-plus" @click="$router.push('/asset/parking')">
              Add Parking Asset
            </el-button>
            <el-button type="warning" icon="el-icon-plus" @click="$router.push('/asset/facility')">
              Add Facility Asset
            </el-button>
            <el-button type="info" icon="el-icon-plus" @click="$router.push('/maintenance')">
              Create Work Order
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Dashboard',
  data() {
    return {
      stats: {
        totalAssets: 0,
        inUse: 0,
        maintenance: 0,
        idle: 0
      }
    };
  },
  mounted() {
    this.loadStats();
  },
  methods: {
    async loadStats() {
      try {
        // TODO: Load actual statistics from API
        // const response = await this.$http.get('/dashboard/stats');
        // this.stats = response.data;
      } catch (error) {
        console.error('Failed to load stats:', error);
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.dashboard {
  .dashboard-card {
    .card-content {
      display: flex;
      align-items: center;
      gap: 20px;

      .card-icon {
        width: 60px;
        height: 60px;
        border-radius: 8px;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #fff;
        font-size: 24px;
      }

      .card-info {
        flex: 1;

        h3 {
          font-size: 14px;
          color: #909399;
          margin-bottom: 8px;
          font-weight: 400;
        }

        .card-number {
          font-size: 28px;
          font-weight: 600;
          color: #303133;
          margin: 0;
        }
      }
    }

    .chart-container {
      .chart-placeholder {
        color: #909399;
        font-size: 14px;
      }
    }

    .activity-list {
      .activity-placeholder {
        color: #909399;
        font-size: 14px;
        text-align: center;
        padding: 20px 0;
      }
    }

    .quick-actions {
      display: flex;
      gap: 10px;
      flex-wrap: wrap;
    }
  }
}
</style>
