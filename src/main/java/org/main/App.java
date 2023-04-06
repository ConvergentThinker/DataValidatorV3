package org.main;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.util.SystemInfo;
import org.main.UtilsClass.SoundUtils;
import org.main.datavalidator.Rule1ValidatorEngine;
import org.main.datavalidator.Rule2ValidatorEngine;
import org.main.engine.ReaderEngine;
import org.main.filechooser.ImageFileView;
import org.main.filechooser.ImageFilter;
import org.main.filechooser.ImagePreview;
import org.main.jTable.CustomRenderer;
import org.main.jTable.Rule1.Rule1Model;
import org.main.jTable.Rule1.Rule1TableModel;
import org.main.jTable.Rule1.Rule1FieldsWindow;
import org.main.jTable.Rule2.Rule2FieldsWindow;
import org.main.jTable.Rule2.Rule2Model;
import org.main.jTable.Rule2.Rule2TableModel;
import org.main.jTable.ScrollBarCustom;
import org.main.jTable.TableDark;
import org.main.loadRuleFC.RuleFileView;
import org.main.loadRuleFC.RuleFilter;
import org.main.loadRuleFC.RulePreview;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

public class App extends JPanel implements ActionListener {

    int hz = 500;
    int msec = 200;
    double vol = 0.3;

    JPanel rightJpanel;
    JPanel leftJPanel;
    private int maxX;
    private int maxY;
    private JFileChooser fc;
    private JFileChooser fcLoadRule;
    private JTextField filePath;
    JButton uploadButton;
    JButton reload;
    JButton downloadRule;
    JButton uploadRule;
    JButton run;
    JTextArea output;
    public static Font fontTitle = new Font("Comic Sans Ms", Font.BOLD, 12);
    // Engine variables
    Map<String, Map<String, Map<Integer, String>>> inputExcelData;
    final ReaderEngine readerEngine = new ReaderEngine();


    // Table1 creation variables
    JButton remove1;
    JButton edit1;
    private javax.swing.JScrollPane jScrollPane1;
    private Rule1TableModel tableModel = new Rule1TableModel();
  //  private JTable table = new JTable(tableModel);
    private TableDark table = new TableDark(tableModel){
      public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
      {
          //Always toggle on single selection
          super.changeSelection(rowIndex, columnIndex, !extend, extend);
      }
     };

    private AddRowAction addRowAction = new AddRowAction("Add +", KeyEvent.VK_A);

    // Table2  creation variables
    private javax.swing.JScrollPane jScrollPane2;
    JButton remove2;
    JButton edit2;
    private Rule2TableModel tableModel2 = new Rule2TableModel();
    private TableDark table2 = new TableDark(tableModel2){
        public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
        {
            //Always toggle on single selection
            super.changeSelection(rowIndex, columnIndex, !extend, extend);
        }
    };

    private AddRowAction2 addRowAction2 = new AddRowAction2("Add +", KeyEvent.VK_A);

// Table3  creation variables

    private Rule1TableModel tableModel3 = new Rule1TableModel();
    private JTable table3 = new JTable(tableModel3);

    private AddRowAction3 addRowAction3 = new AddRowAction3("Add +", KeyEvent.VK_A);



    public App() {
        //
        jScrollPane1 = new javax.swing.JScrollPane();
        table.fixTable(jScrollPane1);
        jScrollPane1.setViewportView(table);

        //Set your own renderer.  You'll have to set this for Number and Boolean too if you're using those
        CustomRenderer cr = new CustomRenderer(table.getDefaultRenderer(Object.class), Color.darkGray, Color.darkGray, Color.darkGray, Color.darkGray);
        table.setDefaultRenderer(Object.class, cr);


        jScrollPane2 = new javax.swing.JScrollPane();
        table2.fixTable(jScrollPane2);
        jScrollPane2.setViewportView(table2);

        //Set your own renderer.  You'll have to set this for Number and Boolean too if you're using those
        CustomRenderer cr2 = new CustomRenderer(table2.getDefaultRenderer(Object.class), Color.darkGray, Color.darkGray, Color.darkGray, Color.darkGray);
        table2.setDefaultRenderer(Object.class, cr2);

        //


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
                        ,
                        "\n"
                        , ""

                );

