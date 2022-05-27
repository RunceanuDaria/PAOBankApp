package Database;

import java.sql.*;

public class ConnectionManager {
    private Connection conn;
    private final String url;
    private final String user;
    private final String password;

    public ConnectionManager(String url, String user, String password) throws ClassNotFoundException, SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void initialize() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
    }

    public void close() throws SQLException {
        conn.close();
    }

    public PreparedStatement prepareStatement(String stmt) throws SQLException {
        if (conn == null || conn.isClosed()) {
            throw new SQLException();
        }
        return conn.prepareStatement(stmt);
    }

    public PreparedStatement prepareInsertStatement(String stmt) throws SQLException {
        if (conn == null || conn.isClosed()) {
            throw new SQLException();
        }
        return conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
    }
}
