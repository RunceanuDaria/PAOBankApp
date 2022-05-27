package Repository;

import Customers.LegalPerson;
import Customers.NaturalPerson;
import Database.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class LegalPersonRepository {
    private final String createString = "insert into legal_person(guid,email,address_id,name,registration_code,bank_account) values(?, ?, ?, ?, ?, ?)";
    private final String getString = "SELECT * FROM legal_person WHERE id=?";

    private final String updateString = "UPDATE legal_person SET guid=?,email=?,address_id=?,name=?,registration_code=?,bank_account=? WHERE id=? ";

    private final String deleteString = "DELETE FROM legal_person WHERE id = ?";
    private final ConnectionManager connectionManager;

    public LegalPersonRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public int create(LegalPerson entity) throws SQLException {
        PreparedStatement statement = connectionManager.prepareInsertStatement(createString);
        statement.setString(1, entity.getCustomerGuid().toString());
        statement.setString(2, entity.getEmail());
        if (entity.getAddress() != null) {
            statement.setInt(3, entity.getAddress().getID());
        } else {
            statement.setNull(3, Types.NUMERIC);
        }
        statement.setString(4, entity.getName());
        statement.setString(5, entity.getRegistrationCode());
        statement.setString(6, entity.getBankAccount());

        return verifyInsertQuerySuccess(statement);
    }

    public LegalPerson get(int id) throws SQLException {
        LegalPerson customer = null;
        PreparedStatement statement = connectionManager.prepareStatement(getString);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            int ID = rs.getInt("id");
            String customerGuid = rs.getString("guid");
            String email = rs.getString("email");
            String name = rs.getString("name");
            String registrationCode = rs.getString("registration_code");
            String bankAccount = rs.getString("bank_account");
            try {
                customer = new LegalPerson(email, customerGuid, null, name, registrationCode, bankAccount);
                customer.setID(ID);
            }
            catch (Exception ignored){

            }
        }
        return customer;
    }

    public boolean update(LegalPerson entity) throws SQLException {
        PreparedStatement statement = connectionManager.prepareStatement(updateString);
        statement.setString(1, entity.getCustomerGuid().toString());
        statement.setString(2, entity.getEmail());

        if (entity.getAddress() != null) {
            statement.setInt(3, entity.getAddress().getID());
        } else {
            statement.setNull(3, Types.NUMERIC);
        }

        statement.setString(4, entity.getName());
        statement.setString(5, entity.getRegistrationCode());
        statement.setString(6, entity.getBankAccount());

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
