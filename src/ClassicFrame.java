import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.IOException;

public class ClassicFrame extends GeneralFrame implements ActionListener, KeyListener {

    Classic classic;
    JButton check;
    private JLabel move = new JLabel();

    ClassicFrame() throws IOException {
        super(9, 3);
        frame.setTitle("Classic Sudoku");
        classic = new Classic();
        classic.files();
        this.makeFrame();


    }

    private void makeFrame(){

        check = new JButton("Check");
        mb.add(check);
        JLabel help = new JLabel("  For help hold 'H' ->  ");
        mb.add(help);
        mb.add(move);


        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                if (classic.getPuzzleToSolve()[i][j]!=0)
                    board[i][j].setText(Integer.toString(classic.getPuzzleToSolve()[i][j]));
            }


        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                board[i][j].addActionListener(this);
                board[i][j].setActionCommand(i+""+j);
            }
        check.addActionListener(this);

    }




    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
        String choose = e.getActionCommand();

        if (choose.equals("Check")){
            if (classic.EndOfGame()){
                JDialog d = new JDialog(frame,"WINNER WINNER CHICKEN DINNER");
                JLabel l = new JLabel("YOU WON !!!");
                d.add(l, BorderLayout.CENTER);
                d.setSize(400, 50);
                d.setLocationRelativeTo(null);
                d.setVisible(true);

                for (int i=0; i<9; i++)
                    for (int j=0; j<9; j++)
                        board[i][j].setBackground(Color.green);

            }
            else {
                JDialog d = new JDialog(frame,"LOSER LOSER CHICKEN DINNER ? ");
                JLabel l = new JLabel("TRY AGAIN !!!");
                d.add(l, BorderLayout.CENTER);
                d.setSize(400, 50);
                d.setLocationRelativeTo(null);
                d.setVisible(true);

                for (int i=0; i<9; i++)
                    for (int j=0; j<9; j++) {
                        if(classic.MoveChecker(i,j, classic.getPuzzleToSolve()[i][j]))
                            board[i][j].setBackground(Color.green);
                        else
                            board[i][j].setBackground(Color.red);
                    }
            }
        }

        a = Integer.parseInt(choose)/10;
        b = Integer.parseInt(choose)%10;
        board[a][b].addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent k) {
        int element = k.getKeyChar() - '0';
        if (element > 0 && element < 10){
            if( classic.checkValidMove(classic.getPuzzleToSolve(),9,a, b).contains(element) ) {
                classic.Move(a, b, element);
                board[a][b].setText(Integer.toString(element));
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent k) {
        if(k.getKeyChar() == 'h' || k.getKeyChar() == 'H'){
            if(move.getText().equals("")){
                for (int i = 0; i< classic.checkValidMove(classic.getPuzzleToSolve(),9,a, b).size() ; i++){
                    move.setText( move.getText() + " " + classic.checkValidMove(classic.getPuzzleToSolve(),9,a, b).get(i));
                }
            }

        }
    }
    @Override
    public void keyReleased(KeyEvent k) {
        move.setText("");
    }

}
