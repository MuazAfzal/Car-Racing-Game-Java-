//STEP 2

/*

🔍 Explanation:
JFrame is like a window in a desktop app.
We're customizing the window with:
A title ("Simple Racing Game")
Telling it to close when you click the X button
Fixing the size so the player can't stretch it
Adding a custom panel where we will draw the game (from GamePanel)
Making it visible

*/



import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("Simple Racing Game"); // Set the window title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when you close the window
        this.setResizable(false); // Disable window resizing
        this.add(new GamePanel()); // Add the panel where the game will be drawn
        this.pack(); // Size the window to fit its content
        this.setLocationRelativeTo(null); // Center the window on the screen
        this.setVisible(true); // Make the window visible
    }
}

