package Customers;

import java.util.Scanner;
import java.util.UUID;

public class Customer {
// use universal unique identifier for a customer
    UUID customerGuid;
    private String email;
    private Address address;

    public Customer(String email, Address address)
    {
        this.customerGuid = UUID.randomUUID();
        this.email = email;
        this.address = address;
    }
// create for input
    public Customer(Scanner scanner)
    {
        System.out.println("Please enter customer info");
        System.out.println("Email: ");
        this.email = scanner.nextLine();
        this.address = new Address(scanner);
        this.customerGuid = UUID.randomUUID();
    }

    @Override
    public String toString()
    {
        return  String.format("%s%s%s%s",
                "Customer information\n",
                "ID: " + customerGuid + "\n",
                "Email: " + this.email + "\n",
                "Address: " + this.address

        );
    }
// getters
    public UUID getGuid() {return this.customerGuid;}

    public String getEmail() {return this.email;}

    public Address getCustomerAddress() {return this.address;}


}
