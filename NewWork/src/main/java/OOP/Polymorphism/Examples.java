package OOP.Polymorphism;

import OOP.Inheritance.Child;

public class Examples {

    public static void main(String[] args){
        //Создаем два обекта Animal у которых можно вызвать функцию animalSound()
        Animal lion = new Lion();
        Animal dog = new Dog();
        lion.animalSound();
        dog.animalSound();
    }
}
