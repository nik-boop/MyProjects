package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.Human;

public class Main {
    private static void pr(int d){
        System.out.println(d);
    }
    private static void pr(String d){
        System.out.println(d);
    }
    private static void pr(LocalDate d){
        System.out.println(d.toString());
    }
    public static void main(String[] args) throws FileNotFoundException {
        var human = new ArrayList <Human>();
        Scanner sc = new Scanner(new FileInputStream("src/main/resources/Human"));
        String newLine;
        String Head = sc.nextLine();
        String[] data;
        String[] d2;
        int age;
        String firstName;
        String lastName;
        LocalDate birthDate;
        int weight;
        int Year;
        int Month;
        int Day;
        while (sc.hasNext()){
            newLine = sc.nextLine();
            data = newLine.split(",");
            age = Integer.parseInt(data[0]);
            firstName = data[1];
            lastName = data[2];
            d2 = data[3].split("/");
            birthDate = LocalDate.of(Integer.parseInt(d2[0]), Integer.parseInt(d2[1]), Integer.parseInt(d2[2]));
            weight = Integer.parseInt(data[4]);
            System.out.println(age);
            human.add(new Human(age, firstName, lastName, birthDate, weight));
        }
        human.stream().forEach(x -> System.out.println(x.toString()));
        System.out.println("---");
        human.stream().sorted(Comparator.comparing(Human::getFirstName).reversed()).forEach(x -> System.out.println(x.toString()));
        System.out.println("---");
        human.stream().filter(x -> x.getAge() > 20).forEach(x -> System.out.println(x.toString()));
        System.out.println("---");
        human.stream().limit(3).forEach(x -> System.out.println(x.toString()));
        System.out.println("---");
        System.out.println(human.stream().map(x -> x.getFirstName()).collect(Collectors.joining(" ")));
        System.out.println("---");
    }
}