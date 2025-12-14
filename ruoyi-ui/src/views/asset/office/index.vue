<template>
  <div class="app-container">
    <!-- Search/Filter Area -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
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
      <el-form-item label="Office Type" prop="officeType">
        <el-select v-model="queryParams.officeType" placeholder="Select type" clearable>
          <el-option
            v-for="dict in dict.type.office_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Asset Name" prop="assetName">
        <el-input
          v-model="queryParams.assetName"
          placeholder="Enter asset name"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Use User" prop="useUser">
        <el-input
          v-model="queryParams.useUser"
          placeholder="Enter user name"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Status" prop="status">
        <el-select v-model="queryParams.status" placeholder="Select status" clearable>
          <el-option
            v-for="dict in dict.type.asset_status"
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
          v-hasPermi="['asset:office:add']"
        >Add</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['asset:office:edit']"
        >Edit</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['asset:office:remove']"
        >Delete</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:office:export']"
        >Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <!-- Data Table -->
    <el-table v-loading="loading" :data="officeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Asset Code" align="center" prop="assetCode" v-if="columns[0].visible" />
      <el-table-column label="Asset Name" align="center" prop="assetName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
      <el-table-column label="Project" align="center" prop="projectName" v-if="columns[2].visible" />
      <el-table-column label="Office Type" align="center" prop="officeTypeLabel" v-if="columns[3].visible" />
      <el-table-column label="Workplace No" align="center" prop="workplaceNo" v-if="columns[4].visible" />
      <el-table-column label="Use User" align="center" prop="useUser" v-if="columns[5].visible" />
      <el-table-column label="Brand/Model" align="center" v-if="columns[6].visible">
        <template slot-scope="scope">
          {{ scope.row.brand || '-' }} / {{ scope.row.model || '-' }}
        </template>
      </el-table-column>
      <el-table-column label="Serial No" align="center" prop="serialNo" v-if="columns[7].visible" :show-overflow-tooltip="true" />
      <el-table-column label="Status" align="center" prop="statusLabel" v-if="columns[8].visible">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.asset_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" class-name="small-padding fixed-width" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['asset:office:query']"
          >View</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:office:edit']"
          >Edit</el-button>
          <el-dropdown trigger="click" @command="(cmd) => handleCommand(cmd, scope.row)">
            <el-button size="mini" type="text" icon="el-icon-more">More</el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="maintenance" icon="el-icon-setting">
                Initiate Maintenance
              </el-dropdown-item>
              <el-dropdown-item command="delete" icon="el-icon-delete">
                Delete
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <!-- Pagination -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- Add/Edit Dialog -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px" v-loading="formLoading">
        <!-- Basic Info Group -->
        <el-divider content-position="left">Basic Information</el-divider>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Asset Code" v-if="form.id">
              <el-input v-model="form.assetCode" disabled placeholder="Auto-generated" />
            </el-form-item>
            <el-form-item label="Asset Code" v-else>
              <el-input value="Auto-generated after save" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Project" prop="projectId">
              <el-select v-model="form.projectId" placeholder="Select project" style="width: 100%" :disabled="!!form.id">
                <el-option
                  v-for="item in projectOptions"
                  :key="item.id"
                  :label="item.projectName"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="Asset Name" prop="assetName">
              <el-input v-model="form.assetName" placeholder="Enter asset name" />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- Location Group -->
        <el-divider content-position="left">Location</el-divider>
        <el-row>
          <el-col :span="8">
            <el-form-item label="Building" prop="building">
              <el-input v-model="form.building" placeholder="Enter building" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="Floor" prop="floor">
              <el-input v-model="form.floor" placeholder="Enter floor" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="Room No" prop="roomNo">
              <el-input v-model="form.roomNo" placeholder="Enter room no" />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- Office Info Group -->
        <el-divider content-position="left">Office Equipment Information</el-divider>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Office Type" prop="officeType">
              <el-select v-model="form.officeType" placeholder="Select type" style="width: 100%">
                <el-option
                  v-for="dict in dict.type.office_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Workplace No" prop="workplaceNo">
              <el-input v-model="form.workplaceNo" placeholder="Enter workplace/desk number" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Use User" prop="useUser">
              <el-input v-model="form.useUser" placeholder="Enter assigned user" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Serial No" prop="serialNo">
              <el-input v-model="form.serialNo" placeholder="Enter serial number" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Brand" prop="brand">
              <el-input v-model="form.brand" placeholder="Enter brand" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Model" prop="model">
              <el-input v-model="form.model" placeholder="Enter model" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="OS Info" prop="osInfo">
              <el-input v-model="form.osInfo" placeholder="Enter OS information (for IT equipment)" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="Config Desc" prop="configDesc">
              <el-input v-model="form.configDesc" type="textarea" placeholder="Enter configuration description" />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- Other Fields -->
        <el-divider content-position="left">Other</el-divider>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Original Value" prop="originalValue">
              <el-input-number v-model="form.originalValue" :precision="2" :min="0" placeholder="Enter value" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Purchase Date" prop="purchaseDate">
              <el-date-picker v-model="form.purchaseDate" type="date" placeholder="Select date" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="Location Desc" prop="locationDesc">
              <el-input v-model="form.locationDesc" type="textarea" placeholder="Enter location description" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="Remark" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="Enter remark" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" :loading="formLoading">Confirm</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </div>
    </el-dialog>

    <!-- Detail Drawer -->
    <el-drawer
      title="Office Asset Detail"
      :visible.sync="detailOpen"
      direction="rtl"
      size="50%"
      :before-close="handleDetailClose"
    >
      <!-- Header with Asset Code prominently displayed -->
      <div class="detail-header" style="padding: 0 20px 20px;">
        <div style="display: flex; justify-content: space-between; align-items: center;">
          <div>
            <h2 style="margin: 0; font-size: 24px; color: #303133;">{{ detail.assetCode }}</h2>
            <p style="margin: 8px 0 0; color: #909399;">{{ detail.assetName }}</p>
          </div>
          <div>
            <dict-tag :options="dict.type.asset_status" :value="detail.status"/>
          </div>
        </div>
        <!-- Action Buttons -->
        <div style="margin-top: 16px;">
          <el-button
            type="primary"
            size="small"
            icon="el-icon-edit"
            @click="handleDetailEdit"
            v-hasPermi="['asset:office:edit']"
          >Edit</el-button>
          <el-button
            type="warning"
            size="small"
            icon="el-icon-setting"
            @click="handleCreateMaintOrder"
          >Initiate Maintenance</el-button>
          <el-button
            type="danger"
            size="small"
            icon="el-icon-delete"
            @click="handleDetailDelete"
            v-hasPermi="['asset:office:remove']"
          >Delete</el-button>
        </div>

        <!-- Office Info Card - Quick Access -->
        <div class="office-info-card" v-if="detail.useUser || detail.workplaceNo">
          <div class="office-info-icon">
            <i class="el-icon-monitor"></i>
          </div>
          <div class="office-info-content">
            <div class="office-info-label">Assigned User</div>
            <div class="office-info-name">{{ detail.useUser || '-' }}</div>
            <div class="office-info-meta">
              <span v-if="detail.workplaceNo">Workplace: {{ detail.workplaceNo }}</span>
              <span v-if="detail.officeTypeLabel" style="margin-left: 12px;">Type: {{ detail.officeTypeLabel }}</span>
            </div>
          </div>
        </div>
      </div>

      <!-- Tabbed Layout -->
      <el-tabs v-model="detailActiveTab" style="padding: 0 20px;">
        <!-- Basic Info Tab -->
        <el-tab-pane label="Basic Info" name="basic">
          <el-descriptions :column="2" border>
            <el-descriptions-item label="Project">{{ detail.projectName }}</el-descriptions-item>
            <el-descriptions-item label="Status">
              <dict-tag :options="dict.type.asset_status" :value="detail.status"/>
            </el-descriptions-item>
            <el-descriptions-item label="Building">{{ detail.building || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Floor">{{ detail.floor || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Room No">{{ detail.roomNo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Location">{{ detail.locationDesc || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Original Value">{{ detail.originalValue ? '¥' + detail.originalValue : '-' }}</el-descriptions-item>
            <el-descriptions-item label="Net Value">{{ detail.netValue ? '¥' + detail.netValue : '-' }}</el-descriptions-item>
            <el-descriptions-item label="Create Time">{{ detail.createTime }}</el-descriptions-item>
            <el-descriptions-item label="Update Time">{{ detail.updateTime }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>

        <!-- Office Info Tab -->
        <el-tab-pane label="Office Info" name="office">
          <el-descriptions :column="2" border>
            <el-descriptions-item label="Office Type">{{ detail.officeTypeLabel || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Workplace No">{{ detail.workplaceNo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Use User">{{ detail.useUser || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Serial No">{{ detail.serialNo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Brand">{{ detail.brand || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Model">{{ detail.model || '-' }}</el-descriptions-item>
            <el-descriptions-item label="OS Info" :span="2">{{ detail.osInfo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Config Desc" :span="2">{{ detail.configDesc || '-' }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>

        <!-- Maintenance Tab -->
        <el-tab-pane label="Maintenance" name="maintenance">
          <div style="text-align: center; padding: 20px;">
            <el-empty description="No maintenance records">
              <el-button
                type="primary"
                size="small"
                @click="handleCreateMaintOrder"
              >Create Maintenance Order</el-button>
            </el-empty>
          </div>
          <div style="margin-top: 20px;">
            <el-link type="primary" @click="goToMaintOrders">
              View All Maintenance Orders <i class="el-icon-arrow-right"></i>
            </el-link>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-drawer>
  </div>
</template>

<script>
import { getOfficeAssetList, getOfficeAsset, addOfficeAsset, updateOfficeAsset, deleteOfficeAsset } from "@/api/asset/office";

export default {
  name: "OfficeAsset",
  dicts: ['asset_status', 'office_type'],
  data() {
    return {
      // Loading state
      loading: true,
      // Form loading state
      formLoading: false,
      // Selected IDs
      ids: [],
      // Single selection
      single: true,
      // Multiple selection
      multiple: true,
      // Show search
      showSearch: true,
      // Total count
      total: 0,
      // Office list data
      officeList: [],
      // Dialog title
      title: "",
      // Dialog visibility
      open: false,
      // Detail drawer visibility
      detailOpen: false,
      // Detail active tab
      detailActiveTab: 'basic',
      // Detail data
      detail: {},
      // Project options
      projectOptions: [],
      // Query params
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectId: null,
        officeType: null,
        assetName: null,
        useUser: null,
        status: null
      },
      // Form data
      form: {},
      // Form rules
      rules: {
        assetName: [
          { required: true, message: "Asset name is required", trigger: "blur" }
        ],
        projectId: [
          { required: true, message: "Project is required", trigger: "change" }
        ]
      },
      // Column visibility configuration
      columns: [
        { key: 0, label: 'Asset Code', visible: true },
        { key: 1, label: 'Asset Name', visible: true },
        { key: 2, label: 'Project', visible: true },
        { key: 3, label: 'Office Type', visible: true },
        { key: 4, label: 'Workplace No', visible: true },
        { key: 5, label: 'Use User', visible: true },
        { key: 6, label: 'Brand/Model', visible: true },
        { key: 7, label: 'Serial No', visible: false },
        { key: 8, label: 'Status', visible: true }
      ]
    };
  },
  created() {
    this.getList();
    this.loadProjectOptions();
  },
  methods: {
    /** Query office list */
    getList() {
      this.loading = true;
      getOfficeAssetList(this.queryParams).then(response => {
        this.officeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** Load project options */
    loadProjectOptions() {
      // TODO: Load from project API
      this.projectOptions = [];
    },
    /** Cancel button */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** Reset form */
    reset() {
      this.form = {
        id: null,
        assetCode: null,
        assetName: null,
        projectId: null,
        building: null,
        floor: null,
        roomNo: null,
        locationDesc: null,
        officeType: null,
        workplaceNo: null,
        useUser: null,
        serialNo: null,
        osInfo: null,
        configDesc: null,
        brand: null,
        model: null,
        originalValue: null,
        purchaseDate: null,
        remark: null
      };
      this.formLoading = false;
      this.resetForm("form");
    },
    /** Search button */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** Reset button */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** Selection change */
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** Add button */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "Add Office Asset";
    },
    /** Update button */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids[0];
      getOfficeAsset(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "Edit Office Asset";
      });
    },
    /** View button */
    handleView(row) {
      getOfficeAsset(row.id).then(response => {
        this.detail = response.data;
        this.detailOpen = true;
      });
    },
    /** Submit form */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.formLoading = true;
          if (this.form.id != null) {
            updateOfficeAsset(this.form).then(response => {
              this.$modal.msgSuccess("Updated successfully");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.formLoading = false;
            });
          } else {
            addOfficeAsset(this.form).then(response => {
              this.$modal.msgSuccess("Added successfully");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.formLoading = false;
            });
          }
        }
      });
    },
    /** Delete button */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('Are you sure you want to delete the selected office asset(s)?').then(() => {
        return deleteOfficeAsset(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("Deleted successfully");
      }).catch(() => {});
    },
    /** Export button */
    handleExport() {
      this.download('asset/office/export', {
        ...this.queryParams
      }, `office_${new Date().getTime()}.xlsx`);
    },
    /** Handle dropdown command */
    handleCommand(command, row) {
      switch (command) {
        case 'maintenance':
          this.handleInitiateMaintenance(row);
          break;
        case 'delete':
          this.handleDelete(row);
          break;
      }
    },
    /** Initiate maintenance for office */
    handleInitiateMaintenance(row) {
      this.$router.push({
        path: '/maintenance/order/add',
        query: { assetId: row.id, assetCode: row.assetCode }
      });
    },
    /** Handle detail drawer close */
    handleDetailClose(done) {
      this.detailActiveTab = 'basic';
      done();
    },
    /** Edit from detail drawer */
    handleDetailEdit() {
      this.detailOpen = false;
      this.handleUpdate(this.detail);
    },
    /** Delete from detail drawer */
    handleDetailDelete() {
      this.$modal.confirm('Are you sure you want to delete this office asset?').then(() => {
        return deleteOfficeAsset(this.detail.id);
      }).then(() => {
        this.detailOpen = false;
        this.getList();
        this.$modal.msgSuccess("Deleted successfully");
      }).catch(() => {});
    },
    /** Create maintenance order */
    handleCreateMaintOrder() {
      this.$router.push({
        path: '/maintenance/order/add',
        query: { assetId: this.detail.id, assetCode: this.detail.assetCode }
      });
    },
    /** Go to maintenance orders list */
    goToMaintOrders() {
      this.$router.push({
        path: '/maintenance/order',
        query: { assetId: this.detail.id }
      });
    }
  }
};
</script>

<style scoped>
.office-info-card {
  margin-top: 16px;
  padding: 12px 16px;
  background: linear-gradient(135deg, #e2f3fe 0%, #c9e8fd 100%);
  border-radius: 8px;
  display: flex;
  align-items: center;
  border-left: 4px solid #409EFF;
}

.office-info-icon {
  width: 40px;
  height: 40px;
  background: #409EFF;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 12px;
  flex-shrink: 0;
}

.office-info-icon i {
  font-size: 20px;
  color: #fff;
}

.office-info-content {
  flex: 1;
}

.office-info-label {
  font-size: 12px;
  color: #909399;
  margin-bottom: 2px;
}

.office-info-name {
  font-size: 18px;
  font-weight: 600;
  color: #303133;
}

.office-info-meta {
  font-size: 13px;
  color: #606266;
  margin-top: 4px;
}
</style>
