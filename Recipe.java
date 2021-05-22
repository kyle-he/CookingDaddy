import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<Ingredient> ingredients;

    public Recipe(String name){
        this.name = name;
        ingredients = new ArrayList<Ingredient>();
    }

    public Recipe() {
        ingredients = new ArrayList<Ingredient>();
    }

    public String toString(){
        String recipe = "Name: " + name;
        recipe += "\nIngredients and Sauces: ";
        for (Ingredient s: ingredients)
        {
            recipe += s + " ";
        }
        return recipe;
    }

    public void addIngredient(int i, Ingredient s){
        ingredients.add(i, s);
    }

    public void addIngredient(Ingredient s)
    {
        ingredients.add(s);
    }

    public ArrayList<Ingredient> getIngredients()
    {
        return ingredients;
    }
}
