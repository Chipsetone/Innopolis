package tests.unit;

import com.semakin.ResultPrinter;

/**
 * Created by Chi on 11.02.2017.
 */
public class ResultPrinterMock extends ResultPrinter {
    private String lastMessage;
    @Override
    public void println(String message) {
        super.println(message);
        lastMessage = message;
    }

    public String getLastMessage(){
        return lastMessage;
    }
}
