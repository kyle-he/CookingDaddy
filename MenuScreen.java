import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuScreen {
    public MenuScreen(){
        displayMenu();
    }

    public void displayMenu(){
        
        JFrame frame = new JFrame("Cooking Daddy");
        frame.setSize(1000,1000);

        JPanel contentPane =  new JPanel();
        contentPane.setLayout(new GridLayout(2, 1, 5, 5));
        try
        {
            BufferedImage img = ImageIO.read(new File("images/ui/title_screen.png"));
            ImageIcon icon =new ImageIcon(img);
            JLabel lbl=new JLabel();
            lbl.setIcon(icon);
            lbl.setText("eeeee");
            contentPane.add(lbl);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }
}
