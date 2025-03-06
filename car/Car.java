package car;

import position.Direction;
import position.Position;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Car implements Moveable {
    
    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected String modelName;
    public Position position = new Position(Direction.NORTH, new Point2D.Double(0, 0));
    public Boolean loaded = false; // State

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

    public void startEngine() {
        if (this instanceof Scania && ((Scania) this).rampPosition) {
        } else if (getCurrentSpeed() == 0) {
            currentSpeed = 0.1;
        }
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
        if ( currentSpeed != 0) {
        if (amount < 0.0 || amount > 1.0) {
            throw new IllegalArgumentException("Amount too low or too high");}
        incrementSpeed(amount);}
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount too low or too high");}
        decrementSpeed(amount);
    }

    public void loadCar (Point2D.Double p, Direction d) {
        this.stopEngine();
        this.loaded = true;
        position.setCoordinates(p);
        if (d != null) {
        position.setDirection(d);}
    }

    public void unloadCar (Point2D.Double p, Direction d) {
        this.startEngine();
        this.loaded = false;
        position.setDirection(d);
        this.position.setCoordinates(p);
    }

    @Override
    public void move() {
        double x = position.getX();
        double y = position.getY();

        if (!this.loaded) {
            switch (position.getDirection()) {
                case Direction.EAST:
                    position.setCoordinates(x + currentSpeed, y);
                    break;
                case Direction.WEST:
                    position.setCoordinates((x - currentSpeed), y);
                    break;
                case Direction.SOUTH:
                    position.setCoordinates(x ,y + currentSpeed);
                    break;
                case Direction.NORTH:
                    position.setCoordinates(x ,y - currentSpeed);
                    break;
            }
        }
    }

    @Override
    public void turnLeft() {
        switch (position.getDirection()) {
            case Direction.NORTH:
                position.setDirection(Direction.WEST);
                break;
            case Direction.WEST:
                position.setDirection(Direction.SOUTH);
                break;
            case Direction.SOUTH:
                position.setDirection(Direction.EAST);
                break;
            case Direction.EAST:
                position.setDirection(Direction.NORTH);
                break;
        }
    }

    @Override
    public void turnRight() {
        switch (position.getDirection()) {
            case Direction.NORTH:
                position.setDirection(Direction.EAST);
                break;
            case Direction.WEST:
                position.setDirection(Direction.NORTH);
                break;
            case Direction.SOUTH:
                position.setDirection(Direction.WEST);
                break;
            case Direction.EAST:
                position.setDirection(Direction.SOUTH);
                break;
        }
    }
}
