import { test, expect } from '@playwright/test';

test.describe('Dashboard Page', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/');
    await page.waitForLoadState('networkidle');
  });

  test.describe('Page Load', () => {
    test('should load dashboard page successfully', async ({ page }) => {
      await expect(page).toHaveTitle(/Asset Management System/);
      await expect(page.locator('#app')).toBeVisible();
    });

    test('should display dashboard content', async ({ page }) => {
      await expect(page.locator('.dashboard')).toBeVisible();
    });

    test('should have correct URL', async ({ page }) => {
      await expect(page).toHaveURL(/.*\/(#\/)?(dashboard)?$/);
    });
  });

  test.describe('Statistics Cards', () => {
    test('should display all four stat cards', async ({ page }) => {
      // Check for Total Assets card
      await expect(page.locator('h3:has-text("Total Assets")')).toBeVisible();

      // Check for In Use card
      await expect(page.locator('h3:has-text("In Use")')).toBeVisible();

      // Check for Maintenance card
      await expect(page.locator('h3:has-text("Maintenance")')).toBeVisible();

      // Check for Idle card
      await expect(page.locator('h3:has-text("Idle")')).toBeVisible();
    });

    test('should display stat card numbers', async ({ page }) => {
      // Each stat card should have a number display
      const cardNumbers = page.locator('.card-number');
      const count = await cardNumbers.count();
      expect(count).toBe(4);

      // Each number should be visible
      for (let i = 0; i < count; i++) {
        await expect(cardNumbers.nth(i)).toBeVisible();
      }
    });

    test('should display stat card icons', async ({ page }) => {
      // Check for icons in stat cards
      const cardIcons = page.locator('.card-icon');
      const count = await cardIcons.count();
      expect(count).toBe(4);

      // Verify specific icons exist
      await expect(page.locator('.card-icon .el-icon-office-building')).toBeVisible();
      await expect(page.locator('.card-icon .el-icon-check')).toBeVisible();
      await expect(page.locator('.card-icon .el-icon-tools')).toBeVisible();
      await expect(page.locator('.card-icon .el-icon-warning')).toBeVisible();
    });

    test('should display stat values as zero initially', async ({ page }) => {
      // Stats should initialize to 0
      const cardNumbers = page.locator('.card-number');
      for (let i = 0; i < 4; i++) {
        await expect(cardNumbers.nth(i)).toContainText('0');
      }
    });
  });

  test.describe('Asset Distribution Section', () => {
    test('should display Asset Distribution card', async ({ page }) => {
      await expect(page.locator('text=Asset Distribution')).toBeVisible();
    });

    test('should show chart placeholder', async ({ page }) => {
      await expect(page.locator('.chart-placeholder')).toBeVisible();
      await expect(page.locator('.chart-placeholder')).toContainText('Chart will be displayed here');
    });

    test('should have chart container with proper height', async ({ page }) => {
      const chartContainer = page.locator('.chart-container');
      await expect(chartContainer).toBeVisible();

      // Verify container has explicit height style
      const style = await chartContainer.getAttribute('style');
      expect(style).toContain('height: 300px');
    });
  });

  test.describe('Recent Activities Section', () => {
    test('should display Recent Activities card', async ({ page }) => {
      await expect(page.locator('text=Recent Activities')).toBeVisible();
    });

    test('should show empty activities placeholder', async ({ page }) => {
      await expect(page.locator('.activity-placeholder')).toBeVisible();
      await expect(page.locator('.activity-placeholder')).toContainText('No recent activities');
    });
  });

  test.describe('Quick Actions Section', () => {
    test('should display Quick Actions card', async ({ page }) => {
      await expect(page.locator('text=Quick Actions')).toBeVisible();
    });

    test('should display all quick action buttons', async ({ page }) => {
      // Check for all four quick action buttons
      await expect(page.locator('button:has-text("Add House Asset")')).toBeVisible();
      await expect(page.locator('button:has-text("Add Parking Asset")')).toBeVisible();
      await expect(page.locator('button:has-text("Add Facility Asset")')).toBeVisible();
      await expect(page.locator('button:has-text("Create Work Order")')).toBeVisible();
    });

    test('should have correct button types', async ({ page }) => {
      // Primary button for house asset
      const houseButton = page.locator('button:has-text("Add House Asset")');
      await expect(houseButton).toHaveClass(/el-button--primary/);

      // Success button for parking asset
      const parkingButton = page.locator('button:has-text("Add Parking Asset")');
      await expect(parkingButton).toHaveClass(/el-button--success/);

      // Warning button for facility asset
      const facilityButton = page.locator('button:has-text("Add Facility Asset")');
      await expect(facilityButton).toHaveClass(/el-button--warning/);

      // Info button for work order
      const workOrderButton = page.locator('button:has-text("Create Work Order")');
      await expect(workOrderButton).toHaveClass(/el-button--info/);
    });

    test('should display plus icons on buttons', async ({ page }) => {
      // Each quick action button should have a plus icon
      const quickActionsSection = page.locator('.quick-actions');
      const plusIcons = quickActionsSection.locator('.el-icon-plus');
      const count = await plusIcons.count();
      expect(count).toBe(4);
    });
  });

  test.describe('Quick Action Navigation', () => {
    test('should navigate to house assets page when clicking Add House Asset', async ({ page }) => {
      await page.click('button:has-text("Add House Asset")');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/house/);
    });

    test('should navigate to parking assets page when clicking Add Parking Asset', async ({ page }) => {
      await page.click('button:has-text("Add Parking Asset")');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/parking/);
    });

    test('should navigate to facility assets page when clicking Add Facility Asset', async ({ page }) => {
      await page.click('button:has-text("Add Facility Asset")');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/facility/);
    });

    test('should navigate to maintenance page when clicking Create Work Order', async ({ page }) => {
      await page.click('button:has-text("Create Work Order")');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/maintenance/);
    });
  });

  test.describe('Card Layout', () => {
    test('should have proper grid layout for stat cards', async ({ page }) => {
      // First row should have 4 columns
      const firstRow = page.locator('.el-row').first();
      const cols = firstRow.locator('.el-col');
      const count = await cols.count();
      expect(count).toBe(4);
    });

    test('should have dashboard cards with proper styling', async ({ page }) => {
      const dashboardCards = page.locator('.dashboard-card');
      const count = await dashboardCards.count();
      expect(count).toBeGreaterThanOrEqual(6); // 4 stat + chart + activities + quick actions

      // Each card should be visible
      for (let i = 0; i < count; i++) {
        await expect(dashboardCards.nth(i)).toBeVisible();
      }
    });
  });

  test.describe('Responsive Layout', () => {
    test('should display properly on desktop viewport', async ({ page }) => {
      await page.setViewportSize({ width: 1920, height: 1080 });
      await page.reload();
      await page.waitForLoadState('networkidle');

      // All elements should be visible
      await expect(page.locator('.dashboard')).toBeVisible();
      await expect(page.locator('.quick-actions')).toBeVisible();
    });

    test('should display properly on tablet viewport', async ({ page }) => {
      await page.setViewportSize({ width: 1024, height: 768 });
      await page.reload();
      await page.waitForLoadState('networkidle');

      await expect(page.locator('.dashboard')).toBeVisible();
    });
  });
});
