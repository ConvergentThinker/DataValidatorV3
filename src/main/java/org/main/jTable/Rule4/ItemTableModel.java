package org.main.jTable.Rule4;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ItemTableModel extends AbstractTableModel {
    private static final String[] COL_NAMES = { "Item Name", "Quantity", "Unit Price", "Total" };
    private static final long serialVersionUID = 1L;
    private List<ItemWithCount> itemsWithCount = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return itemsWithCount.size();
    }

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

    @Override
    public String getColumnName(int column) {
        return COL_NAMES[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        ItemWithCount itemWithCount = itemsWithCount.get(row);
        switch (column) {
            case 0:
                return itemWithCount.getItem().getName();
            case 1:
                return itemWithCount.getCount();
            case 2:
                return itemWithCount.getItem().getUnitPrice();
            case 3:
                return itemWithCount.getCount() * itemWithCount.getItem().getUnitPrice();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ItemWithCount itemWithCount = itemsWithCount.get(rowIndex);
        switch (columnIndex) {
            case 1:
                itemWithCount.setCount((int) aValue);
                fireTableRowsUpdated(rowIndex, rowIndex);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 1;
    }

    public void addRow(Item item, int quantity) {
        ItemWithCount itemWithCount = new ItemWithCount(item, quantity);
        itemsWithCount.add(itemWithCount);
        int row = itemsWithCount.size() - 1;
        fireTableRowsInserted(row, row);
    }

    private class ItemWithCount {
        private Item item;
        private int count;

        public ItemWithCount(Item item, int count) {
            this.item = item;
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Item getItem() {
            return item;
        }

    }
}
