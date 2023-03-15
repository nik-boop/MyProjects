package MyLibrary;

import java.time.LocalDate;

public class Meat extends Item{
    protected double weight;
    protected int weightType;

    public Meat(int id, String name, LocalDate shelfLife, double weight, int weightType, String comment) {
        super(id, name, shelfLife, comment);
        this.weight = weight;
        this.weightType = weightType;
    }

    @Override
    public String getInfo() {
        String str = String.format("Meat\n id:%s\n name:%s\n shelfLife:%s\n weight:%s%s\n comment:%s\n----", id, name, shelfLife, weight, weightId.get(weightType), comment);
        return str;
    }
}
