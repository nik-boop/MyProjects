package OOP;

public class Encapsulation {
    //Защищаемые данные

    private String safe_data = "Password:1234";
    private String password = "1234";

    //Публичный интерфейс для получения доступа к данным

    public String getSafe_data(String password) {
        //Проверка для доступа к данным
        if (password.equals(this.password)){
            return safe_data;
        } else {
            return "No access to data";
        }
    }

    public boolean setSafe_data(String password, String safe_data) {
        //Проверка для доступа к данным
        if (password.equals(this.password)){
            this.safe_data = "Password:" + safe_data;
            this.password = safe_data;
            return true;
        } else {
            return false;
        }
    }
}
