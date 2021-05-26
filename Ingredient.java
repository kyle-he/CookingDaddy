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
    private BufferedImage image;

    /**
     * Create a new Ingredient object from a given name and type.
     * @param n name
     * @param t type
     */
    public Ingredient(String n, Type t){
        name = n;
        type = t;

        try
        {
            image = ImageIO.read(new File(getImagePath()));
        }
        catch (IOException e)
        {
            System.out.println(getImagePath());
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
        return imagePath + name.replaceAll(" ", "_") + ".png";
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

    /**
     * Gets the type.
     * @return type
     */
    public Type getType()
    {
        return type;
    }

    /**
     * Gets the associated image.
     * @return image
     */
    public BufferedImage getImage(){
        return image;
    }

    /**
     * {@inheritDoc}
     */
    public String toString(){
        return name;
    }
}
