import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.*;

public class GeneralFrame implements ActionListener {
    Locale greek;
    ResourceBundle bundle;
    protected JFrame frame;
    protected JButton[][] board;
    protected int GAP;
    protected int sqrSide;
    protected int side;
    protected int a;
    protected int b;
    protected JButton ng;
    protected JMenuBar mb;
    protected Container contentPaneMain;
    protected boolean wordoku;
    protected String username;


    GeneralFrame(int s, int sqr){
        side = s;
        sqrSide = sqr;
        GAP = sqr;
        board = new JButton[side][side];
        this.makeFrame();
        greek=new Locale("el","GR");




    }



    private void makeFrame(){

        frame = new JFrame();
        frame.setLayout(new BorderLayout());


        contentPaneMain = frame.getContentPane();
        contentPaneMain.setSize(700, 700);

        mb = new JMenuBar();
        ng = new JButton("New Game");
        mb.add(ng);


        JPanel contentPane1 = new JPanel();
        contentPane1.setSize(700, 700);
        contentPane1.setBackground(Color.gray);
        contentPane1.setLayout(new GridLayout(sqrSide, sqrSide, GAP, GAP));


        contentPaneMain.add(mb, BorderLayout.PAGE_START);
        contentPaneMain.add(contentPane1, BorderLayout.CENTER);


        /* ************************* */

        JPanel[][] subpanels = new JPanel[sqrSide][sqrSide];


        for (int i=0; i<sqrSide; i++)
            for (int j=0; j<sqrSide; j++){
                subpanels[i][j] = new JPanel(new GridLayout(sqrSide, sqrSide));
                contentPane1.add(subpanels[i][j]);
            }


        for (int i=0; i<side; i++) {
            int panelIcounter = i / sqrSide;
            for (int j = 0; j < side; j++) {
                int panelJcounter = j / sqrSide;
                board[i][j] = new JButton();

                board[i][j].setBackground(Color.pink);
                board[i][j].setForeground(Color.black);
                subpanels[panelIcounter][panelJcounter].add(board[i][j]);
            }
        }

        ng.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String choose = e.getActionCommand();
        if (choose.equals("New Game")) {
            MenuFrame menu = new MenuFrame(wordoku, username);
            frame.dispose();
        }
    }


}
