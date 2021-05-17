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
    private Ingredient drink;
    private Recipe recipe;

    public Order(Ingredient d, Recipe r)
    {
        drink = d;
        drink.setType("drink");
        recipe = r;
    }

    public Order()
    {
        drink = new Ingredient("drink");
        recipe = new Recipe();
    }

    public String toString()
    {
        return recipe.toString() + "\nDrink: " + drink;
    }

    public Ingredient getDrink()
    {
        return drink;
    }

    public Recipe getRecipe()
    {
        return recipe;
    }

    public void addDrink(Ingredient d)
    {
        drink = d;
        drink.setType("drink");
    }
}
