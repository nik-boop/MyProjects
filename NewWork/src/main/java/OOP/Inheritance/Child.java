package OOP.Inheritance;

import OOP.Inheritance.Parent;

public class Child extends Parent {
    public void childFunction(){
        System.out.println("Start childFunction work");
        parentFunction_1();
        parentFunction_2();
        parentFunction_3();
        System.out.println("Finish childFunction work");
    }
}
