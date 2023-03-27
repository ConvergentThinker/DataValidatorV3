package org.main.jTable.Rule4;

import javax.swing.*;
import java.awt.*;

public class NewRowPanel {

    private JPanel mainPanel = new JPanel();
    private JComboBox<Item> itemsCombo;
    private JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));

    @SuppressWarnings("serial")
    public NewRowPanel(Item[] items) {
        itemsCombo = new JComboBox<>(items);
        itemsCombo.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                if (value != null) {
                    value = ((Item) value).getName();
                } else {
                    value = "";
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        mainPanel.add(new JLabel("Item:"));
        mainPanel.add(itemsCombo);
        mainPanel.add(Box.createHorizontalStrut(15));
        mainPanel.add(new JLabel("Quantity"));
        mainPanel.add(quantitySpinner);
    }

    public void reset() {
        itemsCombo.setSelectedIndex(-1);
        quantitySpinner.setValue(0);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Item getSelectedItem() {
        return (Item) itemsCombo.getSelectedItem();
    }

    public int getQuantity() {
        return (int) quantitySpinner.getValue();
    }
}