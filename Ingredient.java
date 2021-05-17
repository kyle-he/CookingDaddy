public class Ingredient {
    // image stuff
    private String name;
    private String type;

    public Ingredient(String n, String t){
        name = n;
        type = t;
    }

    public Ingredient(String t)
    {
        type = t;
    }

    public Ingredient()
    {
        type = "";
    }

    public void setType(String t)
    {
        type = t;
    }

    public String getType()
    {
        return type;
    }

    public String toString(){
        return name;
    }
}
