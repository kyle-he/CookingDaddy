import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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

        // g.setColor(c.getBackground().darker());
        // g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
        // g.setColor(c.getBackground());
        // g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
    }
}