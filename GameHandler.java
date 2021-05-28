import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameHandler {
    private static int coins = 0;
    private static CustomerHandler ch = new CustomerHandler();
    private static int level = 1;
    private static Customer currCustomer;
    private static OrderBuilder ob;
    private static GUIHandler ex;

    public static void runGame(JFrame frame){
        ex = new GUIHandler(frame);
        startGame();
        checkLevelUp();
    }

    public static void startGame()
    {
        ch.addCustomer(level);
        currCustomer = ch.getCustomer();
        ob = new OrderBuilder(currCustomer.getOrder());
        ex.orderPanel.displayCustomer(currCustomer);
        JOptionPane.showMessageDialog(null, currCustomer.getOrder().getCost());
    }

    public static void checkLevelUp()
    {
        if (coins > level * 20)
        level++;
        JOptionPane.showMessageDialog(null, "Level up! You are now at level " + level + "!");
    }

    public static void addCoins()
    {

    }
}
