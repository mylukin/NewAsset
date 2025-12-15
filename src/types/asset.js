/**
 * Asset Type Definitions
 * Entity and DTO definitions for Asset Management System
 */

/**
 * Asset Status Enum - 9-state machine
 */
export const AssetStatus = {
  AVAILABLE_IDLE: 'AVAILABLE_IDLE',           // Available - Idle
  AVAILABLE_IN_USE: 'AVAILABLE_IN_USE',       // Available - In Use
  AVAILABLE_RENTED: 'AVAILABLE_RENTED',       // Available - Rented
  MAINTENANCE_ROUTINE: 'MAINTENANCE_ROUTINE', // Maintenance - Routine
  MAINTENANCE_REPAIR: 'MAINTENANCE_REPAIR',   // Maintenance - Repair
  UNAVAILABLE_DAMAGED: 'UNAVAILABLE_DAMAGED', // Unavailable - Damaged
  UNAVAILABLE_PENDING_DISPOSAL: 'UNAVAILABLE_PENDING_DISPOSAL', // Unavailable - Pending Disposal
  DISPOSED_SCRAPPED: 'DISPOSED_SCRAPPED',     // Disposed - Scrapped
  DISPOSED_SOLD: 'DISPOSED_SOLD'              // Disposed - Sold
}

/**
 * Asset Status Labels (Chinese)
 */
export const AssetStatusLabel = {
  [AssetStatus.AVAILABLE_IDLE]: '闲置',
  [AssetStatus.AVAILABLE_IN_USE]: '使用中',
  [AssetStatus.AVAILABLE_RENTED]: '已出租',
  [AssetStatus.MAINTENANCE_ROUTINE]: '例行维护',
  [AssetStatus.MAINTENANCE_REPAIR]: '维修中',
  [AssetStatus.UNAVAILABLE_DAMAGED]: '损坏',
  [AssetStatus.UNAVAILABLE_PENDING_DISPOSAL]: '待处置',
  [AssetStatus.DISPOSED_SCRAPPED]: '已报废',
  [AssetStatus.DISPOSED_SOLD]: '已出售'
}

/**
 * Asset Type Enum
 */
export const AssetType = {
  HOUSE: 'HOUSE',       // House/Room
  PARKING: 'PARKING',   // Parking Space
  FACILITY: 'FACILITY', // Facility/Equipment
  VENUE: 'VENUE',       // Venue/Meeting Room
  OFFICE: 'OFFICE'      // Office Equipment
}

/**
 * Asset Type Labels (Chinese)
 */
export const AssetTypeLabel = {
  [AssetType.HOUSE]: '房屋资产',
  [AssetType.PARKING]: '车位资产',
  [AssetType.FACILITY]: '设施设备',
  [AssetType.VENUE]: '场地场馆',
  [AssetType.OFFICE]: '办公资产'
}

/**
 * Asset Type Code Prefixes
 */
export const AssetTypePrefix = {
  [AssetType.HOUSE]: 'HA',
  [AssetType.PARKING]: 'PA',
  [AssetType.FACILITY]: 'FA',
  [AssetType.VENUE]: 'VE',
  [AssetType.OFFICE]: 'OF'
}

/**
 * Base Asset Entity
 * @typedef {Object} Asset
 * @property {number} id - Primary key
 * @property {string} assetCode - Asset code (unique, auto-generated)
 * @property {string} name - Asset name
 * @property {string} assetType - Asset type (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
 * @property {string} status - Asset status (9-state machine)
 * @property {number} projectId - Project ID
 * @property {number} buildingId - Building ID (level 1 location)
 * @property {number} floorId - Floor ID (level 2 location)
 * @property {number} unitId - Unit ID (level 3 location)
 * @property {string} locationDesc - Location description text
 * @property {string} responsiblePerson - Responsible person name
 * @property {string} responsiblePhone - Responsible person phone
 * @property {string} purchaseDate - Purchase/acquisition date
 * @property {number} purchasePrice - Purchase price
 * @property {string} warrantyExpireDate - Warranty expiration date
 * @property {string} description - Asset description
 * @property {string} statusChangedAt - Last status change time
 * @property {string} statusChangedBy - Last status change operator
 * @property {string} createBy - Creator
 * @property {string} createTime - Creation time
 * @property {string} updateBy - Updater
 * @property {string} updateTime - Update time
 * @property {string} remark - Remark
 */

/**
 * Asset VO (View Object) with joined location names
 * @typedef {Asset} AssetVO
 * @property {string} buildingName - Building name
 * @property {string} floorName - Floor name
 * @property {string} unitName - Unit name
 * @property {string} statusLabel - Status display label
 * @property {string} assetTypeLabel - Asset type display label
 */

