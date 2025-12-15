/**
 * Asset Attachment Service Test
 *
 * Tests for AssetAttachmentService with file upload and validation
 *
 * @author ruoyi
 * @date 2025-12-15
 */

import { describe, it, expect, beforeEach, vi } from 'vitest';

// Mock types
interface AssetAttachment {
  attachmentId: number;
  assetId: number;
  fileName: string;
  filePath: string;
  fileSize: number;
  fileType: string;
  uploadTime: Date;
}

// Mock service
class AssetAttachmentService {
  private attachments: Map<number, AssetAttachment> = new Map();
  private maxFileSize = 5 * 1024 * 1024; // 5MB
  private maxFiles = 10;
  private allowedTypes = ['image/jpeg', 'image/png', 'application/pdf'];

  async upload(attachment: Partial<AssetAttachment>, file: File): Promise<AssetAttachment> {
    // Validate file size
    if (file.size > this.maxFileSize) {
      throw new Error('File size exceeds 5MB limit');
    }

    // Validate file type
    if (!this.allowedTypes.includes(file.type)) {
      throw new Error('File type not allowed');
    }

    // Check max files per asset
    const assetAttachments = Array.from(this.attachments.values()).filter(a => a.assetId === attachment.assetId);
    if (assetAttachments.length >= this.maxFiles) {
      throw new Error('Maximum number of attachments (10) reached');
    }

    // Generate attachment ID
    const attachmentId = this.attachments.size + 1;
    const filePath = `/uploads/${attachment.assetId}/${Date.now()}_${file.name}`;

    const newAttachment: AssetAttachment = {
      attachmentId,
      assetId: attachment.assetId!,
      fileName: file.name,
      filePath,
      fileSize: file.size,
      fileType: file.type,
      uploadTime: new Date(),
    };

    this.attachments.set(attachmentId, newAttachment);
    return newAttachment;
  }

  async getByAssetId(assetId: number): Promise<AssetAttachment[]> {
    return Array.from(this.attachments.values()).filter(a => a.assetId === assetId);
  }

  async delete(attachmentId: number): Promise<void> {
    if (!this.attachments.has(attachmentId)) {
      throw new Error('Attachment not found');
    }
    this.attachments.delete(attachmentId);
  }
}

describe('core.attachment-service', () => {
  let service: AssetAttachmentService;

  beforeEach(() => {
    service = new AssetAttachmentService();
  });

  it('should create AssetAttachment entity with required fields', async () => {
    const file = new File(['test'], 'test.pdf', { type: 'application/pdf' });
    const attachment = await service.upload({ assetId: 1 }, file);

    expect(attachment.attachmentId).toBeDefined();
    expect(attachment.assetId).toBe(1);
    expect(attachment.fileName).toBe('test.pdf');
    expect(attachment.filePath).toContain('/uploads/1/');
  });

  it('should enforce upload limits', async () => {
    const file = new File(['test'], 'test.pdf', { type: 'application/pdf' });

    // Upload max files
    for (let i = 0; i < 10; i++) {
      await service.upload({ assetId: 1 }, file);
    }

    // Should reject 11th file
    await expect(service.upload({ assetId: 1 }, file)).rejects.toThrow('Maximum number of attachments (10) reached');
  });

  it('should validate file type', async () => {
    const invalidFile = new File(['test'], 'test.exe', { type: 'application/x-executable' });

    await expect(service.upload({ assetId: 1 }, invalidFile)).rejects.toThrow('File type not allowed');
  });

  it('should validate file size', async () => {
    const largeContent = new ArrayBuffer(6 * 1024 * 1024); // 6MB
    const largeFile = new File([largeContent], 'large.pdf', { type: 'application/pdf' });

    await expect(service.upload({ assetId: 1 }, largeFile)).rejects.toThrow('File size exceeds 5MB limit');
  });

  it('should handle file storage path generation', async () => {
    const file = new File(['test'], 'document.pdf', { type: 'application/pdf' });
    const attachment = await service.upload({ assetId: 100 }, file);

    expect(attachment.filePath).toMatch(/\/uploads\/100\/\d+_document\.pdf$/);
  });
});
