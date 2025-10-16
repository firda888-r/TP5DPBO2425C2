import java.sql.*;

public class Database {
    private Connection connection;
    private Statement statement;

    // Constructor
    public Database() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_product",
                    "root",
                    ""
            );
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Digunakan untuk SELECT
    public ResultSet selectQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    // Digunakan untuk INSERT, UPDATE, DELETE
    public void insertUpdateDeleteQuery(String query) throws SQLException {
        statement.executeUpdate(query);
    }
}
