
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class CarMechanicsTest {


    @Test
    public void universalCarWorkshop() {
        ArrayList<Class<? extends Car>> carList = new ArrayList<>();
        carList.add(Car.class);

        CarMechanics  carWorkshop = new CarMechanics(carList, 5);

        assertTrue(carWorkshop.acceptableCar(new Saab95()));
        assertTrue(carWorkshop.acceptableCar(new Volvo240()));
        assertTrue(carWorkshop.acceptableCar(new TransportTruck()));
        assertTrue(carWorkshop.acceptableCar(new Scania()));

    }



    @Test //test for 2 acceptable car type workshop (Volvo and Saab)
    public void saabAndVolvoWorkshop() {
        ArrayList<Class<? extends Car>> carList = new ArrayList<>();
        carList.add(Volvo240.class);
        carList.add(Saab95.class);



// loading the workshop with 2 different cars
        CarMechanics<Car> saabAndVolvo = new CarMechanics(carList , 10);
        Volvo240 volvo =  new Volvo240();
        saabAndVolvo.loadWorkShop(volvo);

        Saab95 saab = new Saab95();
        saabAndVolvo.loadWorkShop(saab);

        assertTrue(saabAndVolvo.carsInWorkshop.size() == 2);
        Car repairedVolvo = saabAndVolvo.unloadWorkShop(volvo);
        assertTrue(saabAndVolvo.carsInWorkshop.size() == 1);
        assertEquals(repairedVolvo,volvo);

        Car truck = new TransportTruck();
        assertThrows(IllegalArgumentException.class, () -> saabAndVolvo.loadWorkShop(truck));
        assertFalse(saabAndVolvo.acceptableCar(truck));

        for ( int i = 0; i < 9; i ++) {
            saabAndVolvo.loadWorkShop(new Volvo240()); // fyller resterande 9 plattser med volvo
        }

        assertTrue(saabAndVolvo.carsInWorkshop.size() == 10);
        assertThrows(IllegalArgumentException.class, () -> saabAndVolvo.loadWorkShop(new Volvo240()));
    }


     @Test // test for only Saab workshop
     public void saabWorkshop() {
         ArrayList<Class<? extends Car>> saabList = new ArrayList<>();
         saabList.add(Saab95.class);
         CarMechanics<Saab95> saabWorkshop = new CarMechanics<Saab95>(saabList, 10);


         Saab95 saab =  new Saab95();
         saabWorkshop.loadWorkShop(saab);

         assertTrue(saabWorkshop.carsInWorkshop.size() == 1);

         Saab95 repairedSaab  = saabWorkshop.unloadWorkShop(saab);

         assertTrue(saabWorkshop.carsInWorkshop.isEmpty());
         assertEquals(repairedSaab, saab);

         Car volvo = new Volvo240();
         assertFalse(saabWorkshop.acceptableCar(volvo));
     }


    @Test
    public void truckWorkshop () {
        ArrayList<Class<? extends Car>> truckList = new ArrayList<>();
        truckList.add(TransportTruck.class);
        truckList.add(Scania.class);

        CarMechanics truckWorkshop = new CarMechanics<>(truckList, 4);
        TransportTruck transportTruck = new TransportTruck();
        Scania scania = new Scania();

        truckWorkshop.loadWorkShop(transportTruck);
        truckWorkshop.loadWorkShop(scania);

        assertThrows(IllegalArgumentException.class, () -> truckWorkshop.loadWorkShop(new Volvo240()));
    }

    @Test
    public void scaniaWorkshop() {
        ArrayList<Class<? extends Car>> scaniaList = new ArrayList<>();
        scaniaList.add(Scania.class);

        CarMechanics<Scania> truckWorkshop = new CarMechanics(scaniaList, 4);
        TransportTruck transportTruck = new TransportTruck();
         Scania scania = new Scania();

        truckWorkshop.loadWorkShop(scania);

    }

}
