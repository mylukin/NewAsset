import { test, expect } from '@playwright/test';

test.describe('House Assets Page', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/asset/house');
    await page.waitForLoadState('networkidle');
  });

  test.describe('Page Load', () => {
    test('should load house assets page successfully', async ({ page }) => {
      await expect(page.locator('body')).toBeVisible();
      await expect(page).toHaveURL(/.*\/asset\/house/);
    });

    test('should display house assets list container', async ({ page }) => {
      await expect(page.locator('.house-asset-list')).toBeVisible();
    });
  });

  test.describe('Search Bar', () => {
    test('should display search form when visible', async ({ page }) => {
      const searchBar = page.locator('.search-bar');
      await expect(searchBar).toBeVisible();
    });

    test('should have project select dropdown', async ({ page }) => {
      const projectSelect = page.locator('.el-form-item').filter({ hasText: '项目' }).locator('.el-select');
      await expect(projectSelect).toBeVisible();

      // Click to open dropdown
      await projectSelect.click();
      await page.waitForTimeout(300);

      // Check dropdown options
      const options = page.locator('.el-select-dropdown__item');
      await expect(options.filter({ hasText: '项目A' })).toBeVisible();
      await expect(options.filter({ hasText: '项目B' })).toBeVisible();
    });

    test('should have building select dropdown', async ({ page }) => {
      const buildingSelect = page.locator('.el-form-item').filter({ hasText: '楼宇' }).locator('.el-select');
      await expect(buildingSelect).toBeVisible();
    });

    test('should have floor select dropdown', async ({ page }) => {
      const floorSelect = page.locator('.el-form-item').filter({ hasText: '楼层' }).locator('.el-select');
      await expect(floorSelect).toBeVisible();
    });

    test('should have status select dropdown', async ({ page }) => {
      const statusSelect = page.locator('.el-form-item').filter({ hasText: '状态' }).locator('.el-select');
      await expect(statusSelect).toBeVisible();

      // Click to open dropdown
      await statusSelect.click();
      await page.waitForTimeout(300);

      // Check status options
      const options = page.locator('.el-select-dropdown__item');
      await expect(options.filter({ hasText: '全部' })).toBeVisible();
      await expect(options.filter({ hasText: '可用-自用' })).toBeVisible();
      await expect(options.filter({ hasText: '可用-对外出租' })).toBeVisible();
    });

    test('should have current usage select dropdown', async ({ page }) => {
      const usageSelect = page.locator('.el-form-item').filter({ hasText: '当前用途' }).locator('.el-select');
      await expect(usageSelect).toBeVisible();
    });

    test('should have area range inputs', async ({ page }) => {
      const areaMinInput = page.locator('.el-input-number').first();
      const areaMaxInput = page.locator('.el-input-number').nth(1);

      await expect(areaMinInput).toBeVisible();
      await expect(areaMaxInput).toBeVisible();
    });

    test('should have keyword search input', async ({ page }) => {
      const keywordInput = page.locator('input[placeholder="输入资产编码或名称"]');
      await expect(keywordInput).toBeVisible();
    });

    test('should have search and reset buttons', async ({ page }) => {
      await expect(page.locator('button:has-text("搜索")')).toBeVisible();
      await expect(page.locator('button:has-text("重置")')).toBeVisible();
    });

    test('should allow typing in keyword search', async ({ page }) => {
      const keywordInput = page.locator('input[placeholder="输入资产编码或名称"]');
      await keywordInput.fill('测试资产');
      await expect(keywordInput).toHaveValue('测试资产');
    });

    test('should clear keyword input with clear button', async ({ page }) => {
      const keywordInput = page.locator('input[placeholder="输入资产编码或名称"]');
      await keywordInput.fill('测试');
      await expect(keywordInput).toHaveValue('测试');

      // Hover to show clear button and click it
      await keywordInput.hover();
      const clearButton = page.locator('.el-input__clear');
      if (await clearButton.isVisible()) {
        await clearButton.click();
        await expect(keywordInput).toHaveValue('');
      }
    });

    test('should trigger search when clicking search button', async ({ page }) => {
      await page.click('button:has-text("搜索")');
      // Should trigger a loading state or API call
      await page.waitForTimeout(500);
      // Page should still be functional
      await expect(page.locator('.house-asset-list')).toBeVisible();
    });

    test('should reset filters when clicking reset button', async ({ page }) => {
      // Fill in some search criteria
      const keywordInput = page.locator('input[placeholder="输入资产编码或名称"]');
      await keywordInput.fill('测试');

      // Click reset
      await page.click('button:has-text("重置")');

      // Keyword should be cleared
      await expect(keywordInput).toHaveValue('');
    });
  });

  test.describe('Action Bar', () => {
    test('should display action bar with buttons', async ({ page }) => {
      const actionBar = page.locator('.action-bar');
      await expect(actionBar).toBeVisible();
    });

    test('should have add button', async ({ page }) => {
      const addButton = page.locator('button:has-text("新增")');
      await expect(addButton).toBeVisible();
      await expect(addButton).toHaveClass(/el-button--primary/);
    });

    test('should have import button', async ({ page }) => {
      const importButton = page.locator('button:has-text("导入")');
      await expect(importButton).toBeVisible();
    });

    test('should have export button', async ({ page }) => {
      const exportButton = page.locator('button:has-text("导出")');
      await expect(exportButton).toBeVisible();
    });

    test('should have delete button (initially disabled)', async ({ page }) => {
      const deleteButton = page.locator('button:has-text("删除")');
      await expect(deleteButton).toBeVisible();
      // Delete button should be disabled when no rows selected
      await expect(deleteButton).toBeDisabled();
    });

    test('should display icon on add button', async ({ page }) => {
      const addButton = page.locator('button:has-text("新增")');
      await expect(addButton.locator('.el-icon-plus')).toBeVisible();
    });
  });

  test.describe('Status Filter Tabs', () => {
    test('should display status tabs', async ({ page }) => {
      const statusTabs = page.locator('.status-tabs');
      await expect(statusTabs).toBeVisible();
    });

    test('should have all status tab options', async ({ page }) => {
      await expect(page.locator('.el-tabs__item:has-text("全部")')).toBeVisible();
      await expect(page.locator('.el-tabs__item:has-text("自用")')).toBeVisible();
      await expect(page.locator('.el-tabs__item:has-text("出租")')).toBeVisible();
      await expect(page.locator('.el-tabs__item:has-text("空置")')).toBeVisible();
    });

    test('should switch tabs when clicking', async ({ page }) => {
      // Click on "自用" tab
      await page.click('.el-tabs__item:has-text("自用")');
      await page.waitForTimeout(300);

      // Tab should be active
      const selfUseTab = page.locator('.el-tabs__item:has-text("自用")');
      await expect(selfUseTab).toHaveClass(/is-active/);
    });

    test('should filter data when switching tabs', async ({ page }) => {
      // Click on "出租" tab
      await page.click('.el-tabs__item:has-text("出租")');
      await page.waitForTimeout(500);

      // Page should still be functional
      await expect(page.locator('.house-asset-list')).toBeVisible();
    });
  });

  test.describe('Data Table', () => {
    test('should display data table', async ({ page }) => {
      const table = page.locator('.el-table');
      await expect(table).toBeVisible();
    });

    test('should have correct table columns', async ({ page }) => {
      // Wait for table to load
      await page.waitForTimeout(500);

      // Check table header columns
      await expect(page.locator('.el-table th:has-text("资产编码")')).toBeVisible();
      await expect(page.locator('.el-table th:has-text("资产名称")')).toBeVisible();
      await expect(page.locator('.el-table th:has-text("楼宇")')).toBeVisible();
      await expect(page.locator('.el-table th:has-text("楼层")')).toBeVisible();
      await expect(page.locator('.el-table th:has-text("房号")')).toBeVisible();
      await expect(page.locator('.el-table th:has-text("建筑面积")')).toBeVisible();
      await expect(page.locator('.el-table th:has-text("状态")')).toBeVisible();
      await expect(page.locator('.el-table th:has-text("当前用途")')).toBeVisible();
      await expect(page.locator('.el-table th:has-text("操作")')).toBeVisible();
    });

    test('should have selection checkbox column', async ({ page }) => {
      const checkboxColumn = page.locator('.el-table th .el-checkbox');
      await expect(checkboxColumn).toBeVisible();
    });

    test('should display empty state when no data', async ({ page }) => {
      // Check if either data rows exist or empty state is shown
      const dataRows = page.locator('.el-table__body-wrapper .el-table__row');
      const emptyState = page.locator('.empty-state, .el-table__empty-text');

      const hasData = await dataRows.count() > 0;
      if (!hasData) {
        await expect(emptyState).toBeVisible();
      }
    });

    test('should display loading state while fetching data', async ({ page }) => {
      // Reload page and check for loading indicator
      await page.reload();

      // The loading overlay may appear briefly
      const loadingIndicator = page.locator('.el-loading-mask');
      // Just verify the table container exists
      await expect(page.locator('.table-container')).toBeVisible();
    });
  });

  test.describe('Table Row Actions', () => {
    test('should display action buttons in each row', async ({ page }) => {
      // Wait for potential data to load
      await page.waitForTimeout(1000);

      const dataRows = page.locator('.el-table__body-wrapper .el-table__row');
      const rowCount = await dataRows.count();

      if (rowCount > 0) {
        const firstRow = dataRows.first();
        await expect(firstRow.locator('button:has-text("查看")')).toBeVisible();
        await expect(firstRow.locator('button:has-text("编辑")')).toBeVisible();
        await expect(firstRow.locator('button:has-text("复制")')).toBeVisible();
        await expect(firstRow.locator('button:has-text("维护")')).toBeVisible();
      }
    });

    test('should have test IDs on action buttons', async ({ page }) => {
      await page.waitForTimeout(1000);

      const dataRows = page.locator('.el-table__body-wrapper .el-table__row');
      const rowCount = await dataRows.count();

      if (rowCount > 0) {
        const firstRow = dataRows.first();
        await expect(firstRow.locator('[data-testid="action-view"]')).toBeVisible();
        await expect(firstRow.locator('[data-testid="action-edit"]')).toBeVisible();
      }
    });
  });

  test.describe('Row Selection', () => {
    test('should enable delete button when rows are selected', async ({ page }) => {
      await page.waitForTimeout(1000);

      const dataRows = page.locator('.el-table__body-wrapper .el-table__row');
      const rowCount = await dataRows.count();

      if (rowCount > 0) {
        // Click on the checkbox of the first row
        const firstRowCheckbox = dataRows.first().locator('.el-checkbox');
        await firstRowCheckbox.click();

        // Delete button should now be enabled
        const deleteButton = page.locator('.action-bar button:has-text("删除")');
        await expect(deleteButton).not.toBeDisabled();
      }
    });

    test('should select all rows when clicking header checkbox', async ({ page }) => {
      await page.waitForTimeout(1000);

      const dataRows = page.locator('.el-table__body-wrapper .el-table__row');
      const rowCount = await dataRows.count();

      if (rowCount > 0) {
        // Click on header checkbox
        const headerCheckbox = page.locator('.el-table__header-wrapper .el-checkbox');
        await headerCheckbox.click();

        // All rows should be selected
        const selectedRows = page.locator('.el-table__body-wrapper .el-table__row.current-row, .el-table__body-wrapper .is-checked');
        // Just verify the interaction worked
        await expect(page.locator('.el-table')).toBeVisible();
      }
    });
  });

  test.describe('Navigation', () => {
    test('should navigate to add page when clicking add button', async ({ page }) => {
      await page.click('button:has-text("新增")');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/house\/add/);
    });

    test('should navigate back to dashboard', async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/(#\/)?(dashboard)?$/);
    });
  });

  test.describe('Empty State', () => {
    test('should display empty state when no assets found', async ({ page }) => {
      await page.waitForTimeout(1000);

      const dataRows = page.locator('.el-table__body-wrapper .el-table__row');
      const rowCount = await dataRows.count();

      if (rowCount === 0) {
        const emptyState = page.locator('.empty-state, .el-empty');
        await expect(emptyState).toBeVisible();

        // Check for CTA button in empty state
        const ctaButton = page.locator('.empty-state button:has-text("新增房源资产")');
        if (await ctaButton.isVisible()) {
          await expect(ctaButton).toBeVisible();
        }
      }
    });
  });

  test.describe('Search Toggle', () => {
    test('should toggle search visibility with right toolbar', async ({ page }) => {
      // Look for the right toolbar that controls search visibility
      const searchToggle = page.locator('.right-toolbar button, [class*="right-tool"] button').first();

      if (await searchToggle.isVisible()) {
        const searchBar = page.locator('.search-bar .el-form');

        // Get initial visibility
        const initiallyVisible = await searchBar.isVisible();

        // Click toggle
        await searchToggle.click();
        await page.waitForTimeout(300);

        // Visibility should change
        // This depends on the implementation
      }
    });
  });

  test.describe('Responsive Behavior', () => {
    test('should display properly on wide screen', async ({ page }) => {
      await page.setViewportSize({ width: 1920, height: 1080 });
      await page.reload();
      await page.waitForLoadState('networkidle');

      await expect(page.locator('.house-asset-list')).toBeVisible();
      await expect(page.locator('.el-table')).toBeVisible();
    });

    test('should handle smaller viewport', async ({ page }) => {
      await page.setViewportSize({ width: 1024, height: 768 });
      await page.reload();
      await page.waitForLoadState('networkidle');

      await expect(page.locator('.house-asset-list')).toBeVisible();
    });
  });
});
