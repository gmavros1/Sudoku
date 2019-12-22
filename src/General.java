import java.util.ArrayList;

public class General {

    public void checkValidMove(int matrix[][], int len,  int a, int b){
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

    }

    private boolean CheckSthlh(int matrix[][],int len, int b, int i){
        for( int j=0; i<9; i++){
            if (i==matrix[j][b]){
                return true;
            }
        }
        return false;
    }

    private boolean CheckRaw(int matrix[][],int len, int a, int i){
        for( int j=0; i<9; i++){
            if (i==matrix[a][j]){
                return true;
            }
        }
        return false;
    }

    private boolean squareint (int matrix[][],int len, int a,int b, int elemet){
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


}
