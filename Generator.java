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
    private static ArrayList<String> allAdjectives = loadFile("adjective");

    private static ArrayList<Ingredient> allDrinks = loadIngredientFile("drinks", Ingredient.Type.DRINK);
    private static ArrayList<Ingredient> allSauces = loadIngredientFile("sauces", Ingredient.Type.SAUCE);
    private static ArrayList<Ingredient> allIngredients = loadIngredientFile("ingredients", Ingredient.Type.FOOD);

    private Generator(){}

    /**
     * Generates a recipe at a given difficulty level. All recipes contain a top and bottom bun.
     * @param level difficulty level
     * @return recipe
     */
    public static Recipe generateRecipe(int level){
        Recipe recipe = new Recipe(generateRecipeName());
        recipe.addIngredient(new Ingredient("bun bottom", Ingredient.Type.FOOD));
        for (int i = 0; i < level * 2; i++)
        {
            recipe.addIngredient(getRandom(allIngredients));
        }
//        for (int i = 0; i < Math.max(allSauces.size(), allIngredients.size()); i++)
//        {
//            if (i < allIngredients.size() && Math.random() > 0.5)
//            {
//                recipe.addIngredient(0, allIngredients.get(i));
//            }
//            if (i < allSauces.size() && Math.random() > 0.5)
//            {
//                recipe.addIngredient(0, allSauces.get(i));
//            }
//        }

        recipe.addIngredient(new Ingredient("bun top", Ingredient.Type.FOOD));
        // TODO: think of better way to handle bread
        // recipe.addIngredient(0, "bun");
        // recipe.addIngredient(recipe.getIngredientsAndSauces().size(), "bun");

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
        HashSet<Ingredient> sauces = new HashSet<>();
        for (Ingredient i: allSauces)
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
            ingredientList.add(new Ingredient(s, type));
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
            System.out.println(ex);
        }
        return list;
    }

    /**
     * Gets allIngredients.
     * @return allIngredients
     */
    public static ArrayList<Ingredient> getAllIngredients(){
        return allIngredients;
    }

    private static <T> T getRandom(ArrayList<T> list){
       return list.get((int) (Math.random() * list.size()));
    }
}
