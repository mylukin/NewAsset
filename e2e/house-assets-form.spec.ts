import { test, expect } from '@playwright/test';

test.describe('House Assets Form Page', () => {
  test.describe('Add Form Page', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should load add form page', async ({ page }) => {
      await expect(page).toHaveURL(/.*\/asset\/house\/add/);
      await expect(page.locator('.house-asset-form, .el-form')).toBeVisible();
    });

    test('should display basic information section', async ({ page }) => {
      const sectionTitle = page.locator('h4:has-text("基本信息")');
      await expect(sectionTitle).toBeVisible();
    });

    test('should display responsibility section', async ({ page }) => {
      const sectionTitle = page.locator('h4:has-text("责任人信息")');
      await expect(sectionTitle).toBeVisible();
    });

    test('should display financial information section', async ({ page }) => {
      const sectionTitle = page.locator('h4:has-text("财务信息")');
      await expect(sectionTitle).toBeVisible();
    });

    test('should display house information section', async ({ page }) => {
      const sectionTitle = page.locator('h4:has-text("房屋信息")');
      await expect(sectionTitle).toBeVisible();
    });

    test('should display other information section', async ({ page }) => {
      const sectionTitle = page.locator('h4:has-text("其他信息")');
      await expect(sectionTitle).toBeVisible();
    });

    test('should display attachments section', async ({ page }) => {
      const sectionTitle = page.locator('h4:has-text("附件上传")');
      await expect(sectionTitle).toBeVisible();
    });
  });

  test.describe('Basic Information Form Fields', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should have asset name input field', async ({ page }) => {
      const nameInput = page.locator('.el-form-item:has-text("资产名称") input');
      await expect(nameInput).toBeVisible();
    });

    test('should have disabled asset type field', async ({ page }) => {
      const typeSelect = page.locator('.el-form-item:has-text("资产类型") .el-select');
      await expect(typeSelect).toBeVisible();

      // Check if it's disabled
      const isDisabled = await typeSelect.locator('.el-input').getAttribute('class');
      expect(isDisabled).toContain('is-disabled');
    });

    test('should have project select dropdown', async ({ page }) => {
      const projectSelect = page.locator('.el-form-item:has-text("项目") .el-select');
      await expect(projectSelect).toBeVisible();

      // Open dropdown
      await projectSelect.click();
      await page.waitForTimeout(300);

      // Check options
      await expect(page.locator('.el-select-dropdown__item:has-text("项目A")')).toBeVisible();
      await expect(page.locator('.el-select-dropdown__item:has-text("项目B")')).toBeVisible();
    });

    test('should have status select dropdown', async ({ page }) => {
      const statusSelect = page.locator('.el-form-item:has-text("状态") .el-select');
      await expect(statusSelect).toBeVisible();

      // Open dropdown
      await statusSelect.click();
      await page.waitForTimeout(300);

      // Check status options
      await expect(page.locator('.el-select-dropdown__item:has-text("可用-自用")')).toBeVisible();
      await expect(page.locator('.el-select-dropdown__item:has-text("可用-对外出租")')).toBeVisible();
      await expect(page.locator('.el-select-dropdown__item:has-text("可用-空置")')).toBeVisible();
    });

    test('should have location cascader', async ({ page }) => {
      const locationCascader = page.locator('.el-form-item:has-text("位置") .el-cascader');
      await expect(locationCascader).toBeVisible();
    });

    test('should have location description textarea', async ({ page }) => {
      const locationDescTextarea = page.locator('.el-form-item:has-text("位置描述") textarea');
      await expect(locationDescTextarea).toBeVisible();
    });
  });

  test.describe('Responsibility Form Fields', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should have responsible person input', async ({ page }) => {
      const personInput = page.locator('.el-form-item:has-text("负责人") input');
      await expect(personInput).toBeVisible();
    });

    test('should have contact phone input', async ({ page }) => {
      const phoneInput = page.locator('.el-form-item:has-text("联系电话") input');
      await expect(phoneInput).toBeVisible();
    });
  });

  test.describe('Financial Information Form Fields', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should have purchase date picker', async ({ page }) => {
      const datePicker = page.locator('.el-form-item:has-text("购置日期") .el-date-editor');
      await expect(datePicker).toBeVisible();
    });

    test('should have purchase price input', async ({ page }) => {
      const priceInput = page.locator('.el-form-item:has-text("购置价格") .el-input-number');
      await expect(priceInput).toBeVisible();
    });

    test('should have warranty expiry date picker', async ({ page }) => {
      const datePicker = page.locator('.el-form-item:has-text("保修到期") .el-date-editor');
      await expect(datePicker).toBeVisible();
    });
  });

  test.describe('House Information Form Fields', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should have building area input', async ({ page }) => {
      const areaInput = page.locator('.el-form-item:has-text("建筑面积") .el-input-number');
      await expect(areaInput).toBeVisible();
    });

    test('should have inner area input', async ({ page }) => {
      const areaInput = page.locator('.el-form-item:has-text("套内面积") .el-input-number');
      await expect(areaInput).toBeVisible();
    });

    test('should have house type select', async ({ page }) => {
      const typeSelect = page.locator('.el-form-item:has-text("户型") .el-select');
      await expect(typeSelect).toBeVisible();

      // Open dropdown
      await typeSelect.click();
      await page.waitForTimeout(300);

      // Check options
      await expect(page.locator('.el-select-dropdown__item:has-text("一居")')).toBeVisible();
      await expect(page.locator('.el-select-dropdown__item:has-text("两居")')).toBeVisible();
      await expect(page.locator('.el-select-dropdown__item:has-text("三居")')).toBeVisible();
    });

    test('should have house usage select', async ({ page }) => {
      const usageSelect = page.locator('.el-form-item:has-text("房屋用途") .el-select');
      await expect(usageSelect).toBeVisible();

      // Open dropdown
      await usageSelect.click();
      await page.waitForTimeout(300);

      // Check options
      await expect(page.locator('.el-select-dropdown__item:has-text("住宅")')).toBeVisible();
      await expect(page.locator('.el-select-dropdown__item:has-text("商用")')).toBeVisible();
      await expect(page.locator('.el-select-dropdown__item:has-text("公寓")')).toBeVisible();
    });

    test('should have current usage select', async ({ page }) => {
      const currentUsageSelect = page.locator('.el-form-item:has-text("当前用途") .el-select');
      await expect(currentUsageSelect).toBeVisible();

      // Open dropdown
      await currentUsageSelect.click();
      await page.waitForTimeout(300);

      // Check options
      await expect(page.locator('.el-select-dropdown__item:has-text("自住")')).toBeVisible();
      await expect(page.locator('.el-select-dropdown__item:has-text("出租")')).toBeVisible();
      await expect(page.locator('.el-select-dropdown__item:has-text("空置")')).toBeVisible();
    });

    test('should have current user input', async ({ page }) => {
      const userInput = page.locator('.el-form-item:has-text("当前使用人") input');
      await expect(userInput).toBeVisible();
    });

    test('should have contract number input', async ({ page }) => {
      const contractInput = page.locator('.el-form-item:has-text("合同编号") input');
      await expect(contractInput).toBeVisible();
    });

    test('should have rent unit price input', async ({ page }) => {
      const rentInput = page.locator('.el-form-item:has-text("租金单价") .el-input-number');
      await expect(rentInput).toBeVisible();
    });

    test('should have rent total input', async ({ page }) => {
      const rentTotalInput = page.locator('.el-form-item:has-text("租金总额") .el-input-number');
      await expect(rentTotalInput).toBeVisible();
    });
  });

  test.describe('Other Information Form Fields', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should have description textarea', async ({ page }) => {
      const descTextarea = page.locator('.el-form-item:has-text("描述") textarea');
      await expect(descTextarea).toBeVisible();
    });

    test('should have remark textarea', async ({ page }) => {
      const remarkTextarea = page.locator('.el-form-item:has-text("备注") textarea');
      await expect(remarkTextarea).toBeVisible();
    });
  });

  test.describe('Attachments Section', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should have file upload component', async ({ page }) => {
      const uploadComponent = page.locator('.el-upload');
      await expect(uploadComponent).toBeVisible();
    });

    test('should display upload hint text', async ({ page }) => {
      const uploadTip = page.locator('.el-upload__tip');
      await expect(uploadTip).toBeVisible();
      await expect(uploadTip).toContainText('支持jpg/png/gif/doc/pdf等格式');
    });

    test('should have drag and drop area', async ({ page }) => {
      const dragArea = page.locator('.el-upload-dragger');
      await expect(dragArea).toBeVisible();
    });
  });

  test.describe('Form Actions', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should display submit button', async ({ page }) => {
      const submitButton = page.locator('button:has-text("提交")');
      await expect(submitButton).toBeVisible();
      await expect(submitButton).toHaveClass(/el-button--primary/);
    });

    test('should display cancel button', async ({ page }) => {
      const cancelButton = page.locator('button:has-text("取消")');
      await expect(cancelButton).toBeVisible();
    });

    test('should display reset button', async ({ page }) => {
      const resetButton = page.locator('button:has-text("重置")');
      await expect(resetButton).toBeVisible();
    });
  });

  test.describe('Form Validation', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should show validation error for required asset name', async ({ page }) => {
      // Click submit without filling required fields
      await page.click('button:has-text("提交")');
      await page.waitForTimeout(500);

      // Check for validation error message
      const errorMessage = page.locator('.el-form-item__error');
      await expect(errorMessage.first()).toBeVisible();
    });

    test('should allow filling asset name', async ({ page }) => {
      const nameInput = page.locator('.el-form-item:has-text("资产名称") input');
      await nameInput.fill('测试房源资产');
      await expect(nameInput).toHaveValue('测试房源资产');
    });

    test('should clear form when clicking reset', async ({ page }) => {
      // Fill in some data
      const nameInput = page.locator('.el-form-item:has-text("资产名称") input');
      await nameInput.fill('测试数据');
      await expect(nameInput).toHaveValue('测试数据');

      // Click reset
      await page.click('button:has-text("重置")');
      await page.waitForTimeout(300);

      // Field should be cleared
      await expect(nameInput).toHaveValue('');
    });
  });

  test.describe('Edit Form Page', () => {
    test('should load edit form page with ID', async ({ page }) => {
      await page.goto('/asset/house/edit/1');
      await page.waitForLoadState('networkidle');

      await expect(page).toHaveURL(/.*\/asset\/house\/edit\/\d+/);
      await expect(page.locator('.house-asset-form, .el-form')).toBeVisible();
    });
  });

  test.describe('Form Interaction', () => {
    test.beforeEach(async ({ page }) => {
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');
    });

    test('should open location cascader and show options', async ({ page }) => {
      const cascader = page.locator('.el-form-item:has-text("位置") .el-cascader');
      await cascader.click();
      await page.waitForTimeout(500);

      // Check for cascader panel
      const panel = page.locator('.el-cascader-panel');
      await expect(panel).toBeVisible();
    });

    test('should open date picker', async ({ page }) => {
      const datePicker = page.locator('.el-form-item:has-text("购置日期") .el-date-editor');
      await datePicker.click();
      await page.waitForTimeout(300);

      // Check for date picker panel
      const panel = page.locator('.el-picker-panel');
      await expect(panel).toBeVisible();
    });

    test('should increment/decrement number inputs', async ({ page }) => {
      const buildingAreaInput = page.locator('.el-form-item:has-text("建筑面积") .el-input-number');

      // Click increment button
      const incrementButton = buildingAreaInput.locator('.el-input-number__increase');
      await incrementButton.click();
      await page.waitForTimeout(200);

      // Value should have increased
      const inputValue = await buildingAreaInput.locator('input').inputValue();
      expect(parseFloat(inputValue)).toBeGreaterThan(0);
    });
  });

  test.describe('Responsive Layout', () => {
    test('should display form properly on desktop', async ({ page }) => {
      await page.setViewportSize({ width: 1920, height: 1080 });
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');

      await expect(page.locator('.house-asset-form, .el-form')).toBeVisible();
    });

    test('should display form properly on tablet', async ({ page }) => {
      await page.setViewportSize({ width: 1024, height: 768 });
      await page.goto('/asset/house/add');
      await page.waitForLoadState('networkidle');

      await expect(page.locator('.house-asset-form, .el-form')).toBeVisible();
    });
  });
});
