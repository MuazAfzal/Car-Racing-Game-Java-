//STEP 1

/*
🔍 Explanation:

This is the main entry point of the Java program.
public static void main(String[] args) is where the program starts running.
new GameFrame(); creates a new window for the game. It's like saying, “Show the game screen.”

*/


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Game");
        GamePanel gamePanel = new GamePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
    }
}


