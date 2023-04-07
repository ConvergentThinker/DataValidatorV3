package org.main.jTable.Rule1;

import org.main.UtilsClass.SpringUtilities;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.Set;

public class Rule1FieldsWindow implements ItemListener {

    private JPanel mainPanel = new JPanel();
    JComboBox toRunDrp;
    JComboBox sheetDrp;
    JComboBox targetColumnDrp;
    JComboBox noOfRowsToRunDrp;
    JTextField textFieldFrom;
    JTextField textFieldTo;
    JLabel l5;
    JLabel l6;
    Map<String, Map<String, Map<Integer, String>>> workbook;

    @SuppressWarnings("serial")
    public Rule1FieldsWindow() {
        mainPanel.setLayout(new SpringLayout());

        String[] COL_NAMES = {"Validate?:", "Sheet:", "All rows/Custom]:", "Row From:", "Row To:", "Target Column:"};
        int numPairs = COL_NAMES.length;

        // 1
        String[] toRun = {"Yes", "No"};
        JLabel l = new JLabel(COL_NAMES[0], JLabel.TRAILING);
        mainPanel.add(l);
        toRunDrp = new JComboBox(toRun);
        toRunDrp.setEditable(false);
        l.setLabelFor(toRunDrp);
        mainPanel.add(toRunDrp);
        JLabel l2 = new JLabel(COL_NAMES[1], JLabel.TRAILING);
        mainPanel.add(l2);

        String[] sheets = {};
        sheetDrp = new JComboBox(sheets);
        sheetDrp.setEditable(false);
        sheetDrp.addItemListener(this);
        l2.setLabelFor(sheetDrp);
        mainPanel.add(sheetDrp);

        JLabel l3 = new JLabel(COL_NAMES[5], JLabel.TRAILING);
        mainPanel.add(l3);
        targetColumnDrp = new JComboBox();
        targetColumnDrp.setEditable(false);
        l2.setLabelFor(targetColumnDrp);
        mainPanel.add(targetColumnDrp);

        JLabel l4 = new JLabel(COL_NAMES[2], JLabel.TRAILING);
        mainPanel.add(l4);
        String[] noOfRowsToRun = {"All Rows", "Custom"};
        noOfRowsToRunDrp = new JComboBox(noOfRowsToRun);
        noOfRowsToRunDrp.setEditable(false);
        noOfRowsToRunDrp.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getItem().toString().equals("Custom")){
                    textFieldFrom.setVisible(true);
                    textFieldTo.setVisible(true);
                    l5.setVisible(true);
                    l6.setVisible(true);

                } else if (e.getItem().toString().equals("All Rows")) {
                    textFieldFrom.setVisible(false);
                    textFieldTo.setVisible(false);

                    l5.setVisible(false);
                    l6.setVisible(false);

                }
            }
        });
        l4.setLabelFor(noOfRowsToRunDrp);
        mainPanel.add(noOfRowsToRunDrp);

         l5 = new JLabel(COL_NAMES[3], JLabel.TRAILING);
        mainPanel.add(l5);
        textFieldFrom = new JTextField(10);
        l5.setLabelFor(textFieldFrom);
        textFieldFrom.setVisible(false);
        l5.setVisible(false);
        mainPanel.add(textFieldFrom);

         l6 = new JLabel(COL_NAMES[4], JLabel.TRAILING);
        mainPanel.add(l6);
        textFieldTo = new JTextField(10);
        l6.setLabelFor(textFieldTo);
        textFieldTo.setVisible(false);
        l6.setVisible(false);
        mainPanel.add(textFieldTo);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(mainPanel,
                6, 2, //rows, cols
                20, 10,        //initX, initY
                40, 10);       //xPad, yPad


    }
    public JPanel getMainPanel() {
        return mainPanel;
    }


    public JPanel getMainPanel(Map<String, Map<String, Map<Integer, String>>> workbook) {
        this.workbook = workbook;
        sheetDrp.setModel(new DefaultComboBoxModel(convert(workbook.keySet())));
        sheetDrp.setSelectedIndex(-1);
        targetColumnDrp.setSelectedIndex(-1);
        noOfRowsToRunDrp.setSelectedIndex(-1);
        return mainPanel;
    }
    public Rule1Model getSelectedItem() {

        if(noOfRowsToRunDrp.getSelectedItem().toString().equals("Custom")){
            return new Rule1Model(toRunDrp.getSelectedItem().toString(),
                    sheetDrp.getSelectedItem().toString(),
                    targetColumnDrp.getSelectedItem().toString(),
                    noOfRowsToRunDrp.getSelectedItem().toString(),
                    textFieldFrom.getText().toString().trim(), textFieldTo.getText().toString().trim()
            );
        }else {
            return new Rule1Model(toRunDrp.getSelectedItem().toString(),
                    sheetDrp.getSelectedItem().toString(),
                    targetColumnDrp.getSelectedItem().toString(),
                    noOfRowsToRunDrp.getSelectedItem().toString()
            );
        }

    }

    public void pushDataIntoForm(Rule1Model model,Map<String, Map<String, Map<Integer, String>>> workbook) {
        this.workbook = workbook;
        sheetDrp.setModel(new DefaultComboBoxModel(convert(workbook.keySet())));
        toRunDrp.setSelectedItem(model.getIsToRun());
        sheetDrp.setSelectedItem(model.getSheet());
        targetColumnDrp.setModel(new DefaultComboBoxModel(convert(workbook.get(model.getSheet()).keySet())));
        targetColumnDrp.setSelectedItem(model.getTargetHeader());
        noOfRowsToRunDrp.setSelectedItem(model.getRuleExecutionType());
        textFieldFrom.setText(model.getFromRow());
        textFieldTo.setText(model.getToRow());
    }


    // Function to convert Set<String> to String[]
    public static String[] convert(Set<String> setOfString) {

        // Create String[] of size of setOfString
        String[] arrayOfString = new String[setOfString.size()];

        // Copy elements from set to string array
        // using advanced for loop
        int index = 0;
        for (String str : setOfString)
            arrayOfString[index++] = str;

        // return the formed String[]
        return arrayOfString;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {

        if ((e.getStateChange() == ItemEvent.SELECTED)) {
             String selection = sheetDrp.getSelectedItem().toString();
            targetColumnDrp.setModel(new DefaultComboBoxModel(convert(workbook.get(selection).keySet())));

        }
    }




}