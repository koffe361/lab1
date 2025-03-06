import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeListener implements ActionListener {
    CarController cc;

    public TimeListener (CarController cc) {
        this.cc = cc;
    }

    public void actionPerformed(ActionEvent e) {
    cc.updateStatus();
    }
}