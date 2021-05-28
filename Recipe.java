import java.util.ArrayList;

/**
 * Represents a burger to which ingredients can be added.
 *
 * @author Angela Jiao
 * @author Kyle He
 * @version April 29, 2021
 */
public class Recipe
{

    private String                name;
    private ArrayList<Ingredient> ingredients;
    private int                   cost;

    /**
     * Create a new Recipe object with one parameter.
     *
     * @param name
     *            name of the recipe
     */
    public Recipe(String name)
    {
        this.name = name;
        ingredients = new ArrayList<Ingredient>();
    }


    /**
     * Create a new Recipe object with no parameters.
     */
    public Recipe()
    {
        ingredients = new ArrayList<Ingredient>();
    }


    @Override
    public boolean equals(Object o)
    {
        return ingredients.equals(((Recipe)o).getIngredients());
    }


    /**
     * {@inheritDoc}
     */
    public String toString()
    {
        String recipe = "Name: " + name;
        recipe += "\nIngredients: ";

        String delim = "";
        for (Ingredient i : ingredients)
        {
            recipe += delim + i;
            delim = ", ";
        }
        return recipe;
    }

    /**
     * Adds a given Ingredient to the end of the list.
     *
     * @param s
     *            Ingredient to add
     */
    public void addIngredient(Ingredient s)
    {
        ingredients.add(s);
    }


    /**
     * Gets the ArrayList of ingredients.
     *
     * @return ingredients
     */
    public ArrayList<Ingredient> getIngredients()
    {
        return ingredients;
    }


    /**
     * Gets the name of the burger.
     *
     * @return ingredients
     */
    public String getName()
    {
        return name;
    }


    /**
     * Gets the cost.
     *
     * @return cost
     */
    public int getCost()
    {
        for (Ingredient i : ingredients)
        {
            cost += i.getCost();
        }
        return cost;
    }
}
