import i18n.Internationalization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Αρχική κλάση γραφικών όπου θα επιλέγεται ένα απο
 * τα τρία είδη παζλ
 */
public class MenuFrame implements ActionListener {

    private Internationalization translate;
    String lang;
    String country;

    private JLabel languageLabel ;
    private JButton languageButton ;

    private JFrame frame;
    private JButton classic;
    private JButton killer;
    private JButton duidoku;
    private boolean wordoku;
    private String username;

    JDialog optionsD ; // παράθυρο που θα ανοιγει για να εισαχθεί επιλογή wordoku όνομα χρήστη και γλώσσα
    JLabel wrdku ; // ετικέτα για επιλογή wordoku
    JButton wurdokuButton = new JButton(); // το on-off του wordoku
    JLabel setUsername = new JLabel(); // σήμναση για εισαγωγή ονόματος χρήση στο διπλανό textfield
    JTextField writeUsername = new JTextField(15); // textfield για εισαγωγή ονόματος χρήστη

    private JButton scores; // κουμπί για την προβολή στατιστικών

    /**
     * Δημιουργεία βασικών στοιχείων του menu όταν ένα αντικείμενο της κλάσης καλείται για πρώτη φορά (χωρίς ορίσματα):
     * Δημιουργεία κουμπιών για τα τρία διαφορετικά είδη
     * Αρχικοποίηση κατάστασης wordoku ως ανενεργή
     * Αρχικοποίηση jLabel το οποίο προτρέπει στον χρήστη να εισάγει όνομα
     * Δημιουργεια κουμπιου στατιστικών
     * κλήση συνάρτησης για την δημιουργεια του frame και την εισαγωγή στοιχείων σε αυτό
     */
    public MenuFrame(){

        lang = "en";
        country = "UK";
        translate = new Internationalization(lang, country);

        classic = new JButton("Classic Sudoku");
        classic.setBackground(Color.pink);
        classic.setActionCommand( "Classic Sudoku" );

        killer = new JButton("Killer Sudoku");
        killer.setBackground(Color.gray);
        killer.setActionCommand( "Killer Sudoku" );

        duidoku = new JButton("Duidoku");
        duidoku.setBackground(Color.CYAN);

        wordoku = false;
        wurdokuButton.setText("OFF");

        assert false;
        setUsername.setText("Current username : " );

        scores = new JButton("Scores");
        scores.setActionCommand("Scores");

        this.makeFrame();
    }


    /**
     * εκτελεί της ίδες διαδικασίες με την MenuFrame() αλλά με τις παρακάτω διαφορές :
     * Ελέγχει αν ο χρήστης έχει επιλέξει την χρήση wordoku και θέτει την κατάλληλη τιμή στην boolean μεταβλητη
     * αλλα και στο label που σχετίζεται με αυτήν σύμφωνα με την τιμή του ορίσματος w
     * Παίρνει το username ως όρισμα και θέτει καταλλήλος το περιεχόμενο του setUsername
     * επίσης
     * @param w επιλογη wordoku
     * @param u username χρήστη
     */
    public MenuFrame(boolean w, String u, String l, String c){

        lang = l;
        country = c;
        translate = new Internationalization(lang, country);

        classic = new JButton(translate.getTranslatedMessage( "Classic" ));
        classic.setBackground(Color.pink);
        classic.setActionCommand( "Classic Sudoku" );

        killer = new JButton(translate.getTranslatedMessage( "Killer" ));
        killer.setBackground(Color.gray);
        killer.setActionCommand( "Killer Sudoku" );

        duidoku = new JButton("Duidoku");
        duidoku.setBackground(Color.CYAN);

        wordoku = w;
        if (w)
            wurdokuButton.setText("ON");
        else
            wurdokuButton.setText("OFF");

        username = u;

        assert false;
        setUsername.setText(translate.getTranslatedMessage( "Current_username" ) + username);

        scores = new JButton(translate.getTranslatedMessage( "Scores" ));
        scores.setActionCommand("Scores");

        this.makeFrame();
    }

