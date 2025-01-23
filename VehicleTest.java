import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    Vehicle testVehicle = new Saab95();
    double x1 = testVehicle.x;
    double y1 = testVehicle.y;

    @Test
    void move() {
        testVehicle.currentDirection = Vehicle.Direction.FORWARD; // y += currentSpeed
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
        copy.currentDirection = Vehicle.Direction.RIGHT;
        copy.turnLeft();
        assertNotEquals(Vehicle.Direction.RIGHT,copy.currentDirection ); // CurrentD != RIGHT
        assertEquals(Vehicle.Direction.FORWARD, copy.currentDirection); // CurrentD == FORWARD

        copy.currentDirection = Vehicle.Direction.LEFT;
        copy.turnLeft();
        assertNotEquals(Vehicle.Direction.LEFT,copy.currentDirection ); // CurrentD != LEFT
        assertEquals(Vehicle.Direction.BACKWARD, copy.currentDirection); // CurrentD == BACKWARD

    }

    @Test
    void turnRight() {
        Vehicle copy = new Vehicle();
        copy.currentDirection = Vehicle.Direction.RIGHT;
        copy.turnRight();
        assertNotEquals(Vehicle.Direction.RIGHT,copy.currentDirection ); // CurrentD != RIGHT
        assertEquals(Vehicle.Direction.BACKWARD, copy.currentDirection); // CurrentD == BACKWARD

        copy.currentDirection = Vehicle.Direction.LEFT;
        copy.turnRight();
        assertNotEquals(Vehicle.Direction.LEFT,copy.currentDirection ); // CurrentD != LEFT
        assertEquals(Vehicle.Direction.FORWARD, copy.currentDirection); // CurrentD == FORWARD

    }

    @Test
    void startEngine() {
        Vehicle copy = new Vehicle();
        copy.startEngine(); // set currentSpeed to 0.1
        assertNotEquals(testVehicle.currentSpeed, copy.currentSpeed);
    }

    @Test
    void stopEngine() {
        Vehicle copy = new Vehicle();
        copy.startEngine();
        copy.stopEngine();
        assertEquals(testVehicle.currentSpeed, copy.currentSpeed);
    }

    @Test
    void speedFactor() {
        Vehicle testSaab = new Vehicle();
        Vehicle testVolvo = new Vehicle();
    }

    @Test
    void incrementSpeed() {
        Vehicle testSaab = new Vehicle();
        Vehicle testVolvo = new Vehicle();
        testVolvo.startEngine();
        testSaab.startEngine();
        double speed1 = testVolvo.currentSpeed;
        double speed2 = testSaab.currentSpeed;
    }

    @Test
    void decrementSpeed() {
        Vehicle testSaab = new Vehicle();
        Vehicle testVolvo = new Vehicle();
    }

    @Test
    void gas() {
    }

    @Test
    void brake() {
    }


}