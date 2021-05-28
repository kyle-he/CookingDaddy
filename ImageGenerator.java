import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  This class generates all the image based stuff
 *
 *  @author Kyle He
 *  @version May 13, 2021
 */
public final class ImageGenerator {    
    public static BufferedImage generateFoodImage(Recipe recipe, int image_width, int image_height){
        BufferedImage foodImage = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = foodImage.createGraphics();

        int yValue = 0;
        
        for (Ingredient i: recipe.getIngredients()){
            if (i.getType() != Ingredient.Type.SAUCE){
                BufferedImage image = i.getImage();
                int height = image_width * image.getHeight()/image.getWidth();
                Image newImage = image.getScaledInstance(image_width, height, Image.SCALE_SMOOTH);
                graphics.drawImage(newImage, 0, (image_height - yValue) - height, null);
                yValue += height - 10;
            }
        }

        return foodImage;
    }

    public static BufferedImage generateSauceImage(Order order, int image_width, int image_height){
        BufferedImage sauceImage = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = sauceImage.createGraphics();

        int xValue = 0;
        for (Ingredient i: order.getSauces()){
            BufferedImage image = i.getImage();
            int width = image_height * image.getWidth()/image.getHeight();
            Image newImage = image.getScaledInstance(width, image_height, Image.SCALE_SMOOTH);
            
            graphics.drawImage(newImage, (image_width - xValue) - width, 0, null);
            xValue += width - 10;
        }
        return sauceImage;
    }

    public static BufferedImage generateDrinkImage(Order order, int image_width, int image_height){
        // not the best way to convert from buffered image to image, but the best without any external packages
        BufferedImage sauceImage = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = sauceImage.createGraphics();
        BufferedImage image = order.getDrink().getImage();
        Image newImage = image.getScaledInstance(image_height * image.getWidth()/image.getHeight(), image_height, Image.SCALE_SMOOTH);
        graphics.drawImage(newImage, 0, 0, null);
        return sauceImage;
    }
}
