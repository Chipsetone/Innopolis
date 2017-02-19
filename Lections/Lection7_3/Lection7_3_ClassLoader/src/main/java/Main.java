import com.semakin.classloader.UrlJarClassLoader;
import com.semakin.lection5.serializer.ReflectionSerializator;

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

        Object beaver = constructor.newInstance("beaver");
        printSerializedObject(beaver);


    }

    public static void printSerializedObject(Object serializeVictim) throws IllegalAccessException {
        ReflectionSerializator serializator = new ReflectionSerializator();
        String serializedBeaver = serializator.serialize(serializeVictim);

        System.out.println(serializedBeaver);
    }
}
