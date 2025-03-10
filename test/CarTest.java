import car.Car;
import car.Saab95;
import car.Volvo240;
import org.junit.jupiter.api.Test;
import position.*;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    void moveNORTH() {
        Car testCar = new Volvo240(); // Start direction is set to North.
        double y1 = testCar.position.getY();
        double x1 = testCar.position.getX();
        testCar.startEngine();
        testCar.move();
        assertTrue(testCar.position.getY() >= y1); // Compare the incremented value of y
        assertTrue(testCar.position.getX() == x1);// x should have the same value
    }

    @Test
    void moveSOUTH() {
        Car testCar = new Volvo240();
        testCar.position.setDirection(Direction.SOUTH);
        double y1 = testCar.position.getY();
        double x1 = testCar.position.getX();
        testCar.startEngine();
        testCar.move();
        assertTrue(testCar.position.getY() <= y1); // y should have the same value
        assertTrue(testCar.position.getX() == x1); // Compare the incremented value of x
    }

    @Test
    void moveWEST() {
        Car testCar = new Volvo240();
        testCar.position.setDirection(Direction.WEST);
        double y1 = testCar.position.getY();
        double x1 = testCar.position.getX();
        testCar.startEngine();
        testCar.move();
        assertTrue(testCar.position.getY() == y1); // y should have the same value
        assertTrue(testCar.position.getX() <= x1); // Compare the incremented value of x
    }


    @Test
    void moveEast() {
        Car testCar = new Volvo240(); // Start direction is set to North.
        testCar.position.setDirection(Direction.EAST);
        double y1 = testCar.position.getY();
        double x1 = testCar.position.getX();
        testCar.startEngine();
        testCar.move();
        assertTrue(testCar.position.getY() == y1); // Compare the incremented value of y
        assertTrue(testCar.position.getX() >= x1); //  x should have the same value
    }


    @Test
    void turnLeft() {
        Car testCar = new Saab95();
        testCar.position.setDirection(Direction.EAST);
        testCar.turnLeft();
        assertNotEquals(Direction.EAST,testCar.position.getDirection() ); // CurrentD != RIGHT
        assertEquals(Direction.NORTH, testCar.position.getDirection()); // CurrentD == FORWARD

        testCar.position.setDirection(Direction.WEST);
        testCar.turnLeft();
        assertNotEquals(Direction.WEST,testCar.position.getDirection() ); // CurrentD != LEFT
        assertEquals(Direction.SOUTH, testCar.position.getDirection()); // CurrentD == BACKWARD

    }

    @Test
    void turnRight() {
        Car testCar = new Saab95();
        testCar.position.setDirection(Direction.EAST);
        testCar.turnRight();
        assertNotEquals(Direction.EAST,testCar.position.getDirection() ); // CurrentD != RIGHT
        assertEquals(Direction.SOUTH, testCar.position.getDirection()); // CurrentD == BACKWARD

        testCar.position.setDirection(Direction.WEST);
        testCar.turnRight();
        assertNotEquals(Direction.WEST,testCar.position.getDirection() ); // CurrentD != LEFT
        assertEquals(Direction.NORTH, testCar.position.getDirection()); // CurrentD == FORWARD

    }

    @Test
    void startEngine() {
        Car testCar = new Saab95();
        testCar.startEngine(); // set currentSpeed to 0.1
        assertNotEquals(0, testCar.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        Car testCar = new Saab95();
        testCar.startEngine();
        testCar.stopEngine();
        assertEquals(0, testCar.getCurrentSpeed());
    }


    @Test
    void incrementSpeed() {
        Car testSaab = new Saab95();
        testSaab.startEngine();

        for (double i = 0.0; i <= 1.0; i+= 0.1) {
            double currentSpeedBefore = testSaab.getCurrentSpeed();
            testSaab.gas(i);
            assertTrue(currentSpeedBefore <= testSaab.getCurrentSpeed());
        }
    }

    @Test
    void decrementSpeed() {
        Car testSaab = new Saab95();
        testSaab.startEngine();
        double currentSpeedBefore = testSaab.getCurrentSpeed();

        for (double i = 0.0; i <= 1.0; i+= 0.1) {
            testSaab.brake(i);
            assertTrue(currentSpeedBefore >= testSaab.getCurrentSpeed());
        }
    }

    @Test
    void speedFactor(){
        Saab95 testSaab = new Saab95();
        double y1 = testSaab.speedFactor();
        Saab95 testSaab2 = new Saab95();
        testSaab2.setTurboOn();
        assertTrue(testSaab.speedFactor() < testSaab2.speedFactor());

        Volvo240 testVolvo = new Volvo240();
        double y = testVolvo.speedFactor();
        assertEquals(testVolvo.speedFactor(), testVolvo.getEnginePower() * 0.01 * Volvo240.trimFactor);
    }


    @Test
    void turbo(){
        Car testCar1 = new Saab95();
        testCar1.startEngine();
        testCar1.gas(0.5);
        Saab95 testCar2 = new Saab95();
        testCar2.startEngine();
        testCar2.setTurboOn();
        testCar2.gas(0.5);
        assertTrue(testCar2.getCurrentSpeed() > testCar1.getCurrentSpeed());
    }

    @Test
    void gas() {
        Car testSaab = new Saab95();
        startEngine();
        double currentSpeedBefore = testSaab.getCurrentSpeed();;
        assertThrows(IllegalArgumentException.class, () -> testSaab.gas(-0.1));
        assertThrows(IllegalArgumentException.class, () -> testSaab.gas(1.1));
        // Exception-testing

        for (double i = 0.1; i <= 1.0; i += 0.1) {
            testSaab.gas(i);
            assertNotEquals(currentSpeedBefore, testSaab.getCurrentSpeed());
            assertFalse(testSaab.getCurrentSpeed() <= currentSpeedBefore);
        // Test case for valid values
        }
    }

    @Test
    void brake() {
        Saab95 testSaab = new Saab95();
        testSaab.startEngine();
        double currentSpeedBefore = testSaab.getCurrentSpeed();
        assertThrows(IllegalArgumentException.class, () -> testSaab.brake(-0.1));
        assertThrows(IllegalArgumentException.class, () -> testSaab.brake(1.1));
        // Exception-testing

        for (double i = 0.0; i <= 1.0; i += 0.1) {
            testSaab.brake(i);
            assertTrue(testSaab.getCurrentSpeed() <= currentSpeedBefore);
            // Test case for valid values
        }


    }
}