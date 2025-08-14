# Atipera GitHub Repo API

A Spring Boot application that consumes the GitHub REST API and returns a list of a user's repositories **that are not forks**, along with their branches and last commit SHA.

## 📌 Features
- Fetches repositories for a given GitHub username
- Filters out forked repositories
- For each repository, fetches:
    - Branch name
    - Last commit SHA
- Returns data in JSON format
- Handles user-not-found errors with a structured 404 JSON response

## Technologies Used
- Java 21
- Spring Boot 3.5
- REST API consumption using `RestTemplate`
- Maven
- Lombok
- JUnit & MockMvc for integration testing

## Endpoint
**GET** `/users/{username}/repos`

### Example request
```bash
curl http://localhost:8080/users/octocat/repos