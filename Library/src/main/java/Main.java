import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Book books = new Book();

        try {
            ArrayList<Integer> idBooks = books.getIdBooks();
            System.out.println(idBooks.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        /*
        HashMap<String , String> addRow = new HashMap<>();
        String[] columnName = new String[] {"id", "name", "author", "edition", "publisher", "publication_year", "category"};
        String[] values = new String[]{"20", "Book2", "Author1", "Издано: \"2003\"", "ABC\"Book\"", "2003", "roman"};
        for (int index = 0; index < columnName.length; index++){
            addRow.put(columnName[index], values[index]);
        }

        //books.addNewBook(addRow);

        try {
            ArrayList<Integer> ids = books.getIDsFromValue("author", "Author1");

            System.out.printf("Value %s include in row with ids: %s%n", "Author1", ids);

            HashMap<String, String> row1 = books.getRowFromId(1);
            for (String name: row1.keySet()) {
                String value = row1.get(name).toString();
                System.out.printf("%-20s :: %s%n",name, value);
            }

            ArrayList<HashMap<String, String>> rows = books.getRowsFromId(ids);

            System.out.printf("Value %s include in row with ids: %s%n", "Author1", ids);

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

            //HashMap<String, String> deleteRow = books.deleteRow(2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

         */
    }
}
