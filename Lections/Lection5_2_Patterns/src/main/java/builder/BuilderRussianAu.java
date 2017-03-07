package builder;

/**
 * @author Семакин Виктор
 */
public class BuilderRussianAu extends AuWindowBuilder {
    @Override
    public void buildModel() {
        auWindow.setModel("RussianWindow");
    }

    @Override
    public void buildHeight() {
        auWindow.setHeight(200);
    }

    @Override
    public void buildWidth() {
        auWindow.setWidth(200);
    }
}
