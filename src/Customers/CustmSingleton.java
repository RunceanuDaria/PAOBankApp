package Customers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;

public class CustmSingleton {
    private static CustmSingleton unique_instance = null;

    private List <Customer> customers = new ArrayList<Customer>();

    public static CustmSingleton getInstance(){
        if (unique_instance == null)
            unique_instance = new CustmSingleton();
        return unique_instance;
    }

    public List<Customer> getCustomers(){
        return customers;
    }

    public void setCustomers(List<Customer> customers){
        this.customers = customers;
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
            System.out.println("Customers do not exist yet!");
        }
        return data;
    }
// load fields from CSV
    public void loadFromCSV(){
        try{
            var data = CustmSingleton.getFromCSV("storage/customers.csv");
            for(var info : data){
        // primul field al CSV-ului pentru customers contine tipul acestui
        // customerType retine tipul clientului: NaturalPerson - 1, LegalPerson - 2
                int customerType = Integer.parseInt(info[0]);
                if(customerType == 1){
                    var customer = new NaturalPerson(
                            info[1],
                            new Address(info[2], info[3], info[4],Integer.parseInt(info[5])),
                            info[6],
                            info[7],
                            info[8]);
                    customers.add(customer);

                }
                else if(customerType == 2){
                    var customer = new LegalPerson(
                            info[0],
                            new Address(info[2], info[3], info[4],Integer.parseInt(info[5])),
                            info[6],
                            info[7],
                            info[8]);
                    customers.add(customer);
                }
            }
            } catch(ParseException excp){
                System.out.println(excp.toString());
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
// write in CSV file
    public void writeToCsv(){
        try{
            var writer = new FileWriter("storage/customers.csv");
            for(var customer: this.customers){
                writer.write(customer.convertToCsv());
                writer.write("\n");
            }
            writer.close();

        }catch (IOException exp){
            System.out.println(exp.toString());
        }
    }

    }

