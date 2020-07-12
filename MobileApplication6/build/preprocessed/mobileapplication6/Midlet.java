/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplication6;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author vishg
 */
public class Midlet extends MIDlet {

    private Display display; // The display
    private myCanvas canvas; // Canvas

    public Midlet() {
        display = Display.getDisplay(this);
        canvas = new myCanvas(this);
    }

    protected void startApp() {
        display.setCurrent(canvas);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}

class myCanvas extends Canvas implements CommandListener{

    private Command cmExit; // Exit midlet
    private Midlet midlet;
    int x,y, h=40, w=40;
    
    public myCanvas(Midlet midlet) {
        this.midlet = midlet;
        x  = (getWidth()-w)/2;
        y = (getHeight()-h)/2;
        // Create exit command & listen for events
        cmExit = new Command("Exit", Command.EXIT, 1);
        addCommand(cmExit);
        setCommandListener(this);
    }

    protected void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0, 0, 0);
        g.drawRect(x, y, w, h);
    }
    
    protected void keyPressed(int keyCode){
        if(keyCode == 50 || keyCode ==  -1){
            y = y - 10;
            if(y<0){
                y = 0;
            }
        }
        if(keyCode == 54 || keyCode ==  -4){
            x = x + 10;
            if(x>getWidth()-w){
                x = getWidth()-w;
            }
        }
        if(keyCode == 56 || keyCode ==  -2){
            y = y + 10;
            if(y>getHeight()-h){
                y = getHeight()-h;
            }
        }
        if(keyCode == 52 || keyCode ==  -3){
            x = x - 10;
            if(x<0){
                x = 0;
            }
        }
        
        this.repaint();
        
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmExit) {
            midlet.destroyApp(true);
        }
    }
}