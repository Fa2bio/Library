<h1>Library</h1>

> Status: Finished ✔️

## Contents

* [Requirements](#requirements)
* [Technologies Used](#tec)
* [Installing](#installing)
* [Run Application](#run-application)
* [General Usage](#general-usage)
* [Diagrams](#uml)

## <a name="Requirements"></a>Requirements

- Java 11
- Spring Boot
- Lombok

## <a name="tec"></a>Technologies Used

- Java
- Spring Boot (versão 2.7.7)
- Spring Data JPA
- Spring Cloud
- MySQL
- Flyway
- Lombok
- Model Mapper
- Data Transfer Object (DTO)
- Bean validation
- ViaCep-Api (https://github.com/Fa2bio/ViaCep-Api)
- Isbn-Api (https://github.com/Fa2bio/Isbn-Api)

## <a name="installing"></a>Installing

- Clone the repository to your device;
- Import it as a MAVEN project in your IDE;
- In your Postman client, import the requests models available at [Library.postman_collection.zip](https://github.com/Fa2bio/Library-Api/files/10462929/Library.postman_collection.zip);
- By default, this application runs using port 8080;
- This API contains a mass of data inserted over aftermigrate;
- It's necessary to inform the username and password of your localhost server, for that go to the application.properties file and edit:

### Application.properties
```xml
spring.datasource.username=
spring.datasource.password=
```

## <a name="run-application"></a>Run Application
- After fulfilling the requirements and installing, run a LibraryApplication class as a spring boot application;
- To access documentation for features supported by this API, access the Swagger available at http://localhost:8080/swagger-ui/#/

## <a name="general-usage"></a>General Usage

### CRUD

* After importing the requests to your Postman client and execute the API, you will have access to all the features.

## <a name="uml"></a> Diagrams
![2](https://user-images.githubusercontent.com/41877566/213610444-b142b562-6251-4709-a44a-108567ce5b75.png)
