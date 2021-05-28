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
import javax.swing.JTextField;

public class MenuScreen implements ActionListener {
    private JFrame frame;
    private JTextField textField;

    /**
     * Constructor
     */
    public MenuScreen(){
        displayMenu();
    }

    /**
     * Display Menu
     */
    public void displayMenu(){
        frame = new JFrame("Cooking Daddy");
        frame.setSize(900,900);
        frame.setResizable(false);

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

        JLabel textLabel = new JLabel("Time of Run (seconds): ");
        textLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        mainMenu.add(textLabel);

        textField = new JTextField(16);
        mainMenu.add(textField);

        PrettyButton startButton = new PrettyButton();
        startButton.addActionListener(this);
        startButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        mainMenu.add(Box.createVerticalStrut(20));
        mainMenu.add(startButton);

        contentPane.add(mainMenu);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            GameHandler.runGame(frame, Integer.parseInt(textField.getText()));
        } catch(NumberFormatException ex){
            GameHandler.runGame(frame, 120);
        }
    }
}
