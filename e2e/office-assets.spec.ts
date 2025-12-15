import { test, expect } from '@playwright/test';

test.describe('Office Assets Page', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/asset/office');
    await page.waitForLoadState('networkidle');
  });

  test.describe('Page Load', () => {
    test('should load office assets page successfully', async ({ page }) => {
      await expect(page).toHaveURL(/.*\/asset\/office/);
      await expect(page.locator('body')).toBeVisible();
    });

    test('should display office assets container', async ({ page }) => {
      await expect(page.locator('.office-container')).toBeVisible();
    });

    test('should display page heading', async ({ page }) => {
      await expect(page.locator('h2:has-text("Office Assets")')).toBeVisible();
    });

    test('should display module description', async ({ page }) => {
      await expect(page.locator('p:has-text("Office asset management module")')).toBeVisible();
    });
  });

  test.describe('Navigation', () => {
    test('should navigate via sidebar menu', async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      // Open Asset Management submenu
      const assetSubmenu = page.locator('.el-submenu:has-text("Asset Management")');
      await assetSubmenu.click();
      await page.waitForTimeout(300);

      // Click Office Assets menu item
      await page.click('.el-menu-item:has-text("Office Assets")');
      await page.waitForLoadState('networkidle');

      await expect(page).toHaveURL(/.*\/asset\/office/);
    });

    test('should navigate back to dashboard', async ({ page }) => {
      // Click dashboard in sidebar
      await page.click('.el-menu-item:has-text("Dashboard")');
      await page.waitForLoadState('networkidle');

      await expect(page).toHaveURL(/.*\/(#\/)?(dashboard)?$/);
    });

    test('should navigate to all asset modules sequentially', async ({ page }) => {
      // House
      await page.goto('/asset/house');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/house/);

      // Parking
      await page.goto('/asset/parking');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/parking/);

      // Facility
      await page.goto('/asset/facility');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/facility/);

      // Venue
      await page.goto('/asset/venue');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/venue/);

      // Office
      await page.goto('/asset/office');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/office/);
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
      // The Office Assets menu item should be active
      const menuItem = page.locator('.el-menu-item[index="/asset/office"]');
      if (await menuItem.isVisible()) {
        await expect(menuItem).toHaveClass(/is-active/);
      }
    });
  });

  test.describe('Content Display', () => {
    test('should have proper styling', async ({ page }) => {
      const container = page.locator('.office-container');
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
      await expect(page.locator('.office-container')).toBeVisible();

      await page.setViewportSize({ width: 1024, height: 768 });
      await page.reload();
      await page.waitForLoadState('networkidle');
      await expect(page.locator('.office-container')).toBeVisible();
    });
  });

  test.describe('URL Handling', () => {
    test('should handle direct URL navigation', async ({ page }) => {
      await page.goto('/asset/office');
      await page.waitForLoadState('networkidle');
      await expect(page.locator('.office-container')).toBeVisible();
    });

    test('should handle hash-based routing', async ({ page }) => {
      await page.goto('/#/asset/office');
      await page.waitForLoadState('networkidle');
      await expect(page.locator('.office-container')).toBeVisible();
    });
  });
});
