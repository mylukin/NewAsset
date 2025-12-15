# Development Guide

## Getting Started

This guide will help you set up and run the Asset Management System in development mode.

## Prerequisites

- Java 17+
- Node.js 16+
- Maven 3.6+
- npm 8+

## Development Setup

### Backend Setup

1. **Install Dependencies**
   ```bash
   mvn clean install
   ```

2. **Create Database Directory**
   ```bash
   mkdir -p data
   ```

3. **Run Database Migrations**
   ```bash
   mvn flyway:migrate
   ```

4. **Start Backend Server**
   ```bash
   mvn spring-boot:run
   ```

The backend will start on http://localhost:8080

### Frontend Setup

1. **Install Dependencies**
   ```bash
   npm install
   ```

2. **Start Development Server**
   ```bash
   npm run serve
   ```

The frontend will start on http://localhost:8081

### Full Stack Development

To run both backend and frontend simultaneously:

**Terminal 1 (Backend):**
```bash
mvn spring-boot:run
```

**Terminal 2 (Frontend):**
```bash
npm run serve
```

## Development Workflow

### Backend Development

**Database Schema Changes**
- Edit SQL files in `sql/` directory
- Run migrations: `mvn flyway:migrate`

**API Development**
- Controllers are in `src/main/java/com/ruoyi/asset/controller/`
- Services are in `src/main/java/com/ruoyi/asset/service/`
- Entities are in `src/main/java/com/ruoyi/asset/domain/entity/`

**Testing**
```bash
mvn test
```

### Frontend Development

**Component Structure**
```
src/
├── components/    # Reusable Vue components
├── views/         # Page components
├── router/        # Vue Router configuration
├── store/         # Vuex store
├── api/           # API call functions
├── styles/        # SCSS styles
└── utils/         # Utility functions
```

**Testing**
```bash
npm run test
```

**Linting**
```bash
npm run lint
```

## Environment Configuration

### Backend Configuration

**Development Profile (application-dev.yml)**
```yaml
spring:
  datasource:
    url: jdbc:sqlite:./data/asset.db
  mybatis-plus:
    configuration:
      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

### Frontend Configuration

**Environment Variables (.env.development)**
```env
VUE_APP_API_BASE_URL=/
VUE_APP_DEV_MODE=true
```

## Debugging

### Backend Debugging

**Enable Debug Logging**
```yaml
logging:
  level:
    com.ruoyi.asset: DEBUG
```

**SQL Query Logging**
MyBatis-Plus SQL logs are enabled in development mode.

### Frontend Debugging

**Vue DevTools**
Install Vue DevTools browser extension for debugging.

**Console Logging**
Use browser dev tools console for debugging.

## Database Management

### SQLite Browser
- Install DB Browser for SQLite: https://sqlitebrowser.org/
- Open database file: `./data/asset.db`

### Reset Database
```bash
rm data/asset.db
mvn flyway:migrate
```

## API Testing

### Using curl
```bash
# Test health endpoint
curl http://localhost:8080/actuator/health

# Test asset list
curl http://localhost:8080/asset/house/list
```

### Using Postman
Import the API collection from `docs/api/`

## Troubleshooting

### Common Issues

**Backend won't start**
- Check Java version: `java -version`
- Check port 8080 availability: `lsof -i :8080`

**Frontend won't start**
- Check Node.js version: `node -v`
- Clear npm cache: `npm cache clean --force`
- Reinstall dependencies: `rm -rf node_modules && npm install`

**Database locked**
- Ensure no other processes are accessing the database
- Restart the application

**CORS errors**
- Check proxy configuration in `vue.config.js`
- Verify backend CORS settings

## Hot Reload

### Backend
Spring Boot dev tools provide automatic restart on code changes.

### Frontend
Vite provides hot module replacement (HMR) for instant updates.

## Testing

### Backend Tests
```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=AssetHouseServiceTest
```

### Frontend Tests
```bash
# Run unit tests
npm run test

# Run tests in watch mode
npm run test -- --watch
```

## Code Style

### Backend (Java)
- Follow Google Java Style Guide
- Use Lombok for boilerplate code
- Maximum line length: 100 characters

### Frontend (Vue/JavaScript)
- Follow Vue.js style guide
- Use ESLint configuration provided
- Use SCSS for styling
- Maximum line length: 100 characters

## Git Workflow

1. Create feature branch: `git checkout -b feature/new-feature`
2. Make changes and commit: `git commit -m "feat: add new feature"`
3. Push and create PR: `git push origin feature/new-feature`

## Performance Tips

### Backend
- Use database indexes for frequently queried fields
- Implement pagination for large datasets
- Enable query caching for expensive operations

### Frontend
- Use Vue's async components for code splitting
- Implement virtual scrolling for large lists
- Optimize images and assets

## Security

- Never commit credentials to version control
- Use environment variables for sensitive data
- Enable HTTPS in production
- Implement proper authentication and authorization

## Documentation

- Update README.md for user-facing changes
- Update this file for developer-facing changes
- Document API changes in docs/api/
- Write Javadoc for new Java code
- Add comments for complex Vue components
