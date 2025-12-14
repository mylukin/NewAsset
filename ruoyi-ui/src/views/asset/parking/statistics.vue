<template>
  <div class="app-container">
    <!-- Filter Area -->
    <el-card class="filter-card" shadow="never">
      <el-form :model="filterParams" ref="filterForm" size="small" :inline="true">
        <el-form-item label="Project" prop="projectId">
          <el-select v-model="filterParams.projectId" placeholder="All Projects" clearable @change="handleFilterChange">
            <el-option
              v-for="item in projectOptions"
              :key="item.id"
              :label="item.projectName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Parking Zone" prop="parkingZone">
          <el-select v-model="filterParams.parkingZone" placeholder="All Zones" clearable @change="handleFilterChange">
            <el-option
              v-for="dict in dict.type.parking_zone"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-refresh" @click="resetFilter">Reset</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- Statistics Overview -->
    <el-row :gutter="20" class="stats-row" v-loading="loading">
      <!-- Total Parking Spaces -->
      <el-col :xs="24" :sm="12" :lg="6">
        <el-card class="stat-card total-card" shadow="hover">
          <div class="stat-icon">
            <i class="el-icon-place"></i>
          </div>
          <div class="stat-content">
            <div class="stat-value">{{ statistics.totalCount || 0 }}</div>
            <div class="stat-label">Total Parking Spaces</div>
            <div class="stat-area">
              <span>{{ formatArea(statistics.totalArea) }} m²</span>
            </div>
          </div>
        </el-card>
      </el-col>

      <!-- Self-Use -->
      <el-col :xs="24" :sm="12" :lg="6">
        <el-card class="stat-card selfuse-card" shadow="hover">
          <div class="stat-icon">
            <i class="el-icon-user"></i>
          </div>
          <div class="stat-content">
            <div class="stat-value">{{ statistics.selfUseCount || 0 }}</div>
            <div class="stat-label">Self-Use</div>
            <div class="stat-progress">
              <el-progress
                :percentage="statistics.selfUseRate || 0"
                :stroke-width="8"
                :show-text="false"
                color="#67C23A"
              />
              <span class="progress-text">{{ statistics.selfUseRate || 0 }}%</span>
            </div>
          </div>
        </el-card>
      </el-col>

      <!-- Rented Out -->
      <el-col :xs="24" :sm="12" :lg="6">
        <el-card class="stat-card rent-card" shadow="hover">
          <div class="stat-icon">
            <i class="el-icon-money"></i>
          </div>
          <div class="stat-content">
            <div class="stat-value">{{ statistics.rentCount || 0 }}</div>
            <div class="stat-label">Rented Out</div>
            <div class="stat-progress">
              <el-progress
                :percentage="statistics.rentRate || 0"
                :stroke-width="8"
                :show-text="false"
                color="#409EFF"
              />
              <span class="progress-text">{{ statistics.rentRate || 0 }}%</span>
            </div>
          </div>
        </el-card>
      </el-col>

      <!-- Idle -->
      <el-col :xs="24" :sm="12" :lg="6">
        <el-card class="stat-card idle-card" shadow="hover">
          <div class="stat-icon">
            <i class="el-icon-minus"></i>
          </div>
          <div class="stat-content">
            <div class="stat-value">{{ statistics.idleCount || 0 }}</div>
            <div class="stat-label">Idle</div>
            <div class="stat-progress">
              <el-progress
                :percentage="statistics.idleRate || 0"
                :stroke-width="8"
                :show-text="false"
                color="#E6A23C"
              />
              <span class="progress-text">{{ statistics.idleRate || 0 }}%</span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- Detailed Statistics -->
    <el-row :gutter="20" class="detail-row">
      <!-- Usage Distribution -->
      <el-col :xs="24" :lg="12">
        <el-card class="detail-card" shadow="hover">
          <div slot="header" class="card-header">
            <span>Usage Distribution</span>
          </div>
          <div class="distribution-chart">
            <div class="distribution-item">
              <div class="dist-label">
                <span class="dot selfuse"></span>
                <span>Self-Use</span>
              </div>
              <div class="dist-bar">
                <div class="dist-fill selfuse" :style="{ width: (statistics.selfUseRate || 0) + '%' }"></div>
              </div>
              <div class="dist-value">{{ statistics.selfUseCount || 0 }} spaces</div>
            </div>
            <div class="distribution-item">
              <div class="dist-label">
                <span class="dot rent"></span>
                <span>Rented</span>
              </div>
              <div class="dist-bar">
                <div class="dist-fill rent" :style="{ width: (statistics.rentRate || 0) + '%' }"></div>
              </div>
              <div class="dist-value">{{ statistics.rentCount || 0 }} spaces</div>
            </div>
            <div class="distribution-item">
              <div class="dist-label">
                <span class="dot idle"></span>
                <span>Idle</span>
              </div>
              <div class="dist-bar">
                <div class="dist-fill idle" :style="{ width: (statistics.idleRate || 0) + '%' }"></div>
              </div>
              <div class="dist-value">{{ statistics.idleCount || 0 }} spaces</div>
            </div>
          </div>
        </el-card>
      </el-col>

      <!-- Type & Status Breakdown -->
      <el-col :xs="24" :lg="12">
        <el-card class="detail-card" shadow="hover">
          <div slot="header" class="card-header">
            <span>Parking Type & Status</span>
          </div>
          <el-row :gutter="20">
            <el-col :span="12">
              <div class="breakdown-section">
                <h4>By Type</h4>
                <div class="breakdown-item">
                  <span class="label">Fixed</span>
                  <span class="value">{{ statistics.fixedCount || 0 }}</span>
                </div>
                <div class="breakdown-item">
                  <span class="label">Temporary</span>
                  <span class="value">{{ statistics.tempCount || 0 }}</span>
                </div>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="breakdown-section">
                <h4>By Status</h4>
                <div class="breakdown-item">
                  <span class="label">In Use</span>
                  <span class="value">{{ statistics.inUseCount || 0 }}</span>
                </div>
                <div class="breakdown-item">
                  <span class="label">Maintaining</span>
                  <span class="value">{{ statistics.maintainingCount || 0 }}</span>
                </div>
                <div class="breakdown-item">
                  <span class="label">Pending Disposal</span>
                  <span class="value">{{ statistics.pendingDisposalCount || 0 }}</span>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    <!-- Rental Income Summary -->
    <el-row :gutter="20" class="income-row">
      <el-col :span="24">
        <el-card class="income-card" shadow="hover">
          <div slot="header" class="card-header">
            <span>Rental Income Summary</span>
          </div>
          <div class="income-content">
            <div class="income-main">
              <div class="income-icon">
                <i class="el-icon-wallet"></i>
              </div>
              <div class="income-details">
                <div class="income-value">¥{{ formatCurrency(statistics.totalRentIncome) }}</div>
                <div class="income-label">Total Monthly Rental Income</div>
              </div>
            </div>
            <div class="income-stats">
              <div class="income-stat">
                <span class="stat-num">{{ statistics.rentCount || 0 }}</span>
                <span class="stat-text">Rented Spaces</span>
              </div>
              <div class="income-stat">
                <span class="stat-num">{{ formatArea(statistics.rentArea) }}</span>
                <span class="stat-text">Rented Area (m²)</span>
              </div>
              <div class="income-stat" v-if="statistics.rentCount > 0">
                <span class="stat-num">¥{{ formatCurrency(statistics.totalRentIncome / statistics.rentCount) }}</span>
                <span class="stat-text">Avg. Rent/Space</span>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getParkingStatistics } from "@/api/asset/parking";

