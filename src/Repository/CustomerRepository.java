package Repository;

import Customers.Address;
import Customers.Customer;
import Database.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CustomerRepository {
    private final String createString = "insert into customers(guid,email,address_id) values(?, ?, ?)";
    private final String getString = "SELECT * FROM customers WHERE id=?";

    private final String updateString = "UPDATE customers SET guid=?,email=?,address_id=? WHERE id=? ";

    private final String deleteString = "DELETE FROM customers WHERE id = ?";
    private final ConnectionManager connectionManager;

    public CustomerRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public int create(Customer entity) throws SQLException {
        PreparedStatement statement = connectionManager.prepareInsertStatement(createString);
        statement.setString(1, entity.getCustomerGuid().toString());
        statement.setString(2, entity.getEmail());
        if (entity.getAddress() != null) {
            statement.setInt(3, entity.getAddress().getID());
        } else {
            statement.setNull(3, Types.NUMERIC);
        }

        return verifyInsertQuerySuccess(statement);
    }

    public Customer get(int id) throws SQLException {
        Customer customer = null;
        PreparedStatement statement = connectionManager.prepareStatement(getString);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            int ID = rs.getInt("id");
            String customerGuid = rs.getString("guid");
            String email = rs.getString("email");
            customer = new Customer(email, customerGuid, null);
            customer.setID(ID);
        }
        return customer;
    }

    public boolean update(Customer entity) throws SQLException {
        PreparedStatement statement = connectionManager.prepareStatement(updateString);
        statement.setString(1, entity.getCustomerGuid().toString());
        statement.setString(2, entity.getEmail());

        if (entity.getAddress() != null) {
            statement.setInt(3, entity.getAddress().getID());
        } else {
            statement.setNull(3, Types.NUMERIC);
        }
        statement.setInt(4, entity.getID());

        var a= statement.executeUpdate();

        return a > 0;
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement statement = connectionManager.prepareStatement(deleteString);
        statement.setInt(1, id);

        return statement.executeUpdate() > 0;
    }

    private int verifyInsertQuerySuccess(PreparedStatement statement) throws SQLException {
        int id;
        boolean success = statement.executeUpdate() > 0;

        if (!success) {
            throw new SQLException("Creating address failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        return id;
    }
}
