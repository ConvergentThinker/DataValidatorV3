package dynamicFields;

import java.awt.GridLayout;
import javax.swing.*;

public class SwingFoo {
    private static final int ROWS = 10;
    private static final int COLS = 16;

    private static void createAndShowGui() {

        JPanel bodyPanelMain = new JPanel();
        bodyPanelMain.setLayout(new GridLayout(1, 4, 10, 10));

        JTextArea one = new JTextArea("Hi", ROWS, COLS);
        one.setLineWrap(true);
        // one.setSize(100, 100);
        JTextArea two = new JTextArea("Goodbye", ROWS, COLS);
        two.setLineWrap(true);
        // two.setSize(100, 100);


        




        bodyPanelMain.add(new JScrollPane(one));
        bodyPanelMain.add(new JScrollPane(two));
        // bodyPanelMain.repaint();





        JFrame frame = new JFrame("SwingFoo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(bodyPanelMain);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}