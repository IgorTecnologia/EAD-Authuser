# EAD-Authuser

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

Esta é uma aplicação que utiliza do modelo arquitetural de microservices e de uma APIRestful (De acordo com o Modelo de maturidade de Richardson) para realizar a comunicação entre os microservices.

This is an application that uses the microservices architectural model and a Restful API (According to the Richardson Maturity Model) to carry out communication between microservices.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/IgorTecnologia/EAD-Authuser.git
```

2. Install dependencies with Maven

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8087
3. You must have a PostgreSQL server active on the machine.

## Collection Postman

Baixe esses arquivos e importe-os para o seu Postman para utilizar os métodos HTTP prontos juntamente com a variávei ​​de ambiente já configurada, para realizar as solicitações/respostas.

Download these files and import them into your Postman to use the ready-made HTTP methods along with the already configured environment variable, to perform the requests/responses.

[Download Collections](https://github.com/IgorTecnologia/EAD-Authuser/blob/docs-postman/User-collections.json)

[Download Enviroment Variables](https://github.com/IgorTecnologia/EAD-Authuser/blob/docs-postman/Local-host-environment.json)

## API Endpoints
The API provides the following endpoints:

**POST /auth/signup**
```markdown
POST /auth/signup - Register User.
```
```json
{
    "username" : "Bruno",
    "email" : "bruno@gmail.com.br",
    "password" : "1234567",
    "fullName" : "Bruno Silva Ferreira Melo",
    "phoneNumber" : "+55 11 91009-0807",
    "cpf" : "123-321-144-7",
    "imageUrl" : "www.image.com"
}
```
**GET USERS**

Optional: Use pagination parameters and/or advanced filtering parameters (already contained in Collections.json provided above).

Opcional: Use parâmetros de paginação e/ou parâmetros de filtragem avançados (já contidos em Collections.json fornecido acima).
```markdown
GET /users - Retrieve a pagination of all users, along with hyperlinks to another endpoint.
```
```json
"content": [
        {
            "id": "69e40d66-6dbb-4fc5-9f7e-ccf306d0bbb0",
            "username": "Bruno",
            "email": "bruno@gmail.com.br",
            "fullName": "Bruno Silva Ferreira Melo",
            "phoneNumber": "+55 11 91009-0807",
            "cpf": "123-321-144-7",
            "imageUrl": "www.imageUrl.com",
            "type": "STUDENT",
            "status": "ACTIVE",
            "creationDate": "2024-08-15T03:12:54.022328",
            "lastUpdateDate": "2024-08-15T03:12:54.023329",
            "links": [
                {
                    "rel": "self",
                    "href": "http://localhost:8087/users/69e40d66-6dbb-4fc5-9f7e-ccf306d0bbb0"
                }
            ]
        }
```
**GET USERS/ID**
```markdown
GET /users/id - Retrieve a single user by id.
```

```json
{
    "id": "69e40d66-6dbb-4fc5-9f7e-ccf306d0bbb0",
    "username": "Bruno",
    "email": "bruno@gmail.com.br",
    "fullName": "Bruno Silva Ferreira Melo",
    "phoneNumber": "+55 11 91009-0807",
    "cpf": "123-321-144-7",
    "imageUrl": "www.imageUrl.com",
    "type": "STUDENT",
    "status": "ACTIVE",
    "creationDate": "2024-08-15T03:12:54.022328",
    "lastUpdateDate": "2024-08-15T03:12:54.023329",
    "links": []
}
```
**PUT USERS**
```markdown
PUT /users/id - Update a user by id.
```
```json
{
    "fullName" : "Bruno Silva Ferreira de Souza",
    "cpf" : "123-321-213-7",
    "phoneNumber" : "+55 11 97110-3144"
}
```
**PUT PASSWORD**
```markdown
PUT users/{id}/password - Update a password by id.
```
```json
{
    "oldPassword" : "1234567",
    "password" : "7654321"
}
```
**PUT IMAGE**
```markdown
PUT users/{id}/image - Update a image by id.
```
```json
{
    "imageUrl" : "www.imageUrl.com.br"
}
```
**DELETE USER**
```markdown
DELETE /users/id - Delete a user by id.

Return HTTP status: 200 OK
Body: "User deleted successfully!"

```
## Database
This application uses [PostgreSQL](https://www.postgresql.org/docs/) as its database.

## Technologies Used

- JDK 22
- Java version 11
- Spring Boot
- Maven
- PostgreSQL
- IntelliJ IDEA
- Postman

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, abra um problema ou envie uma solicitação pull ao repositório.

Ao contribuir para este projeto, siga o estilo de código existente, [convenções de commit](https://medium.com/linkapi-solutions/conventional-commits-pattern-3778d1a1e657), e envie suas alterações em uma branch separada.

![Spring](https://hermes.dio.me/articles/cover/79a1dddc-5f58-46db-bd5f-95733ba66097.png)
