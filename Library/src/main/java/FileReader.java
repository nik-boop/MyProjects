import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class FileReader {

    private final String rootPath = "src/main/resources";
    private final String dataDir;
    protected final String[] columnsName;

    private int length = 0;
    private int ID = 0;
    private String readColumnName;
    private java.io.FileReader reader;

    public FileReader(String dataDir, String[] columnsName) {
        this.dataDir = dataDir;
        this.columnsName = columnsName;
    }

    private boolean checkColumnName(String column){
        for (String element : columnsName) {
            if (element == column) {
                return true;
            }
        }
        return false;
    }

    public int getCountRows() throws Exception {
        startRead(columnsName[0]);
        while (true) {
            if (stepOnNextLine() == -1) break;
        }
        length = ID;
        stopRead();
        return length;
    }

    public void startRead(String column) throws Exception {
        if(!checkColumnName(column)) throw new Exception("don`t find column");
        reader = new java.io.FileReader(rootPath + "/" + dataDir + "/" + column);
        ID = 0;
        readColumnName = getNextLine();

    }

    public void stopRead(){
        ID = 0;
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

    private void addToColumn(String column, String row){
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
                    ID++;
                    return ID;
                }
                character = reader.read();
            }
        } catch (IOException e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        return -1;
    }

    private String getNextLine(){
        int character = 0;
        StringBuilder sb = new StringBuilder();
        try {
            character = reader.read();
            while (character != -1) {
                if (character == 10){
                    ID++;
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

    public String getValueFromID(int id) throws Exception {
        if(ID>id) throw new Exception("Position read after desired position. Restart Reader!");
        while (ID != id) {
            stepOnNextLine();
        }
        return getNextLine();
    }

    public ArrayList<Integer> getIDsFromValue(String column, String value) throws Exception {
        startRead(column);
        ArrayList<Integer> id = new ArrayList<>();
        String getValue = getNextLine();
        while (getValue != null) {
            if (getValue.equals(value)){
                id.add(ID);
            }
            getValue = getNextLine();
        }
        return id;
    }

    public HashMap<String, String> getRowFromId(int id) throws Exception {
        HashMap<String, String> bookInfo = new HashMap<>();
        for (String column :columnsName){
            startRead(column);
            bookInfo.put(column, getValueFromID(id));
        }
        return bookInfo;
    }

    public ArrayList<HashMap<String, String>> getRowsFromId(ArrayList<Integer> ids) throws Exception {
        ArrayList<HashMap<String, String>> books = new ArrayList<>();
        for (int id :ids){
            books.add(getRowFromId(id));
        }
        return books;
    }
    public ArrayList<HashMap<String, String>> getAllData() throws Exception {
        getCountRows();
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        for (int id = 1; id < length; id++){
            HashMap<String, String> bookInfo = new HashMap<>();
            for (String column :columnsName){
                startRead(column);
                bookInfo.put(column, getValueFromID(id));
            }
            data.add(bookInfo);
        }
        return data;
    }

    private String deleteRowFromColumn(String column, int id) throws Exception {
        StringBuilder saveData = new StringBuilder();
        String deleteValue;
        startRead(column);
        saveData.append(readColumnName).append("\n");
        while (ID != id) {
            saveData.append(getNextLine()).append("\n");
        }
        deleteValue = getNextLine();
        while (ID < length) {
            saveData.append(getNextLine()).append("\n");
        }
        rewriteColumn(column, saveData.toString());
        return deleteValue;
    }

    public HashMap<String, String> deleteRow(int id) throws Exception {
        getCountRows();
        HashMap<String, String> delRow = new HashMap<>();
        for (String column :columnsName){
            delRow.put(column, deleteRowFromColumn(column, id));
        }
        length--;
        return delRow;
    }

    public void addNewRow(HashMap<String, String> newRow){
        for (String column :columnsName){
            addToColumn(column, newRow.get(column)+"\n");
        }
        length++;
    }

    public String getReadColumnName() {
        return readColumnName;
    }

    abstract void log();
}
