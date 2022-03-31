import Cards.Card;
import Cards.MasterCard;
import Cards.VisaCard;
import Customers.Customer;
import Customers.LegalPerson;
import Bank.*;
import Customers.NaturalPerson;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Service {
    // create customers

    public Customer createCustomer() throws Exception{
        System.out.println("Choose one of the above type of customers:  simple, natural, legal.");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();

        Customer customer;
        switch (type) {
            case "simple" -> {
                customer = createSimpleCustomer(scanner);
                System.out.println(customer.toString());
            }
            case "natural" -> {
                customer = createNaturalPerson(scanner);
                System.out.println(customer.toString());

            }
            case "legal" -> {
                customer = createLegalPerson(scanner);
                System.out.println(customer.toString());

            }
            default -> throw new Exception("This type of customers does not exist!");
        }

        return customer;
    }

    public Customer createSimpleCustomer(Scanner scanner)
    {
        return new Customer(scanner);
    }

    public NaturalPerson createNaturalPerson(Scanner scanner)
    {
        return new NaturalPerson(scanner);
    }

    public LegalPerson createLegalPerson(Scanner scanner)
    {
        return new LegalPerson(scanner);
    }



    // create cards : Visa, MasterCard
    public Card createCard()throws Exception
    {
        System.out.println("Choose one of the type: visa, mastercard.");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        Card newCard ;
        if(type.equals("visa"))
        {
            newCard = new VisaCard(scanner);

        }
         else if (type.equals("master"))
        {
            newCard = new MasterCard(scanner);
        }
        else throw new Exception("This type of card does not exist!");
        System.out.println(newCard.toString());
        return newCard;
    }

    // create account for a specific customer
    public Account createAccount(Customer customer, String iban, String status)
    {
        System.out.println("Create an account");
        return new Account(customer, iban, status);

    }

    // get cash or pay with the card for a user
    // add money to an account
    public void addMoney(Account account, long amount, String cardNumber)
    {


    }
    // display user information
    public void userInformation()
    {

    }
    // display user transactions
    // display current amount of money
    // display the last spent amount of money for a user
    // close account
    public void closeAccount(Account account)
    {
        account.setStatus("false");
    }
    // delete user
    // display top1 3 users (in terms of amount of money they own)
    public void top3Amount(Account[] accounts)
    {
        int numberOfAccounts = accounts.length;
        long [] amounts = new long[numberOfAccounts];
        for(int i = 0; i< numberOfAccounts; i++)
        {
            amounts[i] = accounts[i].getTotalAmount();
        }

        Arrays.sort(amounts);


    }
    // display top2 3 users (in terms of number of transactions)
    public void top3Transactions()
    {

    }
}
