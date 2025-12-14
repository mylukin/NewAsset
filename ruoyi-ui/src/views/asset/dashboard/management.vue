<template>
  <div class="dashboard-container">
    <!-- Header with Project Selector -->
    <div class="dashboard-header">
      <h2 class="dashboard-title">Management Dashboard</h2>
      <div class="project-selector">
        <el-select v-model="selectedProjectId" placeholder="All Projects" clearable @change="handleProjectChange">
          <el-option
            v-for="item in projectOptions"
            :key="item.id"
            :label="item.projectName"
            :value="item.id"
          />
        </el-select>
      </div>
    </div>

    <!-- Metric Cards Row -->
    <el-row :gutter="20" class="metric-cards">
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="metric-card" shadow="hover" v-loading="cardsLoading">
          <div class="metric-content">
            <div class="metric-icon" style="background: #409EFF;">
              <i class="el-icon-office-building"></i>
            </div>
            <div class="metric-info">
              <div class="metric-value">{{ dashboard.totalAssetCount || 0 }}</div>
              <div class="metric-label">Total Assets</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="metric-card" shadow="hover" v-loading="cardsLoading">
          <div class="metric-content">
            <div class="metric-icon" style="background: #67C23A;">
              <i class="el-icon-money"></i>
            </div>
            <div class="metric-info">
              <div class="metric-value">{{ formatCurrency(dashboard.totalAssetValue) }}</div>
              <div class="metric-label">Total Value</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="metric-card" shadow="hover" v-loading="cardsLoading">
          <div class="metric-content">
            <div class="metric-icon" style="background: #E6A23C;">
              <i class="el-icon-warning"></i>
            </div>
            <div class="metric-info">
              <div class="metric-value">{{ dashboard.pendingMaintOrderCount || 0 }}</div>
              <div class="metric-label">Pending Orders</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="metric-card" shadow="hover" v-loading="cardsLoading">
          <div class="metric-content">
            <div class="metric-icon" style="background: #F56C6C;">
              <i class="el-icon-setting"></i>
            </div>
            <div class="metric-info">
              <div class="metric-value">{{ dashboard.processingMaintOrderCount || 0 }}</div>
              <div class="metric-label">Processing</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- Charts Row -->
    <el-row :gutter="20" class="charts-row">
      <!-- Asset Type Distribution -->
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <el-card class="chart-card" shadow="hover" v-loading="chartsLoading">
          <div slot="header">
            <span>Asset Distribution by Type</span>
          </div>
          <div class="chart-container" ref="typeChart"></div>
        </el-card>
      </el-col>
      <!-- Asset Status Distribution -->
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <el-card class="chart-card" shadow="hover" v-loading="chartsLoading">
          <div slot="header">
            <span>Asset Distribution by Status</span>
          </div>
          <div class="chart-container" ref="statusChart"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- House/Parking Statistics Row -->
    <el-row :gutter="20" class="stats-row">
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <el-card class="stats-card" shadow="hover" v-loading="statsLoading">
          <div slot="header">
            <span>House Statistics</span>
          </div>
          <div class="stats-content" v-if="dashboard.houseStatistics">
            <el-row :gutter="10">
              <el-col :span="8">
                <el-statistic title="Total Houses" :value="dashboard.houseStatistics.totalCount || 0" />
              </el-col>
              <el-col :span="8">
                <el-statistic title="Self-use" :value="dashboard.houseStatistics.selfUseCount || 0" />
              </el-col>
              <el-col :span="8">
                <el-statistic title="Rented" :value="dashboard.houseStatistics.rentCount || 0" />
              </el-col>
            </el-row>
            <el-progress
              :percentage="dashboard.houseStatistics.rentRate || 0"
              :format="() => `Rent Rate: ${dashboard.houseStatistics.rentRate}%`"
              style="margin-top: 15px;"
            />
          </div>
          <el-empty v-else description="No data" :image-size="60" />
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="12" :md="12" :lg="12">
        <el-card class="stats-card" shadow="hover" v-loading="statsLoading">
          <div slot="header">
            <span>Parking Statistics</span>
          </div>
          <div class="stats-content" v-if="dashboard.parkingStatistics">
            <el-row :gutter="10">
              <el-col :span="8">
                <el-statistic title="Total Spaces" :value="dashboard.parkingStatistics.totalCount || 0" />
              </el-col>
              <el-col :span="8">
                <el-statistic title="Occupied" :value="dashboard.parkingStatistics.occupiedCount || 0" />
              </el-col>
              <el-col :span="8">
                <el-statistic title="Available" :value="dashboard.parkingStatistics.availableCount || 0" />
              </el-col>
            </el-row>
            <el-progress
              :percentage="dashboard.parkingStatistics.occupancyRate || 0"
              :format="() => `Occupancy: ${dashboard.parkingStatistics.occupancyRate}%`"
              style="margin-top: 15px;"
            />
          </div>
          <el-empty v-else description="No data" :image-size="60" />
        </el-card>
      </el-col>
    </el-row>

    <!-- Recent Orders Table -->
    <el-card class="orders-card" shadow="hover" v-loading="ordersLoading">
      <div slot="header">
        <span>Recent Maintenance Orders (Top 10)</span>
        <el-button type="text" style="float: right" @click="goToOrderList">View All</el-button>
      </div>
      <el-table :data="dashboard.recentMaintOrders" size="small" stripe>
        <el-table-column label="Order No" prop="orderNo" width="150" />
        <el-table-column label="Asset" prop="assetName" :show-overflow-tooltip="true" />
        <el-table-column label="Type" prop="orderTypeLabel" width="100" />
        <el-table-column label="Title" prop="title" :show-overflow-tooltip="true" />
        <el-table-column label="Priority" prop="priority" width="90">
          <template slot-scope="scope">
            <el-tag :type="getPriorityTagType(scope.row.priority)" size="mini">
              {{ scope.row.priorityLabel }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="Status" prop="status" width="100">
          <template slot-scope="scope">
            <el-tag :type="getStatusTagType(scope.row.status)" size="mini">
              {{ scope.row.statusLabel }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="Create Time" prop="createTime" width="150">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
      </el-table>
      <el-empty v-if="!ordersLoading && (!dashboard.recentMaintOrders || dashboard.recentMaintOrders.length === 0)" description="No recent orders" />
    </el-card>
  </div>
</template>

<script>
import { getManagementDashboard } from '@/api/asset/dashboard'
import * as echarts from 'echarts'

export default {
  name: 'ManagementDashboard',
  data() {
    return {
      selectedProjectId: null,
      projectOptions: [],
      dashboard: {},
      cardsLoading: true,
      chartsLoading: true,
      statsLoading: true,
      ordersLoading: true,
      typeChartInstance: null,
      statusChartInstance: null
    }
  },
  mounted() {
    this.loadDashboard()
    this.loadProjectOptions()
    window.addEventListener('resize', this.handleResize)
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.handleResize)
    if (this.typeChartInstance) {
      this.typeChartInstance.dispose()
    }
    if (this.statusChartInstance) {
      this.statusChartInstance.dispose()
    }
  },
  methods: {
    loadDashboard() {
      this.cardsLoading = true
      this.chartsLoading = true
      this.statsLoading = true
      this.ordersLoading = true

      getManagementDashboard(this.selectedProjectId).then(response => {
        this.dashboard = response.data || {}
        this.cardsLoading = false
        this.statsLoading = false
        this.ordersLoading = false

        this.$nextTick(() => {
          this.initCharts()
          this.chartsLoading = false
        })
      }).catch(() => {
        this.cardsLoading = false
        this.chartsLoading = false
        this.statsLoading = false
        this.ordersLoading = false
      })
    },
    loadProjectOptions() {
      // Would load from API - placeholder
      this.projectOptions = []
    },
    handleProjectChange() {
      this.loadDashboard()
    },
    initCharts() {
      this.initTypeChart()
      this.initStatusChart()
    },
    initTypeChart() {
      const chartDom = this.$refs.typeChart
      if (!chartDom) return

      if (this.typeChartInstance) {
        this.typeChartInstance.dispose()
      }

      this.typeChartInstance = echarts.init(chartDom)

      const data = []
      const typeLabels = {
        'HOUSE': 'House',
        'PARKING': 'Parking',
        'VENUE': 'Venue',
        'OFFICE': 'Office',
        'FACILITY': 'Facility'
      }

      if (this.dashboard.assetCountByType) {
        for (const [key, value] of Object.entries(this.dashboard.assetCountByType)) {
          data.push({ name: typeLabels[key] || key, value: value })
        }
      }

      const option = {
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          right: 10,
          top: 'center'
        },
        series: [{
          type: 'pie',
          radius: ['40%', '70%'],
          center: ['40%', '50%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: false
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 14,
              fontWeight: 'bold'
            }
          },
          data: data
        }]
      }

      this.typeChartInstance.setOption(option)
    },
    initStatusChart() {
      const chartDom = this.$refs.statusChart
      if (!chartDom) return

      if (this.statusChartInstance) {
        this.statusChartInstance.dispose()
      }

      this.statusChartInstance = echarts.init(chartDom)

      const data = []
      const statusLabels = {
        'IN_USE': 'In Use',
        'IDLE': 'Idle',
        'MAINTAINING': 'Maintaining',
        'PENDING_DISPOSAL': 'Pending Disposal'
      }
      const statusColors = {
        'IN_USE': '#67C23A',
        'IDLE': '#909399',
        'MAINTAINING': '#E6A23C',
        'PENDING_DISPOSAL': '#F56C6C'
      }

      if (this.dashboard.assetCountByStatus) {
        for (const [key, value] of Object.entries(this.dashboard.assetCountByStatus)) {
          data.push({
            name: statusLabels[key] || key,
            value: value,
            itemStyle: { color: statusColors[key] }
          })
        }
      }

      const option = {
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          right: 10,
          top: 'center'
        },
        series: [{
          type: 'pie',
          radius: ['40%', '70%'],
          center: ['40%', '50%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: false
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 14,
              fontWeight: 'bold'
            }
          },
          data: data
        }]
      }

      this.statusChartInstance.setOption(option)
    },
    handleResize() {
      if (this.typeChartInstance) {
        this.typeChartInstance.resize()
      }
      if (this.statusChartInstance) {
        this.statusChartInstance.resize()
      }
    },
    formatCurrency(value) {
      if (!value) return '0'
      return new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'CNY',
        minimumFractionDigits: 0,
        maximumFractionDigits: 0
      }).format(value)
    },
    getStatusTagType(status) {
      const map = {
        'WAIT_ASSIGN': 'warning',
        'WAIT_ACCEPT': 'warning',
        'PROCESSING': 'primary',
        'WAIT_CONFIRM': 'info',
        'CLOSED': 'success',
        'CANCELED': 'danger'
      }
      return map[status] || 'info'
    },
    getPriorityTagType(priority) {
      const map = {
        'URGENT': 'danger',
        'IMPORTANT': 'warning',
        'NORMAL': 'info'
      }
      return map[priority] || 'info'
    },
    goToOrderList() {
      this.$router.push('/asset/maint/orderList')
    }
  }
}
</script>

<style scoped>
.dashboard-container {
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

.metric-cards {
  margin-bottom: 20px;
}

.metric-card {
  height: 100%;
}

.metric-card .el-card__body {
  padding: 20px;
}

.metric-content {
  display: flex;
  align-items: center;
}

.metric-icon {
  width: 64px;
  height: 64px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 16px;
}

.metric-icon i {
  font-size: 28px;
  color: white;
}

.metric-info {
  flex: 1;
}

.metric-value {
  font-size: 28px;
  font-weight: bold;
  color: #303133;
  line-height: 1.2;
}

.metric-label {
  font-size: 14px;
  color: #909399;
  margin-top: 4px;
}

.charts-row {
  margin-bottom: 20px;
}

.chart-card,
.stats-card {
  height: 100%;
  min-height: 320px;
}

.chart-container {
  height: 260px;
}

.stats-row {
  margin-bottom: 20px;
}

.stats-content {
  padding: 10px 0;
}

.orders-card {
  margin-bottom: 20px;
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

  .metric-content {
    flex-direction: column;
    text-align: center;
  }

  .metric-icon {
    margin-right: 0;
    margin-bottom: 10px;
  }
}
</style>
