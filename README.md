# Asset Management System

A comprehensive asset management platform built with Spring Boot (backend) and Vue 2 (frontend).

## Features

- **Multi-asset Support**: Manage 5 types of assets (House, Parking, Facility, Venue, Office)
- **Lifecycle Tracking**: 9-state status machine for complete asset lifecycle
- **Maintenance Management**: Work order workflow with 6-state progression
- **Dashboard & Analytics**: Real-time KPIs and statistics
- **Excel Import**: Bulk asset import with validation
- **Role-based Access**: Permission-controlled module access

## Tech Stack

### Backend
- **Spring Boot 3.x**: Application framework
- **MyBatis-Plus**: Data access layer
- **SQLite**: Database (development)
- **Maven**: Build tool

### Frontend
- **Vue 2**: JavaScript framework
- **Element UI**: Component library
- **Vue Router**: Routing
- **Vuex**: State management
- **Axios**: HTTP client

## Prerequisites

- Java 17 or higher
- Node.js 16+ and npm 8+
- Maven 3.6+

## Quick Start

### 1. Clone the repository

```bash
git clone <repository-url>
cd NewAsset
```

### 2. Backend Setup

```bash
# Install dependencies
mvn clean install

# Create data directory
mkdir -p data

# Start backend (Spring Boot)
mvn spring-boot:run
```

Backend will start on http://localhost:8080

### 3. Frontend Setup

```bash
# Install dependencies
npm install

# Start development server
npm run serve
```

Frontend will start on http://localhost:8081

### 4. Access the Application

Open your browser and navigate to:
- **Application**: http://localhost:8081
- **API Documentation**: http://localhost:8080/doc.html

## Development

### Backend Development

**Database Configuration**
- Development uses SQLite (file-based)
- Database file: `./data/asset.db`
- Auto-creates tables on first run

**API Endpoints**
- Base path: `/asset`
- Example: `GET /asset/house/list`

**Hot Reload**
Backend automatically reloads on code changes.

### Frontend Development

**Project Structure**
```
src/
├── api/          # API calls
├── components/   # Reusable components
├── router/       # Vue Router configuration
├── store/        # Vuex store
├── styles/       # SCSS styles
├── views/        # Page components
├── utils/        # Utility functions
└── main.js       # Application entry
```

**Hot Reload**
Frontend automatically reloads on code changes.

### API Proxy Configuration

The frontend is configured to proxy API calls to the backend during development:

```javascript
// vue.config.js
proxy: {
  '/asset': {
    target: 'http://localhost:8080',
    changeOrigin: true
  }
}
```

## Environment Configuration

### Backend Configuration

**application.yml** (default)
```yaml
spring:
  datasource:
    url: jdbc:sqlite:./data/asset.db
server:
  port: 8080
```

### Frontend Configuration

**Environment Variables**
Create `.env.development` for development:
```env
VUE_APP_API_BASE_URL=/
```

## Database Management

### SQLite Browser Tools
- **DB Browser for SQLite**: https://sqlitebrowser.org/
- **sqlite3 CLI**: Command-line tool

### Schema Initialization
Database schema is automatically created from SQL files in `sql/` directory:
- `asset_core.sql`: Core tables
- `asset_house.sql`: House extension
- `asset_parking.sql`: Parking extension
- etc.

## Testing

### Backend Tests
```bash
mvn test
```

### Frontend Tests
```bash
npm run test:unit
```

## Building for Production

### Backend
```bash
mvn clean package
java -jar target/asset-management-1.0.0.jar
```

### Frontend
```bash
npm run build
```

The build artifacts will be stored in the `dist/` directory.

## Troubleshooting

### Backend Issues

**Port 8080 already in use**
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>
```

**SQLite database locked**
- Ensure no other process is accessing `./data/asset.db`
- Delete the lock file if it exists

### Frontend Issues

**Port 8081 already in use**
```bash
# Specify a different port
npm run serve -- --port 8082
```

**Node modules issues**
```bash
# Clean install
rm -rf node_modules package-lock.json
npm install
```

## API Documentation

Once the backend is running, visit:
- **Swagger UI**: http://localhost:8080/doc.html

## Contributing

1. Follow the existing code style
2. Write tests for new features
3. Update documentation as needed
4. Submit pull requests to the main branch

## License

Proprietary - All rights reserved

## Support

For issues and questions, please contact the development team.
