import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Generator {
    private ArrayList<String> allNames;
    private ArrayList<String> allAdjectives;
    
    private ArrayList<Ingredient> allDrinks;
    private ArrayList<Ingredient> allSauces;
    private ArrayList<Ingredient> allIngredients;

    public Generator(){
        allNames = loadFile("names");
        allAdjectives = loadFile("adjective");

        allIngredients = loadIngredientFile("ingredients");
        allSauces = loadIngredientFile("sauces");
        allDrinks = loadIngredientFile("drinks");
    }

    public Recipe generateRecipe(){
        Recipe recipe = new Recipe(generateRecipeName());
        for (int i = 0; i < Math.max(allSauces.size(), allIngredients.size()); i++)
        {
            if (i < allIngredients.size() && Math.random() > 0.5)
            {
                recipe.addIngredient(0, allIngredients.get(i));
            }
            if (i < allSauces.size() && Math.random() > 0.5)
            {
                recipe.addIngredient(0, allSauces.get(i));
            }
        }

        // TODO: think of better way to handle bread
        // recipe.addIngredient(0, "bun");
        // recipe.addIngredient(recipe.getIngredientsAndSauces().size(), "bun");
        
        return recipe;
    }

    public String generateRecipeName(){
        String name = allNames.get((int) (Math.random() * allNames.size()));
        String adjective = allAdjectives.get((int) (Math.random() * allAdjectives.size()));

        return String.format("%s's %s Burger", name, adjective);
    }

    public Order generateOrder()
    {
        int i = (int) (Math.random()*allDrinks.size());
        Order o = new Order(allDrinks.get(i), generateRecipe());
        return o;
    }

    public ArrayList<Ingredient> loadIngredientFile(String name){
        ArrayList<String> list = loadFile(name);
        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

        for (String s: list){
            ingredientList.add(new Ingredient(s));
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
}
