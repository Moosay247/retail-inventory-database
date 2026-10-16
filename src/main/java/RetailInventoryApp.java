import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Console application for managing products in the retail inventory database.
 * Database credentials are read from environment variables and are never
 * stored in source control.
 */
public class RetailInventoryApp {
    private static final String DB_URL = requireEnvironmentVariable("DB_URL");
    private static final String DB_USER = requireEnvironmentVariable("DB_USER");
    private static final String DB_PASSWORD = requireEnvironmentVariable("DB_PASSWORD");

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the Retail Inventory database.");
            runMenu(connection, input);
        } catch (SQLException exception) {
            System.err.println("Database operation failed: " + exception.getMessage());
        }
    }

    private static void runMenu(Connection connection, Scanner input) throws SQLException {
        int choice;
        do {
            printMenu();
            choice = readInt(input, "Enter your choice: ");

            switch (choice) {
                case 1 -> showAllProducts(connection);
                case 2 -> addProduct(connection, input);
                case 3 -> updateProductQuantity(connection, input);
                case 4 -> deleteProduct(connection, input);
                case 5 -> showLowStockProducts(connection);
                case 6 -> System.out.println("Goodbye.");
                default -> System.out.println("That is not a valid choice.");
            }
        } while (choice != 6);
    }

    private static void printMenu() {
        System.out.println("\n--- Retail Inventory Menu ---");
        System.out.println("1. Show all products");
        System.out.println("2. Add a new product");
        System.out.println("3. Update product quantity");
        System.out.println("4. Delete a product");
        System.out.println("5. Show low stock products");
        System.out.println("6. Exit");
    }

    private static void showAllProducts(Connection connection) throws SQLException {
        String sql = "SELECT product_id, product_name, category, price, quantity_in_stock FROM Products ORDER BY product_id";
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(sql)) {
            System.out.println("\nProduct List:");
            while (results.next()) {
                System.out.printf("%d | %s | %s | $%.2f | Stock: %d%n",
                        results.getInt("product_id"),
                        results.getString("product_name"),
                        results.getString("category"),
                        results.getDouble("price"),
                        results.getInt("quantity_in_stock"));
            }
        }
    }

    private static void addProduct(Connection connection, Scanner input) throws SQLException {
        System.out.print("Enter product name: ");
        String name = input.nextLine();
        System.out.print("Enter category: ");
        String category = input.nextLine();
        double price = readDouble(input, "Enter price: ");
        int quantity = readInt(input, "Enter quantity in stock: ");
        int reorderLevel = readInt(input, "Enter reorder level: ");

        String sql = "INSERT INTO Products "
                + "(product_name, category, price, quantity_in_stock, reorder_level) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setDouble(3, price);
            statement.setInt(4, quantity);
            statement.setInt(5, reorderLevel);
            statement.executeUpdate();
            System.out.println("Product was added.");
        }
    }

    private static void updateProductQuantity(Connection connection, Scanner input) throws SQLException {
        int productId = readInt(input, "Enter product ID to update: ");
        int newQuantity = readInt(input, "Enter the new quantity: ");
        String sql = "UPDATE Products SET quantity_in_stock = ? WHERE product_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, productId);
            int rows = statement.executeUpdate();
            System.out.println(rows > 0
                    ? "Product quantity was updated."
                    : "No product was found with that ID.");
        }
    }

    private static void deleteProduct(Connection connection, Scanner input) throws SQLException {
        int productId = readInt(input, "Enter product ID to delete: ");
        String sql = "DELETE FROM Products WHERE product_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            int rows = statement.executeUpdate();
            System.out.println(rows > 0
                    ? "Product was deleted."
                    : "No product was found with that ID.");
        }
    }

    private static void showLowStockProducts(Connection connection) throws SQLException {
        String sql = "SELECT product_id, product_name, quantity_in_stock, reorder_level "
                + "FROM Low_Stock_Products ORDER BY quantity_in_stock";
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(sql)) {
            System.out.println("\nLow Stock Products:");
            while (results.next()) {
                System.out.printf("%d | %s | Stock: %d | Reorder Level: %d%n",
                        results.getInt("product_id"),
                        results.getString("product_name"),
                        results.getInt("quantity_in_stock"),
                        results.getInt("reorder_level"));
            }
        }
    }

    private static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException exception) {
                System.out.println("Enter a whole number.");
            }
        }
    }

    private static double readDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine().trim();
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static String requireEnvironmentVariable(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(name + " environment variable is required.");
        }
        return value;
    }
}


