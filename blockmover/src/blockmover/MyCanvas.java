/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockmover;

import javax.microedition.lcdui.*;

public class MyCanvas extends Canvas implements
        CommandListener {

    private Command cmExit; // Exit midlet
    private DrawBlockMidlet midlet;

    private int xPos, yPos, step = 10, sqrWidth = 5;
    int direction = 53;
    boolean ref = false;

    Thread moveThread;

    public MyCanvas(DrawBlockMidlet midlet) {
        this.midlet = midlet;
// Create exit command & listen for events
        cmExit = new Command("Exit", Command.EXIT, 1);
        addCommand(cmExit);
        setCommandListener(this);
        this.xPos = getWidth() / 2 - sqrWidth / 2;
        this.yPos = getHeight() / 2 - sqrWidth / 2;

        moveThread = new Thread(
                new Runnable() {
                    public void run() {
                        try {
                            while (true) {
                                moveSquare(direction);
                                Thread.sleep(40);
                            }
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        moveThread.start();
    }

    protected void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0, 0, 0);
        g.fillRect(xPos, yPos, sqrWidth, sqrWidth);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmExit) {
            midlet.exitMIDlet();
        }
    }

    void reflect(int dir) { // dir = 0 for xDir, dir = 1 for yDir
        switch (direction) {
            case 50:
                direction = 56;
                break;
            case 56:
                direction = 50;
                break;
            case 54:
                direction = 52;
                break;
            case 52:
                direction = 54;
                break;
            case 49:
                if (dir == 0) {
                    direction = 51;
                } else {
                    direction = 55;
                }
                break;
            case 57:
                if (dir == 0) {
                    direction = 55;
                
                } else {
                    direction = 51;
                }
                break;
            case 51:
                if (dir == 0) {
                    direction = 49;
                } else {
                    direction = 57;
                }
                break;
            case 55:
                if (dir == 0) {
                    direction = 57;
                } else {
                    direction = 49;
                }
                break;
        }
    }

    void moveSquare(int keyCode) {
        switch (keyCode) {
            case 50: // Up
                if (yPos - step < 0) {
                    yPos = 0;
                    repaint();
                    reflect(1);
                    break;
                }
                yPos -= step;
                break;
            case 56: // Down
                if (yPos + step + sqrWidth > getHeight()) {
                    yPos = getHeight() - sqrWidth;
                    repaint();
                    reflect(1);
                    break;
                }
                yPos += step;
                break;
            case 54: // Right
                if (xPos + step + sqrWidth > getWidth()) {
                    xPos = getWidth() - sqrWidth;
                    repaint();
                    reflect(0);
                    break;
                }
                xPos += step;
                break;
            case 52: // Left
                if (xPos - step < 0) {
                    xPos = 0;
                    repaint();
                    reflect(0);
                    break;
                }
                xPos -= step;
                break;

            case 49: // Top-Left
                moveSquare(50); // Top
                moveSquare(52); // Left
                break;
            case 51: // Top-Right
                moveSquare(50); // Top
                moveSquare(54); //Right
                break;
            case 55: // Bottom-Left
                moveSquare(56); // Bottom
                moveSquare(52); // Left
                break;
            case 57: // Bottom-Right
                moveSquare(56); // Bottom
                moveSquare(54); // Right
                break;
        }
        this.repaint();

//    1: 49, 3:51, 7:55, 9,57
    }

    public void keyPressed(int keyCode) {
        direction = keyCode;
    }
}