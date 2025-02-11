import java.util.ArrayList;

public class CarMechanics<T extends  Car> {


    public final ArrayList<Class<? extends Car>> carTypes; // tillåter alla klasser av subtyp till car
    public ArrayList<T> carsInWorkshop  = new ArrayList<>();
    public final int maxCars;


    public CarMechanics(ArrayList<Class<? extends Car>> carTypes, int maxCars) {
        this.carTypes = carTypes;
        this.maxCars = maxCars;
    }


    public  void  loadWorkShop(T car) {
        if (!acceptableCar(car)) {throw new IllegalArgumentException("Car is not allowed in here!!");}
        if (carsInWorkshop.size() == maxCars) {
            throw  new IllegalArgumentException("Workshop is full!");
        }
        else carsInWorkshop.add(car);
    }

    public T unloadWorkShop (T input) { // ändra o se om vi kan fpå ut
        T carResult = null;
        for (T car : carsInWorkshop) {
            if (input.equals(car)) {
                carResult = car;
                carsInWorkshop.remove(car);
                break;
            }
        }
        if (carResult == null) {throw  new IllegalArgumentException("Car not in the workshop");}
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
