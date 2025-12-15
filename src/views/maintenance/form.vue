<template>
  <div class="maintenance-order-form">
    <el-form ref="formRef" :model="form" :rules="rules" label-width="120px" size="small">
      <!-- Asset Selection Section -->
      <div class="form-section">
        <h4 class="section-title">关联资产</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="资产编码" prop="assetId">
              <el-select v-model="form.assetId" filterable placeholder="选择资产" style="width: 100%" @change="handleAssetChange">
                <el-option v-for="asset in assetOptions" :key="asset.id" :label="asset.assetCode + ' - ' + asset.name" :value="asset.id" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资产名称">
              <el-input v-model="selectedAsset.name" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资产类型">
              <el-input v-model="selectedAsset.assetType" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属项目">
              <el-input v-model="selectedAsset.projectName" disabled />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Work Order Information Section -->
      <div class="form-section">
        <h4 class="section-title">工单信息</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="维修类型" prop="maintType">
              <el-select v-model="form.maintType" placeholder="选择维修类型" style="width: 100%">
                <el-option label="日常维护" value="routine" />
                <el-option label="故障维修" value="repair" />
                <el-option label="预防性维护" value="preventive" />
                <el-option label="改造升级" value="upgrade" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="优先级" prop="priority">
              <el-select v-model="form.priority" placeholder="选择优先级" style="width: 100%">
                <el-option label="紧急" value="urgent" />
                <el-option label="高" value="high" />
                <el-option label="中" value="medium" />
                <el-option label="低" value="low" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="预计完成时间">
              <el-date-picker v-model="form.expectedCompleteTime" type="datetime" placeholder="选择预计完成时间" style="width: 100%" value-format="yyyy-MM-dd HH:mm:ss" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="负责人">
              <el-input v-model="form.assignee" placeholder="请输入负责人（可稍后分配）" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Problem Description Section -->
      <div class="form-section">
        <h4 class="section-title">问题描述</h4>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="问题标题" prop="title">
              <el-input v-model="form.title" placeholder="请简要描述问题" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="详细描述" prop="description">
              <el-input v-model="form.description" type="textarea" :rows="4" placeholder="请详细描述问题情况、故障现象等" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Reporter Section -->
      <div class="form-section">
        <h4 class="section-title">报修人信息</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="报修人">
              <el-input v-model="form.reporterName" placeholder="请输入报修人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话">
              <el-input v-model="form.reporterPhone" placeholder="请输入联系电话" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Attachments Section -->
      <div class="form-section">
        <h4 class="section-title">附件上传</h4>
        <el-form-item label="附件">
          <el-upload class="upload-demo" drag action="#" multiple :show-file-list="true" :before-upload="beforeUpload">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip">支持图片、文档等格式，可上传故障照片便于诊断</div>
          </el-upload>
        </el-form-item>
      </div>

      <!-- Footer Actions -->
      <div class="form-footer">
        <el-form-item>
          <el-button type="primary" @click="onSubmit" :loading="submitting">提交工单</el-button>
          <el-button @click="onCancel">取消</el-button>
          <el-button @click="onReset">重置</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import { getOrder, addOrder, updateOrder } from '@/api/maintenance/order'

export default {
  name: 'MaintenanceOrderForm',
  props: {
    orderId: { type: [String, Number], default: null },
    isEdit: { type: Boolean, default: false }
  },
  data() {
    return {
      form: {
        assetId: null,
        maintType: '',
        priority: 'medium',
        expectedCompleteTime: '',
        assignee: '',
        title: '',
        description: '',
        reporterName: '',
        reporterPhone: ''
      },
      selectedAsset: {
        name: '',
        assetType: '',
        projectName: ''
      },
      assetOptions: [
        { id: '1', assetCode: 'HOUSE-001', name: '101室', assetType: '房源', projectName: '项目A' },
        { id: '2', assetCode: 'PARK-001', name: 'B1-001车位', assetType: '车位', projectName: '项目A' },
        { id: '3', assetCode: 'FAC-001', name: '1号电梯', assetType: '设施设备', projectName: '项目A' }
      ],
      rules: {
        assetId: [{ required: true, message: '请选择关联资产', trigger: 'change' }],
        maintType: [{ required: true, message: '请选择维修类型', trigger: 'change' }],
        priority: [{ required: true, message: '请选择优先级', trigger: 'change' }],
        title: [{ required: true, message: '请输入问题标题', trigger: 'blur' }],
        description: [{ required: true, message: '请输入详细描述', trigger: 'blur' }]
      },
      submitting: false
    }
  },
  created() {
    // Check if assetId is passed via query params
    const assetId = this.$route.query.assetId
    if (assetId) {
      this.form.assetId = assetId
      this.handleAssetChange(assetId)
    }

    if (this.isEdit && this.orderId) {
      this.getOrderDetail()
    }
  },
  methods: {
    getOrderDetail() {
      getOrder(this.orderId).then(response => {
        const data = response.data || {}
        this.form = { ...this.form, ...data }
        if (data.assetId) {
          this.handleAssetChange(data.assetId)
        }
      })
    },
    handleAssetChange(assetId) {
      const asset = this.assetOptions.find(a => a.id === assetId)
      if (asset) {
        this.selectedAsset = {
          name: asset.name,
          assetType: asset.assetType,
          projectName: asset.projectName
        }
      }
    },
    onSubmit() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.submitting = true
          const submitMethod = this.isEdit ? updateOrder : addOrder
          const successMessage = this.isEdit ? '更新成功' : '工单创建成功'
          submitMethod(this.form).then(() => {
            this.$message.success(successMessage)
            this.$emit('success')
            this.$router.push('/maintenance')
          }).catch(() => {
            this.$message.error('操作失败')
          }).finally(() => {
            this.submitting = false
          })
        } else {
          this.$message.warning('请完善表单信息')
        }
      })
    },
    onCancel() {
      this.$router.push('/maintenance')
    },
    onReset() {
      this.$refs.formRef.resetFields()
      this.selectedAsset = { name: '', assetType: '', projectName: '' }
    },
    beforeUpload(file) {
      const isLt5M = file.size / 1024 / 1024 < 5
      if (!isLt5M) {
        this.$message.error('上传文件大小不能超过 5MB!')
      }
      return isLt5M
    }
  }
}
</script>

<style scoped>
.maintenance-order-form { padding: 20px; }
.form-section { margin-bottom: 30px; padding: 20px; background: #fff; border-radius: 4px; border: 1px solid #e4e7ed; }
.section-title { margin: 0 0 20px 0; padding-bottom: 10px; border-bottom: 1px solid #e4e7ed; font-size: 16px; color: #303133; font-weight: 500; }
.form-footer { margin-top: 30px; padding: 20px; background: #fff; border-radius: 4px; border: 1px solid #e4e7ed; text-align: right; }
</style>
