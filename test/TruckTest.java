import car.*;
import org.junit.jupiter.api.Test;
import position.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class TruckTest {

    // car.Scania-tester

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

        System.out.println(truckTest.position.getCoordinates());
        saab95.position.setX(truckTest.position.getX());
        saab95.position.setY(truckTest.position.getY());
        assertTrue(saab95.position.withinRadius(truckTest.position.getCoordinates(),1));


        saab95.position.setX(10.0);
        saab95.position.setY(10.0);
        assertFalse(saab95.position.withinRadius(truckTest.position.getCoordinates(),1));


        // Testing truck capacity
        TransportTruck transportTruck = new TransportTruck();
        transportTruck.stopEngine();
        transportTruck.position.setDirection(Direction.NORTH);
        transportTruck.setRampPosition(transportTruck.lowered);

        for (int i = 1; i < 6; i ++) {
            Saab95 car = new Saab95();
            car.position.setY( transportTruck.position.getY());
            car.position.setX(transportTruck.position.getX());

            transportTruck.load(car);
        }

        assertTrue(transportTruck.cargo.size() == transportTruck.maxCargo);
        Car car = new Saab95();
    }

    @Test
    public void unloadCar() {
        TransportTruck testTruck = new TransportTruck();
        testTruck.stopEngine();
        testTruck.position.setDirection(Direction.NORTH);
        testTruck.setRampPosition(testTruck.lowered);

        Saab95 saab = new Saab95();
        saab.position.setY( testTruck.position.getY());
        saab.position.setX(testTruck.position.getX());

        Volvo240 volvo = new Volvo240();
        volvo.position.setY( testTruck.position.getY());
        volvo.position.setX(testTruck.position.getX());

        testTruck.load(saab);
        testTruck.load(volvo);

        testTruck.setRampPosition(testTruck.raised);
        testTruck.startEngine();
        testTruck.gas(0.4);
        testTruck.move();

        testTruck.stopEngine();
        testTruck.setRampPosition(testTruck.lowered);

        int sizeBefore = testTruck.cargo.size();
        Car car = testTruck.unload();
        int sizeAfter = testTruck.cargo.size();
        testTruck.move();
        assertEquals(car, volvo);
        assertTrue(sizeAfter == sizeBefore -1);
        assertTrue(car.position.getCoordinates() == testTruck.position.getCoordinates());

    }
}

