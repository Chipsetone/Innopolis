package cretionalPatterns.builder;

/**
 * @author Семакин Виктор
 */
public abstract class AuWindowBuilder {

    protected AuWindow auWindow;

    public AuWindow Build(){
        auWindow = new AuWindow();

        return auWindow;
    }

    public AuWindow getAuWindow() {
        if(auWindow != null) {
            return auWindow;
        }

        return new AuWindow();
    }

    public abstract void buildModel();
    public abstract void buildHeight();
    public abstract void buildWidth();

}
