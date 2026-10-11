# POS Examination Starter

Starter Spring Boot application for the **Practical Examination - Point of Sale (POS) System**.

## Technology

- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Jakarta Bean Validation
- MySQL JDBC driver
- PostgreSQL JDBC driver
- Springdoc OpenAPI 2.8.x for Spring Boot 3

## Run the starter

```bash
mvn spring-boot:run
```

The starter uses the MySQL profile by default. Update the credentials in
`src/main/resources/application-mysql.properties` before starting.

The database credentials use environment variables. For MySQL, for example:

```bash
export DB_USERNAME=root
export DB_PASSWORD=your-mysql-password
```

To use PostgreSQL instead, change the profile in `application.properties`:

```properties
spring.profiles.active=postgresql
```

Then update the credentials in `src/main/resources/application-postgresql.properties`.

The application expects a database named `pos_db`. The configured JPA setting
`ddl-auto=update` creates and updates tables from the students' entity classes.

Check that the application is running:

```bash
curl http://localhost:8080/api/v1/test
```

The response follows the examination response contract:

```json
{
  "data": {
    "application": "POS Examination Starter",
    "message": "The Spring Boot application is running."
  },
  "status": {
    "code": "200",
    "message": "Test request successful"
  }
}
```

## Common response and exception pattern

The `com.example.pos.network` package provides the common pattern for all student endpoints:

- `ResponseData<T>` for one data object.
- `ResponseDataPack<T>` for a list of data objects.
- `NetworkUtils.wrap(...)` to produce the HTTP response.
- `GlobalExceptionHandler` for validation, malformed JSON, and unexpected errors.
- Every response contains the top-level `data` and `status` nodes.
- `status.code` matches the HTTP status code and `status.message` contains the success or error message.

Example service/controller usage:

```java
return NetworkUtils.wrap(ResponseData.success(product, "Product retrieved successfully"));
```

For a collection:

```java
return NetworkUtils.wrap(ResponseDataPack.success(products, "Products retrieved successfully"));
```

For a known error:

```java
return NetworkUtils.error("Product not found", HttpStatus.NOT_FOUND);
```

The sample test flow demonstrates the intended service split:

- `TestServiceImpl` is the repository-facing implementation. It performs persistence, mapping, transactions, and database business operations.
- `TestMapper` owns all `Test` entity/DTO conversions in the `mapper` package.
- `TestService` is the service facade. It delegates to `TestServiceImpl` and converts unexpected failures into `ResponseData` or `ResponseDataPack` errors.
- Controllers call `NetworkUtils.wrap(...)` to convert the service result into the final `ResponseEntity`.

## Native query sample

The sample `Test` entity now contains a code, category, status, priority, and
automatic created/updated timestamps. `ITestRepository` contains a native SQL
example with filtering and sorting:

```java
@Query(value = "SELECT * FROM test "
        + "WHERE category = :category AND status = :status "
        + "ORDER BY priority DESC, created_at DESC", nativeQuery = true)
List<Test> findByCategoryAndStatusNative(String category, String status);
```

It is available through `TestServiceImpl.findByCategoryAndStatusNative()` and
`TestService.findTestsByCategoryAndStatusNative()`, while the normal controller
continues to use the standard Spring Data JPA query.

## Test controller sample endpoints

- `GET /api/v1/test`
- `GET /api/v1/test/{id}`
- `POST /api/v1/test`
- `PUT /api/v1/test/{id}`
- `DELETE /api/v1/test/{id}`
- `GET /api/v1/test/search?category=backend&status=ACTIVE`

## Student task

Extend this application by implementing the six required operations from the examination document:

1. `POST /api/v1/products`
2. `GET /api/v1/products`
3. `PUT /api/v1/products/{productId}`
4. `POST /api/v1/sales`
5. `GET /api/v1/sales/{saleId}`
6. `GET /api/v1/sales/summary?date={date}`

Students are expected to add the entity, DTO, repository, service, controller, validation, exception handling, and transaction-management code required by the examination. The starter does not implement the examination endpoints.

## Suggested package structure

```text
com.example.pos
├── common.api
├── product
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
├── sale
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
└── test
```

## Important examination constraints

- Use `/api/v1` as the API base path.
- Use `data` and `status` in every API response.
- Keep business logic in services, not controllers.
- Do not access repositories directly from controllers.
- Use Spring Data JPA and a relational database for persistence.
- Process a sale and update stock in one transaction.
- Do not trust client-supplied prices, totals, sale dates, or sale statuses.
