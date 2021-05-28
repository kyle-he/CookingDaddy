import java.awt.BorderLayout;
import java.awt.Color;
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

public class GUIHandler{
    public scoreCard scoreCard;
    public buildingPanel buildingPanel;
    public orderPanel orderPanel;
    public ingredientPanel ingredientPanel;

    public GUIHandler(JFrame frame) {
        initUI(frame);
    }

    private void initUI(JFrame frame) {
        frame.setSize(900,900);

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
        private JLabel foodImage = new JLabel();
        private JLabel sauceImage = new JLabel();
        private JLabel drinkImage = new JLabel();

        public orderPanel(){
            super(PrettyJPanel.Type.OUTLINED);
        }

        public void setVisible(){
            setLayout(new BorderLayout());
            setBackground(new Color(0xF7F9F9));

            title = new JLabel("Some Guy's Order");
            title.setFont(new Font("Helvetica", Font.BOLD, 20));

            add(title, BorderLayout.NORTH);
            add(Box.createVerticalStrut(20));
            
            foodImage.setHorizontalAlignment(JLabel.CENTER);
            sauceImage.setHorizontalAlignment(JLabel.CENTER);
            drinkImage.setHorizontalAlignment(JLabel.CENTER);

            foodImage.setHorizontalTextPosition(JLabel.CENTER);
            foodImage.setVerticalTextPosition(JLabel.BOTTOM);
            foodImage.setFont(new Font("Helvetica", Font.PLAIN, 15));

            PrettyJPanel bottomImage = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT);
            bottomImage.add(sauceImage);
            bottomImage.add(drinkImage);

            add(foodImage, BorderLayout.CENTER);
            add(bottomImage, BorderLayout.SOUTH);
        }

