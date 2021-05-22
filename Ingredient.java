import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    private BufferedImage image;

    public Ingredient(String n, Type t){
        name = n;
        type = t;

        try
        {
            image = ImageIO.read(new File(getImagePath()));
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
        return imagePath + name.replaceAll(" ", "_") + ".png";
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

    public BufferedImage getImage(){
        return image;
    } 

    public String toString(){
        return name;
    }
}
