import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.IOException;
import java.util.Random;

public class KillerFrame extends GeneralFrame implements ActionListener, KeyListener {

    Killer killer;
    JButton check;
    private JLabel move = new JLabel();
    private boolean wordoku;

    KillerFrame(boolean w) throws IOException {
        super(9, 3);
        wordoku = w;
        frame.setTitle("Killer Sudoku");
        killer = new Killer();
        killer.files();
        this.makeFrame();
    }

    private void makeFrame(){
        check = new JButton("Check");
        mb.add(check);
        JLabel help = new JLabel("  For help hold 'h' ->  ");
        mb.add(help);
        mb.add(move);

        /*for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                if (killer.getPuzzleToSolve()[i][j]!=0)
                    board[i][j].setText(Integer.toString(killer.getPuzzleToSolve()[i][j]));
            }*/

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

        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++)
                haveBennEncountered[i][j] =false;

        int match;
        int sum;

        JMenuBar colorSums = new JMenuBar();
        contentPaneMain.add(colorSums, BorderLayout.PAGE_END);
        JLabel colors = new JLabel("Colors 'n Sums : ");
        colors.setForeground(Color.white);
        colorSums.add(colors);
        colorSums.setBackground(Color.black);


        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){

                Color random ;
                sum = 0;
                if (!haveBennEncountered[i][j]){
                    match = killer.getColours()[i][j];

                    Random rand1 = new Random();
                    int ena = rand1.nextInt(151)+100;

                    Random rand2 = new Random();
                    int einai = rand2.nextInt(151)+100;

                    Random rand3 = new Random();
                    int taidoni = rand3.nextInt(151)+100;


                    random = new Color( ena, einai, taidoni);



                    for (int k = i; k<side; k++)
                        for (int n = j; n<side; n++){

                            if (killer.getColours()[k][n] == match && !haveBennEncountered[k][n] ){
                                haveBennEncountered[k][n] = true;
                                sum = sum + killer.getSolvedPuzzle()[k][n];
                                board[k][n].setBackground(random);

                            }

                        }

                    String ssum = ( "|sum="+ (sum) +"|");
                    board[i][j].setFont(new Font("Arial", Font.PLAIN, 9));
                    board[i][j].setText(ssum);

                    JLabel finale = new JLabel();
                    colorSums.add(finale);
                    finale.setForeground(random);
                    finale.setText(sum + "|");

                }

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
                JLabel l = new JLabel(" NEXT TIME  ...");
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
        //*****wordoku option*****//
        if (wordoku){
            if ( "ABCDEFGHI".contains(String.valueOf(k.getKeyChar())) ){
                if( killer.checkValidMove(killer.getPuzzleToSolve(),9,a, b).contains(killer.WorToSuElement(k.getKeyChar()))){
                    killer.Move(a, b, killer.WorToSuElement(k.getKeyChar()));
                    board[a][b].setFont(new Font("Arial", Font.BOLD, 13));
                    board[a][b].setText(String.valueOf(k.getKeyChar()));
                }
            }
        }
        //*****wordoku option*****//
        else {
            int element = k.getKeyChar() - '0';
            if (element > 0 && element < 10){
                if( killer.checkValidMove(killer.getPuzzleToSolve(),9,a, b).contains(element) ) {
                    killer.Move(a, b, element);
                    board[a][b].setFont(new Font("Arial", Font.BOLD, 13));
                    board[a][b].setText(Integer.toString(element));
                }

            }
        }

    }

    @Override
    public void keyPressed(KeyEvent k) {
        if(k.getKeyChar() == 'h' ){
            if(move.getText().equals("")){
                for (int i = 0; i< killer.checkValidMove(killer.getPuzzleToSolve(),9,a, b).size() ; i++){
                    //*****wordoku option*****//
                    if (wordoku)
                        move.setText( move.getText() + " " + killer.WordokuElement(killer.checkValidMove(killer.getPuzzleToSolve(),9,a, b).get(i)) );
                        //*****wordoku option*****//
                    else
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
