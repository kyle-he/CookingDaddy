import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameHandler
{
    private static int             coins = 10;
    private static CustomerHandler ch    = new CustomerHandler();
    private static int             level = 1;
    private static Customer        currCustomer;
    private static OrderBuilder    ob;
    private static GUIHandler      ex;
    private static int time = 120;

    public static void runGame(JFrame frame, int inputTime)
    {
        time = inputTime;
        
        ex = new GUIHandler(frame);
        ex.scoreCard.displayBalance(coins);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                startGame();
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                timer();
            }
        });
        executorService.shutdown();
    }

    public static void startGame()
    {
        ch.addCustomer(level);
        currCustomer = ch.getCustomer();

        ob = new OrderBuilder(currCustomer.getOrder());
        
        ex.scoreCard.displayOrder(currCustomer.getOrder());
        ex.orderPanel.displayCustomer(currCustomer);
        ex.buildingPanel.clearDisplay();
    }

    private static void timer(){
        for (int i = 0; i < 1000; i++){
            try
            {
                Thread.sleep(time);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            ex.buildingPanel.incrementTime(-1);
        }
        endGame();
    }

    private static void endGame(){
        JOptionPane.showMessageDialog(null, "Your " + time + " second run is over! You ended with " + coins + " coins in " + Generator.getLocation(level));
        System.exit(0);
    }

    public static void checkLevelUp()
    {
        if (coins > level * 60)
        {
            level++;
            ex.scoreCard.displayMessage("Level up! You are now at level " + level + "!");
        }
        startGame();
    }


    public static void addIngredient(Ingredient i)
    {
        Order curr = ob.getCurrent();
        Order goal = ob.getGoal();
        if (ob.build(i))
        {
            ex.buildingPanel.displayFood(curr);
            if (curr.equals(goal))
            {
                coins += goal.getCost();
                ex.scoreCard.displayMessage("You received " + goal.getCost() + " coins!");
                ex.scoreCard.displayBalance(coins);
                checkLevelUp();
                return;
            }
        }
        else
        {
            int coinsLost = 0;
            coinsLost = i.getCost() + curr.calculateCost();
            coins -= coinsLost;
            ex.scoreCard.displayMessage("Incorrect ingredient. You lost " + coinsLost + " coins.");
            ex.scoreCard.displayBalance(coins);
        }
    }

    public static int getTime(){
        return time;
    }
}
