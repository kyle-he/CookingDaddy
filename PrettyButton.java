import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
class PrettyButton extends JButton{

    public PrettyButton(){
        setFont(new Font("Helvetica", Font.PLAIN, 36));
        setBackground(new Color(0xff8c00));
        setForeground(Color.white);
        setText("START GAME");
        
        setUI(new PrettyButtonUI());
    } 

    private class PrettyButtonUI extends BasicButtonUI {
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
            paintBackground(g, b, b.getModel().isPressed() ? 1 : 0);
            super.paint(g, c);
        }
        
        private void paintBackground (Graphics g, JComponent c, int yOffset){
            Dimension size = c.getSize();

            g.setColor(c.getBackground().darker());
            g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
            g.setColor(c.getBackground());
            g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
        }
    }
}
