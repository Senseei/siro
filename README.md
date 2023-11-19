# Hospital System API

The Hospital System API is a RESTful API designed to manage patient documentation protocols within a hospital. It provides endpoints to perform CRUD operations on patients, employees, doctors, and the associated protocol registers.

## Table of Contents
- [Entities](#entities)
- [Endpoints](#endpoints)
- [API Operations](#api-operations)
- [Usage](#usage)
- [Example Requests](#example-requests)

## Entities

The system consists of the following entities:

- **Person**
  - Base class for Patients, Employees, and Doctors.

- **Patient**
  - Represents a patient in the hospital system.

- **Employee**
  - Represents an employee in the hospital system.

- **Doctor**
  - Represents a doctor in the hospital system.

- **Protocol Register**
  - Contains information about patient documentation protocols, including the responsible doctor, patient details, documentation number, patient's relative, call attempts, occurrences, and the employee responsible for the register.

- **Documentation**
  - Represents a type of documentation.

- **Occurrence**
  - Represents an occurrence the process may have.

- **Contact Attempt**
  - Represents an attempt to get in touch with the patient's relatives.

## Endpoints

The API follows RESTful principles with the following endpoints:

- **/people**
  - GET: Retrieve a list of all people.
  - POST: Create a new person.
  - PUT: Update an existing person.

- **/patients**
  - GET: Retrieve a list of all patients.
  - POST: Create a new patient.
  - PUT: Update an existing patient.

- **/employees**
  - GET: Retrieve a list of all employees.
  - POST: Create a new employee.
  - PUT: Update an existing employee.

- **/doctors**
  - GET: Retrieve a list of all doctors.
  - POST: Create a new doctor.
  - PUT: Update an existing doctor.

- **/registers**
  - GET: Retrieve a list of all protocol registers.
  - POST: Create a new protocol register.

    - **/{id}**
    - GET: Retrieve details of a specific protocol register.
    - PUT: Update details of a specific protocol register.

    - **/{id}/documentation**
    - GET: Retrieve all the documentation of a particular register.
    - POST: Add a new documentation associated to that particular register.

    - **/{id}/occurrences**
    - GET: Retrieve all the occurrences of a particular register.
    - POST: Add a new occurrence associated to that particular register.

    - **/{id}/contactattempt**
    - GET: Retrieve all the contactattempts of a particular register.

    - **/{id}/contactattempt/success**
    - POST: Add a new success contact attempt associated to that particular register.

    - **/{id}/contactattempt/unsuccess**
    - POST: Add a new unsuccess contact attempt associated to that particular register.


## API Operations

- **GET**
  - Retrieve information or a list of entities.

- **POST**
  - Create a new entity.

- **PUT**
  - Update existing entity details.

## Usage

To use the Hospital System API, you need to make HTTP requests to the provided endpoints.

## Example Requests

Here are examples of how to make requests to the API:

### Create a new entity
```http
POST /patients
{
  "name": "John Doe",
  "birthdate": "1990-01-01",
  "rh": 12345
}

POST /doctors
{
  "name": "Lucas Moroe",
  "birthdate": "1990-01-01",
  "crm": 12345
}

POST /employees
{
  "name": "Gabriel Pedro",
  "birthdate": "1990-01-01",
  "re": 12345
}
```

We can do the same to update some entity, using the PUT method and the entity's ID, passing the parameters you want to update.
```http
PUT /doctors/{id}
{
  "name": "Some name",
}
