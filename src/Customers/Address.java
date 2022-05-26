package Customers;

import java.util.Scanner;

public class Address {

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int ID;
    private String street;
    private String city;
    private String country;
    private int number;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getNumber() {
        return number;
    }


    public Address(String street, String city, String country, int number) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
    }

    // create from input
    public Address(Scanner scanner) {
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

    // method use for print
    @Override
    public String toString() {
        return String.format("%s%s%s%s",
                "Street: " + this.street + "\n",
                "City: " + this.city + "\n",
                "Country: " + this.country + "\n",
                "Number: " + this.number + "\n"
        );
    }

    public String convertToCsv() {
        return street + "," +
                city + "," +
                country + "," +
                number;
    }
}
