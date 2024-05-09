package Project;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class grid implements Observer{
    //MineSweeper mineSweeper;

    private static grid instance;

    HowTo howTo;
    JFrame frame = new JFrame("Minesweeper");
    PrevGameState prevGameState;

    JPanel northTextPanel = new JPanel();
    JLabel northText = new JLabel();

    JPanel eastTextPanel = new JPanel();
    JLabel eastText = new JLabel();

    JPanel southTextPanel = new JPanel();
    JLabel southText = new JLabel();

    JPanel westTextPanel = new JPanel();
    JLabel westText = new JLabel();

    JButton settingsBtn = new JButton("Settings");
    JButton restartBtn = new JButton("Restart");
    JButton exitBtn = new JButton("Exit");
    JButton replayDebugBtn = new JButton("Replay/Debug");
    JButton newButton = new JButton("Cheat/Hint");

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> highscorelist = new JList<>(listModel);
    JScrollPane highscroll = new JScrollPane(highscorelist);

    JPanel mainPanel = new JPanel();

    public static grid getInstance() {
        return instance;
    }

    grid(MineSweeper mineSweeper){
        //mineSweeper = new MineSweeper();
        instance = this;
        mineSweeper.registerObserver(this);

        prevGameState = new PrevGameState(mineSweeper);
        mineSweeper.gridActive = true;

        howTo = new HowTo();
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
        restartBtn.addActionListener(e -> mineSweeper.restart());
        settingsBtn.addActionListener(e -> new settings(mineSweeper));
        //exitBtn.addActionListener(e -> System.exit(0));
        replayDebugBtn.addActionListener(e -> prevGameState.printCapturedStates());
        newButton.addActionListener(e -> mineSweeper.revealBombPosition()); // Add functionality to Button 4
        
        restartBtn.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2), (int)(frame.getHeight()*0.07)));
        settingsBtn.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2), (int)(frame.getHeight()*0.07))); 
        newButton.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2), (int)(frame.getHeight()*0.07)));
        replayDebugBtn.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2), (int)(frame.getHeight()*0.07)));
        
        southTextPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Using FlowLayout with horizontal center alignment and spacing
        southTextPanel.add(settingsBtn); // Add settings button
        southTextPanel.add(restartBtn); // Add restart button
        southTextPanel.add(newButton); // Add newButton
        southTextPanel.add(replayDebugBtn); // Add replayDebugBtn
        southTextPanel.setPreferredSize(new Dimension(frame.getWidth(), (int)(frame.getHeight()*0.1)));
        southTextPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        //West
        westText.setText("How to play");
        westText.setFont(new Font("Arial",Font.BOLD, (int)(frame.getWidth()*0.02)));
        westText.setHorizontalAlignment(JLabel.CENTER);

        westTextPanel.setLayout(new BorderLayout());
        westTextPanel.add(westText, BorderLayout.NORTH);
        westTextPanel.setPreferredSize(new Dimension((int)(frame.getWidth()*0.2),frame.getHeight()));
        westTextPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,1, Color.BLACK));
        westTextPanel.add((howTo.getHowToPanel(frame.getHeight())),BorderLayout.CENTER);

        //Adding to frame
        frame.add(northTextPanel, BorderLayout.NORTH);
        frame.add(eastTextPanel, BorderLayout.EAST);
        frame.add(southTextPanel, BorderLayout.SOUTH);
        frame.add(westTextPanel, BorderLayout.WEST);
        frame.add(mineSweeper.getMainPanel(), BorderLayout.CENTER);
        
        loadHighScores("Project/HighscoresEasy.txt"); //This may need to be changed before exporting project
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

    public void reloadHighScores(String path) {
        listModel.clear(); // Clear existing high scores
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String item = scanner.nextLine();
                listModel.addElement(item); // Add each high score to the list model
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading or loading file");
            System.out.println("Current working directory: " + new File(".").getAbsolutePath());
        }
        eastTextPanel.revalidate(); // Revalidate the panel to reflect the changes
        eastTextPanel.repaint(); // Repaint the panel
    }

    @Override
    public void update() {
        refreshUI();
    }

    private void refreshUI() {
        frame.repaint();
    }
}
