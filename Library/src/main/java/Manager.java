package main.java;

public class Manager extends Employee {

    public Manager(int id, String name, String surname, String middleName, String address, int employeeId) {
        super(id, name, surname, middleName, address, employeeId);
    }

    public void createNewBook() {
        // Менеджер создает новую книгу
    }

    public void deleteBook() {
        // Менеджер удаляет книгу
    }

    public void findBooksByCategory() {
        // Менеджер ищет кол-во книг по категории
    }

    public void findBooksByReader() {
        // Менеджер ищет книги, которые брал конкретный читатель
    }

    public void findBooksDistribution() {
        // Менеджер определяет сколько книг было взято читателями
        // и сколько еще находится в библиотеке
    }
}
