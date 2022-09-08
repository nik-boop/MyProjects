

import my.com.MyClassApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MyClassAppTests {
    @Test
    @DisplayName("testing (MyClassApp.scalarProduct)")
    void testScalarProduct()
    {
        int[] v1 = MyClassApp.createVector(1, 2, 3);
        int[] v2 = MyClassApp.createVector(1, 1, 1);
        assertEquals(6, MyClassApp.scalarProduct(v1, v2));
    }
    @Test
    @DisplayName("testing (MyClassApp.vectorProduct)")
    void testVectorProduct()
    {
        int[] v1 = MyClassApp.createVector(1, 2, 3);
        int[] v2 = MyClassApp.createVector(1, 1, 1);
        int[] v3 = new int[] {-1, 2, -1};

        assertEquals(Arrays.toString(v3), Arrays.toString(MyClassApp.vectorProduct(v1, v2)));
    }
    @Test
    @DisplayName("testing (MyClassApp.additionVector)")
    void testAdditionVector()
    {
        int[] v1 = MyClassApp.createVector(1, 2, 3);
        int[] v2 = MyClassApp.createVector(1, 1, 1);
        int[] v3 = new int[] {2, 3, 4};
        assertEquals(Arrays.toString(v3), Arrays.toString(MyClassApp.additionVector(v1, v2)));
    }
    @Test
    @DisplayName("testing (MyClassApp.subtractionVector)")
    void testSubtractionVector()
    {
        int[] v1 = MyClassApp.createVector(1, 2, 3);
        int[] v2 = MyClassApp.createVector(1, 1, 1);
        int[] v3 = MyClassApp.createVector(0, 1, 2);
        assertEquals(Arrays.toString(v3), Arrays.toString(MyClassApp.subtractionVector(v1, v2)));
    }
    @Test
    @DisplayName("testing (MyClassApp.moduleVector)")
    void testModuleVector()
    {
        int[] v1 = MyClassApp.createVector(1, 2, 3);
        double t = 3.7416573867739413;
        assertEquals(MyClassApp.moduleVector(v1), t);
    }
    @Test
    @DisplayName("testing (MyClassApp.angleBetweenVectors)")
    void testAngleBetweenVectors()
    {
        int[] v1 = MyClassApp.createVector(1, 2, 3);
        int[] v2 = MyClassApp.createVector(1, 1, 1);
        double t = 22.207654298596477;
        assertEquals(MyClassApp.angleBetweenVectors(v1, v2) * (180 / Math.PI), t);
    }
}