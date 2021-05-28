import java.util.ArrayList;

/**
 *  Represents a burger to which ingredients can be added.
 *
 *  @author Angela Jiao
 *  @author Kyle He
 *  @version April 29, 2021
 */
public class Recipe {

    private String name;
    private ArrayList<Ingredient> ingredients;
    private int cost;

    /**
     * Create a new Recipe object with one parameter.
     * @param name name of the recipe
     */
    public Recipe(String name){
        this.name = name;
        ingredients = new ArrayList<Ingredient>();
    }
    /**
     * Create a new Recipe object with no parameters.
     */
    public Recipe() {
        ingredients = new ArrayList<Ingredient>();
    }

//    /**
//     * Compares one Recipe to another that has more ingredients.
//     * @param r Recipe to compare to
//     * @return 1 if the Recipes are different, 0 if they are exactly the same,
//     * and -1 otherwise
//     */
//    public int compareTo(Recipe r)
//    {
//        for (int i = 0; i < ingredients.size(); i++)
//        {
//            if (!ingredients.get(i).toString().equals(r.ingredients.get(i).toString()))
//            {
//                return 1;
//            }
//        }
//        if (ingredients.size() == r.ingredients.size())
//        {
//            return 0;
//        }
//        return -1;
//    }

    @Override
    public boolean equals(Object o)
    {
        return ingredients.equals(((Recipe)o).getIngredients());
    }

    /**
     * {@inheritDoc}
     */
    public String toString(){
        String recipe = "Name: " + name;
        recipe += "\nIngredients: ";
        for (Ingredient s: ingredients)
        {
            recipe += s + " ";
        }
        return recipe;
    }

    /**
     * Adds a given Ingredient at a given index.
     * @param i index
     * @param s Ingredient to add
     */
    public void addIngredient(int i, Ingredient s){
        ingredients.add(i, s);
    }

    /**
     * Adds a given Ingredient to the end of the list.
     * @param s Ingredient to add
     */
    public void addIngredient(Ingredient s)
    {
        ingredients.add(s);
    }

    /**
     * Gets the ArrayList of ingredients.
     * @return ingredients
     */
    public ArrayList<Ingredient> getIngredients()
    {
        return ingredients;
    }

    /**
     * Gets the name of the burger.
     * @return ingredients
     */
    public String getName()
    {
        return name;
    }

    public int getCost()
    {
        for (Ingredient i: ingredients)
        {
            cost += i.getCost();
        }
        return cost;
    }
}
