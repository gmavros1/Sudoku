import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class ClassicFrame implements ActionListener, KeyListener {

    private JFrame frame;
    private JButton[][] board = new JButton [9][9];
    private int GAP = 3;
    private int sqrSide = 3;
    private int side = 9;
    private int a;
    private int b;
    JButton check;
    JButton ng;

    Classic classic;

    ClassicFrame(){
        classic = new Classic();
        this.makeFrame();

    }

    private void makeFrame(){

        frame = new JFrame("Classic Sudoku");
        frame.setLayout(new BorderLayout());


        /*Container contentPane1 = frame.getContentPane();
        contentPane1.setBackground(Color.gray);

        GridLayout board1 = new GridLayout(sqrSide, sqrSide, GAP, GAP);
        contentPane1.setLayout(board1);*/

        Container contentPaneMain = frame.getContentPane();
        contentPaneMain.setSize(700, 700);

        JMenuBar mb = new JMenuBar();
        check = new JButton("Check");
        ng = new JButton("New Game");
        mb.add(ng);
        mb.add(check);

        /*JPanel Des = new JPanel();
        Des.setLayout(new GridBagLayout());
        Des.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Des.setSize(600, 700);*/


        JPanel contentPane1 = new JPanel();
        contentPane1.setSize(700, 700);
        contentPane1.setBackground(Color.gray);
        contentPane1.setLayout(new GridLayout(sqrSide, sqrSide, GAP, GAP));


        contentPaneMain.add(mb, BorderLayout.PAGE_START);
        contentPaneMain.add(contentPane1, BorderLayout.CENTER);


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

                if (classic.getPuzzleToSolve()[i][j]!=0)
                    board[i][j].setText(Integer.toString(classic.getPuzzleToSolve()[i][j]));

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
        check.addActionListener(this);
        ng.addActionListener(this);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
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
        if (choose.equals("New Game")){
            MainFrame main = new MainFrame();
            frame.dispose();
        }

        a = Integer.parseInt(choose)/10;
        b = Integer.parseInt(choose)%10;
        board[a][b].addKeyListener(this);


    }

    @Override
    public void keyTyped(KeyEvent k) {
        //System.out.println(keyEvent);
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

    }

    @Override
    public void keyReleased(KeyEvent k) {

    }
}
