

import my.com.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    @Test
    @DisplayName("testing get employer")
    void testGetFullInfo()
    {
        Employee.createEmployer(
                1,
                "mik",
                "mok",
                2001,
                "v bock",
                1234.56,
                "0");
        Employee.createEmployer(
                3,
                "mik2",
                "mok",
                2001,
                "v bock",
                1234.56,
                "0");

        assertEquals(Employee.getFullInfo(1).toString(), "{lastName=mok, year=2001, name=mik, id=1, birthdayPlease=v bock, salary=1234.56, maritalStatus=0}");
    }

    @Test
    @DisplayName("testing get name year")
    void testGetFullNameYear()
    {
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

        assertEquals(Employee.getFullInfoByName("mik").toString(), "[{lastName=mok, year=2001, name=mik, id=1, birthdayPlease=v bock1, salary=1.2E7, maritalStatus=0}, {lastName=mok, year=2001, name=mik, id=2, birthdayPlease=v bock2, salary=12.0, maritalStatus=0}]");
        assertEquals(Employee.getFullInfoByYear(2002).toString(), "[{lastName=mok, year=2002, name=mik2, id=3, birthdayPlease=v bock3, salary=1.2E9, maritalStatus=0}]");
    }

    @Test
    @DisplayName("testing set info")
    void testChangeInfo()
    {
        Employee.createEmployer(
                1,
                "mik",
                "mok",
                2001,
                "v bock1",
                12000000,
                "0");
        Employee.setName(1, "mak");
        assertEquals(Employee.getName(1), "mak");
        Employee.setLastName(1, "lak");
        assertEquals(Employee.getLastName(1), "lak");
        Employee.setYear(1, 2008);
        assertEquals(Employee.getYear(1), "2008");
        Employee.setBirthPlace(1, "lon");
        assertEquals(Employee.getBirthPlace(1), "lon");
        Employee.setSalary(1, 100000);
        assertEquals(Double.parseDouble(Employee.getSalary(1)), 100000);
        Employee.setMaritalStatus(1, "1");
        assertEquals(Employee.getMaritalStatus(1), "1");

        Employee.createEmployer(
                3,
                "mik",
                "mok",
                2001,
                "v bock1",
                12000000,
                "0");
        Employee.setId(3, 2);
        assertNull(Employee.getFullInfo(3));
        assertEquals(Employee.getName(2), "mik");
    }

    @Test
    @DisplayName("testing salary sum")
    void testSolveSum()
    {
        Employee.createEmployer(
                1,
                "mik",
                "mok",
                2001,
                "v bock",
                1234.56,
                "0");
        Employee.createEmployer(
                2,
                "mik2",
                "mok",
                2001,
                "v bock",
                1234.56,
                "0");

        assertEquals(Employee.getFullSalary(), 2469.12);
    }
}