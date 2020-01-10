import i18n.Internationalization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.*;


/**
 * Δημιουργεία frame από το οποίο θα κλειρωνομούν οι κλάσεις frame των classic killer kai diudoku
 */
public class GeneralFrame implements ActionListener {

    protected Internationalization translate;
    private String lang;
    private String country;

    protected JFrame frame; //γενικό frame
    protected JButton[][] board; //δισδιαστατος πινακας κουμπιών που διαδραματίζει τον ρόλο των κελιών του sudoku
    protected int GAP; // Χρήσιμοποιείται στην διαμόρφωση κενών, διαχωρίζοντας έτσι τα υποτετράγωνα του πίνακα sudoku
    protected int sqrSide; // o αριθμός των κουμπιών των υποτετραγώνων του sudoku (δηλαδη η ρίζα του side)
    protected int side; // o αριθμός των κουμπιών της πλευράς του πίνακα του σουντόκου
    protected int a; // το a διατρέχει τις γραμμές του πίνακα board
    protected int b; // το a διατρέχει τις στειλες του πίνακα board
    protected JButton ng; // κουμπι για νεο παιχνιδι το οποίο πετάει τον χρήστη στο menu
    protected JMenuBar mb; //  μια μπαρα που περιεχει τα κουμπια new game και check αλλα και τις διαθεσιμες επιλογές του χρηστη
    protected Container contentPaneMain; // το container του frmae
    protected boolean wordoku; // επιλογή wordoku
    protected String username; // ονομα χρήστη


    /**
     *Το GΑP παίρνει την τιμη της sqr καθώς ανα τόσα τετράγωνα θα αφήνεται το απαιτούμενο κενό
     * Δημιουργεία του πινακα board σαν πινακα side * side
     * @param s τιμή που θέτουμε στην μεταβλητή side
     * @param sqr τιμή που θέτουμε στην μεταβλητή sqrSide
     */
    GeneralFrame(int s, int sqr, String l, String c){
        lang = l;
        country = c;
        translate = new Internationalization(lang, country);

        side = s;
        sqrSide = sqr;
        GAP = sqr;
        board = new JButton[side][side];
        this.makeFrame();

    }


    /**
     *Για την δημιουργεια του πινακα - πλεγματος :
     * Δημιουργώ τον Panel contentPane1 στο οποίο εισάγω με κατανομή πλεγματος τα στοιχεία του
     * πίνακα subpanles τύπου panel διαστασης sqrside * sqrside.
     * Έπeιτα σειρά έχει η προσθηκη των κουμπιών του board στα subpanels.
     * Aυτό γίνεται με την βοήθεια των μεταβλητών panelIcounter και panelJcounter.
     * Στο for loop που τρέχει τα i και j είναι οι γραμμές και στείλες του board
     * και οι δύο παραπάνω panelIcounter και panelJcounter μεταβλητές αφου διαιρεθούν ακέραια με τις i
     * kai j αντίστοιχα, μας δείχνουν σε ποιό στοιχείο του subpanel πρεπει να προσθεθούν τα στοιχεία του board.
     * Aυτό γινέται καθώς το στοιχείο του board [0][4] πρεπει να προσθεθεί στο subpanel[0][1]
     *
     * Αυτή η διαδικασία εγινε για να εχουμε ένα γκαπ στο τέλος κάθε subpanel
     */
    private void makeFrame(){

        frame = new JFrame();
        frame.setLayout(new BorderLayout());


        contentPaneMain = frame.getContentPane();
        contentPaneMain.setSize(700, 700);

        mb = new JMenuBar();
        ng = new JButton(translate.getTranslatedMessage( "New" ));
        ng.setActionCommand( "New game" );
        mb.add(ng);


        JPanel contentPane1 = new JPanel();
        contentPane1.setSize(700, 700);
        contentPane1.setBackground(Color.gray);
        contentPane1.setLayout(new GridLayout(sqrSide, sqrSide, GAP, GAP));


        contentPaneMain.add(mb, BorderLayout.PAGE_START);
        contentPaneMain.add(contentPane1, BorderLayout.CENTER);


        /* ************************* */

        JPanel[][] subpanels = new JPanel[sqrSide][sqrSide];


        for (int i=0; i<sqrSide; i++)
            for (int j=0; j<sqrSide; j++){
                subpanels[i][j] = new JPanel(new GridLayout(sqrSide, sqrSide));
                contentPane1.add(subpanels[i][j]);
            }


        for (int i=0; i<side; i++) {
            int panelIcounter = i / sqrSide;
            for (int j = 0; j < side; j++) {
                int panelJcounter = j / sqrSide;
                board[i][j] = new JButton();

                board[i][j].setBackground(Color.pink);
                board[i][j].setForeground(Color.black);
                subpanels[panelIcounter][panelJcounter].add(board[i][j]);
            }
        }

        ng.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Mε την επιλογή Νew Game καταστρεφεται το αντικείμενο του τρεχοντως παιχνιδιού
     * και ανοιγει το menuFrame
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        String choose = e.getActionCommand();
        if (choose.equals("New game")) {
            MenuFrame menu = new MenuFrame(wordoku, username, lang, country);
            frame.dispose();
        }
    }


}
