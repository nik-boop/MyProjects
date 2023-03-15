package org.example;

import java.util.ArrayList;

public interface ILibraryManagment {
    public void addCard(String name, int year, String publishingHouse, String autor,
                        String city);
    public void addCard(String name, int year, String publishingHouse, int number,
                        String city);
    public void removeCard(int index);
    public CardBase getCard(int index);
    public ArrayList<CardBase> findCard(String name);
    public void printCards();
}
