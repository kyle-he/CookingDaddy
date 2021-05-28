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

    /**
     * Create a new Customer object from a given order.
     * @param o order
     */
    public Customer(Order o)
    {
        order = o;
        customerName = Generator.generateCustomerName();
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
}
