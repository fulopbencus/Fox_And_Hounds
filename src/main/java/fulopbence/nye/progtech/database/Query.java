package fulopbence.nye.progtech.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Database related methods.
 */
public class Query {
    /**
     * Queries.
     */
    public static void selectAll() {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:h2:tcp://localhost/./user", "sa", "aranyhal");
        String selectAll = "SELECT * FROM USERS";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectAll);
        while (resultSet.next()) {
            String name = resultSet.getString("NAME");
            int score = resultSet.getInt("SCORE");
            System.out.println(name + " --- " + score);
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inserts a user.
     */
    public static void setUser() {
        System.out.println("Username: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        int score = 1;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:h2:tcp://localhost/./user", "sa", "aranyhal");
            Statement statement = connection.createStatement();
            int set = statement.executeUpdate(
                    "INSERT INTO USERS(NAME,SCORE)VALUES('" + name + "'," + score + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
