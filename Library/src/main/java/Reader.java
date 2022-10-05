package main.java;

public class Reader extends Human {
    public Reader(int id, String name, String surname, String middleName, String address) {
        super(id, name, surname, middleName, address);
    }

    public void takeBook() {
        // Читатель просит библиотечного работника дать ему книгу
    }

    public void returnBook() {
        // Читатель дает книгу библиотечному
        // работнику и он возвращает книгу в библиотеку
    }
}
