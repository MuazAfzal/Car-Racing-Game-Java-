import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingsMenu extends JPanel implements ActionListener {
    private JButton backButton;
    private JSlider volumeSlider;
    private JFrame frame;

    public SettingsMenu(JFrame frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());

        // Title label
        JLabel title = new JLabel("Settings", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        this.add(title, BorderLayout.NORTH);

        // Settings Panel
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Volume Control (JSlider)
        JLabel volumeLabel = new JLabel("Volume:");
        volumeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        settingsPanel.add(volumeLabel);

        volumeSlider = new JSlider(0, 100, 50); // Min: 0, Max: 100, Default: 50
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        settingsPanel.add(volumeSlider);

        // Back Button
        backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 30));
        backButton.addActionListener(this);
        settingsPanel.add(backButton);

        this.add(settingsPanel, BorderLayout.CENTER);

        this.setBackground(Color.DARK_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Go back to main menu
            MainMenu mainMenu = new MainMenu(frame);
            frame.setContentPane(mainMenu);
            frame.revalidate();  // Refresh the frame to show main menu
            frame.repaint();  // Repaint the frame
        }
    }
}
