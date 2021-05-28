import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameHandler {
    private static int coins = 10;
    private static CustomerHandler ch = new CustomerHandler();
    private static int level = 1;
    private static Customer currCustomer;
    private static OrderBuilder ob;
    private static GUIHandler ex;

    public static void runGame(JFrame frame){
        ex = new GUIHandler(frame);
        startGame();
    }

    public static void startGame()
    {
        ch.addCustomer(level);
        currCustomer = ch.getCustomer();
        System.out.println(currCustomer.getOrder());
        ob = new OrderBuilder(currCustomer.getOrder());
        ex.orderPanel.displayCustomer(currCustomer);
        JOptionPane.showMessageDialog(null, currCustomer.getOrder().getCost());
    }

    public static void checkLevelUp()
    {
        if (coins > level * 20)
        {
            level++;
            JOptionPane.showMessageDialog(null, "Level up! You are now at level " + level + "!");
        }
        startGame();
    }

    public static void addIngredient(Ingredient i)
    {
        Order curr = ob.getCurrent();
        Order goal = ob.getGoal();
        if (ob.build(i))
        {
            System.out.println(i);
            if (curr.equals(goal))
            {
                coins += goal.getCost();
                JOptionPane.showMessageDialog(null, "You received " + goal.getCost() + " coins!");
                checkLevelUp();
                return;
            }
        }
        else
        {
            System.out.println(i);
            coins -= i.getCost();
            JOptionPane.showMessageDialog(null, "Incorrect ingredient. You lost " + i.getCost() + " coins.");
        }
    }
}
