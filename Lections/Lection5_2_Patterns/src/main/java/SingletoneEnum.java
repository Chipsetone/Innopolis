/**
 *
 * @author Семакин Виктор
 */
public enum SingletoneEnum {
    INSTANCE;
    private Object obj = null;
    public Object createInstance(){
        if (obj == null) {
            obj = new Object();
        }

        return obj;
    }
}
