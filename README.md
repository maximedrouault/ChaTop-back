# Chatop - Backend

Welcome to the Chatop backend, a project designed to manage rental listing advertisements. This project is developed using Spring Boot for the backend and Angular for the frontend.

## Prerequisites

- **Java 21 or later**: [Download Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html).
- **Maven 3.9.9 or later**: [Download Maven](https://maven.apache.org/download.cgi).
- **MySQL 8.0.40 or later**: [Download MySQL](https://dev.mysql.com/downloads/installer/).
- **AWS S3**: Used for storing advertisement images. Set up your AWS account here: [AWS S3](https://aws.amazon.com/s3/).

## Key Features

- Securing endpoints with **Spring Security**.
- User management with **JWT-based authentication**.
- Global exception handling using `@ControllerAdvice`.
- Rental listing management.
- User-to-user messaging.
- API documentation with **SpringDoc OpenAPI (Swagger)**.
- Data validation using **Spring Validation**.
- Interaction with **AWS S3** for file storage.
- Secret management via a `.env` file at the project root.
- Entity-to-DTO mapping using **MapStruct**.

## Installation and Setup

### Clone the Project

```bash
git clone https://github.com/maximedrouault/Chatop-back.git
cd ChaTop-back
```

### Environment Configuration

1. A `.env_example` file is located at the project root. Rename this file to `.env` and fill it in with your specific values (e.g., database information, AWS keys, etc.).

```env
DB_HOST=your_db_host
DB_PORT=your_db_port
DB_NAME=your_db_name
DB_USER=your_db_username
DB_PASSWORD=your_db_password

AWS_ACCESS_KEY_ID=your_access_key
AWS_SECRET_ACCESS_KEY=your_secret_key
AWS_BUCKET_NAME=your_bucket_name
AWS_REGION=your_region
```

2. Ensure your MySQL database is configured and the information matches.

3. Verify that the `.env` file is correctly configured with all necessary values for the application to run properly.

### Database Initialization

1. Ensure MySQL is installed and running on your machine.
2. Log in to your MySQL database with a user having appropriate privileges.
3. Create a database for the application:
   ```sql
   CREATE DATABASE chatop;
   ```
4. Import the `script.sql` file located in the `resources` folder at the project root to initialize the schema and data:
   ```bash
   mysql -u your_db_username -p your_db_password chatop < resources/script.sql
   ```
   Replace `your_db_username` and `your_db_password` with your MySQL credentials.
5. Verify that the database contains the necessary tables and data after the import.

### Install Dependencies

Run the following command to install Maven dependencies:

```bash
mvn clean install
```

### Run the Application

Start the application using the following command:

```bash
mvn spring-boot:run
```

The API will be available at: `http://localhost:3001`

## Testing the Project

To fully test this project:

1. **Frontend Angular**: Clone and set up the frontend part developed in Angular available at the following repository: [Chatop Frontend](https://github.com/maximedrouault/ChaTop-front). Follow the README instructions in that repository to run the frontend.

2. **Postman Collection**: A Postman collection is included in the project to test all endpoints. It is located at the project root in the `resources/ChaTop.postman_collection` file. Import this collection into Postman to easily execute requests to the various endpoints.

## API Documentation

Access the Swagger documentation here:

```
http://localhost:3001/swagger-ui/index.html
```

Use the **Authorize** button to authenticate with a JWT token. You can obtain the token by sending a POST request to the `/api/auth/login` endpoint with your user credentials.

### Available Test Users

The following users are available for testing endpoints (via Swagger or Postman) and obtaining a token. All passwords are: `test!31`:

- [**test@test.com**](mailto\:test@test.com)
- [**user1@example.com**](mailto\:user1@example.com)
- [**user2@example.com**](mailto\:user2@example.com)
- [**user3@example.com**](mailto\:user3@example.com)

## Project Structure

- **/src/main/java**: Contains the main source code.

    - **config**: API documentation configuration (SpringDoc OpenAPI Swagger).
    - **controller**: REST controllers.
    - **dto**: Data transfer objects.
    - **entity**: JPA entity definitions.
    - **exception**: Custom exception handling using a global exception handler (ControllerAdvice).
    - **mapper**: MapStruct mappings between DTOs and entities.
    - **repository**: JPA interfaces for data access.
    - **response**: Specific API response structures.
    - **security**: Security configurations and services (Spring Security and JWT).
    - **service**: Business logic.
    - **validation**: Classes for data validation.

- **/src/main/resources**:

    - **application.yaml**: Application configuration.

- **/resources**:

    - **ChaTop.postman\_collection**: Postman collection for testing endpoints.
    - **script.sql**: SQL script to initialize the database.

- **/.env\_example**: Example environment configuration file.

- **/README.md**: Main project documentation.