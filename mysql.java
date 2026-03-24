import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql {
    public static int executeUpdate(String query) {
    try {
        Statement statement = getConnection().createStatement();
        return statement.executeUpdate(query);
    } catch (SQLException e) {
        e.printStackTrace();
        return -1;
    }
}
private static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/horsestablemanagement";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "Sonic2005885";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected to MySQL database successfully.");
            } catch (java.sql.SQLException e) {
                System.err.println("Failed to connect to MySQL database: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("MySQL database connection closed.");
            } catch (java.sql.SQLException e) {
                System.err.println("Failed to close MySQL database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public static ResultSet executeQuery(String query){
        
            Statement statement = null;
            try {
                statement = getConnection().createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
            
    }
    


    public static void main(String[] args) {
    connection = mysql.getConnection();
    ResultSet resultSet = executeQuery("SELECT * FROM user");
    
    try {
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    mysql.closeConnection();
}
    }


