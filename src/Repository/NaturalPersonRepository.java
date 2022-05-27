package Repository;

import Customers.Customer;
import Customers.NaturalPerson;
import Database.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class NaturalPersonRepository {
    private final String createString = "insert into natural_person(guid,email,address_id,first_name,last_name,cnp) values(?, ?, ?, ?, ?, ?)";
    private final String getString = "SELECT * FROM natural_person WHERE id=?";

    private final String updateString = "UPDATE natural_person SET guid=?,email=?,address_id=?,first_name=?,last_name=?,cnp=? WHERE id=? ";

    private final String deleteString = "DELETE FROM natural_person WHERE id = ?";
    private final ConnectionManager connectionManager;

    public NaturalPersonRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public int create(NaturalPerson entity) throws SQLException {
        PreparedStatement statement = connectionManager.prepareInsertStatement(createString);
        statement.setString(1, entity.getCustomerGuid().toString());
        statement.setString(2, entity.getEmail());
        if (entity.getAddress() != null) {
            statement.setInt(3, entity.getAddress().getID());
        } else {
            statement.setNull(3, Types.NUMERIC);
        }
        statement.setString(4, entity.getFirstName());
        statement.setString(5, entity.getLastName());
        statement.setString(6, entity.getCnp());

        return verifyInsertQuerySuccess(statement);
    }

    public NaturalPerson get(int id) throws SQLException {
        NaturalPerson customer = null;
        PreparedStatement statement = connectionManager.prepareStatement(getString);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            int ID = rs.getInt("id");
            String customerGuid = rs.getString("guid");
            String email = rs.getString("email");
            String fistName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String cnp = rs.getString("cnp");
            try {
                customer = new NaturalPerson(email, customerGuid, null, fistName, lastName, cnp);
                customer.setID(ID);
            }
            catch (Exception ignored){

            }
        }
        return customer;
    }

    public boolean update(NaturalPerson entity) throws SQLException {
        PreparedStatement statement = connectionManager.prepareStatement(updateString);
        statement.setString(1, entity.getCustomerGuid().toString());
        statement.setString(2, entity.getEmail());

        if (entity.getAddress() != null) {
            statement.setInt(3, entity.getAddress().getID());
        } else {
            statement.setNull(3, Types.NUMERIC);
        }

        statement.setString(4, entity.getFirstName());
        statement.setString(5, entity.getLastName());
        statement.setString(6, entity.getCnp());

        statement.setInt(7, entity.getID());

        var a = statement.executeUpdate();

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