                add(new JLabel(headerText));
            }
        }, BorderLayout.PAGE_END);


        // ---------------------- right side
        rightJpanel = new JPanel(new BorderLayout());

        JPanel rightSideHeaderPanel = new JPanel();
        rightJpanel.add(rightSideHeaderPanel,BorderLayout.PAGE_START);
        JScrollPane ruleScrollPane = new JScrollPane(rightJpanel);

        ruleScrollPane.setVerticalScrollBar(new ScrollBarCustom());


        ruleScrollPane.setPreferredSize(new Dimension(maxX / 2 , maxY - 150));






        rightJpanel.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                        " Available Rules ", TitledBorder.CENTER, TitledBorder.TOP)
        );

        //todo:- need to re-design this full table layout for better usability


//  layout right side parent container
        GridBagLayout layout = new GridBagLayout();

        JPanel rightSideParentTalePanel = new JPanel(layout);
        JScrollPane ruleScroll = new JScrollPane(rightSideParentTalePanel);
        ruleScroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        ruleScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;

        // ============================== Rule 1 starts ====================
        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel rule1Panel = new JPanel();
        rightSideParentTalePanel.add(rule1Panel, gbc);

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
        //rule1HeaderDesPanel.add(new JLabel("Hi"),BorderLayout.PAGE_START);
        rule1HeaderDesPanel.add(new JLabel("  Rule 1 :- Find and print empty Cells in particular Column"),BorderLayout.CENTER);
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

        JScrollPane scrollPane = new JScrollPane(table);
        rule1TablePanel.add(scrollPane);


