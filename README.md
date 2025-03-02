# Mortgage Check

This project is a Spring Boot application that provides an API for checking the feasibility of mortgage requests and retrieving current interest rates.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Exception Handling](#exception-handling)
- [Testing](#testing)
- [Docker](#docker)
- [License](#license)

## Overview

The Mortgage Check application allows users to check the feasibility of mortgage requests based on their income, maturity period, loan value, and home value. It also provides an endpoint to retrieve the current interest rates.

## Features

- Retrieve current interest rates
- Check the feasibility of mortgage requests
- Exception handling for various error scenarios

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/mortgage-check.git
    cd mortgage-check
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage

Once the application is running, you can access the API endpoints using a tool like `curl` or Postman.

## API Endpoints

### Retrieve Current Interest Rates

- **URL:** `/api/interest-rates`
- **Method:** `GET`
- **Response:**
    ```json
    [
        {
            "maturityPeriod": 10,
            "interestRate": 3.0,
            "lastUpdate": "2025-03-02T12:34:56.789Z"
        },
        {
            "maturityPeriod": 15,
            "interestRate": 3.5,
            "lastUpdate": "2025-03-02T12:34:56.789Z"
        }
    ]
    ```

### Check Mortgage Feasibility

- **URL:** `/api/mortgage-check`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "income": 10000,
        "maturityPeriod": 10,
        "loanValue": 12000,
        "homeValue": 20000
    }
    ```
- **Response:**
    ```json
    {
        "feasible": true,
        "monthlyCost": 115.87
    }
    ```

## Exception Handling

The application includes a global exception handler to handle various exceptions:

- **Generic Exception:** Returns a 500 Internal Server Error with the exception message.
- **Maturity Period Not Found Exception:** Returns a 404 Not Found with the exception message.
- **HTTP Message Not Readable Exception:** Returns a 400 Bad Request with a JSON parse error message.

## Testing

The project includes unit tests for the controllers and exception handlers. To run the tests, use the following command:

```sh
mvn test
```

## Docker

To run the application using Docker, follow these steps:

1. Build the Docker image:
    ```sh
    docker build -t mortgage-check .
    ```

2. Run the Docker container:
    ```sh
    docker run -p 8080:8080 mortgage-check
    ```

## License

This project is licensed under the MIT License.