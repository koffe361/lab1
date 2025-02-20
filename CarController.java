import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.TypeDescriptor;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
*/

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());


    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed


    ArrayList<Car> cars = new ArrayList<>();

    ArrayList<Class<? extends Car>> carClasses = new ArrayList<>();
    CarMechanics<Volvo240> volvoWorkShop = new CarMechanics<>(carClasses, 5, 300, 0);

    //methods:
    public static void main(String[] args) {
        // Instance of this class

        Volvo240 volvo = new Volvo240();
        Saab95 saab95 = new Saab95();
        Scania scania = new Scania();

        volvo.setDirection(Direction.EAST);

        saab95.setY(100);

        scania.setY(200);

        CarController cc = new CarController();
        cc.cars.add(volvo);
        cc.cars.add(saab95);
        cc.cars.add(scania);

        cc.carClasses.add(Volvo240.class);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int index = 0;

            for (Car car : cars) {
                if (car instanceof Scania) {
                    System.out.println(((Scania) car).rampPosition);
                }

                if (car instanceof Volvo240) {
                    if (isWithinRadiusofWorkshop(car, volvoWorkShop.x, volvoWorkShop.y)) {
                        volvoWorkShop.loadWorkShop((Volvo240) car);
                        car.setX(volvoWorkShop.x);
                        car.setY(volvoWorkShop.y);
                    }
                }

                carInBounds();
                car.move();

                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                frame.drawPanel.moveit(index, x, y);


                System.out.println(volvoWorkShop.carsInWorkshop.size());
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                index++;
            }
        }
    }

    public <T extends Car> boolean isWithinRadiusofWorkshop (T car, int x, int y) {
        if ((car.getY() >= y - 10 && car.getY() <= y + 10)  && (car.getX() >= x - 10 && car.getX() <= x + 10)) {
            return true;
        }
        return false;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Car car : cars) {
           if (car.currentSpeed != 0) {
            car.gas(gas);
           }
        }
    }

    void brake (int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void startCar() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    void stopCar() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();

            }
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();

            }
        }
    }

    void lowerBedButton () {
        for (Car car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).setRampPosition(0);
            }
        }
    }

    void liftBedButton () {
        for (Car car: cars) {
            if (car instanceof Scania) {
                ((Scania) car).setRampPosition(70);
           }
        }
    }

    void carInBounds() {
        for (Car car : cars) {
            switch (car.getDirection()) {
            case NORTH:
                if (car.getY() - car.currentSpeed <= 0  ) {
                    car.stopEngine();
                    car.setDirection(Direction.SOUTH);
                    car.startEngine();
                }
                break;
            case SOUTH:
                if (car.getY() + car.currentSpeed >= 500) {
                    car.stopEngine();
                    car.setDirection(Direction.NORTH);
                    car.startEngine();
                }
                break;
            case Direction.WEST:
                if (car.getX()  - car.currentSpeed  <= 0 ) {
                    car.stopEngine();
                    car.setDirection(Direction.EAST);
                    car.startEngine();
                }
                break;
            case Direction.EAST:
                if (car.getX()  + car.currentSpeed >= 700 ) {
                    car.stopEngine();
                    car.setDirection(Direction.WEST);
                    car.startEngine();
                }
                break;
        }
        }
    }
}
