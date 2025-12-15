---
id: core.environment-setup
module: core
priority: 1
status: passing
version: 6
origin: manual
dependsOn: []
supersedes: []
tags:
  - infrastructure
  - setup
verification:
  verifiedAt: '2025-12-15T15:11:23.560Z'
  verdict: pass
  verifiedBy: claude
  commitHash: 9ade1cc9307bcbd6ff37f4922629593a7d8cfc2d
  summary: 9/9 criteria satisfied
tddGuidance:
  generatedAt: '2025-12-15T14:55:38.598Z'
  generatedBy: claude
  forVersion: 2
  suggestedTestFiles:
    unit:
      - tests/core/environment-setup.test.ts
    e2e:
      - e2e/core/environment-setup.spec.ts
  unitTestCases:
    - name: should create Spring Boot project structure
      assertions:
        - >-
          expect(fs.existsSync('src/main/java/com/example/application')).toBe(true)
        - expect(fs.existsSync('src/main/resources')).toBe(true)
        - expect(fs.existsSync('src/test/java')).toBe(true)
        - expect(fs.existsSync('pom.xml')).toBe(true)
    - name: should configure SQLite database
      assertions:
        - >-
          expect(fs.existsSync('src/main/resources/application.properties')).toBe(true)
        - >-
          expect(fs.readFileSync('src/main/resources/application.properties',
          'utf8')).toContain('jdbc:sqlite')
        - 'expect(fs.readFileSync(''pom.xml'', ''utf8'')).toContain(''sqlite-jdbc'')'
    - name: should create main application class
      assertions:
        - >-
          expect(fs.existsSync('src/main/java/com/example/application/Application.java')).toBe(true)
        - >-
          expect(fs.readFileSync('src/main/java/com/example/application/Application.java',
          'utf8')).toContain('@SpringBootApplication')
        - >-
          expect(fs.readFileSync('src/main/java/com/example/application/Application.java',
          'utf8')).toContain('main(String[] args)')
    - name: should initialize Vue 2 project structure
      assertions:
        - expect(fs.existsSync('src/components')).toBe(true)
        - expect(fs.existsSync('src/views')).toBe(true)
        - expect(fs.existsSync('src/router')).toBe(true)
        - expect(fs.existsSync('src/store')).toBe(true)
    - name: should configure Vue development environment
      assertions:
        - expect(fs.existsSync('package.json')).toBe(true)
        - expect(fs.existsSync('vite.config.js')).toBe(true)
        - 'expect(fs.readFileSync(''package.json'', ''utf8'')).toContain(''vue'')'
        - >-
          expect(fs.readFileSync('package.json',
          'utf8')).toContain('vue-template-compiler')
    - name: should create frontend structure
      assertions:
        - expect(fs.existsSync('public/index.html')).toBe(true)
        - expect(fs.existsSync('src/App.vue')).toBe(true)
        - expect(fs.existsSync('src/main.js')).toBe(true)
        - expect(fs.existsSync('src/assets')).toBe(true)
    - name: should create development documentation
      assertions:
        - expect(fs.existsSync('README.md')).toBe(true)
        - 'expect(fs.readFileSync(''README.md'', ''utf8'')).toContain(''development'')'
        - expect(fs.existsSync('docs/DEVELOPMENT.md')).toBe(true)
        - >-
          expect(fs.readFileSync('docs/DEVELOPMENT.md',
          'utf8')).toContain('setup')
    - name: should add development tools configuration
      assertions:
        - expect(fs.existsSync('.vscode/settings.json')).toBe(true)
        - expect(fs.existsSync('.eslintrc.js')).toBe(true)
        - expect(fs.existsSync('.gitignore')).toBe(true)
        - >-
          expect(fs.readFileSync('.gitignore',
          'utf8')).toContain('node_modules')
    - name: should create start scripts
      assertions:
        - expect(fs.existsSync('package.json')).toBe(true)
        - 'expect(fs.readFileSync(''package.json'', ''utf8'')).toContain(''dev'')'
        - 'expect(fs.readFileSync(''package.json'', ''utf8'')).toContain(''build'')'
        - 'expect(fs.readFileSync(''package.json'', ''utf8'')).toContain(''start'')'
  e2eScenarios: []
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
