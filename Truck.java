import java.awt.*;

public  abstract class Truck extends Car {
    final  boolean raised = true, lowered = false;
    public boolean rampPosition;  // Raised/lowered

    public Truck() {
        super.transportableByTruck = false;
        super.nrDoors = 2;
    }

    @Override
    public void gas (double amount) {
        if (rampPosition == raised) {
        super.gas(amount);
        }
    }

    @Override
    public double speedFactor() {
        return enginePower * 0.01;
    }
}
