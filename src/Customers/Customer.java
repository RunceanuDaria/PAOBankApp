package Customers;

import java.util.Scanner;
import java.util.UUID;

public class Customer {
    // use universal unique identifier for a customer

    int ID;
    UUID customerGuid;
    private String email;

    private Address address;

    public void setEmail(String email) {
        this.email = email;
    }


    public int getID() {
        return ID;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public UUID getCustomerGuid() {
        return customerGuid;
    }

    public void setCustomerGuid(UUID customerGuid) {
        this.customerGuid = customerGuid;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }


    public Customer(String email, Address address) {
        this.customerGuid = UUID.randomUUID();
        this.email = email;
        this.address = address;
    }

    public Customer(String email, String guid, Address address) {
        this.customerGuid = UUID.fromString(guid);
        this.email = email;
        this.address = address;
    }

    // create for input
    public Customer(Scanner scanner) {
        /*
         System.out.println("Please enter customer info");
         System.out.println("Email: ");
        */
        this.email = scanner.nextLine();
        this.address = new Address(scanner);
        this.customerGuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s",
                "Customer information\n",
                "ID: " + customerGuid + "\n",
                "Email: " + this.email + "\n",
                "Address: " + this.address

        );
    }

    // transform to CSV file format
    public String convertToCsv() {
        return email + "," +
                address.convertToCsv();
    }

}
