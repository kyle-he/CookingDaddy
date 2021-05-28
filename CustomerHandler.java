import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Stores Customers in a queue.
 *
 * @author Angela Jiao
 * @author Kyle He
 * @version May 13, 2021
 */
public class CustomerHandler
{
    private HashSet<Order>  pastOrders;
    private Queue<Customer> customerQueue;

    /**
     * Create a new CustomerHandler object.
     */
    public CustomerHandler()
    {
        pastOrders = new HashSet<>();
        customerQueue = new LinkedList<>();
    }


    /**
     * Adds a Customer at a given level
     *
     * @param level
     *            level of Customer
     */
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


    /**
     * Gets a Customer from the front of the queue
     *
     * @return Customer
     */
    public Customer getCustomer()
    {
        return customerQueue.remove();
    }


    @Override
    public String toString()
    {
        String output = "";
        for (Customer c : customerQueue)
        {
            output += c.getCustomerName() + ":\n" + c.getOrder() + "\n\n";
        }
        return output;
    }
}
