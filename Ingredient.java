import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *  Represents an ingredient.
 *
 *  @author Kyle He
 *  @author Angela Jiao
 *  @version May 13, 2021
 */
public class Ingredient {
    /**
     *  Represents a type of Ingredient.
     *
     *  @author Kyle He, Angela Jiao
     *  @version May 17, 2021
     */
    // image stuff
    public enum Type
    {
        /**
         * a drink
         */
        DRINK,
        /**
         * a food
         */
        FOOD,
        /**
         * a sauce
         */
        SAUCE
    }

    private Type type;
    private String name;
    private int cost;
    private BufferedImage image;
    private BufferedImage buttonImage;

    /**
     * Create a new Ingredient object from a given name and type.
     * @param n name
     * @param t type
     * @param c cost
     */
    public Ingredient(String n, Type t, int c){
        name = n;
        type = t;
        cost = c;

        try
        {
            image = ImageIO.read(new File(getImagePath()));
            buttonImage = ImageIO.read(new File(getIconImagePath()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String getImagePath(){
        String imagePath = "";
        switch(type){
            case DRINK:
                imagePath = "images/drink/drink_";
                break;
            case FOOD:
                imagePath = "images/food/food_";
                break;
            case SAUCE:
                imagePath = "images/sauce/sauce_";
                break;
        }
        return imagePath + name.toLowerCase().replaceAll(" ", "_") + ".png";
    }

    private String getIconImagePath(){
        String imagePath = "";
        switch(type){
            case DRINK:
                imagePath = "images/drink/drink_";
                break;
            case FOOD:
                imagePath = "images/icon/icon_";
                break;
            case SAUCE:
                imagePath = "images/sauce/sauce_";
                break;
        }
        return imagePath + name.toLowerCase().replaceAll(" ", "_") + ".png";
    }

    /**
     * Create a new Ingredient object from a given name.
     * @param n name
     */
    public Ingredient(String n)
    {
        name = n;
    }

    /**
     * Create a new Ingredient object from a given type.
     * @param t type
     */
    public Ingredient(Type t)
    {
        type = t;
    }

    /**
     * Sets the type to a given value.
     * @param t type to set to
     */
    public void setType(Type t)
    {
        type = t;
    }

    @Override
    public boolean equals(Object o)
    {
        if (name == null || o == null)
        {
            return false;
        }
        return name.equals(((Ingredient)o).getName());
    }

    /**
     * Gets the type.
     * @return type
     */
    public Type getType()
    {
        return type;
    }

    public String getName(){
        return name;
    }

    public int getCost()
    {
        return cost;
    }

    /**
     * Gets the associated image.
     * @return image
     */
    public BufferedImage getImage(){
        return image;
    }

    /**
     * Gets the associated button image.
     * @return image
     */
    public BufferedImage getButtonImage(){
        return buttonImage;
    }

    /**
     * {@inheritDoc}
     */
    public String toString(){
        return name;
    }
}