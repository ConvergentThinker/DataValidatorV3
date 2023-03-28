package org.main;

import org.main.filechooser.ImageFileView;
import org.main.filechooser.ImageFilter;
import org.main.filechooser.ImagePreview;
import org.main.jTable.Rule1.Rule1Model;
import org.main.jTable.Rule1.Rule1TableModel;
import org.main.jTable.Rule1.Rule1FieldsWindow;
import org.main.jTable.Rule2.Rule2FieldsWindow;
import org.main.jTable.Rule2.Rule2Model;
import org.main.jTable.Rule2.Rule2TableModel;
import org.main.loadRuleFC.RuleFileView;
import org.main.loadRuleFC.RuleFilter;
import org.main.loadRuleFC.RulePreview;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App extends JPanel implements ActionListener {
    JPanel rightJpanel;
    JPanel leftJPanel;
    private int maxX;
    private int maxY;
    private JFileChooser fc;
    private JFileChooser fcLoadRule;
    private JTextField filePath;
    private JTextField loadRulefilePath;

    JButton uploadButton;

    JButton downloadRule;
    JButton uploadRule;

    // Table1 creation variables
    JButton remove1;
    JButton edit1;
    private Rule1TableModel tableModel = new Rule1TableModel();
    private JTable table = new JTable(tableModel);
    private AddRowAction addRowAction = new AddRowAction("Add +", KeyEvent.VK_A);

    // Table2  creation variables
    JButton remove2;
    JButton edit2;
    private Rule2TableModel tableModel2 = new Rule2TableModel();
    private JTable table2 = new JTable(tableModel2);

    private AddRowAction2 addRowAction2 = new AddRowAction2("Add +", KeyEvent.VK_A);

// Table3  creation variables

    private Rule1TableModel tableModel3 = new Rule1TableModel();
    private JTable table3 = new JTable(tableModel3);

    private AddRowAction3 addRowAction3 = new AddRowAction3("Add +", KeyEvent.VK_A);



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


        // ---------------------- right side
        rightJpanel = new JPanel();
        JScrollPane ruleScrollPane = new JScrollPane(rightJpanel);
        ruleScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ruleScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ruleScrollPane.setPreferredSize(new Dimension(maxX / 2 , maxY - 150));
        ruleScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Rule Tables "),
                                BorderFactory.createEmptyBorder(5, 5, 5, 5)),
                        ruleScrollPane.getBorder()));

//  layout right side parent container
        GridBagLayout layout = new GridBagLayout();
        rightJpanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;

        // ============================== Rule 1 starts ====================
        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel rule1Panel = new JPanel();
        rightJpanel.add(rule1Panel, gbc);

        //  add element to rule1Panel
        GridBagLayout gridBagLayoutRule1 = new GridBagLayout();
        rule1Panel.setLayout(gridBagLayoutRule1);
        GridBagConstraints gbcRule1 = new GridBagConstraints();
        gbcRule1.fill = GridBagConstraints.HORIZONTAL;
        gbcRule1.gridwidth = GridBagConstraints.REMAINDER;
        gbcRule1.weightx = 1;

        gbcRule1.gridx = 0;
        gbcRule1.gridy = 0;
        JPanel rule1HeaderPanel = new JPanel(new BorderLayout());
        rule1Panel.add(rule1HeaderPanel,gbcRule1);

        gbcRule1.gridx = 0;
        gbcRule1.gridy = 1;
        JPanel rule1TablePanel = new JPanel(new BorderLayout());
        rule1Panel.add(rule1TablePanel,gbcRule1);

        JPanel rule1HeaderDesPanel = new JPanel(new BorderLayout());
        rule1HeaderDesPanel.add(new JLabel("Hi"),BorderLayout.PAGE_START);
        rule1HeaderDesPanel.add(new JLabel("explain rule "),BorderLayout.CENTER);
        rule1HeaderPanel.add(rule1HeaderDesPanel,  BorderLayout.CENTER); // rule descripion
        JPanel rule1HeaderBtnPanel = new JPanel(new FlowLayout());
        rule1HeaderBtnPanel.add(new JButton(addRowAction));
        edit1 = new JButton("Edit");
        rule1HeaderBtnPanel.add(edit1);
        edit1.addActionListener(this);
        remove1 = new JButton("Remove");
        rule1HeaderBtnPanel.add(remove1);
        remove1.addActionListener(this);
        rule1HeaderPanel.add(rule1HeaderBtnPanel,BorderLayout.EAST); // btns

        // Table creation starts - rule1TablePanel
        rule1TablePanel.add(new JScrollPane(table));

