import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Killer extends Puzzle {

    private float[][] Sums;
    private int[][] Colours;


    Killer(){
        super();
        Sums=new float[9][9];
        Colours=new int[9][9];
    }


    /**
     * getter gia
     * @return Colors
     */
    public int[][] getColours() {
        return Colours;
    }

    /**
     * Φόρτωση στοιχείων Puzzle από αρχείο κειμένου.
     * Εκχώρησή όλων των στοιχείων στον πινακα SolvedPuzzle
     * και μερικών(ή κανενός) στον Πίνακα PuzzleToSolve
     */
    public void files() throws IOException {
        float[][] Sums=new float[9][9];
        BufferedReader puf=new BufferedReader(new FileReader("Killer.txt"));
        Random ono=new Random(System.currentTimeMillis());
        int c;
        do{
            c=(ono.nextInt(90)/9)*9;
        }while(c==0);
        for(int k=0;k<c;k++){
            puf.readLine();
        }
        for(int i=0;i<9;i++){
            String[] u=puf.readLine().trim().split(" ");
            for(int j=0;j<9;j++){
                Sums[i][j]= Float.valueOf(u[i]).floatValue();
            }
        }

        puf.close();
    }
    public void conversion(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                SolvedPuzzle[i][j]=(int)Sums[i][j];
                Colours[i][j]=(int)((Sums[i][j]-SolvedPuzzle[i][j])*10);
            }
        }
    }



}
