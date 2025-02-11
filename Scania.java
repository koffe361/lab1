import java.awt.*;

public class Scania extends Car implements HasRamp {
    public boolean rampPosition;
    public double angle;

          public Scania(){
            super.color = Color.blue;
            super.enginePower = 100;
            super.modelName = "Scania";
            this.rampPosition = lowered;
            this.angle = 0;
            stopEngine();
    }

    @Override
    public double speedFactor() {
        return 0;
    }


    public void setRampPosition (double amount) {
        if (currentSpeed == 0 && amount >= 0 && amount <= 70) {
            angle = amount;

            if (amount == 70) {
                rampPosition = true ;
            }
            else
                rampPosition = false;
        }
        else {throw new IllegalArgumentException("Angle can only be set between 0 an 70 and truck is parked");}
    }

    @Override
    public void setRampPosition() {

    }
}
