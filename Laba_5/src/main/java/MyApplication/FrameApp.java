package MyApplication;


import MyLibrary.Bird;
import MyLibrary.Fish;
import MyLibrary.Item;
import MyLibrary.Meat;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.ArrayList;

public class FrameApp extends JFrame {
    private LibraryManagement libraryManagement;
    private JPanel panelAction;
    private JPanel panelInformation;
    private JButton buttonAdd;
    private JButton buttonDel;
    private JButton buttonFind;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTextField textFieldID;
    private JTextField textFieldName;
    private JTextField textFieldLastDate;
    private JTextField textFieldWeight;
    private JTextField textFieldWeightType;
    private JTextField textFieldComment;
    private JButton buttonCopyAll;
    private JButton buttonCopySelect;

    private JRadioButton radioButtonMeat;
    private JRadioButton radioButtonFish;
    private JRadioButton radioButtonBird;

    public FrameApp(LibraryManagement libraryManagement) {
        super("Система учета печатных изданий");

        this.libraryManagement = libraryManagement;

        //Создаем панель управениея
        panelAction = new JPanel();
        //Задаем патерн расположения
        panelAction.setLayout(new FlowLayout());

        //Панель с кнопками
        JPanel panelNew = new JPanel();
        //Задаем патерн расположения
        panelNew.setLayout(new BoxLayout(panelNew, BoxLayout.Y_AXIS));

        //Создаем кнопки и систенеры для них
        buttonAdd = new JButton("Добавить запись");
        buttonAdd.addActionListener(new ButtonAdd());
        buttonDel = new JButton("Удалить запись");
        buttonDel.addActionListener(new ButtonDel());
        textField = new JTextField();
        buttonFind = new JButton("Найти");
        buttonFind.addActionListener(new ButtonFind());
        buttonCopyAll = new JButton("В буф.обмена все");
        buttonCopyAll.addActionListener(new ButtonCopyAll());
        buttonCopySelect = new JButton("В буф.обмена выд-е");
        buttonCopySelect.addActionListener(new ButtonCopySelect());

        //Добавляем кнопки на панель
        panelNew.add(buttonAdd);
        panelNew.add(buttonDel);
        panelNew.add(textField);
        panelNew.add(buttonFind);
        panelNew.add(buttonCopyAll);
        panelNew.add(buttonCopySelect);

        //Добавляем панель с кнопками в панель управления
        panelAction.add(panelNew);
        getContentPane().add(BorderLayout.EAST, panelAction);

        //Создаем таблицу с данными
        model = new DefaultTableModel();
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //Создаем столбцы таблицы
        model.addColumn("ID");
        model.addColumn("Название");
        model.addColumn("Срок годности");
        model.addColumn("Вес");

        //Задем листенер для выделенного элемента
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new MyListSelect());

        //Добавляем скролбар
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        getContentPane().add(BorderLayout.CENTER, scrollPane);

        //Панель с информацией
        panelInformation = new JPanel();
        //Панель с данными
        JPanel panelElements = new JPanel();

        // Поля для ввода
        textFieldID = new JTextField(20);
        textFieldName = new JTextField();
        textFieldLastDate = new JTextField();
        textFieldWeight = new JTextField();
        textFieldComment = new JTextField();
        textFieldWeightType = new JTextField();

        JPanel radioButton = new JPanel();

        radioButtonBird = new JRadioButton("Птица");
        radioButtonBird.addActionListener(new RadioButtonBird());
        radioButtonFish = new JRadioButton("Рыба");
        radioButtonFish.addActionListener(new RadioButtonFish());
        radioButtonMeat = new JRadioButton("Мясо");
        radioButtonMeat.addActionListener(new RadioButtonMeat());

        radioButtonBird.setSelected(true);

        radioButton.add(radioButtonBird);
        radioButton.add(radioButtonFish);
        radioButton.add(radioButtonMeat);

        GridLayout experimentLayout = new GridLayout(8, 2, 5, 5);
        panelElements.setLayout(experimentLayout);
        panelElements.add(new JLabel("ID"));
        panelElements.add(textFieldID);
        panelElements.add(new JLabel("Название"));
        panelElements.add(textFieldName);
        panelElements.add(new JLabel("Срок годности"));
        panelElements.add(textFieldLastDate);
        panelElements.add(new JLabel("Вес"));
        panelElements.add(textFieldWeight);
        panelElements.add(new JLabel("Единицы измерения(Тип):"));
        panelElements.add(textFieldWeightType);
        panelElements.add(new JLabel("Комментарий"));
        panelElements.add(textFieldComment);
        panelElements.add(new JPanel());
        panelElements.add(radioButton);

        panelInformation.setLayout(new FlowLayout());

        panelInformation.add(panelElements);

        getContentPane().add(BorderLayout.SOUTH, panelInformation);