        public void displayCustomer(Customer c){
            title.setText(c.getCustomerName() + "'s Order");
            Recipe recipe = c.getOrder().getRecipe();

            foodImage.setIcon(new ImageIcon(ImageGenerator.generateFoodImage(recipe, getSize().width - 100, getSize().height*4/7)));
            foodImage.setText(c.getOrder().getRecipe().getName());

            sauceImage.setIcon(new ImageIcon(ImageGenerator.generateSauceImage(c.getOrder(), getSize().width/2 - 40, 80)));
            drinkImage.setIcon(new ImageIcon(ImageGenerator.generateDrinkImage(c.getOrder(), getSize().width/2 - 40, 80)));

            repaint();
        }
    }

    class buildingPanel extends PrettyJPanel{
        private JLabel title;
        private JLabel foodImage = new JLabel();
        private JLabel sauceImage = new JLabel();
        private JLabel drinkImage = new JLabel();

        private timeCountdown timeCountdown;

        public buildingPanel(){
            super(PrettyJPanel.Type.OUTLINED);
        }

        public void setVisible(){
            setLayout(new BorderLayout());
            setBackground(new Color(0xFCF3CF));

            title = new JLabel("Burger in Progress");
            title.setFont(new Font("Helvetica", Font.BOLD, 20));

            timeCountdown = new timeCountdown((int) getSize().getWidth());
            add(title, BorderLayout.NORTH);

            PrettyJPanel burgerProgress = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT);

            foodImage.setHorizontalAlignment(JLabel.CENTER);
            sauceImage.setHorizontalAlignment(JLabel.CENTER);
            drinkImage.setHorizontalAlignment(JLabel.CENTER);

            PrettyJPanel bottomImage = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT);
            bottomImage.add(sauceImage);
            bottomImage.add(drinkImage);

            burgerProgress.add(foodImage, BorderLayout.CENTER);
            burgerProgress.add(bottomImage, BorderLayout.SOUTH);

            add(burgerProgress);

            add(timeCountdown, BorderLayout.SOUTH);
        }

        public void incrementTime(int amount){
            timeCountdown.incrementFill(amount);

            repaint();
        }

        public void displayFood(Order order){
            Recipe recipe = order.getRecipe();

            foodImage.setIcon(new ImageIcon(ImageGenerator.generateFoodImage(recipe, getSize().width - 100, getSize().height*4/7)));

            sauceImage.setIcon(new ImageIcon(ImageGenerator.generateSauceImage(order, getSize().width/2 - 40, 80)));
            drinkImage.setIcon(new ImageIcon(ImageGenerator.generateDrinkImage(order, getSize().width/2 - 40, 80)));

            repaint();
        }

        public void clearDisplay(){
            foodImage.setIcon(null);
            sauceImage.setIcon(null);
            drinkImage.setIcon(null);

            repaint();
        }
    }

    class scoreCard extends PrettyJPanel{
        private JLabel balance;
        private JLabel level;
        private JLabel location;
        private JLabel message;
        private JLabel currentOrder;

        public scoreCard(){
            super(PrettyJPanel.Type.TRANSPARENT);
        }

        public void setVisible(){
            setLayout(new GridLayout(2,1,0,5));
            setBackground(new Color(0xEBF5FB));

            PrettyJPanel upperPanel = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT);
            upperPanel.setLayout(new GridLayout(2,1,0,5));
            
            PrettyJPanel messageCard = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT, PrettyJPanel.Type.OUTLINED);
            messageCard.setLayout(new BoxLayout(messageCard, BoxLayout.Y_AXIS));
            message = new JLabel("<html>Welcome to the Cooking Daddy! <br>You have " + GameHandler.getTime() + " seconds to make as much money as possible.</html>");
            message.setFont(new Font("Helvetica", Font.PLAIN, 15));

            JLabel messageTitle = new JLabel("Messages");
            messageTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
            messageCard.add(messageTitle);
            messageCard.add(message);

            PrettyJPanel statsCard = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT, PrettyJPanel.Type.OUTLINED);
            statsCard.setLayout(new BoxLayout(statsCard, BoxLayout.Y_AXIS));
            
            balance = new JLabel("Balance: 0 coins");
            level = new JLabel("Level: 1");
            location = new JLabel("Venue: Daddy's Garage");
            
            balance.setFont(new Font("Helvetica", Font.BOLD, 15));
            level.setFont(new Font("Helvetica", Font.BOLD, 15));
            location.setFont(new Font("Helvetica", Font.BOLD, 15));

            JLabel statsTitle = new JLabel("Statistics");
            statsTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
            statsCard.add(statsTitle);
            statsCard.add(balance);
            statsCard.add(level);
            statsCard.add(location);

            PrettyJPanel orderPanel = new PrettyJPanel(PrettyJPanel.Type.TRANSPARENT, PrettyJPanel.Type.OUTLINED);
            orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

            JLabel orderTitle = new JLabel("Current Burger Recipe");
            orderTitle.setFont(new Font("Helvetica", Font.BOLD, 20));

            currentOrder = new JLabel("Recipe");
            currentOrder.setFont(new Font("Helvetica", Font.PLAIN, 15));

            orderPanel.add(orderTitle);
            orderPanel.add(currentOrder);

            upperPanel.add(messageCard);
            upperPanel.add(statsCard);

            add(upperPanel);
            add(orderPanel);
        }

        public void displayMessage(String msg){
            message.setText(msg);

            repaint();
        }

        public void displayOrder(Order order){
            currentOrder.setText("<html>" + order.toString().replace("\n", "<br> <br>") + "</html>");

            repaint();
        }

        public void displayBalance(int amount){
            balance.setText("Balance: " + amount + " coins");

            repaint();
        }

        public void displayLevel(int level){
            balance.setText("Level: " + level);
            location.setText("Venue: " + Generator.getLocation(level));

            repaint();
        }
    }

    private class timeCountdown extends JComponent{
        private int width;
        private int fill = 1000; //from 1-100

        public timeCountdown(int width){
            super();

            this.width = width;
            setSize(width, 30);
        }

        public int getFill(){
            return fill;
        }

        public void incrementFill(int amount){
            fill += amount;
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(new Color(0x181818));
            g.fillRect(0, 0, width, 30);

            g.setColor(new Color(0xff8c00));
            g.fillRect(0, 0, fill*width/1000, 30);
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(width, 30);
        }
    }
}

