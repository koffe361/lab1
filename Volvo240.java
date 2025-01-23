import java.awt.*;

public class Volvo240 extends Vehicle{

    public final static double trimFactor = 1.25; // unik för Volvo240
    
    public Volvo240(){
     this.nrDoors = 4;
     this.color = Color.black;
     this.enginePower = 100;
     this.modelName = "Volvo240";
     this.stopEngine();
    }

    @Override
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    @Override
    public void incrementSpeed(double amount){
	    currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    @Override
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

}
