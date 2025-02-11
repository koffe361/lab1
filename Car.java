
import java.awt.*;

public abstract class Car implements Moveable {
    
    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected String modelName;
    private double x = 0;
    private double y = 0;
    private Direction currentDirection = Direction.NORTH;
    public boolean transportableByTruck = true;

    public double getY(){
        return this.y;
    }

    public void  setY(double y1) { y = y1 ;}

    public double getX(){
        return this.x;
    }

    public void  setX(double x1) { x = x1; }

    public Direction getDirection() {
        return this.currentDirection;
    }

    public void setDirection(Direction d) {
        this.currentDirection = d;
    }

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

    public abstract double speedFactor();

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
