---
id: core.environment-setup
module: core
priority: 1
status: failed
version: 10
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
  generatedAt: '2025-12-15T23:09:29.008Z'
  generatedBy: claude
  forVersion: 8
  suggestedTestFiles:
    unit:
      - tests/core/environment-setup.test.ts
    e2e:
      - e2e/core/environment-setup.spec.ts
  unitTestCases:
    - name: should have valid Spring Boot project structure
      assertions:
        - expect(fs.existsSync('backend/src/main/java')).toBe(true)
        - expect(fs.existsSync('backend/pom.xml')).toBe(true)
        - expect(fs.existsSync('backend/src/main/resources')).toBe(true)
    - name: should have SQLite database configuration
      assertions:
        - expect(applicationProperties).toContain('spring.datasource.url')
        - 'expect(applicationProperties).toContain(''jdbc:sqlite'')'
        - >-
          expect(applicationProperties).toContain('spring.jpa.database-platform')
    - name: should have main application class with correct annotations
      assertions:
        - >-
          expect(fs.existsSync('backend/src/main/java/com/newasset/NewAssetApplication.java')).toBe(true)
        - expect(mainAppContent).toContain('@SpringBootApplication')
        - expect(mainAppContent).toContain('public static void main')
    - name: should have Vue 2 project structure initialized
      assertions:
        - expect(fs.existsSync('frontend/package.json')).toBe(true)
        - expect(packageJson.dependencies).toHaveProperty('vue')
        - expect(packageJson.dependencies.vue).toMatch(/^2\./)
    - name: should have Vue development environment configured
      assertions:
        - expect(fs.existsSync('frontend/vue.config.js')).toBe(true)
        - expect(packageJson.devDependencies).toHaveProperty('@vue/cli-service')
        - expect(vueConfig).toContain('devServer')
    - name: should have proper frontend directory structure
      assertions:
        - expect(fs.existsSync('frontend/src/components')).toBe(true)
        - expect(fs.existsSync('frontend/src/views')).toBe(true)
        - expect(fs.existsSync('frontend/src/router')).toBe(true)
        - expect(fs.existsSync('frontend/src/store')).toBe(true)
    - name: should have development documentation
      assertions:
        - expect(fs.existsSync('docs/DEVELOPMENT.md')).toBe(true)
        - expect(devDocsContent).toContain('Getting Started')
        - expect(devDocsContent).toContain('Prerequisites')
    - name: should have development tools configuration files
      assertions:
        - expect(fs.existsSync('.editorconfig')).toBe(true)
        - expect(fs.existsSync('.gitignore')).toBe(true)
        - expect(fs.existsSync('frontend/.eslintrc.js')).toBe(true)
    - name: should have start scripts for development
      assertions:
        - expect(fs.existsSync('scripts/start-backend.sh')).toBe(true)
        - expect(fs.existsSync('scripts/start-frontend.sh')).toBe(true)
        - expect(packageJson.scripts).toHaveProperty('dev')
  e2eScenarios:
    - name: frontend application loads successfully
      steps:
        - 'navigate to http://localhost:8080'
        - wait for Vue app to mount
        - verify main app container is visible
    - name: backend API is accessible
      steps:
        - 'send GET request to http://localhost:3000/api/health'
        - verify response status is 200
        - verify response contains health status
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
