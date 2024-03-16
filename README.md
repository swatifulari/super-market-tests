# super-market-app

Integration Tests written for super-market-app APIs using Rest Assured.

## Execution Steps:
- Before starting with execution, boot the Spring Boot App - [super-market-app](https://github.com/swatifulari/super-market-app)
- Run the following command:



## Execution Output
Maven console output, post-execution, is uploaded in the same project at the path - "/super-market-tests/src/main/resources/consoleOutput/TestExecutionMavenOutput.txt"

## Future Enhancements:
- Reading JSON string from a file.
- Update Assertions. For example, after creating an order, check if business requirements are handled correctly.
- If a database is used, perform DB queries for data validations.
- For createOrder, first get the Products List and use the same to create the request payload for the createOrder API.
- As the integration tests are separated from the application code, it is difficult to maintain tests separately. Instead, the same can be included in the app code, as a separate Maven project example - "integration_tests" under src/test. When integrating with Jenkins, Jenkins code can be written such that, post-application deployment, code must check if there is a test folder - "integration_tests" under "src/test". If present, run "mvn test" command on the same.
- Test categorization like below can be included:
  - For API, we can include Request Validation (tests which will test how invalid user input is handled by app).
  - Functional Validation - Includes testing business flows for both success and error (backend) scenarios.
  - Authentication and Authorization - If any policy is applied then test success/error scenarios around them.
