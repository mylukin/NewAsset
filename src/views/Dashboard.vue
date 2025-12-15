<template>
  <div class="dashboard">
    <!-- KPI Cards Row -->
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="dashboard-card kpi-card" v-loading="loading">
          <div class="card-content">
            <div class="card-icon" style="background: #409EFF;">
              <i class="el-icon-office-building"></i>
            </div>
            <div class="card-info">
              <h3>资产总数</h3>
              <p class="card-number">{{ stats.totalAssets || 0 }}</p>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card class="dashboard-card kpi-card" v-loading="loading">
          <div class="card-content">
            <div class="card-icon" style="background: #67C23A;">
              <i class="el-icon-check"></i>
            </div>
            <div class="card-info">
              <h3>使用中</h3>
              <p class="card-number">{{ stats.inUse || 0 }}</p>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card class="dashboard-card kpi-card" v-loading="loading">
          <div class="card-content">
            <div class="card-icon" style="background: #E6A23C;">
              <i class="el-icon-tools"></i>
            </div>
            <div class="card-info">
              <h3>维护中</h3>
              <p class="card-number">{{ stats.maintenance || 0 }}</p>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="6">
        <el-card class="dashboard-card kpi-card" v-loading="loading">
          <div class="card-content">
            <div class="card-icon" style="background: #F56C6C;">
              <i class="el-icon-warning"></i>
            </div>
            <div class="card-info">
              <h3>空置</h3>
              <p class="card-number">{{ stats.idle || 0 }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- Charts Row -->
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="16">
        <el-card class="dashboard-card">
          <div slot="header" class="card-header">
            <span>资产分布</span>
            <el-button-group>
              <el-button size="mini" :type="chartType === 'type' ? 'primary' : ''" @click="chartType = 'type'">按类型</el-button>
              <el-button size="mini" :type="chartType === 'status' ? 'primary' : ''" @click="chartType = 'status'">按状态</el-button>
            </el-button-group>
          </div>
          <div class="chart-container" v-loading="chartLoading">
            <div class="distribution-chart">
              <div class="chart-bars" v-if="chartType === 'type'">
                <div class="chart-item" v-for="(item, index) in assetTypeDistribution" :key="index">
                  <div class="chart-label">{{ item.name }}</div>
                  <div class="chart-bar-wrapper">
                    <div class="chart-bar" :style="{ width: getBarWidth(item.count) + '%', backgroundColor: getBarColor(index) }"></div>
                    <span class="chart-value">{{ item.count }}</span>
                  </div>
                </div>
              </div>
              <div class="chart-bars" v-else>
                <div class="chart-item" v-for="(item, index) in assetStatusDistribution" :key="index">
                  <div class="chart-label">{{ item.name }}</div>
                  <div class="chart-bar-wrapper">
                    <div class="chart-bar" :style="{ width: getBarWidth(item.count) + '%', backgroundColor: getStatusColor(item.status) }"></div>
                    <span class="chart-value">{{ item.count }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="8">
        <el-card class="dashboard-card">
          <div slot="header" class="card-header">
            <span>最近活动</span>
            <el-button type="text" size="mini" @click="$router.push('/maintenance')">查看全部</el-button>
          </div>
          <div class="activity-list" v-loading="activityLoading">
            <div class="activity-item" v-for="(activity, index) in recentActivities" :key="index">
              <div class="activity-icon" :style="{ backgroundColor: getActivityColor(activity.type) }">
                <i :class="getActivityIcon(activity.type)"></i>
              </div>
              <div class="activity-content">
                <div class="activity-title">{{ activity.title }}</div>
                <div class="activity-time">{{ activity.time }}</div>
              </div>
            </div>
            <div v-if="recentActivities.length === 0" class="empty-state">
              <el-empty description="暂无最近活动" :image-size="60" />
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- Maintenance & Quick Actions Row -->
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="12">
        <el-card class="dashboard-card">
          <div slot="header" class="card-header">
            <span>待处理工单</span>
            <el-badge :value="pendingOrders.length" class="item" type="warning" />
          </div>
          <div class="pending-orders" v-loading="orderLoading">
            <el-table :data="pendingOrders" size="small" style="width: 100%">
              <el-table-column prop="orderNo" label="工单号" width="140" />
              <el-table-column prop="assetName" label="资产名称" />
              <el-table-column prop="priority" label="优先级" width="80">
                <template slot-scope="scope">
                  <el-tag :type="getPriorityTagType(scope.row.priority)" size="mini">
                    {{ getPriorityText(scope.row.priority) }}
                  </el-tag>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="80">
                <template slot-scope="scope">
                  <el-button type="text" size="mini" @click="handleViewOrder(scope.row)">处理</el-button>
                </template>
              </el-table-column>
            </el-table>
            <div v-if="pendingOrders.length === 0" class="empty-state">
              <el-empty description="暂无待处理工单" :image-size="60" />
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card class="dashboard-card">
          <div slot="header">
            <span>快捷操作</span>
          </div>
          <div class="quick-actions">
            <el-row :gutter="15">
              <el-col :span="12">
                <div class="quick-action-item" @click="$router.push('/asset/house')">
                  <div class="action-icon" style="background: #409EFF;"><i class="el-icon-house"></i></div>
                  <span>房源管理</span>
                </div>
              </el-col>
              <el-col :span="12">
                <div class="quick-action-item" @click="$router.push('/asset/parking')">
                  <div class="action-icon" style="background: #67C23A;"><i class="el-icon-truck"></i></div>
                  <span>车位管理</span>
                </div>
              </el-col>
              <el-col :span="12">
                <div class="quick-action-item" @click="$router.push('/asset/facility')">
                  <div class="action-icon" style="background: #E6A23C;"><i class="el-icon-setting"></i></div>
                  <span>设施设备</span>
                </div>
              </el-col>
              <el-col :span="12">
                <div class="quick-action-item" @click="$router.push('/maintenance')">
                  <div class="action-icon" style="background: #F56C6C;"><i class="el-icon-tickets"></i></div>
                  <span>维修工单</span>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getDashboardStats, getAssetDistribution, getRecentActivities, getMaintenanceStats } from '@/api/dashboard'

export default {
  name: 'Dashboard',
  data() {
    return {
      loading: true,
      chartLoading: true,
      activityLoading: true,
      orderLoading: true,
      chartType: 'type',
      stats: {
        totalAssets: 0,
        inUse: 0,
        maintenance: 0,
        idle: 0
      },
      assetTypeDistribution: [
        { name: '房源', count: 0 },
        { name: '车位', count: 0 },
        { name: '设施设备', count: 0 },
        { name: '场地', count: 0 },
        { name: '办公资产', count: 0 }
      ],
      assetStatusDistribution: [
        { name: '使用中', status: 'in_use', count: 0 },
        { name: '空置', status: 'idle', count: 0 },
        { name: '维护中', status: 'maintenance', count: 0 },
        { name: '故障', status: 'fault', count: 0 }
      ],
      recentActivities: [],
      pendingOrders: []
    }
  },
  mounted() {
    this.loadDashboardData()
  },
  methods: {
    loadDashboardData() {
      this.loadStats()
      this.loadDistribution()
      this.loadActivities()
      this.loadPendingOrders()
    },
    loadStats() {
      this.loading = true
      getDashboardStats().then(response => {
        this.stats = response.data || this.stats
      }).catch(() => {
        // Use mock data if API fails
        this.stats = {
          totalAssets: 156,
          inUse: 98,
          maintenance: 12,
          idle: 46
        }
      }).finally(() => {
        this.loading = false
      })
    },
    loadDistribution() {
      this.chartLoading = true
      getAssetDistribution().then(response => {
        if (response.data) {
          this.assetTypeDistribution = response.data.typeDistribution || this.assetTypeDistribution
          this.assetStatusDistribution = response.data.statusDistribution || this.assetStatusDistribution
        }
      }).catch(() => {
        // Use mock data if API fails
        this.assetTypeDistribution = [
          { name: '房源', count: 45 },
          { name: '车位', count: 68 },
          { name: '设施设备', count: 23 },
          { name: '场地', count: 12 },
          { name: '办公资产', count: 8 }
        ]
        this.assetStatusDistribution = [
          { name: '使用中', status: 'in_use', count: 98 },
          { name: '空置', status: 'idle', count: 46 },
          { name: '维护中', status: 'maintenance', count: 8 },
          { name: '故障', status: 'fault', count: 4 }
        ]
      }).finally(() => {
        this.chartLoading = false
      })
    },
    loadActivities() {
      this.activityLoading = true
      getRecentActivities({ limit: 5 }).then(response => {
        this.recentActivities = response.rows || []
      }).catch(() => {
        // Use mock data if API fails
        this.recentActivities = [
          { type: 'create', title: '新增房源资产 HOUSE-001', time: '10分钟前' },
          { type: 'maintenance', title: '创建维修工单 MO-2024001', time: '30分钟前' },
          { type: 'update', title: '更新车位信息 PARK-025', time: '1小时前' },
          { type: 'complete', title: '完成维修工单 MO-2024000', time: '2小时前' }
        ]
      }).finally(() => {
        this.activityLoading = false
      })
    },
    loadPendingOrders() {
      this.orderLoading = true
      getMaintenanceStats().then(response => {
        this.pendingOrders = response.data?.pendingOrders || []
      }).catch(() => {
        // Use mock data if API fails
        this.pendingOrders = [
          { id: 1, orderNo: 'MO-2024001', assetName: '1号电梯', priority: 'high' },
          { id: 2, orderNo: 'MO-2024002', assetName: '101室空调', priority: 'medium' },
          { id: 3, orderNo: 'MO-2024003', assetName: 'B1-005车位', priority: 'low' }
        ]
      }).finally(() => {
        this.orderLoading = false
      })
    },
    getBarWidth(count) {
      const maxCount = Math.max(...this.assetTypeDistribution.map(i => i.count), ...this.assetStatusDistribution.map(i => i.count))
      return maxCount > 0 ? (count / maxCount * 100) : 0
    },
    getBarColor(index) {
      const colors = ['#409EFF', '#67C23A', '#E6A23C', '#F56C6C', '#909399']
      return colors[index % colors.length]
    },
    getStatusColor(status) {
      const colorMap = {
        'in_use': '#67C23A',
        'idle': '#E6A23C',
        'maintenance': '#409EFF',
        'fault': '#F56C6C'
      }
      return colorMap[status] || '#909399'
    },
    getActivityColor(type) {
      const colorMap = {
        'create': '#67C23A',
        'update': '#409EFF',
        'maintenance': '#E6A23C',
        'complete': '#67C23A',
        'delete': '#F56C6C'
      }
      return colorMap[type] || '#909399'
    },
    getActivityIcon(type) {
      const iconMap = {
        'create': 'el-icon-plus',
        'update': 'el-icon-edit',
        'maintenance': 'el-icon-wrench',
        'complete': 'el-icon-check',
        'delete': 'el-icon-delete'
      }
      return iconMap[type] || 'el-icon-info'
    },
    getPriorityTagType(priority) {
      const priorityMap = {
        'urgent': 'danger',
        'high': 'warning',
        'medium': 'info',
        'low': ''
      }
      return priorityMap[priority] || 'info'
    },
    getPriorityText(priority) {
      const priorityMap = {
        'urgent': '紧急',
        'high': '高',
        'medium': '中',
        'low': '低'
      }
      return priorityMap[priority] || priority
    },
    handleViewOrder(row) {
      this.$router.push(`/maintenance/detail/${row.id}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  padding: 20px;

  .dashboard-card {
    margin-bottom: 0;

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
  }

  .kpi-card {
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
  }

  .chart-container {
    height: 280px;
    padding: 10px 0;
  }

  .distribution-chart {
    height: 100%;

    .chart-bars {
      .chart-item {
        display: flex;
        align-items: center;
        margin-bottom: 15px;

        .chart-label {
          width: 80px;
          font-size: 14px;
          color: #606266;
        }

        .chart-bar-wrapper {
          flex: 1;
          display: flex;
          align-items: center;

          .chart-bar {
            height: 24px;
            border-radius: 4px;
            transition: width 0.3s ease;
          }

          .chart-value {
            margin-left: 10px;
            font-size: 14px;
            color: #303133;
            font-weight: 500;
          }
        }
      }
    }
  }

  .activity-list {
    max-height: 280px;
    overflow-y: auto;

    .activity-item {
      display: flex;
      align-items: flex-start;
      padding: 12px 0;
      border-bottom: 1px solid #f0f0f0;

      &:last-child {
        border-bottom: none;
      }

      .activity-icon {
        width: 32px;
        height: 32px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #fff;
        font-size: 14px;
        margin-right: 12px;
        flex-shrink: 0;
      }

      .activity-content {
        flex: 1;

        .activity-title {
          font-size: 14px;
          color: #303133;
          margin-bottom: 4px;
        }

        .activity-time {
          font-size: 12px;
          color: #909399;
        }
      }
    }
  }

  .pending-orders {
    max-height: 200px;
    overflow-y: auto;
  }

  .quick-actions {
    .quick-action-item {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 20px;
      margin-bottom: 15px;
      border-radius: 8px;
      background: #f5f7fa;
      cursor: pointer;
      transition: all 0.3s;

      &:hover {
        background: #e4e7ed;
        transform: translateY(-2px);
      }

      .action-icon {
        width: 48px;
        height: 48px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #fff;
        font-size: 20px;
        margin-bottom: 10px;
      }

      span {
        font-size: 14px;
        color: #606266;
      }
    }
  }

  .empty-state {
    padding: 20px 0;
    text-align: center;
  }
}
</style>
