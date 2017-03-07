/**
 * @author Семакин Виктор
 */
public class SingletonHolderExample {

    static class SingletonHolder{
        private static final SingletonHolderExample INSTANCE = new SingletonHolderExample();

        public SingletonHolderExample getINSTANCE(){
            return INSTANCE;
        }
    }
}
