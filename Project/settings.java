package Project;
import java.awt.*;
import javax.swing.*;

public class settings implements Observer{
    private MineSweeper mineSweeper;
    JDialog frame1 = new JDialog();

    JPanel northTextPanel2 = new JPanel();
    JLabel northText2 = new JLabel();

    JPanel westTextPanel2 = new JPanel();
    JLabel westText2 = new JLabel();
    JPanel eastTextPanel2 = new JPanel();
    JLabel eastText2 = new JLabel();

    JPanel contentPanel2 = new JPanel();

    JPanel bottomPanel2 = new JPanel();
    JButton closeButton2 = new JButton("Confirm");

    JLabel difflabel = new JLabel();
    private JComboBox<String> difficulty;

    JLabel optlabel = new JLabel();
    JComboBox<String> opt;

    JPanel panel = new JPanel();

    @Override
    public void update() {
        // Update UI components based on game state changes
    }
    public settings(MineSweeper mineSweeper){
        this.mineSweeper = mineSweeper;
        mineSweeper.registerObserver(this);
        initializeSettingsUI();
    }

    /**
     * The `initializeSettingsUI` function sets up a user interface for settings in a Java application,
     * including layout, components, and event handling.
     */
    private void initializeSettingsUI() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame1.setSize((int)(screenSize.width * 0.2), (int)(screenSize.height*0.3));
        frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        frame1.setResizable(false);

        //North
        northText2.setText("Settings");
        northText2.setFont(new Font("Arial",Font.BOLD, (int)(frame1.getHeight()*0.05)));
        northText2.setHorizontalAlignment(JLabel.CENTER);

        northTextPanel2.setLayout(new BorderLayout());
        northTextPanel2.add(northText2);
        northTextPanel2.setPreferredSize(new Dimension(frame1.getWidth(),(int)(frame1.getHeight()*0.1)));
        northTextPanel2.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));

        //west, bufferzone
        westText2.setHorizontalAlignment(JLabel.CENTER);
        westTextPanel2.setLayout(new BorderLayout());
        westTextPanel2.setPreferredSize(new Dimension((int)(frame1.getWidth()*0.2),frame1.getHeight()));

        //East, Bufferzone
        eastText2.setHorizontalAlignment(JLabel.CENTER);
        eastTextPanel2.setLayout(new BorderLayout());
        eastTextPanel2.setPreferredSize(new Dimension((int)(frame1.getWidth()*0.2),frame1.getHeight()));

        //Center, with the content
        String[] difficultyopt = {"Easy", "Medium", "Hard"};
        difficulty = new JComboBox<>(difficultyopt);
        difficulty.addActionListener(e -> changeDifficulty());

        String[] opti = {"opt1", "opt2", "opt3"};
        opt = new JComboBox<>(opti);

        difflabel.setText("Difficulty");
        //difflabel.setHorizontalAlignment(JLabel.LEFT);
        optlabel.setText("Options");
        //optlabel.setHorizontalAlignment(JLabel.LEFT);
        contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.Y_AXIS));
        contentPanel2.add(difflabel);
        contentPanel2.add(difficulty);
        contentPanel2.add(optlabel);
        contentPanel2.add(opt);

        //South
        closeButton2.addActionListener(e -> {
            mineSweeper.restart();
            frame1.dispose();
        });
        bottomPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel2.add(closeButton2);
        
        frame1.add(northTextPanel2, BorderLayout.NORTH);
        frame1.add(westTextPanel2, BorderLayout.WEST);
        frame1.add(eastTextPanel2, BorderLayout.EAST);
        frame1.add(bottomPanel2, BorderLayout.SOUTH);
        frame1.add(contentPanel2, BorderLayout.CENTER);
        frame1.setVisible(true);
        frame1.setAlwaysOnTop(true);
    }

    /**
     * The function `changeDifficulty` sets the strategy of a MineSweeper game based on the selected
     * difficulty level.
     */
    private void changeDifficulty(){
        String selectedDifficulty = (String) difficulty.getSelectedItem();
        switch (selectedDifficulty) {
            case "Easy":
                mineSweeper.setStrategy(new EasyStrategy());
                break;
            case "Medium":
                mineSweeper.setStrategy(new MediumStratergy());
                break;
            case "Hard":
                mineSweeper.setStrategy(new HardStratergy());
                break;
        }
    }

    /**
     * The `getDifficulty` function returns the selected difficulty as a String from a dropdown menu.
     * 
     * @return The method `getDifficulty()` is returning the selected item from a dropdown menu named
     * `difficulty` as a String.
     */
    public String getDifficulty() {
        return (String)difficulty.getSelectedItem();
    }
}
