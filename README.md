# Digicheese-v2

## Getting Started

### Prerequisite :

Be sure to have Docker Desktop installed on your computer.

**Docker Desktop**: [Installation Guide](https://www.docker.com/products/docker-desktop/)

### 1. Clone the repository of this project

### 2. Configure environnement variables

Copy `.env.example` file at the root of the repository, and name its copy `.env`,
then define the variables inside it, to define your database.

Copy `.env.example` file in the frontend repository, and name its copy `.env`,
then define the variables inside it, to define your database.

### 3. Start Project

To build or rebuild the Project and start it, use this in your CLI:

```bash
docker-compose up --build --watch
```

When the project is already built, and doesn't need a rebuilt, use this instead:

```bash
docker-compose up --watch
```

### 4. Documentation

For some addiotionnal auto-generated documentation and easier Api testing, we decided to use [Swagger UI](http://localhost:8080/swagger-ui/index.html).
