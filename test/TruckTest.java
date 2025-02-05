import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TruckTest {

    // Scania-tester

    @Test
    void changeangle() {
        // Test for valid values
        Scania scaniaTest = new Scania();
        double angleBefore = scaniaTest.angle;
        double amount = 50;
        scaniaTest.changeAngleScania(50);
        assertEquals(amount, scaniaTest.angle);
        scaniaTest.changeAngleScania(0);

        // Test for values out of bounds
        scaniaTest.changeAngleScania(90);
        assertThrows(IllegalArgumentException.class, () -> scaniaTest.changeAngleScania(90));
        scaniaTest.changeAngleScania(0);

        scaniaTest.changeAngleScania(-10);
        assertThrows(IllegalArgumentException.class, () -> scaniaTest.changeAngleScania(-10));
        scaniaTest.changeAngleScania(0);

        // Test for changing ramp-position while moving
        scaniaTest.startEngine();
        scaniaTest.gas(0.5);
        assertThrows(IllegalArgumentException.class, () -> scaniaTest.changeAngleScania(20));
    }



    void loadCarToTruck() {


    }



}

