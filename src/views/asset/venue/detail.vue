<template>
  <div class="venue-asset-detail">
    <el-drawer
      :title="`场地资产详情 - ${venueDetail.name || venueDetail.assetCode}`"
      :visible.sync="drawerVisible"
      direction="rtl"
      :size="800"
      :before-close="handleClose"
    >
      <div class="detail-container">
        <!-- Header -->
        <div class="detail-header">
          <div class="header-left">
            <h2 class="asset-code">{{ venueDetail.assetCode }}</h2>
            <h3 class="asset-name">{{ venueDetail.name }}</h3>
          </div>
          <div class="header-right">
            <el-tag :type="getStatusTagType(venueDetail.status)" size="large">
              {{ getStatusText(venueDetail.status) }}
            </el-tag>
          </div>
        </div>

        <!-- Header Actions -->
        <div class="header-actions">
          <el-button type="primary" icon="el-icon-edit" v-hasPermi="['asset:venue:edit']" @click="handleEdit">编辑</el-button>
          <el-button type="warning" icon="el-icon-wrench" v-hasPermi="['asset:maint:add']" @click="handleMaintenance">发起维护</el-button>
          <el-button type="danger" icon="el-icon-delete" v-hasPermi="['asset:venue:remove']" @click="handleDelete">删除</el-button>
        </div>

        <!-- Tabs -->
        <el-tabs v-model="activeTab" class="detail-tabs">
          <!-- Tab 1: Basic Info -->
          <el-tab-pane label="基本信息" name="basic">
            <div class="tab-content">
              <el-descriptions :column="2" border>
                <el-descriptions-item label="资产编码">{{ venueDetail.assetCode }}</el-descriptions-item>
                <el-descriptions-item label="资产名称">{{ venueDetail.name }}</el-descriptions-item>
                <el-descriptions-item label="资产类型">
                  <el-tag size="small">场地</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="状态">
                  <el-tag :type="getStatusTagType(venueDetail.status)">
                    {{ getStatusText(venueDetail.status) }}
                  </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="项目" span="2">{{ venueDetail.projectName }}</el-descriptions-item>
                <el-descriptions-item label="楼宇">{{ venueDetail.buildingName }}</el-descriptions-item>
                <el-descriptions-item label="位置描述">{{ venueDetail.locationDesc || '-' }}</el-descriptions-item>
                <el-descriptions-item label="负责人">{{ venueDetail.responsiblePerson || '-' }}</el-descriptions-item>
                <el-descriptions-item label="联系电话">{{ venueDetail.responsiblePhone || '-' }}</el-descriptions-item>
              </el-descriptions>

              <!-- Venue-specific fields -->
              <h4 class="section-title">场地信息</h4>
              <el-descriptions :column="2" border>
                <el-descriptions-item label="场地类型">{{ getVenueTypeText(venueDetail.venueType) || '-' }}</el-descriptions-item>
                <el-descriptions-item label="场地面积">{{ venueDetail.area ? venueDetail.area + '㎡' : '-' }}</el-descriptions-item>
                <el-descriptions-item label="容纳人数">{{ venueDetail.capacity ? venueDetail.capacity + '人' : '-' }}</el-descriptions-item>
                <el-descriptions-item label="所属楼层">{{ venueDetail.floorName || '-' }}</el-descriptions-item>
                <el-descriptions-item label="当前用途">
                  <el-tag :type="getUsageTagType(venueDetail.currentUsage)" size="small">
                    {{ getUsageText(venueDetail.currentUsage) }}
                  </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="租金单价">{{ venueDetail.rentUnitPrice ? venueDetail.rentUnitPrice + '元/小时' : '-' }}</el-descriptions-item>
                <el-descriptions-item label="配套设施" span="2">{{ venueDetail.facilities || '-' }}</el-descriptions-item>
                <el-descriptions-item label="开放时间" span="2">{{ venueDetail.openTime || '-' }}</el-descriptions-item>
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
              <el-upload class="upload-demo" drag action="#" multiple :show-file-list="false" v-hasPermi="['asset:venue:edit']">
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
import { getVenue } from '@/api/asset/venue'

export default {
  name: 'VenueAssetDetail',
  data() {
    return {
      drawerVisible: true,
      activeTab: 'basic',
      venueDetail: {},
      maintenanceList: [],
      attachmentList: []
    }
  },
  created() {
    const assetId = this.$route.params.id
    if (assetId) {
      this.getVenueDetail(assetId)
    }
  },
  methods: {
    getVenueDetail(id) {
      getVenue(id).then(response => {
        this.venueDetail = response.data || {}
        this.maintenanceList = []
        this.attachmentList = []
      })
    },
    handleClose() {
      this.$router.push('/asset/venue')
    },
    handleEdit() {
      this.$router.push(`/asset/venue/edit/${this.venueDetail.id}`)
    },
    handleMaintenance() {
      this.$router.push(`/maintenance/create?assetId=${this.venueDetail.id}`)
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
      this.$router.push(`/maintenance/create?assetId=${this.venueDetail.id}`)
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
    },
    getUsageTagType(usage) {
      const usageMap = {
        'self_use': 'success',
        'rented': 'warning',
        'idle': 'danger'
      }
      return usageMap[usage] || 'info'
    },
    getUsageText(usage) {
      const usageMap = {
        'self_use': '自用',
        'rented': '出租',
        'idle': '空置'
      }
      return usageMap[usage] || usage
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
