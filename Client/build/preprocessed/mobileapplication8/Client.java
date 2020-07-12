package mobileapplication8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.TextField.*;

public class Client extends MIDlet implements CommandListener {

    private Display display;
    private Form form;
    private Command send;
    private Command exit;
    private Command clear;
    private TextField txt;
    private TextField ans;
    private Thread t;
    private DatagramConnection c;
    private Datagram d;
    private DatagramConnection dgc;
    private InputStream is;
    private OutputStream os;

    public void startApp() {
        display = Display.getDisplay(this);
        form = new Form("Form");

        exit = new Command("Exit", Command.EXIT, 0);
        send = new Command("Send", Command.OK, 0);
        clear = new Command("Clear", Command.OK, 0);

        txt = new TextField("txt", "", 256, 0);
        ans = new TextField("ans", "", 256, 0);

        form.addCommand(exit);
        form.addCommand(send);
        form.addCommand(clear);

        form.append(txt);
        form.append(ans);

        form.setCommandListener(this);
        display.setCurrent(form);

        t = new Thread(new Runnable() {
            public void run() {
                try {
                    String val = txt.getString();
                    c = (DatagramConnection) Connector.open("datagram://127.0.0.1:9000");

                    int len = String.valueOf(val).length();
                    byte[] si = new byte[1];
                    si[0] = (byte) len;
                    c.send(c.newDatagram(si, 1));

                    c.send(c.newDatagram(val.getBytes(), val.getBytes().length));

                    dgc = (DatagramConnection) Connector.open("datagram://:9001");
                    try {
                        Datagram d = dgc.newDatagram(1);
                        dgc.receive(d);
                        int size = (int) d.readByte();
                        Datagram datagram = dgc.newDatagram(size);
                        
                        dgc.receive(datagram);
                        System.out.println(new String(datagram.getData()).trim());
                        ans.setString(new String(datagram.getData()));
                    } finally {
                        dgc.close();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exit) {
            notifyDestroyed();
        }
        if (c == send) {
            t.run();
        }
        if (c == clear) {
            txt.setString("");
            ans.setString("");
        }
    }
}
