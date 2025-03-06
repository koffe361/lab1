import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage saab95Image;
    BufferedImage scaniaImage;

    //public ArrayList<BufferedImage> images = new ArrayList<>();

    public Map<Integer, Point2D.Double> carsToBeDrawn = new HashMap<>();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 0);

    // TODO: Make this general for all cars
//    void moveit(int index, Double x, Double y) {
//        int intx = x.intValue();
//        int inty = y.intValue();
//        Point p = new Point(intx,inty);
//        carsToBeDrawn.put(index, p);
//    }

    public void setMap(Map<Integer, Point2D.Double> cars) {
        carsToBeDrawn = cars;
    }


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("car.Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            saab95Image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        for(int i = 0; i < 10; i ++ ){
//        images.add(volvoImage);}
//        for(int i = 0; i < 10; i ++ ){
//        images.add(saab95Image);}
//        for(int i = 0; i < 10; i ++ ){
//        images.add(scaniaImage);}
    }


    public BufferedImage getImage (int index) {
        if (index < 10) {
            return volvoImage;
        }
        else if (index < 20) {
            return saab95Image;
        }
        else if (index < 30) {
            return scaniaImage;
        }
        else {
            return null;
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (Map.Entry<Integer, Point2D.Double> entry : carsToBeDrawn.entrySet()) {

            int index = entry.getKey();
            Point2D.Double point = entry.getValue();
            Double x = point.x;
            Double y = point.y;
            int intX = x.intValue();
            int inty = y.intValue();

            BufferedImage i = getImage(index);

            g.drawImage(i, intX, inty, null);

        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
