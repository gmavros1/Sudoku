import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DuidokuFrame extends GeneralFrame implements ActionListener, KeyListener {

    Duidoku duidoku;
    private JLabel move = new JLabel();

    DuidokuFrame(){
        super(4, 2);
        frame.setTitle("Duidoku");
        duidoku = new Duidoku();
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


        if (duidoku.checkValidMove(duidoku.getDuiBoard(), 4, a, b).contains(element) && duidoku.getDuiBoard()[a][b] == 0 ){
            boolean flag = false; // winner flag

            duidoku.Move(a, b, element);
            board[a][b].setText(Integer.toString(element));

            if (duidoku.NoAvailableMoves()){
                JDialog d = new JDialog(frame,"WINNER WINNER CHICKEN DINNER");
                JLabel l = new JLabel("YOU WON !!!");
                d.add(l, BorderLayout.CENTER);
                d.setSize(400, 50);
                d.setLocationRelativeTo(null);
                d.setVisible(true);
                flag = true;
            }

            String choose = duidoku.MchineMove();
            a = Integer.parseInt(choose)/10;
            b = Integer.parseInt(choose)%10;
            board[a][b].setText(Integer.toString(duidoku.getDuiBoard()[a][b]));

            if (duidoku.NoAvailableMoves() && !flag){
                JDialog d = new JDialog(frame,"LOSER LOSER CHICKEN DINNER ? ");
                JLabel l = new JLabel("TRY AGAIN !!!");
                d.add(l, BorderLayout.CENTER);
                d.setSize(400, 50);
                d.setLocationRelativeTo(null);
                d.setVisible(true);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        if(k.getKeyChar() == 'h' || k.getKeyChar() == 'H'){
            if(move.getText().equals("")){
                for (int i = 0; i< duidoku.checkValidMove(duidoku.getDuiBoard(),4,a, b).size() ; i++){
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
