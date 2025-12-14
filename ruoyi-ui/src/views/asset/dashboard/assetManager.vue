<template>
  <div class="asset-manager-dashboard">
    <!-- Header with Project Selector -->
    <div class="dashboard-header">
      <h2 class="dashboard-title">Asset Manager Dashboard</h2>
      <div class="project-selector">
        <el-select v-model="selectedProjectId" placeholder="All Projects" clearable @change="loadDashboard">
          <el-option
            v-for="item in projectOptions"
            :key="item.id"
            :label="item.projectName"
            :value="item.id"
          />
        </el-select>
      </div>
    </div>

    <!-- Summary Cards -->
    <el-row :gutter="20" class="summary-cards">
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="summary-card" shadow="hover" v-loading="loading">
          <div class="summary-content">
            <div class="summary-icon" style="background: #409EFF;">
              <i class="el-icon-s-data"></i>
            </div>
            <div class="summary-info">
              <div class="summary-value">{{ dashboard.totalAssetCount || 0 }}</div>
              <div class="summary-label">Total Assets</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="summary-card" shadow="hover" v-loading="loading">
          <div class="summary-content">
            <div class="summary-icon" style="background: #67C23A;">
              <i class="el-icon-house"></i>
            </div>
            <div class="summary-info">
              <div class="summary-value">{{ dashboard.houseStatistics?.totalCount || 0 }}</div>
              <div class="summary-label">Houses</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="summary-card" shadow="hover" v-loading="loading">
          <div class="summary-content">
            <div class="summary-icon" style="background: #E6A23C;">
              <i class="el-icon-truck"></i>
            </div>
            <div class="summary-info">
              <div class="summary-value">{{ dashboard.parkingStatistics?.totalCount || 0 }}</div>
              <div class="summary-label">Parking Spaces</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="summary-card" shadow="hover" v-loading="loading">
          <div class="summary-content">
            <div class="summary-icon" style="background: #F56C6C;">
              <i class="el-icon-warning"></i>
            </div>
            <div class="summary-info">
              <div class="summary-value">{{ dashboard.pendingMaintOrderCount || 0 }}</div>
              <div class="summary-label">Pending Maint.</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <!-- Left Column -->
      <el-col :xs="24" :sm="16" :md="16" :lg="16">
        <!-- Asset Distribution Chart -->
        <el-card class="chart-card" shadow="hover" v-loading="chartLoading">
          <div slot="header">
            <span>Asset Distribution by Type</span>
          </div>
          <div class="chart-container" ref="distributionChart"></div>
          <el-empty v-if="!chartLoading && !hasChartData" description="No asset data" :image-size="80" />
        </el-card>

        <!-- House & Parking Stats -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-card class="stats-card" shadow="hover" v-loading="loading">
              <div slot="header">
                <span>House Utilization</span>
              </div>
              <div class="utilization-stats" v-if="dashboard.houseStatistics">
                <div class="util-row">
                  <span class="util-label">Self-use</span>
                  <el-progress
                    :percentage="Number(dashboard.houseStatistics.selfUseRate) || 0"
                    :stroke-width="12"
                    color="#67C23A"
                  />
                </div>
                <div class="util-row">
                  <span class="util-label">Rented</span>
                  <el-progress
                    :percentage="Number(dashboard.houseStatistics.rentRate) || 0"
                    :stroke-width="12"
                    color="#409EFF"
                  />
                </div>
                <div class="util-row">
                  <span class="util-label">Idle</span>
                  <el-progress
                    :percentage="Number(dashboard.houseStatistics.idleRate) || 0"
                    :stroke-width="12"
                    color="#909399"
                  />
                </div>
              </div>
              <el-empty v-else description="No data" :image-size="50" />
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card class="stats-card" shadow="hover" v-loading="loading">
              <div slot="header">
                <span>Parking Occupancy</span>
              </div>
              <div class="occupancy-stats" v-if="dashboard.parkingStatistics">
                <div class="occupancy-chart">
                  <el-progress
                    type="dashboard"
                    :percentage="Number(dashboard.parkingStatistics.occupancyRate) || 0"
                    :width="120"
                    :stroke-width="10"
                  />
                </div>
                <div class="occupancy-detail">
                  <div class="detail-item">
                    <span class="detail-value">{{ dashboard.parkingStatistics.occupiedCount || 0 }}</span>
                    <span class="detail-label">Occupied</span>
                  </div>
                  <div class="detail-item">
                    <span class="detail-value">{{ dashboard.parkingStatistics.availableCount || 0 }}</span>
                    <span class="detail-label">Available</span>
                  </div>
                </div>
              </div>
              <el-empty v-else description="No data" :image-size="50" />
            </el-card>
          </el-col>
        </el-row>
      </el-col>

      <!-- Right Column -->
      <el-col :xs="24" :sm="8" :md="8" :lg="8">
        <!-- Assets by Status -->
        <el-card class="status-card" shadow="hover" v-loading="loading">
          <div slot="header">
            <span>Assets by Status</span>
          </div>
          <div class="status-list" v-if="dashboard.assetCountByStatus">
            <div
              class="status-item"
              v-for="(count, status) in dashboard.assetCountByStatus"
              :key="status"
            >
              <div class="status-info">
                <el-tag :type="getStatusType(status)" size="small">{{ formatStatus(status) }}</el-tag>
              </div>
              <div class="status-count">{{ count }}</div>
            </div>
          </div>
          <el-empty v-else description="No data" :image-size="50" />
        </el-card>

        <!-- Maintenance Summary -->
        <el-card class="maint-card" shadow="hover" v-loading="loading">
          <div slot="header">
            <span>Maintenance Overview</span>
          </div>
          <div class="maint-list" v-if="dashboard.maintOrderCountByStatus">
            <div class="maint-item">
              <div class="maint-label">Wait Assign</div>
              <div class="maint-value warning">{{ dashboard.maintOrderCountByStatus.WAIT_ASSIGN || 0 }}</div>
            </div>
            <div class="maint-item">
              <div class="maint-label">Wait Accept</div>
              <div class="maint-value warning">{{ dashboard.maintOrderCountByStatus.WAIT_ACCEPT || 0 }}</div>
            </div>
            <div class="maint-item">
              <div class="maint-label">Processing</div>
              <div class="maint-value primary">{{ dashboard.maintOrderCountByStatus.PROCESSING || 0 }}</div>
            </div>
            <div class="maint-item">
              <div class="maint-label">Closed</div>
              <div class="maint-value success">{{ dashboard.maintOrderCountByStatus.CLOSED || 0 }}</div>
            </div>
          </div>
          <el-button type="text" @click="goToMaintOrders" class="view-all-btn">
            View All Orders <i class="el-icon-arrow-right"></i>
          </el-button>
        </el-card>

        <!-- Quick Links -->
        <el-card class="links-card" shadow="hover">
          <div slot="header">
            <span>Quick Links</span>
          </div>
          <div class="quick-links">
            <el-button type="text" @click="goToHouses"><i class="el-icon-house"></i> Houses</el-button>
            <el-button type="text" @click="goToParking"><i class="el-icon-truck"></i> Parking</el-button>
            <el-button type="text" @click="goToVenues"><i class="el-icon-place"></i> Venues</el-button>
            <el-button type="text" @click="goToOffice"><i class="el-icon-office-building"></i> Office</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getAssetManagerDashboard } from '@/api/asset/dashboard'
