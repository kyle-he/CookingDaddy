import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

        // this way, we can run getSize() without after the size has actually been calculated
        scoreCard.setVisible();
        buildingPanel.setVisible();
        orderPanel.setVisible();
        ingredientPanel.setVisible();
    }

    class ingredientPanel extends PrettyJPanel{
        public ingredientPanel(){
            super(PrettyJPanel.Type.OUTLINED);
        }

        public void setVisible(){
            setLayout(new GridLayout(1, 2, 5, 5));
            setBackground(new Color(0xF5CBA7));

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

    class orderPanel extends PrettyJPanel{
        private JLabel title;

        public orderPanel(){
            super(PrettyJPanel.Type.OUTLINED);
        }

        public void setVisible(){
            setLayout(new BorderLayout());
            setBackground(new Color(0xF7F9F9));

            title = new JLabel("Some Guy's Order");
            title.setFont(new Font("Helvetica", Font.BOLD, 15));

            add(title, BorderLayout.NORTH);
            add(Box.createVerticalStrut(20));
        }

        public void displayCustomer(Customer c){
            title.setText(c.getCustomerName() + "'s Order");
            Recipe recipe = c.getOrder().getRecipe();

            JLabel foodImage = new JLabel(new ImageIcon(ImageGenerator.generateFoodImage(recipe, getSize().width - 100, getSize().height - 200)), JLabel.CENTER);
    
            foodImage.setHorizontalTextPosition(JLabel.CENTER);
            foodImage.setVerticalTextPosition(JLabel.BOTTOM);
            foodImage.setText(c.getOrder().getRecipe().getName());
            foodImage.setFont(new Font("Helvetica", Font.PLAIN, 15));

            PrettyJPanel bottomImage = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT);
            JLabel sauceImage = new JLabel(new ImageIcon(ImageGenerator.generateSauceImage(c.getOrder(), getSize().width/2 - 40, 80)), JLabel.CENTER);
            JLabel drinkImage = new JLabel(new ImageIcon(ImageGenerator.generateDrinkImage(c.getOrder(), getSize().width/2 - 40, 80)), JLabel.CENTER);
            bottomImage.add(sauceImage);
            bottomImage.add(drinkImage);

            add(foodImage, BorderLayout.CENTER);
            add(bottomImage, BorderLayout.SOUTH);
        }
    }

    class buildingPanel extends PrettyJPanel{
        private JLabel title;
        private JComponent timeCountdown;

        public buildingPanel(){
            super(PrettyJPanel.Type.OUTLINED);
        }

        public void setVisible(){
            setLayout(new BorderLayout());
            setBackground(new Color(0xFCF3CF));

            title = new JLabel("Burger in Progress");
            title.setFont(new Font("Helvetica", Font.BOLD, 15));

            timeCountdown = new timeCountdown((int) getSize().getWidth());
            add(title, BorderLayout.NORTH);
            add(timeCountdown, BorderLayout.SOUTH);
        }

        public void getCountdown(){

        }

        public void displayFood(Recipe recipe){
            JLabel picLabel = new JLabel(new ImageIcon(ImageGenerator.generateFoodImage(recipe, getSize().width - 100, getSize().height - 200)), JLabel.CENTER);
            picLabel.setHorizontalTextPosition(JLabel.CENTER);
            picLabel.setVerticalTextPosition(JLabel.BOTTOM);

            add(picLabel, BorderLayout.CENTER);
        }
    }

    class scoreCard extends PrettyJPanel{
        JLabel balance;
        JLabel levelCard;
        PrettyJPanel pastOrders;

        public scoreCard(){
            super(PrettyJPanel.Type.TRANSPARENT);
        }

        public void setVisible(){
            setLayout(new GridLayout(2,0,0,5));
            setBackground(new Color(0xEBF5FB));

            PrettyJPanel upperPanel = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT);
            upperPanel.setLayout(new GridLayout(2,0,0,5));
            
            PrettyJPanel balanceCard = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT, PrettyJPanel.Type.OUTLINED);
            balance = new JLabel("Balance: 0 coins");
            balanceCard.add(balance);

            PrettyJPanel levelCard = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT, PrettyJPanel.Type.OUTLINED);
            balance = new JLabel("Level: 1, Big House");
            levelCard.add(balance);

            pastOrders = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT, PrettyJPanel.Type.OUTLINED);
            pastOrders.add(new JLabel("pastorders"));

            upperPanel.add(balanceCard);
            upperPanel.add(levelCard);

            add(upperPanel);
            add(pastOrders);
        }

        public void displayBalance(int amount){
            balance.setText("Balance: " + amount + " coins");
        }

        public void displayLevel(int level){
            balance.setText("Level: " + level + " " + Generator.getLocation(level));
        }
    }

    private class timeCountdown extends JComponent{
        private int width;
        private int fill = 10; //from 1-100

        public timeCountdown(int width){
            super();

            this.width = width;
            setSize(width, 30);
        }

        public void updateFill(int newFill){
            fill = newFill;
        }

        public void incrementFill(int amount){
            fill += amount;
        }

        public int getFill(){
            return fill;
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(new Color(0x181818));
            g.fillRect(0, 0, width, 30);

            g.setColor(new Color(0xFF0000));
            g.fillRect(0, 0, fill, 30);
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(width, 30);
        }
    }
}

