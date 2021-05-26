import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *  This class generates all the text based stuff
 *
 *  @author Kyle He
 *  @version May 13, 2021
 */
public class Generator {
    private ArrayList<String> allNames;
    private ArrayList<String> allLastNames;
    private ArrayList<String> allAdjectives;

    private ArrayList<Ingredient> allDrinks;
    private ArrayList<Ingredient> allSauces;
    private ArrayList<Ingredient> allIngredients;

    public Generator(){
        allNames = loadFile("names");
        allLastNames = loadFile("last_names");
        allAdjectives = loadFile("adjective");

        allIngredients = loadIngredientFile("ingredients", Ingredient.Type.FOOD);
        allSauces = loadIngredientFile("sauces", Ingredient.Type.SAUCE);
        allDrinks = loadIngredientFile("drinks", Ingredient.Type.DRINK);
    }

    public Recipe generateRecipe(int level){
        Recipe recipe = new Recipe(generateRecipeName());
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

        // TODO: think of better way to handle bread
        // recipe.addIngredient(0, "bun");
        // recipe.addIngredient(recipe.getIngredientsAndSauces().size(), "bun");

        return recipe;
    }

    public String generateRecipeName(){
        String name = getRandom(allNames);
        String adjective = getRandom(allAdjectives);

        return String.format("%s's %s Burger", name, adjective);
    }

    public Order generateOrder(int level)
    {
        HashSet<Ingredient> sauces = new HashSet<>();
        for (Ingredient i: allSauces)
        {
            sauces.add(getRandom(allSauces));
        }
        Order o = new Order(generateCustomerName(), getRandom(allDrinks), sauces, generateRecipe(level));
        return o;
    }

    public String generateCustomerName(){
        return getRandom(allNames) + " " + getRandom(allLastNames);
    }

    public ArrayList<Ingredient> loadIngredientFile(String name, Ingredient.Type type){
        ArrayList<String> list = loadFile(name);
        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

        for (String s: list){
            ingredientList.add(new Ingredient(s, type));
        }

        return ingredientList;
    }

    public ArrayList<String> loadFile(String name){
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

    public ArrayList<Ingredient> getAllIngredients(){
        return allIngredients;
    }

    private <T> T getRandom(ArrayList<T> list){
       return list.get((int) (Math.random() * list.size()));
    }
}
