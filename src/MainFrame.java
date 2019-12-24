import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Αρχική κλάση γραφικών όπου θα επιλέγεται ένα απο
 * τα τρία είδη παζλ
 */
public class MainFrame implements ActionListener {


    private JFrame frame;
    private JButton classic;
    private JButton killer;
    private JButton duidoku;

    public MainFrame(){
            classic = new JButton("Classic Sudoku");
            killer = new JButton("Killer Sudoku");
            duidoku = new JButton("Duidoku");
            this.makeFrame();
    }

    private void makeFrame(){
        frame = new JFrame("Sudoku");
        frame.setLayout(new BorderLayout());

        Container contentPane = frame.getContentPane();

        JPanel buttons = new JPanel();
        buttons.add(classic);
        buttons.add(killer);
        buttons.add(duidoku);

        contentPane.add(buttons,BorderLayout.CENTER);

        classic.addActionListener(this);
        killer.addActionListener(this);
        duidoku.addActionListener(this);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        String points = e.getActionCommand();
        if (points.equals("Classic Sudoku"))
        {
            ClassicFrame Classic = new ClassicFrame();

        }
        else if (points.equals("Killer Sudoku"))
        {

        }
        else if (points.equals("Duidoku"))
        {

        }
    }

}
