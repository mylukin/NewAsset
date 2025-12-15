---
id: core.environment-setup
module: core
priority: 1
status: passing
version: 11
origin: manual
dependsOn: []
supersedes: []
tags:
  - infrastructure
  - setup
verification:
  verifiedAt: '2025-12-15T23:10:09.848Z'
  verdict: fail
  verifiedBy: claude
  commitHash: c5b4807148679a6d98499241a8cd615ab5ae5fac
  summary: 0/9 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T23:23:30.873Z'
  generatedBy: codex
  forVersion: 10
  suggestedTestFiles:
    unit:
      - tests/core/environment-setup.test.ts
    e2e:
      - e2e/core/environment-setup.spec.ts
  unitTestCases:
    - name: >-
        should create Spring Boot project structure with expected core
        directories and build files
      assertions:
        - >-
          expect(existsSync('backend/pom.xml') || existsSync('pom.xml') ||
          existsSync('backend/build.gradle') ||
          existsSync('build.gradle')).toBe(true)
        - >-
          expect(existsSync('backend/src/main/java') ||
          existsSync('src/main/java')).toBe(true)
        - >-
          expect(existsSync('backend/src/test/java') ||
          existsSync('src/test/java')).toBe(true)
    - name: >-
        should configure SQLite database with a SQLite JDBC dependency and
        datasource settings
      assertions:
        - >-
          expect(readFileSync('backend/pom.xml', 'utf-8') +
          readFileSync('pom.xml',
          'utf-8')).toMatch(/sqlite-jdbc|org\.xerial:sqlite-jdbc/)
        - >-
          expect(readFileSync('backend/src/main/resources/application\.properties',
          'utf-8') + readFileSync('src/main/resources/application\.properties',
          'utf-8') + readFileSync('backend/src/main/resources/application\.yml',
          'utf-8') + readFileSync('src/main/resources/application\.yml',
          'utf-8')).toMatch(/jdbc:sqlite:/)
    - name: >-
        should create the main Spring Boot application class with
        @SpringBootApplication and a main method
      assertions:
        - >-
          expect(globSync('backend/src/main/java/**/*.java').length ||
          globSync('src/main/java/**/*.java').length).toBeGreaterThan(0)
        - expect(readAllJavaMainSources()).toMatch(/@SpringBootApplication/)
        - >-
          expect(readAllJavaMainSources()).toMatch(/public\s+static\s+void\s+main\s*\(String\[\]\s+args\)/)
    - name: should initialize a Vue 2 project structure with expected entry files
      assertions:
        - >-
          expect(existsSync('frontend/package.json') ||
          existsSync('package.json')).toBe(true)
        - >-
          expect(readPackageJson('frontend')?.dependencies?.vue ||
          readPackageJson('.')?.dependencies?.vue).toMatch(/^2\./)
        - >-
          expect(existsSync('frontend/src/main.js') ||
          existsSync('src/main.js')).toBe(true)
    - name: >-
        should configure Vue development environment with a dev server script
        and localhost base URL
      assertions:
        - >-
          expect(readPackageJson('frontend')?.scripts?.dev ||
          readPackageJson('.')?.scripts?.dev ||
          readPackageJson('frontend')?.scripts?.serve ||
          readPackageJson('.')?.scripts?.serve).toBeTruthy()
        - expect(getDevServerConfigText()).toMatch(/(localhost|127\.0\.0\.1)/)
    - name: >-
        should create frontend structure including App.vue and a basic
        route/view entry point
      assertions:
        - >-
          expect(existsSync('frontend/src/App.vue') ||
          existsSync('src/App.vue')).toBe(true)
        - >-
          expect(existsSync('frontend/src/components') ||
          existsSync('src/components')).toBe(true)
        - >-
          expect(existsSync('frontend/src/router') || existsSync('src/router')
          || existsSync('frontend/src/views') ||
          existsSync('src/views')).toBe(true)
    - name: >-
        should include development documentation describing setup and how to run
        backend and frontend
      assertions:
        - >-
          expect(existsSync('README.md') || existsSync('docs/development.md') ||
          existsSync('docs/DEVELOPMENT.md')).toBe(true)
        - expect(readDocText()).toMatch(/(setup|install|prerequisites)/i)
        - expect(readDocText()).toMatch(/(run|start|dev)/i)
    - name: >-
        should add development tools configuration for linting/formatting and
        editor consistency
      assertions:
        - expect(existsSync('.editorconfig')).toBe(true)
        - >-
          expect(existsSync('.eslintrc.js') || existsSync('.eslintrc.cjs') ||
          existsSync('.eslintrc.json') ||
          existsSync('eslint.config.js')).toBe(true)
        - >-
          expect(existsSync('.prettierrc') || existsSync('.prettierrc.json') ||
          existsSync('.prettierrc.js') ||
          existsSync('prettier.config.js')).toBe(true)
    - name: >-
        should provide start scripts to run backend, frontend, and optionally
        both together
      assertions:
        - >-
          expect(readPackageJson('.')?.scripts?.start ||
          readPackageJson('.')?.scripts?.dev).toBeTruthy()
        - >-
          expect(readPackageJson('.')?.scripts?.backend ||
          readPackageJson('.')?.scripts?.server ||
          readPackageJson('.')?.scripts?.frontend ||
          readPackageJson('.')?.scripts?.client).toBeTruthy()
        - >-
          expect(readPackageJson('.')?.scripts?.dev ||
          '').toMatch(/(concurrently|npm-run-all|turbo|pnpm|yarn)/)
  e2eScenarios:
    - name: user opens the Vue app and sees the home UI render
      steps:
        - start the frontend dev server
        - navigate to the app root URL
        - 'verify the page loads and a root element (e.g., #app) is visible'
    - name: user navigates within the frontend (router) and sees view content change
      steps:
        - start the frontend dev server
        - navigate to the app root URL
        - 'click a navigation link (e.g., Home/About)'
        - >-
          verify the URL changes (hash/history) and the displayed heading/text
          updates
    - name: >-
        user verifies development environment is wired (assets/styles load
        without errors)
      steps:
        - start the frontend dev server
        - navigate to the app root URL
        - open browser console logs
        - verify there are no failed network requests for main JS/CSS bundles
  frameworkHint: vitest
---
# Project Environment Setup

## Context

Configure complete development environment for Asset Management System with Spring Boot backend and Vue 3 frontend using SQLite for local development.

## Acceptance Criteria

1. Create Spring Boot project structure:
2. Configure SQLite database:
3. Create main application class:
4. Initialize Vue 2 project structure:
5. Configure Vue development environment:
6. Create frontend structure:
7. Create development documentation:
8. Add development tools configuration:
9. Create start scripts:
## Technical Stack

### Backend
- Spring Boot 3.x
- MyBatis-Plus for data access
- SQLite for local development
- Port: 8080 (default Spring Boot)
- Database file location: `./data/asset.db`

### Frontend
- Vue 2 with Options API
- Vue CLI for build tooling
- Element UI for UI components
- Vue Router for routing
- Axios for API calls
- Port: 8081 (default Vue CLI dev server)

## Development Workflow

1. Start backend: `mvn spring-boot:run` or `./mvnw spring-boot:run`
2. Start frontend: `npm run serve` or `yarn serve`
3. Access application: http://localhost:8081
4. Backend API: http://localhost:8080

## Notes

Verification failed: Verification failed: Project uses RuoYi structure (ruoyi-admin/ruoyi-ui) instead of expected (backend/frontend)
