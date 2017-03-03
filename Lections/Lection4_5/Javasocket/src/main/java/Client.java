import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Семакин Виктор
 */
public class Client {
    private Socket socket;
    private int port = 57000;


    public void start() throws IOException {
        socket = new Socket("localhost", port);

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        String message = null;
        while((message= scanner.next()) != null){
            printWriter.println(message);
            printWriter.flush();
        }
    }
}
