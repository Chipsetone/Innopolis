package builder;

/**
 * @author Семакин Виктор
 */
public class BuilderMarketAu extends AuWindowBuilder {

    @Override
    public void buildModel() {
        auWindow.setModel("MarketWindow");
    }

    @Override
    public void buildHeight() {
        auWindow.setHeight(100);
    }

    @Override
    public void buildWidth() {
        auWindow.setWidth(100);
    }
}
