# Security Incident Report API

## Objective
Create a simple RESTful API using Java and Spring Boot to manage security incident reports
within an organization.

---

## Requirements:

### API Endpoints:
1. **Incidents**:
   - Create a new incident report (fields: title, description, severity level, incident
   date).
   - Update an existing incident report (e.g., to change the status or add notes).
   - List all incidents, with optional filtering by severity level and date range.
   - Retrieve a specific incident report by its ID.

2. **Database**:
   - Use an in-memory database (e.g., H2) for storage.
   - Design an entity model for Incident and set up a JPA repository.

3. **Business Logic**:
   - Ensure that incidents cannot be created with a past date greater than 30 days or
   a future date.
   - Implement logic to validate severity levels (e.g., “Low”, “Medium”, “High”).
   - Ensure that the title of the incident is unique and descriptive (minimum length of
   10 characters).

4. **Security Considerations**:
   - Use basic authentication to secure the API (e.g., a hardcoded username and
   password for simplicity).
   - Log all API requests to a file, including timestamp, IP address, endpoint
   accessed, and HTTP method.

5. **Error Handling**:
   - Implement basic error handling for invalid input (e.g., invalid date or severity
   level) and non-existent records.
   - Return appropriate HTTP status codes.

6. **Testing**:
   - Write unit tests for at least two service methods using JUnit.
   - Include at least one test that validates security restrictions (e.g., unauthenticated
   access).