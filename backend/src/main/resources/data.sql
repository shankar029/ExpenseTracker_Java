SELECT 1;
-- This file will be executed automatically by Spring Boot on startup
-- It's useful for development and testing

-- Sample data will be inserted only if tables are empty
-- You can remove this file in production or use it for initial setup data

-- Example: Insert sample categories (though they're handled by enum)
-- The ExpenseCategory enum handles the categories, so no need to insert them here

-- Example users and expenses would be inserted here if needed for testing
-- For security reasons, we're not including sample data in this file
-- Users should register through the API

-- To test the application:
-- 1. Start the application
-- 2. Register a new user via POST /api/auth/register
-- 3. Login via POST /api/auth/login to get a token
-- 4. Use the token to create expenses via POST /api/expenses