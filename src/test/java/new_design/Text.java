package new_design;

import java.applet.*;
import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.lang.Math;
public class Text extends Applet
{

    //Function to get the list of fonts available
    public void init()
    {
        setBackground(Color.white);


    }
    //Function to draw the text

    public static void main(String[] args) {

        String fonts[];
        GraphicsEnvironment GE;
        GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts = GE.getAvailableFontFamilyNames();

        for (String font:fonts
             ) {
            System.out.println("\""+font+"\",");
        }





    }


}