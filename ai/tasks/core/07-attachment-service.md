---
id: core.attachment-service
module: core
priority: 7
status: passing
version: 15
origin: manual
dependsOn:
  - core.database-schema
supersedes: []
tags:
  - service
  - file-upload
  - p0
testRequirements:
  unit:
    required: false
    pattern: tests/core/**/*.test.*
verification:
  verifiedAt: '2025-12-14T12:04:11.379Z'
  verdict: pass
  verifiedBy: strategy-framework
  commitHash: dc9e7e71d42d1c2b666f9a571a4938d4f670eb6a
  summary: 7/7 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-14T12:03:16.034Z'
  generatedBy: claude
  forVersion: 10
  suggestedTestFiles:
    unit:
      - src/test/java/com/ruoyi/asset/service/AssetAttachmentServiceTest.java
      - src/test/java/com/ruoyi/asset/mapper/AssetAttachmentMapperTest.java
    e2e: []
  unitTestCases:
    - name: shouldCreateAssetAttachmentEntityWithCorrectTableMapping
      assertions:
        - assertNotNull(attachment)
        - >-
          assertEquals("t_asset_attachment",
          getTableName(AssetAttachment.class))
    - name: shouldPerformCrudOperationsViaMapper
      assertions:
        - 'assertEquals(1, mapper.insert(attachment))'
        - assertNotNull(mapper.selectById(id))
        - 'assertEquals(1, mapper.updateById(attachment))'
        - 'assertEquals(1, mapper.deleteById(id))'
    - name: shouldProvideAttachmentServiceMethods
      assertions:
        - 'assertNotNull(service.uploadAttachment(assetId, file))'
        - assertNotNull(service.getAttachmentsByAssetId(assetId))
        - assertTrue(service.deleteAttachment(attachmentId))
    - name: shouldIntegrateWithRuoYiFileUploadInfrastructure
      assertions:
        - 'assertNotNull(service.uploadAttachment(assetId, multipartFile))'
        - assertTrue(filePath.startsWith(ruoyiUploadPath))
    - name: shouldSupportMultipleFileTypes
      assertions:
        - assertTrue(service.isAllowedFileType("image/png"))
        - assertTrue(service.isAllowedFileType("application/pdf"))
        - assertTrue(service.isAllowedFileType("application/msword"))
    - name: shouldStoreFileMetadataCorrectly
      assertions:
        - 'assertEquals(fileName, attachment.getFileName())'
        - 'assertEquals(filePath, attachment.getFilePath())'
        - 'assertEquals(fileType, attachment.getFileType())'
        - 'assertEquals(fileSize, attachment.getFileSize())'
        - assertNotNull(attachment.getUploadBy())
        - assertNotNull(attachment.getUploadTime())
    - name: shouldValidateFileSizeAndType
      assertions:
        - >-
          assertThrows(FileSizeExceededException.class, () ->
          service.uploadAttachment(assetId, oversizedFile))
        - >-
          assertThrows(InvalidFileTypeException.class, () ->
          service.uploadAttachment(assetId, invalidTypeFile))
  e2eScenarios: []
  frameworkHint: junit5-surefire
---
# Create Asset Attachment Service

## Context

Assets and maintenance orders support file attachments (photos, contracts, documents). A unified attachment service handles upload, storage, and retrieval.

## Acceptance Criteria

1. Create `AssetAttachment` entity mapping `t_asset_attachment`
2. Create `AssetAttachmentMapper` for CRUD operations
3. Create `AssetAttachmentService` with methods:
4. Integrate with RuoYi file upload infrastructure
5. Support multiple file types (images, PDF, documents)
6. Store file metadata: fileName, filePath, fileType, fileSize, uploadBy, uploadTime
7. Add file size and type validation
## Technical Notes

- Reference: PRD Section 4.5, RuoYi file upload components
- Pattern: Service + Mapper pattern
- Storage: Use RuoYi configured file storage path
- Location: `com.ruoyi.asset.service.AssetAttachmentService`
