import java.util.HashSet;
import java.util.List;

/**
 * @author Семакин Виктор
 */

public class Thread2UniqueChecker extends Thread{
    private MillisecondsCounter msCounter;
    private MessageQueue messageQueue;
    private final static int secondsInterval = 5000;
    private HashSet<Integer> allGeneratednumbers = new HashSet<>();

    public Thread2UniqueChecker(MillisecondsCounter msCounter, MessageQueue messageQueue){
        this.msCounter = msCounter;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run(){
        int prevTime = msCounter.getTime();
        int currentTime = prevTime;

        while(!isStopped()){

            if((currentTime - prevTime) >= secondsInterval){
                // обсчитать очередь
                HashSet<Integer> generatedMessages = messageQueue.getMessagesAndClean();
                print("" + generatedMessages.size());

                for (int generatedNumber :
                        generatedMessages) {
                    if (isStopped()) {
                        // остановить остальных
                        msCounter.stop(true);
                        return;
                    }
                    allGeneratednumbers.add(generatedNumber);
                }
                prevTime = currentTime;
            }
            currentTime = msCounter.getTime();
        }
    }

    private boolean isStopped(){
        return allGeneratednumbers.size() >= 100;
    }

    private void print(String message){
        System.out.println(Thread.currentThread().getName() + " " + message);
    }
}
