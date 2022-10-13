import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class Book {

    /**
     *Путь от корневой директории до папки с ресурсамти
     */
    private final String rootPath = "src/main/resources";
    /**
     *Папка с файлаами атрибутов книг
     */
    private final String dataDir = "Books";
    /**
     * Имена фалвов хранящих информацию о книге
     */
    private final String[] columnsName  = new String[] {"id", "name", "author", "publisher", "edition", "publication_year", "category"};

    /**
     * Количество записей о книгах
     */
    private int length = 0;
    /**
     * Текущий номер просматриваемой строки
     */
    private int Index = 0;
    /**
     * Просматриваемый столбец
     */
    private String readColumnName;
    /**
     * {@code java.io.FileReader}
     */
    private java.io.FileReader reader;

    /**
     *  {@code ArrayList} хранящий HashMap<String, String> с записями атрибутов всех книг
     */
    private ArrayList<HashMap<String, String>> allData;

    /**
     * ID всех книг
     * После создания экземпляра нужно обновить
     */
    private ArrayList<Integer> IDBooks = new ArrayList<>();

    /**
     * Creator ласса
     * при создании экземпляра считывает всю информацию в переменную addData
     * и получает ID книг
     */
    public Book() {
        try {
            allData = getAllData();
            getIdBooks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Проверка есть ли у книиги заданный атрибут
     * @param column имя атрибута
     * @return true ели токой атрибут есть false если нет
     */
    private boolean checkColumnName(String column){
        if (column.equals("log")) return true;
        for (String element : columnsName) {
            if (element.equals(column)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Считает количество строк и записывает в переменную {@code length}
     * @throws Exception В случае если неполучается открыть файл вызывет исключение
     */
    public void getCountRows() throws Exception {
        startRead(columnsName[0]);
        while (true) {
            if (stepOnNextLine() == -1) break;
        }
        length = Index;
        stopRead();
    }

    /**
     * Получает ID сех книг
     * @return Возвращает {@code ArrayList<Integer>} с ID сех книг
     * @throws Exception В случае если неполучается открыть файл вызывет исключение
     */
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

    /**
     * Начинат считывание файла с атрибутом книги
     * @param column имя атрибута для считывания
     * @throws Exception В случае если неполучается открыть файл вызывет исключение
     */
    private void startRead(String column) throws Exception {
        // Проверка на существование заданного атрибута
        if(!checkColumnName(column)) throw new Exception("don`t find column");
        reader = new java.io.FileReader(rootPath + "/" + dataDir + "/" + column);
        Index = 0;
        readColumnName = getLine();
    }

    /**
     * Остановка чтения файла
     */
    private void stopRead(){
        Index = 0;
        reader = null;
        readColumnName = null;
    }

    /**
     * Внутренний метод для перезаписи атрибута всех книг
     * используется для удаления строк
     * @param column атрибут
     * @param data содержание файла - удаляемая строка
     */
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

    /**
     * Добавление в атрибут нового занчения
     * @param column атрибут
     * @param row значение
     */
    private void addToColumn(String column, String row) {
        try {
            // Дозапись в существующий файл нового значения
            Files.write(Path.of(rootPath + "/" + dataDir + "/" + column), row.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println("Text append failed.");
            e.printStackTrace();
        }
    }

    /**
     * Функция для перехода по строкам атрибутов книг
     * @return Идекс текущей строки, если файл кончиля вернется -1
     */
    private int stepOnNextLine(){
        // переменнаяя для записи считываемого символа
        int character = 0;
        try {
            character = reader.read();
            // пока в файле есть симвлы считываем их
            while (character != -1) {
                // Если считаный символ это переход на повую строку то считывание превывается
                // и возвращается индекс новой строки
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
        // если файл кончиля вернется -1
        return -1;
    }

    /**
     * Функция для считывания текущей строки читаемого файла.
     * После считывания переходит на новую строку
     * @return Значение в строкке, если ничего считать не удалось — файл кончиля, вернется null
     */
    private String getLine(){
        // переменнаяя для записи считываемого символа
        int character = 0;
        // StringBuilder для сохранения прочтенных символов
        StringBuilder sb = new StringBuilder();
        try {
            // Считывание символа
            character = reader.read();
            // пока в файле есть симвлы считываем их
            while (character != -1) {
                if (character == 10){
                    // Если считаный символ это переход на повую строку то считывание превывается
                    // и возвращается значение считанной строки
                    Index++;
                    return sb.toString();
                }
                // Схраняем считаный символ
                sb.append((char) character);
                character = reader.read();
            }
        } catch (IOException e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        // если ничего считать не удалось — файл кончиля, вернется null
        return null;
    }

    /**
     * функция для получения значения строки с аданым индексом
     * @param index Индекс считываемой строки
     * @return значение в считываемой строке
     * @throws Exception Если индекс запрашиваемой строки менньше текущего то вызывется исключение
     * т.к прочесть значение строки будет невозможно
     */
    private String getValueFromIndex(int index) throws Exception {
        if (Index > index) throw new Exception("Position read after desired position. Restart Reader!");
        while (Index != index) {
            stepOnNextLine();
        }
        return getLine();
    }

    /**
     * Функция проверяет есть ли у книги заданный атрибут и возвращает Индексы всех книг с таким атрибутом
     * @param column атрибут книги
     * @param value искомое значение
     * @return {@code ArrayList<Integer>} с Индексами! строк где содержится информация о книге
     */
    public ArrayList<Integer> getIndexesFromValue(String column, String value){
        try {
            startRead(column);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> id = new ArrayList<>();
        String getValue = getLine();
        // Проходим по всем значениям атрибутов и ищем соотвветствия,
        // если соответствие найдено добавляем индекс к списку отввета
        while (getValue != null) {
            if (getValue.equals(value)){
                id.add(Index -1);
            }
            getValue = getLine();
        }
        return id;
    }

    /**
     * Функция для получения всей инфрмации о книге по индексу
     * @param index индекс получемой строки
     * @return {@code HashMap<String, String>} где key это атрибут а value это значение атрибута
     * @throws Exception В случае если неполучается открыть файл вызывет исключение
     */
    public HashMap<String, String> getRowFromIndex(int index) throws Exception {
        HashMap<String, String> bookInfo = new HashMap<>();
        // Обходим все атрибуты книги
        for (String column :columnsName){
            startRead(column);
            // Добавляем ативибу и значение этого атрибута
            bookInfo.put(column, getValueFromIndex(index));
        }
        return bookInfo;
    }

    /**
     * Функция для получения всей инфрмации о книгах по индексам
     * @param Indexes Индексы искомых книг
     * @return {@code ArrayList<HashMap<String, String>} где key это атрибут а value это значение атрибута
     * @throws Exception В случае если неполучается открыть файл вызывет исключение
     */
    public ArrayList<HashMap<String, String>> getRowsFromIndex(ArrayList<Integer> Indexes) throws Exception {
        ArrayList<HashMap<String, String>> books = new ArrayList<>();
        // Обходим все индексы
        for (int index :Indexes){
            books.add(getRowFromIndex(index));
        }
        return books;
    }

    /**
     * Функция для получения всей информации о книгах в виде {@code ArrayList<HashMap<String, String>>}
     * @return массив с словарями информации о книгах
     * @throws Exception В случае если неполучается открыть файл вызывет исключение
     */
    public ArrayList<HashMap<String, String>> getAllData() throws Exception {
        getCountRows();
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        for (int index = 1; index < length; index++){
            data.add(getRowFromIndex(index));
        }
        return data;
    }

    /**
     * Дополнительная функция для {@linkplain Book#deleteRow(int) deleteRow(int index)}
     * удаляет запись о книге по индексу из строки с заданым атрибутом
     * @param column атрибут
     * @param index индекс строки
     * @return возвращает удаленное значение
     * @throws Exception В случае если неполучается открыть файл вызывет исключение
     */
    private String deleteRowFromColumn(String column, int index) throws Exception {
        StringBuilder saveData = new StringBuilder();
        String deleteValue;
        // начало считывания всех атрибутов
        startRead(column);
        // сохранение имени колонки атрибута
        saveData.append(readColumnName).append("\n");
        // считывание всех значений до индекса
        while (Index != index) {
            saveData.append(getLine()).append("\n");
        }
        // получение значения по индексу
        deleteValue = getLine();
        // сичтывание всех значений после индекса
        while (Index < length) {
            saveData.append(getLine()).append("\n");
        }
        // перезапись файла с атрибутами книг
        rewriteColumn(column, saveData.toString());
        return deleteValue;
    }

    /**
     * Удаляет информацию о книге по индексу
     * @param index индекс
     * @return информация о удаленной книге
     * @throws Exception В случае если неполучается открыть файл вызывет исключение
     */
    public HashMap<String, String> deleteRow(int index) throws Exception {
        getCountRows();
        HashMap<String, String> delRow = new HashMap<>();
        for (String column :columnsName){
            delRow.put(column, deleteRowFromColumn(column, index));
        }
        length--;
        IDBooks.remove(Integer.parseInt(delRow.get("id")));
        return delRow;
    }

    /**
     * Удаляет информацию о книге по ID книги
     * @param id Уникальныйидентификатор книги
     * @return Удаляемая информация
     * @throws Exception В случае если неполучается открыть файл вызывет исключение.
     * Если нет книги с таким ID
     */
    public HashMap<String, String> deleteRowByID(int id) throws Exception {
        if (!IDBooks.contains(id)) throw new Exception("Position read after desired position. Restart Reader!");
        int index = getIndexesFromValue("id", Integer.toString(id)).get(0);
        return deleteRow(index);
    }

    /**
     * Добавляет запись о новой книге
     * @param newRow информация о добаляемой книге. Имена атрибутов можно узнать через {@link Book#getColumnsName() имена атрибутов книги}
     * @throws Exception Если ID уже существует вызовет исключение. Все ID можно узнать через {@link Book#getIdBooks() getIdBooks() с перебором всех ID}
     * или {@link Book#getIDBooks() getIDBooks() через атрибут экземпляра}
     */
    public void addNewBook(HashMap<String, String> newRow) throws Exception {
        // Проверка на уникальность ID
        if ( IDBooks.contains(Integer.parseInt(newRow.get("id")))) throw new Exception("ID already exist");
        for (String column :columnsName){
            addToColumn(column, newRow.get(column)+"\n");
        }
        IDBooks.add(Integer.parseInt(newRow.get("id")));
        length++;
    }

    /**
     * Добавляет запись о новой книге
     * @param id Уникальный идентификатор книги. Все ID можно узнать через {@link Book#getIdBooks() getIdBooks() с перебором всех ID}
     * или {@link Book#getIDBooks() getIDBooks() через атрибут экземпляра}
     * @param name Название книги
     * @param author Автор
     * @param edition Издание
     * @param publisher Издатель
     * @param publication_year Год издания
     * @param category Категоря
     * @throws Exception Если ID уже существует вызовет исключение.
     */
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

    /**
     * Геттер названий атрибутов книги
     * @return регистрируемые атрибуты книги
     */
    public String[] getColumnsName(){
        return columnsName;
    }

    /**
     * Геттер всей информации о книгах
     * @return {@code ArrayList<HashMap<String, String>>} со словарями информации о книгах
     */
    public ArrayList<HashMap<String, String>> getAllDataList(){
        return allData;
    }

    /**
     * Геттер ID книг
     * @return ID всех записаных книг
     */
    public ArrayList<Integer> getIDBooks(){
        return IDBooks;
    }
}
