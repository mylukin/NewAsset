<template>
  <div class="app-container">
    <!-- Search Form -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="Plan Name" prop="planName">
        <el-input
          v-model="queryParams.planName"
          placeholder="Enter plan name"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Project" prop="projectId">
        <el-select v-model="queryParams.projectId" placeholder="Select project" clearable>
          <el-option
            v-for="item in projectOptions"
            :key="item.id"
            :label="item.projectName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Asset Type" prop="assetType">
        <el-select v-model="queryParams.assetType" placeholder="Select type" clearable>
          <el-option
            v-for="dict in dict.type.asset_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Cycle Type" prop="cycleType">
        <el-select v-model="queryParams.cycleType" placeholder="Select cycle" clearable>
          <el-option
            v-for="dict in dict.type.maint_cycle_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Status" prop="status">
        <el-select v-model="queryParams.status" placeholder="Select status" clearable>
          <el-option
            v-for="dict in dict.type.maint_plan_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">Search</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">Reset</el-button>
      </el-form-item>
    </el-form>

    <!-- Toolbar -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['asset:maint:plan:add']"
        >Create</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['asset:maint:plan:remove']"
        >Delete</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:maint:plan:export']"
        >Export</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-video-play"
          size="mini"
          @click="handleExecute"
          v-hasPermi="['asset:maint:plan:execute']"
        >Execute Now</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- Data Table -->
    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Plan Name" align="center" prop="planName" :show-overflow-tooltip="true" />
      <el-table-column label="Project" align="center" prop="projectName" width="120" :show-overflow-tooltip="true" />
      <el-table-column label="Asset Type" align="center" prop="assetTypeLabel" width="120" />
      <el-table-column label="Cycle Type" align="center" prop="cycleTypeLabel" width="100" />
      <el-table-column label="Assets" align="center" prop="assetCount" width="80" />
      <el-table-column label="Next Generate" align="center" prop="nextGenerateTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.nextGenerateTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Status" align="center" prop="status" width="100">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="ENABLED"
            inactive-value="DISABLED"
            @change="handleStatusChange(scope.row)"
            v-hasPermi="['asset:maint:plan:edit']"
          />
        </template>
      </el-table-column>
      <el-table-column label="Create Time" align="center" prop="createTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" class-name="small-padding fixed-width" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['asset:maint:plan:query']"
          >View</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleEdit(scope.row)"
            v-hasPermi="['asset:maint:plan:edit']"
          >Edit</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:maint:plan:remove']"
          >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- Pagination -->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- Create/Edit Dialog -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="Plan Name" prop="planName">
          <el-input v-model="form.planName" placeholder="Enter plan name" />
        </el-form-item>
        <el-form-item label="Project" prop="projectId">
          <el-select v-model="form.projectId" placeholder="Select project" style="width: 100%">
            <el-option
              v-for="item in projectOptions"
              :key="item.id"
              :label="item.projectName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Asset Type" prop="assetType">
          <el-select v-model="form.assetType" placeholder="Select asset type" clearable style="width: 100%">
            <el-option
              v-for="dict in dict.type.asset_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Asset Category" prop="assetCategory">
          <el-input v-model="form.assetCategory" placeholder="Enter asset category (optional)" />
        </el-form-item>
        <el-form-item label="Cycle Type" prop="cycleType">
          <el-select v-model="form.cycleType" placeholder="Select cycle type" style="width: 100%">
            <el-option
              v-for="dict in dict.type.maint_cycle_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="Next Generate" prop="nextGenerateTime">
          <el-date-picker
            v-model="form.nextGenerateTime"
            type="datetime"
            placeholder="Select next generate time"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="Status" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="ENABLED">Enabled</el-radio>
            <el-radio label="DISABLED">Disabled</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Remark" prop="remark">
          <el-input v-model="form.remark" type="textarea" :rows="2" placeholder="Enter remark" />
        </el-form-item>
        <el-form-item label="Select Assets" prop="assetIds">
          <el-transfer
            v-model="form.assetIds"
            :data="assetOptions"
            :titles="['Available', 'Selected']"
            :props="{ key: 'id', label: 'displayName' }"
            filterable
            filter-placeholder="Search assets"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">Submit</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </div>
    </el-dialog>

    <!-- Detail Drawer -->
    <el-drawer :title="'Plan Details'" :visible.sync="drawerVisible" size="50%">
      <div class="drawer-content" v-if="currentPlan">
        <el-descriptions :column="2" border>
          <el-descriptions-item label="Plan Name">{{ currentPlan.planName }}</el-descriptions-item>
          <el-descriptions-item label="Status">
            <el-tag :type="currentPlan.status === 'ENABLED' ? 'success' : 'danger'" size="small">
              {{ currentPlan.statusLabel }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="Project">{{ currentPlan.projectName || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Asset Type">{{ currentPlan.assetTypeLabel || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Asset Category">{{ currentPlan.assetCategory || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Cycle Type">{{ currentPlan.cycleTypeLabel }}</el-descriptions-item>
          <el-descriptions-item label="Asset Count">{{ currentPlan.assetCount }}</el-descriptions-item>
          <el-descriptions-item label="Next Generate">{{ parseTime(currentPlan.nextGenerateTime) || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Remark" :span="2">{{ currentPlan.remark || '-' }}</el-descriptions-item>
          <el-descriptions-item label="Create By">{{ currentPlan.createBy }}</el-descriptions-item>
          <el-descriptions-item label="Create Time">{{ parseTime(currentPlan.createTime) }}</el-descriptions-item>
        </el-descriptions>

        <!-- Associated Assets -->
        <div class="section-title" style="margin-top: 20px;">Associated Assets ({{ currentPlan.assetIds ? currentPlan.assetIds.length : 0 }})</div>
        <el-table :data="selectedAssets" size="small" max-height="300">
          <el-table-column label="Asset Code" prop="assetCode" width="120" />
          <el-table-column label="Asset Name" prop="assetName" />
          <el-table-column label="Asset Type" prop="assetTypeLabel" width="100" />
        </el-table>
        <el-empty v-if="!selectedAssets || selectedAssets.length === 0" description="No assets selected"></el-empty>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {
  getMaintPlanList,
  getMaintPlan,
  addMaintPlan,
  updateMaintPlan,
  deleteMaintPlan,
  enableMaintPlan,
  disableMaintPlan,
  executeScheduledPlans
} from '@/api/asset/maintPlan'

export default {
  name: 'MaintPlanList',
  dicts: ['asset_type', 'maint_cycle_type', 'maint_plan_status'],
  data() {
    return {
      loading: true,
      showSearch: true,
      ids: [],
      single: true,
      multiple: true,
      total: 0,
      planList: [],
      title: '',
      open: false,
      drawerVisible: false,
      currentPlan: null,
      selectedAssets: [],
      projectOptions: [],
      assetOptions: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planName: null,
        projectId: null,
        assetType: null,
        cycleType: null,
        status: null
      },
      form: {},
      rules: {
        planName: [{ required: true, message: 'Plan name is required', trigger: 'blur' }],
        cycleType: [{ required: true, message: 'Cycle type is required', trigger: 'change' }],
        status: [{ required: true, message: 'Status is required', trigger: 'change' }]
      }
    }
  },
  created() {
    this.getList()
    this.loadOptions()
  },
  methods: {
    getList() {
      this.loading = true
      getMaintPlanList(this.queryParams).then(response => {
        this.planList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    loadOptions() {
      // Load project options, asset options
      // These would normally come from API calls
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    handleStatusChange(row) {
      const action = row.status === 'ENABLED' ? enableMaintPlan : disableMaintPlan
      action(row.id).then(() => {
        this.$modal.msgSuccess(`Plan ${row.status === 'ENABLED' ? 'enabled' : 'disabled'} successfully`)
      }).catch(() => {
        // Revert on error
        row.status = row.status === 'ENABLED' ? 'DISABLED' : 'ENABLED'
      })
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = 'Create Maintenance Plan'
    },
    handleEdit(row) {
      this.reset()
      getMaintPlan(row.id).then(response => {
        const data = response.data
        this.form = {
          id: data.id,
          planName: data.planName,
          projectId: data.projectId,
          assetType: data.assetType,
          assetCategory: data.assetCategory,
          cycleType: data.cycleType,
          nextGenerateTime: data.nextGenerateTime,
          status: data.status,
          remark: data.remark,
          assetIds: data.assetIds || []
        }
        this.open = true
        this.title = 'Edit Maintenance Plan'
      })
    },
    handleView(row) {
      getMaintPlan(row.id).then(response => {
        this.currentPlan = response.data
        // Load selected assets info
        this.loadSelectedAssets(this.currentPlan.assetIds)
        this.drawerVisible = true
      })
    },
    loadSelectedAssets(assetIds) {
      if (!assetIds || assetIds.length === 0) {
        this.selectedAssets = []
        return
      }
      // Filter from asset options
      this.selectedAssets = this.assetOptions.filter(a => assetIds.includes(a.id))
    },
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('Are you sure to delete the selected plans?').then(() => {
        return deleteMaintPlan(ids)
      }).then(() => {
        this.$modal.msgSuccess('Deleted successfully')
        this.getList()
      }).catch(() => {})
    },
    handleExport() {
      this.download('/asset/maint/plan/export', { ...this.queryParams }, `maint_plans_${new Date().getTime()}.xlsx`)
    },
    handleExecute() {
      this.$modal.confirm('Are you sure to execute all pending scheduled plans now?').then(() => {
        return executeScheduledPlans()
      }).then(() => {
        this.$modal.msgSuccess('Scheduled plans executed successfully')
        this.getList()
      }).catch(() => {})
    },
    reset() {
      this.form = {
        id: null,
        planName: '',
        projectId: null,
        assetType: null,
        assetCategory: null,
        cycleType: null,
        nextGenerateTime: null,
        status: 'DISABLED',
        remark: '',
        assetIds: []
      }
      this.resetForm('form')
    },
    cancel() {
      this.open = false
      this.reset()
    },
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          const data = {
            plan: {
              id: this.form.id,
              planName: this.form.planName,
              projectId: this.form.projectId,
              assetType: this.form.assetType,
              assetCategory: this.form.assetCategory,
              cycleType: this.form.cycleType,
              nextGenerateTime: this.form.nextGenerateTime,
              status: this.form.status,
              remark: this.form.remark
            },
            assetIds: this.form.assetIds
          }

          const action = this.form.id ? updateMaintPlan : addMaintPlan
          action(data).then(() => {
            this.$modal.msgSuccess(this.form.id ? 'Updated successfully' : 'Created successfully')
            this.open = false
            this.getList()
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.drawer-content {
  padding: 20px;
}
.section-title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #303133;
}
</style>
