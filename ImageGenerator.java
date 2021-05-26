import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;

/**
 *  This class generates all the image based stuff
 *
 *  @author Kyle He
 *  @version May 13, 2021
 */
public class ImageGenerator {
    // public ImageGenerator(){
        
    // } 
    
    public BufferedImage generateFoodImage(Recipe recipe, int width){
        width -= 30;
        BufferedImage foodImage = new BufferedImage(width, width,BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = foodImage.createGraphics();
        int yValue = 0;
        for (Ingredient i: recipe.getIngredients()){
            if (i.getType() != Ingredient.Type.SAUCE){
                BufferedImage image = i.getImage();
                int height = width * image.getHeight()/image.getWidth();
                Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                graphics.drawImage(newImage, 0, yValue, null);
                yValue += height;
            }
        }
        return foodImage;
    }
}
