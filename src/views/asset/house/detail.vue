<template>
  <div class="house-asset-detail">
    <el-drawer
      :title="`房源资产详情 - ${houseDetail.name || houseDetail.assetCode}`"
      :visible.sync="drawerVisible"
      direction="rtl"
      :size="800"
      :before-close="handleClose"
    >
      <div class="detail-container">
        <!-- Header -->
        <div class="detail-header">
          <div class="header-left">
            <h2 class="asset-code">{{ houseDetail.assetCode }}</h2>
            <h3 class="asset-name">{{ houseDetail.name }}</h3>
          </div>
          <div class="header-right">
            <el-tag :type="getStatusTagType(houseDetail.status)" size="large">
              {{ getStatusText(houseDetail.status) }}
            </el-tag>
          </div>
        </div>

        <!-- Header Actions -->
        <div class="header-actions">
          <el-button
            type="primary"
            icon="el-icon-edit"
            v-hasPermi="['asset:house:edit']"
            @click="handleEdit"
          >编辑</el-button>
          <el-button
            type="warning"
            icon="el-icon-wrench"
            v-hasPermi="['asset:maint:add']"
            @click="handleMaintenance"
          >发起维护</el-button>
          <el-button
            type="danger"
            icon="el-icon-delete"
            v-hasPermi="['asset:house:remove']"
            @click="handleDelete"
          >删除</el-button>
        </div>

        <!-- Tabs -->
        <el-tabs v-model="activeTab" class="detail-tabs">
          <!-- Tab 1: Basic Info -->
          <el-tab-pane label="基本信息" name="basic">
            <div class="tab-content">
              <el-descriptions :column="2" border>
                <el-descriptions-item label="资产编码">{{ houseDetail.assetCode }}</el-descriptions-item>
                <el-descriptions-item label="资产名称">{{ houseDetail.name }}</el-descriptions-item>
                <el-descriptions-item label="资产类型">
                  <el-tag size="small">房源</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="状态">
                  <el-tag :type="getStatusTagType(houseDetail.status)">
                    {{ getStatusText(houseDetail.status) }}
                  </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="项目" span="2">{{ houseDetail.projectName }}</el-descriptions-item>
                <el-descriptions-item label="楼宇">{{ houseDetail.buildingName }}</el-descriptions-item>
                <el-descriptions-item label="楼层">{{ houseDetail.floorName }}</el-descriptions-item>
                <el-descriptions-item label="房号">{{ houseDetail.unitName }}</el-descriptions-item>
                <el-descriptions-item label="位置描述">{{ houseDetail.locationDesc || '-' }}</el-descriptions-item>
                <el-descriptions-item label="负责人">{{ houseDetail.responsiblePerson || '-' }}</el-descriptions-item>
                <el-descriptions-item label="联系电话">{{ houseDetail.responsiblePhone || '-' }}</el-descriptions-item>
                <el-descriptions-item label="购置日期">{{ houseDetail.purchaseDate || '-' }}</el-descriptions-item>
                <el-descriptions-item label="购置价格">
                  {{ houseDetail.purchasePrice ? houseDetail.purchasePrice + '元' : '-' }}
                </el-descriptions-item>
                <el-descriptions-item label="保修到期">{{ houseDetail.warrantyExpireDate || '-' }}</el-descriptions-item>
                <el-descriptions-item label="描述" span="2">{{ houseDetail.description || '-' }}</el-descriptions-item>
              </el-descriptions>

              <!-- House-specific fields -->
              <h4 class="section-title">房屋信息</h4>
              <el-descriptions :column="2" border>
                <el-descriptions-item label="建筑面积">
                  {{ houseDetail.buildingArea ? houseDetail.buildingArea + '㎡' : '-' }}
                </el-descriptions-item>
                <el-descriptions-item label="套内面积">
                  {{ houseDetail.innerArea ? houseDetail.innerArea + '㎡' : '-' }}
                </el-descriptions-item>
                <el-descriptions-item label="户型">
                  {{ getHouseTypeText(houseDetail.houseType) || '-' }}
                </el-descriptions-item>
                <el-descriptions-item label="房屋用途">
                  {{ getHouseUsageText(houseDetail.houseUsage) || '-' }}
                </el-descriptions-item>
                <el-descriptions-item label="当前用途">
                  <el-tag :type="getUsageTagType(houseDetail.currentUsage)" size="small">
                    {{ getUsageText(houseDetail.currentUsage) }}
                  </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="当前使用人">{{ houseDetail.currentUser || '-' }}</el-descriptions-item>
                <el-descriptions-item label="合同编号">{{ houseDetail.contractNo || '-' }}</el-descriptions-item>
                <el-descriptions-item label="租金单价">
                  {{ houseDetail.rentUnitPrice ? houseDetail.rentUnitPrice + '元/月' : '-' }}
                </el-descriptions-item>
                <el-descriptions-item label="租金总额">
                  {{ houseDetail.rentTotal ? houseDetail.rentTotal + '元' : '-' }}
                </el-descriptions-item>
              </el-descriptions>
            </div>
          </el-tab-pane>

          <!-- Tab 2: Maintenance Records -->
          <el-tab-pane label="维护记录" name="maintenance">
            <div class="tab-content">
              <div class="tab-header">
                <el-button
                  type="primary"
                  icon="el-icon-plus"
                  v-hasPermi="['asset:maint:add']"
                  @click="handleCreateMaintenance"
                >创建工单</el-button>
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
                <el-table-column prop="handler" label="处理人" width="120" align="center" />
                <el-table-column label="操作" width="150" align="center" fixed="right">
                  <template slot-scope="scope">
                    <el-button
                      type="text"
                      size="mini"
                      @click="handleViewMaintenance(scope.row)"
                    >查看</el-button>
                  </template>
                </el-table-column>
              </el-table>

              <div v-if="maintenanceList.length === 0" class="empty-data">
                <el-empty description="暂无维护记录" :image-size="100" />
              </div>
            </div>
          </el-tab-pane>

          <!-- Tab 3: Attachments -->
          <el-tab-pane label="附件" name="attachments">
            <div class="tab-content">
              <el-upload
                class="upload-demo"
                drag
                action="#"
                multiple
                :show-file-list="false"
                v-hasPermi="['asset:house:edit']"
              >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip">支持jpg/png/gif/doc/pdf等格式，单个文件不超过5MB</div>
              </el-upload>

              <el-row :gutter="20" class="attachment-grid">
                <el-col :span="6" v-for="file in attachmentList" :key="file.id">
                  <el-card :body-style="{ padding: '10px' }" class="attachment-card">
                    <div class="attachment-preview">
                      <img
                        v-if="isImageFile(file.fileName)"
                        :src="file.filePath"
                        :alt="file.fileName"
                        @click="previewImage(file)"
                      />
                      <div v-else class="file-icon">
                        <i class="el-icon-document"></i>
                      </div>
                    </div>
                    <div class="attachment-info">
                      <div class="file-name" :title="file.fileName">{{ file.fileName }}</div>
                      <div class="file-size">{{ formatFileSize(file.fileSize) }}</div>
                      <el-button
                        type="text"
                        size="mini"
                        icon="el-icon-download"
                        @click="downloadFile(file)"
                      >下载</el-button>
                      <el-button
                        type="text"
                        size="mini"
                        icon="el-icon-delete"
                        v-hasPermi="['asset:house:edit']"
                        @click="deleteAttachment(file)"
                      >删除</el-button>
                    </div>
                  </el-card>
                </el-col>
              </el-row>

              <div v-if="attachmentList.length === 0" class="empty-data">
                <el-empty description="暂无附件" :image-size="100" />
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-drawer>

    <!-- Image Preview Dialog -->
    <el-dialog :visible.sync="imagePreviewVisible" width="50%">
      <img :src="previewImageUrl" alt="Preview" style="width: 100%" />
    </el-dialog>
  </div>
