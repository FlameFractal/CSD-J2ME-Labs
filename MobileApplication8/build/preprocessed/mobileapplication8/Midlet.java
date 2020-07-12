package mobileapplication8;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Midlet extends MIDlet {

    Display d;
    MyCanvas canvas;

    Midlet() {
        canvas = new MyCanvas(this);
    }

    public void startApp() {
        d = Display.getDisplay(this);
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

class MyCanvas extends Canvas implements CommandListener {

    int arcR, arcG, arcB, spinSpeed, d;
    Command exit, stop;
    Midlet midlet;
    Thread t;

    public MyCanvas(Midlet midlet) {
        this.midlet = midlet;
        exit = new Command("Exit", Command.EXIT, 0);
        stop = new Command("Stop", Command.OK, 0);
        addCommand(exit);
        addCommand(stop);
        setCommandListener(this);

        arcR = -30;
        arcG = -150;
        arcB = 90;
        spinSpeed = 0;

        t = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        rotateCircle();
                        Thread.sleep(10);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void paint(Graphics g) {
        
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(255, 0, 0);
        g.fillArc(getWidth() / 2 - 100 / 2, getHeight() / 2 - 100 / 2, 100, 100, -arcR, 120);
        g.setColor(0, 255, 0);
        g.fillArc(getWidth() / 2 - 100 / 2, getHeight() / 2 - 100 / 2, 100, 100, -arcG, 120);
        g.setColor(0, 0, 255);
        g.fillArc(getWidth() / 2 - 100 / 2, getHeight() / 2 - 100 / 2, 100, 100, -arcB, 120);
//g.drawString(Integer.toString(spinSpeed), getWidth()-30, getHeight()-30, 0);
//        if(spinSpeed<20)
//            g.drawString(Integer.toString(spinSpeed), spinSpeed+5, 40, 0);
//        else if(spinSpeed>215)   
//            g.drawString(Integer.toString(spinSpeed), 215, 40, 0);
//        else 
//            g.drawString(Integer.toString(spinSpeed), spinSpeed, 40, 0);
//        
//        for(int i=0;i<spinSpeed;i++){
//            g.setColor(i, 255-i, 20);
//            g.fillRect(i, 20, 1, 20);
//        }
    }

    protected void rotateCircle() {
        arcR += spinSpeed;
        arcG += spinSpeed;
        arcB += spinSpeed;
        this.repaint();
    }

    protected void keyPressed(int keyCode) {
        switch (d) {
            case -1:
                spinSpeed += 5;
                if (spinSpeed > getWidth()) {
                    spinSpeed = getWidth();
                }
                break;
            case -2:
                spinSpeed -= 10;
                if (spinSpeed < 0) {
                    spinSpeed = 0;
                }
                break;
        }
        System.out.println("spinSpeed= " + spinSpeed);
        d = keyCode;
    }

    protected void pointerDragged(int x, int y) {
        spinSpeed = x;
        System.out.println("spinSpeed= " + spinSpeed);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exit) {
            midlet.exitApp();
        }
        if (c == stop) {
            spinSpeed = 0;
        }
    }
}
