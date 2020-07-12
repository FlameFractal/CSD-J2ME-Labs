/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileapplication5;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author vishg
 */
public class Midlet extends MIDlet {

    private Display display; // The display
    private myCanvas canvas; // Canvas
    Ticker aticker;

    public Midlet() {
        display = Display.getDisplay(this);
        canvas = new myCanvas(this);
    }

    protected void startApp() {
        display.setCurrent(canvas);
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional
    ) {
    }

    public void exitMIDlet() {
        destroyApp(true);
        notifyDestroyed();
    }
}

class myCanvas extends Canvas implements CommandListener{

    private Command cmExit; // Exit midlet
    private Midlet midlet;
    private String message = "Georgia Southern";

    public myCanvas(Midlet midlet) {
        this.midlet = midlet;   
        // Create exit command & listen for events
        cmExit = new Command("Exit", Command.EXIT, 1);
        addCommand(cmExit);
        setCommandListener(this);
    }

    protected void paint(Graphics g) {
        Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        g.setFont(font);
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0, 0, 0);
        g.drawString(message, getWidth() / 2, getHeight() / 2,
                Graphics.BASELINE | Graphics.HCENTER);
    }
    
    protected void keyPressed(int keyCode){
        message = getKeyName(keyCode) + " " + keyCode;
        this.repaint();
        
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmExit) {
            midlet.exitMIDlet();
        }
    }
}