<template>
  <div class="facility-asset-list">
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
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="选择状态" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="可用-自用" value="available_self" />
            <el-option label="可用-空置" value="available_idle" />
            <el-option label="故障/停用" value="fault" />
            <el-option label="维修中" value="maintaining" />
          </el-select>
        </el-form-item>
        <el-form-item label="设备类型" prop="facilityType">
          <el-select v-model="queryParams.facilityType" placeholder="选择类型" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="电梯" value="elevator" />
            <el-option label="空调" value="hvac" />
            <el-option label="消防" value="fire_protection" />
            <el-option label="安防" value="security" />
            <el-option label="供电" value="power" />
            <el-option label="给排水" value="plumbing" />
            <el-option label="其他" value="other" />
          </el-select>
        </el-form-item>
        <el-form-item label="保修状态" prop="warrantyStatus">
          <el-select v-model="queryParams.warrantyStatus" placeholder="保修状态" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="保修期内" value="in_warranty" />
            <el-option label="即将过保" value="expiring_soon" />
            <el-option label="已过保" value="expired" />
          </el-select>
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
          <el-button type="primary" plain icon="el-icon-plus" size="mini" v-hasPermi="['asset:facility:add']" @click="handleAdd">新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-upload2" size="mini" v-hasPermi="['asset:facility:import']" @click="handleImport">导入</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-download" size="mini" v-hasPermi="['asset:facility:export']" @click="handleExport">导出</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-delete" size="mini" :disabled="multiple" v-hasPermi="['asset:facility:remove']" @click="handleDelete">删除</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
    </div>

    <!-- Data Table -->
    <div class="table-container">
      <el-table v-loading="loading" :data="facilityList" @selection-change="handleSelectionChange" ref="table" style="width: 100%">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="资产编码" align="center" prop="assetCode" width="150" />
        <el-table-column label="资产名称" align="center" prop="name" width="200" />
        <el-table-column label="楼宇" align="center" prop="buildingName" width="100" />
        <el-table-column label="设备类型" align="center" prop="facilityType" width="100">
          <template slot-scope="scope">
            {{ getFacilityTypeText(scope.row.facilityType) }}
          </template>
        </el-table-column>
        <el-table-column label="品牌/型号" align="center" width="150">
          <template slot-scope="scope">
            {{ scope.row.brand }} / {{ scope.row.model }}
          </template>
        </el-table-column>
        <el-table-column label="保修到期" align="center" prop="warrantyExpireDate" width="120">
          <template slot-scope="scope">
            <span :class="getWarrantyClass(scope.row.warrantyExpireDate)">
              {{ scope.row.warrantyExpireDate }}
            </span>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status" width="120">
          <template slot-scope="scope">
            <el-tag :type="getStatusTagType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200" fixed="right">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)" v-hasPermi="['asset:facility:query']">查看</el-button>
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleEdit(scope.row)" v-hasPermi="['asset:facility:edit']">编辑</el-button>
            <el-button size="mini" type="text" icon="el-icon-wrench" @click="handleMaintenance(scope.row)" v-hasPermi="['asset:maint:add']">维护</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div v-if="!loading && facilityList.length === 0" class="empty-state">
        <el-empty description="暂无设施设备数据，点击"新增"创建第一个资产">
          <el-button type="primary" icon="el-icon-plus" v-hasPermi="['asset:facility:add']" @click="handleAdd">新增设施资产</el-button>
        </el-empty>
      </div>

      <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    </div>
  </div>
</template>

<script>
import { listFacility, delFacility, exportFacility } from '@/api/asset/facility'

export default {
  name: 'FacilityAsset',
  data() {
    return {
      loading: true,
      showSearch: true,
      ids: [],
      multiple: true,
      total: 0,
      facilityList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 20,
        projectId: null,
        buildingId: null,
        status: '',
        facilityType: '',
        warrantyStatus: '',
        keyword: ''
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      listFacility(this.queryParams).then(response => {
        this.facilityList = response.rows || []
        this.total = response.total || 0
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 20,
        projectId: null,
        buildingId: null,
        status: '',
        facilityType: '',
        warrantyStatus: '',
        keyword: ''
      }
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.multiple = !selection.length
    },
    handleAdd() {
      this.$router.push('/asset/facility/add')
    },
    handleEdit(row) {
      this.$router.push(`/asset/facility/edit/${row.id}`)
    },
    handleView(row) {
      this.$router.push(`/asset/facility/detail/${row.id}`)
    },
    handleMaintenance(row) {
      this.$router.push(`/asset/maintenance/create?assetId=${row.id}`)
    },
    handleDelete() {
      const ids = this.ids
      this.$confirm('是否确认删除选中的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return delFacility(ids.join(','))
      }).then(() => {
        this.getList()
        this.$message.success('删除成功')
      }).catch(() => {})
    },
    handleImport() {
      this.$message.info('导入功能待开发')
    },
    handleExport() {
      exportFacility(this.queryParams).then(() => {
        this.$message.success('导出成功')
      })
    },
    getStatusTagType(status) {
      const statusMap = {
        'available_self': 'success',
        'available_idle': 'warning',
        'fault': 'danger',
        'maintaining': 'warning',
        'to_be_scrapped': 'danger',
        'scrapped': 'danger'
      }
      return statusMap[status] || 'info'
    },
    getStatusText(status) {
      const statusMap = {
        'under_construction': '在建/未投入',
        'available_self': '可用-自用',
        'available_idle': '可用-空置',
        'temp_closed': '暂停使用',
        'fault': '故障/停用',
        'maintaining': '维修中',
        'to_be_scrapped': '计划报废',
        'scrapped': '已报废'
      }
      return statusMap[status] || status
    },
    getFacilityTypeText(type) {
      const typeMap = {
        'elevator': '电梯',
        'hvac': '空调',
        'fire_protection': '消防',
        'security': '安防',
        'power': '供电',
        'plumbing': '给排水',
        'other': '其他'
      }
      return typeMap[type] || type
    },
    getWarrantyClass(date) {
      if (!date) return ''
      const expiry = new Date(date)
      const now = new Date()
      const days = (expiry - now) / (1000 * 60 * 60 * 24)
      if (days < 0) return 'warranty-expired'
      if (days < 30) return 'warranty-expiring'
      return 'warranty-valid'
    }
  }
}
</script>

<style scoped>
.facility-asset-list { padding: 20px; }
.search-bar { margin-bottom: 20px; }
.action-bar { margin-bottom: 15px; }
.table-container { background: #fff; padding: 20px; border-radius: 4px; }
.empty-state { padding: 40px 0; }
.warranty-expired { color: #f56c6c; }
.warranty-expiring { color: #e6a23c; }
.warranty-valid { color: #67c23a; }
</style>
