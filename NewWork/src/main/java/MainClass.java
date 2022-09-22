public class MainClass {

    public static void main(String[] args){
        Dog dog = new Dog();
        dog.move();
        dog.eat();
        dog.breed();
        dog.talk();
        dog.feed();
        System.out.println("___");
        Crocodile crocodile = new Crocodile();
        crocodile.move();
        crocodile.eat();
        crocodile.breed();
        crocodile.talk();
        crocodile.feed();
        crocodile.numberPaws();
    }
}
