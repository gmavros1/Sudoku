import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Υλοποιείται το frame του duidoku
 */
public class DuidokuFrame extends GeneralFrame implements ActionListener, KeyListener {

    Duidoku duidoku;
    nickname newData;
    private JLabel move = new JLabel();

    /**
     * Καλείται αρχικα με την super ο constractor της γενικής κλασης general περνώντας ως όρισμα τον αριθμο
     * των κελιών της μιας πλευράς του πίνακα του sudoku και την ρίζα αυτου
     * αποθηκεύται η επιλογή wordoku στην μεταβλήτη wordoku και το username του χρήστη στην μεταβλητή username
     * @param w επιλογη wordoku
     * @param u username χρήστη
     */
    DuidokuFrame(boolean w, String u, String l, String c){
        super(4, 2, l, c);
        wordoku = w;
        frame.setTitle("Duidoku");
        duidoku = new Duidoku();
        username = u;

        newData = new nickname( username );

        this.makeFrame();


    }

    /**
     *Εισαγωγή στην βασικη μπάρα της επιλογη της βοήθειας (label).
     * προστήθονται actionlisteners για κάθε κουμπί και ως action command η συντεταγμένη του ως στρινγκ.
     */
    private void makeFrame(){
        JLabel help = new JLabel(translate.getTranslatedMessage( "helpp" ));
        mb.add(help);
        mb.add(move);

        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                board[i][j].addActionListener(this);
                board[i][j].setActionCommand(i+""+j);
            }

    }

    /**
     *προσθέτουμε keylistener για τα στοιχεια του board επιλέγοντας το στοιχείο μe την βοήθεια του a b
     * a = το πρωτο ψηφιο του string choose
     * b = το δευτερο ψηφιο του string choose
     * ειναι ακεραιοι
     */
    public void actionPerformed(ActionEvent e){
        super.actionPerformed(e);
        String choose = e.getActionCommand();

        a = Integer.parseInt(choose)/10;
        b = Integer.parseInt(choose)%10;
        board[a][b].addKeyListener(this);

    }


    /**
     * Στο στοιχείο element αποθηκεύεται σε μορφή ακεραίου η επιλογή του παίχτη
     * και ελέγχεται η εκγυρότητα της κίνησης προκειμένου να συνεχιστεί η διαδικασία.
     * Στην συνέχεια δηλώνεται μια boolean μεταβλητή η οποία αρχικοποιήται σε λανθασμένη
     * και δείχνει αν έχει παιχτεί η τελευταία κίνηση του παιχνιδιού.
     *
     * Αμέσως μετά εκτελείται η κίνηση του χρήστη με κατάλληλο τρόπο αν ειναι wordoku ή normal
     * και καλείται η συνάρτηση lockelements για να αχρηστευτουν τα πεδια στα οποία δεν εισάγεται καμια κίνηση
     *
     * Μετά την κίνση του παίχτη, γινεται έλεχος με την συνάρτηση boolean NoAvailableMoves(), και
     * αν είναι αληθής. δεν υπαρχει διαθέσιμη κίνηση για το μηχάνημα αρα νικάει ο Χρηστης και εμφανίζεται το κα-
     * τάλληλο μήνυμα. Επίσης καλείται και η κατάλληλη συνάρτηση για την αλλαγή του σκορ του τρέχοντος χρήστη.
     * Τότε και η μεταβλητή flag γίνεται true
     *
     * To παιχνιδι συνεχίζεται απο το μηχάνημα εαν flag == flase
     *
     * Μέσω της συνάρτησης επιστρέφεται η θέση της κίνησης του υπολογιστη ως string, αφου εκτελεστεί, και έπειτα
     * αποθηκεύουμε τις συντεταγμενες στα a και b ως ακεραιους. Με την βοηθεια των a και b γραφουμε στην θέση
     * του board[a][b] το στοιχείο του DuiBoard[a][b].
     * Έπειτα γίνετια η ίδια διαδικασία για τον έλεγχο της νίκης.
     */
    @Override
    public void keyTyped(KeyEvent k)  {
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

                try {
                    newData.newDataDui(1, 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

                    try {
                        newData.newDataDui(0, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }


    /**
     * Λοκάρει τα κουτάκια στα οποία δεν μπορείς να κάνεις καμία έγκυρη κίνηση
     * O έλεγχος γίνεται μέσω της συνάρτησης checkValidMove
     * με τα παρακάτω εμφολευμενα for loops που τσεκάρουν τους αριθμους απο 1- side
     * αν μπορουνα να εισαχθούν σε όλλες τις πιθανές θέσεις
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

    /**
     *Στην περίπτωση που ο χρήστης πατάει παρατεταμένα το κουμπι Η
     * εάν το label move είναι άδειο, μέσω της συνάρτησης checkvalid move επιστρέφονται οι διαθέσιμες
     * κινήσεις και αναγράφονται σε αυτό
     **/
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

    /**
     *Όταν απελευθερώνεται το κουμπί Η το label move γίνεται παλι κενό.
     */
    @Override
    public void keyReleased(KeyEvent k) {
        move.setText("");
    }

}
