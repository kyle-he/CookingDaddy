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
    public static BufferedImage generateFoodImage(Recipe recipe, int imageWidth, int imageHeight){
        
        int burgerHeight = Math.max(getTotalHeight(recipe.getIngredients(), imageWidth), imageHeight);
        BufferedImage foodImage = new BufferedImage(imageWidth, burgerHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = foodImage.createGraphics();

        int yValue = 0;
        
        for (Ingredient i: recipe.getIngredients()){
            if (i.getType() != Ingredient.Type.SAUCE){
                BufferedImage image = i.getImage();
                int height = imageWidth * image.getHeight()/image.getWidth();
                Image newImage = image.getScaledInstance(imageWidth, height, Image.SCALE_SMOOTH);
                graphics.drawImage(newImage, 0, (burgerHeight - yValue) - height, null);
                yValue += height - 10;
            }
        }

        foodImage = resizeImage(foodImage, imageWidth, imageHeight);
        return foodImage;
    }

    public static BufferedImage generateSauceImage(Order order, int imageWidth, int imageHeight){
        BufferedImage sauceImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = sauceImage.createGraphics();

        int xValue = 0;
        for (Ingredient i: order.getSauces()){
            BufferedImage image = i.getImage();
            int width = imageHeight * image.getWidth()/image.getHeight();
            Image newImage = image.getScaledInstance(width, imageHeight, Image.SCALE_SMOOTH);
            graphics.drawImage(newImage, (imageWidth - xValue) - width, 0, null);
            xValue += width - 10;
        }
        return sauceImage;
    }

    public static BufferedImage generateDrinkImage(Order order, int imageWidth, int imageHeight){
        // not the best way to convert from buffered image to image, but the best without any external packages
        BufferedImage sauceImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = sauceImage.createGraphics();
        BufferedImage image = order.getDrink().getImage();
        if (image == null){
            return sauceImage;
        }
        Image newImage = image.getScaledInstance(imageHeight * image.getWidth()/image.getHeight(), imageHeight, Image.SCALE_SMOOTH);
        graphics.drawImage(newImage, 0, 0, null);
        return sauceImage;
    }

    private static BufferedImage resizeImage(BufferedImage img, int width, int height){
        Image tmp = img.getScaledInstance(height * img.getWidth()/img.getHeight(), height, Image.SCALE_SMOOTH);
        BufferedImage small_img = new BufferedImage(height * img.getWidth()/img.getHeight() , height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = small_img.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return small_img;
    } 

    private static int getTotalHeight(ArrayList<Ingredient> ingredients, int imageWidth){
        int total_height = 0;
        for (Ingredient i: ingredients){
            if (i.getType() != Ingredient.Type.SAUCE){
                BufferedImage image = i.getImage();
                int height = imageWidth * image.getHeight()/image.getWidth();
                total_height += height;
            }
        }
        return total_height;
    }
}
