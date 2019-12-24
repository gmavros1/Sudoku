import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KillerFrame {

    private JFrame frame;

    KillerFrame(){
        this.makeFrame();
    }

    private void makeFrame(){
        frame = new JFrame("Killer Sudoku");
        frame.setLayout(new BorderLayout(6,6));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
