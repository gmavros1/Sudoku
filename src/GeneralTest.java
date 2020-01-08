import jdk.jfr.StackTrace;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class GeneralTest {
    private General general;

    @Test
    public void testCheckValidMove(){
        general = new General();
        int[][] matrix=new int[2][2];
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++){
                matrix[i][j]=0;
            }
        }
        ArrayList<Integer> expMoves=new ArrayList<Integer>( );
        expMoves.add(1);
        expMoves.add(2);
        ArrayList<Integer> result = general.checkValidMove( matrix, 2, 1, 0 );
        assertEquals( expMoves,result );
    }
    @Test
    public void testWordokuElement(){
      general=new General();
      char expR='A';
      char result=general.WordokuElement(1);
      assertEquals( expR,result );
    }
    @Test
    public void testWordoku(){
        general=new General();
        int[][] matrix=new int[2][2];
        matrix[0][0]=1;
        matrix[0][1]=2;
        matrix[1][0]=2;
        matrix[1][1]=1;
        char[][] expMatrix=new char[2][2];
        expMatrix[0][0]='A';
        expMatrix[0][1]='B';
        expMatrix[1][0]='B';
        expMatrix[1][1]='A';
        char[][] result=general.Wordoku( matrix,2 );


    }



}
