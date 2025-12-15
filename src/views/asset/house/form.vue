<template>
  <div class="house-asset-form">
    <el-form
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="120px"
      size="small"
    >
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
                <el-option label="房源" value="HOUSE" />
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
            <el-form-item label="位置" prop="location">
              <el-cascader
                v-model="form.location"
                :options="locationOptions"
                :props="{ value: 'id', label: 'name', children: 'children' }"
                placeholder="选择位置"
                style="width: 100%"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="位置描述">
              <el-input
                v-model="form.locationDesc"
                type="textarea"
                :rows="2"
                placeholder="详细位置描述"
              />
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

      <!-- Financial Information Section -->
      <div class="form-section">
        <h4 class="section-title">财务信息</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="购置日期">
              <el-date-picker
                v-model="form.purchaseDate"
                type="date"
                placeholder="选择购置日期"
                style="width: 100%"
                value-format="yyyy-MM-dd"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="购置价格">
              <el-input-number
                v-model="form.purchasePrice"
                :precision="2"
                :step="1000"
                :min="0"
                placeholder="请输入购置价格"
                style="width: 100%"
              />
              <span style="margin-left: 10px;">元</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="保修到期">
              <el-date-picker
                v-model="form.warrantyExpireDate"
                type="date"
                placeholder="选择保修到期日期"
                style="width: 100%"
                value-format="yyyy-MM-dd"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- House-specific Section -->
      <div class="form-section">
        <h4 class="section-title">房屋信息</h4>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="建筑面积">
              <el-input-number
                v-model="form.buildingArea"
                :precision="2"
                :step="0.1"
                :min="0"
                placeholder="请输入建筑面积"
                style="width: 100%"
              />
              <span style="margin-left: 10px;">㎡</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="套内面积">
              <el-input-number
                v-model="form.innerArea"
                :precision="2"
                :step="0.1"
                :min="0"
                placeholder="请输入套内面积"
                style="width: 100%"
              />
              <span style="margin-left: 10px;">㎡</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="户型">
              <el-select v-model="form.houseType" placeholder="选择户型" style="width: 100%">
                <el-option label="一居" value="one_room" />
                <el-option label="两居" value="two_room" />
                <el-option label="三居" value="three_room" />
                <el-option label="四居" value="four_room" />
                <el-option label="其他" value="other" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="房屋用途">
              <el-select v-model="form.houseUsage" placeholder="选择用途" style="width: 100%">
                <el-option label="住宅" value="residential" />
                <el-option label="商用" value="commercial" />
                <el-option label="公寓" value="apartment" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="当前用途">
              <el-select v-model="form.currentUsage" placeholder="选择当前用途" style="width: 100%">
                <el-option label="自住" value="self_use" />
                <el-option label="出租" value="rented" />
                <el-option label="空置" value="idle" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="当前使用人">
              <el-input v-model="form.currentUser" placeholder="请输入当前使用人" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="合同编号">
              <el-input v-model="form.contractNo" placeholder="请输入合同编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="租金单价">
              <el-input-number
                v-model="form.rentUnitPrice"
                :precision="2"
                :step="100"
                :min="0"
                placeholder="请输入租金单价"
                style="width: 100%"
              />
              <span style="margin-left: 10px;">元/月</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="租金总额">
              <el-input-number
                v-model="form.rentTotal"
                :precision="2"
                :step="1000"
                :min="0"
                placeholder="请输入租金总额"
                style="width: 100%"
              />
              <span style="margin-left: 10px;">元</span>
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
              <el-input
                v-model="form.description"
                type="textarea"
                :rows="3"
                placeholder="请输入资产描述"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input
                v-model="form.remark"
                type="textarea"
                :rows="3"
                placeholder="请输入备注信息"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <!-- Attachments Section -->
      <div class="form-section">
        <h4 class="section-title">附件上传</h4>
        <el-form-item label="附件">
          <el-upload
            class="upload-demo"
            drag
            action="#"
            multiple
            :show-file-list="true"
            :before-upload="beforeUpload"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip">支持jpg/png/gif/doc/pdf等格式，单个文件不超过5MB</div>
          </el-upload>
        </el-form-item>
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
import { getHouse, addHouse, updateHouse } from '@/api/asset/house'

