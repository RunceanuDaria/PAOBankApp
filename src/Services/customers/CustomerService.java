package Services.customers;

import Customers.Address;
import Customers.Customer;
import Repository.AddressRepository;
import Repository.CustomerRepository;


import java.sql.SQLException;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public Customer create(Customer entity) {
        Customer result = null;
        try {
            int id = customerRepository.create(entity);
            result = getWithRelatedFields(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public Customer get(int id) {
        try {
            return customerRepository.get(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Customer getWithRelatedFields(int id) {
        try {
            Customer customer = customerRepository.get(id);
            Address address = addressRepository.getByCustomerID(id);
            customer.setAddress(address);
            return customer;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update(Customer entity) {
        try {
            return customerRepository.update(entity);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            return customerRepository.delete(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

