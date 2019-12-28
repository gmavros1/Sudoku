import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Classic extends Puzzle{

    Classic(){
        super();

    }
    public void files() throws IOException {
        BufferedReader puf=new BufferedReader(new FileReader("Classic.txt"));
        Random ono=new Random(System.currentTimeMillis());
        int c;
        do{
            c=(ono.nextInt(60)/6)*6;
        }while(c==0);
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
            }
        }
        puf.close();
    }



}
