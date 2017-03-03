import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Семакин Виктор
 */
public class Server {
    private final int port = 57000;

    private ServerSocket serverSocket;
    private Socket socket;


    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));

        String message = null;

        while((message = bufferedReader.readLine()) != null){
            System.out.println("Message: " + message);
        }

    }
}
