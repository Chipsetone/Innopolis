import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.*;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {

        String inputStr = "";
        System.out.println("starting");

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        while(! "exit".equals(inputStr)){
            try {
                inputStr = inputReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            method3(inputStr);

            System.out.println("\n\nready");
        }

    }

    public static void method3(String resourceAddress){
        URL url = null;
        try {
            url = new URL(resourceAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is =  conn.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String inputLine;
            int inputChar;
            while ((inputChar = br.read()) != -1) {
                System.out.print((char)inputChar); //inputLine);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
