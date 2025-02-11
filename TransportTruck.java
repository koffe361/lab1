import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class TransportTruck extends Car implements HasRamp {
    public boolean rampPosition;
    protected final int maxCargo = 5;
    public Stack<Car> cargo = new Stack<>();

    public TransportTruck() {
        super.enginePower = 125;
        super.modelName = "Transporter";
        super.color = Color.black;
        this.rampPosition = lowered;
    }


    public <T extends Car & PassengerCar> void load (T car) {
       if (cargo.size() == maxCargo) {
           throw new IllegalArgumentException("Truck is full");
       }
       if (rampPosition == lowered && isWithinRange(car)) {
            cargo.push(car);
            car.setDirection(this.getDirection());
       }
    }

    public Car unload () {
        if (rampPosition == lowered && !cargo.isEmpty()) {
            Car car = cargo.pop();
            switch (getDirection()) {
                case Direction.NORTH:
                    car.setX(this.getX());
                    car.setY(this.getY() - 1);
                    break;
                case Direction.SOUTH:
                    car.setX(this.getX());
                    car.setY(this.getY() + 1);
                    break;
                case Direction.EAST:
                    car.setX(this.getX() - 1);
                    car.setY(this.getY());
                    break;
                case Direction.WEST:
                    car.setX(this.getX() + 1);
                    car.setY(this.getY());
                    break;

            }
            return car;
        }
        return null;
    }

            public boolean isWithinRange (Car car){
                switch (getDirection()) {
                    case Direction.NORTH:
                        if (this.getY() - car.getY() == 1 && this.getX() == car.getX()) {
                            return true;
                        }
                        break;
                    case Direction.SOUTH:
                        if (car.getY() - this.getY() == 1 && car.getX() == this.getX()) {
                            return true;
                        }
                        break;
                    case Direction.EAST:
                        if (this.getX() - car.getX() == 1 && this.getY() == car.getY()) {
                            return true;
                        }
                        break;
                    case Direction.WEST:
                        if (car.getX() - this.getX() == 1 && car.getY() == this.getY() ) {
                            return true;
                        }
                        break;

                }
                return false;
            }


    @Override
    public double speedFactor() {
        return 0;
    }

    // Moves the truck and uppdates the cars being transported
    @Override
    public void move() {
        super.move();
        double dY = this.getY();
        double dX = this.getX();
        for (Car car : cargo) {
            car.setY(dY);
            car.setX(dX);
            car.setDirection(this.getDirection());
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