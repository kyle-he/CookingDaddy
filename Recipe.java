import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<Ingredient> ingredientsAndSauces;

    public Recipe(String name){
        this.name = name;
        ingredientsAndSauces = new ArrayList<Ingredient>();
    }
    public Recipe() {
        ingredientsAndSauces = new ArrayList<Ingredient>();
    }

    public String toString(){
        String recipe = "Name: " + name;
        recipe += "\nIngredients and Sauces: ";
        for (Ingredient s: ingredientsAndSauces)
        {
            recipe += s + " ";
        }
        return recipe;
    }

    public void addIngredient(int i, Ingredient s){
        ingredientsAndSauces.add(i, s);
    }

    public void addIngredient(Ingredient s)
    {
        ingredientsAndSauces.add(s);
    }

    public ArrayList<Ingredient> getIngredientsAndSauces()
    {
        return ingredientsAndSauces;
    }
}
