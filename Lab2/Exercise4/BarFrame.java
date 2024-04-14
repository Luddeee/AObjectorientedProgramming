import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.event.MouseListener;

/**
  A class that implements an Observer object that displays a barchart view of
  a data model.
*/
public class BarFrame extends JFrame implements ChangeListener, MouseListener
{
   /**
      Constructs a BarFrame object
      @param dataModel the data that is displayed in the barchart
   */
   public BarFrame(DataModel dataModel)
   {
      this.dataModel = dataModel;
      a = dataModel.getData();

      setLocation(0,200);
      setLayout(new BorderLayout());

      Icon barIcon = new Icon()
      {
         public int getIconWidth() { return ICON_WIDTH; }
         public int getIconHeight() { return ICON_HEIGHT; }

         public void paintIcon(Component c, Graphics g, int x, int y)
         {
            Graphics2D g2 = (Graphics2D) g;

            //g2.setColor(Color.red);

            double max =  (a.get(0)).doubleValue();
            for (Double v : a)
            {
               double val = v.doubleValue();
               if (val > max)
                  max = val;
            }

            double barHeight = getIconHeight() / a.size();

            int i = 0;
            for (Double v : a)
            {
               double value = v.doubleValue();

               double barLength = getIconWidth() * value / max;

               Rectangle2D.Double rectangle = new Rectangle2D.Double
                  (0, barHeight * i, barLength, barHeight);
               
               if(i % 2 ==0){
                  g2.setColor(Color.red);
               }
               else{
                  g2.setColor(Color.blue);
               }
               i++;
               g2.fill(rectangle);
            }
         }
      };

      add(new JLabel(barIcon));
      addMouseListener(this);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

   /**
      Called when the data in the model is changed.
      @param e the event representing the change
   */
   public void stateChanged(ChangeEvent e)
   {
      a = dataModel.getData();
      repaint();
   }

   private ArrayList<Double> a;
   private DataModel dataModel;

   private static final int ICON_WIDTH = 200;
   private static final int ICON_HEIGHT = 200;

   boolean pressed = false;

   @Override
   public void mouseClicked(MouseEvent e) {
   }

   @Override
   public void mousePressed(MouseEvent e) {

      //System.out.println(e.getPoint());

      int closestBar = (int) Math.floor((e.getY()-30)/50); //Clesest bar the mouse is to
      if(closestBar >3){
         closestBar = 3;
      }
      //System.out.println(closestBar);
      double max = (a.get(0)).doubleValue();
      for (Double v : a) {
         double val = v.doubleValue();
         if (val > max)
            max = val;
      }
      double xVal = (e.getX()-7)*(max/ICON_WIDTH);
      dataModel.update(closestBar, xVal);
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      pressed = false;
   }

   @Override
   public void mouseEntered(MouseEvent e) {
   }

   @Override
   public void mouseExited(MouseEvent e) {
   }
}
