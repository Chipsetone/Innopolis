package tests.unit.parsers;

import com.semakin.exceptions.InnerResourceException;
import com.semakin.parsers.CollectionParser;
import com.semakin.validation.StringNumberValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Chi on 07.02.2017.
 */

class CollectionParserTest {

    // набор должен содержать лишь числа, унарный оператор "-" и пробелы
    /*
    Дефис и тире считать за минус
    если минус перед числом- то число отрицательное
    если после числа- то это ошибка
    */
    @Test
    void parseStringContent_ValidStringNotEmpty() throws InnerResourceException{

        String parserVictim = "5 20 -3   100 16090 -3333";
        CollectionParser parser =  getCollectionParser();
        List<Integer> expected = Arrays.asList(5, 20, -3, 100, 16090, -3333);

        List<Integer> actual = parser.parseStringContent(parserVictim);

        Assertions.assertTrue(expected.equals(actual));
    }
    @Test
    /**
     *  тестируем дефис и минус отсюда: http://webstyle.sfu-kras.ru/tire-defis
     */
    void parseStringContent_DifferentMinusSigns_Valid() throws InnerResourceException{
        String parserVictim = "-1 −2"; //
        CollectionParser parser =  getCollectionParser();
        List<Integer> expected = Arrays.asList(-1, -2);

        List<Integer> actual = parser.parseStringContent(parserVictim);

        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    void parseStringContent_Null_InnerResourceException(){
        CollectionParser parser = getCollectionParser();
        String parserVictim = null;

        try{
            parser.parseStringContent(parserVictim);
        }
        catch(InnerResourceException ex){
            System.out.println("отлично. то что надо.");
            return;
        }

        Assertions.fail("Ожидалось InnerResourceException");
    }

    @Test
    void parseStringContent_EmptyString() {
        CollectionParser parser = getCollectionParser();
        String emptyString = "";

        try{
            parser.parseStringContent(emptyString);
        }
        catch(InnerResourceException ex){
            System.out.println("отлично. то что надо.");
            return;
        }

        Assertions.fail("Ожидалось InnerResourceException");
    }

    @Test
    void parseStringContent_Whitespaces() {
        CollectionParser parser = getCollectionParser();
        String whiteSpaces = "    ";

        try{
            parser.parseStringContent(whiteSpaces);
        }
        catch(InnerResourceException ex){
            System.out.println("отлично. то что надо.");
            return;
        }

        Assertions.fail("Ожидалось InnerResourceException");
    }

    @Test
    void parseStringContent_invalidData_InnerResourceException() {
        CollectionParser parser = getCollectionParser();
        String invalidSymbolsSring = "asd876 fs78d6f   as7f669234@#%&^V";

        try {
            parser.parseStringContent(invalidSymbolsSring);
        }
        catch(InnerResourceException ex){
            System.out.println("отлично. то что надо.");
            return;
        }
        Assertions.fail("Ожидалось InnerResourceException");
    }

    @Test
    void arrayCompareTestHelper(){ // Давайте все вместе поможем автору понять как сравниваются массивы в Java
        List<Integer> arr1 = Arrays.asList(5, 10, -5);
        List<Integer> arr2familarToArr1 = Arrays.asList(5, 10, -5);

        System.out.println("arr1: " + arr1);
        System.out.println("arr2: " + arr2familarToArr1);
        System.out.println("arr1 == arr2 : " + (arr1 == arr2familarToArr1));
        System.out.println("arr1.equals( arr2) : " + (arr1.equals(arr2familarToArr1)));

        Assertions.assertEquals(arr1, arr2familarToArr1);
    }

    private CollectionParser getCollectionParser(){
        return new CollectionParser(new StringNumberValidator());
    }
}