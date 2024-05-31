package library_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessDatabaseInsert {

    /**
     * Insert data into the database table.
     *
     * @param databasePath the path to the MS Access database file
     * @param id           the ID to insert
     * @param title        the title to insert
     * @param author       the author to insert
     * @param year         the year to insert
     * @throws SQLException if a database access error occurs
     */
	
    public static void insertData(String databasePath, int id, String title, String author, String year) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // Connect to the database
            String url = "jdbc:ucanaccess://" + databasePath;
            connection = DriverManager.getConnection(url);

            // Insert SQL statement
            String insertSQL = "INSERT INTO books (ID, TITLE, AUTHOR, YEAR_) VALUES (?, ?, ?, ?)";

            // Prepare statement
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, year);

            // Execute update
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new book was inserted successfully!");
            }
        } finally {
            // Clean up
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            // close the connection
            if (connection != null) {
                connection.close();
            }
        }
    }
}
