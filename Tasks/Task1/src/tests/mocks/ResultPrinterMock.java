package tests.mocks;

import com.semakin.ResultPrinter;

import java.util.LinkedList;

/**
 * Created by Chi on 11.02.2017.
 */
public class ResultPrinterMock extends ResultPrinter {
    private LinkedList<String> printedMessages;

    public ResultPrinterMock() {
        this(new LinkedList<String>());
    }

    public ResultPrinterMock(LinkedList<String> printedTarget) {
        this.printedMessages = printedTarget;
    }

    @Override
    public void println(String message) {
        super.println(message);
        printedMessages.add(message);
    }

    public String getLastMessage(){
        return printedMessages.getLast();
    }
}
