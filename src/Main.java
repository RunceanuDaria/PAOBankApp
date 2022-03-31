import Bank.*;
import Cards.*;
import Customers.*;
import java.util.Scanner;

public class Main {

    private static final String [] actions = {"1-create and display a customer", "2-create and display a card",
                                              "3-create account", "4-get cash or pay with a specific card",
                                              "5-add money for a specific user", "6-display user information",
                                              "7-display current amount of money", "8-display account information",
                                              "9-close account", "10-add card to account", "11-add asset to account",
                                              "12-display user transactions",
                                              "13-display top1 3 users (in terms of amount of money they own)",
                                              "14-display top2 3 users (in terms of number of transactions)",
                                              "15-exit"};

    private static void displayActions()
    {
        System.out.println("Press the number before each action to perform it!");
        for (String action : actions) {
            System.out.println(action);
        }

    }

  // create and add customer: simple, natural or legal person user
  // create and add customer_card : Visa, MasterCard
  // create account
  // get cash or pay with the card for a user
  // add money for a specific user
  // display user information
  // display user transactions
  // display current amount of money
  // display the last spent amount of money for a user
  // close account
  // delete user
  // display top1 3 users (in terms of amount of money they own)
  // display top2 3 users (in terms of number of transactions)
  // display  menu
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Service service = new Service();
        Main.displayActions();
        boolean stop = false;

        Customer[] customers = new Customer[10];
        Card[] cards = new Card[20];
        Transaction[] transactions = new Transaction[50];
        Account[] accounts = new Account[10];
        int numberOfCustomers = 0;
        int numberOfCards = 0;
        int numberOfTransactions = 0;
        int numberOfAccounts = 0;

        while (!stop) {
            System.out.println("Press the specific number to perform an action.");
            System.out.println("Press 14 to exit.");
            int actionNumber = Integer.parseInt(scanner.nextLine());
            if (actionNumber < 1 || actionNumber > 15) {
                System.out.println("Invalid action number!");
                break;

            }
            switch (actionNumber) {
                case 1 -> customers[numberOfCustomers++] = service.createCustomer();
                case 2 -> cards[numberOfCards++] = service.createCard();
                /*case 3 ->
                        {   System.out.println("Choose the customer index for which create account");
                            // we suppose that the index exists in the customers array
                            int customerIndex = Integer.parseInt(scanner.nextLine());
                            String iban = scanner.nextLine();
                            String status = scanner.nextLine();
                            accounts[numberOfAccounts++] = service.createAccount(customers[customerIndex], iban, status);
                        }
                /*case 4 ->;
                case 5 -> ;
                case 6 ->;
                case 7 ->;
                case 8 ->;
                case 9 ->;
                case 10 ->;
                case 11 ->;
                case 12 ->;
                case 13->;*/
                case 15 -> stop = true;
            }
            ;
        }




    /*
        Customer custom = new Customer(scanner);
        String custom2 = custom.toString();
        System.out.println(custom2);

        Customer [] customers =  new Customer[2];
       customers[0] = new Customer("customer0@test.ro", new Address("Eroilor", "Bucuresti", "Romania", 95));
       customers[1] = new Customer("customer1@test.ro", new Address("Eroilor", "Bucuresti", "Romania", 95));
       System.out.println(customers[0].getGuid());
       System.out.println(customers[1].getGuid());*/

      /* Card [] cards = new Card[2];
       cards[0] = new Card(20,"Dacian", "24/01/2024", "RO01011415", 6000);
       cards[1] = new Card(200,"Alina", "14/01/2025","RO02050678", 899);

       System.out.println(cards[0].toString());
       System.out.println(cards[1].toString());*/
        /*Scanner scanner = new Scanner(System.in);
        Service service = new Service();
        service.createCustomer("simple", scanner);*/

        //


        // }

    }
}
