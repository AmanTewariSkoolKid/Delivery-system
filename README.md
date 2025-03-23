# Delivery System Application

This is a Java-based delivery system application designed to manage orders, users, and warehouses, along with user authentication.

## Features

* **Order Management:**
    * Create, update, and view orders.
    * Track order status (delivered, in transit, pending).
* **User Management:**
    * Create, update, and view user accounts.
    * Manage user roles (admin, user).
* **Warehouse Management:**
    * Create, update, and view warehouse information.
* **User Authentication:**
    * Secure user login using username and password.
    * GUI Login page with background image.

## Prerequisites

* Java Development Kit (JDK) 8 or later.

## Setup and Installation

1.  **Clone the repository:**

    ```bash
    git clone <repository_url>
    ```

2.  **Navigate to the project directory:**

    ```bash
    cd Delivery-system
    ```

3.  **Compile the Java source files:**

    ```bash
    cd src
    javac Main.java entities/*.java daos/*.java services/*.java controllers/*.java LoginGUI.java
    ```

4.  **Run the application (GUI Login):**

    ```bash
    cd ..
    java LoginGUI
    ```

    * Alternatively, to run the console version, run:

    ```bash
    java Main
    ```

## File Structure****

Coding partner
Markdown

# Delivery System Application

This is a Java-based delivery system application designed to manage orders, users, and warehouses, along with user authentication.

## Features

* **Order Management:**
    * Create, update, and view orders.
    * Track order status (delivered, in transit, pending).
* **User Management:**
    * Create, update, and view user accounts.
    * Manage user roles (admin, user).
* **Warehouse Management:**
    * Create, update, and view warehouse information.
* **User Authentication:**
    * Secure user login using username and password.
    * GUI Login page with background image.

## Prerequisites

* Java Development Kit (JDK) 8 or later.

## Setup and Installation

1.  **Clone the repository:**

    ```bash
    git clone <repository_url>
    ```

2.  **Navigate to the project directory:**

    ```bash
    cd Delivery-system
    ```

3.  **Compile the Java source files:**

    ```bash
    cd src
    javac Main.java entities/*.java daos/*.java services/*.java controllers/*.java LoginGUI.java
    ```

4.  **Run the application (GUI Login):**

    ```bash
    cd ..
    java LoginGUI
    ```

    * Alternatively, to run the console version, run:

    ```bash
    java Main
    ```

## File Structure

Delivery-system/
├── src/
│   ├── Main.java              // Main application entry point.
│   ├── LoginGUI.java          // GUI Login screen.
│   ├── controllers/           // Contains controller classes.
│   │   ├── AuthController.java   // Handles user authentication.
│   │   ├── OrderController.java  // Handles order-related operations.
│   │   ├── UserController.java // Handles user-related operations.
│   │   └── WarehouseController.java // Handles warehouse-related operations.
│   ├── daos/                  // Contains data access object classes.
│   │   ├── OrderDAO.java       // Data access for orders.
│   │   ├── UserDAO.java        // Data access for users.
│   │   └── WarehouseDAO.java   // Data access for warehouses.
│   ├── entities/              // Contains entity classes.
│   │   ├── Order.java          // Represents an order.
│   │   ├── User.java           // Represents a user.
│   │   └── Warehouse.java      // Represents a warehouse.
│   ├── services/              // Contains service classes.
│   │   ├── AuthenticationService.java // Handles authentication logic.
│   │   ├── OrderService.java     // Handles order-related business logic.
│   │   ├── UserService.java    // Handles user-related business logic.
│   │   └── WarehouseService.java // Handles warehouse-related business logic.
└── README.md              // This file.
## Usage

* **Login:**
    * Run the `LoginGUI` application.
    * Enter your username and password.
    * Click "Sign in."
* **Main Application (Console):**
    * Once logged in (or if running `Main.java`) follow the on-screen prompts to manage orders, users, and warehouses.

## Notes

* Replace `"login_background.png"` in `LoginGUI.java` with the actual path to your login background image.
* The application uses in-memory data storage. For persistent storage, integrate with a database.
* The `openMainApplication()` method in `LoginGUI.java` is a placeholder. Replace it with the code to open your main application window.
* The console version of the app is still fully functional, if you do not want to use the GUI.

## Contributing

Feel free to contribute to this project by submitting pull requests or reporting issues.

## License

This project is licensed under the MIT License.
