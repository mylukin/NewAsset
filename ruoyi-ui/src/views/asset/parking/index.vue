<template>
  <div class="app-container">
    <!-- Search/Filter Area -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">
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
      <el-form-item label="Parking Zone" prop="parkingZone">
        <el-select v-model="queryParams.parkingZone" placeholder="Select zone" clearable>
          <el-option
            v-for="dict in dict.type.parking_zone"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Parking No" prop="parkingNo">
        <el-input
          v-model="queryParams.parkingNo"
          placeholder="Enter parking no"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Parking Type" prop="parkingType">
        <el-select v-model="queryParams.parkingType" placeholder="Select type" clearable>
          <el-option
            v-for="dict in dict.type.parking_type"
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
          v-hasPermi="['asset:parking:add']"
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
          v-hasPermi="['asset:parking:edit']"
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
          v-hasPermi="['asset:parking:remove']"
        >Delete</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:parking:export']"
        >Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <!-- Data Table -->
    <el-table v-loading="loading" :data="parkingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Asset Code" align="center" prop="assetCode" v-if="columns[0].visible" />
      <el-table-column label="Asset Name" align="center" prop="assetName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
      <el-table-column label="Project" align="center" prop="projectName" v-if="columns[2].visible" />
      <el-table-column label="Parking No" align="center" prop="parkingNo" v-if="columns[3].visible" />
      <el-table-column label="Parking Zone" align="center" prop="parkingZoneLabel" v-if="columns[4].visible" />
      <el-table-column label="Parking Type" align="center" prop="parkingTypeLabel" v-if="columns[5].visible" />
      <el-table-column label="Area (m²)" align="center" prop="area" v-if="columns[6].visible" />
      <el-table-column label="Current User" align="center" prop="currentUser" v-if="columns[7].visible" :show-overflow-tooltip="true" />
      <el-table-column label="Plate No" align="center" prop="plateNo" v-if="columns[8].visible" />
      <el-table-column label="Rent Price" align="center" prop="rentPrice" v-if="columns[9].visible">
        <template slot-scope="scope">
          {{ scope.row.rentPrice ? '¥' + scope.row.rentPrice : '-' }}
        </template>
      </el-table-column>
      <el-table-column label="Status" align="center" prop="statusLabel" v-if="columns[10].visible">
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
            v-hasPermi="['asset:parking:query']"
          >View</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:parking:edit']"
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

        <!-- Parking Info Group -->
        <el-divider content-position="left">Parking Information</el-divider>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Parking No" prop="parkingNo">
              <el-input v-model="form.parkingNo" placeholder="Enter parking number" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Parking Zone" prop="parkingZone">
              <el-select v-model="form.parkingZone" placeholder="Select zone" style="width: 100%">
                <el-option
                  v-for="dict in dict.type.parking_zone"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Parking Type" prop="parkingType">
              <el-select v-model="form.parkingType" placeholder="Select type" style="width: 100%">
                <el-option
                  v-for="dict in dict.type.parking_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Area (m²)" prop="area">
              <el-input-number v-model="form.area" :precision="2" :min="0" placeholder="Enter area" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>

        <!-- Usage Info Group -->
        <el-divider content-position="left">Usage Information</el-divider>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Current User" prop="currentUser">
              <el-input v-model="form.currentUser" placeholder="Enter current user" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Plate No" prop="plateNo">
              <el-input v-model="form.plateNo" placeholder="Enter plate number" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Rent Price" prop="rentPrice">
              <el-input-number v-model="form.rentPrice" :precision="2" :min="0" placeholder="Enter rent price" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Contract No" prop="contractNo">
              <el-input v-model="form.contractNo" placeholder="Enter contract number" />
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
      title="Parking Asset Detail"
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
            v-hasPermi="['asset:parking:edit']"
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
            v-hasPermi="['asset:parking:remove']"
          >Delete</el-button>
        </div>

        <!-- Parking Info Card - Quick Access -->
        <div class="parking-info-card" v-if="detail.parkingNo || detail.currentUser">
          <div class="parking-info-icon">
            <i class="el-icon-place"></i>
          </div>
          <div class="parking-info-content">
            <div class="parking-info-label">Parking Space</div>
            <div class="parking-info-no">{{ detail.parkingNo || '-' }}</div>
            <div class="parking-info-user" v-if="detail.currentUser">
              <span>User: {{ detail.currentUser }}</span>
              <span v-if="detail.plateNo" style="margin-left: 12px;">Plate: {{ detail.plateNo }}</span>
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

        <!-- Parking Info Tab -->
        <el-tab-pane label="Parking Info" name="parking">
          <el-descriptions :column="2" border>
            <el-descriptions-item label="Parking No">{{ detail.parkingNo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Parking Zone">{{ detail.parkingZoneLabel || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Parking Type">{{ detail.parkingTypeLabel || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Area (m²)">{{ detail.area || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Current User">{{ detail.currentUser || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Plate No">{{ detail.plateNo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Rent Price">{{ detail.rentPrice ? '¥' + detail.rentPrice + '/month' : '-' }}</el-descriptions-item>
            <el-descriptions-item label="Contract No">{{ detail.contractNo || '-' }}</el-descriptions-item>
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
import { getParkingAssetList, getParkingAsset, addParkingAsset, updateParkingAsset, deleteParkingAsset } from "@/api/asset/parking";

export default {
  name: "ParkingAsset",
  dicts: ['asset_status', 'parking_zone', 'parking_type'],
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
      // Parking list data
      parkingList: [],
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
        parkingZone: null,
        parkingNo: null,
        parkingType: null,
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
        parkingNo: [
          { required: true, message: "Parking number is required", trigger: "blur" }
        ]
      },
      // Column visibility configuration
      columns: [
        { key: 0, label: 'Asset Code', visible: true },
        { key: 1, label: 'Asset Name', visible: true },
        { key: 2, label: 'Project', visible: true },
        { key: 3, label: 'Parking No', visible: true },
        { key: 4, label: 'Parking Zone', visible: true },
        { key: 5, label: 'Parking Type', visible: true },
        { key: 6, label: 'Area', visible: false },
        { key: 7, label: 'Current User', visible: true },
        { key: 8, label: 'Plate No', visible: true },
        { key: 9, label: 'Rent Price', visible: false },
        { key: 10, label: 'Status', visible: true }
      ]
    };
  },
  created() {
    this.getList();
    this.loadProjectOptions();
  },
  methods: {
    /** Query parking list */
    getList() {
      this.loading = true;
      getParkingAssetList(this.queryParams).then(response => {
        this.parkingList = response.rows;
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
        parkingNo: null,
        parkingZone: null,
        parkingType: null,
        area: null,
        currentUser: null,
        plateNo: null,
        rentPrice: null,
        contractNo: null,
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
      this.title = "Add Parking Asset";
    },
    /** Update button */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids[0];
      getParkingAsset(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "Edit Parking Asset";
      });
    },
    /** View button */
    handleView(row) {
      getParkingAsset(row.id).then(response => {
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
            updateParkingAsset(this.form).then(response => {
              this.$modal.msgSuccess("Updated successfully");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.formLoading = false;
            });
          } else {
            addParkingAsset(this.form).then(response => {
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
      this.$modal.confirm('Are you sure you want to delete the selected parking asset(s)?').then(() => {
        return deleteParkingAsset(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("Deleted successfully");
      }).catch(() => {});
    },
    /** Export button */
    handleExport() {
      this.download('asset/parking/export', {
        ...this.queryParams
      }, `parking_${new Date().getTime()}.xlsx`);
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
    /** Initiate maintenance for parking */
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
      this.$modal.confirm('Are you sure you want to delete this parking asset?').then(() => {
        return deleteParkingAsset(this.detail.id);
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
.parking-info-card {
  margin-top: 16px;
  padding: 12px 16px;
  background: linear-gradient(135deg, #e8f4ff 0%, #d4e8ff 100%);
  border-radius: 8px;
  display: flex;
  align-items: center;
  border-left: 4px solid #409EFF;
}

.parking-info-icon {
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

.parking-info-icon i {
  font-size: 20px;
  color: #fff;
}

.parking-info-content {
  flex: 1;
}

.parking-info-label {
  font-size: 12px;
  color: #909399;
  margin-bottom: 2px;
}

.parking-info-no {
  font-size: 18px;
  font-weight: 600;
  color: #303133;
}

.parking-info-user {
  font-size: 13px;
  color: #606266;
  margin-top: 4px;
}
</style>
