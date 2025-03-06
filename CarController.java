
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
*/

public class CarController {

    private VehicleManager manager;

    private CarView frame;

    private final int delay = 50;
    private Timer timer =  new Timer(delay, new TimeListener(this));

    //methods:
    public CarController(){

        // Instance of this class

      //  CarController cc = new CarController();
        manager = new VehicleManager(800, 800);

        // Start a new view and send a reference of self
        frame = new CarView("CarSim 1.0", manager.worldX, manager.worldY);

        // Start the timer
        timer.start();

        frame.addGasListener( e -> manager.gas(frame.getGasAmount()));
        frame.addBrakeListener(e -> manager.brake(frame.getGasAmount()));
        frame.addStartListener(e -> manager.startCar());
        frame.addStopListener(e -> manager.stopCar());
        frame.addTurboOnListener(e -> manager.turboOn());
        frame.addTurboOffListener(e -> manager.turboOff());
        frame.addLiftBedListener(e -> manager.liftBed());
        frame.addLowerBedListener(e -> manager.lowerBed());
        frame.addAddCarListener(e -> manager.addCar("random"));
        frame.addRemoveCarListener(e -> manager.removeCar());
    }

    public static void main(String[] args) {
        CarController cc = new CarController();
    }

    public void updateStatus(){
        this.manager.updateState();

        if (manager.cars.isEmpty()) {
            frame.updatePanel(new HashMap<>());
        }
        frame.updatePanel(manager.currentCoordinates);

        frame.repaintPanel();
    }
}



