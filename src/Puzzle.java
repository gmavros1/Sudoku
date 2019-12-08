

public class Puzzle {

    protected int SolvedPuzzle[][]; // matrix 9X9 with Puzzle elements
    protected int PuzzleToSolve[][]; // matrix 9X9 (empty or with some elements) (It depents from the subclass)


    public Puzzle(){

        SolvedPuzzle = new int [9][9];
        PuzzleToSolve = new int [9][9];

    }


    /**
     * Φόρτωση στοιχείων Puzzle από αρχείο κειμένου.
     * Εκχώρησή όλων των στοιχείων στον πινακα SolvedPuzzle
     * και μερικών(ή κανενός) στον Πίνακα PuzzleToSolve
     * @param file
     */
    protected void PuzzlesLoader(String file){



    }

    /**
     * Ελέγχει αν η σωστή κίνηση του χρήστη ειναι σωστή
     * και επιστρέφει Τrue ή Flase
     * @return True or false
     */
    public Boolean MoveChecker(){

        return true;
    }


    /**
     * Eκχωρεί το στοιχείο element στην κατάλληλη θέση του πίνακα PuzzleToSolve
     * αφού καλέσει την συνάρτηση MoveChecker για να ελέχξει την αν η κίνηση ειναι έγκυρη.
     * Εάν όχι εκκτελεί τις κατάλληλες ενέργιες
     * @param i
     * @param j
     * @param element
     */
    public void Move(int i, int j, int element){

    }


}
