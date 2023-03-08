package org.example;

import java.time.LocalDate;

public class Human {
    int age;
    String firstName;
    String lastName;
    LocalDate birthDate;
    int weight;

    public Human(int age,
                 String firstName,
                 String lastName,
                 LocalDate birthDate,
                 int weight) {
        setAge(age);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setWeight(weight);
    }

    @Override
    public String toString() {
        return String.format("age - %s firstName - %s lastName - %s birthDate - %s weight - %s", age, firstName, lastName, birthDate, weight);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
