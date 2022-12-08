package org.example.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Start");
        JFrame registration = new JFrame("Registration");
        final String DATABASE_URL = "jdbc:sqlite:mydb.db";
        Connection conn = DriverManager.getConnection(DATABASE_URL);
        System.out.println("Connection established!");

        registration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = registration.getContentPane();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        container.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel login_text = new JLabel("login");
        JTextField login = new JTextField(15);
        JLabel password_text = new JLabel("password");
        JPasswordField password = new JPasswordField(15);
        JButton button_log_in = new JButton("Log in");

        constraints.insets    = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        container.add(login_text, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        container.add(login, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(password_text, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        container.add(password, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(button_log_in, constraints);

        registration.pack();
        registration.setLocationRelativeTo(null);

        registration.setVisible(true);

        /* -------------- Main Page -------------- */

        JFrame main_frame = new JFrame("Main window");
        Container main_page_panel = main_frame.getContentPane();
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        /* ---------- Create components ---------- */
        JPanel admin_panel = new JPanel();
        JPanel search = new JPanel();
        JPanel change = new JPanel();
        JPanel stats = new JPanel();

        /* -------------- Admin panel ------------ */
        JLabel add_user_label = new JLabel("Логин пользователя");
        JTextField add_user = new JTextField(15);

        ButtonGroup user_type_group = new ButtonGroup();
        JRadioButton rb_administration = new JRadioButton("Администратор", true);
        JRadioButton rb_editor = new JRadioButton("Редактор", false);
        JRadioButton rb_viewer = new JRadioButton("Зритель", false);
        user_type_group.add(rb_administration);
        user_type_group.add(rb_editor);
        user_type_group.add(rb_viewer);

        JLabel user_password_label = new JLabel("Пароль");
        JTextField user_password = new JPasswordField(15);

        JButton create_user = new JButton("Создать пользователя");

        JButton del_user = new JButton("Удалить пользователя");

        /* -------------- search panel ------------ */
        JLabel employee_id_text = new JLabel("ID пользователя");
        JTextField employee_id = new JTextField(15);
        JLabel employee_name_text = new JLabel("Имя пользователя");
        JTextField employee_name = new JTextField(15);
        JLabel employee_data_br_text = new JLabel("Дата рождения пользователя");
        JTextField employee_data_br = new JTextField(15);

        ButtonGroup search_type_group = new ButtonGroup();
        JRadioButton rb_employee_id = new JRadioButton("Поиск по ID", true);
        JRadioButton rb_employee_name = new JRadioButton("Поик по имени", false);
        JRadioButton rb_employee_data_br = new JRadioButton("Поиск по дате рождения", false);
        search_type_group.add(rb_employee_id);
        search_type_group.add(rb_employee_name);
        search_type_group.add(rb_employee_data_br);

        JButton search_user = new JButton("Найти сотрудника");

        JTable table_search = new JTable();
        JScrollPane sp_search = new JScrollPane(table_search);

        /* -------------- change panel ------------ */
        JLabel change_employee_id_text = new JLabel("ID сотрудника");
        JTextField change_employee_id = new JTextField(15);
        JLabel change_id_text = new JLabel("ID");
        JTextField change_id = new JTextField(15);
        JLabel change_name_text = new JLabel("Имя");
        JTextField change_name = new JTextField(15);
        JLabel change_lsname_text = new JLabel("Фамилия");
        JTextField change_lsname = new JTextField(15);
        JLabel change_dfb_text = new JLabel("Дата рождения");
        JTextField change_dfb = new JTextField(15);
        JLabel change_pfb_text = new JLabel("Место рождения");
        JTextField change_pfb = new JTextField(15);
        JLabel salary_text = new JLabel("Зарплата");
        JTextField salary = new JTextField(15);
        JLabel family_st_text = new JLabel("Семейное положение");
        JCheckBox family_st = new JCheckBox();

        JButton save_change_button = new JButton("Сохранить изменения");

        /* -------------- change panel ------------ */

        JLabel summ_salary_text = new JLabel("Сумма зарплат сотрудников");
        JLabel summ_salary = new JLabel("0");

        JButton get_info = new JButton("Получить информацию о сотрудниках");

        /* ------------ Add components ----------- */

        /* -------------- Admin panel ------------ */

        admin_panel.setLayout(new GridBagLayout());
        admin_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        GridBagConstraints constraints_main_page_admin = new GridBagConstraints();

        constraints_main_page_admin.insets = new Insets(5, 5, 5, 5);

        constraints_main_page_admin.gridx = 0;
        constraints_main_page_admin.gridy = 0;
        constraints_main_page_admin.anchor = GridBagConstraints.LINE_START;
        admin_panel.add(add_user_label, constraints_main_page_admin);

        constraints_main_page_admin.gridx = 1;
        constraints_main_page_admin.gridy = 0;
        constraints_main_page_admin.gridwidth = 1;
        admin_panel.add(add_user, constraints_main_page_admin);
        constraints_main_page_admin.gridwidth = 1;

        constraints_main_page_admin.gridx = 0;
        constraints_main_page_admin.gridy = 1;
        admin_panel.add(rb_administration, constraints_main_page_admin);
        constraints_main_page_admin.gridx = 1;
        constraints_main_page_admin.gridy = 1;
        admin_panel.add(rb_editor, constraints_main_page_admin);
        constraints_main_page_admin.gridx = 2;
        constraints_main_page_admin.gridy = 1;
        admin_panel.add(rb_viewer, constraints_main_page_admin);

        constraints_main_page_admin.gridx = 0;
        constraints_main_page_admin.gridy = 2;
        constraints_main_page_admin.anchor = GridBagConstraints.LINE_START;
        admin_panel.add(user_password_label, constraints_main_page_admin);

        constraints_main_page_admin.gridx = 1;
        constraints_main_page_admin.gridy = 2;
        constraints_main_page_admin.gridwidth = 1;
        admin_panel.add(user_password, constraints_main_page_admin);
        constraints_main_page_admin.gridwidth = 1;

        constraints_main_page_admin.gridx = 0;
        constraints_main_page_admin.gridy = 3;
        admin_panel.add(create_user, constraints_main_page_admin);
        constraints_main_page_admin.gridx = 1;
        constraints_main_page_admin.gridy = 3;
        admin_panel.add(del_user, constraints_main_page_admin);

        /* -------------- search panel ------------ */

        search.setLayout(new GridBagLayout());
        search.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        GridBagConstraints constraints_search = new GridBagConstraints();

        constraints_search.insets = new Insets(5, 5, 5, 5);

        constraints_search.gridx = 0;
        constraints_search.gridy = 0;
        search.add(rb_employee_id, constraints_search);
        constraints_search.gridx = 1;
        constraints_search.gridy = 0;
        search.add(rb_employee_name, constraints_search);
        constraints_search.gridx = 2;
        constraints_search.gridy = 0;
        search.add(rb_employee_data_br, constraints_search);

        constraints_search.gridx = 0;
        constraints_search.gridy = 1;
        constraints_search.anchor = GridBagConstraints.LINE_START;
        search.add(employee_id_text, constraints_search);

        constraints_search.gridx = 1;
        constraints_search.gridy = 1;
        search.add(employee_id, constraints_search);

        constraints_search.gridx = 0;
        constraints_search.gridy = 2;
        search.add(employee_name_text, constraints_search);

        constraints_search.gridx = 1;
        constraints_search.gridy = 2;
        search.add(employee_name, constraints_search);

        constraints_search.gridx = 0;
        constraints_search.gridy = 3;
        search.add(employee_data_br_text, constraints_search);

        constraints_search.gridx = 1;
        constraints_search.gridy = 3;
        search.add(employee_data_br, constraints_search);

        constraints_search.gridx = 0;
        constraints_search.gridy = 4;
        search.add(search_user, constraints_search);

        constraints_search.gridx = 0;
        constraints_search.gridy = 5;
        constraints_search.gridwidth = 3;
        search.add(sp_search, constraints_search);



        /* -------------- change panel ------------ */
        change.setLayout(new GridBagLayout());
        change.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        GridBagConstraints constraints_change = new GridBagConstraints();

        constraints_change.insets = new Insets(5, 5, 5, 5);

        constraints_change.anchor = GridBagConstraints.LINE_START;
        constraints_change.gridx = 0;
        constraints_change.gridy = 0;
        change.add(change_employee_id_text, constraints_change);
        constraints_change.gridx = 1;
        constraints_change.gridy = 0;
        change.add(change_employee_id, constraints_change);
        constraints_change.gridx = 0;
        constraints_change.gridy = 1;
        change.add(change_id_text, constraints_change);
        constraints_change.gridx = 1;
        constraints_change.gridy = 1;
        change.add(change_id, constraints_change);
        constraints_change.gridx = 0;
        constraints_change.gridy = 2;
        change.add(change_name_text, constraints_change);
        constraints_change.gridx = 1;
        constraints_change.gridy = 2;
        change.add(change_name, constraints_change);
        constraints_change.gridx = 0;
        constraints_change.gridy = 3;
        change.add(change_lsname_text, constraints_change);
        constraints_change.gridx = 1;
        constraints_change.gridy = 3;
        change.add(change_lsname, constraints_change);
        constraints_change.gridx = 0;
        constraints_change.gridy = 4;
        change.add(change_dfb_text, constraints_change);
        constraints_change.gridx = 1;
        constraints_change.gridy = 4;
        change.add(change_dfb, constraints_change);
        constraints_change.gridx = 0;
        constraints_change.gridy = 5;
        change.add(change_pfb_text, constraints_change);
        constraints_change.gridx = 1;
        constraints_change.gridy = 5;
        change.add(change_pfb, constraints_change);
        constraints_change.gridx = 0;
        constraints_change.gridy = 6;
        change.add(salary_text, constraints_change);
        constraints_change.gridx = 1;
        constraints_change.gridy = 6;
        change.add(salary, constraints_change);
        constraints_change.gridx = 0;
        constraints_change.gridy = 7;
        change.add(family_st_text, constraints_change);
        constraints_change.gridx = 1;
        constraints_change.gridy = 7;
        change.add(family_st, constraints_change);
        constraints_change.gridx = 0;
        constraints_change.gridy = 8;
        change.add(save_change_button, constraints_change);

        /* -------------- stats panel ------------- */
        stats.setLayout(new GridBagLayout());
        stats.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        GridBagConstraints constraints_stats = new GridBagConstraints();

        constraints_stats.insets = new Insets(5, 5, 5, 5);
        constraints_stats.anchor = GridBagConstraints.LINE_START;
        constraints_stats.gridx = 0;
        constraints_stats.gridy = 0;
        stats.add(summ_salary_text, constraints_stats);
        constraints_stats.gridx = 1;
        constraints_stats.gridy = 0;
        stats.add(summ_salary, constraints_stats);

        constraints_stats.gridx = 0;
        constraints_stats.gridy = 1;
        stats.add(get_info, constraints_stats);




        JTabbedPane jtabbedPane = new JTabbedPane();

        jtabbedPane.addTab("Информация о сотрудниках", stats);
        jtabbedPane.addTab("Администрирование", admin_panel);
        jtabbedPane.addTab("Поиск", search);
        jtabbedPane.addTab("Изменить информацию", change);

        main_page_panel.add(jtabbedPane);





        main_frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


        main_frame.setSize(600, 700);
        main_frame.setLocationRelativeTo(null);
        main_frame.setVisible(false);

        main_frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("JFrame has  been  made visible first  time");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("JFrame is closing.");
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("JFrame is closed.");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("JFrame is  minimized.");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("JFrame is restored.");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("JFrame is activated.");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                System.out.println("JFrame is deactivated.");
            }
        });

        button_log_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkQuery = String.format("""
                        SELECT * FROM people_login WHERE login = "%s" AND password = "%s"
                        """, login.getText(), password.getText());
                System.out.println(checkQuery);
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(checkQuery);
                    if (rs.next()) {
                        System.out.println("Успешный логин");
                        System.out.println(rs.getString("role"));



                        switch (rs.getString("role")) {
                            case "Администратор": {
                                jtabbedPane.setEnabledAt(0, true);
                                jtabbedPane.setEnabledAt(1, true);
                                jtabbedPane.setEnabledAt(2, true);
                                jtabbedPane.setEnabledAt(3, true);
                            }
                            case "Редактор": {
                                jtabbedPane.setEnabledAt(0, true);
                                jtabbedPane.setEnabledAt(1, false);
                                jtabbedPane.setEnabledAt(2, true);
                                jtabbedPane.setEnabledAt(3, true);
                            }
                            case "Зритель": {
                                jtabbedPane.setEnabledAt(0, true);
                                jtabbedPane.setEnabledAt(1, false);
                                jtabbedPane.setEnabledAt(2, true);
                                jtabbedPane.setEnabledAt(3, false);
                            }
                        }

                        main_frame.setVisible(true);
                        registration.setVisible(false);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        search_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("123");
            }
        });

        main_frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("JFrame is closing.");
            }
        });

        new Timer(16, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //step(); Функция изменения чего-то
                //repaint(); В случае изменения нужно вызвать у обекта JPanel для перерисовки
            }
        }).start();



    }
}