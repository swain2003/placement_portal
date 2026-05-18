<div align="center">

# 🎓 Smart Student Placement Portal

### Full-Stack Placement Management System for Students, Recruiters & Admins

<br>

🚀 **Live Demo**  
https://placement-portal-teal-two.vercel.app/

📂 **GitHub Repository**  
https://github.com/swain2003/placement_portal

<br>

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Backend-green?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/SpringSecurity-JWT-darkgreen?style=for-the-badge&logo=springsecurity)
![Spring REST](https://img.shields.io/badge/SpringREST-API-success?style=for-the-badge)
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

Smart Student Placement Portal is a full-stack web application developed to simplify and modernize campus recruitment workflows for students, recruiters, and administrators.

The platform enables students to create profiles, upload resumes, apply for jobs and internships, and track applications. Recruiters can manage hiring workflows, post opportunities, and review applicants, while administrators can monitor the entire placement process through centralized dashboards and analytics.

Built using modern enterprise-grade technologies including React, Spring Boot, Java, and MySQL.

---

# 🌟 Features

## 🔐 Authentication & Security

- JWT-based secure authentication
- Role-Based Access Control (RBAC)
- Secure login and registration system
- BCrypt password hashing
- Protected APIs using Spring Security

---

## 👨‍🎓 Student Module

- Student profile management
- Resume upload functionality
- Apply for jobs and internships
- Track application status
- Skill and academic details management
- Personalized student dashboard

---

## 🏢 Recruiter Module

- Recruiter/company profile management
- Post and manage job opportunities
- View applicants
- Shortlist or reject candidates
- Resume download support

---

## 🛠️ Admin Module

- Manage students and recruiters
- Monitor platform activity
- Manage job postings
- Placement analytics dashboard
- Skill trend analysis

---

## 📊 Additional Features

- Responsive UI design
- RESTful API architecture
- Swagger API documentation
- Enterprise layered backend architecture
- Dark/Light mode support
- Secure database integration

---

# 🧰 Tech Stack

| Category | Technologies |
|---|---|
| Frontend | React.js, JavaScript, HTML5, CSS3, Axios |
| Backend | Java 17, Spring Boot 3, Spring Security |
| Database | MySQL |
| ORM | Hibernate, Spring Data JPA |
| Authentication | JWT Authentication, BCrypt |
| Build Tools | Maven, npm |
| API Documentation | Swagger OpenAPI |
| Deployment | Vercel |

---

# 🏗️ System Architecture

```text
Frontend (React.js)
        ↓
REST APIs (Spring Boot)
        ↓
Service Layer
        ↓
Hibernate / JPA
        ↓
MySQL Database
```

---

# 📂 Project Structure

```text
placement_portal/
│
├── backend/
│   ├── .mvn/
│   │   └── wrapper/
│   │
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/placementportal/
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── entity/
│   │   │   │   ├── exception/
│   │   │   │   ├── repository/
│   │   │   │   ├── security/
│   │   │   │   ├── service/
│   │   │   │   └── PlacementPortalApplication.java
│   │   │   │
│   │   │   └── resources/
│   │   │       └── application.yml
│   │   │
│   │   └── test/
│   │
│   ├── Dockerfile
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml
│
├── database/
│   └── schema.sql
│
├── frontend/
│   ├── src/
│   │   ├── api/
│   │   ├── components/
│   │   ├── context/
│   │   ├── pages/
│   │   ├── styles/
│   │   ├── App.jsx
│   │   └── main.jsx
│   │
│   ├── index.html
│   ├── package-lock.json
│   ├── package.json
│   ├── vercel.json
│   └── vite.config.js
│
├── .gitignore
└── README.md
```

---

# ⚙️ Installation & Setup

## 📌 Prerequisites

Ensure the following are installed:

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

# 2️⃣ Configure Database

Open MySQL:

```bash
mysql -u root -p
```

Create database:

```sql
CREATE DATABASE placement_portal;
```

(Optional) Import schema:

```bash
mysql -u root -p placement_portal < database/schema.sql
```

---

# 3️⃣ Backend Setup

Navigate to backend:

```bash
cd backend
```

Update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/placement_portal
    username: root
    password: YOUR_PASSWORD
```

Configure JWT secret:

```yaml
jwt:
  secret: mysupersecurejwtsecretkeyforplacementportal2026
```

Run backend server:

### Windows

```bash
.\mvnw spring-boot:run
```

### Linux / Mac

```bash
./mvnw spring-boot:run
```

---

# 4️⃣ Frontend Setup

Open a new terminal:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start frontend:

```bash
npm run dev
```

---

# 🌐 Application URLs

| Service | URL |
|---|---|
| Frontend | http://localhost:5173 |
| Backend API | http://localhost:8081 |
| Swagger UI | http://localhost:8081/swagger-ui/index.html |
| Live Deployment | https://placement-portal-teal-two.vercel.app/ |

---

# 🔗 Important API Endpoints

| Method | Endpoint |
|---|---|
| POST | `/api/auth/login` |
| POST | `/api/auth/register/student` |
| POST | `/api/auth/register/recruiter` |
| GET | `/api/students/profile` |
| POST | `/api/students/resume` |
| POST | `/api/recruiters/jobs` |
| GET | `/api/recruiters/jobs/{jobId}/applicants` |
| PATCH | `/api/recruiters/applications/{applicationId}` |
| GET | `/api/dashboard/admin` |

---

# 📸 Project Screenshots

| Login Page | Dashboard |
|---|---|
| Add Screenshot Here | Add Screenshot Here |

| Recruiter Panel | Analytics Dashboard |
|---|---|
| Add Screenshot Here | Add Screenshot Here |

---

# ☁️ Deployment

| Service | Platform |
|---|---|
| Frontend | Vercel |
| Backend | Spring Boot |
| Database | MySQL |

---

# 💡 Resume Highlights

✅ Full-Stack Enterprise Architecture  
✅ JWT Authentication & Authorization  
✅ Role-Based Access Control  
✅ RESTful API Design  
✅ Responsive Frontend UI  
✅ Resume Upload Workflow  
✅ Placement Workflow Automation  
✅ MySQL Database Integration  
✅ Swagger API Documentation  
✅ Secure Password Hashing  
✅ Production-Oriented Project Structure  

---

# 🚀 Future Enhancements

- AI-based resume screening
- Email notifications
- Interview scheduling
- Real-time recruiter chat
- Placement prediction analytics
- Resume parsing using AI

---

# 👨‍💻 Author

## Anubhaba Swain

### B.Tech in Information Technology || KIIT UNIVERSITY

🔗 LinkedIn  
https://www.linkedin.com/in/anubhaba-swain-695a7b176

💻 GitHub  
https://github.com/swain2003

---

# ⭐ Support

If you found this project useful, consider giving the repository a ⭐ on GitHub.

---