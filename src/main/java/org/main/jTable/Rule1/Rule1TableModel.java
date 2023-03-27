package org.main.jTable.Rule1;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class Rule1TableModel extends AbstractTableModel {
    private static final String[] COL_NAMES = {"Validate?", "Sheet","Target Column", "Run[All rows/Custom]", "Row No: From", "Row No: To" };


    private static final long serialVersionUID = 1L;
    private List<Rule1Model> rule1ModelArrayList = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return rule1ModelArrayList.size();
    }

/*
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return super.getColumnClass(columnIndex);
            case 1:
                return Integer.class;
            case 2:
            case 3:
                return Double.class;
        }
        return super.getColumnClass(columnIndex);
    }
*/




    @Override
    public String getColumnName(int column) {
        return COL_NAMES[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Rule1Model rule1Model = rule1ModelArrayList.get(row);
        switch (column) {
            case 0:
                return rule1Model.getIsToRun();
            case 1:
                return rule1Model.getSheet();
            case 2:
                return rule1Model.getRuleExecutionType();
            case 3:
                return rule1Model.getFromRow();
            case 4:
                return rule1Model.getToRow();
            case 5:
                return rule1Model.getTargetHeader();
        }
        return null;
    }

    @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Rule1Model rule1Model = rule1ModelArrayList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                 rule1Model.setIsToRun(aValue.toString());
            case 1:
                 rule1Model.setSheet(aValue.toString());
            case 2:
                 rule1Model.setRuleExecutionType(aValue.toString());
            case 3:
                 rule1Model.setFromRow(aValue.toString());
            case 4:
                 rule1Model.setToRow(aValue.toString());
            case 5:
                 rule1Model.setTargetHeader(aValue.toString());
        }

        fireTableRowsUpdated(rowIndex, rowIndex);

    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //return columnIndex == 0 || columnIndex == 1;
        return false;
    }

    public void addRow(Rule1Model item) {
        Rule1Model itemWithCount = new Rule1Model(item.getIsToRun(),item.getRuleExecutionType(),
                item.getFromRow(), item.getToRow(), item.getSheet(),item.getTargetHeader());
        rule1ModelArrayList.add(itemWithCount);
        int row = rule1ModelArrayList.size() - 1;
        fireTableRowsInserted(row, row);
    }


    public void deleteRow(int rowIndex){
        rule1ModelArrayList.remove(rowIndex);
        fireTableDataChanged();
    }

    public Rule1Model getTableRow(int rowIndex){
        return rule1ModelArrayList.get(rowIndex);
    }

    public void updateTableRow(Rule1Model item,int rowIndex){
        Rule1Model updateModel =  rule1ModelArrayList.get(rowIndex);
        updateModel.setIsToRun(item.getIsToRun());
        updateModel.setSheet(item.getSheet());
        updateModel.setTargetHeader(item.getTargetHeader());
        updateModel.setRuleExecutionType(item.getRuleExecutionType());
        updateModel.setFromRow(item.getFromRow());
        updateModel.setToRow(item.getToRow());
        fireTableDataChanged();

    }



















}
