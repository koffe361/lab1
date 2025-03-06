package car;

import position.Position;

import java.util.ArrayList;

public class CarWorkshop <T extends  Car> {

    public final ArrayList<Class<? extends Car>> carTypes; // tillåter alla klasser av subtyp till car
    public ArrayList<T> carsInWorkshop = new ArrayList<>();
    public final int maxCars;
    public Position position;

    public CarWorkshop(ArrayList<Class<? extends Car>> carTypes, int maxCars, Position p) {
        this.carTypes = carTypes;
        this.maxCars = maxCars;
        this.position = p;
    }

    public void loadWorkShop (T car) {
        if (!carAlreadyInWorkshop (car) && car.position.withinRadius(this.position.getCoordinates(), 50)) {
            if (!acceptableCar(car)) {
                throw new IllegalArgumentException("car.Car is not allowed in here!!");
            }
            if (carsInWorkshop.size() == maxCars) {
                throw new IllegalArgumentException("Workshop is full!");
            } else carsInWorkshop.add(car);
            car.loadCar(position.getCoordinates(), null);
        }
    }

    boolean carAlreadyInWorkshop(T input) {
        for (T car : carsInWorkshop) {
            if (input.equals(car)) {
                return true;
            }
        }
        return false;
    }

    public T unloadWorkShop (T input) { // ändra o se om vi kan fpå ut
        T carResult = null;
        for (T car : carsInWorkshop) {
            if (input.equals(car)) {
                carResult = car;
                carsInWorkshop.remove(car);
                car.loaded = false;
                car.startEngine();
                break;
            }
        }
        if (carResult == null) {throw  new IllegalArgumentException("car.Car not in the workshop");}
        return carResult;
    }


    public boolean acceptableCar (Car input) {
        boolean currentState = false;
        for (Class<? extends Car> car : carTypes ) {
            if (car.isInstance(input)) {
                currentState = true; // kollar om car är av samma class som tilåtna typer
            break;
            }
         }
        return currentState;
    }
}
