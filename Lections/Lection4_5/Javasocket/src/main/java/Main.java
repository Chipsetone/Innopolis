import java.io.IOException;

/**
 * @author Семакин Виктор
 */
public class Main {

    public static void main(String[] args) {
        ` lambda = a -> {
            return a;
        };


        Thread server = new Thread(new Runnable() {
            public void run() {
                Server server1 = new Server();

                try {
                    server1.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread client = new Thread(new Runnable() {
            public void run() {

                Client client1 = new Client();
                try {
//                    Thread.sleep(2000);
                    client1.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        server.start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.start();

        System.out.println("что-то запустилось");
    }


}
