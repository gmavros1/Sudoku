import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.IOException;
import java.util.Random;

/**
 * Υλοποιείται το frame του killer sudoku
 */
public class KillerFrame extends GeneralFrame implements ActionListener, KeyListener {

    Killer killer;
    JButton check;
    private JLabel move = new JLabel();

    /**
     * Καλείται αρχικα με την super ο constractor της γενικής κλασης general περνώντας ως όρισμα τον αριθμο
     * των κελιών της μιας πλευράς του πίνακα του sudoku και την ρίζα αυτου
     * αποθηκεύται η επιλογή wordoku στην μεταβλήτη wordoku και το username του χρήστη στην μεταβλητή username
     * @param w επιλογη wordoku
     * @param u username χρήστη
     */
    KillerFrame(boolean w, String u, String l, String c) throws IOException {
        super(9, 3, l, c);
        wordoku = w;
        frame.setTitle("Killer Sudoku");
        killer = new Killer();
        killer.files();
        username = u;
        this.makeFrame();
    }

    /**
     *Εισαγωγή στην βασικη μπάρα του κουμπιού check και την επιλογη της βοήθειας (label).
     * προστήθονται actionlisteners για κάθε κουμπί και ως action command η συντεταγμένη του ως στρινγκ.
     * Καλείται η συνάρτηση colornums.
     */
    private void makeFrame(){
        check = new JButton("Check");
        mb.add(check);
        JLabel help = new JLabel("  For help hold 'h' ->  ");
        mb.add(help);
        mb.add(move);


        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){
                board[i][j].addActionListener(this);
                board[i][j].setActionCommand(i+""+j);
            }
        check.addActionListener(this);

        this.colorsNsums();


    }

    /**
     * Η συναρτηση αυτή υπολογίζει και αναγράφει τα αθροίσματα των περιοχών του κιλλερ
     * αντιστοιχησμένα με ένα χρώμα.
     */
    private void colorsNsums(){

        boolean[][] haveBennEncountered = new boolean[9][9]; // τα κελία των οποίων οι αριθμοί έχουν καταμετρηθεί σε κάποιο αθροισμα

        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++)
                haveBennEncountered[i][j] =false;

        /*
        O πίνακας Color τοu killer περιέχει τον ίδιο αριθμο στα κελιά των στοιχείων που ανοίκουν
        στο ίδιο άθροισμα. Έτσι στη μεταβλητη match αποθηκεύουμε τον αριθμό color του στοιχειου που εξετάζουμε
        εάν δεν έχει καταμετρηθεί, και έπειτα συγκρίνουμε τους υπόλοιπους αριθμούς color των στοιχείων
        για να προσδιορίσουμε τις αθροιστηκες περιοχές και να υπολογίσουμε τα αθροισματα.
        **/
        int match;
        int sum;

        /*
        Στην colorSums θα αναγράφονται αριθμοί-αθροίσματα χρωματισμένα με το χρώμα της περιοχής του
        πίνακα του σουντόκου, στην οποία ανήκουν.
         */
        JMenuBar colorSums = new JMenuBar();
        contentPaneMain.add(colorSums, BorderLayout.PAGE_END);
        JLabel colors = new JLabel("Colors 'n Sums : ");
        colors.setForeground(Color.white);
        colorSums.add(colors);
        colorSums.setBackground(Color.black);


        /*
        Σε κάθε επανάλυψη αυτού του loop δημιουργείται ενα τυχαίο "ανοιχτό χρώμα" με το οποίο
        χρωματίζεται το backround των κουμπιών που βρίσκονται στην ίδια αθροιστική περιοχή.
        Υπολογιζονται τα αθροίσματα  και αναγράφονται στην κάτω μπαρα .
         */
        for (int i=0; i<side; i++)
            for (int j=0; j<side; j++){

                Color random ;
                sum = 0;
                if (!haveBennEncountered[i][j]){
                    match = killer.getColours()[i][j];

                    Random rand1 = new Random();
                    int ena = rand1.nextInt(151)+100; // από 100 μέχρι 250

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

    /**
     *Στην περίπτωση που ο χρήστης πατάει παρατεταμένα το κουμπι Η
     * εάν το label move είναι άδειο, μέσω της συνάρτησης checkvalid move επιστρέφονται οι διαθέσιμες
     * κινήσεις και αναγράφονται σε αυτό
     **/
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

    /**
     *Όταν απελευθερώνεται το κουμπί Η το label move γίνεται παλι κενό.
     */
    @Override
    public void keyReleased(KeyEvent k) {
        move.setText("");
    }

}
