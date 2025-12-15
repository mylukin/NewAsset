<template>
  <div class="office-asset-list">
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
        <el-form-item label="资产类型" prop="officeType">
          <el-select v-model="queryParams.officeType" placeholder="选择类型" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="办公家具" value="furniture" />
            <el-option label="IT设备" value="it_equipment" />
            <el-option label="办公设备" value="office_equipment" />
            <el-option label="其他" value="other" />
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
          <el-button type="primary" plain icon="el-icon-plus" size="mini" v-hasPermi="['asset:office:add']" @click="handleAdd">新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-upload2" size="mini" v-hasPermi="['asset:office:import']" @click="handleImport">导入</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-download" size="mini" v-hasPermi="['asset:office:export']" @click="handleExport">导出</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-delete" size="mini" :disabled="multiple" v-hasPermi="['asset:office:remove']" @click="handleDelete">删除</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
    </div>

    <!-- Data Table -->
    <div class="table-container">
      <el-table v-loading="loading" :data="officeList" @selection-change="handleSelectionChange" ref="table" style="width: 100%">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="资产编码" align="center" prop="assetCode" width="150" />
        <el-table-column label="资产名称" align="center" prop="name" width="200" />
        <el-table-column label="楼宇/楼层" align="center" width="120">
          <template slot-scope="scope">
            {{ scope.row.buildingName }} / {{ scope.row.floorName }}
          </template>
        </el-table-column>
        <el-table-column label="资产类型" align="center" prop="officeType" width="100">
          <template slot-scope="scope">
            {{ getOfficeTypeText(scope.row.officeType) }}
          </template>
        </el-table-column>
        <el-table-column label="品牌/型号" align="center" width="150">
          <template slot-scope="scope">
            {{ scope.row.brand }} / {{ scope.row.model }}
          </template>
        </el-table-column>
        <el-table-column label="使用人" align="center" prop="responsiblePerson" width="100" />
        <el-table-column label="状态" align="center" prop="status" width="120">
          <template slot-scope="scope">
            <el-tag :type="getStatusTagType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200" fixed="right">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)" v-hasPermi="['asset:office:query']">查看</el-button>
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleEdit(scope.row)" v-hasPermi="['asset:office:edit']">编辑</el-button>
            <el-button size="mini" type="text" icon="el-icon-wrench" @click="handleMaintenance(scope.row)" v-hasPermi="['asset:maint:add']">维护</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div v-if="!loading && officeList.length === 0" class="empty-state">
        <el-empty description="暂无办公资产数据，点击"新增"创建第一个资产">
          <el-button type="primary" icon="el-icon-plus" v-hasPermi="['asset:office:add']" @click="handleAdd">新增办公资产</el-button>
        </el-empty>
      </div>

      <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    </div>
  </div>
</template>

<script>
import { listOffice, delOffice, exportOffice } from '@/api/asset/office'

export default {
  name: 'OfficeAsset',
  data() {
    return {
      loading: true,
      showSearch: true,
      ids: [],
      multiple: true,
      total: 0,
      officeList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 20,
        projectId: null,
        buildingId: null,
        status: '',
        officeType: '',
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
      listOffice(this.queryParams).then(response => {
        this.officeList = response.rows || []
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
        officeType: '',
        keyword: ''
      }
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.multiple = !selection.length
    },
    handleAdd() {
      this.$router.push('/asset/office/add')
    },
    handleEdit(row) {
      this.$router.push(`/asset/office/edit/${row.id}`)
    },
    handleView(row) {
      this.$router.push(`/asset/office/detail/${row.id}`)
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
        return delOffice(ids.join(','))
      }).then(() => {
        this.getList()
        this.$message.success('删除成功')
      }).catch(() => {})
    },
    handleImport() {
      this.$message.info('导入功能待开发')
    },
    handleExport() {
      exportOffice(this.queryParams).then(() => {
        this.$message.success('导出成功')
      })
    },
    getStatusTagType(status) {
      const statusMap = {
        'available_self': 'success',
        'available_idle': 'warning',
        'fault': 'danger',
        'maintaining': 'warning'
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
    getOfficeTypeText(type) {
      const typeMap = {
        'furniture': '办公家具',
        'it_equipment': 'IT设备',
        'office_equipment': '办公设备',
        'other': '其他'
      }
      return typeMap[type] || type
    }
  }
}
</script>

<style scoped>
.office-asset-list { padding: 20px; }
.search-bar { margin-bottom: 20px; }
.action-bar { margin-bottom: 15px; }
.table-container { background: #fff; padding: 20px; border-radius: 4px; }
.empty-state { padding: 40px 0; }
</style>
