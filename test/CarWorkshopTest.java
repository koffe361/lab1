
import car.*;
import org.junit.jupiter.api.Test;
import position.Position;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class CarWorkshopTest {


    @Test
    public void universalCarWorkshop() {
        ArrayList<Class<? extends Car>> carList = new ArrayList<>();
        carList.add(Car.class);

        CarWorkshop  carWorkshop = new CarWorkshop(carList, 5, new Position(null, new Point2D.Double (0, 0)));

        assertTrue(carWorkshop.acceptableCar(new Saab95()));
        assertTrue(carWorkshop.acceptableCar(new Volvo240()));
        assertTrue(carWorkshop.acceptableCar(new TransportTruck()));
        assertTrue(carWorkshop.acceptableCar(new Scania()));

        Volvo240 volvo =  new Volvo240();
        carWorkshop.loadWorkShop(volvo);

        Saab95 saab = new Saab95();
        carWorkshop.loadWorkShop(saab);

        assertTrue(carWorkshop.carsInWorkshop.size() == 2);
        Car repairedVolvo = carWorkshop.unloadWorkShop(volvo);
        assertTrue(carWorkshop.carsInWorkshop.size() == 1);
        assertEquals(repairedVolvo,volvo);


        for ( int i = 0; i < 4; i ++) {
            carWorkshop.loadWorkShop(new Volvo240());
        }
        assertTrue(carWorkshop.carsInWorkshop.size() == 5);
        assertThrows(IllegalArgumentException.class, () -> carWorkshop.loadWorkShop(new Volvo240()));
    }


     @Test // test for only Saab workshop
     public void saabWorkshop() {
         ArrayList<Class<? extends Car>> saabList = new ArrayList<>();
         saabList.add(Saab95.class);
         CarWorkshop<Saab95> saabWorkshop = new CarWorkshop<>(saabList, 10, new Position(null, new Point2D.Double(0,0)));

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
    public void scaniaWorkshop() {
        ArrayList<Class<? extends Car>> scaniaList = new ArrayList<>();
        scaniaList.add(Scania.class);

        CarWorkshop<Scania> truckWorkshop = new CarWorkshop<>(scaniaList, 4, new Position(null, new Point2D.Double(0,0)));
        TransportTruck transportTruck = new TransportTruck();
        Scania scania = new Scania();

        truckWorkshop.loadWorkShop(scania);
    }
}
