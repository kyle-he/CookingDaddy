import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *  This class generates all the image based stuff
 *
 *  @author Kyle He
 *  @version May 13, 2021
 */
public class ImageGenerator {
    private ArrayList<Ingredient> ingredients;

    public ImageGenerator(){

    } 
    
    public BufferedImage foodImage(Recipe r){
        BufferedImage newimg = new BufferedImage(200,100,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics =newimg.createGraphics();
        graphics.fillOval(20,20,40,40);
        return newimg;
    }
}
