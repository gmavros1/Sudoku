import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Duidoku extends General {

    private int[][] DuiBoard;
    protected boolean[][] locked = new boolean[9][9]; //useless squares

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
        if (checkValidMove(DuiBoard, 4, a, b).contains(element) && !locked[a][b] ){
            DuiBoard[a][b] = element;
            locked[a][b] = true;
        }

    }


    /**
     * Υλοποιεί την κίνηση του Μηχανηματος, περνώντας τυχαία ορισματα
     * στις συνήθεις μεταβλητές. Ο έλεγχος της εγκυρώτητας, όπως και η
     * τελική καταχώρηση γίνεται μέσω της μεθόδου Μove.

    public String MchineMove(){
        int a;
        int b;
        int element = -1;
        String r;

        do {
            element +=1;
            a = ThreadLocalRandom.current().nextInt(0, 4 );
            b = ThreadLocalRandom.current().nextInt(0, 4 );
            element = (element + 1) % 5 == 0 ? 1 : (element + 1) % 5  ;
            r = (a) + Integer.toString(b);

        }while (!checkValidMove(DuiBoard, 4, a, b).contains(element) && locked[a][b] );
        Move(a, b, element);
        return r;
    }*/



    /**
     * Υλοποιεί την κίνηση του Μηχανηματος, περνώντας τυχαία ορισματα
     * στις συνήθεις μεταβλητές. Ο έλεγχος της εγκυρώτητας, όπως και η
     * τελική καταχώρηση γίνεται μέσω της μεθόδου Μove.
     */
    public String MchineMove(){
        String r;
        do {
            Random rand1 = new Random(System.currentTimeMillis());
            int element = rand1.nextInt(4)+1;
            for (int a = 0; a < 4 ; a++)
                for (int b = 3; b >= 0; b-- )
                    if (checkValidMove(DuiBoard, 4, a, b).contains(element) &&!locked[a][b]){
                        Move(a, b, element);
                        r = (a) + Integer.toString(b);
                        return r;
                    }

        }while (true);


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
                    if (checkValidMove(DuiBoard, 4, i, j).contains(element) && DuiBoard[i][j]==0)
                        return false;
                }
        return true;
    }
}
