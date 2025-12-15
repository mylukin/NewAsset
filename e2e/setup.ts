import { test as setup, expect } from '@playwright/test';

const authFile = 'playwright/.auth/user.json';

setup('authenticate', async ({ page }) => {
  // For now, just navigate to the home page
  // In a real application, you would log in here
  await page.goto('/');

  // Example authentication (if needed in the future):
  // await page.getByLabel('Username').fill('user');
  // await page.getByLabel('Password').fill('password');
  // await page.getByRole('button', { name: 'Sign in' }).click();

  // Wait for dashboard to load
  await expect(page.locator('text=Quick Actions')).toBeVisible();

  // Save authentication state
  await page.context().storageState({ path: authFile });
});
