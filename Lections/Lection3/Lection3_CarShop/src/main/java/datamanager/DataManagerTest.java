package datamanager;

import models.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.mockito.Mockito.*;


/**
 *
 * @author Семакин Виктор
 */
class DataManagerTest {
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void serialize() {
        Car car = new Car(100,"Lada", "ABC");
       // ObjectOutputStream oos= mock(ObjectOutputStream.class);
        //when(oos).thenReturn(car);
    }

    @Test
    void serialize1() {

    }

    @Test
    void deserialize1() {

    }

    @Test
    void deserialize2() {

    }

}