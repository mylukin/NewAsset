---
id: core.environment-setup
module: core
priority: 1
status: passing
version: 8
origin: manual
dependsOn: []
supersedes: []
tags:
  - infrastructure
  - setup
verification:
  verifiedAt: '2025-12-15T22:52:41.281Z'
  verdict: fail
  verifiedBy: claude
  commitHash: db4af33d60db9bf74461a93f35fad255e97156e6
  summary: 6/9 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T22:51:06.071Z'
  generatedBy: claude
  forVersion: 6
  suggestedTestFiles:
    unit:
      - tests/core/environment-setup.test.ts
    e2e:
      - e2e/core/environment-setup.spec.ts
  unitTestCases:
    - name: should have valid Spring Boot project structure
      assertions:
        - expect(fs.existsSync('src/main/java')).toBe(true)
        - expect(fs.existsSync('src/main/resources')).toBe(true)
        - expect(fs.existsSync('pom.xml')).toBe(true)
    - name: should have SQLite database configuration
      assertions:
        - expect(applicationProperties).toContain('sqlite')
        - >-
          expect(fs.existsSync('src/main/resources/application.properties')).toBe(true)
    - name: should have main application class with SpringBootApplication annotation
      assertions:
        - expect(mainAppContent).toContain('@SpringBootApplication')
        - expect(mainAppContent).toContain('public static void main')
    - name: should have Vue 2 project structure initialized
      assertions:
        - expect(packageJson.dependencies).toHaveProperty('vue')
        - expect(fs.existsSync('frontend/src')).toBe(true)
    - name: should have Vue development environment configured
      assertions:
        - expect(fs.existsSync('frontend/vite.config.ts')).toBe(true)
        - expect(packageJson.scripts).toHaveProperty('dev')
    - name: should have proper frontend directory structure
      assertions:
        - expect(fs.existsSync('frontend/src/components')).toBe(true)
        - expect(fs.existsSync('frontend/src/views')).toBe(true)
        - expect(fs.existsSync('frontend/src/App.vue')).toBe(true)
    - name: should have development documentation
      assertions:
        - expect(fs.existsSync('docs/development.md')).toBe(true)
        - expect(devDocsContent).toContain('setup')
    - name: should have development tools configuration files
      assertions:
        - expect(fs.existsSync('.editorconfig')).toBe(true)
        - expect(fs.existsSync('.prettierrc')).toBe(true)
    - name: should have start scripts for development
      assertions:
        - expect(fs.existsSync('scripts/start-backend.sh')).toBe(true)
        - expect(fs.existsSync('scripts/start-frontend.sh')).toBe(true)
  e2eScenarios:
    - name: development environment loads successfully
      steps:
        - start frontend development server
        - 'navigate to localhost:5173'
        - verify Vue app renders without errors
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
