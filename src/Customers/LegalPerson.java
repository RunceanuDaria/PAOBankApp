package Customers;

import java.util.Scanner;

public class LegalPerson extends Customer {
    private String name;
    private String registrationCode; // cod caen
    private String bankAccount; // cui

    public LegalPerson(String email, Address address, String name, String registrationCode, String bankAccount) {
        super(email, address);
        this.name = name;
        this.registrationCode = registrationCode;
        this.bankAccount = bankAccount;
    }

    public LegalPerson(String email, String guid, Address address, String name, String registrationCode, String bankAccount) {
        super(email, guid, address);
        this.name = name;
        this.registrationCode = registrationCode;
        this.bankAccount = bankAccount;
    }

    // create from input
    public LegalPerson(Scanner scanner) {
        super(scanner);
        System.out.println("Please enter legal person info.");
        System.out.println("Name: ");
        this.name = scanner.nextLine();
        System.out.println("Registration code: ");
        this.registrationCode = scanner.nextLine();
        System.out.println("Bank account: ");
        this.bankAccount = scanner.nextLine();
    }

    public LegalPerson(Scanner scanner, Address address) {
        super(scanner, address);
        System.out.println("Please enter legal person info.");
        System.out.println("Name: ");
        this.name = scanner.nextLine();
        System.out.println("Registration code: ");
        this.registrationCode = scanner.nextLine();
        System.out.println("Bank account: ");
        this.bankAccount = scanner.nextLine();
    }

    @Override
    public String toString() {
        String simpleCustomerInfo = super.toString();
        return String.format("%s%s%s%s%s",
                simpleCustomerInfo,
                "Legal person information\n",
                "Name: " + this.name + "\n",
                "Registration code: " + this.registrationCode + "\n",
                "Bank account: " + this.bankAccount + "\n");
    }

    public String getName() {
        return this.name;
    }

    public String getRegistrationCode() {
        return this.registrationCode;
    }

    public String getBankAccount() {
        return bankAccount;
    }


}
