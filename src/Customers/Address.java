package Customers;

import java.util.Scanner;

public class Address {

    private String street, city, country;
    private int number;

    public Address(String street, String city, String country, int number)
    {
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
    }

    public Address(Scanner scanner)
    {
        System.out.println("Please enter the address");
        System.out.println("Street: ");
        this.street = scanner.nextLine();
        System.out.println("City: ");
        this.city = scanner.nextLine();
        System.out.println("Country: ");
        this.country = scanner.nextLine();
        System.out.println("Number: ");
        this.number = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String toString()
    {
        return String.format("%s%s%s%s",
                            "Street: " + this.street + "\n",
                            "City: " + this.city + "\n",
                            "Country: " + this.country + "\n",
                            "Number: " + this.number + "\n"
                            );
    }
}