</template>

<script>
import { getHouse } from '@/api/asset/house'

export default {
  name: 'HouseAssetDetail',
  data() {
    return {
      // Drawer visibility
      drawerVisible: true,
      // Active tab
      activeTab: 'basic',
      // House detail
      houseDetail: {},
      // Maintenance list
      maintenanceList: [],
      // Attachment list
      attachmentList: [],
      // Image preview
      imagePreviewVisible: false,
      previewImageUrl: ''
    }
  },
  created() {
    const assetId = this.$route.params.id
    if (assetId) {
      this.getHouseDetail(assetId)
    }
  },
  methods: {
    /**
     * Get house asset detail
     */
    getHouseDetail(id) {
      getHouse(id).then(response => {
        this.houseDetail = response.data || {}
        // TODO: Load maintenance records and attachments
        this.maintenanceList = []
        this.attachmentList = []
      })
    },

    /**
     * Handle close
     */
    handleClose() {
      this.$router.push('/asset/house')
    },

    /**
     * Handle edit
     */
    handleEdit() {
      this.$router.push(`/asset/house/edit/${this.houseDetail.id}`)
    },

    /**
     * Handle maintenance
     */
    handleMaintenance() {
      this.$router.push(`/asset/maintenance/create?assetId=${this.houseDetail.id}`)
    },

    /**
     * Handle delete
     */
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

    /**
     * Handle create maintenance
     */
    handleCreateMaintenance() {
      this.$router.push(`/asset/maintenance/create?assetId=${this.houseDetail.id}`)
    },

    /**
     * Handle view maintenance
     */
    handleViewMaintenance(row) {
      this.$router.push(`/asset/maintenance/detail/${row.id}`)
    },

    /**
     * Preview image
     */
    previewImage(file) {
      this.previewImageUrl = file.filePath
      this.imagePreviewVisible = true
    },

    /**
     * Download file
     */
    downloadFile(file) {
      window.open(file.filePath, '_blank')
    },

    /**
     * Delete attachment
     */
    deleteAttachment(file) {
      this.$confirm('是否确认删除该附件?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message.success('删除成功')
        const index = this.attachmentList.indexOf(file)
        if (index !== -1) {
          this.attachmentList.splice(index, 1)
        }
      })
    },

    /**
     * Check if file is image
     */
    isImageFile(fileName) {
      const imageExtensions = ['jpg', 'jpeg', 'png', 'gif', 'bmp', 'webp']
      const ext = fileName.split('.').pop().toLowerCase()
      return imageExtensions.includes(ext)
    },

    /**
     * Format file size
     */
    formatFileSize(bytes) {
      if (bytes === 0) return '0 B'
      const k = 1024
      const sizes = ['B', 'KB', 'MB', 'GB']
      const i = Math.floor(Math.log(bytes) / Math.log(k))
      return Math.round(bytes / Math.pow(k, i) * 100) / 100 + ' ' + sizes[i]
    },

    /**
     * Get status tag type
     */
    getStatusTagType(status) {
      const statusMap = {
        'available_self': 'success',
        'available_rent': 'success',
        'available_idle': 'warning',
        'temp_closed': 'warning',
        'fault': 'danger',
        'maintaining': 'warning',
        'to_be_scrapped': 'danger',
        'scrapped': 'danger'
      }
      return statusMap[status] || 'info'
    },

    /**
     * Get status text
     */
    getStatusText(status) {
      const statusMap = {
        'under_construction': '在建/未投入',
        'available_self': '可用-自用',
        'available_rent': '可用-对外出租',
        'available_idle': '可用-空置',
        'temp_closed': '暂停使用',
        'fault': '故障/停用',
        'maintaining': '维修中',
        'to_be_scrapped': '计划报废',
        'scrapped': '已报废'
      }
      return statusMap[status] || status
    },

    /**
     * Get house type text
     */
    getHouseTypeText(type) {
      const typeMap = {
        'one_room': '一居',
        'two_room': '两居',
        'three_room': '三居',
        'four_room': '四居',
        'other': '其他'
      }
      return typeMap[type] || type
    },

    /**
     * Get house usage text
     */
    getHouseUsageText(usage) {
      const usageMap = {
        'residential': '住宅',
        'commercial': '商用',
        'apartment': '公寓'
      }
      return usageMap[usage] || usage
    },

    /**
     * Get usage tag type
     */
    getUsageTagType(usage) {
      const usageMap = {
        'self_use': 'success',
        'rented': 'warning',
        'idle': 'danger'
      }
      return usageMap[usage] || 'info'
    },

    /**
     * Get usage text
     */
    getUsageText(usage) {
      const usageMap = {
        'self_use': '自住',
        'rented': '出租',
        'idle': '空置'
      }
      return usageMap[usage] || usage
    },

    /**
     * Get maintenance status tag type
     */
    getMaintenanceStatusTagType(status) {
      const statusMap = {
        'pending': 'warning',
        'processing': 'primary',
        'completed': 'success',
        'cancelled': 'info'
      }
      return statusMap[status] || 'info'
    },

    /**
     * Get maintenance status text
     */
    getMaintenanceStatusText(status) {
      const statusMap = {
        'pending': '待处理',
        'processing': '处理中',
        'completed': '已完成',
        'cancelled': '已取消'
      }
      return statusMap[status] || status
    }
  }
}
</script>

