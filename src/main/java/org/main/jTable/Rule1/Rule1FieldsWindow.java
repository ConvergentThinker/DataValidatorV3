package org.main.jTable.Rule1;

import org.main.SpringUtilities;

import javax.swing.*;

public class Rule1FieldsWindow {

    private JPanel mainPanel = new JPanel();
    JComboBox toRunDrp;
    JComboBox sheetDrp;
    JComboBox targetColumnDrp;
    JComboBox noOfRowsToRunDrp;
    JTextField textFieldFrom;
    JTextField textFieldTo ;




    @SuppressWarnings("serial")
    public Rule1FieldsWindow() {

        mainPanel.setLayout(new SpringLayout());

        String[] COL_NAMES = {"Validate?:", "Sheet:", "All rows/Custom]:", "Row From:", "Row To:", "Target Column:"};
        int numPairs = COL_NAMES.length;

        // 1
        String[] toRun = { "Yes","No" };
        JLabel l = new JLabel(COL_NAMES[0], JLabel.TRAILING);
        mainPanel.add(l);
        toRunDrp = new JComboBox(toRun);
        toRunDrp.setEditable(false);
        l.setLabelFor(toRunDrp);
        mainPanel.add(toRunDrp);

        JLabel l2 = new JLabel(COL_NAMES[1], JLabel.TRAILING);
        mainPanel.add(l2);
        String[] sheets = {"Sheet1","Sheet2"};
        sheetDrp = new JComboBox(sheets);
        sheetDrp.setEditable(false);
        l2.setLabelFor(sheetDrp);
        mainPanel.add(sheetDrp);

        JLabel l3 = new JLabel(COL_NAMES[5], JLabel.TRAILING);
        mainPanel.add(l3);
        String[] columns = {"DOB","Age"};
        targetColumnDrp = new JComboBox(columns);
        targetColumnDrp.setEditable(false);
        l2.setLabelFor(targetColumnDrp);
        mainPanel.add(targetColumnDrp);

        JLabel l4 = new JLabel(COL_NAMES[2], JLabel.TRAILING);
        mainPanel.add(l4);
        String[] noOfRowsToRun = {"All Rows","Custom"};
        noOfRowsToRunDrp = new JComboBox(noOfRowsToRun);
        noOfRowsToRunDrp.setEditable(false);
        l4.setLabelFor(noOfRowsToRunDrp);
        mainPanel.add(noOfRowsToRunDrp);

        JLabel l5 = new JLabel(COL_NAMES[3], JLabel.TRAILING);
        mainPanel.add(l5);
        textFieldFrom = new JTextField(10);
        l5.setLabelFor(textFieldFrom);
        mainPanel.add(textFieldFrom);

        JLabel l6 = new JLabel(COL_NAMES[4], JLabel.TRAILING);
        mainPanel.add(l6);
         textFieldTo = new JTextField(10);
        l6.setLabelFor(textFieldTo);
        mainPanel.add(textFieldTo);

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(mainPanel,
                6, 2, //rows, cols
                10, 6,        //initX, initY
                10, 20);       //xPad, yPad


    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Rule1Model getSelectedItem() {
        return new Rule1Model(toRunDrp.getSelectedItem().toString(),noOfRowsToRunDrp.getSelectedItem().toString(),
                textFieldFrom.getText(),textFieldTo.getText(),sheetDrp.getSelectedItem().toString(),targetColumnDrp.getSelectedItem().toString()) ;

    }

    public void pushDataIntoForm(Rule1Model model){
        toRunDrp.setSelectedItem(model.getIsToRun());
        noOfRowsToRunDrp.setSelectedItem(model.getRuleExecutionType());
        sheetDrp.setSelectedItem(model.getSheet());
        targetColumnDrp.setSelectedItem(model.getTargetHeader());
        textFieldFrom.setText(model.getFromRow());
        textFieldTo.setText(model.getToRow());
    }






}