import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KillerFrame extends GeneralFrame {

    Killer killer;


    KillerFrame(){
        super(9, 3);
        frame.setTitle("Killer Sudoku");
        killer = new Killer();
        this.makeFrame();
    }

    private void makeFrame(){

    }
}
