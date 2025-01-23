
import java.awt.*;

public class Vehicle implements Moveable {
    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected String modelName;
    protected int x = 0;
    protected int y = 0;

    public enum Direction {
        FORWARD, BACKWARD, LEFT, RIGHT
    }

    Direction currentDirection = Direction.FORWARD;

    public int getNrDoors(){
        return this.nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public double speedFactor(){
        return 0;
    };

    public void incrementSpeed(double amount){};

    public void decrementSpeed(double amount){};

    // TODO fix this method according to lab pm
    public void gas(double amount){
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount in not between 0 and 1");}
        incrementSpeed(amount);
        if (currentSpeed < 0 || currentSpeed > enginePower) {}

    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        decrementSpeed(amount);
    }


    @Override
    public void move() {
        switch (currentDirection) {
            case RIGHT:
                this.x += currentSpeed;
                break;
            case LEFT:
                this.x -= currentSpeed;
                break;
            case BACKWARD:
                this.y -= currentSpeed;
                break;
            case FORWARD:
                this.y += currentSpeed;
                break;
        }
    }

    @Override
    public void turnLeft() {
        switch (currentDirection) {
            case FORWARD:
                currentDirection = Direction.LEFT;
                break;
            case LEFT:
                currentDirection = Direction.BACKWARD;
                break;
            case BACKWARD:
                currentDirection = Direction.RIGHT;
                break;
            case RIGHT:
                currentDirection = Direction.FORWARD;
                break;
        }
    }

    @Override
    public void turnRight() {
        switch (currentDirection) {
            case FORWARD:
                currentDirection = Direction.RIGHT;
                break;
            case RIGHT:
                currentDirection = Direction.BACKWARD;
                break;
            case BACKWARD:
                currentDirection = Direction.LEFT;
                break;
            case LEFT:
                currentDirection = Direction.FORWARD;
                break;
        }
    }

}
