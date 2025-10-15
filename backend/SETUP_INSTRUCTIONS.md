# Expense Tracker Backend Setup Instructions

## Prerequisites

Before running the Expense Tracker backend, ensure you have the following installed:

### Required Software

1. **Java Development Kit (JDK) 17 or higher**
   - Download from: https://adoptium.net/ or https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version` should show version 17 or higher
   - Set JAVA_HOME environment variable to JDK installation directory

2. **Maven 3.6+ (Optional - Maven Wrapper included)**
   - Download from: https://maven.apache.org/download.cgi
   - Or use the included Maven Wrapper (mvnw/mvnw.cmd)

## Quick Start

### Option 1: Using Maven Wrapper (Recommended)

The project includes Maven Wrapper, so you don't need to install Maven separately.

#### On Windows:
```cmd
# Build the project
./mvnw.cmd clean compile

# Run the application
./mvnw.cmd spring-boot:run
```

#### On Linux/macOS:
```bash
# Make wrapper executable
chmod +x mvnw

# Build the project
./mvnw clean compile

# Run the application
./mvnw spring-boot:run
```

### Option 2: Using Installed Maven

If you have Maven installed:

```bash
# Build the project
mvn clean compile

# Run the application
mvn spring-boot:run
```

## Configuration

### Default Configuration (Development)

The application runs with these default settings:
- **Port**: 8080
- **Database**: H2 in-memory database
- **Profile**: dev
- **CORS**: Enabled for localhost:3000 and localhost:5000

### Database Access (Development)

When running in development mode, you can access the H2 database console:
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: jdbc:h2:mem:expensedb
- **Username**: sa
- **Password**: (leave empty)

### Environment Variables

You can override default settings using environment variables:

```bash
# Set active profile
export SPRING_PROFILES_ACTIVE=prod

# Database configuration (for production)
export DATABASE_URL=jdbc:postgresql://localhost:5432/expensetracker
export DATABASE_USERNAME=postgres
export DATABASE_PASSWORD=your_password

# JWT configuration
export JWT_SECRET=your-secret-key-minimum-32-characters
export JWT_EXPIRATION=900000
```

## API Endpoints

Once the application is running, it will be available at `http://localhost:8080`

### Authentication Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | User login |
| POST | `/api/auth/logout` | User logout |
| GET | `/api/auth/me` | Get current user info |

### Expense Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/expenses` | Get user's expenses |
| POST | `/api/expenses` | Create new expense |
| GET | `/api/expenses/{id}` | Get specific expense |
| PUT | `/api/expenses/{id}` | Update expense |
| DELETE | `/api/expenses/{id}` | Delete expense |
| GET | `/api/expenses/categories` | Get available categories |

## Testing the API

### 1. Register a User

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123"
  }'
```

### 2. Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

Save the token from the response for authenticated requests.

### 3. Create an Expense

```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -d '{
    "amount": 50.00,
    "description": "Lunch at restaurant",
    "category": "Food",
    "date": "2023-12-01"
  }'
```

### 4. Get Expenses

```bash
curl -X GET http://localhost:8080/api/expenses \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

## Frontend Integration

### Updating Frontend Configuration

The frontend currently expects the API at `http://localhost:5000/api`. To connect to this Spring Boot backend:

1. **Option 1**: Update frontend's API base URL to `http://localhost:8080/api`
2. **Option 2**: Change backend port to 5000 by setting `server.port=5000` in application.yml

### Frontend API Service Configuration

Update the frontend's `src/services/api.js`:

```javascript
const API_BASE_URL = 'http://localhost:8080/api'; // Changed from port 5000 to 8080
```

## Production Deployment

### 1. Build for Production

```bash
./mvnw clean package -DskipTests
```

This creates a JAR file in the `target/` directory.

### 2. Run Production Build

```bash
java -jar target/expense-tracker-backend-0.0.1-SNAPSHOT.jar
```

### 3. Production Configuration

Set environment variables for production:

```bash
export SPRING_PROFILES_ACTIVE=prod
export DATABASE_URL=jdbc:postgresql://your-host:5432/expensetracker
export DATABASE_USERNAME=your_username
export DATABASE_PASSWORD=your_password
export JWT_SECRET=your-secure-secret-key-minimum-32-characters
```

## Troubleshooting

### Common Issues

1. **Port 8080 already in use**
   - Solution: Kill the process using port 8080 or change the port in application.yml

2. **Java not found**
   - Solution: Install JDK 17+ and set JAVA_HOME environment variable

3. **Database connection issues**
   - Solution: Verify database credentials and connection URL

4. **CORS issues from frontend**
   - Solution: Ensure frontend URL is in CORS configuration

5. **JWT token issues**
   - Solution: Check token expiration and secret configuration

### Getting Help

Check the application logs for detailed error information. The logs will show:
- Request/response details
- Database operations
- Authentication issues
- Any configuration problems

## Development Tips

1. **Auto-reload**: The application includes Spring Boot DevTools for automatic restart on code changes
2. **Database viewing**: Use H2 console to inspect database state during development
3. **API testing**: Use tools like Postman, curl, or the built-in browser for testing endpoints
4. **Logging**: Increase logging level by setting `logging.level.com.expensetracker=DEBUG`

## Next Steps

1. Start the backend application
2. Test the API endpoints
3. Update frontend configuration if needed
4. Register a user and test the complete flow
5. Enjoy your expense tracking application!