import javax.swing.*;
import java.awt.*;

public class Exercise15 {
    static class circleIcon implements Icon {
        private Color c;
        private int size;

        public circleIcon(Color c) {
            this.c = c;
            this.size = 150;
        }

        public int getIconWidth() {
            return size;
        }

        public int getIconHeight() {
            return size;
        }

        public void paintIcon(Component comp, Graphics grap, int x, int y) {
            grap.setColor(c);
            grap.fillOval(x, y, size, size);
        }

        public void setColor(Color color){
            this.c = color;
        }
    }


    public static void main(String[] args) {
        JFrame window = new JFrame();

        circleIcon circle_icon = new circleIcon(Color.RED);
        JLabel circle = new JLabel(circle_icon);
        window.add(circle, BorderLayout.CENTER);

        JButton redButton = new JButton("Red");
        JButton greenButton = new JButton("Green");
        JButton blueButton = new JButton("Blue");
      
        redButton.addActionListener(event-> {circle_icon.setColor(Color.RED); circle.repaint();});
        greenButton.addActionListener(event-> {circle_icon.setColor(Color.GREEN); circle.repaint();});
        blueButton.addActionListener(event-> {circle_icon.setColor(Color.BLUE); circle.repaint();});

        window.add(redButton);
        window.add(greenButton);
        window.add(blueButton);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setLayout(new FlowLayout());
        window.pack();
        window.setVisible(true);
    }
    
}
