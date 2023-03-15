package org.example;

import MyApplication.LibraryManagement;
import MyLibrary.Bird;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LibraryManagement l = new LibraryManagement();
        Bird b = new Bird(1,"Chicken", LocalDate.now(), 1.2, 1, "");
        l.addBird(b);
        l.addMeat(2,"Cow", LocalDate.now(), 4, 1, "");
        l.addFish(3,"Salmon", LocalDate.now(), 2.75, 1, "");
        l.printItems();
    }
}