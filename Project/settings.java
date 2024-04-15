package Project;
import java.awt.*;
import javax.swing.*;

public class settings {
    JDialog frame1 = new JDialog();

    JPanel northTextPanel = new JPanel();
    JLabel northText = new JLabel();

    JPanel westTextPanel = new JPanel();
    JLabel westText = new JLabel();
    JPanel eastTextPanel = new JPanel();
    JLabel eastText = new JLabel();

    JPanel contentPanel = new JPanel();

    JPanel bottomPanel = new JPanel();
    JButton closeButton = new JButton("Confirm");

    JPanel panel = new JPanel();
    JComboBox<String> difficulty;
    JComboBox<String> opt;

    settings(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame1.setSize((int)(screenSize.width * 0.2), (int)(screenSize.height*0.3));
        frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        frame1.setResizable(false);

        //North
        northText.setText("Settings");
        northText.setFont(new Font("Arial",Font.BOLD, (int)(frame1.getHeight()*0.05)));
        northText.setHorizontalAlignment(JLabel.CENTER);

        northTextPanel.setLayout(new BorderLayout());
        northTextPanel.add(northText);
        northTextPanel.setPreferredSize(new Dimension(frame1.getWidth(),(int)(frame1.getHeight()*0.1)));
        northTextPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));

        //west, bufferzone
        westText.setHorizontalAlignment(JLabel.CENTER);
        westTextPanel.setLayout(new BorderLayout());
        westTextPanel.setPreferredSize(new Dimension((int)(frame1.getWidth()*0.2),frame1.getHeight()));

        //East, Bufferzone
        eastText.setHorizontalAlignment(JLabel.CENTER);
        eastTextPanel.setLayout(new BorderLayout());
        eastTextPanel.setPreferredSize(new Dimension((int)(frame1.getWidth()*0.2),frame1.getHeight()));

        //Center, with the content
        String[] difficultyopt = {"Easy", "Medium", "Hard"};
        difficulty = new JComboBox<>(difficultyopt);

        String[] opti = {"opt1", "opt2", "opt3"};
        opt = new JComboBox<>(opti);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(difficulty,BorderLayout.NORTH);
        contentPanel.add(opt, BorderLayout.CENTER);

        //South
        closeButton.addActionListener(e -> {
            MineSweeper.restart();
            frame1.dispose();
        });
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(closeButton);
        
        frame1.add(northTextPanel, BorderLayout.NORTH);
        frame1.add(westTextPanel, BorderLayout.WEST);
        frame1.add(eastTextPanel, BorderLayout.EAST);
        frame1.add(bottomPanel, BorderLayout.SOUTH);
        frame1.add(contentPanel, BorderLayout.CENTER);
        frame1.setVisible(true);
        frame1.setAlwaysOnTop(true);
    }
}
