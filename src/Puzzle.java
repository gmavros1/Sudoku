import java.util.ArrayList;

public class Puzzle extends General{

    protected int[][] SolvedPuzzle; // matrix 9X9 with Puzzle elements
    protected int[][] PuzzleToSolve; // matrix 9X9 (empty or with some elements) (It depents from the subclass)


    public Puzzle() {

        SolvedPuzzle = new int[9][9];
        PuzzleToSolve = new int[9][9];

    }


    /**
     * Φόρτωση στοιχείων Puzzle από αρχείο κειμένου.
     * Εκχώρησή όλων των στοιχείων στον πινακα SolvedPuzzle
     * και μερικών(ή κανενός) στον Πίνακα PuzzleToSolve
     *
     * @param file
     */
    protected void PuzzlesLoader(String file) {


    }

    /**
     * Ελέγχει αν η σωστή κίνηση του χρήστη ειναι σωστή
     * και επιστρέφει Τrue ή Flase.
     * Θα χρησιμοποιηθεί απο τα γραφικά
     *
     * @param a       γραμμή SolvedPuzzle
     * @param b       στήλη SolvedPuzzle
     * @param element επιλογή παίκτη για το κελί
     * @return True or false
     */
    public Boolean MoveChecker(int a, int b, int element) {
        if (SolvedPuzzle[a][b] == element)
            return true;
        return false;
    }


    /**
     * Eκχωρεί το στοιχείο element στην κατάλληλη θέση του πίνακα PuzzleToSolve
     * αφού καλέσει την συνάρτηση MoveChecker για να ελέχξει την αν η κίνηση ειναι σωστή.
     * Εάν δεν είναι σωστή καλείται η συνάρτηση checkValidMove που κληρωνομείται απο την General,και ελεγχεται αν η κινηση ειναι εγκυρη
     * συγκρίνοντας με την λίστα με τις έγκυρες κινήσεις.
     *
     * @param i γραμμή πίνακα
     * @param j στήλη πίνακα
     * @param element στοιχείο για τοποθέτηση απο παίκτη
     */
    public void Move(int i, int j, int element) {
        ArrayList validmove = new ArrayList<Integer>();
        if(MoveChecker(i,j,element)) {
            PuzzleToSolve[i][j] = element;
        }
        else{
           validmove=checkValidMove(PuzzleToSolve,9,i,j);
           for(int k=0;k<validmove.size();k++){
               if(validmove.get(k).equals(element)){
                   PuzzleToSolve[i][j]=element;
                   break;
               }
           }
        }
        System.out.println("Not valid move");

    }

    /**
     * @return Σε περίπτωση σωστά συμπληρωμένου sudoku επιστρεφει true, αλλιώς false
     */
    public boolean EndOfGame(){
        if (SolvedPuzzle.equals(PuzzleToSolve)){
            return true;
        }
        return false;
    }


}
