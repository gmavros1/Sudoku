import java.util.concurrent.ThreadLocalRandom;

public class Duidoku extends General {

    private int[][] DuiBoard;

    Duidoku(){

        DuiBoard = new int[4][4];
    }

    /**
     * @return duiboard
     */
    public int[][] getDuiBoard(){
       return DuiBoard;
    }



    /**
     *  Η συναρτηση αυτή υλοποιεί την κίνηση του παίχτη.
     *  Καταχωρεί την επιλογή του στον πίνακα αφου ελεγξει την εγκυρώτητα της
     *  απο την συναρτηση ShowValidMoves της κλασης General.
     *  Επιστρέφει true ή false ανάλογα την εγκυρώτητα της κίνησης.
     */
    public void Move(int a, int b, int element){
        if (checkValidMove(DuiBoard, 4, a, b).contains(element))
            DuiBoard[a][b] = element;
    }


    /**
     * Υλοποιεί την κίνηση του Μηχανηματος, περνώντας τυχαία ορισματα
     * στις συνήθεις μεταβλητές. Ο έλεγχος της εγκυρώτητας, όπως και η
     * τελική καταχώρηση γίνεται μέσω της μεθόδου Μove.
     */
    public String MchineMove(){
        int a;
        int b;
        int element;
        String r;

        do {
            a = ThreadLocalRandom.current().nextInt(0, 4 );
            b = ThreadLocalRandom.current().nextInt(0, 4 );
            element = ThreadLocalRandom.current().nextInt(1, 5);
            r = (a) + Integer.toString(b);
        }while (!checkValidMove(DuiBoard, 4, a, b).contains(element));
        Move(a, b, element);
        return r;
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
                    if (checkValidMove(DuiBoard, 4, i, j).contains(element))
                        return false;
                }
        return true;
    }
}
