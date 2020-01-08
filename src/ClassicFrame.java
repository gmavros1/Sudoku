import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.IOException;

/**
 * Υλοποιείται το frame του κλασικού sudoku
 */
public class ClassicFrame extends GeneralFrame implements ActionListener, KeyListener {

    Classic classic; // αντικείμενο της λογικής του classic
    JButton check;  // κουμπί που χρησημοποιείται για τον έλεγχο των ορθών εισαγωγών στα κελιά
    private JLabel move = new JLabel(); // στο label αυτο θα αναγράφονται οι διαθέσιμες κινήσεις

    /**
     * Καλείται αρχικα με την super ο constractor της γενικής κλασης general περνώντας ως όρισμα τον αριθμο
     * των κελιών της μιας πλευράς του πίνακα του sudoku και την ρίζα αυτου
     * αποθηκεύται η επιλογή wordoku στην μεταβλήτη wordoku και το username του χρήστη στην μεταβλητή username
     * @param w επιλογη wordoku
     * @param u username χρήστη
     */
    ClassicFrame(boolean w, String u) throws IOException {
        super(9, 3);
        wordoku = w;
        frame.setTitle("Classic Sudoku");
        classic = new Classic();
        classic.files();
        username = u;
        this.makeFrame();

    }

    /**
     *Εισαγωγή στην βασικη μπάρα του κουμπιού check και την επιλογη της βοήθειας (label).
     * Σε καθε στοιχείο του board στο text εισάγωνται οι αριιθμοι του πίνακα PuzzleToSolve ή σε περίπτωση wordoku τα γράμματα
     * προστήθονται actionlisteners για κάθε κουμπί και ως action command η συντεταγμένη του ως στρινγκ.
     */
    private void makeFrame(){

        check = new JButton("Check");
        mb.add(check);
        JLabel help = new JLabel("  For help hold 'H' ->  ");
        mb.add(help);
        mb.add(move);

        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                if (classic.getPuzzleToSolve()[i][j]!=0)
                    //*****wordoku option*****//
                    if (wordoku){
                        board[i][j].setText( String.valueOf(classic.Wordoku(classic.getPuzzleToSolve(), 9)[i][j]));
                    }
                    //*****wordoku option*****//
                else
                    board[i][j].setText(Integer.toString(classic.getPuzzleToSolve()[i][j]));
            }

        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                board[i][j].addActionListener(this);
                board[i][j].setActionCommand(i+""+j);
            }
        check.addActionListener(this);

    }


    /**
     *σε περιπτωση check ελεχγεται η κατάσταση του παιχνιδιού από την EndOfGame()
     * αλλάζοντας κατάλληλα το χρώμα στα σωστα και λαθος κουτακια. Επειτα βγαζει και το καταλληλο μηνυμα
     *Έπειτα προσθέτουμε keylistener για τα στοιχεια του board επιλέγοντας το στοιχείο μς την βοήθεια του a b
     * a = το πρωτο ψηφιο του string choose
     * b = το δευτερο ψηφιο του string choose
     * ειναι ακεραιοι
     */
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
        String choose = e.getActionCommand(); // το action command που θα διαχειρηστουμε

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
                JLabel l = new JLabel("NEXT TIME ...");
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

    /**
     * Ανάλογα την περιπτωση wordoku ή οχι.
     *
     */
    @Override
    public void keyTyped(KeyEvent k) {
        //*****wordoku option*****//
        if (wordoku){
            if ( "ABCDEFGHI".contains(String.valueOf(k.getKeyChar())) ){
                if( classic.checkValidMove(classic.getPuzzleToSolve(),9,a, b).contains(classic.WorToSuElement(k.getKeyChar())) && !classic.locked[a][b] ){
                    classic.Move(a, b, classic.WorToSuElement(k.getKeyChar()));
                    board[a][b].setFont(new Font("Arial", Font.ITALIC, 14));
                    board[a][b].setText(String.valueOf(k.getKeyChar()));
                }
            }
        }
        //*****wordoku option*****//
        else {
            int element = k.getKeyChar() - '0';
            if (element > 0 && element < 10){
                if( classic.checkValidMove(classic.getPuzzleToSolve(),9,a, b).contains(element) && !classic.locked[a][b] ) {
                    classic.Move(a, b, element);
                    board[a][b].setFont(new Font("Arial", Font.ITALIC, 14));
                    board[a][b].setText(Integer.toString(element));
                }
            }

        }


    }

    @Override
    public void keyPressed(KeyEvent k) {
        if(k.getKeyChar() == 'h' || k.getKeyChar() == 'H'||k.getKeyChar()=='η'||k.getKeyChar()=='Η'){
            if(move.getText().equals("") && !classic.locked[a][b])   {
                for (int i = 0; i< classic.checkValidMove(classic.getPuzzleToSolve(),9,a, b).size() ; i++){
                    //*****wordoku option*****//
                    if (wordoku)
                        move.setText( move.getText() + " " + classic.WordokuElement(classic.checkValidMove(classic.getPuzzleToSolve(),9,a, b).get(i)) );
                    //*****wordoku option*****//
                    else
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