// ============================== Rule 2  ====================

        gbc.gridx = 0;
        gbc.gridy = 1;
        JPanel rule2Panel = new JPanel(new BorderLayout());
        rightSideParentTalePanel.add(rule2Panel, gbc);

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
       // rule2HeaderDesPanel.add(new JLabel("Hi"),BorderLayout.PAGE_START);
        rule2HeaderDesPanel.add(new JLabel("  Rule 2 :- Verify and validate cell Data format"),BorderLayout.CENTER);

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

     /*   //Rule2 Table
        gbc.gridx = 0;
        gbc.gridy = 2;
        JPanel rule3Panel = new JPanel(new BorderLayout());
        rightSideParentTalePanel.add(rule3Panel, gbc);

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
        rule3TablePanel.add(new JScrollPane(table3));*/


        //----------------------
        rightJpanel.add(rightSideParentTalePanel,BorderLayout.CENTER);



        // leftJPanel
        leftJPanel = new JPanel(new BorderLayout());

        JScrollPane editScrollPane = new JScrollPane(leftJPanel);
        //size
        editScrollPane.setPreferredSize(new Dimension(maxX / 2 - 100, maxY - 150));

        editScrollPane.setBorder(
        BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                " DashBoard ", TitledBorder.CENTER, TitledBorder.TOP)
        );


        JPanel fileUpload = new JPanel(new BorderLayout());
        JPanel fields = new JPanel(new BorderLayout());
        JPanel bottomBtnG = new JPanel(new BorderLayout());

        output  = new JTextArea();
        fields.add(new JScrollPane(output),BorderLayout.CENTER);

        leftJPanel.add(fileUpload,BorderLayout.PAGE_START);
        leftJPanel.add(fields,BorderLayout.CENTER);
        //leftJPanel.add(bottomBtnG,BorderLayout.PAGE_END);
        fileUpload.add(bottomBtnG,BorderLayout.PAGE_END);


        fileUpload.setBorder(createTitleBorder("Upload Excel:(only .xlsx file)"));
        fields.setBorder(createTitleBorder("Console Output :-"));
        //bottomBtnG.setBorder(createTitleBorder("Action Buttons "));

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
        run = new JButton(" <<< Run  >> ");
        jPanelBtn.add(run);
        run.addActionListener(this);

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

        JPanel jPanelFileUpload = new JPanel(new BorderLayout());
        uploadButton = new JButton(" Select ");
        uploadButton.addActionListener(this);
        reload  = new JButton(" reload ");
        reload.addActionListener(this);
        jPanelFileUpload.add(uploadButton,BorderLayout.CENTER);
        jPanelFileUpload.add(reload,BorderLayout.WEST);
        fileUpload.add(jPanelFileUpload, BorderLayout.EAST);



        // -------------------------------------
        //main
        JSplitPane splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                editScrollPane, ruleScrollPane
        );
        add(splitPaneMain, BorderLayout.CENTER);








    }

   /* public TitledBorder createTitleBorder(String title){
     return    BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
             title, TitledBorder.LEFT, TitledBorder.TOP,
                fontTitle, Color.gray);

    }*/
    public TitledBorder createTitleBorder(String title){
        return    BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                title, TitledBorder.LEFT, TitledBorder.TOP
                );

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
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            if(inputExcelData != null){
                int reply = JOptionPane.showConfirmDialog(table,
                        newRowPanel.getMainPanel(inputExcelData),
                        "Rule 1 fields ",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (reply == JOptionPane.OK_OPTION) {
                    Rule1Model item = newRowPanel.getSelectedItem();
                    tableModel.addRow(item);
                }
            }else{
                setWarningAlert("Please Upload Input excel file.");
            }

        }
    }

    public void setWarningAlert(String msg){
        JOptionPane optionPane = new JOptionPane(msg,JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog("Warning!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    public void setInfoAlert(String msg){
        JOptionPane optionPane = new JOptionPane(msg,JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Info!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }




    class AddRowAction3 extends AbstractAction {
        private Rule1FieldsWindow newRowPanel = new Rule1FieldsWindow();

        public AddRowAction3(String name, int mnemonic) {
            super(name);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
            if(inputExcelData != null){
                int reply = JOptionPane.showConfirmDialog(table2,
                        newRowPanel.getMainPanel(inputExcelData),
                        "Rule 3 fields ",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (reply == JOptionPane.OK_OPTION) {
                    Rule1Model item = newRowPanel.getSelectedItem();
                    tableModel3.addRow(item);
                }
            }else{
                setWarningAlert("Please Upload Input excel file.");
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
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            if (inputExcelData != null) {

                int reply = JOptionPane.showConfirmDialog(table2,
                        newRowPanel.getMainPanel(inputExcelData),
                        "Rule 2 fields ",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (reply == JOptionPane.OK_OPTION) {
                    Rule2Model item = newRowPanel.getSelectedItem();
                    tableModel2.addRow(item);
                }
            } else {
                setWarningAlert("Please Upload Input excel file.");
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

        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(23,180,252));
        frame.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.YELLOW);


        //Add content to the window.
        frame.add(new App());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        if( SystemInfo.isMacOS ) {
            // enable screen menu bar
            // (moves menu bar from JFrame window to top of screen)
            System.setProperty( "apple.laf.useScreenMenuBar", "true" );

            // application name used in screen menu bar
            // (in first menu after the "apple" menu)
            System.setProperty( "apple.awt.application.name", "My Application" );

            // appearance of window title bars
            // possible values:
            //   - "system": use current macOS appearance (light or dark)
            //   - "NSAppearanceNameAqua": use light appearance
            //   - "NSAppearanceNameDarkAqua": use dark appearance
            // (must be set on main thread and before AWT/Swing is initialized;
            //  setting it on AWT thread does not work)
            System.setProperty( "apple.awt.application.appearance", "system" );
        }


        // set global level font for Jlable only
        //UIManager.put("Label.font", fontTitle);


        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {

               // IntelliJTheme.setup(App.class.getResourceAsStream("DarkPurpleTheme.jar/DarkPurple.theme.json"));
            FlatLightLaf.setup();


                createAndShowGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == uploadButton ) {
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
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
            File file = fc.getSelectedFile();
            //Process the results.
            output.setText("");
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                filePath.setText(file.getAbsolutePath());
            } else {
                output.setText("Attachment cancelled by user.");
            }
            output.setCaretPosition(output.getDocument().getLength());

            //Reset the file chooser for the next time it's shown.
            fc.setSelectedFile(null);

            // Read input excel file
            inputExcelData = readerEngine.readCompleteExcel(file.getAbsolutePath());

            System.out.println("inputExcelData  "+ inputExcelData);

            if(inputExcelData.size() == 0){
                output.setText("");
                output.setText(readerEngine.getException());
            }else{
                output.setText("");
                output.setText("Input data source loaded ");
            }

        }
        // reload button
        else if (e.getSource() == reload ) {

            if(inputExcelData != null){

                // Read input excel file
                inputExcelData = readerEngine.readCompleteExcel(filePath.getText().trim());
                System.out.println("inputExcelData reloaded "+ inputExcelData);

                if(inputExcelData.size() == 0){
                    output.setText("");
                    output.setText(readerEngine.getException());
                }else{
                    output.setText("");
                    output.setText("Input data source reloaded ");
                }

            }else{
                setWarningAlert("Please Upload Input excel file.");
            }

        }
        else if (e.getSource() == remove1) {

            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            if(inputExcelData != null){
                int[] rows = table.getSelectedRows();
                if(rows.length > 0){
                    Rule1TableModel tm = (Rule1TableModel) table.getModel();
                    for (int i = rows.length-1; i >= 0; i--) {
                        System.out.println("I "+ i);
                        tm.deleteRow(rows[i]);
                    }
                }else {
                    setInfoAlert("Please select Row to Remove.");
                }
            }else{
                setWarningAlert("Please Upload Input excel file.");
            }


        }
        else if (e.getSource() == remove2) {
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
            if(inputExcelData != null){
                int[] rows = table2.getSelectedRows();
                if(rows.length > 0){
                    Rule2TableModel tm = (Rule2TableModel) table2.getModel();
                    for (int i = rows.length-1; i >= 0; i--) {
                        System.out.println("I "+ i);
                        tm.deleteRow(rows[i]);
                    }
                }else {
                    setInfoAlert("Please select Row to Remove.");
                }
            }else{
                setWarningAlert("Please Upload Input excel file.");
            }

        } else if (e.getSource() == edit1) {
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            if(inputExcelData != null){

                Rule1FieldsWindow newRowPanel = new Rule1FieldsWindow();

                int row = table.getSelectedRow();

                System.out.println("row "+ row);

                if(row == -1){

                    setInfoAlert("Please select Row to Edit.");

                }else {
                    Rule1TableModel tm = (Rule1TableModel) table.getModel();
                    Rule1Model model = tm.getTableRow(row);
                    // push selected row into newRowPanel
                    newRowPanel.pushDataIntoForm(model,inputExcelData);

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


            }else{
                setWarningAlert("Please Upload Input excel file.");
            }

        }
        else if (e.getSource() == edit2) {
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            if(inputExcelData != null){

                Rule2FieldsWindow newRowPanel = new Rule2FieldsWindow();
                int row = table2.getSelectedRow();
                System.out.println("row "+ row);
                if(row == -1){

                    setInfoAlert("Please select Row to Edit.");

                }else {
                    Rule2TableModel tm = (Rule2TableModel) table2.getModel();
                    Rule2Model model = tm.getTableRow(row);
                    // push selected row into newRowPanel
                    newRowPanel.pushDataIntoForm(model,inputExcelData);

                    int reply = JOptionPane.showConfirmDialog(table2,
                            newRowPanel.getMainPanel(),
                            "Edit Rule 1",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

                    if (reply == JOptionPane.OK_OPTION) {

                        Rule2Model item = newRowPanel.getSelectedItem(); // edited data
                        tableModel2.updateTableRow(item,row);
                    }
                }

            }else{
                setWarningAlert("Please Upload Input excel file.");
            }

        } else if (e.getSource() == downloadRule) {
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }

            boolean run = false;

            if (tableModel.getRule1ModelArrayList().size() > 0) {
                run = true;
            } else if (tableModel2.getRule2ModelArrayList().size() > 0) {
                run = true;
            } else {
                setWarningAlert("Please add at least one rule");
            }

            if(run) {

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
                output.setText("");
                File file = fcLoadRule.getSelectedFile();
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    output.setText(file.getAbsolutePath());
                } else {
                    output.setText("Attachment cancelled by user.");
                }
                output.setCaretPosition(output.getDocument().getLength());
                //Reset the file chooser for the next time it's shown.
                fcLoadRule.setSelectedFile(null);

                String path = CreateFile(file.getAbsolutePath()+".rule");

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
                output.setText("");
                output.setText("Rules file downloaded at: "+ path);
                setInfoAlert("Rules file downloaded at: "+ path);
            }



        }

        else if (e.getSource() == uploadRule) {
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
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
            output.setText("");
            File file = fcLoadRule.getSelectedFile();
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                output.setText(file.getAbsolutePath());
            } else {
                output.setText("Attachment cancelled by user.");
            }
            output.setCaretPosition(output.getDocument().getLength());

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
                        if(modelArr.length>5){
                            rule1ModelArrayList.add(new Rule1Model(modelArr[1],modelArr[2],modelArr[3],modelArr[4],modelArr[5],modelArr[6]));
                        }else {
                            rule1ModelArrayList.add(new Rule1Model(modelArr[1],modelArr[2],modelArr[3],modelArr[4]));
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
                        if (modelArr.length > 6) {
                            rule2ModelArrayList.add(new Rule2Model(modelArr[1], modelArr[2], modelArr[3], modelArr[4], modelArr[5], modelArr[6], modelArr[7]));
                        } else {
                            rule2ModelArrayList.add(new Rule2Model(modelArr[1], modelArr[2], modelArr[3], modelArr[4], modelArr[5]));
                        }
                    }

                }
            } catch (IOException ex) {
            }
            tableModel2.loadTableRows(rule2ModelArrayList);

        } else if (e.getSource() == run) {
            try {
                SoundUtils.tone(hz,msec, vol);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }


            boolean run = false;

            if (tableModel.getRule1ModelArrayList().size() > 0) {
                run = true;
            } else if (tableModel2.getRule2ModelArrayList().size() > 0) {
                run = true;
            } else {
                setWarningAlert("Please add at least one rule");
            }

            if(run) {

                String[] rulesArr = {"R1", "R2"};

                List<Object> masterList = new ArrayList<>();
                masterList.add(0, tableModel.getRule1ModelArrayList());
                masterList.add(1, tableModel2.getRule2ModelArrayList());


                int noOfThreads = 2;
                final App runner = new App();
                ExecutorService executorService = Executors.newFixedThreadPool(noOfThreads);
                Set<Callable<String>> callables = new HashSet<>();

                for (int i = 0; i < noOfThreads; i++) {
                    int finalI = i;

                    callables.add(new Callable<String>() {
                        public String call() {
                            System.out.println(" Thread name: " + Thread.currentThread().getName());
                            return runner.executeRule(rulesArr[finalI], inputExcelData, masterList);
                        }
                    });
                }

                // Run all rules
                List<Future<String>> futures = null;
                try {
                    futures = executorService.invokeAll(callables);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                List<String> reportList = new ArrayList<>();

                // print results
                for (Future<String> future : futures) {
                    //System.out.println("future.get = " + future.isDone());
                    try {
                        // this is to return anything after that particular thread completed.
                        // In our case, we are returning errors list,  just print them on the console.

                        if (future.get().contains("&")) {

                            String[] infoArr = future.get().split("&");
                            System.out.println("===============INFO===============");
                            for (String arr : infoArr) {
                                String[] item = arr.split(",");
                                System.out.println("For " + item[0] + ",in sheet: " + item[1] + " , Row No:" + item[2] + " in column " + item[3] + " >>> INFO: " + item[4]);
                                reportList.add("For " + item[0] + ",in sheet " + item[1] + " , Row No:" + item[2] + " in column " + item[3] + " >>> INFO: " + item[4]);
                            }
                            System.out.println("===================================");
                        } else {
                            reportList.add(future.get().toString());

                        }

                    } catch (ExecutionException e1) {
                        // this is best place to see program failure reason, why?
                        e1.printStackTrace();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                output.setText(join(reportList, "\n"));
                executorService.shutdown();

                setInfoAlert("Rules validation completed!");

            }



        }


    }
    public static String join(List<String> list, String delim) {

        StringBuilder sb = new StringBuilder();

        String loopDelim = "";

        for(String s : list) {

            sb.append(loopDelim);
            sb.append(s);

            loopDelim = delim;
        }

        return sb.toString();
    }



    public String executeRule(String value, Map<String, Map<String, Map<Integer, String>>> inputExcelData,List<Object> masterList) {

        String getErrorListSTR = "";

        switch (value) {

           /* case "R1":
                Rule1ValidatorEngine rule1ValidatorEngine = new Rule1ValidatorEngine();
                rule1ValidatorEngine.validateRule1(inputExcelData);
                getErrorListSTR = rule1ValidatorEngine.getErrorsList();
                break;

            case "R3":
                Rule3ValidatorEngine rule3ValidatorEngine = new Rule3ValidatorEngine();
                rule3ValidatorEngine.validateRule3(inputExcelData);
                getErrorListSTR = rule3ValidatorEngine.getErrorsList();
                break;*/
            case "R1":
                Rule1ValidatorEngine rule1ValidatorEngine = new Rule1ValidatorEngine();
                rule1ValidatorEngine.validateRule4(inputExcelData, (List<Rule1Model>) masterList.get(0));
                if(rule1ValidatorEngine.getRuleListSize() > 0){
                    getErrorListSTR = rule1ValidatorEngine.getErrorsList();
                }else{
                    getErrorListSTR = "";
                }
                break;
            case "R2":
                Rule2ValidatorEngine rule2ValidatorEngine = new Rule2ValidatorEngine();
                rule2ValidatorEngine.validateRule2(inputExcelData,(List<Rule2Model>) masterList.get(1));
                if(rule2ValidatorEngine.getRuleListSize() > 0){
                    getErrorListSTR = rule2ValidatorEngine.getErrorsList();
                }else{
                    getErrorListSTR = "";
                }
                break;

            default:
                System.out.println("input Rule not present to proceed validation. please input available Rules ");

        }

        return getErrorListSTR;

    }



    void addCompForTitledBorder(TitledBorder border,
                                String description,
                                int justification,
                                int position,
                                Container container) {
        border.setTitleJustification(justification);
        border.setTitlePosition(position);
        addCompForBorder(border, description,
                container);
    }

    void addCompForBorder(Border border,
                          String description,
                          Container container) {
        JPanel comp = new JPanel(new GridLayout(1, 1), false);
        JLabel label = new JLabel(description, JLabel.CENTER);
        comp.add(label);
        comp.setBorder(border);

        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(comp);
    }

    public String CreateFile(String fileName) {

        File myObj = new File(fileName);
            try {
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());

                } else {
                    System.out.println("File already exists.");

                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        return myObj.getAbsolutePath();

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
