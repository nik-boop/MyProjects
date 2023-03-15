package org.example;

public class CardAperiodic extends CardBase {
    private String autor;

    public CardAperiodic(String name, int year, int id, String publishingHouse, String
            autor, String city) {
        super(name, year, id, publishingHouse, city);
        this.autor = autor;
    }

    @Override
    public String getInfo() {
        String str = autor + " " + name + " - " + city + ": " + publishingHouse + ", " +
                Integer.toString(year) + ".";
        return str;
    }

    public String getAutor() {
        return autor;
    }
}