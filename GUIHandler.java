import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUIHandler{
    public scoreCard scoreCard;
    public buildingPanel buildingPanel;
    public orderPanel orderPanel;
    public ingredientPanel ingredientPanel;

    private ImageGenerator imageGenerator;
    
    public GUIHandler() {
        imageGenerator = new ImageGenerator();
        initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Cooking Daddy");
        frame.setSize(1000,1000);
        
        JPanel contentPane =  new JPanel();
        contentPane.setLayout(new GridLayout(2, 1, 5, 5));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setContentPane(contentPane);

        JPanel gamePanel = new JPanel(new GridLayout(1, 4, 5, 5));
        scoreCard = new scoreCard();
        buildingPanel = new buildingPanel();
        orderPanel = new orderPanel();
        ingredientPanel = new ingredientPanel();

        gamePanel.add(scoreCard);
        gamePanel.add(buildingPanel);
        gamePanel.add(orderPanel);

        frame.add(gamePanel);
        frame.add(ingredientPanel);

        Generator g = new Generator();
        Order o = g.generateOrder();
        orderPanel.displayOrder(o);

        frame.setVisible(true);
    }    

    private class ingredientPanel extends JPanel{
        public ingredientPanel(){
            setLayout(new GridLayout(4, 4, 5, 5));
            setBackground(new Color(0xF5CBA7));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.black, 3), new EmptyBorder(10, 10, 10, 10)));

            for (int i = 0; i < 15; i++){
                JButton button = new JButton();
                // button.setFont(new Font("Calibri", Font.PLAIN, 14));
                // button.setBackground(new Color(0xA52A2A));
                // button.setForeground(Color.white);

                button.setUI(new IngredientButton());
                add(button);
            }
        }
    }

    private class orderPanel extends JPanel{
        private JLabel title;
        private JLabel description;
        
        public orderPanel(){
            // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            // setBackground(Color.yellow);
            // setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

            // title = new JLabel("Order");
            // title.setFont(new Font("Helvetica", Font.BOLD, 24));
            // title.setAlignmentX(Component.CENTER_ALIGNMENT);

            // description = new JLabel("Descrip");

            // add(title);
            // add(description);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(0xF7F9F9));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.black, 3), new EmptyBorder(10, 10, 10, 10)));

            title = new JLabel("Some Guy's Order");
            title.setFont(new Font("Helvetica", Font.BOLD, 15));

            description = new JLabel("food");

            add(title);
            add(description);
        }

        public void displayOrder(Order order){
            title.setText(order.getCustomerName() + "'s Order");
            description.setText(order.toString());
        }
    }

    private class buildingPanel extends JPanel{
        private JLabel title;
        private JLabel description;
        
        public buildingPanel(){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(0xFCF3CF));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(0x301800), 3), new EmptyBorder(10, 10, 10, 10)));

            title = new JLabel("Burger in Progress");
            title.setFont(new Font("Helvetica", Font.BOLD, 15));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            description = new JLabel("food");

            add(title);
            // add(description);

            // try
            // {
            //     BufferedImage image = ImageIO.read(new File("images/daddy_burgertest.png"));
            //     Image img = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            //     JLabel picLabel = new JLabel(new ImageIcon(img));
            //     add(picLabel);
            // }
            // catch (IOException e)
            // {
            //     e.printStackTrace();
            // }
        }

        public void displayFood(Recipe recipe){
            BufferedImage image = imageGenerator.foodImage(recipe);
            Image img = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(img));
            add(picLabel);
        }
    }

    private class scoreCard extends JPanel{
        private JLabel title;

        public scoreCard(){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(0xEBF5FB));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(0x1B4F72), 3), new EmptyBorder(10, 10, 10, 10)));

            title = new JLabel("Past Orders");
            title.setFont(new Font("Helvetica", Font.BOLD, 15));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);


            add(title);
        }
    }

    private class timeCountdown extends JProgressBar{

    }
}

