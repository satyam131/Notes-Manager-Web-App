# Notes-Manager-Web-App
NoteKeeper is a simple web application for saving and managing notes for users.

## Features

- User authentication: Users can register, login, and securely manage their notes.
- CRUD Operations: Users can create, read, update, and delete their notes.
- Automatic Cleanup: The system automatically deletes older notes on an hourly basis, keeping only the most recent 10 notes per user.
- Validation: Notes are validated to ensure they meet specific criteria (special characters, length).
- Responsive UI: The frontend is designed using Angular for a user-friendly experience.

## Technologies Used

- Frontend: Angular
- Backend: Spring Boot
- Database: MySQL
- Other: Java 8, Spring Boot, JPA

## Getting Started

To run NoteKeeper locally, follow these steps:

### Prerequisites

- Java 8 installed
- Node.js and Angular CLI installed
- MySQL installed

### Installation

1. Clone the repository : git clone https://github.com/yourusername/NoteKeeper.git 

2. Navigate to the backend directory : cd NoteKeeper/backend

3. Configure the database settings in application.properties:
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_database_username
    spring.datasource.password=your_database_password

4. Run the Spring Boot application:
   ./mvnw spring-boot:run

5. Navigate to the frontend directory:
   cd ../frontend

6. Install dependencies:
   npm install

7. Start the Angular development server:
   ng serve

8. Access the application at http://localhost:4200 in your web browser.


### Usage
1. Register or login to your account.
2. Add, view, edit, or delete your notes.
3. The system will automatically clean up older notes, retaining only the latest 10 per user.


