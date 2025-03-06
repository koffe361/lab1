package position;

import car.Car;

import java.awt.*;
import java.awt.geom.Point2D;

public class Position  {

   ;
    Point2D.Double coordinates;
    private Direction currentDirection;

    public Position(Direction d, Point2D.Double p) {
        this.coordinates = p;
        this.currentDirection = d;
    }


    public Direction getDirection() {
        return this.currentDirection;
    }

    public void setDirection(Direction d) {
        this.currentDirection = d;
    }
    
    public Point2D.Double getCoordinates () {
        return this.coordinates;
    }

    public void setCoordinates(double x, double y) {
        this.coordinates.x =  x;
        this.coordinates.y =  y;
    }

    public double getX () {
        return this.coordinates.x;
    }

    public double getY () {
        return this.coordinates.y;
    }

    public void setX (Double x) {
        this.coordinates.x = x;
    }

    public void setY (Double y) {
        this.coordinates.y = y;
    }

    public void setCoordinates (Point2D.Double p) {
        this.coordinates = p;
    }

public boolean withinRadius(Point2D.Double p, double radius) {
    double dx = this.coordinates.x - p.x;
    double dy = this.coordinates.y - p.y;
    double distance = Math.sqrt(dx * dx + dy * dy);
    return distance <= radius;
}

    public boolean withinWorldRange(int maxX, int maxY, Point2D.Double p) {
        if(p.x < 0 || p.x > maxX) {return false;}
        if(p.y  < 0 || p.y > maxY) {return false;}
        else return true;
    }

    public Point2D.Double nextPosition (Double currentSpeed) {
        double x = this.getX();
        double y = this.getY();

        switch (getDirection()) {
            case Direction.EAST:
                return (new Point2D.Double ((x +  currentSpeed), y));
            case Direction.WEST:
                return (new Point2D.Double  ((x - currentSpeed), y));
            case Direction.SOUTH:
                return (new Point2D.Double (x , (y + currentSpeed)));
            case Direction.NORTH:
                return new Point2D.Double (x , (y -  currentSpeed));
        }
        return this.coordinates;
    }
}
