import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *  This is the ingredient button formatter.
 *
 *  @author Kyle He
 *  @version May 13, 2021
 */
class IngredientButton extends JButton{
    public IngredientButton(BufferedImage image){
        setUI(new IngredientButtonUI(image));
    } 
    
    private class IngredientButtonUI extends BasicButtonUI {
        BufferedImage buttonImage;

        public IngredientButtonUI(BufferedImage image){
            buttonImage = image;
        }

        @Override
        public void installUI (JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
            button.setBorder(new EmptyBorder(5, 15, 5, 15));
        }

        @Override   
        public void paint (Graphics g, JComponent c){
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, b.getModel().isPressed() ? 0 : -10);
            super.paint(g, c);
        }

        private void paintBackground (Graphics g, JComponent c, int sizeIncrease){
            
            Dimension size = c.getSize();
            int imageHeight = buttonImage.getHeight();
            int imageWidth = buttonImage.getWidth();

            g.drawImage(buttonImage, -1*sizeIncrease/2, -1*sizeIncrease/2, size.height*imageWidth/imageHeight + sizeIncrease, size.height + sizeIncrease, null);
        }
    }
}