<style scoped>
.house-asset-detail {
  height: 100%;
}

.detail-container {
  padding: 0 20px 20px;
}

.detail-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 20px;
  border-bottom: 1px solid #e4e7ed;
}

.header-left .asset-code {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
  margin: 0 0 5px 0;
}

.header-left .asset-name {
  font-size: 18px;
  color: #606266;
  margin: 0;
}

.header-actions {
  margin-bottom: 20px;
}

.header-actions .el-button {
  margin-right: 10px;
}

.detail-tabs {
  height: calc(100% - 120px);
}

.tab-content {
  padding: 20px 0;
}

.section-title {
  margin: 30px 0 15px 0;
  padding-bottom: 10px;
  border-bottom: 1px solid #e4e7ed;
  font-size: 16px;
  color: #303133;
}

.tab-header {
  margin-bottom: 15px;
}

.empty-data {
  padding: 40px 0;
  text-align: center;
}

.attachment-grid {
  margin-top: 20px;
}

.attachment-card {
  margin-bottom: 15px;
}

.attachment-preview {
  height: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f5f7fa;
  border-radius: 4px;
  cursor: pointer;
}

.attachment-preview img {
  max-width: 100%;
  max-height: 120px;
  object-fit: contain;
}

.file-icon {
  font-size: 48px;
  color: #909399;
}

.attachment-info {
  padding-top: 10px;
}

.file-name {
  font-size: 12px;
  color: #303133;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-bottom: 5px;
}

.file-size {
  font-size: 12px;
  color: #909399;
  margin-bottom: 5px;
}

.upload-demo {
  margin-bottom: 20px;
}
</style>
