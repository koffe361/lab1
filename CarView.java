import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Map;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame {
     final int X;
     final int Y;


    // The controller member
    DrawPanel drawPanel;
    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Raise Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");
    JButton addCarButton = new JButton("Add Car");
    JButton removeCarButton = new JButton("Remove Car");


    // Constructor
    public CarView(String name, int worldX, int worldY) {
        this.X = worldX;
        this.Y = worldY;

        this.drawPanel = new DrawPanel(X, Y - 240);
        this.setTitle(name);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.add(drawPanel);

        initComponents();
    }

    public void updatePanel(Map<Integer, Point2D.Double> cars){
        System.out.println(cars.size());
        drawPanel.setMap(cars);
    }

    public void repaintPanel() {
        this.drawPanel.repaint();
    }

    private void initComponents() {

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        gasSpinner = new JSpinner(spinnerModel);

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 4));
        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(addCarButton,3);
        controlPanel.add(brakeButton, 4);
        controlPanel.add(turboOffButton, 5);
        controlPanel.add(lowerBedButton, 6);
        controlPanel.add(removeCarButton,7);
        controlPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(stopButton);

        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // metod för att skapa en ActionListener
    public double getGasAmount() {
        return ((int) gasSpinner.getValue()) / 100.0;
       // gasAmount = (int) ((JSpinner)e.getSource()).getValue();
    }

    public void addGasListener(ActionListener listener) {
        gasButton.addActionListener(listener);
    }

    public void addBrakeListener(ActionListener listener) {
        brakeButton.addActionListener(listener);
    }

    public void addStartListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void addStopListener(ActionListener listener) {
        stopButton.addActionListener(listener);
    }

    public void addTurboOnListener(ActionListener listener) {
        turboOnButton.addActionListener(listener);
    }

    public void addTurboOffListener(ActionListener listener) {
        turboOffButton.addActionListener(listener);
    }

    public void addLiftBedListener(ActionListener listener) {
        liftBedButton.addActionListener(listener);
    }

    public void addLowerBedListener(ActionListener listener) {
        lowerBedButton.addActionListener(listener);
    }

    public void addAddCarListener(ActionListener listener) {
        addCarButton.addActionListener(listener);
    }

    public void addRemoveCarListener(ActionListener listener) {
        removeCarButton.addActionListener(listener);
    }
}

