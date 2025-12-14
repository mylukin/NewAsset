<template>
  <div class="ops-dashboard-container">
    <!-- Header -->
    <div class="dashboard-header">
      <h2 class="dashboard-title">Operations Dashboard</h2>
      <div class="header-actions">
        <el-select v-model="selectedProjectId" placeholder="All Projects" clearable size="small" @change="loadDashboard">
          <el-option
            v-for="item in projectOptions"
            :key="item.id"
            :label="item.projectName"
            :value="item.id"
          />
        </el-select>
        <el-button type="primary" size="small" icon="el-icon-refresh" @click="loadDashboard">Refresh</el-button>
      </div>
    </div>

    <!-- Summary Cards -->
    <el-row :gutter="20" class="summary-row">
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="summary-card wait-assign" shadow="hover" v-loading="loading">
          <div class="summary-content">
            <div class="summary-value">{{ dashboard.maintOrderCountByStatus?.WAIT_ASSIGN || 0 }}</div>
            <div class="summary-label">Wait Assign</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="summary-card wait-accept" shadow="hover" v-loading="loading">
          <div class="summary-content">
            <div class="summary-value">{{ dashboard.maintOrderCountByStatus?.WAIT_ACCEPT || 0 }}</div>
            <div class="summary-label">Wait Accept</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="summary-card processing" shadow="hover" v-loading="loading">
          <div class="summary-content">
            <div class="summary-value">{{ dashboard.processingMaintOrderCount || 0 }}</div>
            <div class="summary-label">Processing</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6" :md="6" :lg="6">
        <el-card class="summary-card wait-confirm" shadow="hover" v-loading="loading">
          <div class="summary-content">
            <div class="summary-value">{{ dashboard.maintOrderCountByStatus?.WAIT_CONFIRM || 0 }}</div>
            <div class="summary-label">Wait Confirm</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <!-- Pending Orders List -->
      <el-col :xs="24" :sm="16" :md="16" :lg="16">
        <el-card class="orders-card" shadow="hover" v-loading="ordersLoading">
          <div slot="header" class="card-header">
            <span>Pending Orders</span>
            <el-button type="text" @click="goToOrderList">View All <i class="el-icon-arrow-right"></i></el-button>
          </div>
          <el-table :data="dashboard.recentMaintOrders" size="small" :row-class-name="getRowClassName">
            <el-table-column label="Order No" prop="orderNo" width="140">
              <template slot-scope="scope">
                <el-link type="primary" @click="goToOrderDetail(scope.row)">{{ scope.row.orderNo }}</el-link>
              </template>
            </el-table-column>
            <el-table-column label="Asset" prop="assetName" :show-overflow-tooltip="true" />
            <el-table-column label="Title" prop="title" :show-overflow-tooltip="true" />
            <el-table-column label="Priority" prop="priority" width="100">
              <template slot-scope="scope">
                <el-tag :type="getPriorityTagType(scope.row.priority)" size="mini" :effect="scope.row.priority === 'URGENT' ? 'dark' : 'light'">
                  {{ scope.row.priorityLabel }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="Status" prop="status" width="110">
              <template slot-scope="scope">
                <el-tag :type="getStatusTagType(scope.row.status)" size="mini">
                  {{ scope.row.statusLabel }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="Handler" prop="currentHandlerName" width="100" />
            <el-table-column label="Created" prop="createTime" width="100">
              <template slot-scope="scope">
                <span>{{ formatDate(scope.row.createTime) }}</span>
              </template>
            </el-table-column>
          </el-table>
          <el-empty v-if="!ordersLoading && (!dashboard.recentMaintOrders || dashboard.recentMaintOrders.length === 0)" description="No pending orders" />
        </el-card>
      </el-col>

      <!-- Right Side Stats -->
      <el-col :xs="24" :sm="8" :md="8" :lg="8">
        <!-- My Monthly Stats -->
        <el-card class="stats-card" shadow="hover" v-loading="loading">
          <div slot="header">
            <span>Monthly Statistics</span>
          </div>
          <div class="monthly-stats">
            <div class="stat-item">
              <div class="stat-icon completed">
                <i class="el-icon-circle-check"></i>
              </div>
              <div class="stat-info">
                <div class="stat-value">{{ dashboard.maintOrderCountByStatus?.CLOSED || 0 }}</div>
                <div class="stat-label">Closed</div>
              </div>
            </div>
            <div class="stat-item">
              <div class="stat-icon canceled">
                <i class="el-icon-circle-close"></i>
              </div>
              <div class="stat-info">
                <div class="stat-value">{{ dashboard.maintOrderCountByStatus?.CANCELED || 0 }}</div>
                <div class="stat-label">Canceled</div>
              </div>
            </div>
            <div class="stat-item">
              <div class="stat-icon pending">
                <i class="el-icon-time"></i>
              </div>
              <div class="stat-info">
                <div class="stat-value">{{ dashboard.pendingMaintOrderCount || 0 }}</div>
                <div class="stat-label">Pending</div>
              </div>
            </div>
          </div>
        </el-card>

        <!-- Quick Actions -->
        <el-card class="actions-card" shadow="hover">
          <div slot="header">
            <span>Quick Actions</span>
          </div>
          <div class="quick-actions">
            <el-button type="primary" icon="el-icon-plus" @click="goToCreateOrder" plain block>
              Create Order
            </el-button>
            <el-button type="info" icon="el-icon-s-order" @click="goToOrderList" plain block>
              All Orders
            </el-button>
            <el-button type="warning" icon="el-icon-bell" @click="goToMyOrders" plain block>
              My Orders
            </el-button>
          </div>
        </el-card>

        <!-- Attention Required -->
        <el-card class="attention-card" shadow="hover" v-loading="loading">
          <div slot="header">
            <span>Attention Required</span>
          </div>
          <div class="attention-content">
            <div class="attention-item" v-if="dashboard.assetsNeedingAttentionCount > 0">
              <i class="el-icon-warning-outline"></i>
              <span>{{ dashboard.assetsNeedingAttentionCount }} assets need attention</span>
            </div>
            <div class="attention-item" v-if="dashboard.overdueMaintOrderCount > 0">
              <i class="el-icon-time"></i>
              <span>{{ dashboard.overdueMaintOrderCount }} overdue orders</span>
            </div>
            <el-empty v-if="!dashboard.assetsNeedingAttentionCount && !dashboard.overdueMaintOrderCount" description="All good!" :image-size="60" />
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- Auto-refresh indicator -->
    <div class="refresh-indicator" v-if="refreshTimer">
      <i class="el-icon-refresh"></i>
      Auto-refresh in {{ refreshCountdown }}s
    </div>
  </div>
</template>

<script>
import { getOpsDashboard } from '@/api/asset/dashboard'

export default {
  name: 'OpsDashboard',
  data() {
    return {
      selectedProjectId: null,
      projectOptions: [],
      dashboard: {},
      loading: true,
      ordersLoading: true,
      refreshTimer: null,
      refreshCountdown: 300,
      countdownInterval: null
    }
  },
  mounted() {
    this.loadDashboard()
    this.loadProjectOptions()
    this.startAutoRefresh()
  },
  beforeDestroy() {
    this.stopAutoRefresh()
  },
  methods: {
    loadDashboard() {
      this.loading = true
      this.ordersLoading = true

      getOpsDashboard(this.selectedProjectId).then(response => {
        this.dashboard = response.data || {}
        this.loading = false
        this.ordersLoading = false
        this.resetRefreshCountdown()
      }).catch(() => {
        this.loading = false
        this.ordersLoading = false
      })
    },
    loadProjectOptions() {
      // Would load from API
      this.projectOptions = []
    },
    startAutoRefresh() {
      // Refresh every 5 minutes (300 seconds)
      this.refreshTimer = setInterval(() => {
        this.loadDashboard()
      }, 300000)

      // Update countdown every second
      this.countdownInterval = setInterval(() => {
        this.refreshCountdown--
        if (this.refreshCountdown <= 0) {
          this.refreshCountdown = 300
        }
      }, 1000)
    },
    stopAutoRefresh() {
      if (this.refreshTimer) {
        clearInterval(this.refreshTimer)
        this.refreshTimer = null
      }
      if (this.countdownInterval) {
        clearInterval(this.countdownInterval)
        this.countdownInterval = null
      }
    },
    resetRefreshCountdown() {
      this.refreshCountdown = 300
    },
    getRowClassName({ row }) {
      if (row.priority === 'URGENT') {
        return 'urgent-row'
      }
      return ''
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
    formatDate(dateStr) {
      if (!dateStr) return '-'
      const date = new Date(dateStr)
      return `${date.getMonth() + 1}/${date.getDate()}`
    },
    goToOrderList() {
      this.$router.push('/asset/maint/orderList')
    },
    goToOrderDetail(row) {
      this.$router.push(`/asset/maint/orderDetail/${row.id}`)
    },
    goToCreateOrder() {
      this.$router.push('/asset/maint/orderList?action=create')
    },
    goToMyOrders() {
      this.$router.push('/asset/maint/orderList?filter=my')
    }
  }
}
</script>

<style scoped>
.ops-dashboard-container {
  padding: 20px;
  background: #f0f2f5;
  min-height: calc(100vh - 84px);
  position: relative;
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

.header-actions {
  display: flex;
  gap: 10px;
}

.summary-row {
  margin-bottom: 20px;
}

.summary-card {
  text-align: center;
}

.summary-card.wait-assign {
  border-top: 3px solid #E6A23C;
}

.summary-card.wait-accept {
  border-top: 3px solid #F56C6C;
}

.summary-card.processing {
  border-top: 3px solid #409EFF;
}

.summary-card.wait-confirm {
  border-top: 3px solid #909399;
}

.summary-content {
  padding: 10px 0;
}

.summary-value {
  font-size: 36px;
  font-weight: bold;
  color: #303133;
}

.summary-label {
  font-size: 14px;
  color: #909399;
  margin-top: 5px;
}

.orders-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.stats-card,
.actions-card,
.attention-card {
  margin-bottom: 20px;
}

.monthly-stats {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.stat-item {
  display: flex;
  align-items: center;
  gap: 12px;
}

.stat-icon {
  width: 40px;
  height: 40px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.stat-icon i {
  font-size: 20px;
  color: white;
}

.stat-icon.completed {
  background: #67C23A;
}

.stat-icon.canceled {
  background: #F56C6C;
}

.stat-icon.pending {
  background: #E6A23C;
}

.stat-info {
  flex: 1;
}

.stat-value {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
}

.stat-label {
  font-size: 12px;
  color: #909399;
}

.quick-actions {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.quick-actions .el-button {
  width: 100%;
}

.attention-content {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.attention-item {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 10px;
  background: #FDF6EC;
  border-radius: 4px;
  color: #E6A23C;
}

.attention-item i {
  font-size: 18px;
}

.refresh-indicator {
  position: fixed;
  bottom: 20px;
  right: 20px;
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 8px 16px;
  border-radius: 20px;
  font-size: 12px;
  display: flex;
  align-items: center;
  gap: 5px;
}

.refresh-indicator i {
  animation: spin 2s linear infinite;
}

@keyframes spin {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

/* Urgent row highlight */
::v-deep .urgent-row {
  background-color: #FEF0F0 !important;
}

::v-deep .urgent-row:hover > td {
  background-color: #FDE2E2 !important;
}

@media (max-width: 768px) {
  .dashboard-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

  .header-actions {
    width: 100%;
  }
}
</style>
