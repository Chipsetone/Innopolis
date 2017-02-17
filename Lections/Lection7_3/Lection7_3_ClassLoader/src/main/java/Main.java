import com.semakin.classloader.UrlJarClassLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Семакин Виктор
 */
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        UrlJarClassLoader loader = new UrlJarClassLoader();
        Class animalClass = loader.findClass("Animal");
        Constructor constructor = animalClass.getConstructor(String.class);

        Object objBeaver = constructor.newInstance("beaver");
        Animal beaver = (Animal)objBeaver;
       // ReflectionSerializer
        System.out.println(animalClass);
        System.out.println(beaver);

        animalInAction(beaver);
    }

    private static void animalInAction(Animal animal){

        System.out.println("do something in main");

        animal.eat();
        animal.doCrap();
        animal.sleep();
    }
}
