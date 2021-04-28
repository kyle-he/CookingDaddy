import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Generator {
    private ArrayList<String> allNames;
    private ArrayList<String> allIngredients;
    private ArrayList<String> allSauces;
    private ArrayList<String> allAdjectives;

    public Generator(){
        allNames = loadFile("names");
        allIngredients = loadFile("ingredients");
        allSauces = loadFile("sauces");
        allAdjectives = loadFile("adjective");
    }

    public Recipe generateRecipe(){
        Recipe recipe = new Recipe(generateRecipeName());
        for (String i: allIngredients){
            if (Math.random() > 0.5){
                recipe.addIngredient(i);
            }
        }
        for (String s: allSauces){
            if (Math.random() > 0.5){
                recipe.addSauce(s);
            }
        }
        return recipe;
    }

    public String generateRecipeName(){
        String name = allNames.get((int) (Math.random() * allNames.size()));
        String adjective = allAdjectives.get((int) (Math.random() * allAdjectives.size()));

        return String.format("%s's %s Burger", name, adjective);
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
