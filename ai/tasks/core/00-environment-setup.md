---
id: core.environment-setup
module: core
priority: 1
status: passing
version: 2
origin: manual
dependsOn: []
supersedes: []
tags:
  - infrastructure
  - setup
---
# Project Environment Setup

## Context

Configure complete development environment for Asset Management System with Spring Boot backend and Vue 3 frontend using SQLite for local development.

## Acceptance Criteria

### Backend (Spring Boot)

1. Create Spring Boot project structure:
   - `pom.xml` or `build.gradle` build configuration
   - `src/main/resources/application.yml` with SQLite config
   - `src/main/resources/application-dev.yml` for development overrides

2. Configure SQLite database:
   - Add SQLite dependency to build file
   - Configure SQLite datasource in application.yml
   - Set SQL dialect to SQLite
   - Enable auto-schema creation for development

3. Create main application class:
   - `src/main/java/com/ruoyi/AssetApplication.java`
   - Add @SpringBootApplication annotation
   - Enable transaction management

### Frontend (Vue 2)

4. Initialize Vue 2 project structure:
   - `package.json` with Vue 2, Vue CLI, Element UI dependencies
   - `vue.config.js` for build configuration
   - `src/main.js` entry point
   - `src/App.vue` root component

5. Configure Vue development environment:
   - Set up Vue Router for SPA routing
   - Configure Element UI framework
   - Set up Axios for backend API communication
   - Configure proxy for backend API calls

6. Create frontend structure:
   - `src/views/` for page components
   - `src/api/` for API calls
   - `src/components/` for reusable components
   - `src/utils/` for utility functions

### Development Setup

7. Create development documentation:
   - `README.md` with complete setup instructions
   - Backend setup steps (Spring Boot + SQLite)
   - Frontend setup steps (Vue 3 + Vite)
   - Environment variables configuration
   - Database initialization steps

8. Add development tools configuration:
   - Hot reload for both backend and frontend
   - Database browser tool recommendation (SQLite)
   - API testing tool setup (Postman/Insomnia)
   - IDE configuration files (.vscode/settings.json)

9. Create start scripts:
   - `package.json` scripts for frontend dev server
   - Backend dev server configuration
   - Concurrent startup script for both services

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
