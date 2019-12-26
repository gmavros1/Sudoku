import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.Scanner;

public class ClassicFrame implements ActionListener  {

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
        contentPane1.setBackground(Color.gray);


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
                board[i][j] = new JButton();
                board[i][j].setBackground(Color.pink);
                board[i][j].setForeground(Color.black);
                subpanels[panelIcounter][panelJcounter].add(board[i][j]);
            }
        }


        for (int i=0; i<9; i++)
            for (int j=0; j<9; j++){
                board[i][j].addActionListener(this);
                board[i][j].setActionCommand(i+""+j);
            }


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        String choose = e.getActionCommand();
        int i = Integer.parseInt(choose);
        //System.out.println(choose);
        Scanner input = new Scanner(System.in);
        int keypressed;
        keypressed = input.nextInt();

        if (keypressed > 0 && keypressed < 10){

        }


    }


}
