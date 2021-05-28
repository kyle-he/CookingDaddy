import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Angela Jiao
 *  @version May 13, 2021
 */
public class CustomerHandler
{
    private HashSet<Order> pastOrders;
    private Queue<Customer> customerQueue;

    public CustomerHandler()
    {
        pastOrders = new HashSet<>();
        customerQueue = new LinkedList<>();
    }

    public void addCustomer(int level)
    {
        Order o = Generator.generateOrder(level);
        while (pastOrders.contains(o))
        {
            o = Generator.generateOrder(level);
        }
        pastOrders.add(o);
        Customer c = new Customer(o);
        customerQueue.add(c);
    }

    public Customer getCustomer()
    {
        return customerQueue.remove();
    }

    public String toString()
    {
        String output = "";
        for (Customer c: customerQueue)
        {
            output += c.getCustomerName() + ":\n" + c.getOrder() + "\n\n";
        }
        return output;
    }
}
