/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplication9;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.*;

/**
 * @author vishg
 */
public class Midlet extends MIDlet{
    
    MyCanvas canvas;
    Display d;
    
    public void startApp() {
        d = Display.getDisplay(this);
        canvas = new MyCanvas(this);
        d.setCurrent(canvas);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    void exitApp() {
        destroyApp(true);
        notifyDestroyed();
    }

}

class MyCanvas extends Canvas  implements CommandListener{
    
    Midlet midlet;
    Command exit;
    Command showbtn;
    
    int showstring = 0;

    public MyCanvas(Midlet midlet) {
        this.midlet = midlet;
        exit = new Command("Exit", Command.EXIT, 0);
        showbtn = new Command("Show Button", Command.OK, 0);
        addCommand(exit);
        addCommand(showbtn);
        setCommandListener(this);
    }

    protected void paint(Graphics g) {
        int w = 120;
        int h = 50;
        
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if(showstring==1){
        g.setColor(30, 220, 80);
        g.fillRect((getWidth()-w)/2, (getHeight()-h)/2, w, h);
        
        
        //light area
        
        g.setColor(140, 220, 130);
        g.fillRect((getWidth()-w)/2, (getHeight()-h)/2 - 10, w, 10);
        
        g.setColor(140, 220, 130);
        g.fillRect((getWidth()-w)/2 - 10, (getHeight()-h)/2, 10, h);
        
        g.setColor(140, 220, 130);
        g.fillTriangle((getWidth()-w)/2,(getHeight()-h)/2, (getWidth()-w)/2, (getHeight()-h)/2 - 10, (getWidth()-w)/2 - 10 ,(getHeight()-h)/2 - 10);
        
        g.setColor(140, 220, 130);
        g.fillTriangle((getWidth()-w)/2,(getHeight()-h)/2, (getWidth()-w)/2 - 10, (getHeight()-h)/2, (getWidth()-w)/2 - 10 ,(getHeight()-h)/2 - 10);
        
        g.setColor(140, 220, 130);
        g.fillTriangle((getWidth()-w)/2 + w-2,(getHeight()-h)/2, (getWidth()-w)/2 + w-2, (getHeight()-h)/2 - 10, (getWidth()-w)/2 + w + 9 ,(getHeight()-h)/2 - 10);
        
        g.setColor(140, 220, 130);
        g.fillTriangle((getWidth()-w)/2,(getHeight()-h)/2 + h, (getWidth()-w)/2 - 10, (getHeight()-h)/2 +h, (getWidth()-w)/2 -10  ,(getHeight()-h)/2 + h+ 9);


        //dark area
        
        g.setColor(20,150,30);
        g.fillRect((getWidth()-w)/2, (getHeight()-h)/2 + h, w, 10);
        
        g.setColor(20,150,30);
        g.fillRect((getWidth()-w)/2 +w, (getHeight()-h)/2, 10, h);
        
        g.setColor(20,150,30);
        g.fillTriangle((getWidth()-w)/2 + w,(getHeight()-h)/2 + h, (getWidth()-w)/2 + w + 9, (getHeight()-h)/2 +h, (getWidth()-w)/2 + w+9 ,(getHeight()-h)/2 +9 + h);
        
        g.setColor(20,150,30);
        g.fillTriangle((getWidth()-w)/2 + w,(getHeight()-h)/2 + h, (getWidth()-w)/2 +w, (getHeight()-h)/2 + h + 9, (getWidth()-w)/2 + w+ 9 ,(getHeight()-h)/2 +h+ 9);
        
        g.setColor(20,150,30);
        g.fillTriangle((getWidth()-w)/2 + w,(getHeight()-h)/2, (getWidth()-w)/2 + w+9, (getHeight()-h)/2, (getWidth()-w)/2 + w + 9 ,(getHeight()-h)/2 - 10);
        
        g.setColor(20,150,30);
        g.fillTriangle((getWidth()-w)/2,(getHeight()-h)/2 + h, (getWidth()-w)/2, (getHeight()-h)/2 +h+9, (getWidth()-w)/2 -10  ,(getHeight()-h)/2 + h+ 10);
        
        
        g.setColor(255, 255, 255);
//        g.setFont(new Font());
        g.drawString("Vishal", getWidth()/2-20,getHeight()/2-10, 0);
        }
    }

    public void commandAction(Command c, Displayable d) {
        System.out.println("Clicked button");
        if(c==exit){
            System.out.println("Exiting");
            midlet.exitApp();
            System.out.println("Exited?");
        }
        
        if(c==showbtn){
            showstring=1;
            repaint();
        }
        
    }
    
}
