package Bank;

import Customers.Address;
import Customers.Customer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AccSingleton  {
    private static AccSingleton unique_instance = null;

    private List <Account> accounts = new ArrayList<Account>();

    public static AccSingleton getInstance(){
        if (unique_instance == null)
            unique_instance = new AccSingleton();
        return unique_instance;
    }

    public List<Account> getAccounts(){
        return accounts;
    }

    public void setAccounts(List<Account> accounts){
        this.accounts = accounts;
    }

    private static List<String[]> getFromCSV(String file){
        List<String[]> data = new ArrayList<>();
        try(var inp = new BufferedReader(new FileReader(file))){

            String line;
            while((line = inp.readLine()) != null){
                String [] info = line.split(",");
                data.add(info);
            }
        }catch(IOException exp){
            System.out.println("Accounts do not exist yet!");
        }
        return data;
    }

    public void loadFromCSV(){

            var data = AccSingleton.getFromCSV("storage/accounts.csv");
            for(var info : data){
                var account = new Account(
                        new Customer(info[0], new Address(info[1], info[2],info[3], Integer.parseInt(info[4]))),
                        info[5],
                        info[6]);
                accounts.add(account);
            }
    }
// write in CSV format
public void writeToCsv(){
        try{
            var writer = new FileWriter("storage/accounts.csv");
            for(var account: this.accounts){
                writer.write(account.convertToCsv());
                writer.write("\n");
            }
            writer.close();
        }catch(IOException exp){
            System.out.println(exp.toString());
        }
}

}
