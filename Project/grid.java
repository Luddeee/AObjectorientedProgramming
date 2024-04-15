package Project;
import javax.swing.*;
import java.awt.*;

public class grid {
    JFrame frame = new JFrame("Minesweeper");

    JPanel northTextPanel = new JPanel();
    JLabel northText = new JLabel();

    JPanel eastTextPanel = new JPanel();
    JLabel eastText = new JLabel();

    JPanel southTextPanel = new JPanel();
    JLabel southText = new JLabel();

    JPanel westTextPanel = new JPanel();
    JLabel westText = new JLabel();

    JPanel bottomPanel = new JPanel();
    JButton closeButton = new JButton("Confirm");

    JPanel centerPanel = new JPanel();

    grid(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setSize((int)(screenSize.width * 0.6), (int)(screenSize.height*0.6));
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        //North
        northText.setText("Minesweeper");
        northText.setFont(new Font("Arial",Font.BOLD, (int)(frame.getHeight()*0.08)));
        northText.setHorizontalAlignment(JLabel.CENTER);

        northTextPanel.setLayout(new BorderLayout());
        northTextPanel.add(northText);
        northTextPanel.setPreferredSize(new Dimension(frame.getWidth(),(int)(frame.getHeight()*0.1)));
        northTextPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));

        //East
        eastText.setText("Highscore");
        eastText.setFont(new Font("Arial",Font.BOLD, (int)(frame.getWidth()*0.02)));
        eastText.setHorizontalAlignment(JLabel.CENTER);

        eastTextPanel.setLayout(new BorderLayout());
        eastTextPanel.add(eastText);
        eastTextPanel.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2),frame.getHeight()));
        eastTextPanel.setBorder(BorderFactory.createMatteBorder(0,1,0,0, Color.BLACK));

        //South
        southText.setText("Start/stop, buttons");
        southText.setFont(new Font("Arial",Font.BOLD, (int)(frame.getHeight()*0.08)));
        southText.setHorizontalAlignment(JLabel.CENTER);
        closeButton.addActionListener(e -> frame.dispose());
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        

        southTextPanel.setLayout(new BorderLayout());
        southTextPanel.add(southText);
        southTextPanel.add(closeButton, BorderLayout.EAST);
        southTextPanel.setPreferredSize(new Dimension(frame.getWidth(),(int)(frame.getHeight()*0.1)));
        southTextPanel.setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.BLACK));

        //West
        westText.setText("How to play");
        westText.setFont(new Font("Arial",Font.BOLD, (int)(frame.getWidth()*0.02)));
        westText.setHorizontalAlignment(JLabel.CENTER);

        westTextPanel.setLayout(new BorderLayout());
        westTextPanel.add(westText);
        westTextPanel.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2),frame.getHeight()));
        westTextPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,1, Color.BLACK));

        //Center where most of the program lies
        centerPanel.setLayout(new BorderLayout());

        //Adding frame
        frame.add(northTextPanel, BorderLayout.NORTH);
        frame.add(eastTextPanel, BorderLayout.EAST);
        frame.add(southTextPanel, BorderLayout.SOUTH);
        frame.add(westTextPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    //public static void main(String[] args) {
    //    new grid();
    //}

}
