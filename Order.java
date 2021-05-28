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
    private int cost;

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
        int sauceCost = 0;
        for (Ingredient i: sauces)
        {
            i.setType(Ingredient.Type.SAUCE);
        }
        recipe = r;
        cost = calculateCost();
        cost = (int)(cost * 1.5);
    }

//    public Order(Order o)
//    {
//        drink = o.getDrink();
//        sauces = new HashSet<Ingredient>(o.getSauces());
//        recipe = o.getRecipe();
//        cost = o.getCost();
//    }

    /**
     * Create a new Order object with no parameters.
     */
    public Order()
    {
        drink = new Ingredient(Ingredient.Type.DRINK);
        sauces = new HashSet<Ingredient>();
        recipe = new Recipe();
    }

    @Override
    public boolean equals(Object obj)
    {
        Order o = (Order)obj;
        if (recipe == null || drink == null || sauces == null)
        {
            return false;
        }
        // if (recipe.equals(o.getRecipe())){
        //     System.out.println("Burger is done!");
        // }
        // if (drink.equals(o.getDrink())){
        //     System.out.println("Drink is done!");
        // }
        // if (sauces.equals(o.getSauces())){
        //     System.out.println("Sauce is done!");
        // }
        return recipe.equals(o.getRecipe()) && drink.equals(o.getDrink()) && sauces.equals(o.getSauces());
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

//    public void removeDrink()
//    {
//        drink = null;
//    }

    /**
     * Adds a sauce.
     * @param s sauce to add
     */
    public void addSauce(Ingredient s)
    {
        s.setType(Ingredient.Type.SAUCE);
        sauces.add(s);
    }

//    public void removeSauce(Ingredient s)
//    {
//        sauces.remove(s);
//    }

    public int calculateCost()
    {
        int sauceCost = 0;
        for (Ingredient i: sauces)
        {
            sauceCost += i.getCost();
        }
        return recipe.getCost() + drink.getCost() + sauceCost;
    }

    public int getCost()
    {
        return cost;
    }
}
