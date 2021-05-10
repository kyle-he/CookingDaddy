package CookingDaddy;

public class Main{
    public static void main(String[] args) {
        Generator g = new Generator();
        for (int i = 0; i < 10; i++){
            System.out.println(g.generateOrder() + "\n");
        }
    }
}
