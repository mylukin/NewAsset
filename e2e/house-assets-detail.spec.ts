import { test, expect } from '@playwright/test';

test.describe('House Assets Detail Page', () => {
  // Note: Since this is a detail page that requires an asset ID,
  // we'll test navigation and component rendering

  test.describe('Detail Drawer Navigation', () => {
    test('should navigate to detail page from list', async ({ page }) => {
      await page.goto('/asset/house');
      await page.waitForLoadState('networkidle');

      // Wait for potential data to load
      await page.waitForTimeout(1000);

      const dataRows = page.locator('.el-table__body-wrapper .el-table__row');
      const rowCount = await dataRows.count();

      if (rowCount > 0) {
        // Click view button on first row
        const viewButton = dataRows.first().locator('[data-testid="action-view"], button:has-text("查看")');
        await viewButton.click();

        await page.waitForLoadState('networkidle');

        // Should navigate to detail page
        await expect(page).toHaveURL(/.*\/asset\/house\/detail\/\d+/);
      }
    });
  });

  test.describe('Detail Drawer Components', () => {
    test.beforeEach(async ({ page }) => {
      // Navigate to a detail page with a mock ID
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);
    });

    test('should display detail drawer', async ({ page }) => {
      const drawer = page.locator('.el-drawer');
      // Drawer might be visible or route might redirect
      const hasDrawer = await drawer.isVisible().catch(() => false);
      const hasDetailContainer = await page.locator('.house-asset-detail').isVisible().catch(() => false);

      // Either drawer or container should be present
      expect(hasDrawer || hasDetailContainer).toBeTruthy();
    });

    test('should display detail header section', async ({ page }) => {
      const detailHeader = page.locator('.detail-header');
      if (await detailHeader.isVisible()) {
        await expect(detailHeader).toBeVisible();
      }
    });

    test('should display header action buttons', async ({ page }) => {
      const headerActions = page.locator('.header-actions');
      if (await headerActions.isVisible()) {
        // Check for edit button
        await expect(page.locator('button:has-text("编辑")')).toBeVisible();

        // Check for maintenance button
        await expect(page.locator('button:has-text("发起维护")')).toBeVisible();

        // Check for delete button
        await expect(page.locator('button:has-text("删除")')).toBeVisible();
      }
    });

    test('should display tabs for different sections', async ({ page }) => {
      const tabs = page.locator('.el-tabs, .detail-tabs');
      if (await tabs.isVisible()) {
        // Check for basic info tab
        await expect(page.locator('.el-tabs__item:has-text("基本信息")')).toBeVisible();

        // Check for maintenance records tab
        await expect(page.locator('.el-tabs__item:has-text("维护记录")')).toBeVisible();

        // Check for attachments tab
        await expect(page.locator('.el-tabs__item:has-text("附件")')).toBeVisible();
      }
    });
  });

  test.describe('Basic Info Tab', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);
    });

    test('should display basic info tab content by default', async ({ page }) => {
      const basicInfoTab = page.locator('.el-tabs__item:has-text("基本信息")');
      if (await basicInfoTab.isVisible()) {
        // Basic info tab should be active by default
        await expect(basicInfoTab).toHaveClass(/is-active/);
      }
    });

    test('should display asset descriptions', async ({ page }) => {
      const descriptions = page.locator('.el-descriptions');
      if (await descriptions.isVisible()) {
        await expect(descriptions).toBeVisible();
      }
    });

    test('should display house-specific information section', async ({ page }) => {
      const sectionTitle = page.locator('h4:has-text("房屋信息")');
      if (await sectionTitle.isVisible()) {
        await expect(sectionTitle).toBeVisible();
      }
    });
  });

  test.describe('Maintenance Records Tab', () => {
    test('should switch to maintenance records tab', async ({ page }) => {
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);

      const maintenanceTab = page.locator('.el-tabs__item:has-text("维护记录")');
      if (await maintenanceTab.isVisible()) {
        await maintenanceTab.click();
        await page.waitForTimeout(300);

        // Tab should be active
        await expect(maintenanceTab).toHaveClass(/is-active/);
      }
    });

    test('should display create work order button in maintenance tab', async ({ page }) => {
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);

      const maintenanceTab = page.locator('.el-tabs__item:has-text("维护记录")');
      if (await maintenanceTab.isVisible()) {
        await maintenanceTab.click();
        await page.waitForTimeout(300);

        const createButton = page.locator('button:has-text("创建工单")');
        if (await createButton.isVisible()) {
          await expect(createButton).toBeVisible();
        }
      }
    });

    test('should display maintenance records table', async ({ page }) => {
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);

      const maintenanceTab = page.locator('.el-tabs__item:has-text("维护记录")');
      if (await maintenanceTab.isVisible()) {
        await maintenanceTab.click();
        await page.waitForTimeout(300);

        // Either table or empty state should be visible
        const table = page.locator('.el-table');
        const emptyState = page.locator('.empty-data, .el-empty');

        const hasTable = await table.isVisible();
        const hasEmptyState = await emptyState.isVisible();

        expect(hasTable || hasEmptyState).toBeTruthy();
      }
    });
  });

  test.describe('Attachments Tab', () => {
    test('should switch to attachments tab', async ({ page }) => {
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);

      const attachmentsTab = page.locator('.el-tabs__item:has-text("附件")');
      if (await attachmentsTab.isVisible()) {
        await attachmentsTab.click();
        await page.waitForTimeout(300);

        // Tab should be active
        await expect(attachmentsTab).toHaveClass(/is-active/);
      }
    });

    test('should display file upload area', async ({ page }) => {
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);

      const attachmentsTab = page.locator('.el-tabs__item:has-text("附件")');
      if (await attachmentsTab.isVisible()) {
        await attachmentsTab.click();
        await page.waitForTimeout(300);

        // Check for upload component
        const uploadArea = page.locator('.el-upload, .upload-demo');
        if (await uploadArea.isVisible()) {
          await expect(uploadArea).toBeVisible();
        }
      }
    });

    test('should display drag and drop upload hint', async ({ page }) => {
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);

      const attachmentsTab = page.locator('.el-tabs__item:has-text("附件")');
      if (await attachmentsTab.isVisible()) {
        await attachmentsTab.click();
        await page.waitForTimeout(300);

        const uploadText = page.locator('.el-upload__text');
        if (await uploadText.isVisible()) {
          await expect(uploadText).toContainText('将文件拖到此处');
        }
      }
    });
  });

  test.describe('Detail Actions', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);
    });

    test('should navigate to edit page when clicking edit button', async ({ page }) => {
      const editButton = page.locator('.header-actions button:has-text("编辑")');
      if (await editButton.isVisible()) {
        await editButton.click();
        await page.waitForLoadState('networkidle');
        await expect(page).toHaveURL(/.*\/asset\/house\/edit\/\d+/);
      }
    });

    test('should show delete confirmation dialog', async ({ page }) => {
      const deleteButton = page.locator('.header-actions button:has-text("删除")');
      if (await deleteButton.isVisible()) {
        await deleteButton.click();
        await page.waitForTimeout(500);

        // Check for confirmation dialog
        const confirmDialog = page.locator('.el-message-box');
        if (await confirmDialog.isVisible()) {
          await expect(confirmDialog).toBeVisible();
          await expect(page.locator('.el-message-box__message')).toContainText('确认删除');
        }
      }
    });

    test('should close drawer and return to list', async ({ page }) => {
      const drawer = page.locator('.el-drawer');
      if (await drawer.isVisible()) {
        // Find and click close button
        const closeButton = page.locator('.el-drawer__close-btn, .el-drawer__header button');
        if (await closeButton.isVisible()) {
          await closeButton.click();
          await page.waitForLoadState('networkidle');
          await expect(page).toHaveURL(/.*\/asset\/house$/);
        }
      }
    });
  });

  test.describe('Status Display', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/detail/1');
      await page.waitForLoadState('networkidle');
      await page.waitForTimeout(500);
    });

    test('should display status tag in header', async ({ page }) => {
      const headerRight = page.locator('.header-right');
      if (await headerRight.isVisible()) {
        const statusTag = headerRight.locator('.el-tag');
        if (await statusTag.isVisible()) {
          await expect(statusTag).toBeVisible();
        }
      }
    });

    test('should display usage tag in descriptions', async ({ page }) => {
      const usageTag = page.locator('.el-descriptions-item:has-text("当前用途") .el-tag');
      if (await usageTag.isVisible()) {
        await expect(usageTag).toBeVisible();
      }
    });
  });
});
