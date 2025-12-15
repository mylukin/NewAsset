import request from '@/utils/request'

// Base Asset API - Common operations for all asset types

/**
 * Get asset by ID (base asset)
 * @param id Asset ID
 * @returns {*} Asset detail
 */
export function getAsset(id) {
  return request({
    url: `/asset/${id}`,
    method: 'get'
  })
}

/**
 * Get asset by code
 * @param code Asset code
 * @returns {*} Asset detail
 */
export function getAssetByCode(code) {
  return request({
    url: '/asset/code',
    method: 'get',
    params: { code }
  })
}

/**
 * Change asset status
 * @param id Asset ID
 * @param status New status
 * @param remark Status change remark
 * @returns {*} Result
 */
export function changeAssetStatus(id, status, remark) {
  return request({
    url: `/asset/status/${id}`,
    method: 'put',
    data: { status, remark }
  })
}

/**
 * Get asset status history
 * @param id Asset ID
 * @returns {*} Status history list
 */
export function getAssetStatusHistory(id) {
  return request({
    url: `/asset/status-history/${id}`,
    method: 'get'
  })
}

/**
 * Get asset attachments
 * @param id Asset ID
 * @returns {*} Attachment list
 */
export function getAssetAttachments(id) {
  return request({
    url: `/asset/attachments/${id}`,
    method: 'get'
  })
}

/**
 * Upload asset attachment
 * @param id Asset ID
 * @param formData Form data with file
 * @returns {*} Uploaded attachment
 */
export function uploadAssetAttachment(id, formData) {
  return request({
    url: `/asset/attachments/${id}`,
    method: 'post',
    headers: { 'Content-Type': 'multipart/form-data' },
    data: formData
  })
}

/**
 * Delete asset attachment
 * @param assetId Asset ID
 * @param attachmentId Attachment ID
 * @returns {*} Result
 */
export function deleteAssetAttachment(assetId, attachmentId) {
  return request({
    url: `/asset/attachments/${assetId}/${attachmentId}`,
    method: 'delete'
  })
}

export default {
  getAsset,
  getAssetByCode,
  changeAssetStatus,
  getAssetStatusHistory,
  getAssetAttachments,
  uploadAssetAttachment,
  deleteAssetAttachment
}
