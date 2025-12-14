<template>
  <div class="app-container">
    <!-- Search/Filter Area -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">
      <el-form-item label="Asset Name" prop="assetName">
        <el-input
          v-model="queryParams.assetName"
          placeholder="Enter asset name"
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
      <el-form-item label="Building" prop="building">
        <el-input
          v-model="queryParams.building"
          placeholder="Enter building"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Floor" prop="floor">
        <el-input
          v-model="queryParams.floor"
          placeholder="Enter floor"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Facility Type" prop="facilityType">
        <el-select v-model="queryParams.facilityType" placeholder="Select type" clearable>
          <el-option
            v-for="dict in dict.type.facility_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['asset:facility:add']"
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
          v-hasPermi="['asset:facility:edit']"
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
          v-hasPermi="['asset:facility:remove']"
        >Delete</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:facility:export']"
        >Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <!-- Data Table -->
    <el-table v-loading="loading" :data="facilityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Asset Code" align="center" prop="assetCode" v-if="columns[0].visible" />
      <el-table-column label="Asset Name" align="center" prop="assetName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
      <el-table-column label="Project" align="center" prop="projectName" v-if="columns[2].visible" />
      <el-table-column label="Building" align="center" prop="building" v-if="columns[3].visible" />
      <el-table-column label="Floor" align="center" prop="floor" v-if="columns[4].visible" />
      <el-table-column label="Facility Type" align="center" prop="facilityTypeLabel" v-if="columns[5].visible" />
      <el-table-column label="Equipment No" align="center" prop="equipmentNo" v-if="columns[6].visible" />
      <el-table-column label="Install Position" align="center" prop="installPosition" v-if="columns[7].visible" :show-overflow-tooltip="true" />
      <el-table-column label="Maint Org" align="center" prop="maintOrg" v-if="columns[8].visible" :show-overflow-tooltip="true" />
      <el-table-column label="Status" align="center" prop="statusLabel" v-if="columns[9].visible">
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
            v-hasPermi="['asset:facility:query']"
          >View</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:facility:edit']"
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

        <!-- Facility Info Group -->
        <el-divider content-position="left">Facility Information</el-divider>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Facility Type" prop="facilityType">
              <el-select v-model="form.facilityType" placeholder="Select type" style="width: 100%">
                <el-option
                  v-for="dict in dict.type.facility_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Equipment No" prop="equipmentNo">
              <el-input v-model="form.equipmentNo" placeholder="Enter equipment number" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="Install Position" prop="installPosition">
              <el-input v-model="form.installPosition" placeholder="Enter installation position" />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- Maintenance Info Group -->
        <el-divider content-position="left">Maintenance</el-divider>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Maint Org" prop="maintOrg">
              <el-input v-model="form.maintOrg" placeholder="Enter maintenance organization" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Maint Phone" prop="maintPhone">
              <el-input v-model="form.maintPhone" placeholder="Enter maintenance phone" />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- Other Fields -->
        <el-divider content-position="left">Other</el-divider>
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
      title="Facility Asset Detail"
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
            v-hasPermi="['asset:facility:edit']"
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
            v-hasPermi="['asset:facility:remove']"
          >Delete</el-button>
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

        <!-- Facility Info Tab -->
        <el-tab-pane label="Facility Info" name="facility">
          <el-descriptions :column="2" border>
            <el-descriptions-item label="Facility Type">{{ detail.facilityTypeLabel || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Equipment No">{{ detail.equipmentNo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Install Position" :span="2">{{ detail.installPosition || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Maint Org">{{ detail.maintOrg || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Maint Phone">{{ detail.maintPhone || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Brand">{{ detail.brand || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Model">{{ detail.model || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Supplier">{{ detail.supplier || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Warranty Expire">{{ detail.warrantyExpireDate || '-' }}</el-descriptions-item>
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
import { getFacilityAssetList, getFacilityAsset, addFacilityAsset, updateFacilityAsset, deleteFacilityAsset } from "@/api/asset/facility";

export default {
  name: "FacilityAsset",
  dicts: ['asset_status', 'facility_type'],
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
      // Facility list data
      facilityList: [],
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
        assetName: null,
        projectId: null,
        building: null,
        floor: null,
        facilityType: null,
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
        ],
        facilityType: [
          { required: true, message: "Facility type is required", trigger: "change" }
        ]
      },
      // Column visibility configuration
      columns: [
        { key: 0, label: 'Asset Code', visible: true },
        { key: 1, label: 'Asset Name', visible: true },
        { key: 2, label: 'Project', visible: true },
        { key: 3, label: 'Building', visible: true },
        { key: 4, label: 'Floor', visible: true },
        { key: 5, label: 'Facility Type', visible: true },
        { key: 6, label: 'Equipment No', visible: true },
        { key: 7, label: 'Install Position', visible: false },
        { key: 8, label: 'Maint Org', visible: false },
        { key: 9, label: 'Status', visible: true }
      ]
    };
  },
  created() {
    this.getList();
    this.loadProjectOptions();
  },
  methods: {
    /** Query facility list */
    getList() {
      this.loading = true;
      getFacilityAssetList(this.queryParams).then(response => {
        this.facilityList = response.rows;
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
        facilityType: null,
        equipmentNo: null,
        installPosition: null,
        maintOrg: null,
        maintPhone: null,
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
      this.title = "Add Facility Asset";
    },
    /** Update button */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids[0];
      getFacilityAsset(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "Edit Facility Asset";
      });
    },
    /** View button */
    handleView(row) {
      getFacilityAsset(row.id).then(response => {
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
            updateFacilityAsset(this.form).then(response => {
              this.$modal.msgSuccess("Updated successfully");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.formLoading = false;
            });
          } else {
            addFacilityAsset(this.form).then(response => {
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
      this.$modal.confirm('Are you sure you want to delete the selected facility asset(s)?').then(() => {
        return deleteFacilityAsset(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("Deleted successfully");
      }).catch(() => {});
    },
    /** Export button */
    handleExport() {
      this.download('asset/facility/export', {
        ...this.queryParams
      }, `facility_${new Date().getTime()}.xlsx`);
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
    /** Initiate maintenance for facility */
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
      this.$modal.confirm('Are you sure you want to delete this facility asset?').then(() => {
        return deleteFacilityAsset(this.detail.id);
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
