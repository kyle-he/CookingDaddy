import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
    private Generator generator;
    private CustomerHandler ch;
    private int level = 1;
    private Customer currCustomer;
    private OrderBuilder ob;

    public GUIHandler() {
        imageGenerator = new ImageGenerator();
        generator = new Generator();
        ch = new CustomerHandler();
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

        frame.setVisible(true);

        ch.addCustomer(level);
        currCustomer = ch.getCustomer();
        ob = new OrderBuilder(currCustomer.getOrder());
        orderPanel.displayCustomer(currCustomer);
    }

    private class ingredientPanel extends JPanel{
        public ingredientPanel(){
            setLayout(new GridLayout(4, 4, 5, 5));
            setBackground(new Color(0xF5CBA7));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.black, 3), new EmptyBorder(10, 10, 10, 10)));

            for (final Ingredient i: generator.getAllIngredients()){
                JButton button = new JButton();
                button.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        if (ob.build(i)) //clicked the right ingredient
                        {
                            //TODO: gui stuff
                        }
                        else
                        {
                            //TODO: gui stuff
                        }
                    }
                });
                button.setUI(new IngredientButton(i.getImage()));
                add(button);
            }
        }
    }

    private class orderPanel extends JPanel{
        private JLabel title;
        private JLabel description;

        public orderPanel(){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(0xF7F9F9));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.black, 3), new EmptyBorder(10, 10, 10, 10)));

            title = new JLabel("Some Guy's Order");
            title.setFont(new Font("Helvetica", Font.BOLD, 15));

            description = new JLabel("food");

            add(title);
            add(description);
            add( Box.createVerticalStrut(20));
        }

        public void displayCustomer(Customer c){
            title.setText(c.getCustomerName() + "'s Order");
            Recipe recipe = c.getOrder().getRecipe();
            JLabel picLabel = new JLabel(new ImageIcon(imageGenerator.generateFoodImage(recipe, getSize().width)));
            add(picLabel);
            description.setText(c.getOrder().toString()); //TODO: fix spacing
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

