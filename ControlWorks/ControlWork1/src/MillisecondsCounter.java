public class MillisecondsCounter {
    private int interval;
    private int currentTime = 0;
    private final Object lock = new Object();
    private boolean isStopped = false;

    public MillisecondsCounter(int milliseconds) {
        this.interval = milliseconds;
    }

    public void countTime(){
        while(!isStopped()){
            sleep(Thread.currentThread(), interval);
            if(isStopped()){
                return;
            }
            synchronized (lock) {
                currentTime += interval;
                System.out.println("time: " + currentTime);
                lock.notifyAll();
            }
        }
    }

    public int getTime(){
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return currentTime;
        }
    }

    private void sleep(Thread thread, Integer sleepTime){
        try {
            thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void stop(boolean stopped) {
        isStopped = stopped;
    }
}