// ============================== Rule 2  ====================

        gbc.gridx = 0;
        gbc.gridy = 1;
        JPanel rule2Panel = new JPanel(new BorderLayout());
        rightJpanel.add(rule2Panel, gbc);

//  add element to rule2Panel
        GridBagLayout gridBagLayoutRule2 = new GridBagLayout();
        rule2Panel.setLayout(gridBagLayoutRule2);
        GridBagConstraints gbcRule2 = new GridBagConstraints();
        gbcRule2.fill = GridBagConstraints.HORIZONTAL;
        gbcRule2.gridwidth = GridBagConstraints.REMAINDER;
        gbcRule2.weightx = 1;

        gbcRule2.gridx = 0;
        gbcRule2.gridy = 0;
        JPanel rule2HeaderPanel = new JPanel(new BorderLayout());
        rule2Panel.add(rule2HeaderPanel,gbcRule2);

        gbcRule2.gridx = 0;
        gbcRule2.gridy = 1;
        JPanel rule2TablePanel = new JPanel(new BorderLayout());
        rule2Panel.add(rule2TablePanel,gbcRule2);

        JPanel rule2HeaderDesPanel = new JPanel(new BorderLayout());
        rule2HeaderDesPanel.add(new JLabel("Hi"),BorderLayout.PAGE_START);
        rule2HeaderDesPanel.add(new JLabel("explain rule "),BorderLayout.CENTER);
        rule2HeaderPanel.add(rule2HeaderDesPanel,  BorderLayout.CENTER); // rule descripion
        JPanel rule2HeaderBtnPanel = new JPanel(new FlowLayout());
        rule2HeaderBtnPanel.add(new JButton(addRowAction2));
        edit2 = new JButton("Edit");
        rule2HeaderBtnPanel.add(edit2);
        edit2.addActionListener(this);
        remove2 = new JButton("Remove");
        rule2HeaderBtnPanel.add(remove2);
        remove2.addActionListener(this);
        rule2HeaderPanel.add(rule2HeaderBtnPanel,BorderLayout.EAST); // btns

        // Table creation starts - rule2TablePanel
        rule2TablePanel.add(new JScrollPane(table2));

// ============================== Rule 3  ====================

        //Rule2 Table
        gbc.gridx = 0;
        gbc.gridy = 2;
        JPanel rule3Panel = new JPanel(new BorderLayout());
        rightJpanel.add(rule3Panel, gbc);

//  add element to rule3Panel
        GridBagLayout gridBagLayoutRule3 = new GridBagLayout();
        rule3Panel.setLayout(gridBagLayoutRule3);
        GridBagConstraints gbcRule3 = new GridBagConstraints();
        gbcRule3.fill = GridBagConstraints.HORIZONTAL;
        gbcRule3.gridwidth = GridBagConstraints.REMAINDER;
        gbcRule3.weightx = 1;

        gbcRule3.gridx = 0;
        gbcRule3.gridy = 0;
        JPanel rule3HeaderPanel = new JPanel(new BorderLayout());
        rule3Panel.add(rule3HeaderPanel,gbcRule3);

        gbcRule3.gridx = 0;
        gbcRule3.gridy = 1;
        JPanel rule3TablePanel = new JPanel(new BorderLayout());
        rule3Panel.add(rule3TablePanel,gbcRule3);

        JPanel rule3HeaderDesPanel = new JPanel(new BorderLayout());
        rule3HeaderDesPanel.add(new JLabel("Hi"),BorderLayout.PAGE_START);
        rule3HeaderDesPanel.add(new JLabel("explain rule "),BorderLayout.CENTER);
        rule3HeaderPanel.add(rule3HeaderDesPanel,  BorderLayout.CENTER); // rule descripion
        JPanel rule3HeaderBtnPanel = new JPanel(new FlowLayout());
        rule3HeaderBtnPanel.add(new JButton(addRowAction3));
        rule3HeaderBtnPanel.add(new JButton("Remove"));
        rule3HeaderPanel.add(rule3HeaderBtnPanel,BorderLayout.EAST); // btns

        // Table creation starts - rule3TablePanel
        rule3TablePanel.add(new JScrollPane(table3));


        //----------------------

        leftJPanel = new JPanel(new BorderLayout());

        JScrollPane editScrollPane = new JScrollPane(leftJPanel);
        editScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //size
        editScrollPane.setPreferredSize(new Dimension(maxX / 2 - 100, maxY - 150));
        editScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Dashboard"),

                                BorderFactory.createEmptyBorder(5, 5, 5, 5)),
                        editScrollPane.getBorder()));

        JPanel fileUpload = new JPanel(new BorderLayout());
        JPanel fields = new JPanel(new BorderLayout()); // do
        JPanel bottomBtnG = new JPanel(new BorderLayout());

        fields.add(new JScrollPane(new TextArea()),BorderLayout.CENTER);

        leftJPanel.add(fileUpload,BorderLayout.PAGE_START);
        leftJPanel.add(fields,BorderLayout.CENTER);
        leftJPanel.add(bottomBtnG,BorderLayout.PAGE_END);

        fileUpload.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Upload Excel:"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        fields.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Reports"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        bottomBtnG.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Action Buttons"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
