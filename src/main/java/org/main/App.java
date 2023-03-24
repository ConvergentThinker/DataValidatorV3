package org.main;

import org.main.filechooser.ImageFileView;
import org.main.filechooser.ImageFilter;
import org.main.filechooser.ImagePreview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class App extends JPanel implements ActionListener {
    JPanel rightJpanel;
    JPanel leftJPanel;
    private int maxX;
    private int maxY;
    private JFileChooser fc;

    private JTextArea filePath;




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

        JPanel fileUpload = new JPanel(new BorderLayout());
        JPanel fields = new JPanel();
        JPanel bottomBtnG = new JPanel();

        leftJPanel.add(fileUpload,BorderLayout.PAGE_START);
        leftJPanel.add(fields,BorderLayout.CENTER);
        leftJPanel.add(bottomBtnG,BorderLayout.PAGE_END);

        fileUpload.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Upload Excel:"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        fields.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Enter fields "),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        bottomBtnG.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Buttons"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));


        filePath = new JTextArea();
        fileUpload.add(filePath , BorderLayout.CENTER);

        JButton uploadButton = new JButton("Upload Excel");
        uploadButton.addActionListener(this);
        fileUpload.add(uploadButton, BorderLayout.EAST);

        //Create the combo box, select item at index 4.
        String[] availableRulesStrings = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
        JComboBox ruleDrpList = new JComboBox(availableRulesStrings);
        ruleDrpList.setSelectedIndex(4);
        ruleDrpList.addActionListener(this);
        fileUpload.add(ruleDrpList,BorderLayout.PAGE_END);



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
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //Set up the file chooser.
        if (fc == null) {
            fc = new JFileChooser();
            //show hidden files if false then make it true to disable
            fc.setFileHidingEnabled(false);
            //Add a custom file filter and disable the default
            //(Accept All) file filter.
            fc.addChoosableFileFilter(new ImageFilter());
            fc.setAcceptAllFileFilterUsed(false);

            //Add custom icons for file types.
            fc.setFileView(new ImageFileView());

            //Add the preview pane.
            fc.setAccessory(new ImagePreview(fc));
        }

        //Show it.
        int returnVal = fc.showDialog(App.this,
                "Attach");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            filePath.append(file.getAbsolutePath());
        } else {
            filePath.append("Attachment cancelled by user.");
        }
        filePath.setCaretPosition(filePath.getDocument().getLength());

        //Reset the file chooser for the next time it's shown.
        fc.setSelectedFile(null);


    }


}
