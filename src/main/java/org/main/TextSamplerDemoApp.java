package org.main;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextSamplerDemoApp extends JPanel {
    private String newline = "\n";
    public String textEntered;
    String fullDate = null;
    Boolean isValidFF = true;
    String author = null;
    private String strIntermediate;
    private String strError;
    private String main;
    private String ff;

    JEditorPane editorPane; // Error bdd
    JEditorPane textPane; // final FF lines
    JTextArea textArea = new JTextArea(); // main text area

    ArrayList initFF; // initial BDD lines without changes
    ArrayList intermediateLines; // BDD into spec lines
    ArrayList lstParamsInBDD = new ArrayList<String>(); // Data params list in BDD steps
    ArrayList missingParamsInExample = new ArrayList<String>(); //missing Data params list in BDD steps
    ArrayList actualParamsInExample = new ArrayList<String>();
    ArrayList errorBddLines;




    public TextSamplerDemoApp() {
        // Main Parent layout
        setLayout(new BorderLayout());

        //Create a text area. center pane
        //textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(400, 300));
        areaScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder(" Step Definition "),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                        areaScrollPane.getBorder()));



        /* ----------------------------------- */

        //Create an editor pane.
        JEditorPane editorPane = createEditorPane();
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(400, 300));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));

        /* ----------------------------------- */

        //Create a text pane.
        JEditorPane textPane = createTextPane();
        JScrollPane paneScrollPane = new JScrollPane(textPane);
        paneScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScrollPane.setPreferredSize(new Dimension(400, 300));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));

        /* ----------------------------------- */


        //Put the editor pane and the text pane in a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                editorScrollPane,
                paneScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);

        //
        JPanel rightPane = new JPanel(new GridLayout(1,0));
        rightPane.add(splitPane);
        rightPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Need your Attention for Below .."),
                BorderFactory.createEmptyBorder(5,5,5,5)));

        /* ----------------------------------- */

        //Put the  main and error pane in horizontal split
        JSplitPane splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,areaScrollPane,
                rightPane
        );
        splitPaneMain.setOneTouchExpandable(true);
        splitPaneMain.setResizeWeight(0.5);

        //Put everything together.
        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.add(splitPaneMain);
        add(leftPane, BorderLayout.CENTER);
        /* ----------------------------------- */

        // add Header pane
        add(new JPanel() {
            {


                String headerText = String.join("\n"
                        , "\n"
                        , "By SAKTHIVEL IYAPPAN "
                        ," Innovative Solutions "
                        ,"email: innovativesolutionsapps@gmail.com"
                        ,
                        "\n"
                        , ""

                );


                add(new JLabel(headerText));
            }
        }, BorderLayout.PAGE_START);
        // Bottom buttons controlls

        add(new JPanel() {
            {

                // copy
                add(new JButton(new AbstractAction("Copy") {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("Copy");
                        StringSelection stringSelection = new StringSelection(textArea.getText());
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(stringSelection, null);
                    }
                }));
                // clear, to open new instance
                add(new JButton(new AbstractAction("Next") {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        createAndShowGUI();
                    }
                }));
                // Validate FF steps and Example Data
                add(new JButton(new AbstractAction("Generate") {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("run");
                        textEntered = new String();
                        strIntermediate = new String();
                        strError = new String();
                        strError =" ";
                        textEntered = textArea.getText();
                        System.out.println("textEntered ?? "+ textEntered);
                        initFF = new ArrayList<String>();
                        intermediateLines = new ArrayList<String>();
                        isValidFF = true;
                        errorBddLines = new ArrayList<String>();
                        System.out.println("strIntermediate "+ strIntermediate);
                        coreLogicCreation(textEntered);
                        System.out.println("finish");
                    }
                }));

            }

        }, BorderLayout.SOUTH);

    }


    public void coreLogicCreation(String text) {

        String[] initLines = text.split("\\n");

        // finding BDD steps
        for (int i = 0; i < initLines.length; i++) {
            String line = initLines[i];
            if (line.trim().length() > 0) {
                String fLine = line.trim().substring(0, 5);
                boolean a = fLine.contains("And") || fLine.contains("Given") || fLine.contains("Then") || fLine.contains("When");
                //System.out.println(a);
                // System.out.println(i);
                if (a && i + 1 < initLines.length) {
                    String nextBDDLine = initLines[i + 1];
                    boolean b = nextBDDLine.contains("And") || nextBDDLine.contains("Given") || nextBDDLine.contains("Then") || nextBDDLine.contains("When");
                    if (!b) {
                        initFF.add(initLines[i].trim() + initLines[i + 1].trim());
                    } else {
                        initFF.add(line.trim());
                    }
                } else {
                    initFF.add(line.trim());
                }
            }
        }

        for (int i = 0; i < initFF.size(); i++) {
            String bdd = initFF.get(i).toString().trim();
            System.out.println("bdd >> "+ bdd);
        }




        for (int i = 0; i < initFF.size(); i++) {
            String bdd = initFF.get(i).toString().trim();
            if (bdd.startsWith("Given")) {
                String body = bdd.split("Given")[1].trim();
                String subStr = body.substring(0, 5).trim();
                String bddH = bdd.split(subStr)[0].trim();
                String step = bddH + "(\"" + checkTestDataAndReplaceWithRegexInBDD(body,bdd) + "\")" + "{ (args, userInfo) -> Void in" + "\n" + "}";
                intermediateLines.add(step);
            } else if (bdd.startsWith("When")) {
                String body = bdd.split("When")[1].trim();
                String step = "When" + "(\"" + checkTestDataAndReplaceWithRegexInBDD(body,bdd) + "\")" + "{ (args, userInfo) -> Void in" + "\n" + "}";
                intermediateLines.add(step);
            } else if (bdd.startsWith("Then")) {
                String body = bdd.split("Then")[1].trim();
                String step = "Then" + "(\"" + checkTestDataAndReplaceWithRegexInBDD(body,bdd) + "\")" + "{ (args, userInfo) -> Void in" + "\n" + "}";
                intermediateLines.add("\n" + step + "\n");
            }
        }

        // verify examples data against BDD params
        String exDataLine = "";
        for (int i = 0; i < initFF.size(); i++) {
            System.out.println("i"+ i +":" +initFF.get(i));
            if (initFF.get(i).toString().contains("Examples")) {
                exDataLine = initFF.get(i + 1).toString();
            }
        }
        //System.out.println("exDataLine " + exDataLine);
        //System.out.println("lstParamsInBDD size " + lstParamsInBDD.size());
        String[] data = exDataLine.split("\\|");
        //System.out.println("data lengh " + data.length);

        for (int j = 0; j < data.length; j++) {
            actualParamsInExample.add(data[j].trim());
        }

        for (int i = 0; i < lstParamsInBDD.size(); i++) {
            for (int j = 0; j < actualParamsInExample.size(); j++) {
                String element = lstParamsInBDD.get(i).toString().trim().replaceAll("\"", "").replaceAll("<", "").replaceAll(">", "").trim();
                //System.out.println("element >>>>" + element + ":");
                //System.out.println("actualParamsInExample :" + actualParamsInExample.get(j) + ":");
                if (!actualParamsInExample.contains(element)) {
                    if (!missingParamsInExample.contains(lstParamsInBDD.get(i))) {
                        missingParamsInExample.add(lstParamsInBDD.get(i));
                    }
                }
            }
        }

        // Adding existing methods to BDD
        for (Object step : intermediateLines) {
            strIntermediate = strIntermediate + (step + "\n");
        }

        for (int i = 0; i < missingParamsInExample.size(); i++) {
            System.out.println("missingParamsInExample i : " + i + ":" + missingParamsInExample.get(i));
        }

        for (int i=0;i<lstParamsInBDD.size();i++){
            //System.out.println("i:" + i + ": " + lstParamsInBDD.get(i));
        }


        // finding current date
        String localDate = LocalDateTime.of(LocalDate.now(), LocalTime.now()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String month = get_month(localDate);
        String date = get_date(localDate);
        String year = get_year(localDate);
        fullDate = date + " " + month + " " + year;
        // finding current user name from windows
        author = System.getProperty("user.name");
        // skeleton creation
        main = String.join("\n"
                , "Total Number of BDD Steps: " + intermediateLines.size()
                , "\n"
                , "/*"
                , "* @author " + author
                , "* @since " + fullDate
                , "*/"
                ,
                "\n"
                , strIntermediate

        );



        if(isValidFF){
            textArea.setText(main);
            textPane.setText(textEntered);
            for(Object exData:missingParamsInExample){
                strError = strError + (exData + "," + "\n");
            }
            if(strError != " "   ){
                editorPane.setText("Below Test Params are missing in Example Section which are defined in BDD steps" + "\n" + strError);
            }else{
                editorPane.setText("No Errors Found..");
            }

        }else{
            textArea.setText(textEntered);
            for (Object step : errorBddLines) {
                strError = strError + (step + "\n");
            }
            if(strError != null){
                editorPane.setText(strError);
            }else{
                editorPane.setText("No Errors Found..");
            }
        }



    }



    public String checkTestDataAndReplaceWithRegexInBDD(String bddStep,String fullBDDstep) {
        String fBDDStep = "";
        Boolean isHavingDataParam = false;
        char startChar='^';
        char endChar ='$';
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%#"); // % -\ , # - "
        stringBuilder.append("([^@\\\"]*)");
        stringBuilder.append("%#");

        fBDDStep = bddStep;
        String[] allWords = bddStep.split("[^\\da-zA-Z\"<>]+");    //  [^a-zA-Z"<>]+

        for (int i = 0; i < allWords.length; i++) {

            //1.Full match
            final String regexFullMatch = "(\"<.+?>\")+";
            final Pattern patternFullMatch = Pattern.compile(regexFullMatch, Pattern.MULTILINE);
            final Matcher matcherFullMatch = patternFullMatch.matcher(allWords[i]);
            // 2.Full match without < ,> only  - "" == ("(?!<).+?(?<!>)")+
            final String regexFullMatchTwo = "(\"(?!<).+?(?<!>)\")+";
            final Pattern patternFullMatchTwo = Pattern.compile(regexFullMatchTwo, Pattern.MULTILINE);
            final Matcher matcherFullMatchTwo = patternFullMatchTwo.matcher(allWords[i]);

            if (matcherFullMatch.find()) {

                if(!lstParamsInBDD.contains(allWords[i])){
                    lstParamsInBDD.add(allWords[i]);
                }

                System.out.println("ONE");
                isHavingDataParam = true ;
                Pattern p = Pattern.compile(regexFullMatch, Pattern.MULTILINE);
                System.out.println("initial String: " + allWords[i]);
                Matcher m = p.matcher(allWords[i]);
                String tmp = m.replaceAll(stringBuilder.toString());

                String newS = tmp.replaceAll("%", "\\\\");
                String newS1 = newS.replaceAll("#", "\"");
                String newS2 = newS1.replaceAll("@", "\\\\\\\\\\\\");
                System.out.println(newS2);

                fBDDStep = fBDDStep.replace(allWords[i],newS2);

                System.out.println("final string : " + fBDDStep);
            }
            else if (matcherFullMatchTwo.find()) {
                System.out.println("TWO...");
                isHavingDataParam = true ;
                Pattern p1 = Pattern.compile(regexFullMatchTwo, Pattern.MULTILINE);
                System.out.println("initial String: " + allWords[i]);
                Matcher m = p1.matcher(allWords[i]);
                String tmp = m.replaceAll(stringBuilder.toString());

                String newS = tmp.replaceAll("%", "\\\\");
                String newS1 = newS.replaceAll("#", "\"");
                String newS2 = newS1.replaceAll("@", "\\\\\\\\\\\\");
                System.out.println(newS2);

                fBDDStep = fBDDStep.replace(allWords[i],newS2);
                System.out.println("final string : " + fBDDStep);

            } else if (allWords[i].contains("\"") && allWords[i].contains("<") && !allWords[i].contains(">")) {
                isValidFF = false;
                System.out.println(" >>>>>>>>>>>2222 " + allWords[i]);
                String e2 = "Add '>' in -->> " + fullBDDstep;
                errorBddLines.add(e2);

            } else if (allWords[i].contains("\"") && !allWords[i].contains("<") && allWords[i].contains(">")) {
                isValidFF = false;
                System.out.println(" >>>>>>>>>>>333 " + allWords[i]);
                String e1 ="Add '<' in -->> " + fullBDDstep;
                errorBddLines.add(e1);

            } else if (!allWords[i].contains("\"") && allWords[i].contains("<") && allWords[i].contains(">")) {

             String newone =   allWords[i].replace("\"","-");
                if(!newone.contains("\"")){
                    isValidFF = false;
                    System.out.println(" >>>>>>>>>>>444 " + allWords[i]);
                    String e1 = "Add Double Qoutes in --> " + fullBDDstep;
                    errorBddLines.add(e1);
                }

            } else if (!allWords[i].contains("\"") && !allWords[i].contains("<") && allWords[i].contains(">")) {
                isValidFF = false;
                System.out.println(" >>>>>>>>>>>555 " + allWords[i]);
                String e1 = "Add Double Quetes and '<' in --> " + fullBDDstep;
                errorBddLines.add(e1);

            } else if (!allWords[i].contains("\"") && allWords[i].contains("<") && !allWords[i].contains(">")) {
                isValidFF = false;
                System.out.println(" >>>>>>>>>>>666 " + allWords[i]);
                String e1 = "Add Double Quetes and '>' in --> " + fullBDDstep;
                errorBddLines.add(e1);
            }else {
                System.out.println(" >>>>>>>>>>>7777 " + allWords[i]);
            }


        }
        if (isHavingDataParam) {
            fBDDStep = startChar + fBDDStep + endChar;

        }
        return fBDDStep;
    }







    public String get_month(String date) {
        String dateParts[] = date.split("/");
        String month = dateParts[1];
        return getMonthByCode(month);
    }

    public String get_date(String date) {
        String dateParts[] = date.split("/");
        String month = dateParts[0];
        return month;
    }

    public String get_year(String date) {
        String dateParts[] = date.split("/");
        String month = dateParts[2];
        return month;
    }

    //Return Month from number to text with full words
    public String getMonthByCode(String month) {

        if (month.equals("01")) {
            return "January";
        }
        if (month.equals("02")) {
            return "February";
        }
        if (month.equals("03")) {
            return "March";
        }
        if (month.equals("04")) {
            return "April";
        }
        if (month.equals("05")) {
            return "May";
        }
        if (month.equals("06")) {
            return "June";
        }
        if (month.equals("07")) {
            return "July";
        }
        if (month.equals("08")) {
            return "August";
        }
        if (month.equals("09")) {
            return "September";
        }
        if (month.equals("10")) {
            return "October";
        }
        if (month.equals("11")) {
            return "November";
        }
        if (month.equals("12")) {
            return "December";
        } else {
            return null;
        }
    }



    private JEditorPane createEditorPane() {
        editorPane = new JEditorPane();
        editorPane.setEditable(true);
        editorPane.setText("Error BDD lines....");
        return editorPane;
    }

    private JEditorPane createTextPane() {
        textPane = new JEditorPane();
        textPane.setEditable(true);
        textPane.setText("Final FF ");
        return textPane;
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

        //Add content to the window.
        frame.add(new TextSamplerDemoApp());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.TRUE);
                createAndShowGUI();
            }
        });
    }
}
