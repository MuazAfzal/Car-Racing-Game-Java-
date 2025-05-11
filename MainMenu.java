import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel implements ActionListener {
    private JButton playButton, settingsButton;
    private JFrame frame;

    public MainMenu(JFrame frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());

        // Title label
        JLabel title = new JLabel("Car Racing Game", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        this.add(title, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Play Button
        playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.PLAIN, 30));
        playButton.addActionListener(this);
        buttonPanel.add(playButton);

        // Settings Button
        settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Arial", Font.PLAIN, 30));
        settingsButton.addActionListener(this);
        buttonPanel.add(settingsButton);

        this.add(buttonPanel, BorderLayout.CENTER);

        this.setBackground(Color.DARK_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            // Start the game (open game panel)
            System.out.println("Play Button Pressed");
            GamePanel gamePanel = new GamePanel();  // Initialize game panel
            frame.setContentPane(gamePanel);  // Set it as the content pane
            frame.revalidate();  // Refresh the frame to show the new content
            frame.repaint();  // Repaint the frame to display the game panel
        } else if (e.getSource() == settingsButton) {
            // Open settings menu
            SettingsMenu settingsMenu = new SettingsMenu(frame);
            frame.setContentPane(settingsMenu);
            frame.revalidate();  // Refresh the frame to show settings menu
            frame.repaint();  // Repaint the frame
        }
    }
}
