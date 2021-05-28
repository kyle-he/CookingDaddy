import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *  This is the ingredient button formatter.
 *
 *  @author Kyle He
 *  @version May 13, 2021
 */
class IngredientButton extends JButton implements ActionListener {
    private Ingredient ingredient;

    public IngredientButton(Ingredient ingredient){
        this.ingredient = ingredient;

        setUI(new IngredientButtonUI(ingredient.getButtonImage()));
        addActionListener(this);
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
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
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Dimension size = c.getSize();
            int imageHeight = buttonImage.getHeight();
            int imageWidth = buttonImage.getWidth();

            g.drawImage(buttonImage, -1*sizeIncrease/2, -1*sizeIncrease/2, size.height * imageWidth/imageHeight + sizeIncrease, size.height + sizeIncrease, null);
        }
    }
}