package org.main.jTable.Rule2;

import org.main.UtilsClass.SpringUtilities;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.Set;

public class Rule2FieldsWindow implements ItemListener {


    private JPanel mainPanel = new JPanel();
    JComboBox toRunDrp;
    JComboBox sheetDrp;
    JComboBox targetColumnDrp;

    JComboBox formats;

    JComboBox noOfRowsToRunDrp;
    JTextField textFieldFrom;
    JTextField textFieldTo ;
    Map<String, Map<String, Map<Integer, String>>> workbook;

    @SuppressWarnings("serial")
    public Rule2FieldsWindow() {

        mainPanel.setLayout(new SpringLayout());

        String[] COL_NAMES = {"Validate?:", "Sheet:", "All rows/Custom]:", "Row From:", "Row To:", "Target Column:","Format"};
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
        sheetDrp = new JComboBox();
        sheetDrp.addItemListener(this);
        sheetDrp.setEditable(false);
        l2.setLabelFor(sheetDrp);
        mainPanel.add(sheetDrp);

        JLabel l3 = new JLabel(COL_NAMES[5], JLabel.TRAILING);
        mainPanel.add(l3);
        targetColumnDrp = new JComboBox();
        targetColumnDrp.setEditable(false);
        l2.setLabelFor(targetColumnDrp);
        mainPanel.add(targetColumnDrp);

        JLabel l7 = new JLabel(COL_NAMES[6], JLabel.TRAILING);
        mainPanel.add(l7);
        String[] formatsArr = {"dd:mm:yyyy","mm:dd:yyyy","H:M:S","Number","Text",};
        formats = new JComboBox(formatsArr);
        formats.setEditable(false);
        l7.setLabelFor(formats);
        mainPanel.add(formats);


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
                7, 2, //rows, cols
                10, 6,        //initX, initY
                10, 20);       //xPad, yPad


    }


    public JPanel getMainPanel(Map<String, Map<String, Map<Integer, String>>> workbook) {

        this.workbook = workbook;
        sheetDrp.setModel(new DefaultComboBoxModel(convert(workbook.keySet())));
        sheetDrp.setSelectedIndex(-1);
        targetColumnDrp.setSelectedIndex(-1);
        return mainPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    public Rule2Model getSelectedItem() {

        return new Rule2Model(
                toRunDrp.getSelectedItem().toString(),
                sheetDrp.getSelectedItem().toString(),
                targetColumnDrp.getSelectedItem().toString(),
                formats.getSelectedItem().toString(),
                noOfRowsToRunDrp.getSelectedItem().toString(),
                textFieldFrom.getText(),
                textFieldTo.getText()
                ) ;

    }

    public void pushDataIntoForm(Rule2Model model,Map<String, Map<String, Map<Integer, String>>> workbook){

        this.workbook = workbook;
        sheetDrp.setModel(new DefaultComboBoxModel(convert(workbook.keySet())));


        toRunDrp.setSelectedItem(model.getIsToRun());
        noOfRowsToRunDrp.setSelectedItem(model.getRuleExecutionType());
        sheetDrp.setSelectedItem(model.getSheet());
        formats.setSelectedItem(model.getFormat());
        targetColumnDrp.setSelectedItem(model.getTargetHeader());
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
