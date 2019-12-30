import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Αρχική κλάση γραφικών όπου θα επιλέγεται ένα απο
 * τα τρία είδη παζλ
 */
public class MenuFrame implements ActionListener {


    private JFrame frame;
    private JButton classic;
    private JButton killer;
    private JButton duidoku;
    private boolean wordoku;

    JDialog optionsD ;
    JLabel wrdku ;
    JButton wurdokuButton ;


    public MenuFrame(){
            classic = new JButton("Classic Sudoku");
            classic.setBackground(Color.pink);

            killer = new JButton("Killer Sudoku");
            killer.setBackground(Color.gray);

            duidoku = new JButton("Duidoku");
            duidoku.setBackground(Color.CYAN);

            wordoku = false;

            this.makeFrame();
    }

    private void makeFrame(){
        frame = new JFrame("Sudoku");
        frame.setLayout(new BorderLayout());

        Container contentPane = frame.getContentPane();

        JButton options = new JButton("Options");

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
        options.addActionListener(this);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void optionsDialog(){
        optionsD = new JDialog();
        wrdku = new JLabel();
        wurdokuButton = new JButton("OFF");

        optionsD.setTitle("Options");
        optionsD.setSize(600, 200);
        optionsD.setLocationRelativeTo(null);

        wrdku.setText(" Wordoku Mode : ");
        wurdokuButton.setActionCommand("Wordoku");

        optionsD.setLayout( new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        //gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        optionsD.add(wrdku, gc);

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        //gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 1;
        gc.gridy = 0;
        optionsD.add(wurdokuButton, gc);

        wurdokuButton.addActionListener(this);

        optionsD.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        String points = e.getActionCommand();
        switch (points) {
            case "Classic Sudoku":

                try {
                    ClassicFrame Classic = new ClassicFrame(wordoku);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                break;
            case "Killer Sudoku":
                try {
                    KillerFrame killer = new KillerFrame(wordoku);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                break;
            case "Duidoku":
                DuidokuFrame duidoku = new DuidokuFrame(wordoku);
                frame.dispose();
                break;
            case "Options":
                this.optionsDialog();
                break;
            case "Wordoku":
                if (!wordoku){
                    wordoku = true;
                    wurdokuButton.setText("ON");
                }
                else{
                    wordoku = false;
                    wurdokuButton.setText("OFF");
                }
        }
    }

}
