//STEP 4

/*

🔍 Explanation:
The car is just a red rectangle.
It starts at the bottom center.
moveLeft() and moveRight() are used to change its position based on arrow key presses.
It includes boundary checks to keep the car on the screen.

*/

//import java.awt.Color;
//import java.awt.Graphics;
//
//public class PlayerCar {
//    private int x, y, width, height, speed;
//
//    public PlayerCar(int startX, int startY) {
//        this.x = startX;
//        this.y = startY;
//        this.width = 50;
//        this.height = 80;
//        this.speed = 5;
//    }
//
//    public void draw(Graphics g) {
//        g.setColor(Color.RED);
//        g.fillRect(x, y, width, height);
//    }
//
//    public void moveLeft() {
//        if (x > 0) x -= speed;
//    }
//
//    public void moveRight(int panelWidth) {
//        if (x + width < panelWidth) x += speed;
//    }
//
//    public int getX() { return x; }
//    public int getY() { return y; }
//    public int getWidth() { return width; }
//    public int getHeight() { return height; }
//}


//************ UPDATED *************

import java.awt.*;
import javax.swing.*;

public class PlayerCar {
    private int x, y;
    private Image image;
    private final int width = 50;  // Player car width
    private final int height = 100; // Player car height

    public PlayerCar(int startX, int startY, Image playerImage) {
        this.x = startX;
        this.y = startY;
        this.image = playerImage;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);  // Draw player car
    }

    public void moveLeft() {
        if (x > 0) x -= 10; // Reduced movement step for smoother movement
    }

    public void moveRight(int screenWidth) {
        if (x + width < screenWidth) x += 10; // Reduced movement step for smoother movement
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
