package MyLibrary;

import java.time.LocalDate;

public class Bird extends Item{
    protected double weight;
    protected int weightType;

    public Bird(int id, String name, LocalDate shelfLife, double weight, int weightType, String comment) {
        super(id, name, shelfLife, comment);
        this.weight = weight;
        this.weightType = weightType;
    }

    @Override
    public String getInfo() {
        String str = String.format("Bird\n id:%s\n name:%s\n shelfLife:%s\n weight:%s%s\n comment:%s\n----", id, name, shelfLife, weight, weightId.get(weightType), comment);
        return str;
    }
    @Override
    public String getWeight() {
        return String.format("%s%s", weight, weightId.get(weightType));
    }
}