import * as echarts from 'echarts'

export default {
  name: 'AssetManagerDashboard',
  data() {
    return {
      selectedProjectId: null,
      projectOptions: [],
      dashboard: {},
      loading: true,
      chartLoading: true,
      chartInstance: null
    }
  },
  computed: {
    hasChartData() {
      return this.dashboard.assetCountByType && Object.keys(this.dashboard.assetCountByType).length > 0
    }
  },
  mounted() {
    this.loadDashboard()
    this.loadProjectOptions()
    window.addEventListener('resize', this.handleResize)
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize)
    if (this.chartInstance) {
      this.chartInstance.dispose()
    }
  },
  methods: {
    loadDashboard() {
      this.loading = true
      this.chartLoading = true

      getAssetManagerDashboard(this.selectedProjectId).then(response => {
        this.dashboard = response.data || {}
        this.loading = false

        this.$nextTick(() => {
          this.initChart()
          this.chartLoading = false
        })
      }).catch(() => {
        this.loading = false
        this.chartLoading = false
      })
    },
    loadProjectOptions() {
      // Would load from API
      this.projectOptions = []
    },
    initChart() {
      const chartDom = this.$refs.distributionChart
      if (!chartDom || !this.hasChartData) return

      if (this.chartInstance) {
        this.chartInstance.dispose()
      }

      this.chartInstance = echarts.init(chartDom)

      const typeLabels = {
        'HOUSE': 'House',
        'PARKING': 'Parking',
        'VENUE': 'Venue',
        'OFFICE': 'Office',
        'FACILITY': 'Facility'
      }
      const colors = ['#5470C6', '#91CC75', '#FAC858', '#EE6666', '#73C0DE']

      const data = []
      let i = 0
      for (const [key, value] of Object.entries(this.dashboard.assetCountByType)) {
        data.push({
          name: typeLabels[key] || key,
          value: value,
          itemStyle: { color: colors[i % colors.length] }
        })
        i++
      }

      const option = {
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} ({d}%)'
        },
        legend: {
          bottom: 0,
          left: 'center'
        },
        series: [{
          type: 'pie',
          radius: ['35%', '60%'],
          center: ['50%', '45%'],
          avoidLabelOverlap: true,
          itemStyle: {
            borderRadius: 8,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: true,
            formatter: '{b}\n{c}'
          },
          data: data
        }]
      }

      this.chartInstance.setOption(option)
    },
    handleResize() {
      if (this.chartInstance) {
        this.chartInstance.resize()
      }
    },
    getStatusType(status) {
      const map = {
        'IN_USE': 'success',
        'IDLE': 'info',
        'MAINTAINING': 'warning',
        'PENDING_DISPOSAL': 'danger'
      }
      return map[status] || 'info'
    },
    formatStatus(status) {
      const map = {
        'IN_USE': 'In Use',
        'IDLE': 'Idle',
        'MAINTAINING': 'Maintaining',
        'PENDING_DISPOSAL': 'Pending Disposal'
      }
      return map[status] || status
    },
    goToMaintOrders() {
      this.$router.push('/asset/maint/orderList')
    },
    goToHouses() {
      this.$router.push('/asset/house/list')
    },
    goToParking() {
      this.$router.push('/asset/parking/list')
    },
    goToVenues() {
      this.$router.push('/asset/venue/list')
    },
    goToOffice() {
      this.$router.push('/asset/office/list')
    }
  }
}
</script>

