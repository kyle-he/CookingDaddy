import java.util.HashSet;

/**
 * Represents an order consisting of a burger, a drink, and sauces.
 *
 *  @author Angela Jiao
 *  @version May 10, 2021
 */
public class Order
{
    private Ingredient drink;
    private HashSet<Ingredient> sauces;
    private Recipe recipe;

    /**
     * Create a new Order object with 3 parameters.
     * @param d drink
     * @param s sauces
     * @param r recipe
     */
    public Order(Ingredient d, HashSet<Ingredient> s, Recipe r)
    {
        drink = d;
        drink.setType(Ingredient.Type.DRINK);
        sauces = s;
        for (Ingredient i: sauces)
        {
            i.setType(Ingredient.Type.SAUCE);
        }
        recipe = r;
    }

    /**
     * Create a new Order object with no parameters.
     */
    public Order()
    {
        drink = new Ingredient(Ingredient.Type.DRINK);
        sauces = new HashSet<>();
        recipe = new Recipe();
    }

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
        String output = recipe.toString() + "\nDrink: " + drink + "\nSauces: ";
        for (Ingredient i: sauces)
        {
            output += i + " ";
        }
        return output;
    }

    /**
     * Gets the drink.
     * @return drink
     */
    public Ingredient getDrink()
    {
        return drink;
    }

    /**
     * Gets the set of sauces.
     * @return sauces
     */
    public HashSet<Ingredient> getSauces()
    {
        return sauces;
    }

    /**
     * Gets the recipe.
     * @return recipe
     */
    public Recipe getRecipe()
    {
        return recipe;
    }

    /**
     * Adds a drink.
     * @param d drink to add
     */
    public void addDrink(Ingredient d)
    {
        drink = d;
        drink.setType(Ingredient.Type.DRINK);
    }
    /**
     * Adds a sauce.
     * @param s sauce to add
     */
    public void addSauce(Ingredient s)
    {
        s.setType(Ingredient.Type.SAUCE);
        sauces.add(s);
    }
}
