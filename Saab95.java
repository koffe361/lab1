import java.awt.*;

public class Saab95 extends Vehicle{

    private boolean turboOn;
    
    public Saab95(){
        this.nrDoors = 2;
        this.color = Color.red;
        this.enginePower = 125;
	    this.turboOn = false;       //unikt för Saab
        this.modelName = "Saab95";
        stopEngine();
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    @Override
    public void incrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    @Override
    public void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

}
