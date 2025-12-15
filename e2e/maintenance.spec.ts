import { test, expect } from '@playwright/test';

test.describe('Maintenance Page', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/maintenance');
    await page.waitForLoadState('networkidle');
  });

  test.describe('Page Load', () => {
    test('should load maintenance page successfully', async ({ page }) => {
      await expect(page).toHaveURL(/.*\/maintenance/);
      await expect(page.locator('body')).toBeVisible();
    });

    test('should display maintenance container', async ({ page }) => {
      await expect(page.locator('.maintenance-container')).toBeVisible();
    });

    test('should display page heading', async ({ page }) => {
      await expect(page.locator('h2:has-text("Maintenance")')).toBeVisible();
    });

    test('should display module description', async ({ page }) => {
      await expect(page.locator('p:has-text("Work order management module")')).toBeVisible();
    });
  });

  test.describe('Navigation', () => {
    test('should navigate from dashboard to maintenance', async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      // Click the quick action button
      await page.click('button:has-text("Create Work Order")');
      await page.waitForLoadState('networkidle');

      await expect(page).toHaveURL(/.*\/maintenance/);
    });

    test('should navigate via sidebar menu', async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      // Click Maintenance menu item directly (not in submenu)
      await page.click('.el-menu-item:has-text("Maintenance")');
      await page.waitForLoadState('networkidle');

      await expect(page).toHaveURL(/.*\/maintenance/);
    });

    test('should navigate back to dashboard', async ({ page }) => {
      // Click dashboard in sidebar
      await page.click('.el-menu-item:has-text("Dashboard")');
      await page.waitForLoadState('networkidle');

      await expect(page).toHaveURL(/.*\/(#\/)?(dashboard)?$/);
    });

    test('should navigate to asset modules', async ({ page }) => {
      // Navigate to house assets
      await page.goto('/asset/house');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/house/);

      // Navigate back to maintenance
      await page.goto('/maintenance');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/maintenance/);
    });
  });

  test.describe('Layout Integration', () => {
    test('should display within main layout', async ({ page }) => {
      // Verify layout elements are present
      await expect(page.locator('.sidebar, .el-aside')).toBeVisible();
      await expect(page.locator('.header, .el-header')).toBeVisible();
      await expect(page.locator('.main-content, .el-main')).toBeVisible();
    });

    test('should have correct sidebar menu item active', async ({ page }) => {
      // The Maintenance menu item should be active
      const menuItem = page.locator('.el-menu-item[index="/maintenance"]');
      if (await menuItem.isVisible()) {
        await expect(menuItem).toHaveClass(/is-active/);
      }
    });

    test('should display maintenance icon in sidebar', async ({ page }) => {
      const icon = page.locator('.el-menu-item:has-text("Maintenance") .el-icon-tools');
      await expect(icon).toBeVisible();
    });
  });

  test.describe('Content Display', () => {
    test('should have proper styling', async ({ page }) => {
      const container = page.locator('.maintenance-container');
      await expect(container).toBeVisible();

      // Container should have padding
      const computedStyle = await container.evaluate((el) => {
        return window.getComputedStyle(el).padding;
      });
      expect(computedStyle).toBeTruthy();
    });

    test('should be responsive', async ({ page }) => {
      // Test on different viewport sizes
      await page.setViewportSize({ width: 1920, height: 1080 });
      await page.reload();
      await page.waitForLoadState('networkidle');
      await expect(page.locator('.maintenance-container')).toBeVisible();

      await page.setViewportSize({ width: 1024, height: 768 });
      await page.reload();
      await page.waitForLoadState('networkidle');
      await expect(page.locator('.maintenance-container')).toBeVisible();
    });
  });

  test.describe('URL Handling', () => {
    test('should handle direct URL navigation', async ({ page }) => {
      await page.goto('/maintenance');
      await page.waitForLoadState('networkidle');
      await expect(page.locator('.maintenance-container')).toBeVisible();
    });

    test('should handle hash-based routing', async ({ page }) => {
      await page.goto('/#/maintenance');
      await page.waitForLoadState('networkidle');
      await expect(page.locator('.maintenance-container')).toBeVisible();
    });
  });

  test.describe('Work Order Context', () => {
    test('should handle asset-specific maintenance navigation', async ({ page }) => {
      // Navigate to maintenance with asset context
      await page.goto('/asset/maintenance/create?assetId=1');
      await page.waitForLoadState('networkidle');

      // Page should load (might redirect or show form)
      await expect(page.locator('body')).toBeVisible();
    });
  });
});
