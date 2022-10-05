import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        FileReader books = new Books();

        HashMap<String , String> addRow = new HashMap<>();
        String[] columnName = books.columnsName;
        String[] values = new String[]{"20", "Book2", "Author1", "Издано: \"2003\"", "ABC\"Book\"", "2003", "roman"};
        for (int index = 0; index < columnName.length; index++){
            addRow.put(columnName[index], values[index]);
        }
        books.addNewRow(addRow);

        try {
            ArrayList<Integer> ids = books.getIDsFromValue("author", "Author1");

            System.out.printf("Value %s include in row with ids: %s", "Author1", ids);

            HashMap<String, String> row1 = books.getRowFromId(1);
            for (String name: row1.keySet()) {
                String value = row1.get(name).toString();
                System.out.printf("%-20s :: %s%n",name, value);
            }

            ArrayList<HashMap<String, String>> rows = books.getRowsFromId(ids);

            System.out.printf("Value %s include in row with ids: %s", "Author1", ids);

            int count = 0;
            for(HashMap<String, String> row : rows){
                count++;
                System.out.printf("%s%n", count);
                for (String name: row.keySet()) {
                    String value = row.get(name).toString();
                    System.out.printf("\t%-20s :: %s%n",name, value);
                }
            }

            count = 0;
            for(HashMap<String, String> row : books.getAllData()){
                count++;
                System.out.printf("%s%n", count);
                for (String name: row.keySet()) {
                    String value = row.get(name).toString();
                    System.out.printf("\t%-20s :: %s%n",name, value);
                }
            }

            HashMap<String, String> deleteRow = books.deleteRow(2);
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
