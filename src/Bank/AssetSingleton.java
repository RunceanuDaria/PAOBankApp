package Bank;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssetSingleton {
    private static AssetSingleton unique_instance = null;

    private List <Asset> assets = new ArrayList<Asset>();

    public static AssetSingleton getInstance(){
        if(unique_instance == null){
            unique_instance = new AssetSingleton();
        }
        return unique_instance;
    }

    public List <Asset> getAssets(){
        return assets;
    }

    public void setAssets(List <Asset> assets){
        this.assets = assets;
    }

    private static List <String[]> getFromCSV(String file){
        List <String[]> data = new ArrayList<>();

        try(var inp = new BufferedReader(new FileReader(file))){
            String line;

            while((line = inp.readLine()) != null){
                String [] info = line.split(",");
                data.add(info);
            }}catch (IOException exp){
            System.out.println("Assets do not exist yet!");
        }
        return data;
    }

    public void loadFromCSV(){
        try {
            var data = AssetSingleton.getFromCSV("storage/assets.csv");
            for(var info: data){
                var asset = new Asset(
                        info[0],
                        Long.parseLong(info[1]),
                        UUID.fromString(info[2])

                );

                assets.add(asset);
            } } catch(Exception exp){
            System.out.println("Invalid format!");
        }
    }

    public void writeToCsv(){
        try{
            var writer = new FileWriter("storage/transactions.csv");
            for(var transaction: this.assets){
                writer.write(transaction.convertToCsv());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException exp){
            System.out.println(exp.toString());
        }
    }
}



