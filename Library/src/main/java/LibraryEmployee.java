public class LibraryEmployee extends Employee {

    public LibraryEmployee(int id, String name, String surname, String middleName, String address, int employeeId) {
        super(id, name, surname, middleName, address, employeeId);
    }

    public void giveBookToReader() {
        // Библиотечный работник ищет книгу и дает её читателю
    }
}
