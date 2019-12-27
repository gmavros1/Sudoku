import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Αρχική κλάση γραφικών όπου θα επιλέγεται ένα απο
 * τα τρία είδη παζλ
 */
public class MenuFrame implements ActionListener {


    private JFrame frame;
    private JButton classic;
    private JButton killer;
    private JButton duidoku;
    private JButton options;

    public MenuFrame(){
            classic = new JButton("Classic Sudoku");
            classic.setBackground(Color.pink);

            killer = new JButton("Killer Sudoku");
            killer.setBackground(Color.gray);

            duidoku = new JButton("Duidoku");
            duidoku.setBackground(Color.CYAN);

            this.makeFrame();
    }

    private void makeFrame(){
        frame = new JFrame("Sudoku");
        frame.setLayout(new BorderLayout());

        Container contentPane = frame.getContentPane();

        options = new JButton("Options");

        JMenuBar mb = new JMenuBar();
        mb.add(options);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        buttons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        GridBagConstraints gc = new GridBagConstraints();

        //gc.anchor = GridBagConstraints.LINE_START;

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        buttons.add(classic, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 1;
        buttons.add(killer, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = -1;
        buttons.add(duidoku, gc);



        contentPane.add(mb, BorderLayout.PAGE_START);
        contentPane.add(buttons,BorderLayout.CENTER);


        classic.addActionListener(this);
        killer.addActionListener(this);
        duidoku.addActionListener(this);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        String points = e.getActionCommand();
        switch (points) {
            case "Classic Sudoku":

                ClassicFrame Classic = new ClassicFrame();
                frame.dispose();
                break;
            case "Killer Sudoku":
                KillerFrame killer = new KillerFrame();
                frame.dispose();
                break;
            case "Duidoku":
                DuidokuFrame duidoku = new DuidokuFrame();
                frame.dispose();
                break;
        }
    }

}
