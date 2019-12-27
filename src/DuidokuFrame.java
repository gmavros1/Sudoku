import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DuidokuFrame extends GeneralFrame{

    Duidoku duidoku;


    DuidokuFrame(){
        super(4, 2);
        frame.setTitle("Duidoku");
        duidoku = new Duidoku();
        this.makeFrame();
    }

    private void makeFrame(){

    }
}
