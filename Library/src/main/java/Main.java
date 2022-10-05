import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        FileReader books = new FileReader("Books", new String[]{"id", "name", "author", "publisher", "edition", "publication_year", "category"}){
            @Override
            void log() {

            }
        };

        HashMap<String , String> addRow = new HashMap<>();
        for (String column :new String[]{"id", "name", "author", "publisher", "edition", "publication_year", "category"}){
            addRow.put(column, "123");
        }
        try {
            HashMap<String, String> delRow = books.deleteRow(1);
            for (String name: delRow.keySet()) {
                String value = delRow.get(name).toString();
                System.out.printf("%-20s :: %s%n",name, value);
            }
            books.addNewRow(addRow);
            HashMap<String, String> row = books.getRowFromId(1);
            for (String name: row.keySet()) {
                String value = row.get(name).toString();
                System.out.printf("%-20s :: %s%n",name, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /*
        try {
            books.startRead("id");
        } catch (Exception e) {
            System.out.println("Fail open reader");
            e.printStackTrace();
        }
        System.out.println(books.getReadColumnName());
        System.out.println(books.getNextLine());
        System.out.println(books.getNextLine());

        try {
            books.writeFile("Hi");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */
    }
}
