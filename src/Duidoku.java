import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Duidoku extends General {

    private int[][] DuiBoard;

    Duidoku(){

        DuiBoard = new int[4][4];
    }

    /**
     *  Η συναρτηση αυτή υλοποιεί την κίνηση του παίχτη.
     *  Καταχωρεί την επιλογή του στον πίνακα αφου ελεγξει την εγκυρώτητα της
     *  απο την συναρτηση ShowValidMoves της κλασης General.
     *  Επιστρέφει true ή false ανάλογα την εγκυρώτητα της κίνησης.
     */
    public boolean Move(int a, int b, int element){
        ArrayList<Integer> existance = super.checkValidMove(DuiBoard, 4, a, b);
        if (existance.contains(element)){
            DuiBoard[a][b] = element;
            return true;
        }
        return false;

    }


    /**
     * Υλοποιεί την κίνηση του Μηχανηματος, περανώντας τυχαία ορισματα
     * στις συνήθεις μεταβλητές. Ο έλεγχος της εγκυρώτητας, όπως και η
     * τελική καταχώρηση γίνεται μέσω της μεθόδου Μove.
     *
     */
    public void MchineMove(){
        int a;
        int b;
        int element;

        do {
            a = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            b = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            element = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            Move(a, b, element);
        }while (!Move(a, b, element));
    }


    /**
     * Ελέγχει όλες τις θέσεις και όλα τα στοιχεία και μας ενημερώνει εάν
     * δεν υπάρχει καμία εγκυρη κίνηση, διαφορετικά αν έχει τελείωσει το παιχνιδι.
     * Αυτό ιδανικά θα ελέγχεται μετά από κάθε κίνηση. Ο παίκτης του οποίου η κίνηση θα οδηγήσει
     * την συνάρτηση να γυρίσει true θα ειναι και ο νικητής
     * @return true εαν τελείωσε το παιχνιδι
     */
    public boolean NoAvailableMoves(){
        for (int i=0;i<4;i++)
            for (int j=0;j<4;j++)
                for (int element = 1; element<=4;element++){
                    if (Move(i, j, element))
                        return false;
                }
        return true;
    }
}
