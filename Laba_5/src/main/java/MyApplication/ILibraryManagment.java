package MyApplication;

import MyLibrary.Bird;
import MyLibrary.Fish;
import MyLibrary.Item;
import MyLibrary.Meat;
import org.example.CardBase;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ILibraryManagment {
    public void addBird(int id, String name, LocalDate shelfLife, double weight, int weightType, String comment);

    void addBird(Bird bird);

    public void addFish(int id, String name, LocalDate shelfLife, double weight, int weightType, String comment);

    void addFish(Fish bird);

    public void addMeat(int id, String name, LocalDate shelfLife, double weight, int weightType, String comment);

    void addMeat(Meat bird);

    public void removeItem(int id);
    public Item getItem(int id);
    public ArrayList<Item> findItem(String name);
    public ArrayList<Item> findItem(int id);
    public void printItems();
}
