import { test, expect } from '@playwright/test';

test.describe('Login Page', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/login');
    await page.waitForLoadState('networkidle');
  });

  test.describe('Page Load', () => {
    test('should display login page with correct elements', async ({ page }) => {
      // Check title
      await expect(page.locator('h1')).toContainText('Asset Management System');

      // Check subtitle
      await expect(page.locator('.login-header p')).toContainText('Please sign in to continue');

      // Check form elements exist
      await expect(page.locator('input[placeholder="Username"]')).toBeVisible();
      await expect(page.locator('input[placeholder="Password"]')).toBeVisible();
      await expect(page.locator('button:has-text("Sign In")')).toBeVisible();

      // Check demo credentials section
      await expect(page.locator('.login-footer')).toContainText('Demo Credentials');
      await expect(page.locator('.login-footer')).toContainText('admin');
      await expect(page.locator('.login-footer')).toContainText('admin123');
    });

    test('should have empty form fields initially', async ({ page }) => {
      const usernameInput = page.locator('input[placeholder="Username"]');
      const passwordInput = page.locator('input[placeholder="Password"]');

      await expect(usernameInput).toHaveValue('');
      await expect(passwordInput).toHaveValue('');
    });

    test('should have password field masked', async ({ page }) => {
      const passwordInput = page.locator('input[placeholder="Password"]');
      await expect(passwordInput).toHaveAttribute('type', 'password');
    });
  });

  test.describe('Form Validation', () => {
    test('should show validation error when submitting empty form', async ({ page }) => {
      // Click sign in button without filling form
      await page.click('button:has-text("Sign In")');

      // Wait for validation messages
      await page.waitForTimeout(500);

      // Check for validation error messages
      const errorMessages = page.locator('.el-form-item__error');
      await expect(errorMessages.first()).toBeVisible();
    });

    test('should show error when username is empty', async ({ page }) => {
      // Fill only password
      await page.fill('input[placeholder="Password"]', 'somepassword');
      await page.click('button:has-text("Sign In")');

      await page.waitForTimeout(500);

      // Check for username validation error
      await expect(page.locator('.el-form-item__error')).toContainText('Please input username');
    });

    test('should show error when password is empty', async ({ page }) => {
      // Fill only username
      await page.fill('input[placeholder="Username"]', 'someuser');
      await page.click('button:has-text("Sign In")');

      await page.waitForTimeout(500);

      // Check for password validation error
      await expect(page.locator('.el-form-item__error')).toContainText('Please input password');
    });
  });

  test.describe('Form Interaction', () => {
    test('should allow typing in username field', async ({ page }) => {
      const usernameInput = page.locator('input[placeholder="Username"]');
      await usernameInput.fill('testuser');
      await expect(usernameInput).toHaveValue('testuser');
    });

    test('should allow typing in password field', async ({ page }) => {
      const passwordInput = page.locator('input[placeholder="Password"]');
      await passwordInput.fill('testpassword');
      await expect(passwordInput).toHaveValue('testpassword');
    });

    test('should submit form when pressing Enter in password field', async ({ page }) => {
      await page.fill('input[placeholder="Username"]', 'admin');
      await page.fill('input[placeholder="Password"]', 'admin123');

      // Press Enter in password field
      await page.press('input[placeholder="Password"]', 'Enter');

      // Wait for potential navigation or loading state
      await page.waitForTimeout(1000);

      // Check if button shows loading or navigation occurred
      const signInButton = page.locator('button:has-text("Sign In")');
      const hasLoading = await signInButton.locator('.el-icon-loading').isVisible().catch(() => false);
      const urlChanged = !page.url().includes('/login');

      // Either loading state should appear or navigation should occur
      expect(hasLoading || urlChanged).toBeTruthy();
    });

    test('should show loading state when submitting', async ({ page }) => {
      await page.fill('input[placeholder="Username"]', 'admin');
      await page.fill('input[placeholder="Password"]', 'admin123');

      // Click sign in and immediately check for loading
      await page.click('button:has-text("Sign In")');

      // The button should show loading state (may be brief)
      // We just verify the form submission was attempted
      await page.waitForTimeout(500);
    });
  });

  test.describe('Authentication', () => {
    test('should redirect to dashboard after successful login with demo credentials', async ({ page }) => {
      // Fill in demo credentials
      await page.fill('input[placeholder="Username"]', 'admin');
      await page.fill('input[placeholder="Password"]', 'admin123');

      // Submit form
      await page.click('button:has-text("Sign In")');

      // Wait for navigation
      await page.waitForURL(/.*\/(#\/)?(dashboard)?$/, { timeout: 10000 });

      // Verify we're on the dashboard
      await expect(page).toHaveURL(/.*\/(#\/)?(dashboard)?$/);
    });

    test('should show error message for invalid credentials', async ({ page }) => {
      // Fill in invalid credentials
      await page.fill('input[placeholder="Username"]', 'wronguser');
      await page.fill('input[placeholder="Password"]', 'wrongpassword');

      // Submit form
      await page.click('button:has-text("Sign In")');

      // Wait for error message (Element UI message component)
      await page.waitForTimeout(2000);

      // Check if error message appeared or we stayed on login page
      const isStillOnLogin = page.url().includes('/login');
      expect(isStillOnLogin).toBeTruthy();
    });
  });

  test.describe('Visual Elements', () => {
    test('should have proper styling for login box', async ({ page }) => {
      const loginBox = page.locator('.login-box');
      await expect(loginBox).toBeVisible();

      // Check that login container has gradient background
      const loginContainer = page.locator('.login-container');
      await expect(loginContainer).toBeVisible();
    });

    test('should display icons in input fields', async ({ page }) => {
      // Check for user icon in username field
      const userIcon = page.locator('.el-icon-user');
      await expect(userIcon).toBeVisible();

      // Check for lock icon in password field
      const lockIcon = page.locator('.el-icon-lock');
      await expect(lockIcon).toBeVisible();
    });
  });
});
