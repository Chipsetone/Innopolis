package tests.unit;

import com.semakin.ResultPrinter;

/**
 * Created by Chi on 11.02.2017.
 */
public class ResultPrinterMock extends ResultPrinter {
    private volatile String lastMessage;
    @Override
    public synchronized void println(String message) {
        super.println(message);
        lastMessage = message;
    }

    public synchronized String getLastMessage(){
        return lastMessage;
    }
}
