<template>
  <div class="maintenance-order-list">
    <!-- Search Bar -->
    <div class="search-bar">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
        <el-form-item label="项目" prop="projectId">
          <el-select v-model="queryParams.projectId" placeholder="选择项目" style="width: 200px">
            <el-option label="项目A" value="1" />
            <el-option label="项目B" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="工单状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="选择状态" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="待分配" value="pending" />
            <el-option label="已分配" value="assigned" />
            <el-option label="进行中" value="in_progress" />
            <el-option label="已完成" value="completed" />
            <el-option label="已关闭" value="closed" />
            <el-option label="已取消" value="cancelled" />
          </el-select>
        </el-form-item>
        <el-form-item label="优先级" prop="priority">
          <el-select v-model="queryParams.priority" placeholder="选择优先级" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="紧急" value="urgent" />
            <el-option label="高" value="high" />
            <el-option label="中" value="medium" />
            <el-option label="低" value="low" />
          </el-select>
        </el-form-item>
        <el-form-item label="维修类型" prop="maintType">
          <el-select v-model="queryParams.maintType" placeholder="选择类型" style="width: 200px">
            <el-option label="全部" value="" />
            <el-option label="日常维护" value="routine" />
            <el-option label="故障维修" value="repair" />
            <el-option label="预防性维护" value="preventive" />
            <el-option label="改造升级" value="upgrade" />
          </el-select>
        </el-form-item>
        <el-form-item label="关键词" prop="keyword">
          <el-input v-model="queryParams.keyword" placeholder="输入工单号或资产编码" style="width: 200px" clearable />
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
          <el-button type="primary" plain icon="el-icon-plus" size="mini" v-hasPermi="['asset:maint:add']" @click="handleAdd">新增工单</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button plain icon="el-icon-download" size="mini" v-hasPermi="['asset:maint:export']" @click="handleExport">导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
    </div>

    <!-- Status Filter Tabs -->
    <div class="status-tabs">
      <el-tabs v-model="activeStatus" @tab-click="handleStatusTabClick">
        <el-tab-pane label="全部" name="" />
        <el-tab-pane label="待分配" name="pending" />
        <el-tab-pane label="进行中" name="in_progress" />
        <el-tab-pane label="已完成" name="completed" />
        <el-tab-pane label="已关闭" name="closed" />
      </el-tabs>
    </div>

    <!-- Data Table -->
    <div class="table-container">
      <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange" ref="table" style="width: 100%">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="工单号" align="center" prop="orderNo" width="180" />
        <el-table-column label="资产编码" align="center" prop="assetCode" width="150" />
        <el-table-column label="资产名称" align="center" prop="assetName" width="200" />
        <el-table-column label="维修类型" align="center" prop="maintType" width="100">
          <template slot-scope="scope">
            {{ getMaintTypeText(scope.row.maintType) }}
          </template>
        </el-table-column>
        <el-table-column label="优先级" align="center" prop="priority" width="80">
          <template slot-scope="scope">
            <el-tag :type="getPriorityTagType(scope.row.priority)" size="small">
              {{ getPriorityText(scope.row.priority) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status" width="100">
          <template slot-scope="scope">
            <el-tag :type="getStatusTagType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="负责人" align="center" prop="assignee" width="100" />
        <el-table-column label="创建时间" align="center" prop="createTime" width="150" />
        <el-table-column label="操作" align="center" width="250" fixed="right">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-view" @click="handleView(scope.row)" v-hasPermi="['asset:maint:query']">查看</el-button>
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleEdit(scope.row)" v-hasPermi="['asset:maint:edit']" v-if="scope.row.status === 'pending'">编辑</el-button>
            <el-button size="mini" type="text" icon="el-icon-user" @click="handleAssign(scope.row)" v-hasPermi="['asset:maint:assign']" v-if="scope.row.status === 'pending'">分配</el-button>
            <el-button size="mini" type="text" icon="el-icon-video-play" @click="handleStart(scope.row)" v-hasPermi="['asset:maint:edit']" v-if="scope.row.status === 'assigned'">开始</el-button>
            <el-button size="mini" type="text" icon="el-icon-check" @click="handleComplete(scope.row)" v-hasPermi="['asset:maint:edit']" v-if="scope.row.status === 'in_progress'">完成</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div v-if="!loading && orderList.length === 0" class="empty-state">
        <el-empty description="暂无维修工单，点击"新增工单"创建">
          <el-button type="primary" icon="el-icon-plus" v-hasPermi="['asset:maint:add']" @click="handleAdd">新增维修工单</el-button>
        </el-empty>
      </div>

      <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    </div>
  </div>
</template>

<script>
import { listOrder, delOrder, assignOrder, startOrder, completeOrder, exportOrder } from '@/api/maintenance/order'

export default {
  name: 'MaintenanceOrder',
  data() {
    return {
      loading: true,
      showSearch: true,
      ids: [],
      multiple: true,
      activeStatus: '',
      total: 0,
      orderList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 20,
        projectId: null,
        status: '',
        priority: '',
        maintType: '',
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
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows || []
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
        status: '',
        priority: '',
        maintType: '',
        keyword: ''
      }
      this.activeStatus = ''
      this.handleQuery()
    },
    handleStatusTabClick(tab) {
      this.queryParams.status = tab.name
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.multiple = !selection.length
    },
    handleAdd() {
      this.$router.push('/maintenance/create')
    },
    handleEdit(row) {
      this.$router.push(`/maintenance/edit/${row.id}`)
    },
    handleView(row) {
      this.$router.push(`/maintenance/detail/${row.id}`)
    },
    handleAssign(row) {
      this.$prompt('请输入负责人姓名', '分配工单', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        assignOrder(row.id, { assignee: value }).then(() => {
          this.$message.success('分配成功')
          this.getList()
        })
      }).catch(() => {})
    },
    handleStart(row) {
      this.$confirm('确认开始处理该工单?', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        startOrder(row.id).then(() => {
          this.$message.success('工单已开始处理')
          this.getList()
        })
      }).catch(() => {})
    },
    handleComplete(row) {
      this.$prompt('请输入完成说明', '完成工单', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        completeOrder(row.id, { completionNote: value }).then(() => {
          this.$message.success('工单已完成')
          this.getList()
        })
      }).catch(() => {})
    },
    handleExport() {
      exportOrder(this.queryParams).then(() => {
        this.$message.success('导出成功')
      })
    },
    getStatusTagType(status) {
      const statusMap = {
        'pending': 'warning',
        'assigned': 'info',
        'in_progress': 'primary',
        'completed': 'success',
        'closed': '',
        'cancelled': 'danger'
      }
      return statusMap[status] || 'info'
    },
    getStatusText(status) {
      const statusMap = {
        'pending': '待分配',
        'assigned': '已分配',
        'in_progress': '进行中',
        'completed': '已完成',
        'closed': '已关闭',
        'cancelled': '已取消'
      }
      return statusMap[status] || status
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
    getMaintTypeText(type) {
      const typeMap = {
        'routine': '日常维护',
        'repair': '故障维修',
        'preventive': '预防性维护',
        'upgrade': '改造升级'
      }
      return typeMap[type] || type
    }
  }
}
</script>

<style scoped>
.maintenance-order-list { padding: 20px; }
.search-bar { margin-bottom: 20px; }
.action-bar { margin-bottom: 15px; }
.status-tabs { margin-bottom: 15px; }
.table-container { background: #fff; padding: 20px; border-radius: 4px; }
.empty-state { padding: 40px 0; }
</style>
