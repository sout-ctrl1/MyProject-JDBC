package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("John", "Wick", (byte)40);
        user.saveUser("Thomas", "Anderson", (byte)24);
        user.saveUser("Kevin", "Lomaks", (byte)30);
        user.saveUser("Johnny", "Mnemonic", (byte)35);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
