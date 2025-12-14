<template>
  <div class="app-container">
    <!-- Statistics Section -->
    <el-card class="box-card statistics-card" shadow="hover" v-loading="statsLoading">
      <div slot="header" class="clearfix">
        <span>House Asset Statistics</span>
        <el-select
          v-model="statsProjectId"
          placeholder="All Projects"
          clearable
          size="small"
          style="float: right; width: 200px;"
          @change="loadStatistics"
        >
          <el-option
            v-for="item in projectOptions"
            :key="item.id"
            :label="item.projectName"
            :value="item.id"
          />
        </el-select>
      </div>
      <div v-if="statsError" class="stats-error">
        <i class="el-icon-warning-outline"></i>
        <span>{{ statsError }}</span>
        <el-button type="text" @click="loadStatistics">Retry</el-button>
      </div>
      <el-row :gutter="20" v-else>
        <!-- Total Count Card -->
        <el-col :xs="24" :sm="12" :md="6" :lg="6">
          <div class="stat-item">
            <div class="stat-icon" style="background: #409EFF;">
              <i class="el-icon-office-building"></i>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ statistics.totalCount || 0 }}</div>
              <div class="stat-label">Total Houses</div>
            </div>
          </div>
        </el-col>
        <!-- Self Use Card -->
        <el-col :xs="24" :sm="12" :md="6" :lg="6">
          <div class="stat-item">
            <div class="stat-icon" style="background: #67C23A;">
              <i class="el-icon-house"></i>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ statistics.selfUseCount || 0 }}</div>
              <div class="stat-label">Self Use</div>
              <el-progress
                :percentage="statistics.selfUseRate || 0"
                :stroke-width="6"
                :show-text="false"
                color="#67C23A"
              ></el-progress>
              <div class="stat-rate">{{ (statistics.selfUseRate || 0).toFixed(1) }}%</div>
            </div>
          </div>
        </el-col>
        <!-- Rent Card -->
        <el-col :xs="24" :sm="12" :md="6" :lg="6">
          <div class="stat-item">
            <div class="stat-icon" style="background: #E6A23C;">
              <i class="el-icon-money"></i>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ statistics.rentCount || 0 }}</div>
              <div class="stat-label">Rented Out</div>
              <el-progress
                :percentage="statistics.rentRate || 0"
                :stroke-width="6"
                :show-text="false"
                color="#E6A23C"
              ></el-progress>
              <div class="stat-rate">{{ (statistics.rentRate || 0).toFixed(1) }}%</div>
            </div>
          </div>
        </el-col>
        <!-- Idle Card -->
        <el-col :xs="24" :sm="12" :md="6" :lg="6">
          <div class="stat-item">
            <div class="stat-icon" style="background: #909399;">
              <i class="el-icon-question"></i>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ statistics.idleCount || 0 }}</div>
              <div class="stat-label">Idle</div>
              <el-progress
                :percentage="statistics.idleRate || 0"
                :stroke-width="6"
                :show-text="false"
                color="#909399"
              ></el-progress>
              <div class="stat-rate">{{ (statistics.idleRate || 0).toFixed(1) }}%</div>
            </div>
          </div>
        </el-col>
      </el-row>
      <!-- Area Statistics Row -->
      <el-row :gutter="20" style="margin-top: 20px;" v-if="!statsError">
        <el-col :xs="24" :sm="12" :md="8">
          <div class="area-stat">
            <span class="area-label">Total Area:</span>
            <span class="area-value">{{ formatArea(statistics.totalBuildingArea) }}</span>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" :md="8">
          <div class="area-stat">
            <span class="area-label">Rented Area:</span>
            <span class="area-value">{{ formatArea(statistics.rentArea) }}</span>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" :md="8">
          <div class="area-stat">
            <span class="area-label">Total Rent Income:</span>
            <span class="area-value rent-income">¥{{ formatMoney(statistics.totalRentIncome) }}</span>
          </div>
        </el-col>
      </el-row>
    </el-card>

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
        <!-- Area Group -->
        <el-divider content-position="left">Area Information</el-divider>
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

        <!-- Property Type Group -->
        <el-divider content-position="left">Property Type</el-divider>
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

        <!-- Usage & Rental Group -->
        <el-divider content-position="left">Usage & Rental</el-divider>
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

    <!-- Detail Drawer -->
    <el-drawer
      title="House Asset Detail"
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
            v-hasPermi="['asset:house:edit']"
          >Edit</el-button>
          <el-button
            type="danger"
            size="small"
            icon="el-icon-delete"
            @click="handleDetailDelete"
            v-hasPermi="['asset:house:remove']"
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
            <el-descriptions-item label="Building Area">{{ detail.buildingArea ? detail.buildingArea + ' sqm' : '-' }}</el-descriptions-item>
            <el-descriptions-item label="Inner Area">{{ detail.innerArea ? detail.innerArea + ' sqm' : '-' }}</el-descriptions-item>
            <el-descriptions-item label="House Type">{{ detail.houseTypeLabel || '-' }}</el-descriptions-item>
            <el-descriptions-item label="House Usage">{{ detail.houseUsageLabel || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Ownership Type">{{ detail.ownershipTypeLabel || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Owner Org">{{ detail.ownerOrg || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Original Value">{{ detail.originalValue ? '¥' + detail.originalValue : '-' }}</el-descriptions-item>
            <el-descriptions-item label="Net Value">{{ detail.netValue ? '¥' + detail.netValue : '-' }}</el-descriptions-item>
            <el-descriptions-item label="Create Time">{{ detail.createTime }}</el-descriptions-item>
            <el-descriptions-item label="Update Time">{{ detail.updateTime }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>

        <!-- Usage & Rental Tab -->
        <el-tab-pane label="Usage & Rental" name="rental">
          <el-descriptions :column="2" border>
            <el-descriptions-item label="Current Usage">
              <el-tag :type="getUsageTagType(detail.currentUsage)">
                {{ getUsageLabel(detail.currentUsage) }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="Current User/Tenant">{{ detail.currentUser || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Contract No">{{ detail.contractNo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Rent Unit Price">{{ detail.rentUnitPrice ? '¥' + detail.rentUnitPrice + '/sqm' : '-' }}</el-descriptions-item>
            <el-descriptions-item label="Rent Total">{{ detail.rentTotal ? '¥' + detail.rentTotal : '-' }}</el-descriptions-item>
            <el-descriptions-item label="Use Department">{{ detail.useDeptName || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Duty Person">{{ detail.dutyUserName || '-' }}</el-descriptions-item>
            <el-descriptions-item label="Start Use Date">{{ detail.startUseDate || '-' }}</el-descriptions-item>
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
import { getHouseAssetList, getHouseAsset, addHouseAsset, updateHouseAsset, deleteHouseAsset, getHouseStatistics } from "@/api/asset/house";

export default {
  name: "HouseAsset",
  dicts: ['asset_status', 'house_type', 'house_usage'],
  data() {
    return {
      // Loading state
      loading: true,
      // Form loading state
      formLoading: false,
      // Statistics loading state
      statsLoading: false,
      // Statistics error
      statsError: null,
      // Statistics data
      statistics: {},
      // Statistics project filter
      statsProjectId: null,
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
    this.loadStatistics();
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
        assetCode: null,
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
          this.formLoading = true;
          if (this.form.id != null) {
            updateHouseAsset(this.form).then(response => {
              this.$modal.msgSuccess("Updated successfully");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.formLoading = false;
            });
          } else {
            addHouseAsset(this.form).then(response => {
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
      this.$modal.confirm('Are you sure you want to delete this house asset?').then(() => {
        return deleteHouseAsset(this.detail.id);
      }).then(() => {
        this.detailOpen = false;
        this.getList();
        this.$modal.msgSuccess("Deleted successfully");
      }).catch(() => {});
    },
    /** Create maintenance order */
    handleCreateMaintOrder() {
      // TODO: Navigate to create maintenance order with this asset
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
    },
    /** Load statistics */
    loadStatistics() {
      this.statsLoading = true;
      this.statsError = null;
      getHouseStatistics(this.statsProjectId).then(response => {
        this.statistics = response.data || {};
        this.statsLoading = false;
      }).catch(error => {
        this.statsError = 'Failed to load statistics';
        this.statsLoading = false;
        console.error('Statistics load error:', error);
      });
    },
    /** Format area value */
    formatArea(value) {
      if (!value && value !== 0) return '-';
      return value.toLocaleString() + ' sqm';
    },
    /** Format money value */
    formatMoney(value) {
      if (!value && value !== 0) return '0.00';
      return value.toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
    }
  }
};
</script>

<style scoped>
.statistics-card {
  margin-bottom: 20px;
}

.stat-item {
  display: flex;
  align-items: flex-start;
  padding: 15px;
  background: #fafafa;
  border-radius: 4px;
  margin-bottom: 10px;
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 15px;
  flex-shrink: 0;
}

.stat-icon i {
  font-size: 24px;
  color: #fff;
}

.stat-content {
  flex: 1;
  min-width: 0;
}

.stat-value {
  font-size: 28px;
  font-weight: 600;
  color: #303133;
  line-height: 1.2;
}

.stat-label {
  font-size: 14px;
  color: #909399;
  margin-top: 4px;
}

.stat-rate {
  font-size: 12px;
  color: #606266;
  margin-top: 4px;
}

.area-stat {
  padding: 10px 15px;
  background: #f5f7fa;
  border-radius: 4px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.area-label {
  color: #909399;
  font-size: 14px;
}

.area-value {
  font-size: 16px;
  font-weight: 500;
  color: #303133;
}

.area-value.rent-income {
  color: #E6A23C;
}

.stats-error {
  text-align: center;
  padding: 20px;
  color: #F56C6C;
}

.stats-error i {
  font-size: 24px;
  margin-right: 8px;
}
</style>
