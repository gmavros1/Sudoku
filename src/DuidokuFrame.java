import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DuidokuFrame extends GeneralFrame implements ActionListener, KeyListener {

    Duidoku duidoku;
    private JLabel move = new JLabel();

    DuidokuFrame(boolean w, String u){
        super(4, 2);
        wordoku = w;
        frame.setTitle("Duidoku");
        duidoku = new Duidoku();
        username = u;
        this.makeFrame();


    }



    private void makeFrame(){
        JLabel help = new JLabel("  For help hold 'H' ->  ");
        mb.add(help);
        mb.add(move);

        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                board[i][j].addActionListener(this);
                board[i][j].setActionCommand(i+""+j);
            }

    }

    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
        String choose = e.getActionCommand();

        a = Integer.parseInt(choose)/10;
        b = Integer.parseInt(choose)%10;
        board[a][b].addKeyListener(this);

    }


    @Override
    public void keyTyped(KeyEvent k) {
        int element = k.getKeyChar() - '0';


        if ( (duidoku.checkValidMove(duidoku.getDuiBoard(), 4, a, b).contains(element)  || duidoku.checkValidMove(duidoku.getDuiBoard(), 4, a, b).contains(duidoku.WorToSuElement(k.getKeyChar()))) && !duidoku.locked[a][b] ){
            boolean flag = false; // wining situation flag

            //*****wordoku option*****//
            if (wordoku){
                duidoku.Move(a, b, duidoku.WorToSuElement(k.getKeyChar()));
                board[a][b].setText(String.valueOf(k.getKeyChar()));
            }
            //*****wordoku option*****//
            else {
                duidoku.Move(a, b, element);
                board[a][b].setText(Integer.toString(element));
            }
            lockeElements();

            //εαν δεν υπήρχαν διαθέσιμες κινήσεις για την μηχανί το παιχνίδι θα τελειωνε εδω
            if (duidoku.NoAvailableMoves()){
                JDialog d = new JDialog(frame,"WINNER WINNER CHICKEN DINNER");
                JLabel l = new JLabel("YOU WON !!!");
                d.add(l, BorderLayout.CENTER);
                d.setSize(400, 50);
                d.setLocationRelativeTo(null);
                d.setVisible(true);
                flag = true;
            }

            //για να αποφυγουμε την φαυλη επανάλυψη της μηχανής
            if (!flag){
                String choose = duidoku.MchineMove();
                a = Integer.parseInt(choose)/10;
                b = Integer.parseInt(choose)%10;
                //*****wordoku option*****//
                if (wordoku)
                    board[a][b].setText(String.valueOf(duidoku.WordokuElement(duidoku.getDuiBoard()[a][b])));
                //*****wordoku option*****//
                else
                    board[a][b].setText(Integer.toString(duidoku.getDuiBoard()[a][b]));
                lockeElements();

                if (duidoku.NoAvailableMoves() ){
                    JDialog d = new JDialog(frame,"LOSER LOSER CHICKEN DINNER ? ");
                    JLabel l = new JLabel(" NEXT TIME  ...");
                    d.add(l, BorderLayout.CENTER);
                    d.setSize(400, 50);
                    d.setLocationRelativeTo(null);
                    d.setVisible(true);
                }
            }

        }
    }


    /**
     * Λοκάρει τα κουτάκια στα οποία δεν μπορείς να κάνεις καμία έγκυρη κίνηση
     */
    private void lockeElements(){
        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                for (int c = 1; c<=9; c++){
                    if (duidoku.checkValidMove(duidoku.getDuiBoard(), 4, i, j).contains(c))
                        break;
                    if (c==9 && duidoku.getDuiBoard()[i][j]==0){
                        duidoku.locked[i][j]=true;
                        board[i][j].setBackground(Color.gray);
                    }

                }

            }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        if(k.getKeyChar() == 'h' || k.getKeyChar() == 'H'){
            if(move.getText().equals("") && duidoku.getDuiBoard()[a][b]==0 ){
                for (int i = 0; i< duidoku.checkValidMove(duidoku.getDuiBoard(),4,a, b).size() ; i++){
                    //*****wordoku option*****//
                    if (wordoku)
                        move.setText( move.getText() + " " + duidoku.WordokuElement(duidoku.checkValidMove(duidoku.getDuiBoard(),4,a, b).get(i)) );
                    //*****wordoku option*****//
                    else
                        move.setText( move.getText() + " " + duidoku.checkValidMove(duidoku.getDuiBoard(),4,a, b).get(i));
                }
            }

        }
    }
    @Override
    public void keyReleased(KeyEvent k) {
        move.setText("");
    }

}
