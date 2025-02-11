import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class TruckTest {

    // Scania-tester

    @Test
    void setRampPosition() {
        // Test for valid values
        Scania scaniaTest = new Scania();
        TransportTruck truckTest = new TransportTruck();
        double angleBefore = scaniaTest.angle;
        double amount = 50;
        scaniaTest.setRampPosition(50);
        assertEquals(amount, scaniaTest.angle);
        scaniaTest.setRampPosition(0);

        // Test for values out of bounds       
        assertThrows(IllegalArgumentException.class, () -> scaniaTest.setRampPosition(90));
        scaniaTest.setRampPosition(0);

        assertThrows(IllegalArgumentException.class, () -> scaniaTest.setRampPosition(-10));
        scaniaTest.setRampPosition(0);

        // Test for changing ramp-position while moving
        scaniaTest.startEngine();
        scaniaTest.gas(0.5);
        assertThrows(IllegalArgumentException.class, () -> scaniaTest.setRampPosition(20));

        truckTest.startEngine();
        truckTest.gas(0.5);
        truckTest.setRampPosition(true);
        assertFalse(truckTest.rampPosition);

        // Testing if raised or lowered
        scaniaTest.stopEngine();
        scaniaTest.setRampPosition(70);
        assertTrue(scaniaTest.rampPosition);

        scaniaTest.setRampPosition(0);
        assertTrue(!scaniaTest.rampPosition);

        // Test out of bounds value
        scaniaTest.setRampPosition(50);
        assertTrue(!scaniaTest.rampPosition);
    }

    @Test
    public void loadCar() {
        TransportTruck truckTest = new TransportTruck();
        Car saab95 = new Saab95();

        // Valid and invalid range, North
        truckTest.setDirection(Direction.NORTH);
        saab95.setX(truckTest.getX());
        saab95.setY(truckTest.getY()-1);
        assertTrue(truckTest.isWithinRange(saab95));

        saab95.setX(truckTest.getX());
        saab95.setY(truckTest.getY()-2);
        assertFalse(truckTest.isWithinRange(saab95));


        // Valid and invalid range, South
        truckTest.setDirection(Direction.SOUTH);
        saab95.setX(truckTest.getX());
        saab95.setY(truckTest.getY()+1);
        assertTrue(truckTest.isWithinRange(saab95));

        saab95.setX(truckTest.getX()+1);
        saab95.setY(truckTest.getY()+2);
        assertFalse(truckTest.isWithinRange(saab95));

        // Valid and invalid range, West
        truckTest.setDirection(Direction.WEST);
        saab95.setX(truckTest.getX()+1);
        saab95.setY(truckTest.getY());
        assertTrue(truckTest.isWithinRange(saab95));

        saab95.setX(truckTest.getX()+2);
        saab95.setY(truckTest.getY());
        assertFalse(truckTest.isWithinRange(saab95));

        // Valid and invalid range, East
        truckTest.setDirection(Direction.EAST);
        saab95.setX(truckTest.getX()-1);
        saab95.setY(truckTest.getY());
        assertTrue(truckTest.isWithinRange(saab95));

        saab95.setX(truckTest.getX());
        saab95.setY(truckTest.getY()-2);
        assertFalse(truckTest.isWithinRange(saab95));


        // Testing truck capacity
        TransportTruck transportTruck = new TransportTruck();
        transportTruck.stopEngine();
        transportTruck.setDirection(Direction.NORTH);
        transportTruck.setRampPosition(transportTruck.lowered);

        for (int i = 1; i < 6; i ++) {
            Saab95 car = new Saab95();
            car.setY( transportTruck.getY()-1);
            car.setX(transportTruck.getX());

            transportTruck.load(car);
        }

        assertTrue(transportTruck.cargo.size() == transportTruck.maxCargo);
        Car car = new Saab95();
        assertThrows(IllegalArgumentException.class, () -> transportTruck.load(car));
    }

}

