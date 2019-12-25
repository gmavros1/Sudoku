import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassicFrame /*implements ActionListener */ {

    private JFrame frame;

    ClassicFrame(){
        this.makeFrame();
    }

    private void makeFrame(){
        frame = new JFrame("Classic Sudoku");
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
