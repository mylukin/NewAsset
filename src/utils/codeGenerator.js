/**
 * Asset Code Generator Service
 * Generates unique asset codes with type prefix and sequential numbering
 */

import { AssetTypePrefix } from '@/types/asset'

// In-memory sequence store (in real app, this would be backed by database)
const sequenceStore = new Map()

/**
 * Initialize sequence for asset type
 * @param {string} projectId - Project ID
 * @param {string} assetType - Asset type (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
 * @returns {boolean} Success status
 */
export function initializeSequence(projectId, assetType) {
  const key = `${projectId}_${assetType}`
  if (!sequenceStore.has(key)) {
    sequenceStore.set(key, {
      currentSeq: 0,
      version: 0
    })
  }
  return true
}

/**
 * Get next sequence number with optimistic locking
 * @param {string} projectId - Project ID
 * @param {string} assetType - Asset type
 * @returns {Object} Sequence info with number and version
 */
export function getNextSequence(projectId, assetType) {
  const key = `${projectId}_${assetType}`

  // Initialize if not exists
  if (!sequenceStore.has(key)) {
    initializeSequence(projectId, assetType)
  }

  const seqData = sequenceStore.get(key)
  const newSeq = seqData.currentSeq + 1
  const newVersion = seqData.version + 1

  return {
    sequence: newSeq,
    version: seqData.version,
    newVersion: newVersion
  }
}

/**
 * Update sequence with optimistic locking
 * @param {string} projectId - Project ID
 * @param {string} assetType - Asset type
 * @param {number} expectedVersion - Expected version for optimistic lock
 * @param {number} newSequence - New sequence number
 * @returns {boolean} Success status (false if version conflict)
 */
export function updateSequence(projectId, assetType, expectedVersion, newSequence) {
  const key = `${projectId}_${assetType}`
  const seqData = sequenceStore.get(key)

  if (!seqData) {
    return false
  }

  // Optimistic lock check
  if (seqData.version !== expectedVersion) {
    // Version conflict - another process updated the sequence
    return false
  }

  // Update sequence with new version
  sequenceStore.set(key, {
    currentSeq: newSequence,
    version: expectedVersion + 1
  })

  return true
}

/**
 * Generate asset code with format: TYPE_PREFIX-NNNNNN
 * @param {string} projectId - Project ID
 * @param {string} assetType - Asset type (HOUSE, PARKING, FACILITY, VENUE, OFFICE)
 * @param {number} maxRetries - Maximum retries for optimistic locking conflicts
 * @returns {string} Generated asset code (e.g., HA-000001)
 */
export function generateAssetCode(projectId, assetType, maxRetries = 5) {
  const prefix = AssetTypePrefix[assetType]
  if (!prefix) {
    throw new Error(`Unknown asset type: ${assetType}`)
  }

  let retries = 0
  while (retries < maxRetries) {
    const seqInfo = getNextSequence(projectId, assetType)
    const success = updateSequence(projectId, assetType, seqInfo.version, seqInfo.sequence)

    if (success) {
      // Format: PREFIX-NNNNNN (6 digits with leading zeros)
      const seqStr = String(seqInfo.sequence).padStart(6, '0')
      return `${prefix}-${seqStr}`
    }

    retries++
  }

  throw new Error(`Failed to generate asset code after ${maxRetries} retries due to concurrent conflicts`)
}

/**
 * Format sequence number to 6-digit string
 * @param {number} seq - Sequence number
 * @returns {string} Formatted string (e.g., '000001')
 */
export function formatSequence(seq) {
  return String(seq).padStart(6, '0')
}

/**
 * Validate asset code format
 * @param {string} code - Asset code to validate
 * @returns {boolean} True if valid format
 */
export function isValidAssetCode(code) {
  if (!code || typeof code !== 'string') {
    return false
  }

  // Pattern: 2 uppercase letters + hyphen + 6 digits
  const pattern = /^[A-Z]{2}-\d{6}$/
  return pattern.test(code)
}

/**
 * Parse asset code to extract type and sequence
 * @param {string} code - Asset code
 * @returns {Object|null} Parsed info or null if invalid
 */
export function parseAssetCode(code) {
  if (!isValidAssetCode(code)) {
    return null
  }

  const [prefix, seqStr] = code.split('-')
  const assetType = Object.entries(AssetTypePrefix).find(([, p]) => p === prefix)?.[0]

  return {
    prefix,
    assetType: assetType || null,
    sequence: parseInt(seqStr, 10)
  }
}

/**
 * Reset sequence for testing purposes
 * @param {string} projectId - Project ID
 * @param {string} assetType - Asset type
 */
export function resetSequence(projectId, assetType) {
  const key = `${projectId}_${assetType}`
  sequenceStore.delete(key)
}

/**
 * Get current sequence value
 * @param {string} projectId - Project ID
 * @param {string} assetType - Asset type
 * @returns {number} Current sequence value
 */
export function getCurrentSequence(projectId, assetType) {
  const key = `${projectId}_${assetType}`
  const seqData = sequenceStore.get(key)
  return seqData ? seqData.currentSeq : 0
}

export default {
  initializeSequence,
  getNextSequence,
  updateSequence,
  generateAssetCode,
  formatSequence,
  isValidAssetCode,
  parseAssetCode,
  resetSequence,
  getCurrentSequence
}
