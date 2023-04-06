package new_design;


    import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;


    public class JTableColoredBorder extends Box{

        public JTableColoredBorder(){
            super(BoxLayout.Y_AXIS);

            JTable table = new JTable(5,5);
            table.setIntercellSpacing(new Dimension(0,0));//Get rid of cell spacing

            //Set your own renderer.  You'll have to set this for Number and Boolean too if you're using those
            CustomRenderer cr = new CustomRenderer(table.getDefaultRenderer(Object.class), Color.red, Color.orange, Color.pink, Color.magenta);
            table.setDefaultRenderer(Object.class, cr);

            add(table);
        }

        //Custom renderer - do what the natural renderer would do, just add a border
        public static class CustomRenderer implements TableCellRenderer{
            TableCellRenderer render;
            Border b;
            public CustomRenderer(TableCellRenderer r, Color top, Color left,Color bottom, Color right){
                render = r;

                //It looks funky to have a different color on each side - but this is what you asked
                //You can comment out borders if you want too. (example try commenting out top and left borders)
                b = BorderFactory.createCompoundBorder();
                b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(2,0,0,0,top));
                b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,2,0,0,left));
                b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,2,0,bottom));
                b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,0,2,right));
            }

            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus, int row,
                                                           int column) {
                JComponent result = (JComponent)render.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                result.setBorder(b);
                return result;
            }

        }

        /**
         * @param args
         */
        public static void main(String[] args) {

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new JTableColoredBorder());
            frame.validate();
            frame.pack();
            frame.setVisible(true);
        }

    }





