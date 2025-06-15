# Habit Tracker

A simple Java Spring Boot application for tracking habits. This project provides RESTful APIs to create, retrieve, and manage habits, and includes a basic frontend for user interaction.

## Features
- Add new habits
- List all habits
- Simple web frontend (HTML/JS/CSS)
- REST API endpoints

## Tech Stack
- Java 17+
- Spring Boot
- Maven
- H2 Database (or configure your own in `application.properties`)
- HTML, CSS, JavaScript (frontend)

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven

### Build & Run

1. **Clone the repository:**
   ```sh
   git clone https://github.com/ar-jun2001/habit-tracker.git
   cd habit-tracker
   ```
2. **Build the project:**
   ```sh
   mvn clean install
   ```
3. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```
   The app will start on [http://localhost:8080](http://localhost:8080).

### API Endpoints
- `POST /save` - Save a new habit
- `GET /getAllHabits` - Retrieve all habits

### Frontend
This project uses [Thymeleaf](https://www.thymeleaf.org/) as the template engine for the frontend. All HTML templates are located in `src/main/resources/templates/`.

To view the frontend, start the application and open [http://localhost:8080](http://localhost:8080) in your browser.

## Project Structure
- `controller/` - REST controllers
- `entity/` - JPA entities
- `repository/` - Spring Data repositories
- `service/` - Business logic
- `templates/` - Thymeleaf HTML templates
- `static/` - Static assets (CSS, JS)

## Configuration
Edit `src/main/resources/application.properties` to change DB settings or server port.

## License
MIT License
