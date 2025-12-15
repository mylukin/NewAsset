<template>
  <div class="office-asset-form">
    <el-form ref="formRef" :model="form" :rules="rules" label-width="120px" size="small">
      <!-- Basic Information Section -->
      <div class="form-section">
        <h4 class="section-title">基本信息</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="资产名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入资产名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资产类型" prop="assetType">
              <el-select v-model="form.assetType" disabled style="width: 100%">
                <el-option label="办公资产" value="OFFICE" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目" prop="projectId">
              <el-select v-model="form.projectId" placeholder="选择项目" style="width: 100%">
                <el-option label="项目A" value="1" />
                <el-option label="项目B" value="2" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="楼宇" prop="buildingId">
              <el-select v-model="form.buildingId" placeholder="选择楼宇" style="width: 100%">
                <el-option label="1号楼" value="1" />
                <el-option label="2号楼" value="2" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="楼层">
              <el-input v-model="form.floorName" placeholder="如：1F、2F" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="选择状态" style="width: 100%">
                <el-option label="可用-自用" value="available_self" />
                <el-option label="可用-空置" value="available_idle" />
                <el-option label="故障/停用" value="fault" />
                <el-option label="维修中" value="maintaining" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="位置描述">
              <el-input v-model="form.locationDesc" type="textarea" :rows="2" placeholder="详细位置描述" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Office Asset Information Section -->
      <div class="form-section">
        <h4 class="section-title">办公资产信息</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="资产分类" prop="officeType">
              <el-select v-model="form.officeType" placeholder="选择资产分类" style="width: 100%">
                <el-option label="办公家具" value="furniture" />
                <el-option label="IT设备" value="it_equipment" />
                <el-option label="办公设备" value="office_equipment" />
                <el-option label="其他" value="other" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="品牌">
              <el-input v-model="form.brand" placeholder="请输入品牌" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="型号">
              <el-input v-model="form.model" placeholder="请输入型号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="序列号">
              <el-input v-model="form.serialNo" placeholder="请输入序列号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="使用人">
              <el-input v-model="form.currentUser" placeholder="请输入使用人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="使用部门">
              <el-input v-model="form.department" placeholder="请输入使用部门" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Financial Information Section -->
      <div class="form-section">
        <h4 class="section-title">财务信息</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="购置日期">
              <el-date-picker v-model="form.purchaseDate" type="date" placeholder="选择购置日期" style="width: 100%" value-format="yyyy-MM-dd" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="购置价格">
              <el-input-number v-model="form.purchasePrice" :precision="2" :step="100" :min="0" placeholder="请输入购置价格" style="width: 100%" />
              <span style="margin-left: 10px;">元</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="保修到期">
              <el-date-picker v-model="form.warrantyExpireDate" type="date" placeholder="选择保修到期日期" style="width: 100%" value-format="yyyy-MM-dd" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Responsibility Section -->
      <div class="form-section">
        <h4 class="section-title">责任人信息</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="负责人">
              <el-input v-model="form.responsiblePerson" placeholder="请输入负责人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话">
              <el-input v-model="form.responsiblePhone" placeholder="请输入联系电话" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Description Section -->
      <div class="form-section">
        <h4 class="section-title">其他信息</h4>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="描述">
              <el-input v-model="form.description" type="textarea" :rows="3" placeholder="请输入资产描述" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input v-model="form.remark" type="textarea" :rows="3" placeholder="请输入备注信息" />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Footer Actions -->
      <div class="form-footer">
        <el-form-item>
          <el-button type="primary" @click="onSubmit" :loading="submitting">提交</el-button>
          <el-button @click="onCancel">取消</el-button>
          <el-button @click="onReset">重置</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import { getOffice, addOffice, updateOffice } from '@/api/asset/office'

export default {
  name: 'OfficeAssetForm',
  props: {
    assetId: { type: [String, Number], default: null },
    isEdit: { type: Boolean, default: false }
  },
  data() {
    return {
      form: {
        name: '',
        assetType: 'OFFICE',
        projectId: null,
        buildingId: null,
        floorName: '',
        status: 'available_self',
        locationDesc: '',
        officeType: '',
        brand: '',
        model: '',
        serialNo: '',
        currentUser: '',
        department: '',
        purchaseDate: '',
        purchasePrice: null,
        warrantyExpireDate: '',
        responsiblePerson: '',
        responsiblePhone: '',
        description: '',
        remark: ''
      },
      rules: {
        name: [{ required: true, message: '请输入资产名称', trigger: 'blur' }],
        projectId: [{ required: true, message: '请选择项目', trigger: 'change' }],
        officeType: [{ required: true, message: '请选择资产分类', trigger: 'change' }]
      },
      submitting: false
    }
  },
  created() {
    if (this.isEdit && this.assetId) {
      this.getAssetDetail()
    }
  },
  methods: {
    getAssetDetail() {
      getOffice(this.assetId).then(response => {
        const data = response.data || {}
        this.form = { ...this.form, ...data }
      })
    },
    onSubmit() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.submitting = true
          const submitMethod = this.isEdit ? updateOffice : addOffice
          const successMessage = this.isEdit ? '更新成功' : '创建成功'
          submitMethod(this.form).then(() => {
            this.$message.success(successMessage)
            this.$emit('success')
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
      this.$emit('cancel')
    },
    onReset() {
      this.$refs.formRef.resetFields()
    }
  }
}
</script>

<style scoped>
.office-asset-form { padding: 20px; }
.form-section { margin-bottom: 30px; padding: 20px; background: #fff; border-radius: 4px; border: 1px solid #e4e7ed; }
.section-title { margin: 0 0 20px 0; padding-bottom: 10px; border-bottom: 1px solid #e4e7ed; font-size: 16px; color: #303133; font-weight: 500; }
.form-footer { margin-top: 30px; padding: 20px; background: #fff; border-radius: 4px; border: 1px solid #e4e7ed; text-align: right; }
</style>
