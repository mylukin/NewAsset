<template>
  <div class="maintenance-order-detail">
    <el-drawer
      :title="`维修工单详情 - ${orderDetail.orderNo}`"
      :visible.sync="drawerVisible"
      direction="rtl"
      :size="800"
      :before-close="handleClose"
    >
      <div class="detail-container">
        <!-- Header -->
        <div class="detail-header">
          <div class="header-left">
            <h2 class="order-no">{{ orderDetail.orderNo }}</h2>
            <h3 class="asset-info">{{ orderDetail.assetName }} ({{ orderDetail.assetCode }})</h3>
          </div>
          <div class="header-right">
            <el-tag :type="getStatusTagType(orderDetail.status)" size="large">
              {{ getStatusText(orderDetail.status) }}
            </el-tag>
          </div>
        </div>

        <!-- Header Actions -->
        <div class="header-actions">
          <el-button type="primary" icon="el-icon-edit" v-hasPermi="['asset:maint:edit']" @click="handleEdit" v-if="orderDetail.status === 'pending'">编辑</el-button>
          <el-button type="success" icon="el-icon-user" v-hasPermi="['asset:maint:assign']" @click="handleAssign" v-if="orderDetail.status === 'pending'">分配</el-button>
          <el-button type="warning" icon="el-icon-video-play" v-hasPermi="['asset:maint:edit']" @click="handleStart" v-if="orderDetail.status === 'assigned'">开始处理</el-button>
          <el-button type="success" icon="el-icon-check" v-hasPermi="['asset:maint:edit']" @click="handleComplete" v-if="orderDetail.status === 'in_progress'">完成</el-button>
          <el-button type="info" icon="el-icon-circle-close" v-hasPermi="['asset:maint:edit']" @click="handleClose" v-if="orderDetail.status === 'completed'">关闭</el-button>
          <el-button type="danger" icon="el-icon-close" v-hasPermi="['asset:maint:edit']" @click="handleCancel" v-if="['pending', 'assigned'].includes(orderDetail.status)">取消</el-button>
        </div>

        <!-- Tabs -->
        <el-tabs v-model="activeTab" class="detail-tabs">
          <!-- Tab 1: Basic Info -->
          <el-tab-pane label="工单信息" name="basic">
            <div class="tab-content">
              <el-descriptions :column="2" border>
                <el-descriptions-item label="工单编号">{{ orderDetail.orderNo }}</el-descriptions-item>
                <el-descriptions-item label="状态">
                  <el-tag :type="getStatusTagType(orderDetail.status)">
                    {{ getStatusText(orderDetail.status) }}
                  </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="优先级">
                  <el-tag :type="getPriorityTagType(orderDetail.priority)">
                    {{ getPriorityText(orderDetail.priority) }}
                  </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="维修类型">{{ getMaintTypeText(orderDetail.maintType) }}</el-descriptions-item>
                <el-descriptions-item label="创建时间">{{ orderDetail.createTime }}</el-descriptions-item>
                <el-descriptions-item label="创建人">{{ orderDetail.createBy }}</el-descriptions-item>
              </el-descriptions>

              <h4 class="section-title">资产信息</h4>
              <el-descriptions :column="2" border>
                <el-descriptions-item label="资产编码">{{ orderDetail.assetCode }}</el-descriptions-item>
                <el-descriptions-item label="资产名称">{{ orderDetail.assetName }}</el-descriptions-item>
                <el-descriptions-item label="资产类型">{{ orderDetail.assetType }}</el-descriptions-item>
                <el-descriptions-item label="所属项目">{{ orderDetail.projectName }}</el-descriptions-item>
                <el-descriptions-item label="位置" span="2">{{ orderDetail.location }}</el-descriptions-item>
              </el-descriptions>

              <h4 class="section-title">处理信息</h4>
              <el-descriptions :column="2" border>
                <el-descriptions-item label="负责人">{{ orderDetail.assignee || '-' }}</el-descriptions-item>
                <el-descriptions-item label="分配时间">{{ orderDetail.assignTime || '-' }}</el-descriptions-item>
                <el-descriptions-item label="开始时间">{{ orderDetail.startTime || '-' }}</el-descriptions-item>
                <el-descriptions-item label="完成时间">{{ orderDetail.completeTime || '-' }}</el-descriptions-item>
              </el-descriptions>

              <h4 class="section-title">问题描述</h4>
              <div class="description-content">
                {{ orderDetail.description || '无' }}
              </div>

              <h4 class="section-title" v-if="orderDetail.completionNote">完成说明</h4>
              <div class="description-content" v-if="orderDetail.completionNote">
                {{ orderDetail.completionNote }}
              </div>
            </div>
          </el-tab-pane>

          <!-- Tab 2: Progress Timeline -->
          <el-tab-pane label="处理进度" name="timeline">
            <div class="tab-content">
              <el-timeline>
                <el-timeline-item
                  v-for="(item, index) in progressList"
                  :key="index"
                  :timestamp="item.time"
                  :type="item.type"
                  placement="top"
                >
                  <el-card>
                    <h4>{{ item.title }}</h4>
                    <p>{{ item.content }}</p>
                    <p class="operator">操作人: {{ item.operator }}</p>
                  </el-card>
                </el-timeline-item>
              </el-timeline>
              <div v-if="progressList.length === 0" class="empty-data">
                <el-empty description="暂无处理记录" :image-size="100" />
              </div>
            </div>
          </el-tab-pane>

          <!-- Tab 3: Attachments -->
          <el-tab-pane label="附件" name="attachments">
            <div class="tab-content">
              <el-upload class="upload-demo" drag action="#" multiple :show-file-list="false" v-hasPermi="['asset:maint:edit']">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              </el-upload>
              <div v-if="attachmentList.length === 0" class="empty-data">
                <el-empty description="暂无附件" :image-size="100" />
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { getOrder, assignOrder, startOrder, completeOrder, closeOrder, cancelOrder } from '@/api/maintenance/order'

