package Cards;

import Customers.Address;

import java.util.Scanner;
import java.util.UUID;

public class MasterCard extends Card{

    private static long numberOfAtm = 1000000;
    private String worldEliteLevel;

    public MasterCard(int cardNumber,String ownerName, String closingDate, String iban, long amount,String worldEliteLevel)
    {
        super(cardNumber,ownerName,closingDate,iban,amount);
        this.worldEliteLevel = worldEliteLevel;
    }
    public MasterCard(Scanner scanner)
    {
        super(scanner);
        System.out.println("Please enter MasterCard info");
        System.out.println("World Elite level: ");
        this.worldEliteLevel = scanner.nextLine();
    }

    @Override
    public String toString()
    {
        String basicInformation = super.toString();
        return String.format("%s%s%s",
                basicInformation,
                "Number of ATM : " + numberOfAtm+ "\n",
                "Word Elite level: " + this.worldEliteLevel + "\n"
            );
    }

    public static long getNumberOfAtm() {return numberOfAtm;}
    public String getWorldEliteLevel(){return this.worldEliteLevel;}
}
