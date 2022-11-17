import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.lang.FunctionalInterface;
import java.util.Map;
import java.util.Scanner;

// функциональный интерфейс
@FunctionalInterface
interface MyFunction {
    void run(Statement stems, Scanner in);
}

class Item {
    public String label;
    public MyFunction myFunction;
    public Item(String label, MyFunction myFunction) {
        this.label = label;
        this.myFunction = myFunction;
    }
}

public class Menu {
    HashMap<Integer, Item> menu = new HashMap<>();

    public void printMenu() {
        System.out.println("Menu--------------------");
        for (Map.Entry<Integer, Item> entry : menu.entrySet()) {
            System.out.printf("%s - %-20s%n", entry.getKey(), entry.getValue().label);
        }
        System.out.println("------------------------");
    }

    public void addItem(int id, String label, MyFunction funk){
        menu.put(id, new Item(label, funk));
    }

    public void run(int index, Statement stems, Scanner in){
        menu.get(index).myFunction.run(stems, in);
    }

}
