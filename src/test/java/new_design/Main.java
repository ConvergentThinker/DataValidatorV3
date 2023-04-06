package new_design;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Main extends JFrame {

    public Main() {
        JPanel simplePanel = new JPanel(new GridLayout(7, 1, 5, 5));

        simplePanel.setBorder(
                BorderFactory.createTitledBorder(BorderFactory
                .createLineBorder(Color.BLUE), "Title Line Border with color"));

        simplePanel.add(new JLabel("Examples"), JLabel.CENTER);


        add(simplePanel);
    }
    public static void main(String[] argv) {
        Main borderFactoryDemo = new Main();

        borderFactoryDemo.setVisible(true);
        borderFactoryDemo.pack();
    }
}