import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Семакин Виктор
 */

public class MessageQueue {
    private List<Integer> generatedNumbers = new LinkedList<>();
    private Object generatedNumberslocker = new Object();

    public void pushMessage(int number){
        synchronized (generatedNumberslocker){
            generatedNumbers.add(number);
        }
    }

    public HashSet<Integer> getMessagesAndClean(){
        synchronized (generatedNumberslocker){
            HashSet<Integer> returnedCopy = new HashSet<>();

            for (int item :
                    generatedNumbers) {
                returnedCopy.add(item);
            }
            generatedNumbers.clear();
            return returnedCopy;
        }
    }
}
