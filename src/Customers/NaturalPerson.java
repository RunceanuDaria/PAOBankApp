package Customers;

import java.util.Scanner;

public class NaturalPerson extends Customer{

     private String firstName, lastName;
     private String cnp;

     public NaturalPerson ( String email,Address address, String firstName, String lastName, String cnp )throws Exception
     {
         super(email, address);
         if(cnp.length() != 13)
             throw new Exception("The given cnp has not the correct number of digits!");
         this.firstName = firstName;
         this.lastName = lastName;
         this.cnp = cnp;
     }
    public NaturalPerson(Scanner scanner)
    {
        super(scanner);
        System.out.println("Please enter natural person info.");
        System.out.println("Firstname: ");
        this.firstName = scanner.nextLine();
        System.out.println("Lastname: ");
        this.lastName = scanner.nextLine();
        System.out.println("cnp: ");
        this.cnp = scanner.nextLine();
    }

    @Override
    public String toString()
    {
        String simpleCustomerInfo = super.toString();
        return  String.format("%s%s%s%s%s",
                simpleCustomerInfo,
                "Natural person information.\n",
                "Firstname: " + this.firstName + "\n",
                "Lastname: " + this.lastName + "\n",
                "Cnp: " + this.cnp + "\n");
    }
// getters
    public String getFirstName() {return this.firstName;}

     public String getLastName() {return this.lastName;}

     public String getCnp() { return this.cnp;}
}