// bottom button

        JPanel jPanelBtn = new JPanel(new FlowLayout());
        downloadRule = new JButton("Download Rule");
        jPanelBtn.add(downloadRule);
        downloadRule.addActionListener(this);

        uploadRule = new JButton("Upload Rule");
        jPanelBtn.add(uploadRule);
        uploadRule.addActionListener(this);
        bottomBtnG.add(jPanelBtn,BorderLayout.WEST);


        JPanel jPanelBtnRun = new JPanel(new BorderLayout());
        jPanelBtnRun.add(new JButton("Run"),BorderLayout.EAST);

        loadRulefilePath = new JTextField();
        jPanelBtnRun.add(loadRulefilePath,BorderLayout.CENTER);
        // copy paste
        JPopupMenu menu1 = new JPopupMenu();
        Action cut1 = new DefaultEditorKit.CutAction();
        cut1.putValue(Action.NAME, "Cut");
        cut1.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
        menu1.add(cut1);
        Action copy1 = new DefaultEditorKit.CopyAction();
        copy1.putValue(Action.NAME, "Copy");
        copy1.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
        menu1.add(copy1);
        Action paste1 = new DefaultEditorKit.PasteAction();
        paste1.putValue(Action.NAME, "Paste");
        paste1.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
        menu1.add(paste1);
        Action selectAll1 = new SelectAll();
        menu1.add(selectAll1);
        loadRulefilePath.setComponentPopupMenu(menu1);
        //      run
        bottomBtnG.add(jPanelBtnRun,BorderLayout.CENTER);


        filePath = new JTextField();
        fileUpload.add(filePath, BorderLayout.CENTER);
        // copy paste
        JPopupMenu menu = new JPopupMenu();
        Action cut = new DefaultEditorKit.CutAction();
        cut.putValue(Action.NAME, "Cut");
        cut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
        menu.add(cut);
        Action copy = new DefaultEditorKit.CopyAction();
        copy.putValue(Action.NAME, "Copy");
        copy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
        menu.add(copy);
        Action paste = new DefaultEditorKit.PasteAction();
        paste.putValue(Action.NAME, "Paste");
        paste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
        menu.add(paste);
        Action selectAll = new SelectAll();
        menu.add(selectAll);
        filePath.setComponentPopupMenu(menu);
        //
        uploadButton = new JButton("Upload Excel");
        uploadButton.addActionListener(this);
        fileUpload.add(uploadButton, BorderLayout.EAST);




        // -------------------------------------
        //main
        JSplitPane splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                editScrollPane, ruleScrollPane
        );
        add(splitPaneMain, BorderLayout.CENTER);










    }
    static class SelectAll extends TextAction
    {
        public SelectAll()
        {
            super("Select All");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control S"));
        }

        public void actionPerformed(ActionEvent e)
        {
            JTextComponent component = getFocusedComponent();
            component.selectAll();
            component.requestFocusInWindow();
        }
    }


    class AddRowAction extends AbstractAction {
        private Rule1FieldsWindow newRowPanel = new Rule1FieldsWindow();

        public AddRowAction(String name, int mnemonic) {
            super(name);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int reply = JOptionPane.showConfirmDialog(table,
                    newRowPanel.getMainPanel(),
                    "Rule 1 fields ",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (reply == JOptionPane.OK_OPTION) {
                Rule1Model item = newRowPanel.getSelectedItem();
                tableModel.addRow(item);
            }
        }
    }
    class AddRowAction3 extends AbstractAction {
        private Rule1FieldsWindow newRowPanel = new Rule1FieldsWindow();

        public AddRowAction3(String name, int mnemonic) {
            super(name);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int reply = JOptionPane.showConfirmDialog(table2,
                    newRowPanel.getMainPanel(),
                    "Rule 3 fields ",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (reply == JOptionPane.OK_OPTION) {
                Rule1Model item = newRowPanel.getSelectedItem();
                tableModel3.addRow(item);
            }
        }
    }
    class AddRowAction2 extends AbstractAction {
        private Rule2FieldsWindow newRowPanel = new Rule2FieldsWindow();

        public AddRowAction2(String name, int mnemonic) {
            super(name);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int reply = JOptionPane.showConfirmDialog(table2,
                    newRowPanel.getMainPanel(),
                    "Rule 2 fields ",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (reply == JOptionPane.OK_OPTION) {
                Rule2Model item = newRowPanel.getSelectedItem();
                tableModel2.addRow(item);
            }
        }
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
            UIManager.put("Table.gridColor", new ColorUIResource(Color.blue));
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

        if (e.getSource() == uploadButton ) {
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
            filePath.setText("");
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                filePath.setText(file.getAbsolutePath());
            } else {
                filePath.setText("Attachment cancelled by user.");
            }
            filePath.setCaretPosition(filePath.getDocument().getLength());

            //Reset the file chooser for the next time it's shown.
            fc.setSelectedFile(null);
        } else if (e.getSource() == remove1) {

                int[] rows = table.getSelectedRows();
                Rule1TableModel tm = (Rule1TableModel) table.getModel();
                for (int i = rows.length-1; i >= 0; i--) {
                    System.out.println("I "+ i);
                    tm.deleteRow(rows[i]);
                }
        }else if (e.getSource() == remove2) {

            int[] rows = table2.getSelectedRows();
            Rule2TableModel tm = (Rule2TableModel) table2.getModel();
            for (int i = rows.length-1; i >= 0; i--) {
                System.out.println("I "+ i);
                tm.deleteRow(rows[i]);
            }
        } else if (e.getSource() == edit1) {

            Rule1FieldsWindow newRowPanel = new Rule1FieldsWindow();
            int row = table.getSelectedRow();
            Rule1TableModel tm = (Rule1TableModel) table.getModel();
            Rule1Model model = tm.getTableRow(row);
            // push selected row into newRowPanel
            newRowPanel.pushDataIntoForm(model);

            int reply = JOptionPane.showConfirmDialog(table,
                    newRowPanel.getMainPanel(),
                    "Edit Rule 1",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (reply == JOptionPane.OK_OPTION) {

                Rule1Model item = newRowPanel.getSelectedItem(); // edited data
                tableModel.updateTableRow(item,row);

            }
        }
        else if (e.getSource() == edit2) {

            Rule2FieldsWindow newRowPanel = new Rule2FieldsWindow();
            int row = table2.getSelectedRow();
            Rule2TableModel tm = (Rule2TableModel) table2.getModel();
            Rule2Model model = tm.getTableRow(row);
            // push selected row into newRowPanel
            newRowPanel.pushDataIntoForm(model);

            int reply = JOptionPane.showConfirmDialog(table2,
                    newRowPanel.getMainPanel(),
                    "Edit Rule 1",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (reply == JOptionPane.OK_OPTION) {

                Rule2Model item = newRowPanel.getSelectedItem(); // edited data
                tableModel2.updateTableRow(item,row);

            }
        } else if (e.getSource() == downloadRule) {

            String path = documentsDirectory("file.rule");

            try {
                FileWriter writer = new FileWriter(path);
                // rule 1
                int size = tableModel.getRule1ModelArrayList().size();
                for (int i = 0; i < size; i++) {
                    String str = tableModel.getRule1ModelArrayList().get(i).toString();
                        writer.write(str);
                    if (i < size - 1)
                       writer.write("\n");
                }

                // rule 2
                writer.write("\n");
                int size2 = tableModel2.getRule2ModelArrayList().size();
                for (int i = 0; i < size2; i++) {
                    String str = tableModel2.getRule2ModelArrayList().get(i).toString();
                    writer.write(str);
                    if (i < size - 1)
                        writer.write("\n");
                }

                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            System.out.println(">>>>>> Download Rule >>> ends ");
            loadRulefilePath.setText("");
            loadRulefilePath.setText("Rules file downloaded at: "+ path);



        }else if (e.getSource() == uploadRule) {

            //Set up the file chooser.
            if (fcLoadRule == null) {
                fcLoadRule = new JFileChooser();
                //show hidden files if false then make it true to disable
                fcLoadRule.setFileHidingEnabled(false);
                //Add a custom file filter and disable the default
                //(Accept All) file filter.
                fcLoadRule.addChoosableFileFilter(new RuleFilter());
                fcLoadRule.setAcceptAllFileFilterUsed(false);
                //Add custom icons for file types.
                fcLoadRule.setFileView(new RuleFileView());
                //Add the preview pane.
                fcLoadRule.setAccessory(new RulePreview(fcLoadRule));
            }

            //Show it.
            int returnVal = fcLoadRule.showDialog(App.this,
                    "Attach");

            //Process the results.
            loadRulefilePath.setText("");
            File file = fcLoadRule.getSelectedFile();
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                loadRulefilePath.setText(file.getAbsolutePath());
            } else {
                loadRulefilePath.setText("Attachment cancelled by user.");
            }
            loadRulefilePath.setCaretPosition(loadRulefilePath.getDocument().getLength());

            //Reset the file chooser for the next time it's shown.
            fcLoadRule.setSelectedFile(null);



            // Rule1 file upload
            List<Rule1Model> rule1ModelArrayList = new ArrayList<>();
            String fileName;
            FileReader fileReader = null;
            try {
                fileName = file.getAbsolutePath();
                fileReader = new FileReader(fileName);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line;
                while((line = bufferedReader.readLine()) != null) {

                    if(line.contains("Rule1Row")){

                        String[] modelArr = line.split("::");
                        if(modelArr.length>4){
                            rule1ModelArrayList.add(new Rule1Model(modelArr[0],modelArr[1],modelArr[2],modelArr[3],modelArr[4],modelArr[5]));
                        }else {
                            rule1ModelArrayList.add(new Rule1Model(modelArr[0],modelArr[1],modelArr[2],modelArr[3]));
                        }

                    }

                }
            } catch (IOException ex) {
            }
            tableModel.loadTableRows(rule1ModelArrayList);
// Rule2 file upload

            // Rule1 file upload
            List<Rule2Model> rule2ModelArrayList = new ArrayList<>();
            String fileName2;
            FileReader fileReader2 = null;
            try {
                fileName2 = file.getAbsolutePath();
                fileReader2 = new FileReader(fileName2);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try (BufferedReader bufferedReader = new BufferedReader(fileReader2)) {
                String line;
                while((line = bufferedReader.readLine()) != null) {

                    if(line.contains("Rule2Row")) {
                        String[] modelArr = line.split("::");
                        if (modelArr.length > 5) {
                            rule2ModelArrayList.add(new Rule2Model(modelArr[0], modelArr[1], modelArr[2], modelArr[3], modelArr[4], modelArr[5], modelArr[6]));
                        } else {
                            rule2ModelArrayList.add(new Rule2Model(modelArr[0], modelArr[1], modelArr[2], modelArr[3], modelArr[4]));
                        }
                    }

                }
            } catch (IOException ex) {
            }
            tableModel2.loadTableRows(rule2ModelArrayList);





        }

    }


    public void CreateFile(String fileName) {
            try {
                File myObj = new File(fileName);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }


     public String documentsDirectory(String fileName) {
        // From CarbonCore/Folders.h
        final String kDocumentsDirectory = "docs";
        String filePath = "";

         if(System.getProperty("os.name").contains("Mac")){

             filePath = "/Users/" + System.getProperty("user.name") + "/Documents";

         }else{
             filePath = "C:/Users/" + System.getProperty("user.name") + "/Documents";
         }
         System.out.println(filePath+"/" +fileName );

        CreateFile(filePath+"/" +fileName);
        return  filePath+"/" +fileName;
    }




}
