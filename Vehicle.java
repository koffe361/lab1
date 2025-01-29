
import java.awt.*;

public class Vehicle implements Moveable {
    protected int nrDoors; // alternativt private, men då krävs setMetod
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected String modelName;
    protected double x = 0;
    protected double y = 0;

    public enum Direction {
        NORTH, SOUTH, WEST, EAST
    }

    Direction currentDirection = Direction.NORTH;


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

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        if (amount < 0.0 || amount > 1.0) {
            throw new IllegalArgumentException("Amount too low or too high");}
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount too low or too high");}
        decrementSpeed(amount);
    }

    @Override
    public void move() {
        switch (currentDirection) {
            case EAST:
                this.x += currentSpeed;
                break;
            case WEST:
                this.x -= currentSpeed;
                break;
            case SOUTH:
                this.y -= currentSpeed;
                break;
            case NORTH:
                this.y += currentSpeed;
                break;
        }
    }

    @Override
    public void turnLeft() {
        switch (currentDirection) {
            case NORTH:
                currentDirection = Direction.WEST;
                break;
            case WEST:
                currentDirection = Direction.SOUTH;
                break;
            case SOUTH:
                currentDirection = Direction.EAST;
                break;
            case EAST:
                currentDirection = Direction.NORTH;
                break;
        }
    }

    @Override
    public void turnRight() {
        switch (currentDirection) {
            case NORTH:
                currentDirection = Direction.EAST;
                break;
            case EAST:
                currentDirection = Direction.SOUTH;
                break;
            case SOUTH:
                currentDirection = Direction.WEST;
                break;
            case WEST:
                currentDirection = Direction.NORTH;
                break;
        }
    }

}
