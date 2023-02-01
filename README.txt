# READ ME


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