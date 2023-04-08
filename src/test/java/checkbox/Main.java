package checkbox;


import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;
import javax.swing.border.Border;

public class Main {

  static   ListModel<String> listModel = createListModel();
   static JList onlineList = new JList(listModel);




    public static void main(String[] args) {
        JFrame jFrame = createMainFrame();

        Container contentPane = jFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());


        ListSelectionDocument listSelectionDocument = new ListSelectionDocument();



        onlineList.setCellRenderer(new CheckboxListCellRenderer<String>());
        onlineList.setModel(listModel);
        onlineList.addListSelectionListener(listSelectionDocument);
        onlineList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);



        JTextArea listSelectionTextArea = new JTextArea(listSelectionDocument);

        Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();
        listSelectionTextArea.setBorder(loweredBevelBorder);

        contentPane.add(onlineList, BorderLayout.CENTER);
        contentPane.add(listSelectionTextArea, BorderLayout.SOUTH);


        jFrame.setVisible(true);
        jFrame.pack();

    }

    private static JFrame createMainFrame() {
        JFrame jFrame = new JFrame("Custom ListCellRenderer Example");
        jFrame.setSize(240, 240);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return jFrame;
    }

    private static DefaultListModel<String> createListModel() {
        DefaultListModel<String> listModel = new DefaultListModel<String>();

        listModel.addElement("Element 1");
        listModel.addElement("Element 2");
        listModel.addElement("Element 3");
        listModel.addElement("Element 4");

        return listModel;
    }

}