import java.sql.*;
import java.util.Scanner;

public class Task8 {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "Admin01.01";

    protected void add_employer(Statement stmt, int id, String name, String lastName, String dateBirth, String placeBirth, double salary, boolean maritalStatus) {
        try {
            stmt.executeQuery(String.format("INSERT IGNORE INTO People(id, firstname, surname, date_of_birth, place_of_birth, salary, marital_status) VALUE (%s,%s,%s,%s,%s,%s,%s)", id, name, lastName, dateBirth, placeBirth, salary, maritalStatus));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printouts(ResultSet res) {
        while (true) {
            try {
                if (!res.next()) break;
                System.out.println("----------------");
                System.out.println("ID             :" + res.getInt("id"));
                System.out.println("First Name     :" + res.getString("firstname"));
                System.out.println("Surname        :" + res.getString("surname"));
                System.out.println("Date of birth  :" + res.getString("date_of_birth"));
                System.out.println("Place of birth :" + res.getString("place_of_birth"));
                System.out.println("Salary         :" + res.getString("salary"));
                System.out.println("Marital status :" + res.getString("marital_status"));
                System.out.println("----------------");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Menu mainMenu = new Menu();


        try (
            /*
            DriverManager:Этот элемент управляет списком драйверов БД.
            Каждой запрос на соединение требует соответствующего драйвера.
            Первое совпадение даёт нам соединение.
            Connection: Этот интерфейс обеспечивает нас методами для работы с БД.
            Все взаимодействия с БД происходят исключительно через Connection.
            */
                Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            /*
            Statement: Для подтверждения SQL-запросов мы используем объекты,
            созданные с использованием этого интерфейса.
            */
                Statement stmt = conn.createStatement();
            /*
            ResultSet: Экземпляры этого элемента содержат данные, которые были получены в
            результате выполнения SQL – запроса. Он работает как итератор и “пробегает”
            по полученным данным.
            */
                ResultSet rs = stmt.executeQuery("SELECT * FROM mydb.people;");


        ) {
            mainMenu.addItem(1, "add employer", (stems, in) -> {
                try {
                    System.out.print("id: ");
                    int id = in.nextInt();
                    System.out.print("firstname: ");
                    String firstname = in.nextLine();
                    System.out.println();
                    System.out.print("surname: ");
                    String surname = in.nextLine();
                    System.out.println();
                    System.out.print("date_of_birth: ");
                    String date_of_birth = in.nextLine();
                    System.out.println();
                    System.out.print("place_of_birth: ");
                    String place_of_birth = in.nextLine();
                    System.out.println();
                    System.out.print("place_of_birth: ");
                    String salary = in.nextLine();
                    System.out.println();
                    System.out.print("place_of_birth: ");
                    String marital_status = in.nextLine();
                    System.out.println();
                    stems.executeQuery(String.format("INSERT IGNORE INTO People(" +
                                    "id, firstname, surname, date_of_birth, place_of_birth, salary, marital_status) " +
                                    "VALUE (%s,%s,%s,%s,%s,%s,%s)", id, firstname, surname, date_of_birth, place_of_birth,
                            salary, marital_status)
                    );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            mainMenu.addItem(2, "get employer by ID", (stems, in) -> {
                try {
                    System.out.print("Write id: ");
                    int id = in.nextInt();
                    System.out.println();
                    Task8.printouts(stems.executeQuery("SELECT * FROM mydb.people WHERE id = " + id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            mainMenu.addItem(3, "get employer by Name", (stems, in) -> {
                try {
                    System.out.print("Write name: ");
                    String name = in.next();
                    System.out.println();
                    Task8.printouts(stems.executeQuery("SELECT * FROM mydb.people WHERE firstname = '" + name + "'"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            mainMenu.addItem(4, "Date of birth", (stems, in) -> {
                try {
                    System.out.print("Write date of birth: ");
                    String date_of_birth = in.next();
                    System.out.println();
                    Task8.printouts(stems.executeQuery("SELECT * FROM mydb.people WHERE date_of_birth = '" + date_of_birth + "'"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            Scanner in = new Scanner(System.in);
            do {
                mainMenu.printMenu();
                System.out.print("> ");
                int selectedOption = in.nextInt();
                mainMenu.run(selectedOption, stmt, in);
            } while (true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
