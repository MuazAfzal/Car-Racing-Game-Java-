import javax.swing.*;

public class GameApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Racing Game"); // Create a new JFrame with the title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the program when closed
        frame.setSize(400, 600); // Set the window size (height and width) suitable for mobile aspect ratio
        frame.setResizable(false); // Prevent resizing the window

        // Create the main menu as the first screen
        MainMenu mainMenu = new MainMenu(frame);
        frame.setContentPane(mainMenu); // Set the main menu as the content pane of the frame
        frame.setVisible(true); // Make the frame visible
    }
}
