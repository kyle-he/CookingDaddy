import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *  This is the ingredient button formatter.
 *
 *  @author Kyle He
 *  @version May 13, 2021
 */
class IngredientButton extends BasicButtonUI {

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
        // Graphics2D g2 = (Graphics2D) g;
        // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try
        {
            BufferedImage image = ImageIO.read(new File("images/bun_top.png"));
            g.drawImage(image, -1*sizeIncrease/2, -1*sizeIncrease/2, size.width + sizeIncrease, size.height + sizeIncrease, null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}