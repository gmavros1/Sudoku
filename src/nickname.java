import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.String;



public class nickname {
    public String name;

    /**
     * Ο κατασκευαστής που χρησιμοποιείται όταν ο παίκτης δεν επιθυμεί να παίξει ανώνυμα.
     *
     * @param n το όνομα του παίκτη
     */
    nickname(String n) {
        name = n;
    }

    /**
     * Ο κατασκευαστής που χρησιμοποιείται όταν ο παίκτης παίζει ανώνυμα
     */
    nickname() {
        name = null;
    }

    /**
     * Η συνάρτηση κάνει διάσχηση στο αρχείο ελέγχοντας αν το όνομα υπάρχει,δηλαδή αν ο παίκτης έχει ξαναπαίξει.Παραβλέπονται τρεις γράμμες
     * του αρχείου με την for δίοτι αντιστοιχούν στα στατιστικά του κάθε ονόματος ,άρα δεν εξυπηρετούν τον σκοπό της συνάρτησης.
     *
     * @return ψευδές αν δεν βρέθηκε το όνομα,άρα δεν έχει ξαναπαίξει και αληθές αν έχει.
     * @throws IOException
     */
    private boolean hasPlayed() throws IOException {
        if (name != null) {//περίπτωση που παίζει ανώνυμα
            BufferedReader puf = new BufferedReader( new FileReader( "nicknames" ) );
            String sto;
            while ((sto = puf.readLine()) != null) {
                if (sto.equals( name )) return true;
                for (int i = 0; i < 3; i++) {
                    puf.readLine();
                }
            }
            puf.close();
            return false;
        }
        return true;
    }

    /**
     * Η συνάρτηση ψάχνει αν ο παίκτης έχει παίξει το ταμπλο στην σειρά i.
     * Aρχικά ελέγχει αν ο παίκτης έχει ξαναπαίξει.
     * Αν ναι τότε διαβάζει το αρχείου ψάχνοντας το όνομα του
     * έτσι ώστε να διαβάσει δύο σειρές κάτω ,αφού εκεί βρίσκεται η σειρά με τα στατιστικά απο το classic.Κάνει δια-
     * σχιση στην σειρά και αν βρεθεί οτι έχει ξαναπαικτή επιστρέφει false.Αν δεν σταματήσει έκει τότε όλες οι υπόλοιπες
     * εκδοχές επιστρέφουν true αφού ο παίκτης είτε παίζει ανώνυμα είτε όχι μπορεί να παίξει το ταμπλό.
     *
     * @param i ο αριθμός της σειράς του αρχείου όπου ξεκινάει το επιλεγμένο,τυχαια,απο την συνάρτηση classic sudoku
     * @return false αν εχει παικτεί/true αν οχι
     * @throws IOException
     */
    public boolean playedClassic(int i) throws IOException {
        if (name != null) {//περίπτωση που παίζει ανώνυμα
            if (hasPlayed()) {
                BufferedReader puf = new BufferedReader( new FileReader( "nicknames" ) );
                String sto;
                while ((sto = puf.readLine()) != null) {
                    if (sto.equals( name )) {
                        System.out.println( sto);
                        sto = puf.readLine();//σειρά για duidoku
                        System.out.println( sto );
                        String[] u = puf.readLine().trim().split( " " );
                        for (int j = 0; j < u.length; j++) {
                            if ((Integer.parseInt( u[j] )) == i) {
                                puf.close();
                                return false;
                            }
                        }
                        sto = puf.readLine();
                    }
                    puf.close();
                }
            }
        }
        return true;
    }

