package Services.customers;

import Customers.Address;
import Repository.AddressRepository;

import java.sql.SQLException;

public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address create(Address entity) {
        Address result = null;
        try {
            int id = addressRepository.create(entity);
            result = addressRepository.get(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public Address get(int id) {
        try {
            return addressRepository.get(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Address getByCustomerID(int id) {
        try {
            return addressRepository.getByCustomerID(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update(Address entity) {
        try {
            return addressRepository.update(entity);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            return addressRepository.delete(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
