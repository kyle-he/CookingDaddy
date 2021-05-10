package CookingDaddy;
import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<String> ingredientsAndSauces;

    public Recipe(String name){
        this.name = name;
        ingredientsAndSauces = new ArrayList<String>();
    }

    public String toString(){
        String recipe = "Name: " + name;
        recipe += "\nIngredients and Sauces: ";
        for (String s: ingredientsAndSauces)
        {
            recipe += s + " ";
        }
        return recipe;
    }

    public void addSomething(int i, String s){
        ingredientsAndSauces.add(i, s);
    }

    public ArrayList<String> getIngredientsAndSauces()
    {
        return ingredientsAndSauces;
    }
}
