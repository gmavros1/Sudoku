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
        Sums=new float[9][9];
        BufferedReader puf=new BufferedReader(new FileReader("killer"));
        Random ono=new Random(System.currentTimeMillis());
        int c;
       /* do{
            c=(ono.nextInt(63)/9)*9;
        }while(c==0);
        for(int k=0;k<c;k++){
            puf.readLine();
        }*/
        for(int i=0;i<9;i++){
            String[] u=puf.readLine().trim().split(" ");
            for(int j=0;j<9;j++){
                Sums[i][j]= Float.valueOf(u[j]);
                //System.out.println(Sums[i][j]);
            }
        }

        puf.close();

        conversion();
    }
    public void conversion(){
        //System.out.println("sssss");
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                SolvedPuzzle[i][j]=(int)Sums[i][j];
                Colours[i][j]=(int)(((Sums[i][j]-SolvedPuzzle[i][j])+ 0.0001)*100);
                /*String splitedString = String.valueOf(Sums[i][j]);
                int splitedNumber = splitedString.indexOf('.');
                SolvedPuzzle[i][j] = splitedNumber;
                Colours[i][j] = splitedNumber;*/
            }
        }
    }


    /*public void show(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
               System.out.println(SolvedPuzzle[i][j]+" "+Colours[i][j] + "    " + Sums[i][j]);
            }
        }
    }
    public static void main(String Args[]) throws IOException {
        Killer a;
        a=new Killer();
        a.files();
        a.conversion();
        a.show();
    }*/


}
