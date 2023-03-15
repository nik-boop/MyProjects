package MyLibrary;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

abstract public class Item {
    protected Map<Integer, String> weightId = new HashMap<Integer, String>() {{
        put(1, "кг.");
        put(2, "г.");
    }};
    protected int id;
    protected String name;
    protected int weight;
    protected LocalDate shelfLife;
    protected String comment;

    public Item(int id, String name, LocalDate shelfLife, String comment) {
        this.id = id;
        this.name = name;
        this.shelfLife = shelfLife;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getShelfLife() {
        return shelfLife;
    }

    public int getWeight() {
        return weight;
    }

    public String getComment() {
        return comment;
    }

    abstract public String getInfo();
}

