package daos;

import entities.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> users = new ArrayList<>();

    public UserDAO() {
        populateUsers();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void saveUser(User user) {
        users.add(user);
    }

    private void populateUsers() {
        users.add(new User(1L, "admin", "password", "ADMIN", "111-222-3333", "Admin User"));
        users.add(new User(2L, "employee", "password2", "EMPLOYEE", "444-555-6666", "Employee User"));
        users.add(new User(3L, "driver", "password3", "DRIVER", "777-888-9999", "Driver User"));
    }
}