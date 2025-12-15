# E2E Testing with Playwright

This directory contains end-to-end tests for the Asset Management System using Playwright.

## Prerequisites

1. Ensure the frontend application is running:
   ```bash
   npm run serve
   ```

2. Install Playwright browsers:
   ```bash
   npx playwright install chromium
   ```

## Running Tests

### Run all tests
```bash
npm run test:e2e
```

### Run tests in UI mode
```bash
npm run test:e2e:ui
```

### Run tests in headed mode (with browser visible)
```bash
npm run test:e2e:headed
```

### View test report
```bash
npm run test:e2e:report
```

## Test Files

- `dashboard.spec.ts` - Tests for the dashboard page
- `house-assets.spec.ts` - Tests for house asset management
- `parking-assets.spec.ts` - Tests for parking asset management
- `facility-assets.spec.ts` - Tests for facility asset management
- `venue-assets.spec.ts` - Tests for venue asset management
- `office-assets.spec.ts` - Tests for office asset management
- `maintenance.spec.ts` - Tests for maintenance module
- `navigation.spec.ts` - Tests for navigation between pages

## Configuration

See `playwright.config.ts` for test configuration options.

## Test Coverage

The tests cover:
- Page loading and rendering
- Navigation between modules
- UI component visibility
- Basic functionality of each module
- Responsive layout checks

## Screenshots

On test failure, screenshots are automatically saved to `test-results/` directory.
