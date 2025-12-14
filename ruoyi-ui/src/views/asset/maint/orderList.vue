<template>
  <div class="app-container">
    <!-- Search Form -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="Order No" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="Enter order number"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Project" prop="projectId">
        <el-select v-model="queryParams.projectId" placeholder="Select project" clearable>
          <el-option
            v-for="item in projectOptions"
            :key="item.id"
            :label="item.projectName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Order Type" prop="orderType">
        <el-select v-model="queryParams.orderType" placeholder="Select type" clearable>
          <el-option
            v-for="dict in dict.type.maint_order_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Status" prop="status">
        <el-select v-model="queryParams.status" placeholder="Select status" clearable>
          <el-option
            v-for="dict in dict.type.maint_order_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Priority" prop="priority">
        <el-select v-model="queryParams.priority" placeholder="Select priority" clearable>
          <el-option
            v-for="dict in dict.type.maint_priority"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Create Time" prop="createTimeRange">
        <el-date-picker
          v-model="createTimeRange"
          type="daterange"
          range-separator="-"
          start-placeholder="Start"
          end-placeholder="End"
          value-format="yyyy-MM-dd"
          @change="handleDateRangeChange"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">Search</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">Reset</el-button>
      </el-form-item>
    </el-form>

    <!-- Toolbar -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['asset:maint:add']"
        >Create</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['asset:maint:remove']"
        >Delete</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:maint:export']"
        >Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- Data Table -->
    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange" @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Order No" align="center" prop="orderNo" width="160" />
      <el-table-column label="Asset Name" align="center" prop="assetName" :show-overflow-tooltip="true" />
      <el-table-column label="Project" align="center" prop="projectName" width="120" :show-overflow-tooltip="true" />
      <el-table-column label="Type" align="center" prop="orderTypeLabel" width="100" />
      <el-table-column label="Title" align="center" prop="title" :show-overflow-tooltip="true" />
      <el-table-column label="Priority" align="center" prop="priority" width="90">
        <template slot-scope="scope">
          <el-tag :type="getPriorityTagType(scope.row.priority)" size="small">
            {{ scope.row.priorityLabel }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Status" align="center" prop="status" width="100">
        <template slot-scope="scope">
          <el-tag :type="getStatusTagType(scope.row.status)" size="small">
            {{ scope.row.statusLabel }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Handler" align="center" prop="currentHandlerName" width="100" />
      <el-table-column label="Requester" align="center" prop="requesterName" width="100" />
      <el-table-column label="Required Finish" align="center" prop="requireFinishTime" width="110" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.requireFinishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Create Time" align="center" prop="createTime" width="160" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" class-name="small-padding fixed-width" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['asset:maint:query']"
          >View</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-user"
            @click="handleAssign(scope.row)"
            v-if="scope.row.status === 'WAIT_ASSIGN'"
            v-hasPermi="['asset:maint:assign']"
          >Assign</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleAccept(scope.row)"
            v-if="scope.row.status === 'WAIT_ACCEPT'"
            v-hasPermi="['asset:maint:accept']"
          >Accept</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-close"
            @click="handleReject(scope.row)"
            v-if="scope.row.status === 'WAIT_ACCEPT'"
            v-hasPermi="['asset:maint:reject']"
          >Reject</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-finished"
            @click="handleComplete(scope.row)"
            v-if="scope.row.status === 'PROCESSING'"
            v-hasPermi="['asset:maint:complete']"
          >Complete</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-check"
            @click="handleConfirm(scope.row)"
            v-if="scope.row.status === 'WAIT_CONFIRM'"
            v-hasPermi="['asset:maint:confirm']"
          >Confirm</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-close"
            @click="handleCancel(scope.row)"
            v-if="['WAIT_ASSIGN', 'WAIT_ACCEPT'].includes(scope.row.status)"
            v-hasPermi="['asset:maint:cancel']"
          >Cancel</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- Pagination -->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- Create/Edit Dialog -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="Project" prop="projectId">
          <el-select v-model="form.projectId" placeholder="Select project" style="width: 100%">
            <el-option
              v-for="item in projectOptions"
              :key="item.id"
              :label="item.projectName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Asset" prop="assetId">
          <el-select v-model="form.assetId" placeholder="Select asset" filterable style="width: 100%">
            <el-option
              v-for="item in assetOptions"
              :key="item.id"
              :label="`${item.assetCode} - ${item.assetName}`"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Order Type" prop="orderType">
          <el-select v-model="form.orderType" placeholder="Select type" style="width: 100%">
            <el-option
              v-for="dict in dict.type.maint_order_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Fault Type" prop="faultType" v-if="form.orderType === 'FAULT'">
          <el-select v-model="form.faultType" placeholder="Select fault type" style="width: 100%">
            <el-option
              v-for="dict in dict.type.maint_fault_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Title" prop="title">
          <el-input v-model="form.title" placeholder="Enter order title" />
        </el-form-item>
        <el-form-item label="Description" prop="description">
          <el-input v-model="form.description" type="textarea" :rows="3" placeholder="Enter description" />
        </el-form-item>
        <el-form-item label="Priority" prop="priority">
          <el-select v-model="form.priority" placeholder="Select priority" style="width: 100%">
            <el-option
              v-for="dict in dict.type.maint_priority"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Required Finish" prop="requireFinishTime">
          <el-date-picker
            v-model="form.requireFinishTime"
            type="datetime"
            placeholder="Select required finish time"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="Assign To" prop="handlerId">
          <el-select v-model="form.handlerId" placeholder="Select handler (optional)" clearable style="width: 100%">
            <el-option
              v-for="item in userOptions"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">Submit</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </div>
    </el-dialog>

    <!-- Detail Drawer -->
    <el-drawer :title="'Order Details'" :visible.sync="drawerVisible" size="50%">
      <div class="drawer-content" v-if="currentOrder">
        <el-descriptions :column="2" border>
          <el-descriptions-item label="Order No">{{ currentOrder.orderNo }}</el-descriptions-item>
          <el-descriptions-item label="Status">
            <el-tag :type="getStatusTagType(currentOrder.status)" size="small">
              {{ currentOrder.statusLabel }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="Asset Code">{{ currentOrder.assetCode }}</el-descriptions-item>
          <el-descriptions-item label="Asset Name">{{ currentOrder.assetName }}</el-descriptions-item>
          <el-descriptions-item label="Project">{{ currentOrder.projectName }}</el-descriptions-item>
          <el-descriptions-item label="Order Type">{{ currentOrder.orderTypeLabel }}</el-descriptions-item>
          <el-descriptions-item label="Fault Type">{{ currentOrder.faultTypeLabel || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Priority">
            <el-tag :type="getPriorityTagType(currentOrder.priority)" size="small">
              {{ currentOrder.priorityLabel }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="Title" :span="2">{{ currentOrder.title }}</el-descriptions-item>
          <el-descriptions-item label="Description" :span="2">{{ currentOrder.description || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Handler">{{ currentOrder.currentHandlerName || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Requester">{{ currentOrder.requesterName || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Required Finish">{{ parseTime(currentOrder.requireFinishTime) || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Create Time">{{ parseTime(currentOrder.createTime) }}</el-descriptions-item>
        </el-descriptions>

        <!-- Timeline for logs -->
        <div class="section-title" style="margin-top: 20px;">Operation History</div>
        <el-timeline v-if="currentOrder.maintLogs && currentOrder.maintLogs.length">
          <el-timeline-item
            v-for="log in currentOrder.maintLogs"
            :key="log.id"
            :timestamp="parseTime(log.opTime)"
            placement="top"
          >
            <el-card>
              <h4>{{ log.operationTypeLabel }}</h4>
              <p v-if="log.fromStatus">{{ log.fromStatusLabel }} -> {{ log.toStatusLabel }}</p>
              <p>Operator: {{ log.operatorName }}</p>
              <p v-if="log.content">{{ log.content }}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
        <el-empty v-else description="No operation history"></el-empty>
      </div>
    </el-drawer>

    <!-- Assign Dialog -->
    <el-dialog title="Assign Order" :visible.sync="assignDialogVisible" width="400px" append-to-body>
      <el-form :model="assignForm" label-width="80px">
        <el-form-item label="Handler" prop="handlerId">
          <el-select v-model="assignForm.handlerId" placeholder="Select handler" style="width: 100%">
            <el-option
              v-for="item in userOptions"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Remark">
          <el-input v-model="assignForm.remark" type="textarea" :rows="2" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="assignDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitAssign">Confirm</el-button>
      </div>
    </el-dialog>

    <!-- Reason Dialog (for reject/cancel) -->
    <el-dialog :title="reasonDialogTitle" :visible.sync="reasonDialogVisible" width="400px" append-to-body>
      <el-form :model="reasonForm" label-width="80px">
        <el-form-item label="Reason">
          <el-input v-model="reasonForm.reason" type="textarea" :rows="3" placeholder="Enter reason" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="reasonDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitReason">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getMaintOrderList,
  getMaintOrder,
  addMaintOrder,
  deleteMaintOrder,
  assignMaintOrder,
  acceptMaintOrder,
  rejectMaintOrder,
  completeMaintOrder,
  confirmMaintOrder,
  cancelMaintOrder,
  exportMaintOrder
} from '@/api/asset/maintOrder'

export default {
  name: 'MaintOrderList',
  dicts: ['maint_order_type', 'maint_order_status', 'maint_fault_type', 'maint_priority'],
  data() {
    return {
      loading: true,
      showSearch: true,
      ids: [],
      single: true,
      multiple: true,
      total: 0,
      orderList: [],
      title: '',
      open: false,
      drawerVisible: false,
      currentOrder: null,
      createTimeRange: [],
      projectOptions: [],
      assetOptions: [],
      userOptions: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNo: null,
        projectId: null,
        orderType: null,
        status: null,
        priority: null,
        createTimeStart: null,
        createTimeEnd: null
      },
      form: {},
      rules: {
        projectId: [{ required: true, message: 'Project is required', trigger: 'change' }],
        assetId: [{ required: true, message: 'Asset is required', trigger: 'change' }],
        orderType: [{ required: true, message: 'Order type is required', trigger: 'change' }],
        title: [{ required: true, message: 'Title is required', trigger: 'blur' }],
        priority: [{ required: true, message: 'Priority is required', trigger: 'change' }]
      },
      assignDialogVisible: false,
      assignForm: {
        orderId: null,
        handlerId: null,
        remark: ''
      },
      reasonDialogVisible: false,
      reasonDialogTitle: '',
      reasonForm: {
        orderId: null,
        reason: '',
        action: ''
      }
    }
  },
  created() {
    this.getList()
    this.loadOptions()
  },
  methods: {
    getList() {
      this.loading = true
      getMaintOrderList(this.queryParams).then(response => {
        this.orderList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    loadOptions() {
      // Load project options, asset options, user options
      // These would normally come from API calls
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
    handleDateRangeChange(val) {
      if (val) {
        this.queryParams.createTimeStart = val[0]
        this.queryParams.createTimeEnd = val[1]
      } else {
        this.queryParams.createTimeStart = null
        this.queryParams.createTimeEnd = null
      }
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.createTimeRange = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    handleSortChange({ prop, order }) {
      // Handle sort change
      this.getList()
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = 'Create Maintenance Order'
    },
    handleView(row) {
      getMaintOrder(row.id).then(response => {
        this.currentOrder = response.data
        this.drawerVisible = true
      })
    },
    handleAssign(row) {
      this.assignForm.orderId = row.id
      this.assignForm.handlerId = null
      this.assignForm.remark = ''
      this.assignDialogVisible = true
    },
    submitAssign() {
      if (!this.assignForm.handlerId) {
        this.$modal.msgError('Please select a handler')
        return
      }
      assignMaintOrder(this.assignForm.orderId, this.assignForm.handlerId, this.assignForm.remark).then(() => {
        this.$modal.msgSuccess('Order assigned successfully')
        this.assignDialogVisible = false
        this.getList()
      })
    },
    handleAccept(row) {
      this.$modal.confirm('Are you sure to accept this order?').then(() => {
        return acceptMaintOrder(row.id, '')
      }).then(() => {
        this.$modal.msgSuccess('Order accepted')
        this.getList()
      }).catch(() => {})
    },
    handleReject(row) {
      this.reasonForm.orderId = row.id
      this.reasonForm.reason = ''
      this.reasonForm.action = 'reject'
      this.reasonDialogTitle = 'Reject Order'
      this.reasonDialogVisible = true
    },
    handleComplete(row) {
      this.$modal.confirm('Are you sure to complete this order?').then(() => {
        return completeMaintOrder(row.id, '')
      }).then(() => {
        this.$modal.msgSuccess('Order completed')
        this.getList()
      }).catch(() => {})
    },
    handleConfirm(row) {
      this.$modal.confirm('Are you sure to confirm and close this order?').then(() => {
        return confirmMaintOrder(row.id, '')
      }).then(() => {
        this.$modal.msgSuccess('Order closed')
        this.getList()
      }).catch(() => {})
    },
    handleCancel(row) {
      this.reasonForm.orderId = row.id
      this.reasonForm.reason = ''
      this.reasonForm.action = 'cancel'
      this.reasonDialogTitle = 'Cancel Order'
      this.reasonDialogVisible = true
    },
    submitReason() {
      if (!this.reasonForm.reason) {
        this.$modal.msgError('Please enter a reason')
        return
      }
      const action = this.reasonForm.action === 'reject' ? rejectMaintOrder : cancelMaintOrder
      action(this.reasonForm.orderId, this.reasonForm.reason).then(() => {
        this.$modal.msgSuccess(`Order ${this.reasonForm.action}ed successfully`)
        this.reasonDialogVisible = false
        this.getList()
      })
    },
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('Are you sure to delete the selected orders?').then(() => {
        return deleteMaintOrder(ids)
      }).then(() => {
        this.$modal.msgSuccess('Deleted successfully')
        this.getList()
      }).catch(() => {})
    },
    handleExport() {
      this.download('/asset/maint/order/export', { ...this.queryParams }, `maint_orders_${new Date().getTime()}.xlsx`)
    },
    reset() {
      this.form = {
        projectId: null,
        assetId: null,
        orderType: null,
        faultType: null,
        title: '',
        description: '',
        priority: 'NORMAL',
        requireFinishTime: null,
        handlerId: null
      }
      this.resetForm('form')
    },
    cancel() {
      this.open = false
      this.reset()
    },
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          addMaintOrder(this.form).then(() => {
            this.$modal.msgSuccess('Order created successfully')
            this.open = false
            this.getList()
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.drawer-content {
  padding: 20px;
}
.section-title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #303133;
}
</style>
