import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUIHandler{
    public scoreCard scoreCard;
    public buildingPanel buildingPanel;
    public orderPanel orderPanel;
    public ingredientPanel ingredientPanel;

    private int score = 0;
    private CustomerHandler ch;
    private int level = 1;
    private Customer currCustomer;
    private OrderBuilder ob;

    public GUIHandler(JFrame frame) {
        ch = new CustomerHandler();
        initUI(frame);
    }

    private void initUI(JFrame frame) {
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
    }

    class ingredientPanel extends JPanel{
        public ingredientPanel(){
            setLayout(new GridLayout(1, 2, 5, 5));
            setBackground(new Color(0xF5CBA7));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.black, 3), new EmptyBorder(10, 10, 10, 10)));

            JPanel foodGrid = new JPanel();
            foodGrid.setLayout(new GridLayout(4, 4, 5, 5));
            foodGrid.setBackground(new Color(0xF5CBA7));
            for (Ingredient i: Generator.getAllIngredients()){
                IngredientButton button = new IngredientButton(i);
                foodGrid.add(button);
            }

            JPanel otherGrid = new JPanel();
            otherGrid.setLayout(new GridLayout(1, 2, 0, 0));
            otherGrid.setBackground(new Color(0xF5CBA7));

            JPanel sauceGrid = new JPanel();
            sauceGrid.setLayout(new GridLayout(4, 1, 5, 5));
            sauceGrid.setBackground(new Color(0xF5CBA7));
            for (Ingredient i: Generator.getAllSauces()){
                IngredientButton button = new IngredientButton(i);
                sauceGrid.add(button);
            }

            JPanel drinkGrid = new JPanel();
            drinkGrid.setLayout(new GridLayout(4, 1, 5, 5));
            drinkGrid.setBackground(new Color(0xF5CBA7));
            for (Ingredient i: Generator.getAllDrinks()){
                IngredientButton button = new IngredientButton(i);
                drinkGrid.add(button);
            }

            otherGrid.add(sauceGrid);
            otherGrid.add(drinkGrid);

            add(foodGrid);
            add(otherGrid);
        }
    }

    class orderPanel extends JPanel{
        private JLabel title;
        private JLabel description;

        public orderPanel(){
            // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(0xF7F9F9));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.black, 3), new EmptyBorder(10, 10, 10, 10)));

            title = new JLabel("Some Guy's Order");
            title.setFont(new Font("Helvetica", Font.BOLD, 15));

            add(title);
            add(Box.createVerticalStrut(20));
        }

        public void displayCustomer(Customer c){
            title.setText(c.getCustomerName() + "'s Order");
            Recipe recipe = c.getOrder().getRecipe();
            JLabel picLabel = new JLabel(new ImageIcon(ImageGenerator.generateFoodImage(recipe, getSize().width - 100, getSize().height - 200)), JLabel.CENTER);
            picLabel.setHorizontalTextPosition(JLabel.CENTER);
            picLabel.setVerticalTextPosition(JLabel.BOTTOM);
            picLabel.setText(c.getOrder().getRecipe().getName());

            add(picLabel);
        }
    }

    class buildingPanel extends JPanel{
        private JLabel title;
        private JLabel description;

        public buildingPanel(){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(new Color(0xFCF3CF));
            setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(0x301800), 3), new EmptyBorder(10, 10, 10, 10)));

            title = new JLabel("Burger in Progress");
            title.setFont(new Font("Helvetica", Font.BOLD, 15));
            // title.setAlignmentX(Component.CENTER_ALIGNMENT);

            description = new JLabel("food");

            add(new timeCountdown());
            add(title);
        }

        public void displayFood(Recipe recipe){
            BufferedImage image = ImageGenerator.generateFoodImage(recipe, 200, 200);
            Image img = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(img));
            add(picLabel);
        }
    }

    class scoreCard extends JPanel{
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

    class timeCountdown extends JProgressBar{
        public timeCountdown(){
            setValue(40);
            setBounds(0,0,520,50);
            setStringPainted(true);
            setFont(new Font("Helvetica",Font.PLAIN, 25));
            setForeground(Color.red);
            setBackground(Color.black);
        }
    }
}

