<template>
  <div class="management-dashboard">
    <!-- Loading State -->
    <div v-if="loading" class="loading-container">
      <span>Loading dashboard...</span>
    </div>

    <!-- Error State -->
    <div v-else-if="error" class="error-container">
      <span class="error-message">{{ error }}</span>
      <button @click="fetchMetrics" class="retry-button">Retry</button>
    </div>

    <!-- Dashboard Content -->
    <div v-else class="dashboard-content">
      <!-- KPI Cards -->
      <div class="kpi-cards">
        <div class="kpi-card" v-for="kpi in kpis" :key="kpi.id">
          <div class="kpi-label">{{ kpi.label }}</div>
          <div class="kpi-value">{{ kpi.value }}</div>
          <div class="kpi-trend" :class="kpi.trend > 0 ? 'positive' : 'negative'">
            {{ kpi.trend > 0 ? '+' : '' }}{{ kpi.trend }}%
          </div>
        </div>
      </div>

      <!-- Charts Section -->
      <div class="charts-section">
        <!-- Date Range Filter -->
        <div class="date-filter">
          <select v-model="dateRange" @change="onDateRangeChange">
            <option value="7">Last 7 days</option>
            <option value="30">Last 30 days</option>
            <option value="90">Last 90 days</option>
          </select>
        </div>

        <!-- Asset Trend Chart -->
        <div class="chart-container">
          <h3>Asset Trend</h3>
          <div class="chart" ref="assetTrendChart"></div>
        </div>

        <!-- Asset Distribution Chart -->
        <div class="chart-container">
          <h3>Asset Distribution by Type</h3>
          <div class="chart" ref="assetDistributionChart"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ManagementHome',
  data() {
    return {
      loading: false,
      error: null,
      metrics: null,
      dateRange: '30',
      kpis: []
    }
  },
  mounted() {
    this.fetchMetrics()
  },
  methods: {
    async fetchMetrics() {
      this.loading = true
      this.error = null
      try {
        const params = this.getQueryParams()
        const response = await fetch(`/api/dashboard/management-home?${params}`)
        if (!response.ok) {
          throw new Error('Failed to load dashboard')
        }
        this.metrics = await response.json()
        this.updateKPIs()
        this.renderCharts()
      } catch (err) {
        this.error = err.message || 'Failed to load dashboard'
      } finally {
        this.loading = false
      }
    },
    getQueryParams() {
      const days = parseInt(this.dateRange)
      const to = new Date().toISOString().split('T')[0]
      const from = new Date(Date.now() - days * 24 * 60 * 60 * 1000).toISOString().split('T')[0]
      const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone
      return `from=${from}&to=${to}&timezone=${encodeURIComponent(timezone)}`
    },
    updateKPIs() {
      if (!this.metrics) return
      this.kpis = [
        { id: 'totalAssets', label: 'Total Assets', value: this.metrics.totalAssets, trend: 5.2 },
        { id: 'assetsInUse', label: 'Assets In Use', value: this.metrics.assetsInUse, trend: 3.1 },
        { id: 'pendingWorkOrders', label: 'Pending Work Orders', value: this.metrics.pendingWorkOrders, trend: -12.5 },
        { id: 'totalValue', label: 'Total Value', value: this.formatCurrency(this.metrics.totalAssetValue), trend: 2.8 }
      ]
    },
    formatCurrency(value) {
      if (!value) return '$0'
      return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(value)
    },
    onDateRangeChange() {
      this.fetchMetrics()
    },
    renderCharts() {
      // Charts would be rendered using ECharts or similar library
      // This is a placeholder for the actual chart rendering logic
    }
  },
  computed: {
    dashboardState() {
      if (this.loading) return 'loading'
      if (this.error) return 'error'
      return 'ready'
    }
  }
}

// Helper function for deriving UI state
export function deriveManagementHomeState({ data, loading, error }) {
  if (loading) return 'loading'
  if (error) return 'error'
  if (data) return 'ready'
  return 'loading'
}

// Helper function for serializing query params
export function serializeQuery(params) {
  return Object.entries(params)
    .map(([key, value]) => `${key}=${encodeURIComponent(value)}`)
    .join('&')
}
</script>

<style scoped>
.management-dashboard {
  padding: 20px;
}

.loading-container,
.error-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 400px;
}

.error-message {
  color: #f56c6c;
  margin-bottom: 16px;
}

.retry-button {
  padding: 8px 16px;
  background-color: #409eff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.kpi-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.kpi-card {
  background: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.kpi-label {
  font-size: 14px;
  color: #909399;
  margin-bottom: 8px;
}

.kpi-value {
  font-size: 28px;
  font-weight: bold;
  color: #303133;
}

.kpi-trend {
  font-size: 12px;
  margin-top: 8px;
}

.kpi-trend.positive {
  color: #67c23a;
}

.kpi-trend.negative {
  color: #f56c6c;
}

.charts-section {
  margin-top: 20px;
}

.date-filter {
  margin-bottom: 20px;
}

.date-filter select {
  padding: 8px 16px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}

.chart-container {
  background: white;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.chart {
  height: 300px;
}
</style>
