package Services.customers;

import Customers.Address;
import Customers.NaturalPerson;
import Repository.AddressRepository;
import Repository.NaturalPersonRepository;


import java.sql.SQLException;

public class NaturalPersonService {
    private final NaturalPersonRepository naturalPersonRepository;
    private final AddressRepository addressRepository;

    public NaturalPersonService(NaturalPersonRepository naturalPersonRepository, AddressRepository addressRepository) {
        this.naturalPersonRepository = naturalPersonRepository;
        this.addressRepository = addressRepository;
    }

    public NaturalPerson create(NaturalPerson entity) {
        NaturalPerson result = null;
        try {
            int id = naturalPersonRepository.create(entity);
            result = getWithRelatedFields(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public NaturalPerson get(int id) {
        try {
            return naturalPersonRepository.get(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public NaturalPerson getWithRelatedFields(int id) {
        try {
            NaturalPerson naturalPerson = naturalPersonRepository.get(id);
            Address address = addressRepository.getByNaturalPersonID(id);
            naturalPerson.setAddress(address);
            return naturalPerson;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update(NaturalPerson entity) {
        try {
            return naturalPersonRepository.update(entity);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            return naturalPersonRepository.delete(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

