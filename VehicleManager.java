import car.*;
import position.Direction;
import position.Position;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VehicleManager {
    int worldX;
    int worldY;
    final int maxCars = 10;
    ArrayList<Integer> index = new ArrayList<>();

    public Map<Integer, Point2D.Double> currentCoordinates = new HashMap<>();

    ArrayList<Car> cars = new ArrayList<>();

    //verkstad instanser;
    ArrayList<Class<? extends Car>> volvoList = new ArrayList<>();
    CarWorkshop<Car> volvoWorkshop = new CarWorkshop<>(volvoList, 5, new Position(null, new Point2D.Double(300,0 )));

    public VehicleManager(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        volvoList.add(Volvo240.class);
    }

    public int carIndex (Car input) {
        Random rand = new Random();
        if (input instanceof Volvo240) {
            int index1 = rand.nextInt(10);
            if (index.contains(index1)) { carIndex(input);}
            else return index1;
        }
        else if (input instanceof Saab95) {
            int index1 =  rand.nextInt(10)+10;
            if (index.contains(index1)) { carIndex(input);}
            else return index1;
        }
        else if (input instanceof Scania) {
            int index1 = rand.nextInt(10)+20;
            if (index.contains(index1)) { carIndex(input);}
            else return index1;
        }
        else {
            throw new IllegalArgumentException("Invalid car");
        }
        return -1;
    }

    public void updateState() {

        for (Car car : cars) {


            if (car instanceof Volvo240) {
                    volvoWorkshop.loadWorkShop(car);
            }

            // carInBounds();
            collisionWithWall(car);
            car.move();



        }
    }

public void collisionWithWall(Car car) {
    Point2D.Double newP = car.position.nextPosition(car.getCurrentSpeed());
     if(!car.position.withinWorldRange(this.worldX-99, this.worldY-299, newP)) {
         car.stopEngine();
         car.turnLeft(); // Rotating 180 deg
         car.turnLeft();
         car.startEngine();
     }
    }

    public void gas(Double gasAmount) {
        for (Car car: cars) {
            car.gas(gasAmount);
        }
    }

    public void brake(double gasAmount) {
        for (Car car : cars) {
            car.brake((gasAmount));
        }
    }

    public void startCar() {
        for (Car car : cars) {
            System.out.println("start car");
            car.startEngine();
        }
    }

    public void stopCar() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }


    public void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Car car : cars) {
        if ( car instanceof  Saab95) {
            ((Saab95) car).setTurboOff();
        }
        }
    }

    public void liftBed() {
        for (Car car : cars) {
        if (car instanceof Scania) {
            ((Scania) car).setRampPosition(70);
        }}
    }

    public void lowerBed() {
        for (Car car : cars) {
        if (car instanceof  Scania) {
            ((Scania) car).setRampPosition(0);
            }
        }
    }

    public void addCar(String carType) {
        if (cars.size() < maxCars) {
            Car newCar = VehicleFactory.createRandomCar();
            if (newCar != null) {
                cars.add(newCar);
                addCarToHashmap(newCar);
            }
        }
    }

    public void addCarToHashmap(Car car) {
       int i = carIndex(car);
       while(currentCoordinates.get(i) != null) { i = carIndex(car );}
       Point2D.Double p = car.position.getCoordinates();
       currentCoordinates.put(i,p);
       index.add(i);
    }

    public void removeCar() {
        cars.removeLast();
        int i = index.getLast();
        index.removeLast();
        currentCoordinates.remove(i);
    }
}

