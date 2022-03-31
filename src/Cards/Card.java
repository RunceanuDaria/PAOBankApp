package Cards;
import Customers.Address;

import java.util.Scanner;
import java.util.UUID;

public class Card {
    private int cardNumber;
    private int code;
    private String ownerName;
    private final String closingDate;
    private String iban;
    private long amount;


    public Card(int cardNumber, String ownerName, String closingDate, String iban, long amount)
    {
        this.cardNumber = cardNumber;
        this.code = code;
        this.ownerName = ownerName;
        this.closingDate = closingDate;
        this.iban = iban;
        this.amount = amount;
    }
    public Card(Scanner scanner)
    {
        System.out.println("Please enter basic card info");
        System.out.println("Card number: ");
        this.cardNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Code: ");
        this.code = Integer.parseInt(scanner.nextLine());
        System.out.println("Closing date: ");
        this.closingDate = scanner.nextLine();
        System.out.println("IBAN: ");
        this.iban = scanner.nextLine();
        System.out.println("Amount: ");
        this.amount = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String toString()
    {
        return String.format("%s%s%s%s%s%s%s",
                "Card information\n",
                "Card number: " + cardNumber+ "\n",
                "Code: " + this.code + "\n",
                "Owner name: " + this.ownerName + "\n",
                "Closing date: " + this.closingDate + "\n",
                "IBAN: " + this.iban + "\n",
                "Amount: " + this.amount + "\n"

        );
    }

    public int getCode(){return this.code;}
    public String getOwnerName(){return this.ownerName;}
    public String getClosingDate(){return this.closingDate;}
    public String getIban(){return this.iban;}
    public long getAmount(){return this.amount;}

    private void setAmount(long amount){ this.amount += amount;}

}
