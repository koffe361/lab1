import java.util.ArrayList;

public class CarMechanics {


//    Uppgift 3: Parametrisk polymorfism
//    Skapa en representation av en bilverkstad. Följande aspekter ska hanteras:
//
//    En verkstad ska kunna ta emot ("lasta"?) ett antal bilar, upp till något max-antal som kan variera mellan olika verkstäder.
//    Vissa verkstäder ska bara kunna ta emot en viss sorts bilar; andra kan ta emot vilka bilar som helst.
//    Att försöka lämna in "fel" sorts bil i en verkstad ska ge ett statiskt (compile time) fel.
//    Vid uthämtning av en bil från verkstaden ska vi kunna få så precis typinformation som möjligt statiskt.
//            Exempel: För en märkesverkstad som enbart hanterar Volvo 240 bör vi statiskt kunna veta att bilar som hämtas ut från verkstaden alltid är just Volvo 240.


    public final ArrayList<Class<? extends Car>> carTypes; // tillåter alla klasser av subtyp till car
    public ArrayList<Car> carsInWorkshop  = new ArrayList<>();
    public final int maxCars;


    public CarMechanics(ArrayList<Class<? extends Car>> carTypes, int maxCars) {
        this.carTypes = carTypes;
        this.maxCars = maxCars;
    }


    public void loadWorkShop(Car car) {
        if (!acceptableCar(car)) {throw new IllegalArgumentException("Car is not allowed in here!!");}
        if (carsInWorkshop.size() == maxCars) {
            throw  new IllegalArgumentException("Workshop is full!");
        }
        else carsInWorkshop.add(car);
    }

    public Car unloadWorkShop (Car input) {
        Car carResult = null;
        for (Car car : carsInWorkshop) {
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
            break;}
         }
        return currentState;
    }
}
