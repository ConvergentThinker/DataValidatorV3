package org.main.jTable;

import javax.swing.*;
import java.awt.*;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(255,255,255));
        setBackground(new Color(30, 30, 30));
    }
}
