# Student Management System

Spring Boot 4 / Java 21 / PostgreSQL / Thymeleaf / Spring Security / JPA.

## Features
- Student CRUD, search, sorting and pagination
- Secure BCrypt registration/login/logout
- USER/ADMIN role based access control
- Contract CRUD with Draft, Active, Expired and Terminated statuses
- Contract search, status filter and pagination
- Contract document upload/download
- Modification requests with admin approval/rejection
- Contract version history
- Audit/activity logs for login, logout, registration, create, update, delete and password changes
- Admin activity-log search and filtered CSV export
- Validation and REST exception handling

## Run
1. Create PostgreSQL database `student_db`.
2. Update `src/main/resources/application.properties` if your PostgreSQL username/password differs.
3. Use Java 21.
4. Run `./mvnw spring-boot:run` or run `StudentManagementwithloginlogoutApplication` from Eclipse.

Default admin: `admin@example.com` / `Admin@123`.

## Main pages
- `/login`
- `/dashboard`
- `/students`
- `/contracts`
- `/activity-logs` (ADMIN)

## Contract API examples
- `POST /api/contracts`
- `GET /api/contracts?q=nda&status=ACTIVE&page=0&size=10`
- `PUT /api/contracts/{id}`
- `DELETE /api/contracts/{id}`
- `GET /api/contracts/{id}/versions`
- `POST /api/contracts/{id}/documents` with multipart field `file`
- `GET /api/contracts/{id}/documents`
- `POST /api/contracts/{id}/modifications` with JSON contract fields as body
- `POST /api/contracts/modifications/{id}/approve` (ADMIN)
- `POST /api/contracts/modifications/{id}/reject` (ADMIN)
- `GET /api/audit?q=LOGIN` (ADMIN)
- `GET /api/audit/csv?q=LOGIN` (ADMIN)
