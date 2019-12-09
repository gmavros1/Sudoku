


public class Puzzle {

    protected float SolvedPuzzle[][]; // matrix 9X9 with Puzzle elements
    protected float PuzzleToSolve[][]; // matrix 9X9 (empty or with some elements) (It depents from the subclass)


    public Puzzle() {

        SolvedPuzzle = new float[9][9];
        PuzzleToSolve = new float[9][9];

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
     * και επιστρέφει Τrue ή Flase
     *
     * @param a       γραμμή SolvedPuzzle
     * @param b       στήλη SolvedPuzzle
     * @param element επιλογή παίκτη για το κελί
     * @return True or false
     */
    public Boolean MoveChecker(int a, int b, float element) {
        if (SolvedPuzzle[a][b] == element)
            return true;
        return false;
    }


    /**
     * Eκχωρεί το στοιχείο element στην κατάλληλη θέση του πίνακα PuzzleToSolve
     * αφού καλέσει την συνάρτηση MoveChecker για να ελέχξει την αν η κίνηση ειναι έγκυρη.
     * Εάν όχι εκκτελεί τις κατάλληλες ενέργιες
     *
     * @param i γραμμή πίνακα
     * @param j στήλη πίνακα
     * @param element στοιχείο για τοποθέτηση απο παίκτη
     */
    public void Move(int i, int j, float element) {
        float wrongElement;//για να διαχωρίζεται η λάθος τοποθέτηση στοιχείου απο τον παίκτη
        if (MoveChecker(i, j, element))
            PuzzleToSolve[i][j] = element;
        wrongElement = (float) (element + 0.1);
        PuzzleToSolve[i][j]=wrongElement;


    }


}