export default {
  name: "ParkingStatistics",
  dicts: ['parking_zone'],
  data() {
    return {
      loading: false,
      statistics: {},
      projectOptions: [],
      filterParams: {
        projectId: null,
        parkingZone: null
      }
    };
  },
  created() {
    this.loadStatistics();
    this.loadProjectOptions();
  },
  methods: {
    /** Load statistics data */
    loadStatistics() {
      this.loading = true;
      getParkingStatistics(this.filterParams.projectId, this.filterParams.parkingZone)
        .then(response => {
          this.statistics = response.data || {};
        })
        .finally(() => {
          this.loading = false;
        });
    },
    /** Load project options */
    loadProjectOptions() {
      // TODO: Load from project API
      this.projectOptions = [];
    },
    /** Handle filter change - auto refresh */
    handleFilterChange() {
      this.loadStatistics();
    },
    /** Reset filters */
    resetFilter() {
      this.filterParams = {
        projectId: null,
        parkingZone: null
      };
      this.loadStatistics();
    },
    /** Format area value */
    formatArea(value) {
      if (!value) return '0';
      return Number(value).toLocaleString('en-US', { maximumFractionDigits: 2 });
    },
    /** Format currency value */
    formatCurrency(value) {
      if (!value) return '0.00';
      return Number(value).toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
    }
  }
};
</script>

