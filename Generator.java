package CookingDaddy;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Generator {
    private ArrayList<String> allNames;
    private ArrayList<String> allIngredients;
    private ArrayList<String> allSauces;
    private ArrayList<String> allAdjectives;
    private ArrayList<String> allDrinks;

    public Generator(){
        allNames = loadFile("names");
        allIngredients = loadFile("ingredients");
        allSauces = loadFile("sauces");
        allAdjectives = loadFile("adjective");
        allDrinks = loadFile("drinks");
    }

    public Recipe generateRecipe(){
        Recipe recipe = new Recipe(generateRecipeName());
        for (int i = 0; i < Math.max(allSauces.size(), allIngredients.size()); i++)
        {
            if (i < allIngredients.size() && Math.random() > 0.5)
            {
                recipe.addSomething(0, allIngredients.get(i));
            }
            if (i < allSauces.size() && Math.random() > 0.5)
            {
                recipe.addSomething(0, allSauces.get(i));
            }
        }
        recipe.addSomething(0, "bun");
        recipe.addSomething(recipe.getIngredientsAndSauces().size(), "bun");
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
