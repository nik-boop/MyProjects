package org.example;

import java.util.ArrayList;

public class LibraryManagement implements ILibraryManagment{
    private ArrayList<CardBase> listCards = new ArrayList();

    // Добавить описание непериодичского издания
    public void addCard(String name, int year, String publishingHouse, String autor, String city) {
        CardAperiodic cardAperiodic = new CardAperiodic(name, year, getNextID(),
                publishingHouse, autor, city);
        listCards.add(cardAperiodic);
    }

    // Добавить описание периодического издания
    public void addCard(String name, int year, String publishingHouse, int number, String city) {
        CardPeriodic cardPeriodic = new CardPeriodic(name, year, getNextID(),
                publishingHouse, number, city);
        listCards.add(cardPeriodic);
    }

    private int getNextID() {
        int nextID;
        boolean freeID = true;
        do {
            freeID = true;
            nextID = (int) (Math.random() * 100000);
            for (int i = 0; i < listCards.size(); i++) {
                if (listCards.get(i).getId() == nextID)
                    freeID = false;
            }
        } while (freeID == false);
        return nextID;
    }

    public void removeCard(int index) {
        if (index >= listCards.size())
            return;
        listCards.remove(index);
    }

    public CardBase getCard(int index) {
        if (index >= listCards.size())
            return null;
        return listCards.get(index);
    }

    public ArrayList<CardBase> findCard(String name) {
        ArrayList<CardBase> listReturn = new ArrayList<CardBase>();
        for (int i = 0; i < listCards.size(); i++) {
            CardBase card = listCards.get(i);
            if (card.getName().indexOf(name) >= 0)
                listReturn.add(card);
        }
        return listReturn;
    }

    public void printCards() {
        if (listCards.isEmpty()) {
            System.out.println("В базе нет зарегистрированных изданий.");
        }
        for (int i = 0; i < listCards.size(); i++) {
            CardBase card = listCards.get(i);
            String str = card.getInfo();
            System.out.println(str);
        }
    }
}
