<div align="center">

# 🎓 Smart Student Placement Portal

### Full-Stack Placement Management System for Students, Recruiters & Admins

<br>

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Backend-green?style=for-the-badge&logo=springboot)
![Spring REST](https://img.shields.io/badge/SpringREST-API-success?style=for-the-badge)
![Spring Security](https://img.shields.io/badge/SpringSecurity-JWT-darkgreen?style=for-the-badge&logo=springsecurity)
![Spring Data JPA](https://img.shields.io/badge/SpringDataJPA-Hibernate-brightgreen?style=for-the-badge)

![Hibernate](https://img.shields.io/badge/Hibernate-ORM-brown?style=for-the-badge&logo=hibernate)
![React](https://img.shields.io/badge/React-Frontend-blue?style=for-the-badge&logo=react)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6-yellow?style=for-the-badge&logo=javascript)
![HTML5](https://img.shields.io/badge/HTML5-Markup-orange?style=for-the-badge&logo=html5)
![CSS3](https://img.shields.io/badge/CSS3-Styling-blue?style=for-the-badge&logo=css3)

![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql)
![JWT](https://img.shields.io/badge/JWT-Authentication-black?style=for-the-badge&logo=jsonwebtokens)
![Maven](https://img.shields.io/badge/Maven-BuildTool-red?style=for-the-badge&logo=apachemaven)
![REST API](https://img.shields.io/badge/REST-API-purple?style=for-the-badge)

</div>

---

# 📌 Overview

**Smart Student Placement Portal** is a full-stack web application designed to streamline and modernize campus recruitment workflows for **Students**, **Recruiters**, and **Administrators**.

The platform enables:
- Secure authentication and authorization
- Student profile and resume management
- Recruitment workflow automation
- Job and internship management
- Application tracking
- Placement analytics dashboard
- Skill-based hiring workflows

Built using modern enterprise-grade technologies including **React**, **Spring Boot**, **Java**, and **MySQL**.

---

# 🚀 Key Features

---

## 🔐 Authentication & Authorization

- JWT-based secure authentication
- Student Registration
- Recruiter Registration
- Admin Login
- BCrypt password hashing
- Role-Based Access Control (RBAC)
- Protected APIs with Spring Security

---

## 👨‍🎓 Student Module

- Update student profile
- Add skills, CGPA, branch & graduation year
- Upload resume (PDF)
- Browse jobs and internships
- Apply for opportunities
- Track application status
- Student dashboard metrics

---

## 🏢 Recruiter Module

- Manage recruiter/company profile
- Post jobs and internships
- Update or close job listings
- View applicants for each job
- Shortlist / reject / select candidates
- Resume download support

---

## 🛠️ Admin Module

- Manage students and recruiters
- View all jobs and applications
- Monitor platform activity
- Close jobs
- View analytics dashboard
- Skill trend analysis

---

## 📊 Advanced Features

- Placement analytics dashboard
- Skill trend charts using Recharts
- Skill-based matching foundation
- Swagger API documentation
- Dark/Light mode support
- Responsive UI design

---

# 🧰 Tech Stack

| Category | Technologies |
|---|---|
| Frontend | React.js, JavaScript, HTML5, CSS3, Axios, React Router, Recharts |
| Backend | Java 17, Spring Boot 3, Spring Security, Spring REST |
| Database | MySQL |
| ORM | Hibernate, Spring Data JPA |
| Authentication | JWT Authentication, BCrypt |
| Build Tools | Maven, npm |
| API Documentation | Swagger (springdoc-openapi) |

---

# 📂 Project Structure

```text
placement_portal/
├── backend/
│   ├── pom.xml
│   ├── mvnw
│   ├── mvnw.cmd
│   └── src/main/java/com/placementportal/
│       ├── config/
│       ├── controller/
│       ├── dto/
│       ├── entity/
│       ├── exception/
│       ├── repository/
│       ├── security/
│       └── service/
│
├── frontend/
│   ├── package.json
│   └── src/
│       ├── api/
│       ├── components/
│       ├── context/
│       ├── pages/
│       └── styles/
│
└── database/
    └── schema.sql
```

---

# ⚙️ Installation & Setup Guide

## 📌 Prerequisites

Make sure the following are installed:

- Java 17+
- Node.js 18+
- npm
- MySQL 8+
- Git

---

# 1️⃣ Clone Repository

```bash
git clone https://github.com/swain2003/placement_portal.git

cd placement_portal
```

---

# 2️⃣ Configure MySQL Database

## Open MySQL

```bash
mysql -u root -p
```

Create database:

```sql
CREATE DATABASE placement_portal;
```

Exit MySQL:

```sql
exit;
```

(Optional) Import schema:

```bash
mysql -u root -p placement_portal < database/schema.sql
```

---

# 3️⃣ Backend Setup

Navigate to backend folder:

```bash
cd backend
```

---

## Configure Database Credentials

Open:

```text
backend/src/main/resources/application.yml
```

Update the database credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/placement_portal
    username: root
    password: YOUR_PASSWORD
```

---

## Configure JWT Secret

Inside the same file:

```yaml
jwt:
  secret: mysupersecurejwtsecretkeyforplacementportal2026
```

⚠️ JWT secret must be at least 32 bytes long.

---

## Run Backend Server

### Windows

```bash
.\mvnw spring-boot:run
```

### Linux / Mac

```bash
./mvnw spring-boot:run
```

---

## Backend URLs

| Service | URL |
|---|---|
| Backend API | http://localhost:8081 |
| Swagger Documentation | http://localhost:8081/swagger-ui/index.html |

---

# 4️⃣ Frontend Setup

Open a NEW terminal.

Navigate to frontend:

```bash
cd frontend
```

---

## Install Dependencies

```bash
npm install
```

---

## Configure Frontend Environment

Create a `.env` file inside `frontend`:

```env
VITE_API_URL=http://localhost:8081/api
```

---

## Start Frontend

```bash
npm run dev
```

---

## Frontend URL

```text
http://localhost:5173
```

---

# 🧪 Sample Accounts

| Role | Email | Password |
|---|---|---|
| Admin | admin@portal.com | Admin@123 |
| Recruiter | recruiter@company.com | Recruiter@123 |
| Student | student@college.com | Student@123 |

---

# 🔗 Important API Endpoints

| Method | Endpoint |
|---|---|
| POST | `/api/auth/login` |
| POST | `/api/auth/register/student` |
| POST | `/api/auth/register/recruiter` |
| GET | `/api/students/profile` |
| POST | `/api/students/resume` |
| POST | `/api/students/jobs/{jobId}/apply` |
| POST | `/api/recruiters/jobs` |
| GET | `/api/recruiters/jobs/{jobId}/applicants` |
| PATCH | `/api/recruiters/applications/{applicationId}` |
| GET | `/api/dashboard/admin` |

---

# 📈 Highlights

✅ Full-Stack Enterprise Architecture  
✅ RESTful API Design  
✅ JWT Authentication & Authorization  
✅ Role-Based Access Control  
✅ Responsive Frontend UI  
✅ Secure Password Hashing  
✅ Database Integration with MySQL  
✅ Swagger API Documentation  
✅ Production-Oriented Project Structure  
✅ Resume Upload & Recruitment Workflow Automation  

---

# 👨‍💻 Author

## Anubhaba Swain
### B.Tech in Information Technology || KIIT UNIVERSITY

🔗 LinkedIn: https://www.linkedin.com/in/anubhaba-swain-695a7b176

---

# ⭐ Support

If you found this project useful, consider giving the repository a ⭐ on GitHub.

---
