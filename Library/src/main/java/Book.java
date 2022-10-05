import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class Book {

    private final String rootPath = "src/main/resources";
    private String dataDir = "Books";
    private String[] columnsName  = new String[] {"id", "name", "author", "publisher", "edition", "publication_year", "category"};

    private int length = 0;
    private int Index = 0;
    private String readColumnName;
    private java.io.FileReader reader;

    private ArrayList<HashMap<String, String>> allData;

    private ArrayList<Integer> IDBooks = new ArrayList<>();


    public Book() {
        try {
            allData = getAllData();
            getIdBooks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    private boolean checkColumnName(String column){
        if (column.equals("log")) return true;
        for (String element : columnsName) {
            if (element.equals(column)) {
                return true;
            }
        }
        return false;
    }

    public void getCountRows() throws Exception {
        startRead(columnsName[0]);
        while (true) {
            if (stepOnNextLine() == -1) break;
        }
        length = Index;
        stopRead();
    }

    public ArrayList<Integer> getIdBooks() throws Exception {
        startRead("id");
        String id = getLine();
        while (id != null) {
            IDBooks.add(Integer.parseInt(id));
            id = getLine();
        }
        stopRead();
        return IDBooks;
    }

    private void startRead(String column) throws Exception {
        if(!checkColumnName(column)) throw new Exception("don`t find column");
        reader = new java.io.FileReader(rootPath + "/" + dataDir + "/" + column);
        Index = 0;
        readColumnName = getLine();
    }

    private void stopRead(){
        Index = 0;
        reader = null;
        readColumnName = null;
    }

    private void rewriteColumn(String column, String data){
        try {
            FileWriter myWriter = new FileWriter(rootPath + "/" + dataDir + "/" + column);
            myWriter.write(data);
            myWriter.close();
        } catch (Exception e) {
            System.out.println("Text saving failed.");
            e.printStackTrace();
        }
    }

    protected void addToColumn(String column, String row) {
        try {
            Files.write(Path.of(rootPath + "/" + dataDir + "/" + column), row.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println("Text append failed.");
            e.printStackTrace();
        }
    }

    private int stepOnNextLine(){
        int character = 0;
        try {
            character = reader.read();
            while (character != -1) {
                if (character == 10){
                    Index++;
                    return Index;
                }
                character = reader.read();
            }
        } catch (IOException e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        return -1;
    }

    private String getLine(){
        int character = 0;
        StringBuilder sb = new StringBuilder();
        try {
            character = reader.read();
            while (character != -1) {
                if (character == 10){
                    Index++;
                    return sb.toString();
                }
                sb.append((char) character);
                character = reader.read();
            }
        } catch (IOException e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        return null;
    }

    private String getValueFromIndex(int index) throws Exception {
        if(Index >index) throw new Exception("Position read after desired position. Restart Reader!");
        while (Index != index) {
            stepOnNextLine();
        }
        return getLine();
    }

    public ArrayList<Integer> getIndexesFromValue(String column, String value){
        try {
            startRead(column);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> id = new ArrayList<>();
        String getValue = getLine();
        while (getValue != null) {
            if (getValue.equals(value)){
                id.add(Index -1);
            }
            getValue = getLine();
        }
        return id;
    }

    public HashMap<String, String> getRowFromIndex(int index) throws Exception {
        HashMap<String, String> bookInfo = new HashMap<>();
        for (String column :columnsName){
            startRead(column);
            bookInfo.put(column, getValueFromIndex(index));
        }
        return bookInfo;
    }

    public ArrayList<HashMap<String, String>> getRowsFromIndex(ArrayList<Integer> ids) throws Exception {
        ArrayList<HashMap<String, String>> books = new ArrayList<>();
        for (int index :ids){
            books.add(getRowFromIndex(index));
        }
        return books;
    }
    public ArrayList<HashMap<String, String>> getAllData() throws Exception {
        getCountRows();
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        for (int index = 1; index < length; index++){
            HashMap<String, String> bookInfo = new HashMap<>();
            for (String column :columnsName){
                startRead(column);
                bookInfo.put(column, getValueFromIndex(index));
            }
            data.add(bookInfo);
        }
        return data;
    }

    private String deleteRowFromColumn(String column, int index) throws Exception {
        StringBuilder saveData = new StringBuilder();
        String deleteValue;
        startRead(column);
        saveData.append(readColumnName).append("\n");
        while (Index != index) {
            saveData.append(getLine()).append("\n");
        }
        deleteValue = getLine();
        while (Index < length) {
            saveData.append(getLine()).append("\n");
        }
        rewriteColumn(column, saveData.toString());
        return deleteValue;
    }

    public HashMap<String, String> deleteRow(int index) throws Exception {
        getCountRows();
        HashMap<String, String> delRow = new HashMap<>();
        for (String column :columnsName){
            delRow.put(column, deleteRowFromColumn(column, index));
        }
        length--;
        return delRow;
    }

    public HashMap<String, String> deleteRowByID(int id) throws Exception {
        IDBooks.indexOf(id);
        int index = getIndexesFromValue("id", Integer.toString(id)).get(0);
        return deleteRow(index);
    }

    public void addNewBook(HashMap<String, String> newRow) throws Exception {
        if ( IDBooks.contains(Integer.parseInt(newRow.get("id")))) throw new Exception("Index already exist");
        for (String column :columnsName){
            addToColumn(column, newRow.get(column)+"\n");
        }
        length++;
    }

    public void addNewBook(int id, String name, String author, String edition, String publisher, int publication_year, String category) throws Exception {
        HashMap<String , String> addRow = new HashMap<>();
        addRow.put("id", Integer.toString(id));
        addRow.put("author", author);
        addRow.put("edition", edition);
        addRow.put("publisher", publisher);
        addRow.put("publication_year", Integer.toString(publication_year));
        addRow.put("category", category);
        addNewBook(addRow);
    }

    public String[] getColumnsName(){
        return columnsName;
    }
    public ArrayList<HashMap<String, String>> getAllDataList(){
        return allData;
    }
    public ArrayList<Integer> getIDBooks(){
        return IDBooks;
    }
}
