import java.util.ArrayList;

public class General {

    public ArrayList checkValidMove(int[][] matrix, int len, int a, int b){
        ArrayList<Integer> moves;
        moves = new ArrayList<>();


        for(int i = 1; i<=len; i++){
            if (CheckSthlh(matrix, len, b, i))
                continue;
            if (CheckRaw(matrix, len, a, i))
                continue;
            if (squareint(matrix,len,a,b,i))
                continue;

            moves.add(i);
            }
     return moves;
    }

    private boolean CheckSthlh(int[][] matrix, int len, int b, int i){
        for( int j=0; j<len; j++){
            if (i==matrix[j][b]){
                return true;
            }
        }
        return false;
    }

    private boolean CheckRaw(int[][] matrix, int len, int a, int i){
        for( int j=0; j<len; j++){
            if (i==matrix[a][j]){
                return true;
            }
        }
        return false;
    }

    private boolean squareint (int[][] matrix, int len, int a, int b, int elemet){
        int RawBegin = (int)Math.round(a - a%Math.sqrt(len));
        int ColBegin = (int)Math.round(b - b%Math.sqrt(len));

        for (int i = RawBegin; i<Math.sqrt(len); i++){
            for (int j = ColBegin; j<Math.sqrt(len); j++){
                if (elemet == matrix[i][j])
                    return true;
            }

        }
        return false;

    }

    /**
     * Μετατροπέας ενός γράμματος σε αριθμό για κάθε κίνηση του χρήστη αν επιλέξει να παίξεθ wordoku.
     * ετσι ώστε να μπορούν να τρέξουν κανονικά οι λειτουργίες μου δουλεύουν με int.
     * @param element το γράμμα που επέλεξε ο χρήστης
     * @return η μετατροπή του
     */
    public int WorToSuElement(char element) {
        char[] a_i = "ABCDEFGHI".toCharArray();
        for (int i = 0; i < 9; i++) {
            if (a_i[i] == element) {
                return i+1;
            }
        }
        return 0;
    }
    /**
     * Καλείται απο την wordoku για κάθε ένα στοιχείο του πίνακα
     * @param element ο ακέραιος που θα μετατρέψουμε στο αντιστοιχο γράμμα
     * @return η αντιστοιχία του αριθμού
     */
    public char WordokuElement(int element){
        char[] a_i="ABCDEFGHI".toCharArray();
        return a_i[element-1];
    }

    /**
     *
     * @param Sudoku ο πίνακας με τους αριθμους στο άλυτο sudoku
     * @param len μεγεθος του πίνακα(4 ή 9)
     * @return ο νέος πίνακας με τα γράμματα
     */
    public char[][] Wordoku(int[][] Sudoku,int len){
        char[][] suToWo;
        suToWo=new char[len][len];
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(Sudoku[i][j]>0&&Sudoku[i][j]<10) {
                    suToWo[i][j] = WordokuElement(Sudoku[i][j]);
                }
            }
        }
        return suToWo;
    }
}
