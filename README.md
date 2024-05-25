
#### Introduction
This repository contains two Spring Boot applications: restfuldemo and secondapp. These applications demonstrate various features, including CRUD operations, validation, logging, and communication between two separate apps.

#### Technologies Used
- Spring Boot 3.2.5
- Spring Data JPA with H2 Database
- Thymeleaf template engine
- Maven/Gradle for dependency management
- Lombok library to reduce boilerplate code

#### How to Setup and Run
1. **Pre-requisites**:
   - JDK 17
   - Git (optional, for cloning the repository)

2. **Clone the Repository (if using Git)**:
   ```bash
   git clone https://github.com/your-username/SpringBootSecurityFrameworkDemo.git
   ```

3. **Build the Project**:
   ```bash
   ./gradlew clean build
   ```

4. **Run the Application**:
   ```bash
   ./gradlew bootRun
   ```
   This command starts the Spring Boot application on `localhost:8080` which related to the main application called 'restfuldemo'.
   Another application with similar features but adjusted MovieController is set to run on `localhost:8081`

#### Video Link
- **link**: `https://youtu.be/Zx3_7ifCZ-M`

#### Documentation
The Javadoc documentation for this application is automatically generated during the build process using Gradle. To view the documentation:
- Navigate to the `build/docs/javadoc` directory in the project folder.
- Open the `index.html` file in a web browser.


