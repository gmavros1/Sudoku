import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassicFrame /*implements ActionListener */ {

    private JFrame frame;
    //private JButton board[][];
    General classic;

    ClassicFrame(){
        //board = new JButton[9][9];
        classic = new Classic();
        this.makeFrame();

    }

    private void makeFrame(){

        frame = new JFrame("Classic Sudoku");
        frame.setLayout(new BorderLayout());

        Container contentPane = frame.getContentPane();

        GridLayout board = new GridLayout(9, 9);
        contentPane.setLayout(board);


        for (int i=0; i<9; i++)
            for (int j=0; j<9; j++){
                JButton button = new JButton((i+1)+","+(j+1));
                contentPane.add(button);

            }


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
