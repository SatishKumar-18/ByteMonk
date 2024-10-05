# Security Incident Report API

## Objective
Create a simple RESTful API using Java and Spring Boot to manage security incident reports
within an organization.

---

## Requirements:

### API Endpoints:
1. Incidents:
   a. Create a new incident report (fields: title, description, severity level, incident
   date).
   b. Update an existing incident report (e.g., to change the status or add notes).
   c. List all incidents, with optional filtering by severity level and date range.
   d. Retrieve a specific incident report by its ID.

2. Database:
   a. Use an in-memory database (e.g., H2) for storage.
   b. Design an entity model for Incident and set up a JPA repository.

3. Business Logic:
   a. Ensure that incidents cannot be created with a past date greater than 30 days or
   a future date.
   b. Implement logic to validate severity levels (e.g., “Low”, “Medium”, “High”).
   c. Ensure that the title of the incident is unique and descriptive (minimum length of
   10 characters).

4. Security Considerations:
   a. Use basic authentication to secure the API (e.g., a hardcoded username and
   password for simplicity).
   b. Log all API requests to a file, including timestamp, IP address, endpoint
   accessed, and HTTP method.

5. Error Handling:
   a. Implement basic error handling for invalid input (e.g., invalid date or severity
   level) and non-existent records.
   b. Return appropriate HTTP status codes.

6. Testing:
   a. Write unit tests for at least two service methods using JUnit.
   b. Include at least one test that validates security restrictions (e.g., unauthenticated
   access).