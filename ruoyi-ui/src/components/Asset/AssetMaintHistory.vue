<template>
  <div class="asset-maint-history">
    <!-- Filter Bar -->
    <div class="filter-bar" v-if="showFilter">
      <el-row :gutter="10">
        <el-col :span="6">
          <el-select v-model="filterStatus" placeholder="Filter by status" clearable size="small" @change="handleFilter">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-col>
        <el-col :span="6">
          <el-select v-model="filterType" placeholder="Filter by type" clearable size="small" @change="handleFilter">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" size="small" icon="el-icon-search" @click="loadHistory">Search</el-button>
          <el-button size="small" icon="el-icon-refresh" @click="resetFilter">Reset</el-button>
        </el-col>
        <el-col :span="6" style="text-align: right;">
          <el-button type="success" size="small" icon="el-icon-download" @click="handleExport">Export</el-button>
        </el-col>
      </el-row>
    </div>

    <!-- History List -->
    <el-table :data="historyList" v-loading="loading" size="small" stripe>
      <el-table-column label="Order No" prop="orderNo" width="160">
        <template slot-scope="scope">
          <el-link type="primary" @click="goToDetail(scope.row)">{{ scope.row.orderNo }}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="Type" prop="orderTypeLabel" width="100" />
      <el-table-column label="Title" prop="title" :show-overflow-tooltip="true" />
      <el-table-column label="Priority" prop="priority" width="80">
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
      <el-table-column label="Handler" prop="currentHandlerName" width="100" />
      <el-table-column label="Create Time" prop="createTime" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
    </el-table>

    <!-- Empty State -->
    <el-empty v-if="!loading && historyList.length === 0" description="No maintenance history" />

    <!-- Create Order Button -->
    <div class="action-bar" v-if="showCreateButton">
      <el-button type="primary" size="small" icon="el-icon-plus" @click="handleCreate">
        Create Maintenance Order
      </el-button>
    </div>
  </div>
</template>

<script>
import { getMaintOrdersByAssetId, exportMaintOrder } from '@/api/asset/maintOrder'

export default {
  name: 'AssetMaintHistory',
  props: {
    assetId: {
      type: [Number, String],
      required: true
    },
    showFilter: {
      type: Boolean,
      default: true
    },
    showCreateButton: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      loading: false,
      historyList: [],
      allHistoryList: [],
      filterStatus: '',
      filterType: '',
      statusOptions: [
        { value: 'WAIT_ASSIGN', label: 'Wait Assign' },
        { value: 'WAIT_ACCEPT', label: 'Wait Accept' },
        { value: 'PROCESSING', label: 'Processing' },
        { value: 'WAIT_CONFIRM', label: 'Wait Confirm' },
        { value: 'CLOSED', label: 'Closed' },
        { value: 'CANCELED', label: 'Canceled' }
      ],
      typeOptions: [
        { value: 'FAULT', label: 'Fault' },
        { value: 'REPAIR', label: 'Repair' },
        { value: 'INSPECTION', label: 'Inspection' },
        { value: 'MAINTENANCE', label: 'Maintenance' }
      ]
    }
  },
  watch: {
    assetId: {
      immediate: true,
      handler(newVal) {
        if (newVal) {
          this.loadHistory()
        }
      }
    }
  },
  methods: {
    loadHistory() {
      if (!this.assetId) return

      this.loading = true
      getMaintOrdersByAssetId(this.assetId).then(response => {
        this.allHistoryList = response.data || []
        this.handleFilter()
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    handleFilter() {
      let filtered = [...this.allHistoryList]

      if (this.filterStatus) {
        filtered = filtered.filter(item => item.status === this.filterStatus)
      }

      if (this.filterType) {
        filtered = filtered.filter(item => item.orderType === this.filterType)
      }

      this.historyList = filtered
    },
    resetFilter() {
      this.filterStatus = ''
      this.filterType = ''
      this.handleFilter()
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
    goToDetail(row) {
      this.$router.push(`/asset/maint/orderDetail/${row.id}`)
    },
    handleCreate() {
      this.$emit('create-order', this.assetId)
    },
    handleExport() {
      exportMaintOrder({ assetId: this.assetId }).then(() => {
        this.$modal.msgSuccess('Export started')
      })
    }
  }
}
</script>

<style scoped>
.asset-maint-history {
  padding: 10px 0;
}
.filter-bar {
  margin-bottom: 15px;
}
.action-bar {
  margin-top: 15px;
  text-align: center;
}
</style>
