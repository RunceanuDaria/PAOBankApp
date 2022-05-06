package Bank;

import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Transaction {
    private  final String senderIban,  receiverIban;
    private final long amount;
    private final String description;
    private final Date transactionDate;


    public Transaction(String senderIban, String receiverIban, long amount, String description, Date transactionDate)
    {
        this.senderIban = senderIban;
        this.receiverIban = receiverIban;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString()
    {
        return  String.format("%s%s%s%s%s%s",
                "Transaction information\n",
                "Sender IBAN: " + this.senderIban + "\n",
                "Receiver IBAN: " + this.receiverIban + "\n",
                "Amount: " + this.amount + "\n",
                "Description: " + this.description + "\n",
                "Transaction date: " + this.transactionDate + "\n"

        );
    }
   //getters
    public String getSenderIban() {return this.senderIban;}

    public String getReceiverIban() {return this.receiverIban;}

    public long getAmount() {return this.amount;}

    public String getDescription(){return this.description;}
    public Date getTransactionDate() {return this.transactionDate;}
// for writing in CSV format
    public String convertToCsv(){
        return senderIban + "," +
               receiverIban + "," +
               amount + "," +
               description + "," +
               transactionDate;
    }
}
