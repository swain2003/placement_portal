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

## 1) MySQL

Create DB and schema:

```bash
mysql -u root -p < /home/runner/work/placement_portal/placement_portal/database/schema.sql
```

## 2) Backend

```bash
cd /home/runner/work/placement_portal/placement_portal/backend
mvn spring-boot:run
```

Optional env vars:
- `DB_HOST` (default `localhost`)
- `DB_PORT` (default `3306`)
- `DB_NAME` (default `placement_portal`)
- `DB_USER` (default `root`)
- `DB_PASSWORD` (default `root`)
- `JWT_SECRET` (must be 32+ chars)

Backend URL: `http://localhost:8080`
Swagger: `http://localhost:8080/swagger-ui.html`

## 3) Frontend

```bash
cd /home/runner/work/placement_portal/placement_portal/frontend
npm install
npm run dev
```

Frontend URL: `http://localhost:5173`

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
