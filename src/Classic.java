import java.io.*;
import java.util.Random;

public class Classic extends Puzzle{

   public boolean locked[][];
    Classic() {
        super();
        locked=new boolean[9][9];

    }

    /**
     * Φόρτωση στοιχείων Puzzle από αρχείο κειμένου.
     * Εκχώρησή όλων των στοιχείων στον πινακα SolvedPuzzle
     * και μερικών(ή κανενός) στον Πίνακα PuzzleToSolve
     */
    public void files() throws IOException {
        BufferedReader puf = new BufferedReader(new FileReader("classic"));
        Random ono=new Random(System.currentTimeMillis());
        int c=-1;
       do {
            do {
                c = (ono.nextInt(180) / 18) * 18;
            } while (c == 0);
        }while(player.playedKiller(c));
        player.newDataClassic(c);
        for(int k=0;k<c;k++){
            puf.readLine();
        }
        for(int i=0;i<9;i++){
            String[] u=puf.readLine().trim().split(" ");
            for(int j=0;j<9;j++){
                SolvedPuzzle[i][j]=Integer.parseInt(u[j]);
            }
        }

        for(int i=0;i<9;i++){
            String[] u=puf.readLine().trim().split(" ");
            for(int j=0;j<9;j++){
                PuzzleToSolve[i][j]=Integer.parseInt(u[j]);
                if(PuzzleToSolve[i][j]==0){
                    locked[i][j]=false;
                }
                else{
                    locked[i][j]=true;
                }
            }
        }

        puf.close();
    }




}
