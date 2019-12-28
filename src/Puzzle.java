import java.util.ArrayList;

public class Puzzle extends General{

    protected int[][] SolvedPuzzle; // matrix 9X9 with Puzzle elements
    protected int[][] PuzzleToSolve; // matrix 9X9 (empty or with some elements) (It depents from the subclass)



    public Puzzle() {

        SolvedPuzzle = new int[9][9];
        PuzzleToSolve = new int[9][9];

    }


    /**
     * getter για τον λυμενο
     * @return
     */
    public int[][] getSolvedPuzzle() {
        return SolvedPuzzle;
    }

    /**
     * getter για το τον πίνακα που φτιάχνουμε
     */
    public int[][] getPuzzleToSolve(){
        return PuzzleToSolve;
    }

    /**
     * Ελέγχει αν η κίνηση του χρήστη ειναι σωστή
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
        if (checkValidMove(PuzzleToSolve, 9, i, j).contains(element)){
            PuzzleToSolve[i][j] = element;
        };
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
