//STEP 3

/*

🔍 Explanation:
GamePanel is where we draw the game (like the road, car, obstacles).
JPanel is a part of the screen we can customize.
Timer is used to keep the game updating frequently (like 100 times per second).
paintComponent(Graphics g) is where all the drawing happens.
We currently draw just one line of text.
repaint() keeps refreshing the screen so it can animate things later (like a car moving).

*/

//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class GamePanel extends JPanel implements ActionListener {
//    final int WIDTH = 400;
//    final int HEIGHT = 600;
//    Timer timer;
//
//    public GamePanel() {
//        this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Set panel size
//        this.setBackground(Color.DARK_GRAY); // Set background color
//        this.setFocusable(true); // Allow keyboard input later
//        timer = new Timer(10, this); // Create a timer that runs every 10 milliseconds
//        timer.start(); // Start the timer
//    }
//
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g); // Clean up previous drawings
//        draw(g); // Draw the game screen
//    }
//
//    public void draw(Graphics g) {
//        g.setColor(Color.WHITE); // Set drawing color to white
//        g.drawString("Racing Game - Work in Progress", 100, 100); // Draw text
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint(); // Tell the screen to update (runs over and over)
//    }
//}




//STEP 5
//**************** UPDATED **********************
/*

We now modify GamePanel to:
Create a PlayerCar object
Add keyboard listeners
Move the car left/right
Draw the car on the screen

 */


//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class GamePanel extends JPanel implements ActionListener, KeyListener {
//    final int WIDTH = 400;
//    final int HEIGHT = 600;
//    Timer timer;
//    PlayerCar player;
//
//    public GamePanel() {
//        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        this.setBackground(Color.DARK_GRAY);
//        this.setFocusable(true);
//        this.addKeyListener(this); // Listen to key events
//
//        player = new PlayerCar(WIDTH / 2 - 25, HEIGHT - 100); // Start car in middle-bottom
//
//        timer = new Timer(10, this); // 100 FPS approx
//        timer.start();
//    }
//
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        draw(g);
//    }
//
//    public void draw(Graphics g) {
//        // Draw road (optional)
//        g.setColor(Color.WHITE);
//        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
//
//        // Draw the player’s car
//        player.draw(g);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint(); // Keep redrawing
//    }
//
//    // KEYBOARD EVENTS BELOW:
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//        if (key == KeyEvent.VK_LEFT) {
//            player.moveLeft();
//        } else if (key == KeyEvent.VK_RIGHT) {
//            player.moveRight(WIDTH);
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {}
//    @Override
//    public void keyTyped(KeyEvent e) {}
//}


//STEP 7

//**************** UPDATED **********************

/*
We’ll now:
Keep a list of obstacles
Spawn new ones every few seconds
Remove them if they go off-screen
Detect collision with the player car
🔁 Update GamePanel.java
*/


