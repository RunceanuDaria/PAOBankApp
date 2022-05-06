package Bank;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;

public class TransSingleton {
    private static TransSingleton unique_instance = null;

    private List <Transaction> transactions = new ArrayList<Transaction>();

    public static TransSingleton getInstance(){
        if(unique_instance == null){
            unique_instance = new TransSingleton();
        }
        return unique_instance;
    }

    public List <Transaction> getTransactions(){
        return transactions;
    }

    public void setTransactions(List <Transaction> transactions){
        this.transactions = transactions;
    }

    private static List <String[]> getFromCSV(String file){
        List <String[]> data = new ArrayList<>();

        try(var inp = new BufferedReader(new FileReader(file))){
            String line;

            while((line = inp.readLine()) != null){
                String [] info = line.split(",");
                data.add(info);
            }}catch (IOException exp){
                System.out.println("Transactions do not exist yet!");
            }
            return data;
        }

    public void loadFromCSV(){
        try {
            var data = TransSingleton.getFromCSV("storage/transactions.csv");
            for(var info: data){
                var transaction = new Transaction(
                        info[0],
                        info[1],
                        Long.parseLong(info[2]),
                        info[3],
                        new SimpleDateFormat("dd/MM/yyyy").parse(info[4])
                );

                transactions.add(transaction);
            } } catch (ParseException exp){
                System.out.println("Cannot load! - parse exception");
                }
                catch(Exception e){
                    System.out.println("Invalid format!");
                }
    }

    public void writeToCsv(){
        try{
            var writer = new FileWriter("storage/transactions.csv");
            for(var transaction: this.transactions){
                writer.write(transaction.convertToCsv());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException exp){
            System.out.println(exp.toString());
        }
    }
}



