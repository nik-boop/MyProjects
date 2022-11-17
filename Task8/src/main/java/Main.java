import java.sql.*;

public class Main {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "Admin01.01";
    static final String QUERY = "SELECT * FROM people";

    public static void main(String[] args) {
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
                ResultSet rs = stmt.executeQuery(QUERY);)
        {

            while (rs.next()) {
                System.out.println("=========================== ID: " + rs.getInt("id") + " ==========================");
                System.out.println("First Name: " + rs.getString("firstname"));
                System.out.println("Surname: " + rs.getString("surname"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("************************************************************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}