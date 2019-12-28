import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.IOException;

public class KillerFrame extends GeneralFrame implements ActionListener, KeyListener {

    Killer killer;
    JButton check;
    private JLabel move = new JLabel();

    KillerFrame() {
        super(9, 3);
        frame.setTitle("Killer Sudoku");
        killer = new Killer();
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
                if (killer.getPuzzleToSolve()[i][j]!=0)
                    board[i][j].setText(Integer.toString(killer.getPuzzleToSolve()[i][j]));
            }

        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                board[i][j].addActionListener(this);
                board[i][j].setActionCommand(i+""+j);
            }
        check.addActionListener(this);

         this.colorsNsums();

    }

    private void colorsNsums(){

        boolean[][] haveBennEncountered = new boolean[9][9];

        int match;
        int sum;

        JMenuBar colorSums = new JMenuBar();
        contentPaneMain.add(colorSums, BorderLayout.PAGE_END);
        JLabel colors = new JLabel("Colors 'n Sums : ");
        colorSums.add(colors);

        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){

                sum = 0;
                match = killer.getColours()[i][j];

                for (int k = i; k<side; k++)
                    for (int n = j; n<side; n++){

                        if (killer.getColours()[k][n] == match && !haveBennEncountered[k][n] ){
                            haveBennEncountered[k][n] = true;
                            sum += killer.getSolvedPuzzle()[k][n];
                        }

                    }

                JLabel finale = new JLabel();
                colorSums.add(finale);
                finale.setText("   " + sum + "   ");
                finale.setBackground(new Color(20+5*sum, 0, 20 + i*j, 0 ));
            }

    }


    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
        String choose = e.getActionCommand();

        if (choose.equals("Check")){
            if (killer.EndOfGame()){
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
                        if(killer.MoveChecker(i,j, killer.getPuzzleToSolve()[i][j]))
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
            if( killer.checkValidMove(killer.getPuzzleToSolve(),9,a, b).contains(element) ) {
                killer.Move(a, b, element);
                board[a][b].setText(Integer.toString(element));
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent k) {
        if(k.getKeyChar() == 'h' || k.getKeyChar() == 'H'){
            if(move.getText().equals("")){
                for (int i = 0; i< killer.checkValidMove(killer.getPuzzleToSolve(),9,a, b).size() ; i++){
                    move.setText( move.getText() + " " + killer.checkValidMove(killer.getPuzzleToSolve(),9,a, b).get(i));
                }
            }

        }
    }
    @Override
    public void keyReleased(KeyEvent k) {
        move.setText("");
    }

}
