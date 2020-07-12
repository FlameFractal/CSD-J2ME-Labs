/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplication2;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.*;

/**
 * @author vishg
 */
public class Midlet extends MIDlet {

    public void startApp() {
        Form f = new Form("My form");
        Display display = Display.getDisplay(this);
        f.append("Hello world");
        display.setCurrent(f);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
