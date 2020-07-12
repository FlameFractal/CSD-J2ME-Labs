/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockmover;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author Saketh Vallakatla
 */
public class DrawBlockMidlet extends MIDlet {

    private Display display; // The display
    private MyCanvas canvas; // Canvas

    public DrawBlockMidlet() {
        display = Display.getDisplay(this);
        canvas = new MyCanvas(this);

    }

    public void startApp() {
        display.setCurrent(canvas);

    }

    public void pauseApp() {

    }

    public void destroyApp(boolean unconditional) {
    }

    public void exitMIDlet() {
        destroyApp(true);
        notifyDestroyed();
    }
}