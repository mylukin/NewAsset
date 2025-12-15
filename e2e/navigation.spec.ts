import { test, expect } from '@playwright/test';

test.describe('Layout & Navigation Tests', () => {
  test.describe('Layout Structure', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');
    });

    test('should display main layout container', async ({ page }) => {
      await expect(page.locator('.layout-container')).toBeVisible();
    });

    test('should display sidebar', async ({ page }) => {
      const sidebar = page.locator('.sidebar, .el-aside');
      await expect(sidebar).toBeVisible();
    });

    test('should display header', async ({ page }) => {
      const header = page.locator('.header, .el-header');
      await expect(header).toBeVisible();
    });

    test('should display main content area', async ({ page }) => {
      const mainContent = page.locator('.main-content, .el-main');
      await expect(mainContent).toBeVisible();
    });

    test('should display logo in sidebar', async ({ page }) => {
      const logo = page.locator('.logo');
      await expect(logo).toBeVisible();
    });
  });

  test.describe('Sidebar Menu', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');
    });

    test('should display Dashboard menu item', async ({ page }) => {
      await expect(page.locator('.el-menu-item:has-text("Dashboard")')).toBeVisible();
    });

    test('should display Asset Management submenu', async ({ page }) => {
      await expect(page.locator('.el-submenu:has-text("Asset Management")')).toBeVisible();
    });

    test('should display Maintenance menu item', async ({ page }) => {
      await expect(page.locator('.el-menu-item:has-text("Maintenance")')).toBeVisible();
    });

    test('should expand Asset Management submenu on click', async ({ page }) => {
      const submenu = page.locator('.el-submenu:has-text("Asset Management")');
      await submenu.click();
      await page.waitForTimeout(300);

      // Should show child menu items
      await expect(page.locator('.el-menu-item:has-text("House Assets")')).toBeVisible();
      await expect(page.locator('.el-menu-item:has-text("Parking Assets")')).toBeVisible();
      await expect(page.locator('.el-menu-item:has-text("Facility Assets")')).toBeVisible();
      await expect(page.locator('.el-menu-item:has-text("Venue Assets")')).toBeVisible();
      await expect(page.locator('.el-menu-item:has-text("Office Assets")')).toBeVisible();
    });

    test('should display correct icons in menu items', async ({ page }) => {
      // Dashboard icon
      await expect(page.locator('.el-menu-item:has-text("Dashboard") .el-icon-s-home')).toBeVisible();

      // Asset Management icon
      await expect(page.locator('.el-submenu:has-text("Asset Management") .el-icon-box')).toBeVisible();

      // Maintenance icon
      await expect(page.locator('.el-menu-item:has-text("Maintenance") .el-icon-tools')).toBeVisible();
    });
  });

  test.describe('Sidebar Collapse', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');
    });

    test('should have collapse toggle button in header', async ({ page }) => {
      const collapseButton = page.locator('.header-left button');
      await expect(collapseButton).toBeVisible();
    });

    test('should toggle sidebar collapse on button click', async ({ page }) => {
      const sidebar = page.locator('.sidebar, .el-aside');
      const collapseButton = page.locator('.header-left button');

      // Get initial width
      const initialWidth = await sidebar.evaluate((el) => el.offsetWidth);

      // Click toggle
      await collapseButton.click();
      await page.waitForTimeout(500);

      // Width should change
      const newWidth = await sidebar.evaluate((el) => el.offsetWidth);
      expect(newWidth).not.toBe(initialWidth);
    });
  });

  test.describe('Header', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');
    });

    test('should display user dropdown', async ({ page }) => {
      const dropdown = page.locator('.header-right .el-dropdown');
      await expect(dropdown).toBeVisible();
    });

    test('should display user name in dropdown', async ({ page }) => {
      const dropdownLink = page.locator('.el-dropdown-link');
      await expect(dropdownLink).toBeVisible();
    });

    test('should show dropdown menu on click', async ({ page }) => {
      const dropdownLink = page.locator('.el-dropdown-link');
      await dropdownLink.click();
      await page.waitForTimeout(300);

      // Check dropdown menu items
      await expect(page.locator('.el-dropdown-menu__item:has-text("Profile")')).toBeVisible();
      await expect(page.locator('.el-dropdown-menu__item:has-text("Logout")')).toBeVisible();
    });
  });

  test.describe('Navigation Flow', () => {
    test('should navigate through all asset modules', async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      // Navigate to house assets
      await page.goto('/asset/house');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/house/);
      await expect(page.locator('body')).toBeVisible();

      // Navigate to parking
      await page.goto('/asset/parking');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/parking/);
      await expect(page.locator('body')).toBeVisible();

      // Navigate to facility
      await page.goto('/asset/facility');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/facility/);
      await expect(page.locator('body')).toBeVisible();

      // Navigate to venue
      await page.goto('/asset/venue');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/venue/);
      await expect(page.locator('body')).toBeVisible();

      // Navigate to office
      await page.goto('/asset/office');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/office/);
      await expect(page.locator('body')).toBeVisible();

      // Navigate to maintenance
      await page.goto('/maintenance');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/maintenance/);
      await expect(page.locator('body')).toBeVisible();
    });

    test('should navigate via sidebar menu clicks', async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      // Click Dashboard
      await page.click('.el-menu-item:has-text("Dashboard")');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/(#\/)?(dashboard)?$/);

      // Open Asset Management and click House Assets
      await page.click('.el-submenu:has-text("Asset Management")');
      await page.waitForTimeout(300);
      await page.click('.el-menu-item:has-text("House Assets")');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/asset\/house/);

      // Click Maintenance
      await page.click('.el-menu-item:has-text("Maintenance")');
      await page.waitForLoadState('networkidle');
      await expect(page).toHaveURL(/.*\/maintenance/);
    });

    test('should have consistent layout across pages', async ({ page }) => {
      const pagesToTest = [
        '/',
        '/asset/house',
        '/asset/parking',
        '/asset/facility',
        '/asset/venue',
        '/asset/office',
        '/maintenance'
      ];

      for (const url of pagesToTest) {
        await page.goto(url);
        await page.waitForLoadState('networkidle');

        // Verify layout elements are present on each page
        await expect(page.locator('.sidebar, .el-aside')).toBeVisible();
        await expect(page.locator('.header, .el-header')).toBeVisible();
        await expect(page.locator('.main-content, .el-main')).toBeVisible();
      }
    });
  });

  test.describe('Active Menu State', () => {
    test('should highlight Dashboard when on dashboard', async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      const dashboardItem = page.locator('.el-menu-item[index="/dashboard"]');
      await expect(dashboardItem).toHaveClass(/is-active/);
    });

    test('should highlight House Assets when on house page', async ({ page }) => {
      await page.goto('/asset/house');
      await page.waitForLoadState('networkidle');

      const houseItem = page.locator('.el-menu-item[index="/asset/house"]');
      await expect(houseItem).toHaveClass(/is-active/);
    });

    test('should highlight Maintenance when on maintenance page', async ({ page }) => {
      await page.goto('/maintenance');
      await page.waitForLoadState('networkidle');

      const maintenanceItem = page.locator('.el-menu-item[index="/maintenance"]');
      await expect(maintenanceItem).toHaveClass(/is-active/);
    });
  });

  test.describe('Route Handling', () => {
    test('should redirect root to dashboard', async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      // Root should redirect to dashboard
      await expect(page).toHaveURL(/.*\/(#\/)?(dashboard)?$/);
    });

    test('should handle hash-based routing', async ({ page }) => {
      await page.goto('/#/asset/house');
      await page.waitForLoadState('networkidle');
      await expect(page.locator('body')).toBeVisible();
    });

    test('should handle non-existent pages gracefully', async ({ page }) => {
      await page.goto('/non-existent-page');
      await page.waitForLoadState('networkidle');

      // Should not crash, page should still load
      await expect(page.locator('body')).toBeVisible();
    });
  });

  test.describe('Responsive Navigation', () => {
    test('should display sidebar on desktop', async ({ page }) => {
      await page.setViewportSize({ width: 1920, height: 1080 });
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      await expect(page.locator('.sidebar, .el-aside')).toBeVisible();
    });

    test('should handle tablet viewport', async ({ page }) => {
      await page.setViewportSize({ width: 1024, height: 768 });
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      await expect(page.locator('body')).toBeVisible();
      // Layout should still be functional
      await expect(page.locator('.main-content, .el-main')).toBeVisible();
    });
  });

  test.describe('Navigation Performance', () => {
    test('should navigate between pages quickly', async ({ page }) => {
      await page.goto('/');
      await page.waitForLoadState('networkidle');

      const startTime = Date.now();

      await page.goto('/asset/house');
      await page.waitForLoadState('domcontentloaded');

      const endTime = Date.now();
      const loadTime = endTime - startTime;

      // Navigation should be reasonably fast (under 5 seconds)
      expect(loadTime).toBeLessThan(5000);
    });
  });
});
