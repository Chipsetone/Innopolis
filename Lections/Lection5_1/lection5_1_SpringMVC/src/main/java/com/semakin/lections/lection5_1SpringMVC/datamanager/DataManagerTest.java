package com.semakin.lections.lection5_1SpringMVC.datamanager;


import com.semakin.lections.lection5_1SpringMVC.models.Car;
import org.junit.gen5.api.AfterEach;
import org.junit.gen5.api.BeforeEach;
import org.junit.gen5.api.Test;

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