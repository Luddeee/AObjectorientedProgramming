package Project;
import javax.swing.*;
import java.awt.*;

public class grid {
    JFrame frame = new JFrame("Minesweeper");
    JPanel toptextpanel = new JPanel();
    JLabel temptext = new JLabel();

    grid(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setSize((int)(screenSize.width * 0.6), (int)(screenSize.height*0.6));
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        temptext.setText("Minesweeper");
        temptext.setFont(new Font("Arial",Font.BOLD, (int)(frame.getHeight()*0.08)));
        temptext.setHorizontalAlignment(JLabel.CENTER);

        toptextpanel.setLayout(new BorderLayout());
        toptextpanel.add(temptext);
        toptextpanel.setPreferredSize(new Dimension(frame.getWidth(),(int)(frame.getHeight()*0.1)));
        toptextpanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));

        frame.add(toptextpanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new grid();
    }

}
