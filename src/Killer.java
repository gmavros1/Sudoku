


public class Killer extends Puzzle {

    private int[][] Sums;


    Killer(){
        super();
        super.PuzzlesLoader("killer.txt");
        Sums = new int[9][9];

    }
}
