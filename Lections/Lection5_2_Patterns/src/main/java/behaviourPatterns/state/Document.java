package behaviourPatterns.state;

/**
 * @author Семакин Виктор
 */
public class Document {
    DocumentStateEnum state;

    public void doAction(DocumentStateEnum state) {
        switch (this.state) {
            case LOAD:
                if (state == DocumentStateEnum.PREPARED) {
                    setState(state);
                    break;
                }
                printError();
                break;
            case PREPARED:
                if (state == DocumentStateEnum.SIGNED_1) {
                    setState(state);
                    break;
                }
                if (state == DocumentStateEnum.ERROR_SIGNED) {
                    setState(state);
                    break;
                }
                printError();
                break;
            case SIGNED_1:
                if (state == DocumentStateEnum.SENT) {
                    setState(state);
                    break;
                }
                printError();
                break;
            case SENT:
                if (state == DocumentStateEnum.RECEIVED) {
                    setState(state);
                    break;
                }
                printError();
                break;
            case RECEIVED:
                if (state == DocumentStateEnum.ERROR_SIGNED) {
                    setState(state);
                    break;
                }
                if (state == DocumentStateEnum.REJECTED) {
                    setState(state);
                    break;
                }
                printError();
                break;
            case SIGNED_2:
                if (state == DocumentStateEnum.RECEIVED) {
                    setState(state);
                    break;
                }
                printError();
                break;
            case ACCEPT:
                if (state == DocumentStateEnum.SENT_APPROVEMENT) {
                    setState(state);
                    break;
                }
                printError();
                break;
            case SENT_APPROVEMENT:
                if (state == DocumentStateEnum.RECEIVED_APPROVEMENT) {
                    setState(state);
                    break;
                }
                printError();
                break;
            case RECEIVED_APPROVEMENT:

                break;
            case ERROR_SIGNED:
                break;
            case REJECTED:
                break;
        }
    }

    private void printError(){
        System.out.println("Error");
    }

    public void setState(DocumentStateEnum state) {
        System.out.println("set " + state.toString());
        this.state = state;
    }
}
