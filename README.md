# Expense Tracker Application

A full-stack web application for tracking personal expenses, built with Spring Boot (Java) backend and React frontend. Features user authentication, expense management with CRUD operations, and a responsive design.

## 🚀 Features

- **User Authentication**: Secure JWT-based registration and login
- **Expense Management**: Create, read, update, and delete expenses
- **Category Organization**: Predefined expense categories (Food, Transportation, Entertainment, etc.)
- **Responsive Design**: Mobile-first design with flexbox grid layout
- **Real-time Updates**: Dynamic expense list with immediate UI feedback
- **Secure API**: Protected endpoints with user isolation
- **Modern Architecture**: RESTful API with clean separation of concerns

## 🛠 Technology Stack

### Backend
- **Spring Boot 3.2.0** - Enterprise Java application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence and ORM
- **JWT (JSON Web Tokens)** - Stateless authentication
- **H2 Database** - Development database (embedded)
- **PostgreSQL** - Production database
- **Maven** - Dependency management and build tool

### Frontend
- **React 18** - Modern UI library with hooks
- **React Router** - Client-side routing and navigation
- **React Hook Form** - Form management and validation
- **Axios** - HTTP client for API communication
- **CSS Flexbox** - Responsive grid layouts
- **JWT** - Token-based authentication

## 📁 Project Structure

```
ExpenseTracker_Java/
├── backend/                    # Spring Boot API server
│   ├── src/main/java/com/expensetracker/
│   │   ├── config/            # Security and CORS configuration
│   │   ├── controller/        # REST API endpoints
│   │   ├── dto/               # Data Transfer Objects
│   │   ├── entity/            # JPA entities
│   │   ├── repository/        # Data access layer
│   │   ├── service/           # Business logic layer
│   │   ├── security/          # JWT and authentication
│   │   ├── exception/         # Error handling
│   │   └── util/              # Utility classes
│   ├── src/main/resources/
│   │   ├── application.yml    # Application configuration
│   │   └── data.sql          # Sample data
│   └── pom.xml               # Maven dependencies
├── frontend/                  # React application
│   ├── src/
│   │   ├── components/       # Reusable UI components
│   │   ├── pages/           # Page components
│   │   ├── services/        # API integration
│   │   ├── context/         # React context providers
│   │   └── utils/           # Helper functions
│   └── package.json         # NPM dependencies
├── ARCHITECTURE.md          # Detailed technical documentation
└── README.md               # This file
```

## 🚦 Getting Started

### Prerequisites

- **Java 17+** (for backend)
- **Node.js 18+** (for frontend)
- **Maven 3.6+** (for backend build)

### Quick Start

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ExpenseTracker_Java
   ```

2. **Start the Backend**
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```
   Backend will be available at: `http://localhost:8080`

3. **Start the Frontend** (in a new terminal)
   ```bash
   cd frontend
   npm install
   npm start
   ```
   Frontend will be available at: `http://localhost:3000`

4. **Access the Application**
   - Open `http://localhost:3000` in your browser
   - Register a new account or use the demo data
   - Start tracking your expenses!

### Database Access (Development)

The H2 database console is available at: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:expensedb`
- **Username**: `sa`
- **Password**: (leave empty)

## 📖 API Documentation

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

All expense endpoints require JWT authentication via `Authorization: Bearer <token>` header.

## 💳 Expense Categories

- **Food** - Meals, groceries, dining out
- **Transportation** - Gas, public transport, rideshare
- **Entertainment** - Movies, games, subscriptions
- **Healthcare** - Medical bills, prescriptions, insurance
- **Shopping** - Clothing, electronics, general purchases
- **Utilities** - Electricity, water, internet, phone
- **Other** - Miscellaneous expenses

## 🔧 Configuration

### Development Environment

The application uses H2 in-memory database by default for development. No additional setup required.

### Production Environment

Set environment variables for production deployment:

```bash
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:postgresql://localhost:5432/expensetracker
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
JWT_SECRET=your_secure_secret_key
```

## 🧪 Testing

### Backend Testing
```bash
cd backend
mvn test
```

### Frontend Testing
```bash
cd frontend
npm test
```

### Manual API Testing

Example registration and expense creation:

```bash
# Register user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","email":"test@example.com","password":"password123"}'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'

# Create expense (replace TOKEN with JWT from login)
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TOKEN" \
  -d '{"amount":25.50,"description":"Lunch","category":"Food","date":"2023-12-01"}'
```

## 🏗 Architecture

This application follows a modern 3-tier architecture:

- **Presentation Layer**: React frontend with responsive design
- **Business Logic Layer**: Spring Boot services with JWT authentication
- **Data Access Layer**: Spring Data JPA with H2/PostgreSQL

Key architectural features:
- RESTful API design
- JWT stateless authentication
- User data isolation
- Responsive mobile-first UI
- Modular component structure

For detailed architecture documentation, see [`ARCHITECTURE.md`](ARCHITECTURE.md).

## 📱 Responsive Design

The application features a mobile-first responsive design:
- **Mobile**: Single column layout (< 768px)
- **Tablet**: Two column layout (768px - 1024px)
- **Desktop**: Three+ column layout (> 1024px)

## 🔒 Security Features

- **JWT Authentication**: Stateless token-based auth
- **Password Encryption**: BCrypt password hashing
- **CORS Protection**: Configured for frontend origins
- **Input Validation**: Server-side validation with Bean Validation
- **SQL Injection Prevention**: JPA/Hibernate parameterized queries
- **User Isolation**: Users can only access their own data

## 🚀 Deployment

### Production Build

**Backend:**
```bash
cd backend
mvn clean package
java -jar target/expense-tracker-backend-0.0.1-SNAPSHOT.jar
```

**Frontend:**
```bash
cd frontend
npm run build
# Serve the build folder with a web server
```

### Docker Deployment

Dockerfiles and docker-compose configuration can be added for containerized deployment.

## 📚 Development

### Adding New Features

1. **Backend**: Add controllers, services, and entities in appropriate packages
2. **Frontend**: Create components in relevant directories under `src/components/`
3. **API Integration**: Update service files in `src/services/`

### Code Style

- **Backend**: Follow Spring Boot conventions and Java naming standards
- **Frontend**: Use functional components with hooks, follow React best practices
- **Database**: Use JPA annotations and follow naming conventions

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## 📄 License

This project is a demo application for educational and portfolio purposes.

## 🆘 Troubleshooting

### Common Issues

**Port conflicts:**
- Backend default: 8080
- Frontend default: 3000
- Change ports in `application.yml` or `package.json` if needed

**CORS errors:**
- Ensure frontend URL is in backend CORS configuration
- Check that backend is running before starting frontend

**Database issues:**
- Verify H2 console access for development
- Check PostgreSQL connection for production

**Authentication errors:**
- Verify JWT secret configuration
- Check token expiration (default: 15 minutes)

For detailed troubleshooting, see individual README files in [`backend/`](backend/README.md) and [`frontend/`](frontend/README.md) directories.

---

**Happy expense tracking!** 💰📊