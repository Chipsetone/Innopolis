package FactoryMethod;

/**
 * @author Семакин Виктор
 */
public class CreatorTrain extends Creator {

    private CreatorTrain() {
    }

    public static class CreatorHolder{
        private static final CreatorTrain CREATOR_TRAIN = new CreatorTrain();

        public static CreatorTrain getInstance(){
            return CREATOR_TRAIN;
        }
    }


    public Train create() {
        return new Train();
    }
}