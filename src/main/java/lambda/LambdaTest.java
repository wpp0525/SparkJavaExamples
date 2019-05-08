package lambda;

public class LambdaTest {
    public static void main(String[] args) {

        Thread tt = new Thread( () -> {
            System.out.println("wpp");
        });

        tt.start();
    }
}
