package behaviourPatterns.chainOfResponsibilities;

/**
 * @author Семакин Виктор
 */
public class Main {
    public static void main(String[] args) {
        BabkiRumors babkiRumors = new BabkiRumors(){{
            isTrue = true;
        }};

        OfficeRumors officeRumors = new OfficeRumors();
        TeschaRumors teschaRumors = new TeschaRumors();

        officeRumors.setNextRumors(teschaRumors.setNextRumors(babkiRumors));

        officeRumors.chain("Somebody wont work in sb");

    }
}
