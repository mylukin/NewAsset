<template>
  <div class="app-container" v-loading="loading">
    <!-- Header Section -->
    <el-card class="header-card" v-if="order">
      <div class="header-content">
        <div class="header-left">
          <h2 class="order-title">
            <span class="order-no">{{ order.orderNo }}</span>
            <el-tag :type="getStatusTagType(order.status)" size="medium" class="status-badge">
              {{ order.statusLabel }}
            </el-tag>
            <el-tag :type="getPriorityTagType(order.priority)" size="medium" class="priority-badge">
              {{ order.priorityLabel }}
            </el-tag>
          </h2>
          <p class="order-subtitle">{{ order.title }}</p>
        </div>
        <div class="header-actions">
          <el-button
            type="primary"
            icon="el-icon-user"
            @click="handleAssign"
            v-if="order.status === 'WAIT_ASSIGN'"
            v-hasPermi="['asset:maint:assign']"
          >Assign</el-button>
          <el-button
            type="success"
            icon="el-icon-check"
            @click="handleAccept"
            v-if="order.status === 'WAIT_ACCEPT'"
            v-hasPermi="['asset:maint:accept']"
          >Accept</el-button>
          <el-button
            type="warning"
            icon="el-icon-close"
            @click="handleReject"
            v-if="order.status === 'WAIT_ACCEPT'"
            v-hasPermi="['asset:maint:reject']"
          >Reject</el-button>
          <el-button
            type="primary"
            icon="el-icon-finished"
            @click="handleComplete"
            v-if="order.status === 'PROCESSING'"
            v-hasPermi="['asset:maint:complete']"
          >Complete</el-button>
          <el-button
            type="success"
            icon="el-icon-circle-check"
            @click="handleConfirm"
            v-if="order.status === 'WAIT_CONFIRM'"
            v-hasPermi="['asset:maint:confirm']"
          >Confirm & Close</el-button>
          <el-button
            type="danger"
            icon="el-icon-circle-close"
            @click="handleCancel"
            v-if="['WAIT_ASSIGN', 'WAIT_ACCEPT'].includes(order.status)"
            v-hasPermi="['asset:maint:cancel']"
          >Cancel</el-button>
          <el-button icon="el-icon-back" @click="goBack">Back</el-button>
        </div>
      </div>
    </el-card>

    <el-row :gutter="20" v-if="order">
      <!-- Basic Info Section -->
      <el-col :span="16">
        <el-card class="info-card">
          <div slot="header">
            <span>Basic Information</span>
          </div>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="Order No">{{ order.orderNo }}</el-descriptions-item>
            <el-descriptions-item label="Status">
              <el-tag :type="getStatusTagType(order.status)" size="small">{{ order.statusLabel }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="Order Type">{{ order.orderTypeLabel }}</el-descriptions-item>
            <el-descriptions-item label="Fault Type">{{ order.faultTypeLabel || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Priority">
              <el-tag :type="getPriorityTagType(order.priority)" size="small">{{ order.priorityLabel }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="Required Finish">
              <span :class="{ 'overdue': isOverdue }">{{ parseTime(order.requireFinishTime) || '-' }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="Title" :span="2">{{ order.title }}</el-descriptions-item>
            <el-descriptions-item label="Description" :span="2">{{ order.description || '-' }}</el-descriptions-item>
          </el-descriptions>
        </el-card>

        <!-- Asset Info Section -->
        <el-card class="info-card">
          <div slot="header">
            <span>Related Asset</span>
          </div>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="Asset Code">
              <el-link type="primary" @click="goToAsset">{{ order.assetCode }}</el-link>
            </el-descriptions-item>
            <el-descriptions-item label="Asset Name">{{ order.assetName }}</el-descriptions-item>
            <el-descriptions-item label="Asset Type">{{ order.assetTypeLabel || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Project">{{ order.projectName }}</el-descriptions-item>
            <el-descriptions-item label="Location" :span="2">{{ order.assetLocation || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Original Status">{{ order.originAssetStatusLabel || '-' }}</el-descriptions-item>
          </el-descriptions>
        </el-card>

        <!-- Personnel Section -->
        <el-card class="info-card">
          <div slot="header">
            <span>Personnel Information</span>
          </div>
          <el-descriptions :column="2" border>
            <el-descriptions-item label="Requester">{{ order.requesterName || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Handler">{{ order.currentHandlerName || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Create By">{{ order.createBy }}</el-descriptions-item>
            <el-descriptions-item label="Create Time">{{ parseTime(order.createTime) }}</el-descriptions-item>
            <el-descriptions-item label="Update By">{{ order.updateBy || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Update Time">{{ parseTime(order.updateTime) || '-' }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>

      <!-- Timeline Section -->
      <el-col :span="8">
        <el-card class="timeline-card">
          <div slot="header">
            <span>Operation History</span>
          </div>
          <el-timeline v-if="order.maintLogs && order.maintLogs.length">
            <el-timeline-item
              v-for="log in order.maintLogs"
              :key="log.id"
              :timestamp="parseTime(log.opTime)"
              :type="getTimelineItemType(log.operationType)"
              placement="top"
            >
              <el-card shadow="never" class="timeline-card-item">
                <div class="timeline-header">
                  <span class="operation-type">{{ log.operationTypeLabel }}</span>
                </div>
                <div class="timeline-status" v-if="log.fromStatus || log.toStatus">
                  <el-tag size="mini" v-if="log.fromStatusLabel">{{ log.fromStatusLabel }}</el-tag>
                  <i class="el-icon-arrow-right" v-if="log.fromStatus && log.toStatus"></i>
                  <el-tag size="mini" type="success" v-if="log.toStatusLabel">{{ log.toStatusLabel }}</el-tag>
                </div>
                <div class="timeline-operator">
                  <i class="el-icon-user"></i> {{ log.operatorName }}
                </div>
                <div class="timeline-content" v-if="log.content">
                  {{ log.content }}
                </div>
              </el-card>
            </el-timeline-item>
          </el-timeline>
          <el-empty v-else description="No operation history"></el-empty>
        </el-card>
      </el-col>
    </el-row>

    <!-- Assign Dialog -->
    <el-dialog title="Assign Order" :visible.sync="assignDialogVisible" width="400px" append-to-body>
      <el-form :model="workflowForm" label-width="80px">
        <el-form-item label="Handler" required>
          <el-select v-model="workflowForm.handlerId" placeholder="Select handler" style="width: 100%">
            <el-option
              v-for="item in userOptions"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Remark">
          <el-input v-model="workflowForm.remark" type="textarea" :rows="2" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="assignDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitAssign">Confirm</el-button>
      </div>
    </el-dialog>

    <!-- Reason Dialog -->
    <el-dialog :title="reasonDialogTitle" :visible.sync="reasonDialogVisible" width="400px" append-to-body>
      <el-form :model="reasonForm" label-width="80px">
        <el-form-item :label="reasonLabel" required>
          <el-input v-model="reasonForm.reason" type="textarea" :rows="3" :placeholder="reasonPlaceholder" />
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
  getMaintOrder,
  assignMaintOrder,
  acceptMaintOrder,
  rejectMaintOrder,
  completeMaintOrder,
  confirmMaintOrder,
  cancelMaintOrder
} from '@/api/asset/maintOrder'

export default {
  name: 'MaintOrderDetail',
  data() {
    return {
      loading: true,
      orderId: null,
      order: null,
      userOptions: [],
      assignDialogVisible: false,
      reasonDialogVisible: false,
      reasonDialogTitle: '',
      reasonLabel: 'Reason',
      reasonPlaceholder: 'Enter reason',
      workflowForm: {
        handlerId: null,
        remark: ''
      },
      reasonForm: {
        reason: '',
        action: ''
      }
    }
  },
  computed: {
    isOverdue() {
      if (!this.order || !this.order.requireFinishTime) return false
      return new Date(this.order.requireFinishTime) < new Date() &&
             !['CLOSED', 'CANCELED'].includes(this.order.status)
    }
  },
  created() {
    this.orderId = this.$route.params.id
    this.getDetail()
    this.loadUserOptions()
  },
  methods: {
    getDetail() {
      this.loading = true
      getMaintOrder(this.orderId).then(response => {
        this.order = response.data
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    loadUserOptions() {
      // Load users for assignment - would come from API
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
    getTimelineItemType(operationType) {
      const map = {
        'CREATE': 'primary',
        'ASSIGN': 'info',
        'ACCEPT': 'success',
        'REJECT': 'warning',
        'START': 'primary',
        'COMPLETE': 'success',
        'CONFIRM': 'success',
        'CANCEL': 'danger'
      }
      return map[operationType] || ''
    },
    goBack() {
      this.$router.push('/asset/maint/orderList')
    },
    goToAsset() {
      if (this.order && this.order.assetId) {
        // Navigate to asset detail based on asset type
        this.$router.push(`/asset/detail/${this.order.assetId}`)
      }
    },
    handleAssign() {
      this.workflowForm.handlerId = null
      this.workflowForm.remark = ''
      this.assignDialogVisible = true
    },
    submitAssign() {
      if (!this.workflowForm.handlerId) {
        this.$modal.msgError('Please select a handler')
        return
      }
      assignMaintOrder(this.orderId, this.workflowForm.handlerId, this.workflowForm.remark).then(() => {
        this.$modal.msgSuccess('Order assigned successfully')
        this.assignDialogVisible = false
        this.getDetail()
      })
    },
    handleAccept() {
      this.$modal.confirm('Are you sure to accept this order?').then(() => {
        return acceptMaintOrder(this.orderId, '')
      }).then(() => {
        this.$modal.msgSuccess('Order accepted')
        this.getDetail()
      }).catch(() => {})
    },
    handleReject() {
      this.reasonForm.reason = ''
      this.reasonForm.action = 'reject'
      this.reasonDialogTitle = 'Reject Order'
      this.reasonLabel = 'Reason'
      this.reasonPlaceholder = 'Enter rejection reason'
      this.reasonDialogVisible = true
    },
    handleComplete() {
      this.reasonForm.reason = ''
      this.reasonForm.action = 'complete'
      this.reasonDialogTitle = 'Complete Order'
      this.reasonLabel = 'Result'
      this.reasonPlaceholder = 'Enter completion result'
      this.reasonDialogVisible = true
    },
    handleConfirm() {
      this.reasonForm.reason = ''
      this.reasonForm.action = 'confirm'
      this.reasonDialogTitle = 'Confirm & Close Order'
      this.reasonLabel = 'Feedback'
      this.reasonPlaceholder = 'Enter feedback (optional)'
      this.reasonDialogVisible = true
    },
    handleCancel() {
      this.reasonForm.reason = ''
      this.reasonForm.action = 'cancel'
      this.reasonDialogTitle = 'Cancel Order'
      this.reasonLabel = 'Reason'
      this.reasonPlaceholder = 'Enter cancellation reason'
      this.reasonDialogVisible = true
    },
    submitReason() {
      const action = this.reasonForm.action
      if (['reject', 'cancel'].includes(action) && !this.reasonForm.reason) {
        this.$modal.msgError('Reason is required')
        return
      }

      let apiCall
      switch (action) {
        case 'reject':
          apiCall = rejectMaintOrder(this.orderId, this.reasonForm.reason)
          break
        case 'complete':
          apiCall = completeMaintOrder(this.orderId, this.reasonForm.reason)
          break
        case 'confirm':
          apiCall = confirmMaintOrder(this.orderId, this.reasonForm.reason)
          break
        case 'cancel':
          apiCall = cancelMaintOrder(this.orderId, this.reasonForm.reason)
          break
      }

      apiCall.then(() => {
        this.$modal.msgSuccess('Operation successful')
        this.reasonDialogVisible = false
        this.getDetail()
      })
    }
  }
}
</script>

<style scoped>
.header-card {
  margin-bottom: 20px;
}
.header-content {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}
.header-left {
  flex: 1;
}
.order-title {
  margin: 0 0 10px 0;
  display: flex;
  align-items: center;
  gap: 10px;
}
.order-no {
  font-size: 24px;
  font-weight: bold;
}
.order-subtitle {
  color: #606266;
  margin: 0;
}
.header-actions {
  display: flex;
  gap: 10px;
}
.info-card {
  margin-bottom: 20px;
}
.timeline-card {
  height: 100%;
}
.timeline-card-item {
  padding: 10px;
}
.timeline-header {
  margin-bottom: 5px;
}
.operation-type {
  font-weight: bold;
  color: #303133;
}
.timeline-status {
  margin: 5px 0;
  display: flex;
  align-items: center;
  gap: 5px;
}
.timeline-operator {
  color: #909399;
  font-size: 12px;
  margin: 5px 0;
}
.timeline-content {
  color: #606266;
  font-size: 13px;
  background: #f5f7fa;
  padding: 8px;
  border-radius: 4px;
  margin-top: 5px;
}
.overdue {
  color: #f56c6c;
  font-weight: bold;
}
</style>
