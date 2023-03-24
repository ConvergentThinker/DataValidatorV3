package org.main;

import javax.swing.*;
import java.awt.*;

public class App extends JPanel {
    JPanel rightJpanel;
    JPanel leftJPanel;
    private int maxX;
    private int maxY;

    public App() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        maxX = screenSize.width;
        maxY = screenSize.height - 100;
        setPreferredSize(new Dimension(maxX, maxY));
        setLayout(new BorderLayout());
        add(new JPanel() {
            {
                String headerText = String.join("\n"
                        , "\n"
                        , "By SAKTHIVEL IYAPPAN "
                        , " Innovative Solutions "
                        , "email: innovativesolutionsapps@gmail.com"
                        ,
                        "\n"
                        , ""

                );

                add(new JLabel(headerText));
            }
        }, BorderLayout.PAGE_START);
        add(new JPanel() {
            {
                String headerText = String.join("\n"
                        , "\n"
                        , "By SAKTHIVEL IYAPPAN "
                        , " Innovative Solutions "
                        , "email: innovativesolutionsapps@gmail.com"
                        ,
                        "\n"
                        , ""

                );

                add(new JLabel(headerText));
            }
        }, BorderLayout.PAGE_END);


        // ----------------------
        rightJpanel = new JPanel(new BorderLayout());

        JScrollPane ruleScrollPane = new JScrollPane(rightJpanel);
        ruleScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ruleScrollPane.setPreferredSize(new Dimension(maxX / 2 - 20, maxY - 150));
        ruleScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Rule Tables "),
                                BorderFactory.createEmptyBorder(5, 5, 5, 5)),
                        ruleScrollPane.getBorder()));


        //--------------------------

        leftJPanel = new JPanel(new BorderLayout());

        JScrollPane editScrollPane = new JScrollPane(leftJPanel);
        editScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editScrollPane.setPreferredSize(new Dimension(maxX / 2 - 20, maxY - 150));
        editScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Add Rules "),
                                BorderFactory.createEmptyBorder(5, 5, 5, 5)),
                        editScrollPane.getBorder()));



        // -------------------------------------
        //main
        JSplitPane splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                editScrollPane, ruleScrollPane
        );
        add(splitPaneMain, BorderLayout.CENTER);










    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame(" Data Validator ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new App());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


}
