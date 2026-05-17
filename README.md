# Smart Student Placement Portal

Full-stack placement management system with role-based workflows for **Students**, **Recruiters**, and **Admins**.

## Tech Stack

- **Frontend:** React, JavaScript, HTML5, CSS3, Axios, React Router, Recharts
- **Backend:** Java 17, Spring Boot 3, Spring Security, JWT, Spring Data JPA, Hibernate, Maven
- **Database:** MySQL
- **API Docs:** Swagger (springdoc)

## Features

### Authentication & Authorization
- JWT login
- Student signup / Recruiter signup / Admin login
- BCrypt password hashing
- Role-based API protection

### Student
- Update student profile (branch, CGPA, graduation year, skills)
- Upload resume (PDF)
- Browse and search jobs/internships
- Apply to jobs
- Track application status
- Student dashboard metrics

### Recruiter
- Manage recruiter profile
- Post / update / close jobs
- View applicants per job
- Shortlist / reject / select candidates
- Resume download links

### Admin
- View all students, recruiters, jobs, applications
- Close jobs
- Platform analytics dashboard (counts + top skills)

### Advanced
- Skill trend analytics chart
- Basic skill-based matching foundation via required skills in jobs
- Dark/Light mode
- Swagger documentation

---

## Project Structure

```text
placement_portal/
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/placementportal/
│       ├── config/
│       ├── controller/
│       ├── dto/
│       ├── entity/
│       ├── exception/
│       ├── repository/
│       ├── security/
│       └── service/
├── frontend/
│   ├── package.json
│   └── src/
│       ├── api/
│       ├── components/
│       ├── context/
│       ├── pages/
│       └── styles/
└── database/
    └── schema.sql
```

---

## Setup Instructions

### Prerequisites

- Java 17
- Maven 3.9+
- Node.js 18+ with npm
- MySQL 8+

### 1) Clone the repository

```bash
git clone https://github.com/swain2003/placement_portal.git
cd placement_portal
```

### 2) MySQL

Create the database and schema (optional but recommended for a clean start):

```bash
mysql -u root -p < database/schema.sql
```

If you prefer, you can skip the schema import and let Spring Boot create tables automatically on first run.

### 3) Backend

Configure environment variables (defaults are shown):

```bash
export DB_HOST=localhost
export DB_PORT=3306
export DB_NAME=placement_portal
export DB_USER=root
export DB_PASSWORD=root
export JWT_SECRET=change-this-secret-key-which-needs-at-least-32-bytes
```

Run the backend:

```bash
cd backend
mvn spring-boot:run
```

Backend URL: `http://localhost:8080`  
Swagger: `http://localhost:8080/swagger-ui.html`

### 4) Frontend

Optionally set the API base URL (defaults to `http://localhost:8080/api`):

```bash
cat <<EOF > frontend/.env
VITE_API_URL=http://localhost:8080/api
EOF
```

Install dependencies and run the frontend:

```bash
cd frontend
npm install
npm run dev
```

Frontend URL: `http://localhost:5173`

### 5) (Optional) Build & Test

```bash
cd backend
mvn test
cd ../frontend
npm run build
```

---

## Sample Accounts

- **Admin**: `admin@portal.com` / `Admin@123`
- **Recruiter**: `recruiter@company.com` / `Recruiter@123`
- **Student**: `student@college.com` / `Student@123`

(Seeded via backend `DataInitializer`.)

---

## Key API Endpoints

- `POST /api/auth/login`
- `POST /api/auth/register/student`
- `POST /api/auth/register/recruiter`
- `GET /api/students/profile`
- `POST /api/students/resume`
- `POST /api/students/jobs/{jobId}/apply`
- `POST /api/recruiters/jobs`
- `GET /api/recruiters/jobs/{jobId}/applicants`
- `PATCH /api/recruiters/applications/{applicationId}?status=SHORTLISTED`
- `GET /api/dashboard/admin`

---

## Notes

- Resume files are stored locally under `/uploads` and exposed through `/uploads/**`.
- Pagination/sorting available for `GET /api/jobs` with `page`, `size`, `sortBy`, `search`.
