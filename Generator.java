import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *  Static class that stores methods for generating various components.
 *
 *  @author Kyle He
 *  @author Angela Jiao
 *  @version May 13, 2021
 */
public final class Generator {
    private static ArrayList<String> allNames = loadFile("names");
    private static ArrayList<String> allLastNames = loadFile("last_names");
    private static ArrayList<String> allPlaceNames = loadFile("place_names");
    private static ArrayList<String> allAdjectives = loadFile("adjective");

    private static ArrayList<Ingredient> allDrinks = loadIngredientFile("drinks", Ingredient.Type.DRINK);
    private static ArrayList<Ingredient> allSauces = loadIngredientFile("sauces", Ingredient.Type.SAUCE);
    private static ArrayList<Ingredient> allIngredients = loadIngredientFile("ingredients", Ingredient.Type.FOOD);

    /**
     * Generates a recipe at a given difficulty level. All recipes contain a top and bottom bun.
     * @param level difficulty level
     * @return recipe
     */
    public static Recipe generateRecipe(int level){
        Recipe recipe = new Recipe(generateRecipeName());
        recipe.addIngredient(new Ingredient("Bun Bottom", Ingredient.Type.FOOD, 1));
        for (int i = 0; i < Math.min(level * 3, 8); i++)
        {
            Ingredient ingredient;
            do {
                ingredient = getRandom(allIngredients);
            } while (ingredient.getName().equals("Bun Top") || ingredient.getName().equals("Bun Bottom"));

            recipe.addIngredient(ingredient);
        }

        recipe.addIngredient(new Ingredient("Bun Top", Ingredient.Type.FOOD, 1));

        return recipe;
    }

    /**
     * Generates a recipe name.
     * @return a recipe name
     */
    public static String generateRecipeName(){
        String name = getRandom(allNames);
        String adjective = getRandom(allAdjectives);

        return String.format("%s's %s Burger", name, adjective);
    }

    /**
     * Generates an order at a given difficulty level with a drink, a set of sauces, and a recipe.
     * @param level difficulty level
     * @return order
     */
    public static Order generateOrder(int level)
    {
        HashSet<Ingredient> sauces = new HashSet<Ingredient>();
        for (int i = 0; i < Math.min(level*2, 4); i++)
        {
            sauces.add(getRandom(allSauces));
        }
        Order o = new Order(getRandom(allDrinks), sauces, generateRecipe(level));
        return o;
    }

    /**
     * Generates a customer name (first and last).
     * @return customer name
     */
    public static String generateCustomerName(){
        return getRandom(allNames) + " " + getRandom(allLastNames);
    }

    /**
     * Loads a given ingredient file into an ArrayList.
     * @param name name of file
     * @param type ingredient type
     * @return ArrayList of Ingredients containing the contents of the file
     */
    public static ArrayList<Ingredient> loadIngredientFile(String name, Ingredient.Type type){
        ArrayList<String> list = loadFile(name);
        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

        for (String s: list){
            int c = 3;
            if (s.length() < 5)
            {
                c = 1;
            }
            else if (s.length() < 10)
            {
                c = 2;
            }
            ingredientList.add(new Ingredient(s, type, c));
        }

        return ingredientList;
    }

    /**
     * Loads a given file into an ArrayList.
     * @param name name of file
     * @return ArrayList of Strings containing the contents of the file
     */
    public static ArrayList<String> loadFile(String name){
        ArrayList<String> list = new ArrayList<String>();
        try {
            try(BufferedReader br = new BufferedReader(new FileReader("constants/" + name + ".txt"))) {
                String line = br.readLine();

                while (line != null) {
                    list.add(line);
                    line = br.readLine();
                }
            }
        } catch (IOException ex) {
        }
        return list;
    }

    /**
     * Gets corresponding location for each level
     * @return location name
     */
    public static String getLocation(int level){
        return allPlaceNames.get(Math.min(level, allPlaceNames.size()-1));
    }

    /**
     * Gets allIngredients.
     * @return allIngredients
     */
    public static ArrayList<Ingredient> getAllIngredients(){
        return allIngredients;
    }

    /**
     * Gets allSauces.
     * @return allSauces
     */
    public static ArrayList<Ingredient> getAllSauces(){
        return allSauces;
    }

    /**
     * Gets allDrinks.
     * @return allDrinks
     */
    public static ArrayList<Ingredient> getAllDrinks(){
        return allDrinks;
    }

    private static <T> T getRandom(ArrayList<T> list){
       return list.get((int) (Math.random() * list.size()));
    }
}
