package Cards;

import Customers.Address;

import java.util.Scanner;
import java.util.UUID;

public class VisaCard extends Card{
    private static long numberOfAtm = 2000000;
    private String signatureStatus;

    public VisaCard(int cardNumber,String ownerName, String closingDate, String iban, long amount,String signatureStatus)
    {
        super(cardNumber,ownerName, closingDate, iban,amount);
        this.signatureStatus = signatureStatus;
    }
    public VisaCard(Scanner scanner)
    {
        super(scanner);
        System.out.println("Please enter Visa card info");
        System.out.println("Signature status: ");
        this.signatureStatus = scanner.nextLine();
     }

    @Override
    public String toString()
    {
        String basicInformation = super.toString();
        return String.format("%s%s%s",
                basicInformation,
                "Number of ATM: " + numberOfAtm+ "\n",
                "Signature status: " + this.signatureStatus + "\n"
        );
    }

    public static long getNumberOfAtm(){return numberOfAtm;}

    public String getSignatureStatus(){return this.signatureStatus;}


}
