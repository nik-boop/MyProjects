import java.util.HashMap;

public class Books extends FileReader{

    private final String dataDir = "Books";
    protected final String[] columnsName = new String[] {"id", "name", "author", "publisher", "edition", "publication_year", "category"};

    public Books() {

    }


    public void addNewBook(HashMap<String, String> newRow) {
        // Создание статистических данных
        // Запись статистики
        super.addNewRow(newRow);
    }

    @Override
    void log(String text){
        super.addToColumn("log", text);
    }
}
