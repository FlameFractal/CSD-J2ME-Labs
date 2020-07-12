package mobileapplication7;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.midlet.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;

public class Midlet extends MIDlet implements CommandListener {

    private Display display;
    private Command exit;
    private Form f;

    public void startApp() {
        display = Display.getDisplay(this);
        f = new Form("Networking");
        exit = new Command("Exit", Command.EXIT, 0);
        f.addCommand(exit);
        f.setCommandListener(this);
        display.setCurrent(f);
        new NetworkingThread().run();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exit) {
            notifyDestroyed();
        }
    }

    private class NetworkingThread extends Thread {

        private HttpConnection c;
        private InputStream s;

        public void run() {
            try {
                c = (HttpConnection) Connector.open("http://www.gzip.org/algorithm.txt");
                s = c.openInputStream();

                String str = "";
                int a = 0;

                while ((a = s.read()) != -1) {
                    str = str + (char) a;
                }

                System.out.println(str);
                f.append(str);

            } catch (IOException ex) {
            }
        }

    }
}
