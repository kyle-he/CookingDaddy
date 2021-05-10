/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Angela Jiao
 *  @version May 10, 2021
 */
public class Order
{
    private String drink;
    private Recipe recipe;

    public Order(String d, Recipe r)
    {
        drink = d;
        recipe = r;
    }

    public String toString()
    {
        return "Drink: " + drink + "\n" + recipe.toString();
    }
}
