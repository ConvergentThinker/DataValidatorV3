package org.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

class MyFrame extends JFrame implements ActionListener {

    public static Process p;
    public static ProcessBuilder builder;
    public Container c;
    private JLabel name;
    private JTextArea header;
    private JLabel attachementNameL;
    private JLabel serverLabel;
    private JTextField attachmentET;
    private JTextField userName;
    private JTextField password;
    private JTextField tname;
    private JTextField serverEd;
    private JTextArea textArea;
    private JButton takeScreenshot;
    private JButton sub;
    private JButton reset;
    public final JLabel tout = new JLabel();
    public final JScrollPane areaScrollPane = new JScrollPane(tout);
    private JLabel res;
    public String home;


    public MyFrame() throws IOException, InterruptedException {

        String headerText =
                " Innovative Solutions -  Contact : innovativesolutionsapps@gmail.com" +
                        "\n" +
                        "Transform your mundane and Repetitive tasks from manual to automation and increase you productivity. \n" +
                        "We will help you to that for you.let me know if you have something in your mind" + "\n" +
                        " to achieve like this.";

        home = System.getProperty("user.home");
        setTitle("Jira Screenshot Attachment Tool - V1.0   By SAKTHIVEL IYAPPAN  ");
        setBounds(300, 90, 1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);

        name = new JLabel("Issue Id");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(80, 30);
        name.setLocation(10, 270);
        c.add(name);

        tname = new RoundedJTextField(50, "");
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(150, 40);
        tname.setLocation(150, 270);
        c.add(tname);


        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(500, 300));
        areaScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Bug Description "),
                                BorderFactory.createEmptyBorder(5, 5, 5, 5)),
                        areaScrollPane.getBorder()));
        areaScrollPane.setSize(490, 200);
        areaScrollPane.setLocation(10, 400);
        c.add(areaScrollPane);

        attachementNameL = new JLabel("Screenshot Name:");
        attachementNameL.setFont(new Font("Arial", Font.PLAIN, 20));
        attachementNameL.setSize(250, 20);
        attachementNameL.setLocation(10, 340);
        c.add(attachementNameL);

        attachmentET = new RoundedJTextField(50, " file name ");
        attachmentET.setFont(new Font("Arial", Font.PLAIN, 15));
        attachmentET.setSize(300, 40);
        attachmentET.setLocation(200, 330);
        c.add(attachmentET);

        takeScreenshot = new JButton("Take Screenshot ");
        takeScreenshot.setFont(new Font("Arial", Font.PLAIN, 15));
        takeScreenshot.setSize(150, 40);
        takeScreenshot.setLocation(380, 720);
        takeScreenshot.addActionListener(this);
        c.add(takeScreenshot);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(150, 40);
        sub.setLocation(20, 720);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(150, 40);
        reset.setLocation(200, 720);
        reset.addActionListener(this);
        c.add(reset);

        // to show status in the bottom.
        res = new JLabel("");
        res.setForeground(Color.red);
        res.setFont(new Font("Arial", Font.PLAIN, 18));
        res.setSize(500, 50);
        res.setLocation(20, 640);
        c.add(res);

        serverLabel = new JLabel("Server Name");
        serverLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        serverLabel.setSize(80, 30);
        serverLabel.setLocation(10, 210);
        c.add(serverLabel);

        serverEd = new RoundedJTextField(50, " Server Address - xxxx.atlassian.net ");
        serverEd.setFont(new Font("Arial", Font.PLAIN, 15));
        serverEd.setSize(350, 40);
        serverEd.setLocation(150, 210);
        c.add(serverEd);

        // jira user Name and password
        header = new JTextArea();
        header.setFont(new Font("Arial", Font.ITALIC, 14));
        header.setSize(500, 70);
        header.setForeground(Color.BLUE);
        header.setLocation(10, 10);
        c.add(header);
        header.setText(headerText);
        header.setEnabled(false);

        name = new JLabel("User Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(180, 30);
        name.setLocation(10, 94);
        c.add(name);

        userName = new RoundedJTextField(50, " Email Id");
        userName.setFont(new Font("Arial", Font.PLAIN, 15));
        userName.setSize(350, 40);
        userName.setLocation(150, 90);
        c.add(userName);

        name = new JLabel("API Token");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(170, 20);
        name.setLocation(10, 163);
        c.add(name);

        password = new RoundedJTextField(50, " API Token");
        password.setFont(new Font("Arial", Font.PLAIN, 15));
        password.setSize(350, 40);
        password.setLocation(150, 150);
        c.add(password);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {

        } else if (e.getSource() == reset) {

        } else if (e.getSource() == takeScreenshot) {

        }
    }


    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.TRUE);
                try {
                    MyFrame f = new MyFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}


// implement a round-shaped JTextField
class RoundedJTextField extends JTextField {
    private Shape shape;
    private  String _hint;

    public RoundedJTextField(int size,String hint) {
        super(size);
        _hint = hint;
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(_hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
}