        setSize(1000, 500);
        setVisible(true);
    }


    private class MyListSelect implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int[] ss = table.getSelectedRows(); // Получаем список выбранных записей
            if (ss.length == 1) {
                int id = (Integer) model.getValueAt(ss[0], 0);
                Item item = libraryManagement.getItem(id);
                if (item == null) return;
                textFieldID.setText(String.valueOf(item.getId()));
                textFieldName.setText(item.getName());
                textFieldWeight.setText(item.getWeight());
                textFieldLastDate.setText(String.valueOf(item.getShelfLife()));
                textFieldComment.setText(item.getComment());
                if (item.getClass() == Bird.class) {
                    radioButtonBird.setSelected(true);
                    radioButtonFish.setSelected(false);
                    radioButtonMeat.setSelected(false);
                } else if (item.getClass() == Fish.class) {
                    radioButtonFish.setSelected(true);
                    radioButtonBird.setSelected(false);
                    radioButtonMeat.setSelected(false);
                } else if (item.getClass() == Meat.class) {
                    radioButtonMeat.setSelected(true);
                    radioButtonFish.setSelected(false);
                    radioButtonBird.setSelected(false);
                }
            } else {
                textFieldID.setText("");
                textFieldName.setText("");
                textFieldWeight.setText("");
                textFieldLastDate.setText("");
                textFieldComment.setText("");
            }
        }
    }

    private void CreateTable() {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        libraryManagement.start();
        Item item = libraryManagement.getNext();
        while (item != null) {
            Object[] addRow = new Object[]{item.getId(), item.getName(), item.getShelfLife(), item.getWeight()};
            model.addRow(addRow);
            item = libraryManagement.getNext();
        }
    }

    private class ButtonAdd implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (radioButtonBird.isSelected()) {
                libraryManagement.addBird(Integer.parseInt(textFieldID.getText()), textFieldName.getText(), LocalDate.parse(textFieldLastDate.getText()), Double.parseDouble(textFieldWeight.getText()), Integer.parseInt(textFieldWeightType.getText()), textFieldComment.getText());
            } else if (radioButtonFish.isSelected()) {
                libraryManagement.addFish(Integer.parseInt(textFieldID.getText()), textFieldName.getText(), LocalDate.parse(textFieldLastDate.getText()), Double.parseDouble(textFieldWeight.getText()), Integer.parseInt(textFieldWeightType.getText()), textFieldComment.getText());
            } else if (radioButtonMeat.isSelected()) {
                libraryManagement.addMeat(Integer.parseInt(textFieldID.getText()), textFieldName.getText(), LocalDate.parse(textFieldLastDate.getText()), Double.parseDouble(textFieldWeight.getText()), Integer.parseInt(textFieldWeightType.getText()), textFieldComment.getText());
            }
            CreateTable();
        }
    }

    // Обработчик события от кнопки "Meat"
    private class RadioButtonMeat implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            radioButtonMeat.setSelected(true);
            radioButtonFish.setSelected(false);
            radioButtonBird.setSelected(false);
        }
    }

    // Обработчик события от кнопки "Fish"
    private class RadioButtonFish implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            radioButtonFish.setSelected(true);
            radioButtonBird.setSelected(false);
            radioButtonMeat.setSelected(false);
        }
    }

    // Обработчик события от кнопки "Bird"
    private class RadioButtonBird implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            radioButtonBird.setSelected(true);
            radioButtonFish.setSelected(false);
            radioButtonMeat.setSelected(false);
        }
    }

    private class ButtonDel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] ss = table.getSelectedRows();
            if (ss.length == 1) {
                int idSelect = (Integer) model.getValueAt(ss[0], 0);
                model.removeRow(ss[0]);
                libraryManagement.removeItem(idSelect);
            }
        }
    }

    private class ButtonFind implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (model.getRowCount() == 0) {
                CreateTable();
            }
            if (textField.getText().isEmpty()) {
                CreateTable();
                return;
            }
            ArrayList<Item> items = libraryManagement.findItem(textField.getText());
            table.clearSelection();
            for (int i = 0; i < items.size(); i++) {
                for (int j = 0; j < table.getRowCount(); j++) {
                    if ((Integer) table.getValueAt(j, 0) == items.get(i).getId()) {
                        table.addRowSelectionInterval(j, j);
                    }
                }
            }
        }
    }

    private class ButtonCopyAll implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = "Список продуктов:\n";
            for (int i = 0; i < libraryManagement.size(); i++) {
                Item item = libraryManagement.getItem(i);
                str = str + item.getInfo() + "\n";
            }
            StringSelection selection = new StringSelection(str);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }

    private class ButtonCopySelect implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] ss = table.getSelectedRows();
            String str = "Список продуктов:\n";
            for (int i = 0; i < ss.length; i++) {
                int id = (Integer) model.getValueAt(ss[i], 0);
                Item item = libraryManagement.getItem(id);
                str = str + item.getInfo() + "\n";
            }
            StringSelection selection = new StringSelection(str);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
}