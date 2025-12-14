<template>
  <div class="app-container">
    <!-- Search/Filter Area -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="House Type" prop="houseType">
        <el-select v-model="queryParams.houseType" placeholder="Select type" clearable>
          <el-option
            v-for="dict in dict.type.house_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Current Usage" prop="currentUsage">
        <el-select v-model="queryParams.currentUsage" placeholder="Select usage" clearable>
          <el-option label="Self Use" value="self" />
          <el-option label="Rent" value="rent" />
          <el-option label="Idle" value="idle" />
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
          v-hasPermi="['asset:house:add']"
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
          v-hasPermi="['asset:house:edit']"
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
          v-hasPermi="['asset:house:remove']"
        >Delete</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:house:export']"
        >Export</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <!-- Data Table -->
    <el-table v-loading="loading" :data="houseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Asset Code" align="center" prop="assetCode" v-if="columns[0].visible" />
      <el-table-column label="Asset Name" align="center" prop="assetName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
      <el-table-column label="Project" align="center" prop="projectName" v-if="columns[2].visible" />
      <el-table-column label="Building" align="center" prop="building" v-if="columns[3].visible" />
      <el-table-column label="Floor" align="center" prop="floor" v-if="columns[4].visible" />
      <el-table-column label="Room No" align="center" prop="roomNo" v-if="columns[5].visible" />
      <el-table-column label="Building Area" align="center" prop="buildingArea" v-if="columns[6].visible">
        <template slot-scope="scope">
          {{ scope.row.buildingArea }} sqm
        </template>
      </el-table-column>
      <el-table-column label="House Type" align="center" prop="houseTypeLabel" v-if="columns[7].visible" />
      <el-table-column label="House Usage" align="center" prop="houseUsageLabel" v-if="columns[8].visible" />
      <el-table-column label="Current Usage" align="center" prop="currentUsage" v-if="columns[9].visible">
        <template slot-scope="scope">
          <el-tag :type="getUsageTagType(scope.row.currentUsage)">
            {{ getUsageLabel(scope.row.currentUsage) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Current User" align="center" prop="currentUser" v-if="columns[10].visible" />
      <el-table-column label="Rent Total" align="center" prop="rentTotal" v-if="columns[11].visible">
        <template slot-scope="scope">
          {{ scope.row.rentTotal ? '¥' + scope.row.rentTotal : '-' }}
        </template>
      </el-table-column>
      <el-table-column label="Status" align="center" prop="statusLabel" v-if="columns[12].visible">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.asset_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" class-name="small-padding fixed-width" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['asset:house:query']"
          >View</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:house:edit']"
          >Edit</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:house:remove']"
          >Delete</el-button>
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
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="Asset Name" prop="assetName">
              <el-input v-model="form.assetName" placeholder="Enter asset name" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
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
          </el-col>
        </el-row>
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
        <el-row>
          <el-col :span="12">
            <el-form-item label="Building Area" prop="buildingArea">
              <el-input-number v-model="form.buildingArea" :precision="2" :min="0" placeholder="sqm" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Inner Area" prop="innerArea">
              <el-input-number v-model="form.innerArea" :precision="2" :min="0" placeholder="sqm" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="House Type" prop="houseType">
              <el-select v-model="form.houseType" placeholder="Select type" style="width: 100%">
                <el-option
                  v-for="dict in dict.type.house_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="House Usage" prop="houseUsage">
              <el-select v-model="form.houseUsage" placeholder="Select usage" style="width: 100%">
                <el-option
                  v-for="dict in dict.type.house_usage"
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
            <el-form-item label="Current Usage" prop="currentUsage">
              <el-select v-model="form.currentUsage" placeholder="Select" style="width: 100%">
                <el-option label="Self Use" value="self" />
                <el-option label="Rent" value="rent" />
                <el-option label="Idle" value="idle" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Current User" prop="currentUser">
              <el-input v-model="form.currentUser" placeholder="Enter current user/tenant" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Rent Unit Price" prop="rentUnitPrice">
              <el-input-number v-model="form.rentUnitPrice" :precision="2" :min="0" placeholder="per sqm" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Rent Total" prop="rentTotal">
              <el-input-number v-model="form.rentTotal" :precision="2" :min="0" placeholder="total" style="width: 100%" />
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
        <el-button type="primary" @click="submitForm">Confirm</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </div>
    </el-dialog>

    <!-- Detail Dialog -->
    <el-dialog title="House Asset Detail" :visible.sync="detailOpen" width="800px" append-to-body>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="Asset Code">{{ detail.assetCode }}</el-descriptions-item>
        <el-descriptions-item label="Asset Name">{{ detail.assetName }}</el-descriptions-item>
        <el-descriptions-item label="Project">{{ detail.projectName }}</el-descriptions-item>
        <el-descriptions-item label="Status">
          <dict-tag :options="dict.type.asset_status" :value="detail.status"/>
        </el-descriptions-item>
        <el-descriptions-item label="Building">{{ detail.building }}</el-descriptions-item>
        <el-descriptions-item label="Floor">{{ detail.floor }}</el-descriptions-item>
        <el-descriptions-item label="Room No">{{ detail.roomNo }}</el-descriptions-item>
        <el-descriptions-item label="Location">{{ detail.locationDesc }}</el-descriptions-item>
        <el-descriptions-item label="Building Area">{{ detail.buildingArea }} sqm</el-descriptions-item>
        <el-descriptions-item label="Inner Area">{{ detail.innerArea }} sqm</el-descriptions-item>
        <el-descriptions-item label="House Type">{{ detail.houseTypeLabel }}</el-descriptions-item>
        <el-descriptions-item label="House Usage">{{ detail.houseUsageLabel }}</el-descriptions-item>
        <el-descriptions-item label="Current Usage">{{ getUsageLabel(detail.currentUsage) }}</el-descriptions-item>
        <el-descriptions-item label="Current User">{{ detail.currentUser || '-' }}</el-descriptions-item>
        <el-descriptions-item label="Rent Unit Price">{{ detail.rentUnitPrice ? '¥' + detail.rentUnitPrice + '/sqm' : '-' }}</el-descriptions-item>
        <el-descriptions-item label="Rent Total">{{ detail.rentTotal ? '¥' + detail.rentTotal : '-' }}</el-descriptions-item>
        <el-descriptions-item label="Contract No">{{ detail.contractNo || '-' }}</el-descriptions-item>
        <el-descriptions-item label="Original Value">{{ detail.originalValue ? '¥' + detail.originalValue : '-' }}</el-descriptions-item>
        <el-descriptions-item label="Create Time">{{ detail.createTime }}</el-descriptions-item>
        <el-descriptions-item label="Update Time">{{ detail.updateTime }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script>
import { getHouseAssetList, getHouseAsset, addHouseAsset, updateHouseAsset, deleteHouseAsset } from "@/api/asset/house";

export default {
  name: "HouseAsset",
  dicts: ['asset_status', 'house_type', 'house_usage'],
  data() {
    return {
      // Loading state
      loading: true,
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
      // House list data
      houseList: [],
      // Dialog title
      title: "",
      // Dialog visibility
      open: false,
      // Detail dialog visibility
      detailOpen: false,
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
        status: null,
        houseType: null,
        currentUsage: null
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
        buildingArea: [
          { required: true, message: "Building area is required", trigger: "blur" }
        ]
      },
      // Column visibility configuration
      columns: [
        { key: 0, label: 'Asset Code', visible: true },
        { key: 1, label: 'Asset Name', visible: true },
        { key: 2, label: 'Project', visible: true },
        { key: 3, label: 'Building', visible: true },
        { key: 4, label: 'Floor', visible: true },
        { key: 5, label: 'Room No', visible: true },
        { key: 6, label: 'Building Area', visible: true },
        { key: 7, label: 'House Type', visible: true },
        { key: 8, label: 'House Usage', visible: false },
        { key: 9, label: 'Current Usage', visible: true },
        { key: 10, label: 'Current User', visible: false },
        { key: 11, label: 'Rent Total', visible: true },
        { key: 12, label: 'Status', visible: true }
      ]
    };
  },
  created() {
    this.getList();
    this.loadProjectOptions();
  },
  methods: {
    /** Query house list */
    getList() {
      this.loading = true;
      getHouseAssetList(this.queryParams).then(response => {
        this.houseList = response.rows;
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
        assetName: null,
        projectId: null,
        building: null,
        floor: null,
        roomNo: null,
        locationDesc: null,
        buildingArea: null,
        innerArea: null,
        houseType: null,
        houseUsage: null,
        currentUsage: null,
        currentUser: null,
        rentUnitPrice: null,
        rentTotal: null,
        remark: null
      };
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
      this.title = "Add House Asset";
    },
    /** Update button */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids[0];
      getHouseAsset(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "Edit House Asset";
      });
    },
    /** View button */
    handleView(row) {
      getHouseAsset(row.id).then(response => {
        this.detail = response.data;
        this.detailOpen = true;
      });
    },
    /** Submit form */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateHouseAsset(this.form).then(response => {
              this.$modal.msgSuccess("Updated successfully");
              this.open = false;
              this.getList();
            });
          } else {
            addHouseAsset(this.form).then(response => {
              this.$modal.msgSuccess("Added successfully");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** Delete button */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('Are you sure you want to delete the selected house asset(s)?').then(() => {
        return deleteHouseAsset(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("Deleted successfully");
      }).catch(() => {});
    },
    /** Export button */
    handleExport() {
      this.download('asset/house/export', {
        ...this.queryParams
      }, `house_${new Date().getTime()}.xlsx`);
    },
    /** Get usage tag type */
    getUsageTagType(usage) {
      const types = {
        'self': 'success',
        'rent': 'primary',
        'idle': 'info'
      };
      return types[usage] || 'info';
    },
    /** Get usage label */
    getUsageLabel(usage) {
      const labels = {
        'self': 'Self Use',
        'rent': 'Rent',
        'idle': 'Idle'
      };
      return labels[usage] || usage || '-';
    }
  }
};
</script>
