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
        int max_length = 0;
        int last_index = 0;
        for (Map.Entry<Integer, Item> entry : menu.entrySet()) {
            max_length = Integer.max(entry.getValue().label.length(), max_length);
            last_index = entry.getKey();
        }
        max_length += Integer.toString(last_index).length() + 3;
        System.out.println("Menu" + "-".repeat(max_length - 4));
        for (Map.Entry<Integer, Item> entry : menu.entrySet()) {
            System.out.printf("%s - %-20s%n", entry.getKey(), entry.getValue().label);
        }
        System.out.println("-".repeat(max_length));
    }

    public void addItem(int id, String label, MyFunction funk) {
        menu.put(id, new Item(label, funk));
    }

    public void run(int index, Statement stems, Scanner in) {
        menu.get(index).myFunction.run(stems, in);
    }

}
