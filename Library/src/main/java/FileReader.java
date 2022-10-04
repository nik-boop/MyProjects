import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class FileReader {

    private final int CountColumn = 7;
    private String[] path;
    private String separate;
    private ArrayList<ArrayList> lineList = new ArrayList<>();

    public FileReader(String[] path, String separate) {
        this.path = path;
        this.separate = separate;
    }


    private String readFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            java.io.FileReader myReader = new java.io.FileReader(path);
            int character = myReader.read();
            while (character != -1) {
                sb.append((char) character);
                character = myReader.read();
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        return sb.toString();
    }

    public ArrayList<ArrayList> getBooksInfo(HashMap<Integer, String> MapCount){
        ArrayList<String> line = new ArrayList();
        StringBuilder sb = new StringBuilder();
        try {
            java.io.FileReader myReader = new java.io.FileReader(this.path);
            int character = myReader.read();
            while (character != -1) {
                sb.append((char) character);
                character = myReader.read();
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public ArrayList<ArrayList> getBooksFromId(){
        return new ArrayList<>();
    }

    public ArrayList<ArrayList> getAllBooks(){
        return new ArrayList<>();
    }


    public void writeFile(String text) {
        try {
            FileWriter myWriter = new FileWriter(this.path);
            myWriter.write(text);
            myWriter.close();
            System.out.println("Text saved successfully.");
        } catch (Exception e) {
            System.out.println("Text saving failed.");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSeparate() {
        return separate;
    }

    public void setSeparate(String separate) {
        this.path = separate;
    }

    public ArrayList<ArrayList> getLineList() {
        return lineList;
    }

    public void setLineList(ArrayList<ArrayList> lineList) {
        this.lineList = lineList;
    }
}
