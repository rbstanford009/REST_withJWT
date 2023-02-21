# READ ME

To See in Intellij :
View|Tool Windows|Endpoints
ENDPOINTS
spring-boot-security-jwt
/api/auth/signin
/api/auth/signup
/user/
/user/
/user/{id}
/employee/
/employee/
/employee/sort
/employee/tree/{id}
/employee/{id}
/authority/
/authority/
/authority/{id}
/api/test/admin
/api/test/all
/api/test/mod
/api/test/user
/org/
/org/
/org/many
/org/{id}
/department/
/department/
/department/{id}


openapi: "3.0.3"
info:
  title: "REST_withJWTReview API"
  description: "REST_withJWTReview API"
  version: "1.0.0"
servers:
  - url: "https://REST_withJWTReview"
paths:
  /api/auth/signin:
    post:
      summary: "POST api/auth/signin"
      operationId: "authenticateUser"
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/LoginRequest"
        required: true
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /api/auth/signup:
    post:
      summary: "POST api/auth/signup"
      operationId: "registerUser"
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/SignupRequest"
        required: true
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /user/:
    get:
      summary: "GET user/"
      operationId: "getUsers"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
    post:
      summary: "POST user/"
      operationId: "createOrUpdateUsers"
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/UsersDto"
        required: true
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /user/{id}:
    get:
      summary: "GET user/{id}"
      operationId: "getUsers"
      parameters:
        - name: "id"
          in: "path"
          required: true
          schema:
            type: "integer"
            format: "int64"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /employee/:
    get:
      summary: "GET employee/"
      operationId: "getEmployeeS"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
    post:
      summary: "POST employee/"
      operationId: "createUpdateDeleteEmployee"
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/EmployeeDto"
        required: true
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /employee/sort:
    post:
      summary: "POST employee/sort"
      operationId: "getSortedEmployee"
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/SortDto"
        required: true
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /employee/tree/{id}:
    get:
      summary: "GET employee/tree/{id}"
      operationId: "getEmployeeChildren"
      parameters:
        - name: "id"
          in: "path"
          required: true
          schema:
            type: "integer"
            format: "int64"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /employee/{id}:
    get:
      summary: "GET employee/{id}"
      operationId: "getEmployee"
      parameters:
        - name: "id"
          in: "path"
          required: true
          schema:
            type: "integer"
            format: "int64"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /authority/:
    get:
      summary: "GET authority/"
      operationId: "getAuthorities"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
    post:
      summary: "POST authority/"
      operationId: "createOrUpdateAuthorities"
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/AuthoritiesDto"
        required: true
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /authority/{id}:
    get:
      summary: "GET authority/{id}"
      operationId: "getAuthorities"
      parameters:
        - name: "id"
          in: "path"
          required: true
          schema:
            type: "integer"
            format: "int64"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /api/test/admin:
    get:
      summary: "GET api/test/admin"
      operationId: "adminAccess"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                type: "string"
  /api/test/all:
    get:
      summary: "GET api/test/all"
      operationId: "allAccess"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                type: "string"
  /api/test/mod:
    get:
      summary: "GET api/test/mod"
      operationId: "moderatorAccess"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                type: "string"
  /api/test/user:
    get:
      summary: "GET api/test/user"
      operationId: "userAccess"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                type: "string"
  /org/:
    get:
      summary: "GET org/"
      operationId: "getOrganizations"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
    post:
      summary: "POST org/"
      operationId: "createOrUpdateOrganization"
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/OrganizationDto"
        required: true
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /org/many:
    post:
      summary: "POST org/many"
      operationId: "createOrUpdateOrganizationMany"
      requestBody:
        content:
          application/json:
            schema:
              type: "array"
              items:
                $ref: "#/components/schemas/OrganizationDto"
        required: true
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /org/{id}:
    get:
      summary: "GET org/{id}"
      operationId: "getOrganization"
      parameters:
        - name: "id"
          in: "path"
          required: true
          schema:
            type: "integer"
            format: "int64"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /department/:
    get:
      summary: "GET department/"
      operationId: "getDepartments"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
    post:
      summary: "POST department/"
      operationId: "createOrUpdateDepartment"
      requestBody:
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/DepartmentDto"
        required: true
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
  /department/{id}:
    get:
      summary: "GET department/{id}"
      operationId: "getDepartment"
      parameters:
        - name: "id"
          in: "path"
          required: true
          schema:
            type: "integer"
            format: "int64"
      responses:
        "200":
          description: "OK"
          content:
            '*/*':
              schema:
                $ref: "#/components/schemas/ResponseEntity"
