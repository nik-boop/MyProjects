import MyLibrary.Bird;
import MyLibrary.Item;
import org.example.CardBase;
import MyApplication.LibraryManagement;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestLibrary2 {
    public static void main(String[] args) {
        LibraryManagement libraryManagement = new LibraryManagement();
        Bird b = new Bird(1,"Chicken", LocalDate.now(), 1.2, 1, "");
        libraryManagement.addBird(b);
        libraryManagement.addMeat(2,"Cow", LocalDate.now(), 4, 1, "");
        libraryManagement.addMeat(4,"Cow2", LocalDate.now(), 4, 1, "");
        libraryManagement.addFish(3,"Salmon", LocalDate.now(), 2.75, 1, "");
        libraryManagement.printItems();
        System.out.println("Список зарегистрированных в базе изданий:");
        libraryManagement.printItems();
        System.out.println("-------------------------------------------");
        ArrayList<Item> baseArrayList = new ArrayList();
        System.out.println("Выполняем поиск зарегистрированных продуктов, в названии которых встречается подстрока 'Chicken':");
        baseArrayList = libraryManagement.findItem("Chicken");
        if (baseArrayList.isEmpty()){
            System.out.println("Поиск не дал результатов");
        } else{
            for (int i = 0; i < baseArrayList.size(); i++) {
                System.out.println(baseArrayList.get(i).getInfo());
            }
        }
        System.out.println("-------------------------------------------");
        baseArrayList.clear();
        System.out.println("Выполняем поиск зарегистрированных продуктов, в названии которых встречается подстрока 'Cow':");
        baseArrayList = libraryManagement.findItem("Cow");
        if (baseArrayList.isEmpty()){
            System.out.println("Поиск не дал результатов");
        } else{
            for (int i = 0; i < baseArrayList.size(); i++) {
                System.out.println(baseArrayList.get(i).getInfo());
            }
        }
        System.out.println("-------------------------------------------");
        baseArrayList.clear();
        System.out.println("Выполняем поиск зарегистрированных продуктов, в названии которых встречается подстрока 'Salmon':");
        baseArrayList = libraryManagement.findItem("Salmon");
        if (baseArrayList.isEmpty()){
            System.out.println("Поиск не дал результатов");
        } else{
            for (int i = 0; i < baseArrayList.size(); i++) {
                System.out.println(baseArrayList.get(i).getInfo());
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println(libraryManagement.getItem(1).getInfo());
        System.out.println("-------------------------------------------");
        libraryManagement.removeItem(1);
        System.out.printf("%s\n", libraryManagement.getItem(1));
        System.out.println("-------------------------------------------");
    }
}
