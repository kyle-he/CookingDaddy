import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Angela Jiao
 *  @version May 13, 2021
 */
public class Customer
{
    private Order order;
    private String customerName;
    private ArrayList<String> allCustomerNames;

    public Customer(Order o)
    {
        order = o;
        allCustomerNames = loadFile("customers");
        customerName = allCustomerNames.get((int)(Math.random() * allCustomerNames.size()));
    }

    public Order getOrder()
    {
        return order;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public ArrayList<String> loadFile(String name){
        ArrayList<String> list = new ArrayList<String>();
        try {
            try(BufferedReader br = new BufferedReader(new FileReader("constants/" + name + ".txt"))) {
                String line = br.readLine();

                while (line != null) {
                    list.add(line);
                    line = br.readLine();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return list;
    }
}
