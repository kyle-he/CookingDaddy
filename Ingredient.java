/**
 *  This class holds the ingredients
 *
 *  @author Kyle He, Angela Jiao
 *  @version May 13, 2021
 */
public class Ingredient {
    // image stuff
    public enum Type
    {
        DRINK, FOOD, SAUCE
    }

    private Type type;
    private String name;

    public Ingredient(String n, Type t){
        name = n;
        type = t;
    }

    public Ingredient(String n)
    {
        name = n;
    }

    public void setType(Type t)
    {
        type = t;
    }

    public Type getType()
    {
        return type;
    }

    public String toString(){
        return name;
    }
}
