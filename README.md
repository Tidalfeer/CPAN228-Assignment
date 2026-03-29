# CPAN228-Assignment
Group Assignment for CPAN228

#CODE CREW
Ilia Mchedlishvili
Raj Patel
Ramanpreet Grover
Tyler Lee


This is a group assignment for Class CPAN-228  Web Application Development. 

## Data Persistence Layer – Ramanpreet Grover

Implemented the database persistence layer for the Booking system.

### Components Added
- JPA Entity: `Booking.java`
- Spring Data Repository: `BookingRepository`
- H2 in-memory database configuration in `application.properties`
- Automatic timestamp (`createdAt`) using `@PrePersist`
- Sample booking records using `data.sql`
- Repository query method `findByServiceType()`

### Testing
The persistence layer was verified using the H2 console.

Example query used:

SELECT * FROM booking;