package MyApplication;

import MyLibrary.Bird;
import MyLibrary.Fish;
import MyLibrary.Item;
import MyLibrary.Meat;

import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryManagement implements ILibraryManagment {
    private int index;
    private ArrayList<Item> listItem = new ArrayList();

    @Override
    public void addBird(int id, String name, LocalDate shelfLife, double weight, int weightType, String comment) {
        listItem.add(new Bird(id, name, shelfLife, weight, weightType, comment));
    }

    @Override
    public void addBird(Bird bird) {
        listItem.add(bird);
    }

    @Override
    public void addFish(int id, String name, LocalDate shelfLife, double weight, int weightType, String comment) {
        listItem.add(new Fish(id, name, shelfLife, weight, weightType, comment));
    }
    @Override
    public void addFish(Fish bird) {
        listItem.add(bird);
    }

    @Override
    public void addMeat(int id, String name, LocalDate shelfLife, double weight, int weightType, String comment) {
        listItem.add(new Meat(id, name, shelfLife, weight, weightType, comment));
    }
    @Override
    public void addMeat(Meat bird) {
        listItem.add(bird);
    }

    private void start(){
        this.index = 0;
    }

    private Item getNext() {
        if (listItem.size() > index) {
            index++;
            return listItem.get(index-1);
        } else {
            return null;
        }
    }


    @Override
    public void removeItem(int id) {
        start();
        Item item = getNext();
        while (item != null){
            if (item.getId() == id) {
                listItem.remove(index-1);
                return;
            }
            item = getNext();
        }
    }

    @Override
    public Item getItem(int id) {
        start();
        Item item = getNext();
        while (item != null){
            if (item.getId() == id) {
                return item;
            }
            item = getNext();
        }
        return null;
    }

    @Override
    public ArrayList<Item> findItem(String name) {
        ArrayList<Item> findItems = new ArrayList();
        start();
        Item item = getNext();
        while (item != null){
            if (item.getName().indexOf(name) >= 0) {
                findItems.add(item);
            }
            item = getNext();
        }
        return findItems;
    }

    @Override
    public void printItems() {
       listItem.stream().forEach(x -> System.out.println(x.getInfo()));
    }
}
