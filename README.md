# CPAN228 — Group Assignment
**Course:** CPAN-228 Web Application Development
 
## Team — Code Crew
| Name | GitHub |
|---|---|
| Ilia Mchedlishvili | IliaMched |
| Raj Patel | RajPatel514 |
| Ramanpreet Grover | RamanpreetGrover |
| Tyler Lee | Tidalfeer |
 
---

## Project Overview
CodeCrew is a web-based technician booking platform built with Spring Boot and Thymeleaf. Users can browse services, schedule technicians, and manage bookings. Administrators have a dedicated panel to manage users and bookings.

---

### Steps
Terminal:
git clone <repo-url>
cd <project-folder>
mvn spring-boot:run
```
 
The app starts at `http://localhost:8080`

---

### Components Added
- JPA Entity: `Booking.java`
- Spring Data Repository: `BookingRepository`
- H2 in-memory database configuration in `application.properties`
- Automatic timestamp (`createdAt`) using `@PrePersist`
- Sample booking records using `data.sql`
- Repository query method `findByServiceType()`
