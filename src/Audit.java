import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Audit {
    FileWriter writer;
    final DateTimeFormatter date_format = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    public Audit(){
        try{
            this.writer = new FileWriter("storage/audit_actions.csv");
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    public void logAction(String action_name) throws IOException{
        writer.append(action_name);
        writer.append(",");
        writer.append(date_format.format(LocalDateTime.now()));
        writer.append("\n");
        writer.flush();

    }
}
