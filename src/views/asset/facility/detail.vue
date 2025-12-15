<template>
  <div class="facility-asset-detail">
    <el-drawer
      :title="`设施资产详情 - ${facilityDetail.name || facilityDetail.assetCode}`"
      :visible.sync="drawerVisible"
      direction="rtl"
      :size="800"
      :before-close="handleClose"
    >
      <div class="detail-container">
        <!-- Header -->
        <div class="detail-header">
          <div class="header-left">
            <h2 class="asset-code">{{ facilityDetail.assetCode }}</h2>
            <h3 class="asset-name">{{ facilityDetail.name }}</h3>
          </div>
          <div class="header-right">
            <el-tag :type="getStatusTagType(facilityDetail.status)" size="large">
              {{ getStatusText(facilityDetail.status) }}
            </el-tag>
          </div>
        </div>

        <!-- Header Actions -->
        <div class="header-actions">
          <el-button type="primary" icon="el-icon-edit" v-hasPermi="['asset:facility:edit']" @click="handleEdit">编辑</el-button>
          <el-button type="warning" icon="el-icon-wrench" v-hasPermi="['asset:maint:add']" @click="handleMaintenance">发起维护</el-button>
          <el-button type="danger" icon="el-icon-delete" v-hasPermi="['asset:facility:remove']" @click="handleDelete">删除</el-button>
        </div>

        <!-- Tabs -->
        <el-tabs v-model="activeTab" class="detail-tabs">
          <!-- Tab 1: Basic Info -->
          <el-tab-pane label="基本信息" name="basic">
            <div class="tab-content">
              <el-descriptions :column="2" border>
                <el-descriptions-item label="资产编码">{{ facilityDetail.assetCode }}</el-descriptions-item>
                <el-descriptions-item label="资产名称">{{ facilityDetail.name }}</el-descriptions-item>
                <el-descriptions-item label="资产类型">
                  <el-tag size="small">设施设备</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="状态">
                  <el-tag :type="getStatusTagType(facilityDetail.status)">
                    {{ getStatusText(facilityDetail.status) }}
                  </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="项目" span="2">{{ facilityDetail.projectName }}</el-descriptions-item>
                <el-descriptions-item label="楼宇">{{ facilityDetail.buildingName }}</el-descriptions-item>
                <el-descriptions-item label="位置描述">{{ facilityDetail.locationDesc || '-' }}</el-descriptions-item>
                <el-descriptions-item label="负责人">{{ facilityDetail.responsiblePerson || '-' }}</el-descriptions-item>
                <el-descriptions-item label="联系电话">{{ facilityDetail.responsiblePhone || '-' }}</el-descriptions-item>
              </el-descriptions>

              <!-- Facility-specific fields -->
              <h4 class="section-title">设备信息</h4>
              <el-descriptions :column="2" border>
                <el-descriptions-item label="设备类型">{{ getFacilityTypeText(facilityDetail.facilityType) || '-' }}</el-descriptions-item>
                <el-descriptions-item label="品牌">{{ facilityDetail.brand || '-' }}</el-descriptions-item>
                <el-descriptions-item label="型号">{{ facilityDetail.model || '-' }}</el-descriptions-item>
                <el-descriptions-item label="序列号">{{ facilityDetail.serialNo || '-' }}</el-descriptions-item>
                <el-descriptions-item label="购置日期">{{ facilityDetail.purchaseDate || '-' }}</el-descriptions-item>
                <el-descriptions-item label="购置价格">{{ facilityDetail.purchasePrice ? facilityDetail.purchasePrice + '元' : '-' }}</el-descriptions-item>
                <el-descriptions-item label="保修到期">
                  <span :class="getWarrantyClass(facilityDetail.warrantyExpireDate)">
                    {{ facilityDetail.warrantyExpireDate || '-' }}
                  </span>
                </el-descriptions-item>
                <el-descriptions-item label="供应商">{{ facilityDetail.supplier || '-' }}</el-descriptions-item>
                <el-descriptions-item label="维护周期">{{ facilityDetail.maintenanceCycle ? facilityDetail.maintenanceCycle + '天' : '-' }}</el-descriptions-item>
                <el-descriptions-item label="下次维护">{{ facilityDetail.nextMaintenanceDate || '-' }}</el-descriptions-item>
              </el-descriptions>
            </div>
          </el-tab-pane>

          <!-- Tab 2: Maintenance Records -->
          <el-tab-pane label="维护记录" name="maintenance">
            <div class="tab-content">
              <div class="tab-header">
                <el-button type="primary" icon="el-icon-plus" v-hasPermi="['asset:maint:add']" @click="handleCreateMaintenance">创建工单</el-button>
              </div>
              <el-table :data="maintenanceList" stripe style="width: 100%">
                <el-table-column type="index" label="序号" width="80" align="center" />
                <el-table-column prop="orderNo" label="工单编号" width="180" align="center" />
                <el-table-column prop="title" label="工单标题" align="center" />
                <el-table-column prop="status" label="状态" width="120" align="center">
                  <template slot-scope="scope">
                    <el-tag :type="getMaintenanceStatusTagType(scope.row.status)">
                      {{ getMaintenanceStatusText(scope.row.status) }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="180" align="center" />
              </el-table>
              <div v-if="maintenanceList.length === 0" class="empty-data">
                <el-empty description="暂无维护记录" :image-size="100" />
              </div>
            </div>
          </el-tab-pane>

          <!-- Tab 3: Attachments -->
          <el-tab-pane label="附件" name="attachments">
            <div class="tab-content">
              <el-upload class="upload-demo" drag action="#" multiple :show-file-list="false" v-hasPermi="['asset:facility:edit']">
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
import { getFacility } from '@/api/asset/facility'

export default {
  name: 'FacilityAssetDetail',
  data() {
    return {
      drawerVisible: true,
      activeTab: 'basic',
      facilityDetail: {},
      maintenanceList: [],
      attachmentList: []
    }
  },
  created() {
    const assetId = this.$route.params.id
    if (assetId) {
      this.getFacilityDetail(assetId)
    }
  },
  methods: {
    getFacilityDetail(id) {
      getFacility(id).then(response => {
        this.facilityDetail = response.data || {}
        this.maintenanceList = []
        this.attachmentList = []
      })
    },
    handleClose() {
      this.$router.push('/asset/facility')
    },
    handleEdit() {
      this.$router.push(`/asset/facility/edit/${this.facilityDetail.id}`)
    },
    handleMaintenance() {
      this.$router.push(`/maintenance/create?assetId=${this.facilityDetail.id}`)
    },
    handleDelete() {
      this.$confirm('是否确认删除该资产?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message.success('删除成功')
        this.handleClose()
      })
    },
    handleCreateMaintenance() {
      this.$router.push(`/maintenance/create?assetId=${this.facilityDetail.id}`)
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
    },
    getMaintenanceStatusTagType(status) {
      const statusMap = {
        'pending': 'warning',
        'in_progress': 'primary',
        'completed': 'success',
        'cancelled': 'info'
      }
      return statusMap[status] || 'info'
    },
    getMaintenanceStatusText(status) {
      const statusMap = {
        'pending': '待处理',
        'in_progress': '处理中',
        'completed': '已完成',
        'cancelled': '已取消'
      }
      return statusMap[status] || status
    }
  }
}
</script>

<style scoped>
.detail-container { padding: 0 20px 20px; }
.detail-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; padding-bottom: 20px; border-bottom: 1px solid #e4e7ed; }
.header-left .asset-code { font-size: 24px; font-weight: bold; color: #303133; margin: 0 0 5px 0; }
.header-left .asset-name { font-size: 18px; color: #606266; margin: 0; }
.header-actions { margin-bottom: 20px; }
.header-actions .el-button { margin-right: 10px; }
.tab-content { padding: 20px 0; }
.section-title { margin: 30px 0 15px 0; padding-bottom: 10px; border-bottom: 1px solid #e4e7ed; font-size: 16px; color: #303133; }
.tab-header { margin-bottom: 15px; }
.empty-data { padding: 40px 0; text-align: center; }
.warranty-expired { color: #f56c6c; }
.warranty-expiring { color: #e6a23c; }
.warranty-valid { color: #67c23a; }
</style>
