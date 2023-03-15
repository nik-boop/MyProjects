import org.example.CardBase;
import org.example.LibraryManagement;
import java.util.ArrayList;
public class TestLibrary {
    public static void main(String[] args) {
        LibraryManagement libraryManagement = new LibraryManagement();
        libraryManagement.addCard("Сборник стихов Пушкина А.С.", 2014, "ПДЗ",
                "А.С. Пушкин", "Пенза");
        libraryManagement.addCard("Сборник стихов Есенина С.А.", 2014, "ПДЗ",
                "С.А. Есенин", "Пенза");
        libraryManagement.addCard("Компьютерра", 2014, "ООО Журнал 'Компьютерра'", 5, "Москва");
        System.out.println("Список зарегистрированных в базе изданий:");
        libraryManagement.printCards();
        System.out.println("-------------------------------------------");
        ArrayList<CardBase> baseArrayList = new ArrayList<CardBase>();
        System.out.println("Выполняем поиск зарегистрированных изданий, в названии которых встречается подстрока 'Абракадабра':");
        baseArrayList = libraryManagement.findCard("Абракадабра");
        if (baseArrayList.isEmpty()){
            System.out.println("Поиск не дал результатов");
        } else{
            for (int i = 0; i < baseArrayList.size(); i++) {
                System.out.println(baseArrayList.get(i).getInfo());
            }
        }
        System.out.println("-------------------------------------------");
        baseArrayList.clear();
        System.out.println("Выполняем поиск зарегистрированных изданий, в названии которых встречается подстрока 'Пушкин':");
        baseArrayList = libraryManagement.findCard("Пушкин");
        if (baseArrayList.isEmpty()){
            System.out.println("Поиск не дал результатов");
        } else{
            for (int i = 0; i < baseArrayList.size(); i++) {
                System.out.println(baseArrayList.get(i).getInfo());
            }
        }
        System.out.println("-------------------------------------------");
        baseArrayList.clear();
        System.out.println("Выполняем поиск зарегистрированных изданий, в названии которых встречается подстрока 'стих':");
        baseArrayList = libraryManagement.findCard("стих");
        if (baseArrayList.isEmpty()){
            System.out.println("Поиск не дал результатов");
        } else{
            for (int i = 0; i < baseArrayList.size(); i++) {
                System.out.println(baseArrayList.get(i).getInfo());
            }
        }
        System.out.println("-------------------------------------------");
    }
}
