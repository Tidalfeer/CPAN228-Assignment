# CodeCrew — Technician Booking Platform
**Course:** # CPAN228 Web Application Dev.- Group Assignment
 
## Team Members
| Name | GitHub |
|---|---|
| Ilia Mchedlishvili | IliaMched |
| Raj Patel | RajPatel514 |
| Ramanpreet Grover | RamanpreetGrover |
| Tyler Lee | Tidalfeer |
---

## Project Overview

CodeCrew is a web-based technician booking platform where users can browse available services, schedule appointments with technicians, and manage their bookings. Administrators have a dedicated panel to manage users, bookings, and view a full audit log of all admin actions.

---

## Technologies Implemented

- **Java 17** + **Spring Boot**
- **Spring MVC**
- **Thymeleaf**
- **Spring Security**
- **Spring Data JPA**
- **H2 DB** 
- **MySQL 8**
- **Docker**

---
## Account Login Info
| Email | Password | Role |
|---|---|---|
| admin@domain.com | Admin@123 | Admin |
| staff@domain.com | Staff@123 | Staff |
| user@domain.com | User@123 | User |

---
## How to Run Locally
**Prerequisites:** Java 17, Maven

```bash
git clone https://github.com/Tidalfeer/CPAN228-Assignment.git
cd CPAN228-Assignment
./mvnw spring-boot:run
```

Application runs on browser: `http://localhost:8080`

## How to Run Using Docker
**Prerequisites:** Docker Desktop
```bash
git clone https://github.com/Tidalfeer/CPAN228-Assignment.git
cd CPAN228-Assignment
docker-compose up --build
```

Application runs on browser: `http://localhost:8080`

---

## Team Contributions

| Name | Contributions |
|---|---|
| Ilia Mchedlishvili | In charge of the visual themes, styling, user experience and UI, website clarity. Dynamic scalings for mobile and debugging  |
| Raj Patel | Handled documentation, testing/debugging/QA, I contributed to the login and registration system, Spring Security configuration, and user/admin access control. Built the booking feature, including form handling, and Thymeleaf pages, fixing configuration issues.|
| Ramanpreet Grover | Handled data persistence (JPA, entities, repositories) and backend integration. Resolved merge conflicts and duplicate controller/service issues. Set up Docker (app + MySQL), configured dev/QA profiles, and tested full application including login/register.|
| Tyler Lee | Handled error checking, assisted in building web pages and services |
