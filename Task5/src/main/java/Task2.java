import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task2 {
    public final static String fileName = "src/main/resources/fileTask2.txt";
    public static boolean isEnd = true;

    public static HashMap<String, String> ReadFile(String file) throws IOException {
        HashMap<String, String> data = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        FileReader reader = new FileReader(file);
        int character = 0;
        try {
            while (character != -1) {
                character = reader.read();
                if (character == 13) {
                    continue;
                }
                if (character == 10) {
                    data.put(sb.toString().split(":")[0], sb.toString().split(":")[1]);
                    sb.delete(0, sb.length());
                    continue;
                }
                sb.append((char) character);
            }
        } catch (IOException e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        reader.close();
        return data;
    }

    public static void main(String[] args) {
        HashMap<String, String> data;
        try {
            data = ReadFile(fileName);
        } catch (IOException e) {
            System.out.println("Can not open file ");
            throw new RuntimeException(e);
        }
        Scanner in = new Scanner(System.in);
        String input;
        while (isEnd) {
            System.out.print("Ключ: ");
            input = in.nextLine().strip();
            System.out.println("Значение: " + data.get(input));
        }
    }
}

