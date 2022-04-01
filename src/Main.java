import Bank.*;
import Cards.*;
import Customers.*;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    private static final String [] actions = {"1-create and display a customer", "2-create and display a card",
                                              "3-create account", "4-add new card to an account",
                                              "5-add asset to an account", "6-add transaction to an account",
                                              "7-display user information", "8-display current amount",
                                              "9-display account information", "10-display transaction",
                                              "11-close account", "12-exit"};

    private static void displayActions()
    {
        System.out.println("Press the number before each action to perform it!");
        for (String action : actions) {
            System.out.println(action);
        }

    }

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Service service = new Service();
        Main.displayActions();
        boolean stop = false;

        Customer[] customers = new Customer[10];
        Card[] cards = new Card[20];
        Transaction[] transactions = new Transaction[50];
        Account[] accounts = new Account[10];

       // variables use for store the returning data from functions into menu
        Customer customer;
        Card card;
        Account account;
        Transaction transaction;

        // create data to show actions into menu
        // create customers
        Customer testCustomer1 = new Customer("customer1@test.ro", new Address("Eroilor", "Bucuresti", "Romania", 95));
        Customer testCustomer2 = new NaturalPerson("customer2@test.ro", new Address("Iancu", "Iasi", "Romania", 78),
                "Mihai", "Dumitru", "1900117325689");
        Customer testCustomer3 = new LegalPerson("customer3@test.ro", new Address("Soarelui", "Cluj", "Romania", 100),
                "Adona.srl", "200105", "125789635789");

        // create cards
        Card testCard1 = new VisaCard(20,"Mihai", "24/01/2024", "RO01011415", 6000, "active");
        Card testCard2 = new MasterCard(200,"Alina", "14/01/2025","RO02050678", 899, "first");
        Card testCard3 = new MasterCard(400,"Mihai", "18/06/2023","RO00985623", 200, "second");

        // create accounts
        Account testAccount1 =  new Account(testCustomer2, "RO49AAAA1B31007593840000", "active");
        Account testAccount2 = new Account(testCustomer3, "DE89370400440532013000", "active");

        // create transaction
        Transaction testTransaction = new Transaction("RO49AAAA1B31007593840000","DE89370400440532013000",
                                                        2000, "Pay for products",
                                        new SimpleDateFormat("dd/MM/yyyy").parse("31/10/2021"));
        // create assets
        Asset testAsset1 = new Asset("watch", 10000, testCustomer2.getGuid());
        Asset testAsset2 =  new Asset("bracelet", 5000, testCustomer3.getGuid());


        // display actual actions and perform them in an interactive way
        while (!stop) {
            System.out.println("Press the specific number to perform an action.");
            System.out.println("Press 12 to exit.");
            int actionNumber = Integer.parseInt(scanner.nextLine());
            if (actionNumber < 1 || actionNumber > 15) {
                System.out.println("Invalid action number!");
                break;

            }
            switch (actionNumber) {
                case 1 -> customer = service.createCustomer();
                case 2 -> card = service.createCard();
                case 3 ->
                        {
                            System.out.println("Iban:");
                            String iban = scanner.nextLine();
                            System.out.println("Status:");
                            String status = scanner.nextLine();
                            account = service.createAccount(testCustomer1, iban, status);
                            System.out.println(account.toString());

                        }
                case 4 -> { service.addCard(testAccount1, testCard3); service.accountCards(testAccount1);}
                case 5 -> {service.addAsset(testAccount1, testAsset1); service.accountAssets(testAccount1);}
                case 6 -> {service.addTransaction(testAccount2, testTransaction); service.accountTransactions(testAccount2);}
                case 7 ->  service.userInformation(testCustomer2);
                case 8 ->  System.out.println(service.currentAmount(testAccount2));
                case 9 ->  service.accountInformation(testAccount2);
                case 10 -> service.accountTransactions(testAccount1);
                case 11 -> {service.closeAccount(testAccount1); System.out.println(testAccount1.getStatus());}
                case 12 -> stop = true;
            }
        }


    }
}
