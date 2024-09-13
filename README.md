## Kanban Hiring Portal Project

This project implements a Kanban Hiring Portal where users can enter and manage candidate data, displayed in Kanban card format. 
The frontend is built using **Angular (TypeScript)**, and the backend uses **Java (Spring Boot + Hibernate JPA)** with a **MYSQL** database.

## Features

# 1: Candidate Data Entry & Display
- Users can enter data for candidates and view it in a Kanban card format.
- **Mandatory Data**: Candidate Name and Unique ID will be visible on the card.
- **Additional Data**: Age, Total Experience, Skills, Location, and other details are visible when the card is opened.
- **Customizable Tags**: Position, Referral, Projects, etc., can be added to the card.
- **Comment Section**: Users can add comments and feedback once the card is opened.
- **Attachments**: Evaluation forms, photos, and CVs can be attached to the card.
- **Due Date**: Visible on the card.
- **Card Movement**: Cards can be moved between different statuses on the Kanban board.

# 2: Central Database (MYSQL)
- All candidate data and status transitions are stored in a centralized **MYSQL** database.

# 3: Candidate Search
- Users can search candidates by Name or Unique ID using a search bar at the top.

# 4: Data Export to CSV
- Users can export candidate data in CSV format, including filtered results.
- Reports can include periodical data, e.g., monthly or quarterly reports.

# 5: Statuses (Customizable)
- Default statuses include:
  - Backlog
  - Initial Screening
  - On Hold
  - In Progress (Interview Rounds)
  - Manager Discussion
  - Selected
  - Rejected

# 6: User Authentication
- Users can log in using DAS ID or Username and Password. 

# 7: Card Movement Tracking
- Tracks the movement of cards between different statuses in the database.

## Prerequisites
Before you begin, make sure you have the following installed:
- **Node.js** and **Angular CLI**
- **Java (JDK 8+)**, **Maven**
- **MYSQL**
- **Git**

## Installation

## Step 1: Clone the Repository
To get a copy of the project, run the following command:
```bash
git clone <your-github-repo-url>

cd <frontend-directory>
cd <backend-directory>

## Step 2: Setting up the Backend (Java + Hibernate + REST API)
Configure MYSQL Database:
Install MYSQL and create a new database:

CREATE DATABASE kanban_db;

Update the application.properties file with your MYSQL credentials:
spring.datasource.url=jdbc:postgresql://localhost:5432/kanban_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

Build and Run the Backend:
Navigate to the backend directory and build the project:

mvn clean install
mvn spring-boot:run

The backend should be running at http://localhost:8080.

## Step 3: Setting up the Frontend (Angular TypeScript)
Install Angular CLI:
Ensure Node.js is installed, and then install Angular CLI globally:

npm install -g @angular/cli

Install Dependencies:
Navigate to the frontend directory and run:

npm install

Run the Frontend:

Start the development server:

ng serve
Access the frontend at http://localhost:4200.

## Step 4: Application Functionality
Kanban Hiring Portal Features:
You can create, view, and move Kanban cards with details like Name, Candidate ID, and tags.
Attachments (e.g., CV, photos) can be added to the card.

Database:
All data is stored in MYSQL, including status transitions and card movement.

Search and Filter:
Search candidates by Name or ID.
Export data in CSV format.

Login System:
Users can log in with DAS ID or Username and Password.

Step 5: Testing
Use Postman to test API endpoints.
Open the browser console for frontend debugging.
