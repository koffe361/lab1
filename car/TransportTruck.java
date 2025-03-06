package car;



import java.awt.*;
import java.util.Stack;

public class     TransportTruck extends Car implements HasRamp {
    public boolean rampPosition;
    public final int maxCargo = 5;
    public Stack<Car> cargo = new Stack<>();

    public TransportTruck() {
        super.enginePower = 125;
        super.modelName = "Transporter";
        super.color = Color.black;
        this.rampPosition = lowered;
    }

    public <T extends Car & Transportable> void load (T car) {
       if (cargo.size() == maxCargo) {
           throw new IllegalArgumentException("Truck is full");
       }
       if (rampPosition == lowered && car.position.withinRadius(this.position.getCoordinates(),1)) {
            cargo.push(car);
            car.position.setDirection(this.position.getDirection());
       }
    }

    public Car unload () {
        if (rampPosition == lowered && !cargo.isEmpty()) {
            Car car = cargo.pop();
            car.unloadCar(this.position.getCoordinates(), this.position.getDirection());
            return car;
        }
        return null;
    }

    @Override
    public double speedFactor() {
        return 0;
    }

    // Moves the truck and updates the cars being transported
    @Override
    public void move() {
        super.move();

        double dY = this.position.getY();
        double dX = this.position.getX();
        for (Car car : cargo) {
            car.position.setY(dY);
            car.position.setX(dX);
            car.position.setDirection(this.position.getDirection());
        }
    }

    @Override
    public void setRampPosition() {
    }

    public void setRampPosition(Boolean b) {
        if (this.currentSpeed == 0){
            rampPosition = b;
        }
    }
}