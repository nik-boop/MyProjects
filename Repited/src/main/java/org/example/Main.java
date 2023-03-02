package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import org.example.Human;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("src/main/resources/Human"));
        var human = new ArrayList <Human>();
        String newLine = sc.nextLine();
        System.out.println(newLine);
        while (!newLine.equals("")){
            System.out.println(newLine);
            newLine = sc.nextLine();
        }
    }
}