export default {
  name: 'HouseAssetForm',
  props: {
    assetId: {
      type: [String, Number],
      default: null
    },
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      // Form data
      form: {
        name: '',
        assetType: 'HOUSE',
        projectId: null,
        status: 'available_idle',
        location: [],
        locationDesc: '',
        responsiblePerson: '',
        responsiblePhone: '',
        purchaseDate: '',
        purchasePrice: null,
        warrantyExpireDate: '',
        buildingArea: null,
        innerArea: null,
        houseType: '',
        houseUsage: '',
        currentUsage: '',
        currentUser: '',
        contractNo: '',
        rentUnitPrice: null,
        rentTotal: null,
        description: '',
        remark: ''
      },
      // Form validation rules
      rules: {
        name: [
          { required: true, message: '请输入资产名称', trigger: 'blur' }
        ],
        projectId: [
          { required: true, message: '请选择项目', trigger: 'change' }
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ],
        houseType: [
          { required: true, message: '请选择户型', trigger: 'change' }
        ]
      },
      // Location options for cascader
      locationOptions: [
        {
          id: 1,
          name: '1号楼',
          children: [
            {
              id: 11,
              name: '1层',
              children: [
                { id: 111, name: '101室' },
                { id: 112, name: '102室' }
              ]
            },
            {
              id: 12,
              name: '2层',
              children: [
                { id: 121, name: '201室' },
                { id: 122, name: '202室' }
              ]
            }
          ]
        }
      ],
      // Submitting state
      submitting: false
    }
  },
  created() {
    if (this.isEdit && this.assetId) {
      this.getAssetDetail()
    }
  },
  methods: {
    /**
     * Get asset detail
     */
    getAssetDetail() {
      getHouse(this.assetId).then(response => {
        const data = response.data || {}
        this.form = {
          ...this.form,
          ...data,
          location: []
        }
      })
    },

    /**
     * Handle form submit
     */
    onSubmit() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.submitting = true

          // Prepare data
          const formData = { ...this.form }
          if (this.form.location && this.form.location.length >= 3) {
            formData.buildingId = this.form.location[0]
            formData.floorId = this.form.location[1]
            formData.unitId = this.form.location[2]
          }

          const submitMethod = this.isEdit ? updateHouse : addHouse
          const successMessage = this.isEdit ? '更新成功' : '创建成功'

          submitMethod(formData).then(() => {
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

    /**
     * Handle cancel
     */
    onCancel() {
      this.$emit('cancel')
    },

    /**
     * Handle reset
     */
    onReset() {
      this.$refs.formRef.resetFields()
      this.form = {
        name: '',
        assetType: 'HOUSE',
        projectId: null,
        status: 'available_idle',
        location: [],
        locationDesc: '',
        responsiblePerson: '',
        responsiblePhone: '',
        purchaseDate: '',
        purchasePrice: null,
        warrantyExpireDate: '',
        buildingArea: null,
        innerArea: null,
        houseType: '',
        houseUsage: '',
        currentUsage: '',
        currentUser: '',
        contractNo: '',
        rentUnitPrice: null,
        rentTotal: null,
        description: '',
        remark: ''
      }
    },

    /**
     * Before upload
     */
    beforeUpload(file) {
      const isValidType = ['image/jpeg', 'image/png', 'image/gif', 'application/pdf', 'application/msword'].includes(file.type)
      const isLt5M = file.size / 1024 / 1024 < 5

      if (!isValidType) {
        this.$message.error('上传文件只能是 JPG/PNG/GIF/PDF/DOC 格式!')
      }
      if (!isLt5M) {
        this.$message.error('上传文件大小不能超过 5MB!')
      }
      return isValidType && isLt5M
    }
  }
}
</script>

<style scoped>
.house-asset-form {
  padding: 20px;
}

.form-section {
  margin-bottom: 30px;
  padding: 20px;
  background: #fff;
  border-radius: 4px;
  border: 1px solid #e4e7ed;
}

.section-title {
  margin: 0 0 20px 0;
  padding-bottom: 10px;
  border-bottom: 1px solid #e4e7ed;
  font-size: 16px;
  color: #303133;
  font-weight: 500;
}

.form-footer {
  margin-top: 30px;
  padding: 20px;
  background: #fff;
  border-radius: 4px;
  border: 1px solid #e4e7ed;
  text-align: right;
}

.upload-demo {
  width: 100%;
}
</style>
