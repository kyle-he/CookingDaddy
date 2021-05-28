/**
 *  Builds an Order according to a goal.
 *
 *  @author Angela Jiao
 *  @author Kyle He
 *  @version May 17, 2021
 */
public class OrderBuilder
{
    private Order current;
    private Order goal;
    private int index;
    private boolean drinkDone;

    /**
     * Create a new OrderBuilder object.
     * @param g goal Order
     */
    public OrderBuilder(Order g)
    {
        goal = g;
        current = new Order();
        index = 0;
    }

    /**
     * Adds a given Ingredient to the current Order if it is correct (matches
     * the goal).
     * @param i Ingredient to add
     * @return true if the added Ingredient is correct, false if not
     */
    public boolean build(Ingredient i)
    {
        if (i.getType() == Ingredient.Type.DRINK)
        {
            if (!drinkDone && i.equals(goal.getDrink()))
            {
                current.addDrink(i);
                drinkDone = true;
                return true;
            }
            return false;
        }
        if (i.getType() == Ingredient.Type.SAUCE)
        {
            if (goal.getSauces().contains(i) && !current.getSauces().contains(i))
            {
                current.addSauce(i);
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

    /**
     * Gets the index, which is where the next Ingredient will be added.
     * @return index
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * get current order
     * 
     * @return current
     */
    public Order getCurrent()
    {
        return current;
    }

    /**
     * get current goal
     * 
     * @return goal
     */
    public Order getGoal()
    {
        return goal;
    }
}
