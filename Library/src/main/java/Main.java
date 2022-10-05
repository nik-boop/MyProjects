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
        String[] columnName = books.columnsName;
        String[] values = new String[]{"20", "Book2", "Author1", "Издано: \"2003\"", "ABC\"Book\"", "2003", "roman"};
        for (int index = 0; index < columnName.length; index++){
            addRow.put(columnName[index], values[index]);
        }
        books.addNewRow(addRow);

        try {
            int count = 0;
            for(HashMap<String, String> row : books.getAllData()){
                count++;
                System.out.printf("%s%n", count);
                for (String name: row.keySet()) {
                    String value = row.get(name).toString();
                    System.out.printf("\t%-20s :: %s%n",name, value);
                }
            }

            books.deleteRow(2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        /*

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
        //---
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
