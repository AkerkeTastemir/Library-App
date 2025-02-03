# Library Management System

## Overview

This is a simple Library Management System developed using Java and PostgreSQL. The application allows users to manage books, including adding, updating, retrieving, and deleting book records from a PostgreSQL database.

## Features

- Add new books to the library.
- Update existing book information.
- Retrieve details of all books or a specific book by ISBN.
- Delete a book from the library.
- Use PostgreSQL as the database to store book information.

## Project Structure

- **Main.java**: The entry point of the application.
- **controllers**: Contains the `BookController` class, which handles the business logic.
- **repositories**: Contains the `BookRepository` class and `IBookRepository` interface, which interact with the database.
- **data**: Contains the `PostgresDB` class and `IDB` interface, which manage the database connection.
- **models**: Contains the `Book` class, which represents the book entity.
- **ui**: Contains the `MyApplication` class, which handles user input and output.

## Database Schema

The `books` table has the following columns:

- `isbn` (character varying, 17, PRIMARY KEY): The unique ISBN of the book.
- `author` (character varying, 255): The author of the book.
- `title` (character varying, 255): The title of the book.
- `category` (character varying, 255): The category of the book.
- `isAvailable` (boolean): Whether the book is available or not.

## Setup

### Prerequisites

- Java JDK
- PostgreSQL
- PostgreSQL JDBC Driver

#### Thats it!
## Authors

- [@AkerkeTastemir](https://www.github.com/akerketastemir) / Акерке

- [@hqvnd](https://www.github.com/hqvnd) / Даниял

- [@hyanosvke](https://www.github.com/hyanosvke) / Мирас

