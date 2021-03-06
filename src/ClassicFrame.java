import javax.swing.*;
import javax.swing.table.TableRowSorter;
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
    ClassicFrame(boolean w, String u, String l, String c) throws IOException {
        super(9, 3, l, c);
        wordoku = w;
        frame.setTitle(translate.getTranslatedMessage( "Classic" ));
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

        check = new JButton(translate.getTranslatedMessage( "Check" ));
        check.setActionCommand( "Check" );
        mb.add(check);
        JLabel help = new JLabel(translate.getTranslatedMessage( "helpp" ));
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
                JLabel l = new JLabel( translate.getTranslatedMessage( "youwon" ) );
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
                JLabel l = new JLabel(translate.getTranslatedMessage( "nextime" ));
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
     *  wordoku:
     *      Ελέγχει εάν ο χαρακτήρας που πατήθηκε εμπεριέχεται στην συμβολοσειρά ΑBCDEFGHI.
     *      Εάν ναι ελέγχει την εγκυρότητα της κίνησης μεσω συνάρτησης checkValidMove.
     *      Σε περίπτωση έγκυρης κίνησης εκτελείται η κίνηση από την λογική μέσω της συνάρτησης move
     *      και τέλος το τεxt του κουμπιου που επιλέχτηκε παίρνει τον αριθμό επιλογής του παίχτη.
     *  non-wordoku:
     *      μετατρέπεται ο χαρακτήρας σε αριθμός
     *      Εάν βρισκεται αναμεσα στο 1 και στο 9 :
     *      εκτέλειται η ίδια διαδικασία σε αντιστοιχη περιπτωση με το wordoku
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

    /**
     *Στην περίπτωση που ο χρήστης πατάει παρατεταμένα το κουμπι Η
     * εάν το label move είναι άδειο, μέσω της συνάρτησης checkvalid move επιστρέφονται οι διαθέσιμες
     * κινήσεις και αναγράφονται σε αυτό
     **/
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

    /**
     *Όταν απελευθερώνεται το κουμπί Η το label move γίνεται παλι κενό.
     */
    @Override
    public void keyReleased(KeyEvent k) {

        move.setText("");
    }

}
