import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ClassicFrame /*implements ActionListener */ {

    private JFrame frame;
    private JButton[][] board = new JButton [9][9];
    private int GAP = 3;
    private int sqrSide = 3;
    private int side = 9;
    General classic;

    ClassicFrame(){
        classic = new Classic();
        this.makeFrame();

    }

    private void makeFrame(){

        frame = new JFrame("Classic Sudoku");
        frame.setLayout(new BorderLayout());

        Container contentPane1 = frame.getContentPane();
        contentPane1.setBackground(Color.PINK);


        GridLayout board1 = new GridLayout(sqrSide, sqrSide, GAP, GAP);
        contentPane1.setLayout(board1);

        /* ************************* */

        JPanel[][] subpanels = new JPanel[sqrSide][sqrSide];


        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++){
                subpanels[i][j] = new JPanel(new GridLayout(sqrSide, sqrSide));
                contentPane1.add(subpanels[i][j]);
            }


        for (int i=0; i<side; i++) {
            int panelIcounter = i / sqrSide;
            for (int j = 0; j < side; j++) {
                int panelJcounter = j / sqrSide;
                board[i][j] = new JButton((1+i)+","+(j+1));

                subpanels[panelIcounter][panelJcounter].add(board[i][j]);
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
