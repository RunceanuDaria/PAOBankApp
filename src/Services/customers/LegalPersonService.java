package Services.customers;

import Customers.Address;
import Customers.LegalPerson;
import Repository.AddressRepository;
import Repository.LegalPersonRepository;


import java.sql.SQLException;

public class LegalPersonService {
    private final LegalPersonRepository legalPersonRepository;
    private final AddressRepository addressRepository;

    public LegalPersonService(LegalPersonRepository legalPersonRepository, AddressRepository addressRepository) {
        this.legalPersonRepository = legalPersonRepository;
        this.addressRepository = addressRepository;
    }

    public LegalPerson create(LegalPerson entity) {
        LegalPerson result = null;
        try {
            int id = legalPersonRepository.create(entity);
            result = getWithRelatedFields(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public LegalPerson get(int id) {
        try {
            return legalPersonRepository.get(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public LegalPerson getWithRelatedFields(int id) {
        try {
            LegalPerson legalPerson = legalPersonRepository.get(id);
            Address address = addressRepository.getByLegalPersonID(id);
            legalPerson.setAddress(address);
            return legalPerson;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update(LegalPerson entity) {
        try {
            return legalPersonRepository.update(entity);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            return legalPersonRepository.delete(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

