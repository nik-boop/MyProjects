public class Dog extends Mammal{

    @Override
    public void eat() {
        System.out.println("Meat");
    }

    @Override
    public void move() {
        System.out.println("Run");

    }

    @Override
    public void talk() {
        System.out.println("Gav");

    }

    @Override
    public void breed(){
        System.out.println("Dog breed");
    };
}
