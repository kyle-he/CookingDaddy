public class Main{
    public static void main(String[] args) {
        CustomerHandler ch = new CustomerHandler();
        for (int i = 0; i < 10; i++){
            ch.addCustomer();
        }
        System.out.print(ch);
    }
}
