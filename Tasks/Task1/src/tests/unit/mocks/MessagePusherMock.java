package tests.unit.mocks;

import com.semakin.threading.IMessagePushable;
import com.semakin.threading.Message;

/**
 * Created by Chi on 12.02.2017.
 */
public class MessagePusherMock implements IMessagePushable {
    private boolean isStopped = false;
    private boolean isStarted = false;
    private Integer localSum = 0;

    @Override
    public void pushMessage(Message message) {
        if(isStopped){
            return;
        }
        if(message.isInvalidMessage()){
            isStopped = true;
            return;
        }

        localSum += message.getMessage();
        isStarted= true;
        System.out.println("затолкали сообщение в очередь " + localSum.toString());
    }

    public boolean isStopped(){
        return isStopped;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public int getLocalSum(){
        return localSum;
    }
}
