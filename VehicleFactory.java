import car.*;

import java.util.Random;
import java.util.random.RandomGenerator;

public class VehicleFactory {

    public static Car createCar (String type) {

        if (type.equalsIgnoreCase("Volvo240")) {
            return new Volvo240();
        }
        if (type.equalsIgnoreCase("Saab95")) {
            return new Saab95();
        }
        else {
            throw new IllegalArgumentException("Unknown car!");
        }
    }

    public static Car createTruck (String type) {
        if (type.equalsIgnoreCase("TransportTruck")) {
            return new TransportTruck();
        }
        if (type.equalsIgnoreCase("Scania")) {
            return new Scania();
        }
        else {
            throw new IllegalArgumentException("Unknown truck!");
        }
    }

    public static Car createRandomCar(){
          Random rand = new Random();
          int i = rand.nextInt(3);
          switch (i){
            case 0:
                 return createCar("Volvo240");
              case 1:
                  return createCar("Saab95");
              case 2:
                  return createTruck("Scania");
          }
    return null;
    }
}
