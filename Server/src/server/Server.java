package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class Server extends MIDlet implements CommandListener {

    private Display display;
    private Form operationForm;
    private ChoiceGroup cgPrefs;
    private Command exit, clear;
    private TextField txt1, txtResult;
    private static final String[] operations = {"Addition", "Subtraction", "Multiplication", "Division"};
    String curOp; // String which shows the chosen option

    DatagramConnection conn;
    StreamConnection sc;
    InputStream ins;
    OutputStream ous;

    public void startApp() {
        // Init settings
        display = Display.getDisplay(this);
        operationForm = new Form("");

        // Commands
        exit = new Command("Exit", Command.EXIT, 0);
        clear = new Command("Clear", Command.OK, 0);

        // CGPrefs
        cgPrefs = new ChoiceGroup("Calculator", ChoiceGroup.EXCLUSIVE);
        for (int i = 0; i < operations.length; i++) {
            cgPrefs.append(operations[i], null);
        }

        // Textbox setup on operationForm
        txt1 = new TextField("Op1", "", 256, TextField.ANY);
        txtResult = new TextField("Result", "", 256, TextField.ANY);

        operationForm.append(txt1);
        operationForm.append(txtResult);

        // Commands set-up for mainForm
        operationForm.addCommand(exit);
        operationForm.addCommand(clear);

        operationForm.setCommandListener(this);

        display.setCurrent(operationForm);

        Thread t;
        t = new Thread(
                new Runnable() {

                    public void run() {
                        try {
                            conn = (DatagramConnection) Connector.open("datagram://:9000");
                            while (true) {
                                System.out.println("Waiting..");
                                StringBuffer sb1 = new StringBuffer();
                                StringBuffer sb2 = new StringBuffer();
                                float si1 = 0;
                                float si2 = 0;
                                Datagram d = conn.newDatagram(1);
                                conn.receive(d);
                                int size=(int) d.readByte();
                                Datagram dgram = conn.newDatagram(size);
                                System.out.println("Payload size : "+size);
                                System.out.println("Receiving");
                                conn.receive(dgram);
                                System.out.println("Received");
                                System.out.println(new String(dgram.getData()));
                                String data = new String(dgram.getData());

                                //                                dgram.readByte();
                                boolean div = false;
                                for (int i = 0; i < data.length(); i++) {
                                    char ch = data.charAt(i);
                                    if (Character.isDigit((char) ch)) {
                                        if (!div) {
                                            sb1.append((char) ch);
                                        } else {
                                            sb2.append((char) ch);
                                        }
                                    } else {
                                        switch (ch) {
                                            case 'A':
                                                curOp = "Addition";
                                                break;
                                            case 'S':
                                                curOp = "Subtraction";
                                                break;
                                            case 'D':
                                                curOp = "Division";
                                                break;
                                            case 'M':
                                                curOp = "Multiplication";
                                        }
                                        div = true;
                                    }
                                }
                                si1 = Float.parseFloat(sb1.toString());
                                si2 = Float.parseFloat(sb2.toString());
                                System.out.println(sb1);
                                System.out.println(sb2);
                                System.out.println(curOp);

                                float res = Calc(si1, si2);

                                DatagramConnection dconn = (DatagramConnection) Connector.open("datagram://127.0.0.1:9001");
                                int len = String.valueOf(res).length();
                                byte[] si = new byte[1];
                                si[0] = (byte) len;
                                dconn.send(dconn.newDatagram(si, 1));
                                dconn.send(dconn.newDatagram((String.valueOf(res)).getBytes(), String.valueOf(res).length()));
                            }

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );
        t.start();
    }

    public float Calc(float op1, float op2) {
        float res = 0; // Used for calculations
        txt1.setString(String.valueOf(op1) + " " + curOp + " " + String.valueOf(op2));

        if (curOp.equals(operations[0])) {
            res = op1 + op2;
        } // Subtraction
        else if (curOp.equals(operations[1])) {
            res = op1 - op2;
            System.out.println("Res : " + res);

        } // Multiplication
        else if (curOp.equals(operations[2])) {
            res = op1 * op2;
        } // Division
        else if (curOp.equals(operations[3])) {
            System.out.println("Op1 : " + op1 + " Op2 : " + op2);
            res = op1 / op2;
            System.out.println("Res : " + res);

        }
        txtResult.setString(String.valueOf(res) + ", Sending to Client");

        return res;
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        float op1, op2, res; // Used for calculations

        if (c == exit) {
            destroyApp(true);
            notifyDestroyed();
        }
    }
}
