package Project;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class grid {
    //MineSweeper mineSweeper = new MineSweeper();
    JFrame frame = new JFrame("Minesweeper");

    JPanel northTextPanel = new JPanel();
    JLabel northText = new JLabel();

    JPanel eastTextPanel = new JPanel();
    JLabel eastText = new JLabel();

    JPanel southTextPanel = new JPanel();
    JLabel southText = new JLabel();

    JPanel westTextPanel = new JPanel();
    JLabel westText = new JLabel();

    JButton settingsBtn = new JButton("Settings");
    JButton playBtn = new JButton("Play");
    JButton exitBtn = new JButton("Exit");

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> highscorelist = new JList<>(listModel);
    JScrollPane highscroll = new JScrollPane(highscorelist);

    JPanel mainPanel = new JPanel();

    grid(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setSize((int)(screenSize.width * 0.7), (int)(screenSize.height*0.7));
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
        eastText.setText("Highscores");
        eastText.setFont(new Font("Arial",Font.BOLD, (int)(frame.getWidth()*0.02)));
        eastText.setHorizontalAlignment(JLabel.CENTER);
        
        eastTextPanel.setLayout(new BorderLayout());
        eastTextPanel.add(eastText, BorderLayout.NORTH);
        eastTextPanel.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2),frame.getHeight()));
        eastTextPanel.setBorder(BorderFactory.createMatteBorder(0,1,0,0, Color.BLACK));

        //South
        playBtn.addActionListener(e -> MineSweeper.load());
        settingsBtn.addActionListener(e -> new settings());
        exitBtn.addActionListener(e -> frame.dispose());
        

        southTextPanel.setLayout(new BorderLayout());
        southTextPanel.add(playBtn, BorderLayout.CENTER);
        southTextPanel.add(settingsBtn, BorderLayout.WEST);
        southTextPanel.add(exitBtn,BorderLayout.EAST);
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
       // centerPanel.setLayout(new BorderLayout());

        //Adding frame
        frame.add(northTextPanel, BorderLayout.NORTH);
        frame.add(eastTextPanel, BorderLayout.EAST);
        frame.add(southTextPanel, BorderLayout.SOUTH);
        frame.add(westTextPanel, BorderLayout.WEST);
        //frame.add(MineSweeper.getMainPanel(), BorderLayout.CENTER);
        
        loadHighScores("Project/Highscores.txt"); //This may need to be changed before exporting project
        frame.setVisible(true);
    }

    private void loadHighScores(String path){
        highscroll.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2),frame.getHeight()));
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String item = scanner.nextLine();
                listModel.addElement(item);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading or loading file");
            System.out.println("Current working directory: " + new File(".").getAbsolutePath());
        }
        eastTextPanel.add(highscroll, BorderLayout.CENTER);
    }

    /*void addMainPanel(){
        mainPanel = MineSweeper.getMainPanel();
    }*/
}
