import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  Represents an individual customer.
 *
 *  @author Angela Jiao
 *  @author Kyle He
 *  @version May 13, 2021
 */
public class Customer
{
    private Order order;
    private String customerName;
//    private ArrayList<String> allCustomerNames;
    //use Generator instead?

    /**
     * Create a new Customer object from a given order.
     * @param o order
     */
    public Customer(Order o)
    {
        order = o;
        Generator g = new Generator();
        customerName = g.generateCustomerName();
//        allCustomerNames = loadFile("customers");
//        customerName = allCustomerNames.get((int)(Math.random() * allCustomerNames.size()));
    }

    /**
     * Gets the order.
     * @return order
     */
    public Order getOrder()
    {
        return order;
    }

    /**
     * Gets the name.
     * @return customerName
     */
    public String getCustomerName()
    {
        return customerName;
    }


//    public ArrayList<String> loadFile(String name){
//        ArrayList<String> list = new ArrayList<String>();
//        try {
//            try(BufferedReader br = new BufferedReader(new FileReader("constants/" + name + ".txt"))) {
//                String line = br.readLine();
//
//                while (line != null) {
//                    list.add(line);
//                    line = br.readLine();
//                }
//            }
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//        return list;
//    }
}
