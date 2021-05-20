// import javax.swing.JFrame;
// import javax.swing.JButton;
// import java.awt.GridLayout;

import javax.swing.*;
import java.awt.*;

public class GUIHandler{
    
        public GUIHandler() {
            initUI();
        }
    
        private void initUI() {
            JFrame frame = new JFrame("Cooking Daddy");
            frame.setSize(1000,1000);
            frame.setLayout(new GridLayout(2, 1, 5, 5));

            JPanel ingredientPanel = new JPanel(new GridLayout(4, 4, 5, 5));

            for (int i = 0; i < 15; i++){
                JButton button = new JButton();
                // button.setFont(new Font("Calibri", Font.PLAIN, 14));
                // button.setBackground(new Color(0xA52A2A));
                // button.setForeground(Color.white);

                button.setUI(new IngredientButton());
                ingredientPanel.add(button);
            }

            JPanel gamePanel = new JPanel(new GridLayout(1, 4, 5, 5));

            gamePanel.add(new scoreCard());
            gamePanel.add(new buildingPanel());
            gamePanel.add(new orderPanel());

            frame.add(gamePanel);
            frame.add(ingredientPanel);

            frame.setVisible(true);
        }    

        private class orderPanel extends JPanel{
            private Label title;
            private Label description;
            
            public orderPanel(){
                setLayout(new GridLayout(2,1));
                setBackground(Color.yellow);

                title = new Label("Title");
                description = new Label("Descrip");

                add(title);
                add(description);
            }

        }

        private class buildingPanel extends JPanel{
            private Label title;
            private Label description;
            
            public buildingPanel(){
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                setBackground(Color.yellow);
                setAlignmentX(Component.CENTER_ALIGNMENT);

                title = new Label("Food stuff");
                description = new Label("food");

                add(title);
                add(description);
            }

            public void updateFood(Recipe food){

            }
        }

        private class scoreCard extends JPanel{
            private Label title;

            public scoreCard(){
                setLayout(new GridLayout(2,1));

                title = new Label("eee \n eeee \n eeee");
                add(title);
            }
        }
}

