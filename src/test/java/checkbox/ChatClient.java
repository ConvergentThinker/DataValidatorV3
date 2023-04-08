package checkbox;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ChatClient {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);


    JCheckBox checkBox = new JCheckBox("Broadcast");
    DefaultListModel listModel = new DefaultListModel();
    JList onlineList = new JList(listModel);


    public ChatClient() {

        onlineList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(checkBox,BorderLayout.WEST);
        frame.getContentPane().add(new JScrollPane(messageArea), "South");
        frame.getContentPane().add(new JScrollPane(onlineList), "Center");

        frame.pack();

        onlineList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        checkBox.setSelected(true);

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (checkBox.isSelected()) {
                    out.println(textField.getText());
                    textField.setText("");
                }
                else {
                    System.out.println(Arrays.asList(onlineList.getSelectedIndices())); //For testing
                    String selectedName = (String) onlineList.getSelectedValue();
                    out.println(selectedName + ">>"+ textField.getText());
                    textField.setText("");
                }
            }
        });


    }

    private String getServerAddress() {
        return JOptionPane.showInputDialog(
                frame,
                "Enter IP Address of the Server:",
                "Welcome to the Chatter",
                JOptionPane.QUESTION_MESSAGE);
    }

    private String getName() {
        return JOptionPane.showInputDialog(
                frame,
                "Choose a screen name:",
                "Screen name selection",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void run() throws IOException {

        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            } else if (line.startsWith("NEWUSER")) {
                listModel.addElement(line.substring(7));
            } else if (line.startsWith("USEROUT")) {
                listModel.removeElement(line.substring(7));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}