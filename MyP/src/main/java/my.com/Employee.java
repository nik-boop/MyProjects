package my.com;

import java.util.*;

public class Employee {

    private static final int arrayLen = 100;
    private static final ArrayList<Map> employee = new ArrayList<>(arrayLen);

    private static final ArrayList<Integer> idArray = new ArrayList<>(arrayLen);

    public static void createEmployer(
            int id,
            String name,
            String lastName,
            int year,
            String birthdayPlease,
            double salary,
            String maritalStatus){

        if (idArray.contains(id)){
            return;
        } else {
            idArray.add(id);
        }

        Map<String, String> employer = new HashMap<>();

        employer.put("id", Integer.toString(id));
        employer.put("name", name);
        employer.put("lastName", lastName);
        employer.put("year", Integer.toString(year));
        employer.put("birthdayPlease", birthdayPlease);
        employer.put("salary", Double.toString(salary));
        employer.put("maritalStatus", maritalStatus);

        employee.add(employer);
    }

    public static ArrayList<String> getIdArray() {
        ArrayList<String> ids = new ArrayList<>();
        for (var emp: employee) {
            ids.add(emp.get("id").toString());
        }

        return ids;
    }
    public static String setId(int id, int new_id) {

        if (idArray.contains(new_id)){
            return "Impossible id";
        }

        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                emp.put("id", Integer.toString(new_id));
                return (String) emp.get("id");
            }
        }
        return "NaN";
    }
    public static String getName(int id) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                return (String) emp.get("name");
            }
        }
        return "NaN";
    }
    public static String setName(int id, String name) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                emp.put("name", name);
                return (String) emp.get("name");
            }
        }
        return "NaN";
    }
    public static String getLastName(int id) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                return (String) emp.get("lastName");
            }
        }
        return "NaN";
    }
    public static String setLastName(int id, String lastName) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                emp.put("lastName", lastName);
                return (String) emp.get("lastName");
            }
        }
        return "NaN";
    }
    public static String getYear(int id) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                return (String) emp.get("year");
            }
        }
        return "NaN";
    }
    public static String setYear(int id, int year) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                emp.put("year", Integer.toString(year));
                return (String) emp.get("year");
            }
        }
        return "NaN";
    }
    public static String getBirthPlace(int id) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                return (String) emp.get("birthdayPlease");
            }
        }
        return "NaN";
    }
    public static String setBirthPlace(int id, String birthdayPlease) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                emp.put("birthdayPlease", birthdayPlease);
                return (String) emp.get("birthdayPlease");
            }
        }
        return "NaN";
    }
    public static String getSalary(int id) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                return (String) emp.get("salary");
            }
        }
        return "NaN";
    }
    public static String setSalary(int id, double salary) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                emp.put("salary", Double.toString(salary));
                return (String) emp.get("salary");
            }
        }
        return "NaN";
    }
    public static String getMaritalStatus(int id) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                return (String) emp.get("maritalStatus");
            }
        }
        return "NaN";
    }
    public static String setMaritalStatus(int id, String maritalStatus) {
        for (var emp: employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                emp.put("maritalStatus", maritalStatus);
                return (String) emp.get("maritalStatus");
            }
        }
        return "NaN";
    }


    public static Map getFullInfo(int id) {
        Map result = null;

        for (var emp : employee) {
            if (emp.get("id").equals(Integer.toString(id))) {
                result = emp;
                break;
            }
        }
        return result;
    }

    public static ArrayList<Map> getFullInfoByName(String name) {
        ArrayList<Map> result = new ArrayList<Map>();
        for (var emp : employee) {
            if (emp.get("name").equals(name)) {
                result.add(emp);
            }
        }
        return result;

    }

    public static ArrayList<Map> getFullInfoByYear(int birthYear) {
        ArrayList<Map> result = new ArrayList<Map>();
        for (var emp : employee) {
            if (emp.get("year").equals(Integer.toString(birthYear))) {
                result.add(emp);
            }
        }
        return result;

    }

    public static double getFullSalary() {

        double result = 0;
        for (var emp : employee) {
            result += Double.parseDouble(emp.get("salary").toString());
        }
        return result;

    }

    public static void main(String[] args) {
        Employee.createEmployer(
                1,
                "mik",
                "mok",
                2001,
                "v bock1",
                12000000,
                "0");
        Employee.createEmployer(
                2,
                "mik",
                "mok",
                2001,
                "v bock2",
                12,
                "0");
        Employee.createEmployer(
                3,
                "mik2",
                "mok",
                2002,
                "v bock3",
                1200000000,
                "0");


        System.out.println(Arrays.toString(getIdArray().toArray()));
        //System.out.println(Arrays.toString(employee.toArray()));

        System.out.println(getFullInfoByYear(2002));

        //System.out.println(Arrays.toString(employee.toArray()));



    }
}