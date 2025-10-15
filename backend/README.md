# Expense Tracker Backend

A Spring Boot REST API for the Expense Tracker application.

## Features

- User registration and authentication with JWT
- CRUD operations for expenses
- User-specific expense management
- Predefined expense categories
- CORS configuration for React frontend
- H2 database for development
- PostgreSQL support for production

## Technology Stack

- **Spring Boot 3.2.0** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence
- **JWT** - Token-based authentication
- **H2 Database** - Development database
- **PostgreSQL** - Production database
- **Maven** - Build tool

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

### 1. Clone and Navigate

```bash
cd backend
```

### 2. Build the Application

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 4. Access H2 Console (Development)

When running in development mode, you can access the H2 database console at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:expensedb`
- Username: `sa`
- Password: (leave empty)

## API Endpoints

### Authentication

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/api/auth/register` | Register new user | `{username, email, password}` |
| POST | `/api/auth/login` | User login | `{username, password}` |
| POST | `/api/auth/logout` | User logout | - |
| GET | `/api/auth/me` | Get current user | - |

### Expenses

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/api/expenses` | Get user's expenses | - |
| POST | `/api/expenses` | Create new expense | `{amount, description, category, date}` |
| GET | `/api/expenses/{id}` | Get specific expense | - |
| PUT | `/api/expenses/{id}` | Update expense | `{amount, description, category, date}` |
| DELETE | `/api/expenses/{id}` | Delete expense | - |
| GET | `/api/expenses/categories` | Get available categories | - |

### Authentication

All expense endpoints (except categories) require authentication. Include the JWT token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

## Available Expense Categories

- Food
- Transportation
- Entertainment
- Healthcare
- Shopping
- Utilities
- Other

## Configuration

### Development Profile (default)

The application uses H2 in-memory database by default. Configuration is in `application.yml`.

### Production Profile

Set the `SPRING_PROFILES_ACTIVE=prod` environment variable and configure these environment variables:

- `DATABASE_URL` - PostgreSQL connection URL
- `DATABASE_USERNAME` - Database username
- `DATABASE_PASSWORD` - Database password

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_PROFILES_ACTIVE` | Active profile | `dev` |
| `DATABASE_URL` | Database connection URL | `jdbc:h2:mem:expensedb` |
| `DATABASE_USERNAME` | Database username | `sa` |
| `DATABASE_PASSWORD` | Database password | (empty) |
| `JWT_SECRET` | JWT signing secret | (configured in yml) |
| `JWT_EXPIRATION` | JWT expiration time in ms | `900000` (15 minutes) |

## CORS Configuration

The application is configured to accept requests from:
- `http://localhost:3000` (React development server)
- `http://localhost:5000` (Alternative frontend port)

## Testing

### Manual Testing with curl

1. Register a user:
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","email":"test@example.com","password":"password123"}'
```

2. Login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'
```

3. Create an expense (replace TOKEN with the JWT from login):
```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TOKEN" \
  -d '{"amount":50.00,"description":"Lunch","category":"Food","date":"2023-12-01"}'
```

4. Get expenses:
```bash
curl -X GET http://localhost:8080/api/expenses \
  -H "Authorization: Bearer TOKEN"
```

## Frontend Integration

This backend is designed to work with the React frontend. Make sure the frontend's API base URL points to `http://localhost:8080/api`.

## Development Notes

- The application uses JPA auditing for automatic `createdAt` and `updatedAt` timestamps
- Passwords are encrypted using BCrypt
- JWT tokens expire after 15 minutes (configurable)
- User isolation is enforced - users can only access their own expenses
- Input validation is handled using Bean Validation annotations
- Global exception handling provides consistent error responses

## Production Deployment

1. Set the active profile to `prod`
2. Configure PostgreSQL connection
3. Ensure proper JWT secret configuration
4. Configure CORS for your production frontend URL
5. Build the application: `mvn clean package`
6. Run the JAR: `java -jar target/expense-tracker-backend-0.0.1-SNAPSHOT.jar`

## Troubleshooting

### Common Issues

1. **Port 8080 already in use**: Change the port in `application.yml` or kill the process using the port
2. **JWT token issues**: Check that the secret is properly configured and the token hasn't expired
3. **CORS errors**: Ensure the frontend URL is included in the CORS configuration
4. **Database connection issues**: Verify database credentials and connection URL

### Logs

The application logs will show detailed information about requests, database operations, and any errors.