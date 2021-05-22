/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Angela Jiao
 *  @version May 17, 2021
 */
public class OrderBuilder
{
    private Order current;
    private Order goal;
    private int index;

    public OrderBuilder(Order g)
    {
        goal = g;
        current = new Order();
        index = 0;
    }

    public boolean build(Ingredient i)
    {
        if (i.getType().equals("drink"))
        {
            if (i.equals(goal.getDrink()))
            {
                current.addDrink(i);
                return true;
            }
            return false;
        }
        if (i.equals(goal.getRecipe().getIngredients().get(index)))
        {
            current.getRecipe().addIngredient(i);
            index++;
            return true;
        }
        return false;
    }
}