/**
 * Asset DTO (Data Transfer Object) for create/update
 * @typedef {Object} AssetDTO
 * @property {string} name - Asset name (required)
 * @property {string} assetType - Asset type (required)
 * @property {number} projectId - Project ID (required)
 * @property {number} buildingId - Building ID
 * @property {number} floorId - Floor ID
 * @property {number} unitId - Unit ID
 * @property {string} locationDesc - Location description
 * @property {string} responsiblePerson - Responsible person
 * @property {string} responsiblePhone - Responsible phone
 * @property {string} purchaseDate - Purchase date
 * @property {number} purchasePrice - Purchase price
 * @property {string} warrantyExpireDate - Warranty expiration date
 * @property {string} description - Description
 * @property {string} remark - Remark
 */

/**
 * Asset Location Entity
 * @typedef {Object} AssetLocation
 * @property {number} id - Primary key
 * @property {number} parentId - Parent location ID (0 for root)
 * @property {string} name - Location name
 * @property {string} code - Location code
 * @property {number} level - Hierarchy level (1=Building, 2=Floor, 3=Unit)
 * @property {number} sortOrder - Display sort order
 * @property {number} projectId - Project ID
 * @property {string} status - Status (0=normal, 1=disabled)
 */

/**
 * Asset Attachment Entity
 * @typedef {Object} AssetAttachment
 * @property {number} id - Primary key
 * @property {number} assetId - Asset ID
 * @property {string} fileName - Original file name
 * @property {string} filePath - Storage file path
 * @property {number} fileSize - File size in bytes
 * @property {string} fileType - File MIME type
 * @property {number} sortOrder - Display sort order
 */

/**
 * Create empty Asset object
 * @returns {Asset}
 */
export function createEmptyAsset() {
  return {
    id: null,
    assetCode: '',
    name: '',
    assetType: '',
    status: AssetStatus.AVAILABLE_IDLE,
    projectId: null,
    buildingId: null,
    floorId: null,
    unitId: null,
    locationDesc: '',
    responsiblePerson: '',
    responsiblePhone: '',
    purchaseDate: null,
    purchasePrice: null,
    warrantyExpireDate: null,
    description: '',
    statusChangedAt: null,
    statusChangedBy: null,
    createBy: '',
    createTime: null,
    updateBy: '',
    updateTime: null,
    remark: ''
  }
}

/**
 * Get available status transitions for current status
 * @param {string} currentStatus Current status
 * @returns {string[]} Available target statuses
 */
export function getAvailableStatusTransitions(currentStatus) {
  const transitions = {
    [AssetStatus.AVAILABLE_IDLE]: [
      AssetStatus.AVAILABLE_IN_USE,
      AssetStatus.AVAILABLE_RENTED,
      AssetStatus.MAINTENANCE_ROUTINE,
      AssetStatus.MAINTENANCE_REPAIR,
      AssetStatus.UNAVAILABLE_DAMAGED
    ],
    [AssetStatus.AVAILABLE_IN_USE]: [
      AssetStatus.AVAILABLE_IDLE,
      AssetStatus.MAINTENANCE_ROUTINE,
      AssetStatus.MAINTENANCE_REPAIR,
      AssetStatus.UNAVAILABLE_DAMAGED
    ],
    [AssetStatus.AVAILABLE_RENTED]: [
      AssetStatus.AVAILABLE_IDLE,
      AssetStatus.MAINTENANCE_ROUTINE,
      AssetStatus.MAINTENANCE_REPAIR
    ],
    [AssetStatus.MAINTENANCE_ROUTINE]: [
      AssetStatus.AVAILABLE_IDLE,
      AssetStatus.AVAILABLE_IN_USE,
      AssetStatus.AVAILABLE_RENTED
    ],
    [AssetStatus.MAINTENANCE_REPAIR]: [
      AssetStatus.AVAILABLE_IDLE,
      AssetStatus.UNAVAILABLE_DAMAGED,
      AssetStatus.UNAVAILABLE_PENDING_DISPOSAL
    ],
    [AssetStatus.UNAVAILABLE_DAMAGED]: [
      AssetStatus.MAINTENANCE_REPAIR,
      AssetStatus.UNAVAILABLE_PENDING_DISPOSAL
    ],
    [AssetStatus.UNAVAILABLE_PENDING_DISPOSAL]: [
      AssetStatus.DISPOSED_SCRAPPED,
      AssetStatus.DISPOSED_SOLD
    ],
    [AssetStatus.DISPOSED_SCRAPPED]: [],
    [AssetStatus.DISPOSED_SOLD]: []
  }
  return transitions[currentStatus] || []
}

export default {
  AssetStatus,
  AssetStatusLabel,
  AssetType,
  AssetTypeLabel,
  AssetTypePrefix,
  createEmptyAsset,
  getAvailableStatusTransitions
}
