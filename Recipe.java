import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<String> sauces;
    private ArrayList<String> ingredients;

    public Recipe(String name){
        this.name = name;
        sauces = new ArrayList<String>();
        ingredients = new ArrayList<String>();
    }

    public String toString(){
        String recipe = "Name: " + name;
        recipe += "\nSauces: ";
        for (String s: sauces){
            recipe += s + " ";
        }
        recipe += "\nIngredients: ";
        for (String i: ingredients){
            recipe += i + " ";
        }
        return recipe;
    }

    public void addSauce(String sauce){
        sauces.add(sauce);
    }

    public void addIngredient(String ingredient){
        ingredients.add(ingredient);
    }
}
