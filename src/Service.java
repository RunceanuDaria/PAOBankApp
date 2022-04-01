import Cards.*;
import Customers.*;
import Bank.*;
import java.util.Scanner;

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

    public Customer createSimpleCustomer(Scanner scanner) {return new Customer(scanner);}

    public NaturalPerson createNaturalPerson(Scanner scanner) {return new NaturalPerson(scanner);}

    public LegalPerson createLegalPerson(Scanner scanner) {return new LegalPerson(scanner);}

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
         else if (type.equals("mastercard"))
        {
            newCard = new MasterCard(scanner);
        }
        else throw new Exception("This type of card does not exist!");
        System.out.println(newCard.toString());
        return newCard;
    }

    // create account for a customer
    public Account createAccount(Customer customer, String iban, String status)
    {
        return new Account(customer, iban, status);

    }
    // add card to an account
    public void addCard(Account account, Card card)
    {
        if(card.getClass() == VisaCard.class)
        {

            account.addVisaCard((VisaCard)card);
        }
        else if(card.getClass() == MasterCard.class)
        {
            account.addMasterCard((MasterCard) card);
        }
        }

    // add assets to an account
    public void addAsset(Account account, Asset asset) {account.addAsset(asset);}

    // add transaction
    public void addTransaction(Account account, Transaction transaction) {account.addTransaction(transaction);}

    // display user information
    public void userInformation(Customer customer)
    {
        if(customer.getClass() == NaturalPerson.class)
        {
            System.out.println(((NaturalPerson)customer).toString());
        }
        else if (customer.getClass() == LegalPerson.class)
        {
            System.out.println(((LegalPerson)customer).toString());
        }

    }

    // display current amount of money
    public long currentAmount(Account account) {return account.getTotalAmount();}

    // return transactions
    public Transaction []  transactions(Account account) {return account.getTransactions();}

    // display transactions
    public void accountTransactions(Account account)
    {account.displayTransactions();
    }

    // display cards
    public void accountCards(Account account) {account.displayCards();}

    // display assets
    public void accountAssets(Account account) {account.displayAssets();}

    // display account information
    public void accountInformation(Account account) {System.out.println(account.toString());}

    // close account
    public void closeAccount(Account account) {account.setStatus("false");}

}
