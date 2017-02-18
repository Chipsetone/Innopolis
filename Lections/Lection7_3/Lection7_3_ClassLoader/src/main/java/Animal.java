/**
 * @author Семакин Виктор
 */
public class Animal {
    private String name;
    private boolean isEatable = false;

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

    public void eat(){
        print(name + " eating");
    }

    public void sleep(){
        print(name + " sleeping");
    }

    public void doCrap(){
        print(name + " craping");
    }

    private void print(String message){
        System.out.println(message);

    }
}
