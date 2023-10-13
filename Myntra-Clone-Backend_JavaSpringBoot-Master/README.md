# Java-Spring Boot Backend Project - Myntra Clone

## Project Description

This GitHub repository **Java-Spring Boot Backend Project - Myntra Clone**,is a Java Spring Boot application with MySQL database integration. This ongoing project aims to recreate the backend functionalities of the popular online fashion and lifestyle platform, Myntra. This project currently feature **User Registration, login, logout, JWT authentication, OTP validation** etc. You can find the **REST API Swagger Documentaion** after cloning and running the project in local machine.
### Technologies Used

- Java (SE v17)
- Spring Boot (v2.7.14)
- Maven 
- MySQL

### Current Project Features

1. **User Management:**
   - **User Registration:** Users can create an account by providing necessary details.
   - **User Login:** Registered users can log in to their accounts using their credentials.
   - **User Logout:** Users can securely log out of their accounts.
   - **Delete Account:** Users have the option to delete their accounts, ensuring data privacy.

2. **Profile Management:**
   - **Address Management:** Users can add or modify their addresses, making the shopping experience seamless.
   - **Change Password:** Users can change their passwords for enhanced security.

3. **Authentication:**
   - **JWT & Refresh Token:** Authentication is implemented using JSON Web Tokens (JWT) and refresh tokens. This ensures secure and efficient user sessions.
   - **Email OTP Varification:** User email can be verified using OTP sent to user email, which is valid for a certain time.

### How to Clone the Project

Follow these steps to clone and set up the Myntra Backend Clone Project on your local machine:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Myntra-Clone/Myntra-Clone-Backend_JavaSpringBoot.git
   ```
2. **Configure Database:**
   - Update the `application.properties` file with your database configuration.
  
3. **Add Lombok feature in your IDE:**
   
    - Install Lombok following instructions from - https://projectlombok.org

4. **Build and Run the Project:**
   - Use a Java IDE to build and run the project.
   - The application will be accessible at `http://localhost:8500`.
  
 5. **Swagger Documentation:**
   - After running the project you can access _Swagger Api Documentation_ on:
     
    
    http://localhost:8500/myntra/swagger-ui/index.html
    
