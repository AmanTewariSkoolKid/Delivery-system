# E-Commerce Delivery System

This Java application simulates a simplified e-commerce delivery system, demonstrating core object-oriented principles and basic data management. It manages orders, users, and warehouses using in-memory data structures, providing a foundation for more complex delivery system implementations.

## Project Structure
delivery-system/
├── src/
│   ├── Main.java
│   ├── entities/
│   │   ├── Order.java
│   │   ├── User.java
│   │   └── Warehouse.java
│   ├── daos/
│   │   ├── OrderDAO.java
│   │   ├── UserDAO.java
│   │   └── WarehouseDAO.java
│   ├── services/
│   │   ├── OrderService.java
│   │   ├── UserService.java
│   │   ├── WarehouseService.java
│   │   └── AuthenticationService.java
│   └── controllers/
│       ├── OrderController.java
│       ├── UserController.java
│       ├── WarehouseController.java
│       └── AuthController.java
└── README.md
* **`src/`**: Contains all Java source code files.
    * **`Main.java`**: Application entry point, orchestrating controller interactions.
    * **`entities/`**: Data model classes representing core business entities.
        * **`Order.java`**: Represents an order with attributes like order ID, description, customer phone, warehouse ID, and delivery status.
        * **`User.java`**: Represents a system user with attributes like user ID, username, password, role, contact information, and name.
        * **`Warehouse.java`**: Represents a warehouse with attributes like warehouse ID, name, location, contact, and phone.
    * **`daos/`**: Data Access Objects managing data persistence (in this case, in-memory lists).
        * **`OrderDAO.java`**: Manages `Order` data.
        * **`UserDAO.java`**: Manages `User` data.
        * **`WarehouseDAO.java`**: Manages `Warehouse` data.
    * **`services/`**: Business logic implementations.
        * **`OrderService.java`**: Provides order-related operations.
        * **`UserService.java`**: Provides user-related operations.
        * **`WarehouseService.java`**: Provides warehouse-related operations.
        * **`AuthenticationService.java`**: Handles user authentication.
    * **`controllers/`**: Handles application logic and user interactions.
        * **`OrderController.java`**: Manages order-related requests.
        * **`UserController.java`**: Manages user-related requests.
        * **`WarehouseController.java`**: Manages warehouse-related requests.
        * **`AuthController.java`**: Manages authentication requests.
* **`README.md`**: Project documentation.

## Functionality

* **Order Management**: Create, retrieve, and update orders.
* **User Management**: Create and retrieve users.
* **Warehouse Management**: Create and retrieve warehouses.
* **Authentication**: Basic user authentication.

## Getting Started

### Prerequisites

* Java Development Kit (JDK) installed (version 8 or higher recommended).
* A command-line interface (e.g., Terminal, Command Prompt).

### Compilation

1.  Clone the repository to your local machine.
2.  Navigate to the project's root directory (`delivery-system`).
3.  Compile the Java source files:

    ```bash
    javac src/Main.java src/entities/*.java src/daos/*.java src/services/*.java src/controllers/*.java
    ```

    This command compiles all `.java` files and generates `.class` files in the corresponding directories.

### Execution

1.  Ensure you are in the project's root directory.
2.  Run the application:

    ```bash
    java Main
    ```

    This executes the `Main` class, initiating the application.

## Notes

* The application uses in-memory lists for data storage, meaning data is not persisted between application restarts.
* The `Main.class` file should be generated at the root of the project folder after compilation.
* The `controllers` folder must be inside the `src` folder for the compilation to run correctly.
* This is a simplified simulation, intended for educational and demonstration purposes.

## Contributing

Contributions are welcome. Please fork the repository, create a feature branch, and submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
