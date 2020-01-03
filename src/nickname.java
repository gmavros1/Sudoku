import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class nickname {
    public String name;

    /**
     * Ο κατασκευαστής που χρησιμοποιείται όταν ο παίκτης δεν επιθυμεί να παίξει ανώνυμα.
     * @param n το όνομα του παίκτη
     */
    nickname(String n) {
        name = n;
    }

    /**
     * Ο κατασκευαστής που χρησιμοποιείται όταν ο παίκτης παίζει ανώνυμα
     */
    nickname(){
        name=null;
    }

    /**
     * Η συνάρτηση κάνει διάσχηση στο αρχείο ελέγχοντας αν το όνομα υπάρχει,δηλαδή αν ο παίκτης έχει ξαναπαίξει.Παραβλέπονται τρεις γράμμες
     * του αρχείου με την for δίοτι αντιστοιχούν στα στατιστικά του κάθε ονόματος ,άρα δεν εξυπηρετούν τον σκοπό της συνάρτησης.
     * @return ψευδές αν δεν βρέθηκε το όνομα,άρα δεν έχει ξαναπαίξει και αληθές αν έχει.
     * @throws IOException
     */
    public boolean hasPlayed() throws IOException {
        if(name!=null) {//περίπτωση που παίζει ανώνυμα
            BufferedReader puf = new BufferedReader(new FileReader("nicknames"));
            String sto;
            while ((sto = puf.readLine()) != null) {
                if (sto.equals(name)) return true;
                for (int i = 0; i < 3; i++) {
                    puf.readLine();
                }
            }
            puf.close();
            return false;
        } return  true;
    }

    /**
     * Η συνάρτηση ψάχνει αν ο παίκτης έχει παίξει το ταμπλο στην σειρά i.
     * Aρχικά ελέγχει αν ο παίκτης έχει ξαναπαίξει.
     * Αν ναι τότε διαβάζει το αρχείου ψάχνοντας το όνομα του
     * έτσι ώστε να διαβάσει δύο σειρές κάτω ,αφού εκεί βρίσκεται η σειρά με τα στατιστικά απο το classic.Κάνει δια-
     * σχιση στην σειρά και αν βρεθεί οτι έχει ξαναπαικτή επιστρέφει false.Αν δεν σταματήσει έκει τότε όλες οι υπόλοιπες
     * εκδοχές επιστρέφουν true αφού ο παίκτης είτε παίζει ανώνυμα είτε όχι μπορεί να παίξει το ταμπλό.
     * @param i ο αριθμός της σειράς του αρχείου όπου ξεκινάει το επιλεγμένο,τυχαια,απο την συνάρτηση classic sudoku
     *
     * @return false αν εχει παικτεί/true αν οχι
     * @throws IOException
     */
    public boolean playedClassic(int i) throws IOException {
        if (name != null) {//περίπτωση που παίζει ανώνυμα
            if (hasPlayed()) {
                BufferedReader puf = new BufferedReader(new FileReader("nicknames"));
                String sto;
                while ((sto = puf.readLine()) != null) {
                    if (sto.equals(name)) {
                        sto = puf.readLine();//σειρά για duidoku
                        String[] u = puf.readLine().trim().split(" ");
                        for (int j = 0; j < u.length; j++) {
                            if ((Integer.parseInt(u[j])) == i) {
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
        if(name!=null) {
            if (hasPlayed()) {
                BufferedReader puf = new BufferedReader(new FileReader("nicknames"));
                String sto;
                while ((sto = puf.readLine()) != null) {
                    if (sto.equals(name)) {
                        sto = puf.readLine();
                        sto = puf.readLine();
                        String[] u = puf.readLine().trim().split(" ");
                        for (int j = 0; j < u.length; j++) {
                            if ((Integer.parseInt(u[j])) == i) {
                                puf.close();
                                return false;
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
     * @param i
     * @throws IOException
     */
 public void newDataClassic(int i) throws IOException {
        if(name!=null) {
            if (hasPlayed()) {
                try {
                   BufferedReader puf = new BufferedReader(new FileReader("nicknames"));
                    StringBuffer inpuf = new StringBuffer();
                    String leaf;
                    while ((leaf = puf.readLine()) != null) {
                        if (leaf.equals(name)) {
                            inpuf.append(leaf);
                            inpuf.append('\n');
                            leaf = puf.readLine();
                            inpuf.append(leaf);
                            inpuf.append('\n');
                            leaf = puf.readLine();
                            inpuf.append(leaf+" "+i);
                            inpuf.append('\n');
                            leaf = puf.readLine();
                            inpuf.append(leaf);
                            inpuf.append('\n');
                        }
                        else{
                            inpuf.append(leaf);
                            inpuf.append('\n');
                        }
                    }

                    puf.close();
                    String po=inpuf.toString();

                    FileOutputStream popout = new FileOutputStream("nicknames");
                    popout.write(po.getBytes());
                    popout.close();

                } catch (Exception e) {
                    System.out.println("PROBLEM HERE");
                }

            } else {
                try {
                    FileWriter ftw = new FileWriter("classic", true);
                    ftw.write(name + "\n");
                    ftw.write(" \n");
                    ftw.write(i);
                    ftw.write(" \n");
                } catch (IOException ioe) {
                    System.err.println("IOException" + ioe.getMessage());

                }

            }
        }
 }
    public void newDataKiller(int i) throws IOException {
        if(name!=null) {
            if (hasPlayed()) {
                try {
                    BufferedReader puf = new BufferedReader(new FileReader("nicknames"));
                    StringBuffer inpuf = new StringBuffer();
                    String lpuf;
                    while ((lpuf = puf.readLine()) != null) {
                        if (lpuf.equals(name)) {
                            lpuf = puf.readLine();
                            lpuf = puf.readLine();
                            lpuf = puf.readLine();
                            lpuf = lpuf + " " + String.valueOf(i);
                            inpuf.append(lpuf);
                            inpuf.append('\n');
                        }
                    }
                    puf.close();
                    FileOutputStream pufout = new FileOutputStream("nicknames");
                    pufout.write(inpuf.toString().getBytes());
                    pufout.close();
                } catch (Exception e) {
                    System.out.println("PROBLEM HERE");
                }
            } else {
                try {
                    FileWriter ftw = new FileWriter("classic", true);
                    ftw.write(name + "\n");
                    ftw.write('\n');
                    ftw.write(" \n");
                    ftw.write(i + "\n");
                } catch (IOException ioe) {
                    System.err.println("IOException" + ioe.getMessage());

                }

            }
        }
    }
    public void newDataDui(int win,int lose) throws IOException {
        if (name != null) {
            if (hasPlayed()) {
                try {
                    int[] score = new int[2];
                    BufferedReader puf = new BufferedReader(new FileReader("nicknames"));
                    StringBuffer inpuf = new StringBuffer();
                    String lpuf;
                    while ((lpuf = puf.readLine()) != null) {
                        if (lpuf.equals(name)) {
                            String[] u = puf.readLine().trim().split(" ");
                            score[0] = Integer.parseInt(u[0]) + win;
                            score[1] = Integer.parseInt(u[1]) + lose;
                            lpuf = score[0] + " " + score[1];
                            inpuf.append(lpuf);
                            inpuf.append('\n');
                        }
                    }
                    puf.close();
                    FileOutputStream pufout = new FileOutputStream("nicknames");
                    pufout.write(inpuf.toString().getBytes());
                    pufout.close();
                } catch (Exception e) {
                    System.out.println("PROBLEM HERE");
                }
            } else {
                try {
                    FileWriter ftw = new FileWriter("nicknames", true);
                    ftw.write(name);
                    ftw.write(win);
                    ftw.write(lose + "\n");
                    ftw.write(" \n");
                    ftw.write(" \n");
                } catch (IOException ioe) {
                    System.err.println("IOException" + ioe.getMessage());

                }

            }
        }
    }



}