<style scoped>
.asset-manager-dashboard {
  padding: 20px;
  background: #f0f2f5;
  min-height: calc(100vh - 84px);
}

.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.dashboard-title {
  margin: 0;
  font-size: 22px;
  font-weight: 600;
  color: #303133;
}

.project-selector {
  width: 200px;
}

.summary-cards {
  margin-bottom: 20px;
}

.summary-card .el-card__body {
  padding: 15px;
}

.summary-content {
  display: flex;
  align-items: center;
}

.summary-icon {
  width: 50px;
  height: 50px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 12px;
}

.summary-icon i {
  font-size: 24px;
  color: white;
}

.summary-info {
  flex: 1;
}

.summary-value {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
}

.summary-label {
  font-size: 13px;
  color: #909399;
}

.chart-card {
  margin-bottom: 20px;
  min-height: 350px;
}

.chart-container {
  height: 280px;
}

.stats-card {
  margin-bottom: 20px;
  min-height: 220px;
}

.utilization-stats {
  padding: 10px 0;
}

.util-row {
  margin-bottom: 15px;
}

.util-label {
  display: block;
  font-size: 13px;
  color: #606266;
  margin-bottom: 5px;
}

.occupancy-stats {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px 0;
}

.occupancy-chart {
  margin-bottom: 15px;
}

.occupancy-detail {
  display: flex;
  gap: 30px;
}

.detail-item {
  text-align: center;
}

.detail-value {
  display: block;
  font-size: 20px;
  font-weight: bold;
  color: #303133;
}

.detail-label {
  font-size: 12px;
  color: #909399;
}

.status-card,
.maint-card,
.links-card {
  margin-bottom: 20px;
}

.status-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.status-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
  border-bottom: 1px solid #EBEEF5;
}

.status-item:last-child {
  border-bottom: none;
}

.status-count {
  font-size: 18px;
  font-weight: bold;
  color: #303133;
}

.maint-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 15px;
}

.maint-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.maint-label {
  font-size: 14px;
  color: #606266;
}

.maint-value {
  font-size: 18px;
  font-weight: bold;
}

.maint-value.warning {
  color: #E6A23C;
}

.maint-value.primary {
  color: #409EFF;
}

.maint-value.success {
  color: #67C23A;
}

.view-all-btn {
  width: 100%;
  text-align: center;
}

.quick-links {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.quick-links .el-button {
  flex: 1 1 45%;
  justify-content: flex-start;
}

@media (max-width: 768px) {
  .dashboard-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

  .project-selector {
    width: 100%;
  }
}
</style>
