package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            (statement).executeUpdate("CREATE TABLE IF NOT EXISTS preproject" +
                    " (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    " name varchar(127)," +
                    " lastName varchar(127)," +
                    " age INT)");
            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("Table has been created");
        } catch (SQLException | ClassNotFoundException e) {

            System.out.println("Table creating error: " + e.getMessage());
        }
    }

    public void dropUsersTable() throws SQLException {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("DROP TABLE IF EXISTS preproject");
            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("Table has been deleted");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Drop table error: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, int age) throws SQLException {
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO preproject(name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
            System.out.println("User: " + name + " has been added");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Add user error: " + e.getMessage());
        }
    }

    public void removeUserById(long id) throws SQLException {
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM preproject WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User id: " + id + " has been deleted");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("User delete error: " + e.getMessage());
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM preproject");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                connection.setAutoCommit(false);
                connection.commit();
                System.out.println(user);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Getting all users eror: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        try (Connection connection = Util.getMySQLConnection()) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM preproject");
            connection.commit();
            System.out.println("Table has been dropped");
        } catch (NullPointerException | ClassNotFoundException e) {
            System.out.println("Table drop error: " + e.getMessage());
        }
    }
}