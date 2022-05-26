package Repository;

import Customers.Address;
import Database.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRepository {
    private final String createString = "insert into address(street,city,country,number) values(?, ?, ?, ?)";
    private final String getString = "SELECT * FROM address WHERE id=?";

    private final String getByCustomerIDString = "SELECT * FROM address WHERE id= (SELECT address_id from customers WHERE customers.id = ?)";

    private final String getByLegalPersonIDString = "SELECT * FROM address WHERE id= (SELECT address_id from legal_person WHERE legal_person.id = ?)";

    private final String getByNaturalPersonIDString = "SELECT * FROM address WHERE id= (SELECT address_id from natural_person WHERE natural_person.id = ?)";

    private final String updateString = "UPDATE address SET street=?,city=?,country=?,number=? WHERE id=? ";

    private final String deleteString = "DELETE FROM address WHERE id = ?";
    private final ConnectionManager connectionManager;


    public AddressRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public int create(Address entity) throws SQLException {
        PreparedStatement statement = connectionManager.prepareInsertStatement(createString);
        statement.setString(1, entity.getStreet());
        statement.setString(2, entity.getCity());
        statement.setString(3, entity.getCountry());
        statement.setInt(4, entity.getNumber());
        return verifyInsertQuerySuccess(statement);
    }

    public Address get(int id) throws SQLException {
        PreparedStatement statement = connectionManager.prepareStatement(getString);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        return getAddressFromResulSet(rs);

    }

    public Address getByCustomerID(int id) throws SQLException {
        Address address = null;
        PreparedStatement statement = connectionManager.prepareStatement(getByCustomerIDString);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        address = getAddressFromResulSet(rs);
        return address;
    }

    public Address getByLegalPersonID(int id) throws SQLException {
        Address address = null;
        PreparedStatement statement = connectionManager.prepareStatement(getByLegalPersonIDString);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        address = getAddressFromResulSet(rs);
        return address;
    }

    public Address getByNaturalPersonID(int id) throws SQLException {
        Address address = null;
        PreparedStatement statement = connectionManager.prepareStatement(getByNaturalPersonIDString);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        address = getAddressFromResulSet(rs);
        return address;
    }

    private Address getAddressFromResulSet(ResultSet rs) throws SQLException {
        Address address = null;
        if (rs.next()) {
            int ID = rs.getInt("id");
            String street = rs.getString("street");
            String ciy = rs.getString("city");
            String country = rs.getString("country");
            int number = rs.getInt("number");
            address = new Address(street, ciy, country, number);
            address.setID(ID);
        }
        return address;
    }

    public boolean update(Address entity) throws SQLException {
        PreparedStatement statement = connectionManager.prepareStatement(updateString);
        statement.setString(1, entity.getStreet());
        statement.setString(2, entity.getCity());
        statement.setString(3, entity.getCountry());
        statement.setInt(4, entity.getNumber());
        statement.setInt(5, entity.getID());

        return statement.executeUpdate() > 0;
    }

    public boolean delete(int id) throws SQLException {
        PreparedStatement statement = connectionManager.prepareStatement(deleteString);
        statement.setInt(1, id);

        return statement.executeUpdate() > 0;
    }

    private int verifyInsertQuerySuccess(PreparedStatement statement) throws SQLException {
        boolean success;
        int id;
        success = statement.executeUpdate() > 0;

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
