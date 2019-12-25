import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DuidokuFrame {

    private JFrame frame;

    DuidokuFrame(){
        this.makeFrame();
    }

    private void makeFrame(){
        frame = new JFrame("Duidoku");
        frame.setLayout(new BorderLayout(6,6));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
