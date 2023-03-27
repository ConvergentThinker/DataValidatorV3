package org.main;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class GridBagEqualWeightsDemo {
    static void showWindow() {
        // Source: https://www.gutenberg.org/files/98/98-h/98-h.htm
        String text =
                "It was the best of times, it was the worst of times, it was"
                        + " the age of wisdom, it was the age of foolishness, it was the"
                        + " epoch of belief, it was the epoch of incredulity, it was the"
                        + " season of Light, it was the season of Darkness, it was the"
                        + " spring of hope, it was the winter of despair, we had everything"
                        + " before us, we had nothing before us, we were all going direct"
                        + " to Heaven, we were all going direct the other way—in short,"
                        + " the period was so far like the present period, that some of its"
                        + " noisiest authorities insisted on its being received, for good"
                        + " or for evil, in the superlative degree of comparison only.";

        JTextArea topArea = new JTextArea(text, 4, 20);
        topArea.setLineWrap(true);
        topArea.setWrapStyleWord(true);

        JTextArea bottomArea = new JTextArea(text, 8, 20);
        bottomArea.setLineWrap(true);
        bottomArea.setWrapStyleWord(true);

        JScrollPane top = new JScrollPane(topArea);
        JScrollPane bottom = new JScrollPane(bottomArea);

        top.setMinimumSize(top.getPreferredSize());
        bottom.setMinimumSize(bottom.getPreferredSize());

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;

        gbc.weighty = 0.5;
        panel.add(top, gbc);
        panel.add(bottom, gbc);

        JFrame frame = new JFrame("GridBagConstraints Equal Weights");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> showWindow());
    }
}
