# Multi-Step Registration → Payment → Login System

A full-stack Java Spring Boot + React application where users must register, then pay, and only then are allowed to login. The app is structured as a multi-step flow with frontend validation and backend protection for each stage.

---

## Features

- Step 1: Registration
- Step 2: Payment
- Step 3: Login
- Users are blocked from skipping steps
- Validation implemented on both frontend and backend
- CORS configured for secure API access
- Responsive UI with Bootstrap

---

## Tech Stack

| Layer      | Tech Used                            |
|------------|----------------------------------------|
| Frontend   | React.js, Bootstrap 5                  |
| Backend    | Spring Boot, Java, Spring Web, JPA     |
| Database   | MySQL or H2 (optional for testing)     |
| Tools      | Postman, IntelliJ/VSCode, npm          |

---

## Getting Started

### Backend Setup

1. Clone the repository and navigate to backend folder:

   ```bash
   git clone https://github.com/your-username/multi-step-auth-app.git
   cd backend

multi-step-auth-app/
├── frontend/
│   ├── App.js
│   ├── Register.js
│   ├── Payment.js
│   ├── Login.js
│   └── App.css
└── backend/
    ├── controllers/
    ├── services/
    ├── entities/
    ├── dtos/
    └── AuthenticationApplication.java