export default {
  name: 'MaintenanceOrderDetail',
  data() {
    return {
      drawerVisible: true,
      activeTab: 'basic',
      orderDetail: {},
      progressList: [],
      attachmentList: []
    }
  },
  created() {
    const orderId = this.$route.params.id
    if (orderId) {
      this.getOrderDetail(orderId)
    }
  },
  methods: {
    getOrderDetail(id) {
      getOrder(id).then(response => {
        this.orderDetail = response.data || {}
        this.progressList = []
        this.attachmentList = []
      })
    },
    handleClose() {
      this.$router.push('/maintenance')
    },
    handleEdit() {
      this.$router.push(`/maintenance/edit/${this.orderDetail.id}`)
    },
    handleAssign() {
      this.$prompt('请输入负责人姓名', '分配工单', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        assignOrder(this.orderDetail.id, { assignee: value }).then(() => {
          this.$message.success('分配成功')
          this.getOrderDetail(this.orderDetail.id)
        })
      }).catch(() => {})
    },
    handleStart() {
      this.$confirm('确认开始处理该工单?', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        startOrder(this.orderDetail.id).then(() => {
          this.$message.success('工单已开始处理')
          this.getOrderDetail(this.orderDetail.id)
        })
      }).catch(() => {})
    },
    handleComplete() {
      this.$prompt('请输入完成说明', '完成工单', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        completeOrder(this.orderDetail.id, { completionNote: value }).then(() => {
          this.$message.success('工单已完成')
          this.getOrderDetail(this.orderDetail.id)
        })
      }).catch(() => {})
    },
    handleCloseOrder() {
      this.$confirm('确认关闭该工单?', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        closeOrder(this.orderDetail.id).then(() => {
          this.$message.success('工单已关闭')
          this.getOrderDetail(this.orderDetail.id)
        })
      }).catch(() => {})
    },
    handleCancel() {
      this.$prompt('请输入取消原因', '取消工单', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        cancelOrder(this.orderDetail.id, value).then(() => {
          this.$message.success('工单已取消')
          this.getOrderDetail(this.orderDetail.id)
        })
      }).catch(() => {})
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
.detail-container { padding: 0 20px 20px; }
.detail-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; padding-bottom: 20px; border-bottom: 1px solid #e4e7ed; }
.header-left .order-no { font-size: 24px; font-weight: bold; color: #303133; margin: 0 0 5px 0; }
.header-left .asset-info { font-size: 16px; color: #606266; margin: 0; }
.header-actions { margin-bottom: 20px; }
.header-actions .el-button { margin-right: 10px; }
.tab-content { padding: 20px 0; }
.section-title { margin: 30px 0 15px 0; padding-bottom: 10px; border-bottom: 1px solid #e4e7ed; font-size: 16px; color: #303133; }
.description-content { padding: 15px; background: #f5f7fa; border-radius: 4px; line-height: 1.6; }
.empty-data { padding: 40px 0; text-align: center; }
.operator { font-size: 12px; color: #909399; margin-top: 5px; }
</style>
