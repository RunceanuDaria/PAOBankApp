package Bank;


import java.util.UUID;

public class Asset {

    private String name;
    private long value;
    private UUID customerGuid;

    public Asset(String name, long value, UUID customerGuid)
    {
        this.name = name;
        this.value = value;
        this.customerGuid = customerGuid;
    }

    public String getName(){return this.name;}
    public long getValue() {return this.value;}
// use for print
    @Override
    public String toString()
    {
        return String.format("%s%s%s%s",
                              "Asset information.\n",
                               "Name: " + this.name + "\n",
                               "Value: " + this.value + "\n",
                               "Customer ID: " + this.customerGuid + "\n");
    }


}
