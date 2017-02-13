import java.util.Random;

/**
 * @author Семакин Виктор
 */
public class Thread1Generator extends Thread {
    private MillisecondsCounter msCounter;
    private MessageQueue messageQueue;
    Random random = new Random();
    private final int second = 1000;

    public Thread1Generator(MillisecondsCounter msCounter, MessageQueue messageQueue) {
        this.msCounter = msCounter;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run(){
        int prevTime = msCounter.getTime();
        int currentTime = prevTime;

        while(!isStopped()){
            currentTime = msCounter.getTime();
            if((currentTime - prevTime) >= second) {
                int someRandomNumber = getRandomNumber(100);
                System.out.println("Generator: number = " + someRandomNumber);
                messageQueue.pushMessage(someRandomNumber);
                prevTime = currentTime;
            }
        }
    }

    private int getRandomNumber(int bound){
        return random.nextInt(bound);
    }

    private boolean isStopped(){
        return msCounter.isStopped();
    }
}
