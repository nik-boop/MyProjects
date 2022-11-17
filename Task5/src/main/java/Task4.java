import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    public final static String fileName = "src/main/resources/fileTask4.txt";
    public final static String finalFileName = "src/main/resources/fileTask4Results.txt";

    public static String ReadFile(String file) {
        StringBuilder data = new StringBuilder();
        try {
            FileReader newReader = new FileReader(file);
            int character = newReader.read();
            while (character != -1) {
                data.append((char) character);
                character = newReader.read();
            }
            newReader.close();
        } catch (Exception e) {
            System.out.println("Данные из файла не прочитаны вследствие ошибки:");
            e.printStackTrace();
        }
        System.out.println("Чтение файла окончено");
        return data.toString();
    }

    public static void processDataFile(TreeSet<String> onlyWords, String fileData) {
        String[] lines = fileData.split("\n");
        String word;
        try {
            for (String line: lines) {
                Pattern patternSeparate = Pattern.compile( "\\w+", Pattern.UNICODE_CHARACTER_CLASS );
                Matcher matcher = patternSeparate.matcher(line);
                while ( matcher.find() ) {
                    word = matcher.group().toLowerCase();
                    onlyWords.add(word);
                }
            }
            System.out.println("Обработка данных завершена");
        } catch (Exception e) {
            System.out.println("Неудачная обработка данных. Error: ");
            e.printStackTrace();
        }
    }

    public static void fileWriting(String text) {
        try {
            FileWriter myWriter = new FileWriter(finalFileName);
            myWriter.write(String.valueOf(text));
            myWriter.close();
            System.out.println("Сохранение данных в файл");
        } catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        TreeSet<String> Words = new TreeSet<>();
        String infoFirstStep = ReadFile(fileName);
        StringBuilder sb = new StringBuilder();
        processDataFile(Words, infoFirstStep);
        System.out.println(Words);
        for (Object word: Words.toArray()) {
            sb.append(String.format("%s%n", word));
        }
        fileWriting(sb.toString());
    }
}