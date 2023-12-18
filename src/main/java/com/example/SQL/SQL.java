package com.example.SQL;
import com.example.Object.Person;
import com.example.demo.Main;
import com.example.demo.Scene1Controller;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.sql.*;

public class SQL {
    private static String URL = "jdbc:mysql://localhost:3306/";
    private static String USER = "root";
    private static String PASS = "admin";
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();

            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS demoDB;";
            statement.executeUpdate(createDatabaseQuery);

            String useDataBaseQuery = "USE demoDB;";
            statement.executeUpdate(useDataBaseQuery);

            String createTableQuery = "CREATE TABLE IF NOT EXISTS names_records (" +
                    "id INT UNIQUE NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "PRIMARY KEY (id)" +
                    ");";

            boolean execute = statement.execute(createTableQuery);

            connected();
        } catch (SQLException e) {
            cantConnect(e);
        }
    }

    public static void retreiveData() {
        Platform.runLater(() -> {
            try {
                String retreiveDataQuery = "SELECT name FROM names_records";
                ResultSet resultSet = statement.executeQuery(retreiveDataQuery);
                while (resultSet.next()) {
                    String name =resultSet.getString("name");

                    Person person = new Person(name);
                    Main.scene1Controller.addAPerson(person);
                }
            } catch (SQLException e) {
                alertQuery(e);
            }
        });
    }

    public static void deleteAllNames() {
        try {
            String query = "DELETE FROM names_records";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            alertQuery(e);
        }
    }

    private static void connected() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connection");
            alert.setHeaderText("Connected successfully!");
            alert.setContentText("Connected successfully to the database!");
            alert.show();
        });
    }

    private static void cantConnect(SQLException e) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can't connect to the database");
            alert.setContentText(e.getMessage());
            alert.show();
        });
    }

    public static void addAPersonQuery(Person person) {
        try {
            String query = "INSERT INTO names_records(name) VALUES(?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, person.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            alertQuery(e);
        }
    }

    private static void alertQuery(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Something's wrong.");
        alert.setContentText("Can't perform this action to database\n\n" + e.getMessage());
        alert.show();
    }
}