    /**
     * Δημιουργεία βασικής μπάρας που περιέχει την επιλογή options και scores
     * Toποθέτηση κουμπιών για επιλογή pazl σε gridbag
     * προσθήκη actionlistener για τα κουμπια και διαμόρφωση παραθύρου
     */
    private void makeFrame(){
        frame = new JFrame("Sudoku");
        frame.setLayout(new BorderLayout());

        Container contentPane = frame.getContentPane();

        JButton options = new JButton(translate.getTranslatedMessage( "Options" ));
        options.setActionCommand( "Options" );

        JMenuBar mb = new JMenuBar();
        mb.add(options);
        mb.add(scores);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        buttons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        GridBagConstraints gc = new GridBagConstraints();

        //gc.anchor = GridBagConstraints.LINE_START;

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        buttons.add(classic, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 1;
        buttons.add(killer, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = -1;
        buttons.add(duidoku, gc);



        contentPane.add(mb, BorderLayout.PAGE_START);
        contentPane.add(buttons,BorderLayout.CENTER);


        classic.addActionListener(this);
        killer.addActionListener(this);
        duidoku.addActionListener(this);
        options.addActionListener(this);
        scores.addActionListener(this);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * Δημιουργεία παραθύρου για ρυθμίσεις
     * προσθήκη στοιχειων σε διάταξη gridBag
     */
    private void optionsDialog(){
        optionsD = new JDialog();
        wrdku = new JLabel();

        languageLabel = new JLabel(translate.getTranslatedMessage( "Language" ));
        languageButton = new JButton(country);
        languageButton.setActionCommand( "lB" );

        optionsD.setTitle("Options");
        optionsD.setSize(600, 200);
        optionsD.setLocationRelativeTo(null);

        wrdku.setText(" Wordoku Mode : ");
        wurdokuButton.setActionCommand("Wordoku");

        optionsD.setLayout( new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        //gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        optionsD.add(wrdku, gc);

        //gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 1;
        gc.gridy = 0;
        optionsD.add(wurdokuButton, gc);

        wurdokuButton.addActionListener(this); // προσθήκη listener για το κουμπι του wordoku

        gc.gridx = 0;
        gc.gridy = 1;
        optionsD.add(setUsername, gc);

        writeUsername.setSize(100, 10);
        gc.gridx = 1;
        gc.gridy = 1;
        optionsD.add(writeUsername, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        optionsD.add( languageLabel, gc );

        gc.gridx = 1;
        gc.gridy = 2;
        optionsD.add( languageButton, gc );

        languageButton.addActionListener( this );

        /*
         * Σε κάθε εισαγωγή ονόματος θα ανανεώνεται το label του username κατάλληλα
         * και θα εισάγεται το όνομα του χρήστη στην μεταβλητη String username
         **/
        writeUsername.addActionListener(new ActionListener() {
                                             @Override
                                             public void actionPerformed(ActionEvent actionEvent) {

                                                     username = writeUsername.getText();
                                                     if(username!=null){
                                                     setUsername.setText(translate.getTranslatedMessage( "Current_username" ) + " : "+ username);
                                                     writeUsername.setText("");

                                                 }

                                             }
                                         });

                optionsD.setVisible(true);

    }

    /**
     * παράθυρο το οποίο εμφανίζει τα στατισκτικά των παιχτών
     * προσθήκη στοιχειων σε διάταξη gridBag
     */
    public void scoresDialog(){
        JDialog scoresD = new JDialog();
        nickname name = new nickname(username);

        scoresD.setTitle(translate.getTranslatedMessage( "Scores" ));
        scoresD.setSize(600, 600);
        scoresD.setLocationRelativeTo(null);

        scoresD.setLayout( new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        JLabel usernAm = new JLabel();
        usernAm.setText(translate.getTranslatedMessage( "Username" ));

        JLabel wins = new JLabel();
        wins.setText(translate.getTranslatedMessage( "wins" ));

        JLabel looses = new JLabel();
        looses.setText(translate.getTranslatedMessage( "defeats" ));

        gc.gridx = 0;
        gc.gridy = 0;
        scoresD.add(usernAm, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        scoresD.add(wins, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        scoresD.add(looses, gc);

        /*
            εισαγωγή loop του array των ονομάτων στο οπoίο σε καθε σειρα στην
            διαταξη gridbag θα μπαινει ονομα νικες ητες
*/
        int count = 1;
        ArrayList<String> no;
        no=name.playersNames();
        for (String usernames : no){

            JLabel usernm = new JLabel();
            usernm.setText(usernames);

            JLabel winings = new JLabel();
            winings.setText(String.valueOf(name.giveScore(usernames)[0]));

            JLabel loosess = new JLabel();
            loosess.setText(String.valueOf(name.giveScore(usernames)[1]));

            /**/
            gc.gridx = 0;
            gc.gridy = count;
            scoresD.add(usernm, gc);

            gc.gridx = 1;
            gc.gridy = count;
            scoresD.add(winings, gc);

            gc.gridx = 2;
            gc.gridy = count;
            scoresD.add(loosess, gc);


            count+=1;

        }






        scoresD.setVisible(true);

    }

    /**
     *Με κάθε επιλογή τύπου παιχνιδιού διμιουργείται κατάλληλο αντικειμενο με ορίσματα wordoku kai username
     * καταστρέφεται το τρέχον frame
     * Mε την επιλογή Woedoku  ελέγχεται η τρεχουσα κατάσταση του label και αλλάζει κατάλληλα το ίδιο
     * αλλα και η αντιστοιχη boolean μεταβλητή
     */
    public void actionPerformed(ActionEvent e){
        String points = e.getActionCommand();
        switch (points) {
            case "Classic Sudoku":

                try {
                    ClassicFrame Classic = new ClassicFrame(wordoku, username, lang, country);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                break;
            case "Killer Sudoku":
                try {
                    KillerFrame killer = new KillerFrame(wordoku, username, lang, country);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                break;
            case "Duidoku":
                DuidokuFrame duidoku = new DuidokuFrame(wordoku, username, lang, country);
                frame.dispose();
                break;
            case "Options":
                this.optionsDialog();
                break;
            case "Wordoku":
                if (wurdokuButton.getText().equals("OFF")){
                    wordoku = true;
                    wurdokuButton.setText("ON");
                }
                else if (wurdokuButton.getText().equals("ON")){
                    wordoku = false;
                    wurdokuButton.setText("OFF");
                }
                break;
            case "Scores" :
                this.scoresDialog();
                break;
            case "lB":
                if(lang.equals( "en" )){
                    languageButton.setText( "GR" );
                    lang = "el";
                    country = "GR";
                    MenuFrame newframe = new MenuFrame( wordoku, username, lang, country );
                    frame.dispose();
                }
                else if (lang.equals( "el" )){
                    languageButton.setText( "EN" );
                    lang = "en";
                    country = "UK";
                    MenuFrame newframe = new MenuFrame( wordoku, username, lang, country );
                    frame.dispose();
                }
                break;
        }
    }

}
