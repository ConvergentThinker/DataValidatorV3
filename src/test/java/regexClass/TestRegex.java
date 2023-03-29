package regexClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
public class TestRegex {

    public static void main(String[] args) {


        String[] formatsArr = {"Number","Text",
        "D/MM/YY","DD/MM/YYYY","D/M/YY","DD/MM/YY","DD/M/YYYY","DD.M.YYYY","DD-M-YYYY","DD/M/YY",
                "MM/DD/YYYY","MM/DD/YY","MM.DD.YYYY","M.DD.YYYY","MM-DD-YY","MM-DD-YYYY",
                "MM\'DD\'YYYY", "M\'DD\'YYYY","MM/DD/YYYY",""
        };

        JFrame frame = new JFrame("Borders");

        int center = SwingConstants.CENTER;

        JLabel labelFour = new JLabel("TitledBorder", center);
        Border etch = BorderFactory.createEtchedBorder();
        labelFour.setBorder(BorderFactory.createTitledBorder(etch, "Title"));




        frame.setLayout(new GridLayout(3, 2));

        frame.add(labelFour);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);







    }

    // Function to validate the username
    public static boolean isValidDate(String inputStr)
    {
        String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        Pattern p = Pattern.compile(regex);
        if (inputStr == null) {
            return false;
        }
        Matcher m = p.matcher(inputStr);
        return m.matches();
    }





}