<style scoped>
.filter-card {
  margin-bottom: 20px;
}

.stats-row {
  margin-bottom: 20px;
}

.stat-card {
  display: flex;
  align-items: center;
  padding: 20px;
  margin-bottom: 20px;
  border-radius: 8px;
}

.stat-icon {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 16px;
  flex-shrink: 0;
}

.stat-icon i {
  font-size: 28px;
  color: #fff;
}

.total-card .stat-icon {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.selfuse-card .stat-icon {
  background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);
}

.rent-card .stat-icon {
  background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
}

.idle-card .stat-icon {
  background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
}

.stat-content {
  flex: 1;
}

.stat-value {
  font-size: 32px;
  font-weight: 700;
  color: #303133;
  line-height: 1;
}

.stat-label {
  font-size: 14px;
  color: #909399;
  margin-top: 8px;
}

.stat-area {
  font-size: 13px;
  color: #606266;
  margin-top: 4px;
}

.stat-progress {
  margin-top: 8px;
  display: flex;
  align-items: center;
}

.stat-progress .el-progress {
  flex: 1;
  margin-right: 10px;
}

.progress-text {
  font-size: 14px;
  font-weight: 600;
  color: #606266;
  min-width: 45px;
}

.detail-row {
  margin-bottom: 20px;
}

.detail-card {
  margin-bottom: 20px;
}

.card-header {
  font-weight: 600;
  font-size: 16px;
}

.distribution-chart {
  padding: 10px 0;
}

.distribution-item {
  display: flex;
  align-items: center;
  margin-bottom: 16px;
}

.distribution-item:last-child {
  margin-bottom: 0;
}

.dist-label {
  width: 100px;
  display: flex;
  align-items: center;
}

.dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  margin-right: 8px;
}

.dot.selfuse {
  background: #67C23A;
}

.dot.rent {
  background: #409EFF;
}

.dot.idle {
  background: #E6A23C;
}

.dist-bar {
  flex: 1;
  height: 20px;
  background: #EBEEF5;
  border-radius: 10px;
  overflow: hidden;
  margin: 0 16px;
}

.dist-fill {
  height: 100%;
  border-radius: 10px;
  transition: width 0.3s ease;
}

.dist-fill.selfuse {
  background: linear-gradient(90deg, #67C23A 0%, #85ce61 100%);
}

.dist-fill.rent {
  background: linear-gradient(90deg, #409EFF 0%, #66b1ff 100%);
}

.dist-fill.idle {
  background: linear-gradient(90deg, #E6A23C 0%, #ebb563 100%);
}

.dist-value {
  width: 100px;
  text-align: right;
  font-size: 14px;
  color: #606266;
}

.breakdown-section h4 {
  margin: 0 0 12px 0;
  font-size: 14px;
  color: #909399;
}

.breakdown-item {
  display: flex;
  justify-content: space-between;
  padding: 8px 0;
  border-bottom: 1px solid #EBEEF5;
}

.breakdown-item:last-child {
  border-bottom: none;
}

.breakdown-item .label {
  color: #606266;
}

.breakdown-item .value {
  font-weight: 600;
  color: #303133;
}

.income-card {
  margin-bottom: 20px;
}

.income-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
}

.income-main {
  display: flex;
  align-items: center;
}

.income-icon {
  width: 80px;
  height: 80px;
  background: linear-gradient(135deg, #f5af19 0%, #f12711 100%);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20px;
}

.income-icon i {
  font-size: 36px;
  color: #fff;
}

.income-value {
  font-size: 36px;
  font-weight: 700;
  color: #303133;
}

.income-label {
  font-size: 14px;
  color: #909399;
  margin-top: 4px;
}

.income-stats {
  display: flex;
  gap: 40px;
}

.income-stat {
  text-align: center;
}

.income-stat .stat-num {
  display: block;
  font-size: 24px;
  font-weight: 600;
  color: #303133;
}

.income-stat .stat-text {
  display: block;
  font-size: 12px;
  color: #909399;
  margin-top: 4px;
}

@media (max-width: 768px) {
  .income-content {
    flex-direction: column;
    align-items: flex-start;
  }

  .income-stats {
    margin-top: 20px;
    width: 100%;
    justify-content: space-around;
  }
}
</style>
