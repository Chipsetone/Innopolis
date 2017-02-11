package tests.unit.threading;

import com.semakin.threading.RunnableService;
import org.junit.jupiter.api.Test;

/**
 * тесты на понимание. в лог отображает только если запускать отдельно методы этого класса
 */
class RunnableServiceTest {
    @Test
    void addRunnableAction() {
        RunnableService service = new RunnableService();
        Integer actionsCount = 150;

        for (int i = 0; i < actionsCount; i++) {
            final Integer threadnumber = i;
            Runnable actionToAdd = new Runnable() {
                @Override
                public void run() {
                    Thread current = Thread.currentThread();
                    log(threadnumber + " дочерний поток! name: " + current.getName());
                }
            };

            service.runAction(actionToAdd);
        }

        log("Добавление потоков закончено. Главный поток завершен.");
    }

    @Test
    void terminateAllThreads() {
        RunnableService service = new RunnableService();
        Integer actionsCount = 150;

        for (int i = 0; i < actionsCount; i++) {
            final Integer threadnumber = i;
            Runnable actionToAdd = new Runnable() {
                @Override
                public void run() {
                    Thread current = Thread.currentThread();
                    log(threadnumber + " name: " + current.getName());
                    if(threadnumber == 75){
                        service.terminateAllThreads();
                        log(threadnumber + "TERMINATE_ALL!");
                    }
                }
            };

            service.runAction(actionToAdd);
        }

        log("Добавление потоков закончено. Главный поток завершен.");
    }

    private void log(String message){
        System.out.println(message);
    }
}