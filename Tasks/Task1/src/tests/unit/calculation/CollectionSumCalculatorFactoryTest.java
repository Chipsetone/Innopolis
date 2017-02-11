package tests.unit.calculation;

import com.semakin.calculation.CollectionSumCalculatorFactory;
import com.semakin.calculation.SumCalculatorable;
import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.CollectionParser;
import com.semakin.resourceGetters.ReaderGetterable;
import com.semakin.resourceGetters.ResourceStringGetter;
import tests.unit.mocks.ReaderGetterMock;
import com.semakin.validation.NumberValidatorable;
import com.semakin.validation.StringNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Created by Chi on 08.02.2017.
 */
class CollectionSumCalculatorFactoryTest {


    @Test
    void getResourceSumCalculator_ValidData() throws InnerResourceException {
        String someAddress = "someAddress1";
        String resourceValidContent = "1";
        boolean integerValidatorResultForAll = true;
        CollectionSumCalculatorFactory sumCalculatorFactory = getResourceSumCalculatorFactory(resourceValidContent, integerValidatorResultForAll);

        SumCalculatorable sumCalculator = sumCalculatorFactory.getResourceSumCalculator(someAddress);

        Assertions.assertNotNull(sumCalculator);
    }

    @Test
    void getResourceSumCalculator_InvalidResourceContent_Exception() throws InnerResourceException {
        String someAddress = "123";
        String resourceInvalidContent = "a";
        boolean integerValidatorResultForAll = false;
        CollectionSumCalculatorFactory sumCalculatorFactory = getResourceSumCalculatorFactory(resourceInvalidContent, integerValidatorResultForAll);

        try {
            SumCalculatorable sumCalculator = sumCalculatorFactory.getResourceSumCalculator(someAddress);
        }
        catch(InnerResourceException ex){
            System.out.println("отлично. то, что нужно.");
            return;
        }
        Assertions.fail("Ожидалось InnerResourceException");
    }

    private CollectionSumCalculatorFactory getResourceSumCalculatorFactory(String resourceContentStub, boolean integerValidatorResultForAll){
        ReaderGetterable readerGetterMock = getResourceReaderGetterMock();

        ResourceStringGetter resourceStringGetterMock = getResourceStringGetterMock(readerGetterMock, resourceContentStub);
        CollectionParser collectionParserMock = getCollectionParser();
        NumberValidatorable numberValidatorMock = getNumberValidatorMock(integerValidatorResultForAll);

        return new CollectionSumCalculatorFactory(resourceStringGetterMock, collectionParserMock, numberValidatorMock);
    }

    private ReaderGetterable getResourceReaderGetterMock(){
        HashMap<String, String> validValues = new HashMap<>();

        return new ReaderGetterMock(validValues);
    }

    private ResourceStringGetter getResourceStringGetterMock(ReaderGetterable readerGetter, String resourceStringMock) {
        return new ResourceStringGetter(readerGetter){
            @Override
            public String getResourceString(String resourceAddress) throws InnerResourceException {
                return resourceStringMock;
            }
        };
    }

    private CollectionParser getCollectionParser(){
        return new CollectionParser(getStringNumberValidator());
    }

    private StringNumberValidator getStringNumberValidator(){
        return new StringNumberValidator();
    }

    private NumberValidatorable getNumberValidatorMock(boolean integerValidatorResultForAll){
        return new NumberValidatorable() {
            @Override
            public boolean isNumberValid(int number) {
                return integerValidatorResultForAll;
            }
        };
    }
}