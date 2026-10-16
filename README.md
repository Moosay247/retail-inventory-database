# Retail Inventory and Supplier Management Database

A school database project for **Modern Database Management (315-3)**, completed in April 2026. The project combines a normalized MySQL schema with a Java/JDBC command-line application for everyday inventory operations.

## Features

- Five related tables: suppliers, products, purchase orders, order items, and inventory transactions
- Primary keys, foreign keys, constraints, and cascading relationship rules
- Create, read, update, and delete product operations through Java and JDBC
- `Low_Stock_Products` SQL view for reorder monitoring
- Prepared statements for user-provided database values
- Sample records for testing the schema and application
- Credentials loaded from environment variables instead of source code

The `sql/schema.sql` and `sql/sample_data.sql` files are a clean, runnable reconstruction of the coursework database structure for this public portfolio version; the original university database export was not available. The Java menu application was adapted from the original project to remove a hard-coded university connection and to use environment variables.

## Technologies

- Java 17
- MySQL
- JDBC / MySQL Connector/J
- Maven
- Visual Studio Code

## Project structure

```text
retail-inventory-database/
��� src/main/java/RetailInventoryApp.java
��� sql/schema.sql
��� sql/sample_data.sql
��� pom.xml
��� README.md
```

## Setup

1. Install Java 17+, Maven, and MySQL.
2. Run `sql/schema.sql`, followed by `sql/sample_data.sql`.
3. Define the required environment variables:

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/retail_inventory?serverTimezone=UTC&useSSL=false"
$env:DB_USER = "your_mysql_username"
$env:DB_PASSWORD = "your_mysql_password"
```

4. Start the application:

```powershell
mvn compile exec:java
```

## Menu operations

1. Show all products
2. Add a new product
3. Update product quantity
4. Delete a product
5. Show low-stock products
6. Exit

## Security note

Database credentials are intentionally excluded from the repository. Use local environment variables and never commit passwords or private connection details.