    //Υλοποιεί τα ίδια με την playedClassic,όμως παραβλέπει μια σειρά παραπάνω αφού εκει βρίσκονται τα killer ταμπλο που έχουν παικτεί
    public boolean playedKiller(int i) throws IOException {
        if (name != null) {
            if (hasPlayed()) {
                BufferedReader puf = new BufferedReader( new FileReader( "nicknames" ) );
                String sto;
                while ((sto = puf.readLine()) != null) {
                    if (sto.equals( name )) {
                        sto = puf.readLine();
                        sto = puf.readLine();
                        String[] u = puf.readLine().trim().split( " " );
                            for (int j = 0; j < u.length; j++) {
                                if(!(u[j].equals( "" ))) {
                                    if ((Integer.parseInt( u[j] )) == i) {
                                        puf.close();
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                puf.close();
            }

            }

        return true;
    }

    /**
     * Η συνάρτηση προσθέτει στο αρχείο το νέο ταμπλό που παίκτηκε απο τον χρήστη.Διασχίζουμε το αρχείο και όταν
     * βρεθούν τα στοιχεία του παίκτη στην αλλάζουμε την σειρά με μία νέα που έχει στο τέλος της τον αριθμό της σειράς
     * που ξεκινάει το ταμπλό που μόλις παίκτηκε.
     *
     * @param i
     * @throws IOException
     */
    public void newDataClassic(int i) throws IOException {
        System.out.println( "onooooo" );
        if (name != null) {
            if (hasPlayed()) {
                try {
                    BufferedReader puf = new BufferedReader( new FileReader( "nicknames" ) );
                    StringBuffer inpuf = new StringBuffer();
                    String leaf;
                    while ((leaf = puf.readLine()) != null) {
                        if (leaf.equals( name )) {
                            System.out.println( name );
                            inpuf.append( leaf );//όνομα
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//dui
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            if(!(leaf.equals(""))) {
                                inpuf.append( leaf ).append( " " ).append( i );//νέα δεδομένα για classic
                                inpuf.append( '\n' );
                            }else{
                                inpuf.append( i );//νέα δεδομένα για classic
                                inpuf.append( '\n' );
                            }
                            leaf = puf.readLine();
                            inpuf.append( leaf );//δεδομένα killer
                            inpuf.append( '\n' );
                        } else {
                            inpuf.append( leaf );//όνομα
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//dui
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//classic
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//killer
                            inpuf.append( '\n' );

                        }
                    }

                    puf.close();
                    String po = inpuf.toString();
                    System.out.println( po+ "ooooo" );
                    FileOutputStream popout = new FileOutputStream( "nicknames" );
                    popout.write( po.getBytes() );
                    popout.close();

                } catch (Exception e) {
                    System.out.println( "PROBLEM HERE" );
                }

            } else {
                try {
                    FileWriter ftw = new FileWriter( "nicknames", true );
                    ftw.append(name);
                    ftw.append("\n");
                    ftw.append( "0 0\n" );
                    String iStr=String.valueOf( i );
                    ftw.append(iStr);
                    ftw.append( " \n" );
                    ftw.flush();
                    ftw.close();
                } catch (IOException ioe) {
                    System.err.println( "IOException" + ioe.getMessage() );

                }

            }
        }
    }

    //Ιδια συνάρτηση με την παραπάνω,όμως αλλάζει η μια σειρά οπου ανανεώνουμε.
    public void newDataKiller(int i) throws IOException {
        if (name != null) {
            if (hasPlayed()) {
                try {
                    BufferedReader puf = new BufferedReader( new FileReader( "nicknames" ) );
                    StringBuffer inpuf = new StringBuffer();
                    String leaf;
                    while ((leaf = puf.readLine()) != null) {
                        if (leaf.equals(name)) {
                            System.out.println( name );
                            inpuf.append( leaf );//όνομα
                            System.out.println( leaf );
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//duidoku
                            System.out.println( leaf );
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//classic
                            System.out.println( leaf );
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            if(!(leaf.equals(""))) {
                                inpuf.append(leaf).append( " " ).append( i );//killer
                                System.out.println( "heeeeeeew"+i );
                            }else {
                                inpuf.append( i );
                                System.out.println( "hw"+i );
                            }
                            inpuf.append( '\n' );
                        }else{
                            System.out.println( name );
                            inpuf.append( leaf );//όνομα
                            System.out.println( leaf );
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//duidoku
                            System.out.println( leaf );
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//classic
                            System.out.println( leaf );
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//killer
                            inpuf.append( '\n' );
                        }
                    }
                    puf.close();
                    String po = inpuf.toString();
                    System.out.println( po+ "oooooo" );
                    FileOutputStream popout = new FileOutputStream( "nicknames" );
                    popout.write( po.getBytes() );
                    popout.close();
                } catch (Exception e) {
                    System.out.println( "PROBLEM HERE" );
                }
            } else {
                try {
                    FileWriter ftw = new FileWriter( "nicknames", true );
                    ftw.append(name);
                    ftw.append("\n");
                    ftw.append( "0 0\n" );
                    ftw.append( " \n" );
                    String iStr=String.valueOf( i );
                    ftw.append(iStr);
                    ftw.append( " \n" );
                    ftw.flush();
                    ftw.close();
                } catch (IOException ioe) {
                    System.err.println( "IOException" + ioe.getMessage() );

                }

            }
        }
    }


    /**
     * Δέχετε (1,0) για νίκη του παίκτη ή (0,1) για ήττα.
     *
     * @param win  1 αν ο παίκτης νίκησε 0 αν εχασε
     * @param lose 1 αν νικησε ,0 αν έχασε
     * @throws IOException
     */
    public void newDataDui(int win, int lose) throws IOException {
        if (name != null) {
            if (hasPlayed()) {
                try {
                    int[] score = new int[2];
                    BufferedReader puf = new BufferedReader( new FileReader( "nicknames" ) );
                    StringBuffer inpuf = new StringBuffer();
                    String leaf;
                    while ((leaf = puf.readLine()) != null) {
                        if (leaf.equals( name )) {
                            inpuf.append( name );
                            inpuf.append( '\n' );
                            String[] u = puf.readLine().trim().split( " " );
                            score[0] = Integer.parseInt( u[0] ) + win;
                            score[1] = Integer.parseInt( u[1] ) + lose;
                            inpuf.append( score[0] ).append( " " ).append( score[1] );//Δεδομένα duidoku
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//δεδομένα classic
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//δεδομένα killer
                            inpuf.append( '\n' );
                        }
                        else{
                            System.out.println( name );
                            inpuf.append( leaf );//όνομα
                            System.out.println( leaf );
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//duidoku
                            System.out.println( leaf );
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//classic
                            System.out.println( leaf );
                            inpuf.append( '\n' );
                            leaf = puf.readLine();
                            inpuf.append( leaf );//killer
                            inpuf.append( '\n' );
                        }
                    }
                    puf.close();
                    FileOutputStream pufout = new FileOutputStream( "nicknames" );
                    pufout.write( inpuf.toString().getBytes() );
                    pufout.close();
                } catch (Exception e) {
                    System.out.println( "PROBLEM HERE" );
                }
            } else {
                try {
                    FileWriter ftw = new FileWriter( "nicknames", true );
                    ftw.append( name );
                    String lwStr=String.valueOf( win );
                    ftw.append(lwStr);
                    lwStr=String.valueOf(lose);
                    ftw.append( lwStr + "\n" );
                    ftw.append( " \n" );
                    ftw.append( " \n" );
                } catch (IOException ioe) {
                    System.err.println( "IOException" + ioe.getMessage() );

                }

            }
        }
    }

    public int[] giveScore(String name) {
        int[] score = new int[2];
        try {
            BufferedReader puf = new BufferedReader( new FileReader( "nicknames" ) );
            String leaf;
            while ((leaf = puf.readLine()) != null) {
                if (leaf.equals( name )) {
                    String[] u = puf.readLine().trim().split( " " );
                    score[0] = Integer.parseInt( u[0] );
                    score[1] = Integer.parseInt( u[1] );
                    break;
                }
            }
            puf.close();
        } catch (Exception e) {
            System.out.println( "PROBLEM HERE" );
        }
        return score;
    }

    public ArrayList<String> playersNames() {
        ArrayList<String> nam = new ArrayList<String>();
        try {
            BufferedReader puf = new BufferedReader( new FileReader( "nicknames" ) );
            String leaf;
            while ((leaf = puf.readLine())  != null) {
                nam.add(leaf);
                puf.readLine();
                puf.readLine();
                puf.readLine();
            }
        puf.close();
        } catch(Exception e){
            System.out.println( "PROBLEM HERE" );
        }
        return nam;

    }






}
