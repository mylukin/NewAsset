<template>
  <div class="parking-asset-form">
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
                <el-option label="车位" value="PARKING" />
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
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="选择状态" style="width: 100%">
                <el-option label="可用-自用" value="available_self" />
                <el-option label="可用-对外出租" value="available_rent" />
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

      <!-- Parking Information Section -->
      <div class="form-section">
        <h4 class="section-title">车位信息</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="车位编号" prop="parkingNo">
              <el-input v-model="form.parkingNo" placeholder="请输入车位编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车位类型" prop="parkingType">
              <el-select v-model="form.parkingType" placeholder="选择车位类型" style="width: 100%">
                <el-option label="标准车位" value="standard" />
                <el-option label="小型车位" value="compact" />
                <el-option label="大型车位" value="large" />
                <el-option label="无障碍车位" value="accessible" />
                <el-option label="充电车位" value="ev_charging" />
                <el-option label="摩托车位" value="motorcycle" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车位面积">
              <el-input-number v-model="form.area" :precision="2" :step="0.1" :min="0" placeholder="请输入面积" style="width: 100%" />
              <span style="margin-left: 10px;">㎡</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属楼层">
              <el-input v-model="form.floorName" placeholder="如：B1、B2" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="当前用途">
              <el-select v-model="form.currentUsage" placeholder="选择用途" style="width: 100%">
                <el-option label="自用" value="self_use" />
                <el-option label="出租" value="rented" />
                <el-option label="空置" value="idle" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="当前使用人">
              <el-input v-model="form.currentUser" placeholder="请输入使用人姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车牌号">
              <el-input v-model="form.plateNo" placeholder="请输入车牌号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="租金">
              <el-input-number v-model="form.rentPrice" :precision="2" :step="100" :min="0" placeholder="请输入租金" style="width: 100%" />
              <span style="margin-left: 10px;">元/月</span>
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
import { getParking, addParking, updateParking } from '@/api/asset/parking'

export default {
  name: 'ParkingAssetForm',
  props: {
    assetId: { type: [String, Number], default: null },
    isEdit: { type: Boolean, default: false }
  },
  data() {
    return {
      form: {
        name: '',
        assetType: 'PARKING',
        projectId: null,
        buildingId: null,
        status: 'available_idle',
        locationDesc: '',
        parkingNo: '',
        parkingType: '',
        area: null,
        floorName: '',
        currentUsage: '',
        currentUser: '',
        plateNo: '',
        rentPrice: null,
        responsiblePerson: '',
        responsiblePhone: '',
        description: '',
        remark: ''
      },
      rules: {
        name: [{ required: true, message: '请输入资产名称', trigger: 'blur' }],
        projectId: [{ required: true, message: '请选择项目', trigger: 'change' }],
        parkingNo: [{ required: true, message: '请输入车位编号', trigger: 'blur' }],
        parkingType: [{ required: true, message: '请选择车位类型', trigger: 'change' }]
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
      getParking(this.assetId).then(response => {
        const data = response.data || {}
        this.form = { ...this.form, ...data }
      })
    },
    onSubmit() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.submitting = true
          const submitMethod = this.isEdit ? updateParking : addParking
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
.parking-asset-form { padding: 20px; }
.form-section { margin-bottom: 30px; padding: 20px; background: #fff; border-radius: 4px; border: 1px solid #e4e7ed; }
.section-title { margin: 0 0 20px 0; padding-bottom: 10px; border-bottom: 1px solid #e4e7ed; font-size: 16px; color: #303133; font-weight: 500; }
.form-footer { margin-top: 30px; padding: 20px; background: #fff; border-radius: 4px; border: 1px solid #e4e7ed; text-align: right; }
</style>