components:
  schemas:
    LoginRequest:
      type: "object"
      properties:
        username:
          type: "string"
        password:
          type: "string"
    HttpHeaders:
      type: "object"
      properties: { }
    ResponseEntity:
      type: "object"
      properties:
        headers:
          $ref: "#/components/schemas/HttpHeaders"
    SignupRequest:
      type: "object"
      properties:
        username:
          type: "string"
        email:
          type: "string"
        role:
          type: "array"
          items:
            type: "string"
        password:
          type: "string"
    Timestamp:
      type: "object"
      properties:
        nanos:
          type: "integer"
          format: "int32"
    UsersDto:
      type: "object"
      properties:
        id:
          type: "integer"
          format: "int64"
        enabled:
          type: "boolean"
        first_name:
          type: "string"
        last_name:
          type: "string"
        password:
          type: "string"
        username:
          type: "string"
        updated:
          $ref: "#/components/schemas/Timestamp"
    EmployeeDto:
      type: "object"
      properties:
        id:
          type: "integer"
          format: "int64"
        updated:
          $ref: "#/components/schemas/Timestamp"
        department_id:
          type: "integer"
          format: "int32"
        parent_id:
          type: "integer"
          format: "int32"
        user_id:
          type: "integer"
          format: "int32"
    SortDto:
      type: "object"
      properties:
        sort:
          type: "string"
        pagestart:
          type: "string"
        pagesize:
          type: "string"
    AuthoritiesDto:
      type: "object"
      properties:
        id:
          type: "integer"
          format: "int64"
        authority:
          type: "string"
        user_id:
          type: "integer"
          format: "int32"
    OrganizationDto:
      type: "object"
      properties:
        id:
          type: "integer"
          format: "int64"
        description:
          type: "string"
        org_name:
          type: "string"
        updated:
          $ref: "#/components/schemas/Timestamp"
        root:
          type: "integer"
          format: "int32"
    DepartmentDto:
      type: "object"
      properties:
        id:
          type: "integer"
          format: "int64"
        departmentName:
          type: "string"
        description:
          type: "string"
        pickupDateTime:
          $ref: "#/components/schemas/Timestamp"


          ###
          POST http://localhost:8080/api/auth/signin

          ###
          POST http://localhost:8080/api/auth/signup

          ###
          GET http://localhost:8080/user/

          ###
          POST http://localhost:8080/user/

          ###
          GET http://localhost:8080/user/{{id}}

          ###
          GET http://localhost:8080/employee/

          ###
          POST http://localhost:8080/employee/

          ###
          POST http://localhost:8080/employee/sort

          ###
          GET http://localhost:8080/employee/tree/{{id}}

          ###
          GET http://localhost:8080/employee/{{id}}

          ###
          GET http://localhost:8080/authority/

          ###
          POST http://localhost:8080/authority/

          ###
          GET http://localhost:8080/authority/{{id}}

          ###
          GET http://localhost:8080/api/test/admin

          ###
          GET http://localhost:8080/api/test/all

          ###
          GET http://localhost:8080/api/test/mod

          ###
          GET http://localhost:8080/api/test/user

          ###
          GET http://localhost:8080/org/

          ###
          POST http://localhost:8080/org/

          ###
          POST http://localhost:8080/org/many

          ###
          GET http://localhost:8080/org/{{id}}

          ###
          GET http://localhost:8080/department/

          ###
          POST http://localhost:8080/department/

          ###
          GET http://localhost:8080/department/{{id}}

spring.datasource.url= jdbc:mysql://localhost:3306/jwtauth?useSSL=false
spring.datasource.username= root
spring.datasource.password= root

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# App Properties
stanford.app.jwtSecret= stanfordSecretKey
stanford.app.jwtExpirationMs= 86400000

#H2
driver-class-name: org.h2.Driver
spring.datasource.url= jdbc:h2:mem:jwtauth?useSSL=false
spring.datasource.username= sa
spring.datasource.password= password

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# App Properties
stanford.app.jwtSecret= stanfordSecretKey
stanford.app.jwtExpirationMs= 86400000


mvn spring-boot:run
