<template>
  <div class="house-asset-list">
    <!-- Search Bar -->
    <div class="search-bar">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
        <el-form-item label="项目" prop="projectId">
          <el-select v-model="queryParams.projectId" placeholder="选择项目" style="width: 200px">
            <el-option label="项目A" value="1" />
            <el-option label="项目B" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="楼宇" prop="buildingId">
          <el-select v-model="queryParams.buildingId" placeholder="选择楼宇" style="width: 200px">
            <el-option label="1号楼" value="1" />
            <el-option label="2号楼" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="楼层" prop="floorId">
          <el-select v-model="queryParams.floorId" placeholder="选择楼层" style="width: 200px">
            <el-option label="1层" value="1" />
            <el-option label="2层" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="选择状态" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="可用-自用" value="available_self" />
            <el-option label="可用-对外出租" value="available_rent" />
            <el-option label="可用-空置" value="available_idle" />
            <el-option label="故障/停用" value="fault" />
            <el-option label="维修中" value="maintaining" />
          </el-select>
        </el-form-item>
        <el-form-item label="当前用途" prop="currentUsage">
          <el-select v-model="queryParams.currentUsage" placeholder="选择用途" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="自住" value="self_use" />
            <el-option label="出租" value="rented" />
            <el-option label="空置" value="idle" />
          </el-select>
        </el-form-item>
        <el-form-item label="面积范围">
          <el-input-number v-model="queryParams.areaMin" placeholder="最小面积" :min="0" style="width: 150px" />
          <span style="margin: 0 10px;">-</span>
          <el-input-number v-model="queryParams.areaMax" placeholder="最大面积" :min="0" style="width: 150px" />
        </el-form-item>
        <el-form-item label="关键词" prop="keyword">
          <el-input v-model="queryParams.keyword" placeholder="输入资产编码或名称" style="width: 200px" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- Action Bar -->
    <div class="action-bar">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            v-hasPermi="['asset:house:add']"
            @click="handleAdd"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            plain
            icon="el-icon-upload2"
            size="mini"
            v-hasPermi="['asset:house:import']"
            @click="handleImport"
          >导入</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            plain
            icon="el-icon-download"
            size="mini"
            v-hasPermi="['asset:house:export']"
            @click="handleExport"
          >导出</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            v-hasPermi="['asset:house:remove']"
            @click="handleDelete"
          >删除</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
    </div>

    <!-- Status Filter Tabs -->
    <div class="status-tabs">
      <el-tabs v-model="activeStatus" @tab-click="handleStatusTabClick">
        <el-tab-pane label="全部" name="" />
        <el-tab-pane label="自用" name="self_use" />
        <el-tab-pane label="出租" name="rented" />
        <el-tab-pane label="空置" name="idle" />
      </el-tabs>
    </div>

    <!-- Data Table -->
    <div class="table-container">
      <el-table
        v-loading="loading"
        :data="houseList"
        @selection-change="handleSelectionChange"
        ref="table"
        style="width: 100%"
      >
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="资产编码" align="center" prop="assetCode" width="150" />
        <el-table-column label="资产名称" align="center" prop="name" width="200" />
        <el-table-column label="楼宇" align="center" prop="buildingName" width="100" />
        <el-table-column label="楼层" align="center" prop="floorName" width="100" />
        <el-table-column label="房号" align="center" prop="roomNo" width="100" />
        <el-table-column label="建筑面积" align="center" prop="buildingArea" width="100">
          <template slot-scope="scope">
            {{ scope.row.buildingArea }}㎡
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status" width="120">
          <template slot-scope="scope">
            <el-tag :type="getStatusTagType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="当前用途" align="center" prop="currentUsage" width="100">
          <template slot-scope="scope">
            <el-tag :type="getUsageTagType(scope.row.currentUsage)">
              {{ getUsageText(scope.row.currentUsage) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-view"
              @click="handleView(scope.row)"
              v-hasPermi="['asset:house:query']"
              data-testid="action-view"
            >查看</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleEdit(scope.row)"
              v-hasPermi="['asset:house:edit']"
              data-testid="action-edit"
            >编辑</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-copy-document"
              @click="handleCopy(scope.row)"
              v-hasPermi="['asset:house:add']"
            >复制</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-wrench"
              @click="handleMaintenance(scope.row)"
              v-hasPermi="['asset:maint:add']"
            >维护</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- Empty State -->
      <div v-if="!loading && houseList.length === 0" class="empty-state">
        <el-empty description="No house assets found. Click 'New' to create your first asset.">
          <el-button
            type="primary"
            icon="el-icon-plus"
            v-hasPermi="['asset:house:add']"
            @click="handleAdd"
          >新增房源资产</el-button>
        </el-empty>
      </div>

      <!-- Pagination -->
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>
  </div>
</template>

<script>
import {
  listHouse,
  getHouse,
  addHouse,
  updateHouse,
  delHouse,
  copyHouse,
  exportHouse
} from '@/api/asset/house'

export default {
  name: 'HouseAsset',
  data() {
    return {
      // Loading state
      loading: true,
      // Show search bar
      showSearch: true,
      // Selected rows
      ids: [],
      // Multiple selection flag
      multiple: true,
      // Active status tab
      activeStatus: '',
      // Total count
      total: 0,
      // House asset list
      houseList: [],
      // Query parameters
      queryParams: {
        pageNum: 1,
        pageSize: 20,
        projectId: null,
        buildingId: null,
        floorId: null,
        status: '',
        currentUsage: '',
        areaMin: null,
        areaMax: null,
        keyword: ''
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /**
     * Get house asset list
     */
    getList() {
      this.loading = true
      listHouse(this.queryParams).then(response => {
        this.houseList = response.rows || []
        this.total = response.total || 0
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },

    /**
     * Handle search
     */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },

    /**
     * Reset search
     */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 20,
        projectId: null,
        buildingId: null,
        floorId: null,
        status: '',
        currentUsage: '',
        areaMin: null,
        areaMax: null,
        keyword: ''
      }
      this.activeStatus = ''
      this.handleQuery()
    },

    /**
     * Handle status tab click
     */
    handleStatusTabClick(tab) {
      this.queryParams.currentUsage = tab.name
      this.handleQuery()
    },

    /**
     * Handle selection change
     */
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.multiple = !selection.length
    },

    /**
     * Handle add
     */
    handleAdd() {
      this.$router.push('/asset/house/add')
    },

    /**
     * Handle edit
     */
    handleEdit(row) {
      this.$router.push(`/asset/house/edit/${row.id}`)
    },

    /**
     * Handle view
     */
    handleView(row) {
      this.$router.push(`/asset/house/detail/${row.id}`)
    },

    /**
     * Handle copy
     */
    handleCopy(row) {
      copyHouse(row.id).then(response => {
        this.$message.success('复制成功')
        this.getList()
      })
    },

    /**
     * Handle maintenance
     */
    handleMaintenance(row) {
      this.$router.push(`/asset/maintenance/create?assetId=${row.id}`)
    },

    /**
     * Handle delete
     */
    handleDelete() {
      const ids = this.ids
      this.$confirm('是否确认删除选中的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return delHouse(ids.join(','))
      }).then(() => {
        this.getList()
        this.$message.success('删除成功')
      }).catch(() => {})
    },

    /**
     * Handle import
     */
    handleImport() {
      this.$message.info('导入功能待开发')
    },

    /**
     * Handle export
     */
    handleExport() {
      this.queryParams.pageNum = 1
      exportHouse(this.queryParams).then(response => {
        this.$message.success('导出成功')
      })
    },

    /**
     * Get status tag type
     */
    getStatusTagType(status) {
      const statusMap = {
        'available_self': 'success',
        'available_rent': 'success',
        'available_idle': 'warning',
        'temp_closed': 'warning',
        'fault': 'danger',
        'maintaining': 'warning',
        'to_be_scrapped': 'danger',
        'scrapped': 'danger'
      }
      return statusMap[status] || 'info'
    },

    /**
     * Get status text
     */
    getStatusText(status) {
      const statusMap = {
        'under_construction': '在建/未投入',
        'available_self': '可用-自用',
        'available_rent': '可用-对外出租',
        'available_idle': '可用-空置',
        'temp_closed': '暂停使用',
        'fault': '故障/停用',
        'maintaining': '维修中',
        'to_be_scrapped': '计划报废',
        'scrapped': '已报废'
      }
      return statusMap[status] || status
    },

    /**
     * Get usage tag type
     */
    getUsageTagType(usage) {
      const usageMap = {
        'self_use': 'success',
        'rented': 'warning',
        'idle': 'danger'
      }
      return usageMap[usage] || 'info'
    },

    /**
     * Get usage text
     */
    getUsageText(usage) {
      const usageMap = {
        'self_use': '自住',
        'rented': '出租',
        'idle': '空置'
      }
      return usageMap[usage] || usage
    }
  }
}
</script>

<style scoped>
.house-asset-list {
  padding: 20px;
}

.search-bar {
  margin-bottom: 20px;
}

.action-bar {
  margin-bottom: 15px;
}

.status-tabs {
  margin-bottom: 15px;
}

.table-container {
  background: #fff;
  padding: 20px;
  border-radius: 4px;
}

.empty-state {
  padding: 40px 0;
}
</style>
