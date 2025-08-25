<h1 style="text-align: center;">Spring Boot Async Schedule Task Project</h1>

<p style="text-align: center;">
  <a href="https://github.com/deepaksorthiya/spring-boot-async-task/workflows/maven-build.yml">
    <img src="https://github.com/deepaksorthiya/spring-boot-async-task/actions/workflows/maven-build.yml/badge.svg" alt="Java Maven Build Test"/>
  </a>
  <a href="https://hub.docker.com/r/deepaksorthiya/spring-boot-async-task">
    <img src="https://img.shields.io/docker/pulls/deepaksorthiya/spring-boot-async-task" alt="Docker"/>
  </a>
  <a href="https://spring.io/projects/spring-boot">
    <img src="https://img.shields.io/badge/spring--boot-3.5.0-brightgreen?logo=springboot" alt="Spring Boot"/>
  </a>
</p>

## Live Demo

TBD

---

## 📑 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Requirements](#-requirements)
- [Getting Started](#-getting-started)
    - [Clone the Repository](#1-clone-the-repository)
    - [Start Docker](#2-start-docker)
    - [Build the Project](#3-build-the-project)
    - [Run Project Locally](#4-run-the-project)
    - [Build Docker Image](#5-optional-build-docker-image-docker-should-be-running)
    - [Run Docker Image](#6-optional-running-on-docker)
    - [Deploy on Kubernetes with Helm](#7-optionalrun-on-local-minikube-kubernetes-using-helm-chart)
- [Testing](#-testing)
- [Clean Up](#-cleanup)
- [Reference Documentation](#-reference-documentation)
- [Guides](#-guides)

---

## 🚀 Overview

A modern **Spring Boot** starter project with REST API, Async Scheduling Task.

---

## 🚀 Features

- Spring Boot 3.5.5 (Java 24)
- Async Task

---

## 📦 Requirements

- Git `2.51.0+`
- Java `24`
- Maven `3.9+`
- Spring Boot `3.5.5`
- (Optional)Docker Desktop (tested on `4.43.0`)
- (Optional) Minikube/Helm for Kubernetes

---

## 🛠️ Getting Started

### 1. Clone the Repository

```sh
git clone https://github.com/deepaksorthiya/spring-boot-async-task.git
cd spring-boot-async-task
```

### 2. Start Docker

```sh
docker compose up
```

### 3. Build the Project

```sh
./mvnw clean package -DskipTests
```

OR

for native build run

```bash
./mvnw clean native:compile -Pnative
```

### 4. Run the Project

```sh
./mvnw spring-boot:run
```

OR Jar file

```sh
java -jar .\target\spring-boot-async-task-0.0.1-SNAPSHOT.jar
```

OR

Run native image directly

```bash
target/spring-boot-async-task
```

### 5. (Optional) Build Docker Image (docker should be running)

```sh
./mvnw clean spring-boot:build-image -DskipTests
```

OR

To create the native container image, run the following goal:

```bash
./mvnw spring-boot:build-image -Pnative
```

### 6. (Optional) Running On Docker

```sh
docker run -p 8080:8080 --name spring-boot-async-task deepaksorthiya/spring-boot-async-task:latest
```

### 7. (Optional)Run on Local minikube Kubernetes using Helm Chart

```sh
mkdir helm
cd helm
helm create spring-boot-async-task
helm lint spring-boot-async-task
helm install spring-boot-async-task --values=spring-boot-async-task/values.yaml spring-boot-async-task
helm install spring-boot-async-task spring-boot-async-task
helm uninstall spring-boot-async-task
```

---

## 🧪 Testing

- Access the API: [http://localhost:8080](http://localhost:8080)
- H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### Postman API Collection

[Rest APIs Postman Collection](https://www.postman.com/deepaksorthiya/workspace/public-ws/collection/12463530-d326f32e-56e8-4af8-ab26-b09fbde97338?action=share&source=copy-link&creator=12463530)

### Rest API Endpoints

TBD

### Run Unit-Integration Test Cases

```bash
./mvnw clean test
```

To run your existing tests in a native image, run the following goal:

```bash
./mvnw test -PnativeTest
```

---

## 🧹 Cleanup

```sh
docker compose down -v
```

---

## 📚 Reference Documentation

For further reference, please consider the following sections:

- [Scheduling](https://docs.spring.io/spring-framework/reference/integration/scheduling.html)
- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/maven-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/maven-plugin/build-image.html)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/reference/actuator/index.html)
- [Spring Web](https://docs.spring.io/spring-boot/reference/web/servlet.html)
- [Spring Data JPA](https://docs.spring.io/spring-boot/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [Validation](https://docs.spring.io/spring-boot//io/validation.html)
- [Flyway Migration](https://docs.spring.io/spring-boot/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)

---

## 📚 Guides

The following guides illustrate how to use some features concretely:

- [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Validation](https://spring.io/guides/gs/validating-form-input/)
- [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

---

<p style="text-align: center;">
  <b>Happy Coding!</b> 🚀
</p>