//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Random;
//
//public class GamePanel extends JPanel implements ActionListener, KeyListener {
//    final int WIDTH = 400;
//    final int HEIGHT = 600;
//    Timer timer;
//    PlayerCar player;
//    ArrayList<Obstacle> obstacles;
//    Random random;
//    int obstacleTimer;
//    boolean gameOver = false;
//
//    public GamePanel() {
//        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        this.setBackground(Color.DARK_GRAY);
//        this.setFocusable(true);
//        this.addKeyListener(this);
//
//        player = new PlayerCar(WIDTH / 2 - 25, HEIGHT - 100); // Start in center-bottom
//        obstacles = new ArrayList<>();
//        random = new Random();
//        obstacleTimer = 0;
//
//        timer = new Timer(10, this); // Runs every 10 milliseconds (~100 FPS)
//        timer.start();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        draw(g);
//    }
//
//    public void draw(Graphics g) {
//        // Draw center road line
//        g.setColor(Color.WHITE);
//        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
//
//        // Draw player car
//        player.draw(g);
//
//        // Draw all falling obstacles
//        for (Obstacle ob : obstacles) {
//            ob.draw(g);
//        }
//
//        // Show GAME OVER message
//        if (gameOver) {
//            g.setColor(Color.RED);
//            g.setFont(new Font("Arial", Font.BOLD, 32));
//            g.drawString("GAME OVER", WIDTH / 2 - 100, HEIGHT / 2);
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (!gameOver) {
//            // Add new obstacle every 150 timer ticks (~1.5 seconds)
//            obstacleTimer++;
//            if (obstacleTimer > 150) {
//                obstacles.add(new Obstacle(WIDTH));
//                obstacleTimer = 0;
//            }
//
//            // Update each obstacle's position and check for collisions
//            Iterator<Obstacle> iterator = obstacles.iterator();
//            while (iterator.hasNext()) {
//                Obstacle ob = iterator.next();
//                ob.update(); // Move it down
//
//                // Check if it hits the player
//                if (ob.getBounds().intersects(new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight()))) {
//                    gameOver = true;
//                    timer.stop();
//                }
//
//                // Remove obstacle if it goes off screen
//                if (ob.isOffScreen(HEIGHT)) {
//                    iterator.remove();
//                }
//            }
//
//            repaint(); // Refresh the screen
//        }
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (!gameOver) {
//            int key = e.getKeyCode();
//            if (key == KeyEvent.VK_LEFT) {
//                player.moveLeft();
//            } else if (key == KeyEvent.VK_RIGHT) {
//                player.moveRight(WIDTH);
//            }
//        }
//    }
//
//    @Override public void keyReleased(KeyEvent e) {}
//    @Override public void keyTyped(KeyEvent e) {}
//}


//**************** UPDATED **********************


//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Random;
//
//public class GamePanel extends JPanel implements ActionListener, KeyListener {
//    final int WIDTH = 400;
//    final int HEIGHT = 600;
//    Timer timer;
//    PlayerCar player;
//    ArrayList<Obstacle> obstacles;
//    Random random;
//    int obstacleTimer;
//    boolean gameOver = false;
//    int score = 0;
//    int scoreTimer = 0;
//
//    // Road lines
//    int[] lineYPositions;
//    int lineSpeed = 5;
//
//    public GamePanel() {
//        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        this.setBackground(Color.DARK_GRAY);
//        this.setFocusable(true);
//        this.addKeyListener(this);
//
//        initGame();
//    }
//
//    public void initGame() {
//        player = new PlayerCar(WIDTH / 2 - 25, HEIGHT - 100);
//        obstacles = new ArrayList<>();
//        random = new Random();
//        obstacleTimer = 0;
//        gameOver = false;
//        score = 0;
//        scoreTimer = 0;
//
//        // Initialize road lines (5 lines spaced vertically)
//        lineYPositions = new int[]{0, 120, 240, 360, 480};
//
//        timer = new Timer(10, this); // ~100 FPS
//        timer.start();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        draw(g);
//    }
//
//    public void draw(Graphics g) {
//        // Draw animated road lines
//        g.setColor(Color.WHITE);
//        for (int i = 0; i < lineYPositions.length; i++) {
//            g.fillRect(WIDTH / 2 - 5, lineYPositions[i], 10, 60);
//        }
//
//        // Draw player car
//        player.draw(g);
//
//        // Draw obstacles
//        for (Obstacle ob : obstacles) {
//            ob.draw(g);
//        }
//
//        // Draw score
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Arial", Font.PLAIN, 20));
//        g.drawString("Score: " + score, 10, 25);
//
//        // Game Over message
//        if (gameOver) {
//            g.setColor(Color.RED);
//            g.setFont(new Font("Arial", Font.BOLD, 32));
//            g.drawString("GAME OVER", WIDTH / 2 - 100, HEIGHT / 2);
//
//            g.setFont(new Font("Arial", Font.PLAIN, 18));
//            g.setColor(Color.YELLOW);
//            g.drawString("Press ENTER to Restart", WIDTH / 2 - 110, HEIGHT / 2 + 40);
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (!gameOver) {
//            // Score increases every second (every 100 ticks)
//            scoreTimer++;
//            if (scoreTimer >= 100) {
//                score++;
//                scoreTimer = 0;
//            }
//
//            // Animate road lines
//            for (int i = 0; i < lineYPositions.length; i++) {
//                lineYPositions[i] += lineSpeed;
//                if (lineYPositions[i] > HEIGHT) {
//                    lineYPositions[i] = -60;
//                }
//            }
//
//            // Spawn new obstacles every ~1.5 sec
//            obstacleTimer++;
//            if (obstacleTimer > 150) {
//                obstacles.add(new Obstacle(WIDTH));
//                obstacleTimer = 0;
//            }
//
//            // Move obstacles and check collisions
//            Iterator<Obstacle> iterator = obstacles.iterator();
//            while (iterator.hasNext()) {
//                Obstacle ob = iterator.next();
//                ob.update();
//
//                if (ob.getBounds().intersects(new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight()))) {
//                    gameOver = true;
//                    timer.stop();
//                }
//
//                if (ob.isOffScreen(HEIGHT)) {
//                    iterator.remove();
//                }
//            }
//
//            repaint();
//        }
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//
//        if (!gameOver) {
//            if (key == KeyEvent.VK_LEFT) {
//                player.moveLeft();
//            } else if (key == KeyEvent.VK_RIGHT) {
//                player.moveRight(WIDTH);
//            }
//        } else {
//            if (key == KeyEvent.VK_ENTER) {
//                // Restart game
//                initGame();
//            }
//        }
//    }
//
//    @Override public void keyReleased(KeyEvent e) {}
//    @Override public void keyTyped(KeyEvent e) {}
//}


//*********** UPDATED *************

//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Random;
//
//public class GamePanel extends JPanel implements ActionListener, KeyListener {
//    final int WIDTH = 400;
//    final int HEIGHT = 600;
//    Timer timer;
//    PlayerCar player;
//    ArrayList<Obstacle> obstacles;
//    Random random;
//    int obstacleTimer;
//    boolean gameOver = false;
//    boolean isPaused = false;  // New variable to track pause state
//    int score = 0;
//    int scoreTimer = 0;
//
//    // Road lines
//    int[] lineYPositions;
//    int lineSpeed = 5;
//
//    public GamePanel() {
//        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        this.setBackground(Color.DARK_GRAY);
//        this.setFocusable(true);
//        this.addKeyListener(this);
//
//        initGame();
//    }
//
//    public void initGame() {
//        player = new PlayerCar(WIDTH / 2 - 25, HEIGHT - 100);
//        obstacles = new ArrayList<>();
//        random = new Random();
//        obstacleTimer = 0;
//        gameOver = false;
//        score = 0;
//        scoreTimer = 0;
//
//        // Initialize road lines (5 lines spaced vertically)
//        lineYPositions = new int[]{0, 120, 240, 360, 480};
//
//        timer = new Timer(10, this); // ~100 FPS
//        timer.start();
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        draw(g);
//    }
//
//    public void draw(Graphics g) {
//        if (isPaused) {
//            g.setColor(Color.WHITE);
//            g.setFont(new Font("Arial", Font.BOLD, 40));
//            g.drawString("PAUSED", WIDTH / 2 - 80, HEIGHT / 2);
//            return;
//        }
//
//        // Draw animated road lines
//        g.setColor(Color.WHITE);
//        for (int i = 0; i < lineYPositions.length; i++) {
//            g.fillRect(WIDTH / 2 - 5, lineYPositions[i], 10, 60);
//        }
//
//        // Draw player car
//        player.draw(g);
//
//        // Draw obstacles
//        for (Obstacle ob : obstacles) {
//            ob.draw(g);
//        }
//
//        // Draw score
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Arial", Font.PLAIN, 20));
//        g.drawString("Score: " + score, 10, 25);
//
//        // Game Over message
//        if (gameOver) {
//            g.setColor(Color.RED);
//            g.setFont(new Font("Arial", Font.BOLD, 32));
//            g.drawString("GAME OVER", WIDTH / 2 - 100, HEIGHT / 2);
//
//            g.setFont(new Font("Arial", Font.PLAIN, 18));
//            g.setColor(Color.YELLOW);
//            g.drawString("Press ENTER to Restart", WIDTH / 2 - 110, HEIGHT / 2 + 40);
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (isPaused || gameOver) return; // Don't update game if paused or game over
//
//        // Score increases every second (every 100 ticks)
//        scoreTimer++;
//        if (scoreTimer >= 100) {
//            score++;
//            scoreTimer = 0;
//        }
//
//        // Animate road lines
//        for (int i = 0; i < lineYPositions.length; i++) {
//            lineYPositions[i] += lineSpeed;
//            if (lineYPositions[i] > HEIGHT) {
//                lineYPositions[i] = -60;
//            }
//        }
//
//        // Spawn new obstacles every ~1.5 sec
//        obstacleTimer++;
//        if (obstacleTimer > 150) {
//            obstacles.add(new Obstacle(WIDTH));
//            obstacleTimer = 0;
//        }
//
//        // Move obstacles and check collisions
//        Iterator<Obstacle> iterator = obstacles.iterator();
//        while (iterator.hasNext()) {
//            Obstacle ob = iterator.next();
//            ob.update();
//
//            if (ob.getBounds().intersects(new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight()))) {
//                gameOver = true;
//                timer.stop();
//            }
//
//            if (ob.isOffScreen(HEIGHT)) {
//                iterator.remove();
//            }
//        }
//
//        repaint();
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//
//        if (key == KeyEvent.VK_P) {
//            // Toggle pause state
//            isPaused = !isPaused;
//        } else if (!isPaused && !gameOver) {
//            if (key == KeyEvent.VK_LEFT) {
//                player.moveLeft();
//            } else if (key == KeyEvent.VK_RIGHT) {
//                player.moveRight(WIDTH);
//            }
//        } else if (gameOver && key == KeyEvent.VK_ENTER) {
//            // Restart the game when Enter is pressed
//            initGame();
//        }
//    }
//
//    @Override public void keyReleased(KeyEvent e) {}
//    @Override public void keyTyped(KeyEvent e) {}
//}

//********* UPDATED **********



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    final int WIDTH = 360;  // Mobile-like width
    final int HEIGHT = 640; // Mobile-like height
    Timer timer;
    PlayerCar player;
    ArrayList<Obstacle> obstacles;
    Random random;
    int obstacleTimer;
    boolean gameOver = false;
    boolean isPaused = false;
    int score = 0;
    int scoreTimer = 0;

    // Road lines
    int[] lineYPositions;
    int lineSpeed = 5;

    // Car images
    Image playerCarImage;
    Image obstacleCarImage;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(this);

        // Load car images and scale them to fit the screen
        playerCarImage = new ImageIcon("/Users/muaz_afzal/Downloads/Study/Practise/Personal Projects/Car game (Project 1)/car.png").getImage();
        obstacleCarImage = new ImageIcon("/Users/muaz_afzal/Downloads/Study/Practise/Personal Projects/Car game (Project 1)/obs.png").getImage();
        initGame();
    }

    public void initGame() {
        player = new PlayerCar(WIDTH / 2 - 25, HEIGHT - 100, playerCarImage);
        obstacles = new ArrayList<>();
        random = new Random();
        obstacleTimer = 0;
        gameOver = false;
        isPaused = false;
        score = 0;
        scoreTimer = 0;

        // Initialize road lines (5 lines spaced vertically)
        lineYPositions = new int[]{0, 120, 240, 360, 480};

        timer = new Timer(15, this); // Adjusted FPS to 60 (15ms per frame)
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (isPaused) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("PAUSED", WIDTH / 2 - 75, HEIGHT / 2);
            return;
        }

        // Draw animated road lines
        g.setColor(Color.WHITE);
        for (int i = 0; i < lineYPositions.length; i++) {
            g.fillRect(WIDTH / 2 - 5, lineYPositions[i], 10, 60);
        }

        // Draw player car
        player.draw(g);

        // Draw obstacles
        for (Obstacle ob : obstacles) {
            ob.draw(g);
        }

        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 10, 25);

        // Game Over message
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("GAME OVER", WIDTH / 2 - 100, HEIGHT / 2);

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.setColor(Color.YELLOW);
            g.drawString("Press any key to Restart", WIDTH / 2 - 130, HEIGHT / 2 + 40);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !isPaused) {
            // Score increases every second (every 100 ticks)
            scoreTimer++;
            if (scoreTimer >= 100) {
                score++;
                scoreTimer = 0;
            }

            // Animate road lines
            for (int i = 0; i < lineYPositions.length; i++) {
                lineYPositions[i] += lineSpeed;
                if (lineYPositions[i] > HEIGHT) {
                    lineYPositions[i] = -60;
                }
            }

            // Spawn new obstacles every ~1.5 sec
            obstacleTimer++;
            if (obstacleTimer > 150) {
                obstacles.add(new Obstacle(WIDTH, obstacleCarImage)); // Add obstacle cars
                obstacleTimer = 0;
            }

            // Move obstacles and check collisions
            Iterator<Obstacle> iterator = obstacles.iterator();
            while (iterator.hasNext()) {
                Obstacle ob = iterator.next();
                ob.update();

                if (ob.getBounds().intersects(new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight()))) {
                    gameOver = true;
                    timer.stop();
                }

                if (ob.isOffScreen(HEIGHT)) {
                    iterator.remove();
                }
            }

            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) { // Press Escape to pause the game
            isPaused = !isPaused;
        }

        if (!gameOver && !isPaused) {
            if (key == KeyEvent.VK_LEFT) {
                player.moveLeft();
            } else if (key == KeyEvent.VK_RIGHT) {
                player.moveRight(WIDTH);
            }
        } else if (gameOver) {
            // Restart game by pressing any key
            initGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
