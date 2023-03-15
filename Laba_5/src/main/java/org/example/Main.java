package org.example;

import MyApplication.FrameApp;
import MyApplication.LibraryManagement;
import MyLibrary.Bird;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LibraryManagement libraryManagement = new LibraryManagement();
        Bird b = new Bird(1,"Chicken", LocalDate.now(), 1.2, 1, "");
        libraryManagement.addBird(b);
        libraryManagement.addMeat(2,"Cow", LocalDate.now(), 4, 1, "");
        libraryManagement.addMeat(4,"Cow2", LocalDate.now(), 4, 1, "");
        libraryManagement.addFish(3,"Salmon", LocalDate.now(), 2.75, 1, "");
        new FrameApp(libraryManagement);
    }
}