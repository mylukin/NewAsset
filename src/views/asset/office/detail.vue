<template>
  <div class="office-asset-detail">
    <el-drawer
      :title="`办公资产详情 - ${officeDetail.name || officeDetail.assetCode}`"
      :visible.sync="drawerVisible"
      direction="rtl"
      :size="800"
      :before-close="handleClose"
    >
      <div class="detail-container">
        <!-- Header -->
        <div class="detail-header">
          <div class="header-left">
            <h2 class="asset-code">{{ officeDetail.assetCode }}</h2>
            <h3 class="asset-name">{{ officeDetail.name }}</h3>
          </div>
          <div class="header-right">
            <el-tag :type="getStatusTagType(officeDetail.status)" size="large">
              {{ getStatusText(officeDetail.status) }}
            </el-tag>
          </div>
        </div>

        <!-- Header Actions -->
        <div class="header-actions">
          <el-button type="primary" icon="el-icon-edit" v-hasPermi="['asset:office:edit']" @click="handleEdit">编辑</el-button>
          <el-button type="warning" icon="el-icon-wrench" v-hasPermi="['asset:maint:add']" @click="handleMaintenance">发起维护</el-button>
          <el-button type="danger" icon="el-icon-delete" v-hasPermi="['asset:office:remove']" @click="handleDelete">删除</el-button>
        </div>

        <!-- Tabs -->
        <el-tabs v-model="activeTab" class="detail-tabs">
          <!-- Tab 1: Basic Info -->
          <el-tab-pane label="基本信息" name="basic">
            <div class="tab-content">
              <el-descriptions :column="2" border>
                <el-descriptions-item label="资产编码">{{ officeDetail.assetCode }}</el-descriptions-item>
                <el-descriptions-item label="资产名称">{{ officeDetail.name }}</el-descriptions-item>
                <el-descriptions-item label="资产类型">
                  <el-tag size="small">办公资产</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="状态">
                  <el-tag :type="getStatusTagType(officeDetail.status)">
                    {{ getStatusText(officeDetail.status) }}
                  </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="项目" span="2">{{ officeDetail.projectName }}</el-descriptions-item>
                <el-descriptions-item label="楼宇">{{ officeDetail.buildingName }}</el-descriptions-item>
                <el-descriptions-item label="楼层">{{ officeDetail.floorName }}</el-descriptions-item>
                <el-descriptions-item label="位置描述">{{ officeDetail.locationDesc || '-' }}</el-descriptions-item>
                <el-descriptions-item label="负责人">{{ officeDetail.responsiblePerson || '-' }}</el-descriptions-item>
                <el-descriptions-item label="联系电话">{{ officeDetail.responsiblePhone || '-' }}</el-descriptions-item>
              </el-descriptions>

              <!-- Office-specific fields -->
              <h4 class="section-title">办公资产信息</h4>
              <el-descriptions :column="2" border>
                <el-descriptions-item label="资产分类">{{ getOfficeTypeText(officeDetail.officeType) || '-' }}</el-descriptions-item>
                <el-descriptions-item label="品牌">{{ officeDetail.brand || '-' }}</el-descriptions-item>
                <el-descriptions-item label="型号">{{ officeDetail.model || '-' }}</el-descriptions-item>
                <el-descriptions-item label="序列号">{{ officeDetail.serialNo || '-' }}</el-descriptions-item>
                <el-descriptions-item label="购置日期">{{ officeDetail.purchaseDate || '-' }}</el-descriptions-item>
                <el-descriptions-item label="购置价格">{{ officeDetail.purchasePrice ? officeDetail.purchasePrice + '元' : '-' }}</el-descriptions-item>
                <el-descriptions-item label="保修到期">{{ officeDetail.warrantyExpireDate || '-' }}</el-descriptions-item>
                <el-descriptions-item label="使用人">{{ officeDetail.currentUser || '-' }}</el-descriptions-item>
                <el-descriptions-item label="使用部门">{{ officeDetail.department || '-' }}</el-descriptions-item>
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
              <el-upload class="upload-demo" drag action="#" multiple :show-file-list="false" v-hasPermi="['asset:office:edit']">
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
import { getOffice } from '@/api/asset/office'

export default {
  name: 'OfficeAssetDetail',
  data() {
    return {
      drawerVisible: true,
      activeTab: 'basic',
      officeDetail: {},
      maintenanceList: [],
      attachmentList: []
    }
  },
  created() {
    const assetId = this.$route.params.id
    if (assetId) {
      this.getOfficeDetail(assetId)
    }
  },
  methods: {
    getOfficeDetail(id) {
      getOffice(id).then(response => {
        this.officeDetail = response.data || {}
        this.maintenanceList = []
        this.attachmentList = []
      })
    },
    handleClose() {
      this.$router.push('/asset/office')
    },
    handleEdit() {
      this.$router.push(`/asset/office/edit/${this.officeDetail.id}`)
    },
    handleMaintenance() {
      this.$router.push(`/maintenance/create?assetId=${this.officeDetail.id}`)
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
      this.$router.push(`/maintenance/create?assetId=${this.officeDetail.id}`)
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
</style>
