/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gradedlab2;

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
    int x,y, h=40, w=40, step = 20;
    Thread t;
    
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
        g.fillRect(x, y, w, h);
    }
    
    /* change step size to 1 */
    
    protected void keyPressed(int keyCode){
        if(keyCode == 50 || keyCode ==  -1){ /*2*/
////            createthread(x, y-step);
//            t = new Thread(new Runnable() {
//            public void run() {
//                while (true) {
                    y = y - step;
//                    repaint();
//                    }
//                }
//            });
//            t.start();
            if(y<=0){
                y = y + 2*step;
            }
        }
        if(keyCode == 54 || keyCode ==  -4){ /*6*/
            x = x + step;
        }
        if(keyCode == 56 || keyCode ==  -2){ /*8*/
            y = y + step;
        }
        if(keyCode == 52 || keyCode ==  -3){ /*4*/
            x = x - step;
            if(x<=0){
                x = x + 2*step;
            }
        }
        if(keyCode == 49){ /*1*/
            y = y - step;
            x = x - step;
            if(y<=0 || x<=0){
                x = x - step;
                y = y + step;
            }
        }
        if(keyCode == 51){ /*3*/
            x = x + step;
            y = y - step;
        }
        if(keyCode == 55){ /*7*/
            x = x - step;
            y = y + step;
            if(x<=0){
                y = y - 2*step;
            }
        }
        if(keyCode == 57){ /*9*/
            x = x + step;
            y = y + step;
        }
        if(keyCode == 53){ /*5*/
//            t.interrupt();
        }
        
        
        if(y<0){
                y = 0;
        }
        if(x>getWidth()-w){
                x = getWidth()-w;
        }
        if(y>getHeight()-h){
                y = getHeight()-h;
        }
        if(x<0){
                x = 0;
        }        
        this.repaint();
        
    }
    
    public void createthread(final int newx, final int newy){
        
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmExit) {
            midlet.destroyApp(true);
        }
    }
}