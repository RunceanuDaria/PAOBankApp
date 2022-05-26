package Bank;

import Cards.Card;
import Cards.MasterCard;
import Cards.VisaCard;
import Customers.Customer;
import java.util.UUID;

public class Account {
    protected UUID customerGuid;
    protected String iban;
    protected String status;
    protected long totalAmount;
    protected Asset[] assets = new Asset[50];
    protected Card[] cards = new Card[50];
    protected Transaction[] transactions = new Transaction[100];
    protected int numberOfCards = 0;
    protected int numberOfAssets = 0;
    protected int numberOfTransactions = 0;

// an account does not exist without a customer
    public Account(Customer customer, String iban, String status) {
        this.customerGuid = customer.getCustomerGuid();
        this.iban = iban;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s%s%s",
                "Account information\n",
                "Customer ID: " + this.customerGuid + "\n",
                "IBAN: " + this.iban + "\n",
                "Account status: " + this.status + "\n",
                "Amount: " + this.totalAmount + "\n"
        );
    }

    // getters
    public UUID getCustomerGuid() {return this.customerGuid;}

    public String getIban() {return this.iban;}

    public String getStatus() {return this.status;}

    public long getTotalAmount() {return this.totalAmount;}
    public int getNumberOfCards() {return this.numberOfCards;}

    public int getNumberOfAssets() {return this.numberOfAssets;}

    public int getNumberOfTransactions() {return this.numberOfTransactions;}

    public Asset[] getAssets() {
        Asset[] accountAssets = new Asset[this.getNumberOfAssets()];
        for (int i = 0; i < accountAssets.length; i++)
            accountAssets[i] = this.assets[i];
        return accountAssets;
    }
    public void displayAssets()
    {
        for(int i = 0; i<numberOfAssets; i++)
        {
            System.out.println(this.assets[i].toString());
        }
    }

    public Card[] getCards() {
        Card[] accountCards = new Card[this.getNumberOfCards()];
        for (int i = 0; i < accountCards.length; i++)
            accountCards[i] = this.cards[i];
        return accountCards;
    }
    public void displayCards()
    {
        for(int i = 0; i<this.numberOfCards;i++)
        {
            System.out.println(this.cards[i].toString());
        }
    }
    // transactions
    public Transaction[] getTransactions() {
        Transaction[] accountTransactions = new Transaction[this.numberOfTransactions];
        for (int i = 0; i < accountTransactions.length; i++)
            accountTransactions[i] = this.transactions[i];
        return accountTransactions;
    }

    // set the status of the account
    public void setStatus(String actualStatus) {
        this.status = actualStatus;
    }


    public void addMasterCard(MasterCard masterCard) {
        this.cards[this.getNumberOfCards()] = masterCard; //add card to the cards array
        this.totalAmount += masterCard.getAmount(); // actualize the total_amount with the amount of new added card
        this.numberOfCards++;

    }

    public void addVisaCard(VisaCard visaCard) {
        this.cards[this.getNumberOfCards()] = visaCard;
        this.totalAmount += visaCard.getAmount();
        this.numberOfCards++;
    }

    public void addAsset(Asset asset) {
        this.assets[this.numberOfAssets] = asset; // add asset to the asset array
        this.totalAmount += asset.getValue(); // actualize the total_amount with the value of new added asset
        this.numberOfAssets++;
    }


    public void addTransaction(Transaction transaction)
    {
        this.transactions[this.numberOfTransactions] = transaction; //add card to the cards array
        this.totalAmount += transaction.getAmount(); // actualize the total_amount with the amount of new added card
        this.numberOfTransactions++;

    }

    public void displayTransactions()
    {
        for(int i = 0; i<this.numberOfTransactions;i++)
        {
            System.out.println(this.transactions[i].toString());
        }
    }
// for write into CSV format
public String convertToCsv(){
        return customerGuid + "," +
               iban + "," +
               status;
}
}
