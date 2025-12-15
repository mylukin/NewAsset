<template>
  <div class="venue-asset-list">
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
            <el-option label="可用-空置" value="available_idle" />
            <el-option label="可用-对外出租" value="available_rent" />
            <el-option label="故障/停用" value="fault" />
            <el-option label="维修中" value="maintaining" />
          </el-select>
        </el-form-item>
        <el-form-item label="场地类型" prop="venueType">
          <el-select v-model="queryParams.venueType" placeholder="选择类型" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="会议室" value="meeting_room" />
            <el-option label="活动中心" value="activity_center" />
            <el-option label="健身房" value="gym" />
            <el-option label="游泳池" value="pool" />
            <el-option label="多功能厅" value="multi_purpose" />
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
          <el-button type="primary" plain icon="el-icon-plus" size="mini" v-hasPermi="['asset:venue:add']" @click="handleAdd">新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-upload2" size="mini" v-hasPermi="['asset:venue:import']" @click="handleImport">导入</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-download" size="mini" v-hasPermi="['asset:venue:export']" @click="handleExport">导出</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-delete" size="mini" :disabled="multiple" v-hasPermi="['asset:venue:remove']" @click="handleDelete">删除</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
    </div>

    <!-- Data Table -->
    <div class="table-container">
      <el-table v-loading="loading" :data="venueList" @selection-change="handleSelectionChange" ref="table" style="width: 100%">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="资产编码" align="center" prop="assetCode" width="150" />
        <el-table-column label="资产名称" align="center" prop="name" width="200" />
        <el-table-column label="楼宇" align="center" prop="buildingName" width="100" />
        <el-table-column label="场地类型" align="center" prop="venueType" width="100">
          <template slot-scope="scope">
            {{ getVenueTypeText(scope.row.venueType) }}
          </template>
        </el-table-column>
        <el-table-column label="面积" align="center" prop="area" width="100">
          <template slot-scope="scope">
            {{ scope.row.area }}㎡
          </template>
        </el-table-column>
        <el-table-column label="容纳人数" align="center" prop="capacity" width="100" />
        <el-table-column label="状态" align="center" prop="status" width="120">
          <template slot-scope="scope">
            <el-tag :type="getStatusTagType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200" fixed="right">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)" v-hasPermi="['asset:venue:query']">查看</el-button>
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleEdit(scope.row)" v-hasPermi="['asset:venue:edit']">编辑</el-button>
            <el-button size="mini" type="text" icon="el-icon-wrench" @click="handleMaintenance(scope.row)" v-hasPermi="['asset:maint:add']">维护</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div v-if="!loading && venueList.length === 0" class="empty-state">
        <el-empty description="暂无场地资产数据，点击"新增"创建第一个资产">
          <el-button type="primary" icon="el-icon-plus" v-hasPermi="['asset:venue:add']" @click="handleAdd">新增场地资产</el-button>
        </el-empty>
      </div>

      <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    </div>
  </div>
</template>

<script>
import { listVenue, delVenue, exportVenue } from '@/api/asset/venue'

export default {
  name: 'VenueAsset',
  data() {
    return {
      loading: true,
      showSearch: true,
      ids: [],
      multiple: true,
      total: 0,
      venueList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 20,
        projectId: null,
        buildingId: null,
        status: '',
        venueType: '',
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
      listVenue(this.queryParams).then(response => {
        this.venueList = response.rows || []
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
        venueType: '',
        keyword: ''
      }
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.multiple = !selection.length
    },
    handleAdd() {
      this.$router.push('/asset/venue/add')
    },
    handleEdit(row) {
      this.$router.push(`/asset/venue/edit/${row.id}`)
    },
    handleView(row) {
      this.$router.push(`/asset/venue/detail/${row.id}`)
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
        return delVenue(ids.join(','))
      }).then(() => {
        this.getList()
        this.$message.success('删除成功')
      }).catch(() => {})
    },
    handleImport() {
      this.$message.info('导入功能待开发')
    },
    handleExport() {
      exportVenue(this.queryParams).then(() => {
        this.$message.success('导出成功')
      })
    },
    getStatusTagType(status) {
      const statusMap = {
        'available_idle': 'warning',
        'available_rent': 'success',
        'fault': 'danger',
        'maintaining': 'warning'
      }
      return statusMap[status] || 'info'
    },
    getStatusText(status) {
      const statusMap = {
        'under_construction': '在建/未投入',
        'available_idle': '可用-空置',
        'available_rent': '可用-对外出租',
        'temp_closed': '暂停使用',
        'fault': '故障/停用',
        'maintaining': '维修中',
        'to_be_scrapped': '计划报废',
        'scrapped': '已报废'
      }
      return statusMap[status] || status
    },
    getVenueTypeText(type) {
      const typeMap = {
        'meeting_room': '会议室',
        'activity_center': '活动中心',
        'gym': '健身房',
        'pool': '游泳池',
        'multi_purpose': '多功能厅',
        'other': '其他'
      }
      return typeMap[type] || type
    }
  }
}
</script>

<style scoped>
.venue-asset-list { padding: 20px; }
.search-bar { margin-bottom: 20px; }
.action-bar { margin-bottom: 15px; }
.table-container { background: #fff; padding: 20px; border-radius: 4px; }
.empty-state { padding: 40px 0; }
</style>
