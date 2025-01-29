import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    Vehicle testVehicle = new Saab95();
    double x1 = testVehicle.x;
    double y1 = testVehicle.y;

    @Test
    void move() {
        testVehicle.currentDirection = Vehicle.Direction.NORTH; // y += currentSpeed
        double speed = testVehicle.currentSpeed;
        testVehicle.startEngine();
        testVehicle.incrementSpeed(20);
        speed = testVehicle.currentSpeed;
        testVehicle.move();
        double y2 = testVehicle.y;
        double x2 = testVehicle.x;
        assertEquals(y2, testVehicle.y);
        assertNotEquals(y1, testVehicle.y);
    }

    @Test
    void turnLeft() {
        Vehicle copy = new Vehicle();
        copy.currentDirection = Vehicle.Direction.EAST;
        copy.turnLeft();
        assertNotEquals(Vehicle.Direction.EAST,copy.currentDirection ); // CurrentD != RIGHT
        assertEquals(Vehicle.Direction.NORTH, copy.currentDirection); // CurrentD == FORWARD

        copy.currentDirection = Vehicle.Direction.WEST;
        copy.turnLeft();
        assertNotEquals(Vehicle.Direction.WEST,copy.currentDirection ); // CurrentD != LEFT
        assertEquals(Vehicle.Direction.SOUTH, copy.currentDirection); // CurrentD == BACKWARD

    }

    @Test
    void turnRight() {
        Vehicle copy = new Vehicle();
        copy.currentDirection = Vehicle.Direction.EAST;
        copy.turnRight();
        assertNotEquals(Vehicle.Direction.EAST,copy.currentDirection ); // CurrentD != RIGHT
        assertEquals(Vehicle.Direction.SOUTH, copy.currentDirection); // CurrentD == BACKWARD

        copy.currentDirection = Vehicle.Direction.WEST;
        copy.turnRight();
        assertNotEquals(Vehicle.Direction.WEST,copy.currentDirection ); // CurrentD != LEFT
        assertEquals(Vehicle.Direction.NORTH, copy.currentDirection); // CurrentD == FORWARD

    }

    @Test
    void startEngine() {
        Vehicle copy = new Saab95();
        copy.startEngine(); // set currentSpeed to 0.1
        assertNotEquals(testVehicle.getCurrentSpeed(), copy.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        Vehicle copy = new Saab95();
        copy.startEngine();
        copy.stopEngine();
        assertEquals(testVehicle.getCurrentSpeed(), copy.getCurrentSpeed());
    }

    @Test
    void speedFactor() {
        Vehicle testSaab = new Saab95();
        Vehicle testVolvo = new Vehicle();
    }

    @Test
    void incrementSpeed() {
        Vehicle testSaab = new Saab95();
        testSaab.startEngine();

        for (double i = 0.0; i <= 1.0; i+= 0.1) {
            double currentSpeedBefore = testSaab.getCurrentSpeed();
            testSaab.currentSpeed = Math.min(testSaab.getCurrentSpeed() + testSaab.speedFactor() * i, testSaab.enginePower);
            assertTrue(currentSpeedBefore <= testSaab.getCurrentSpeed());
        }

    }

    @Test
    void decrementSpeed() {
        Vehicle testSaab = new Saab95();
        testSaab.startEngine();
        double currentSpeedBefore = testSaab.currentSpeed;

        for (double i = 0.0; i <= 1.0; i+= 0.1) {
            testSaab.brake(i);
            assertTrue(currentSpeedBefore >= testSaab.getCurrentSpeed());
        }

    }

    @Test
    void gas() {
        Vehicle testSaab = new Saab95();
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
        Vehicle testSaab = new Saab95();
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