import java.util.ArrayList;

public class General {

nickname player;
     General(String name){
    player=new nickname(name);
    }
    General(){
        player=new nickname(null);
    }
    /**
     * Η συνάστηση καταχωρεί στο Αrraylist moves τις διαθέσιμες κινήσεις στο κελι (a, b) ελέγχοντας,
     * όλους τους αριθμούς απο το 1 μεχρι το len, αν υπάρχουν σε γραμμή, στήλη ή τετράγωνο.
     * @param matrix ο πίνακας που λύνεται
     * @param len το μέγεθος του πίνακα
     * @param a η γραμμή
     * @param b η στήλη
     * @return To Αrraylist moves με τις διαθέσιμες κινήσεις
     */
    public ArrayList<Integer> checkValidMove(int[][] matrix, int len, int a, int b){
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

    /**
     * Ελέγχει εάν το στοιχείο i υπάρχει στην στήλη b
     * @param matrix ο πίνακας που λύνεται
     * @param len το μέγεθος του πίνακα
     * @param b η στήλη
     * @param i Ένα στοιχείο
     * @return true αν υπάρχει false αν δεν υπάρχει
     */
    private boolean CheckSthlh(int[][] matrix, int len, int b, int i){
        for( int j=0; j<len; j++){
            if (i==matrix[j][b]){
                return true;
            }
        }
        return false;
    }

    /**
     * Ελέγχει εάν το στοιχείο i υπάρχει στην γραμμή a
     * @param matrix ο πίνακας που λύνεται
     * @param len το μέγεθος του πίνακα
     * @param a η στήλη
     * @param i Ένα στοιχείο
     * @return true αν υπάρχει false αν δεν υπάρχει
     */
    private boolean CheckRaw(int[][] matrix, int len, int a, int i){
        for( int j=0; j<len; j++){
            if (i==matrix[a][j]){
                return true;
            }
        }
        return false;
    }


    /**
     * Ελέγχουμε την ύπαρξη του στοιχείου element στο υποτετράγωνο του πίνακα
     * στο οποίο βρίσκεται.
     * @param matrix ο πίνακας που λύνεται
     * @param len το μέγεθος του πίνακα
     * @param a η γραμμή
     * @param b η στήλη
     * @param elemet ένα στοιχείο
     * @return To Αrraylist moves με τις διαθέσιμες κινήσεις
     */
    private boolean squareint (int[][] matrix, int len, int a, int b, int elemet){
        int RawBegin = (int)Math.round(a - a%Math.sqrt(len)); //Η γραμμη που ξεκινάει το τετράγωνο αναλογα με την γραμμη που βρισκομαστε και το μεγεθος του πίνακα
        int ColBegin = (int)Math.round(b - b%Math.sqrt(len));//Η στηλη που ξεκινάει το τετράγωνο αναλογα με την στηλη που βρισκομαστε και το μεγεθος του πίνακα

        for (int i = RawBegin; i<Math.sqrt(len)+RawBegin; i++){
            for (int j = ColBegin; j<Math.sqrt(len)+ColBegin; j++){
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
