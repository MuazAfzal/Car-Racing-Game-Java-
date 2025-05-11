//STEP 6

/*

🔍 Explanation:
Each obstacle is a yellow rectangle that starts at the top and falls down.
update() moves the obstacle down.
isOffScreen() tells us when to remove it.
getBounds() is used for collision detection (we’ll do that soon).

 */

//import java.awt.Color;
//import java.awt.Graphics;
//import java.util.Random;
//
//public class Obstacle {
//    private int x;
//    private int y;
//    private final int width;
//    private int height;
//    private int speed;
//
//    public Obstacle(int panelWidth) {
//        Random rand = new Random();
//        this.width = 50;
//        this.height = 80;
//        this.x = rand.nextInt(panelWidth - width); // Random X position
//        this.y = -height; // Start above the screen
//        this.speed = 4; // Falling speed
//    }
//
//    public void update() {
//        y += speed; // Move obstacle down
//    }
//
//    public void draw(Graphics g) {
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, width, height);
//    }
//
//    public boolean isOffScreen(int panelHeight) {
//        return y > panelHeight;
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle(x, y, width, height);
//    }
//}




//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Rectangle;
//import java.util.Random;
//
//public class Obstacle {
//    private int x, y, width, height, speed;
//
//    public Obstacle(int panelWidth) {
//        Random rand = new Random();
//        this.width = 50;
//        this.height = 80;
//        this.x = rand.nextInt(panelWidth - width); // Random X position
//        this.y = -height; // Start above the screen
//        this.speed = 4; // Falling speed
//    }
//
//    public void update() {
//        y += speed; // Move obstacle down
//    }
//
//    public void draw(Graphics g) {
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, width, height);
//    }
//
//    public boolean isOffScreen(int panelHeight) {
//        return y > panelHeight;
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle(x, y, width, height);
//    }
//}



//************** UPDATED ***********
//with car image

//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Rectangle;
//import javax.swing.ImageIcon;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//
//public class Obstacle {
//    private int x, y, width, height, speed;
//    private Image carImage;
//
//    public Obstacle(int panelWidth) {
//        this.width = 50;
//        this.height = 80;
//        this.x = (int) (Math.random() * (panelWidth - width)); // Random X position
//        this.y = -height; // Start above the screen
//        this.speed = 4; // Falling speed
//
//        try {
//            // Try loading the image
//            carImage = ImageIO.read(new File("car.jpg")); // Make sure car.jpg is in your project folder
//        } catch (IOException e) {
//            e.printStackTrace(); // Print error if image fails to load
//            carImage = new ImageIcon("default_car.png").getImage(); // Load a default car image (if you have one)
//        }
//    }
//
//    public void update() {
//        y += speed; // Move obstacle down
//    }
//
//    public void draw(Graphics g) {
//        g.drawImage(carImage, x, y, width, height, null); // Draw the car image
//    }
//
//    public boolean isOffScreen(int panelHeight) {
//        return y > panelHeight;
//    }
//
//    public Rectangle getBounds() {
//        return new Rectangle(x, y, width, height);
//    }
//}

//*********** UPDATED *************

import java.awt.*;
import javax.swing.*;

public class Obstacle {
    private int x, y;
    private Image image;
    private final int width = 50;  // Reduced the width of the obstacle
    private final int height = 100; // Reduced the height of the obstacle

    public Obstacle(int screenWidth, Image image) {
        this.x = (int) (Math.random() * (screenWidth - width)); // Random x-position within the screen width
        this.y = -height; // Start the obstacle above the screen
        this.image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale the image to fit the new size
    }

    public void update() {
        y += 5; // Move down the screen by 5 pixels per tick
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null); // Draw the scaled obstacle image
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); // Adjust bounds to the new size
    }

    public boolean isOffScreen(int screenHeight) {
        return y > screenHeight; // Check if the obstacle is off the screen
    }
}
