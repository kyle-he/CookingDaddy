import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuScreen {
    public MenuScreen(){
        displayMenu();
    }

    public void displayMenu(){
        final JFrame frame = new JFrame("Cooking Daddy");
        frame.setSize(1000,1000);

        JPanel contentPane =  new JPanel();
        contentPane.setLayout(new GridBagLayout());

        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.PAGE_AXIS));

        try
        {
            BufferedImage img = ImageIO.read(new File("images/ui/cooking_daddy.png"));
            ImageIcon icon =new ImageIcon(img);
            JLabel title=new JLabel();
            title.setIcon(icon);
            title.setAlignmentX(JPanel.CENTER_ALIGNMENT);

            mainMenu.add(title);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        PrettyButton startButton = new PrettyButton();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameHandler.runGame(frame);
            }
        });
        startButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        mainMenu.add(Box.createVerticalStrut(20));
        mainMenu.add(startButton);

        contentPane.add(mainMenu);
        
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }
}
