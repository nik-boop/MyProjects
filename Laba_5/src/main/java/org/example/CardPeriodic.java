package org.example;

public class CardPeriodic extends CardBase {
    private int number;
    public CardPeriodic(String name, int year, int id, String publishingHouse, int number,
                        String city) {
        super(name, year, id, publishingHouse, city);
        this.number = number;
    }
    @Override
    public String getInfo() {
        String str = name + " - " + city + ": " + publishingHouse + ", " + Integer.toString(year) + " - № " + Integer.toString(number) + ".";
        return str;
    }
    public int getNumber(){
        return number;
    }
}
