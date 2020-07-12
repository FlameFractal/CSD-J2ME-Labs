/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplication4;

import java.util.Date;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.TextField.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.Ticker.*;

public class Midlet extends MIDlet implements CommandListener {
    private Display display;
    private Form midlet;
    private Form ticker;
    private Form date;
    private Form textfield;
    private Command exit;
    private Command choose;
    private Command back;
    private Command submitTicker;
    private Command submitText;
    private ChoiceGroup cg;
    private TextField text;
    
    public void startApp() {
        display = Display.getDisplay(this);
        midlet = new Form("Midlet");
        exit = new Command("Exit", Command.EXIT, 0);
        choose = new Command("Choose", Command.OK, 0);
        
        cg = new ChoiceGroup("Preferences", Choice.EXCLUSIVE);
        cg.append("TextField", null);
        cg.append("Date", null);
        cg.append("Ticker", null);
        
        midlet.append(cg);
        midlet.addCommand(exit);
        midlet.addCommand(choose);
        midlet.setCommandListener(this);
        display.setCurrent(midlet);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        
        if(c==exit){
            destroyApp(true);
            notifyDestroyed();
        }
        
        if(c==back){
            display.setCurrent(midlet);
        }
        
        if(c==submitText){
            textfield.append(text.getString());
        }
        
        if(c==submitTicker){
            System.out.println("ticker submitted");
            Ticker aTicker = new Ticker(text.getString());
            ticker.setTicker(aTicker);
        }
        
        if(c==choose){
            if(cg.getString(cg.getSelectedIndex()) == "Ticker"){
                System.out.println("Ticker selected");
                ticker = new Form("Ticker");
                back = new Command("Back", Command.OK, 0);
                submitTicker = new Command("Submit", Command.OK, 0);
                
                text = new TextField("Name", "Type here", 30, TextField.ANY);
                
                ticker.append(text);
                ticker.addCommand(back);
                ticker.addCommand(submitTicker);
                ticker.setCommandListener(this);
                display.setCurrent(ticker);    
            }
            
            if(cg.getString(cg.getSelectedIndex()) == "TextField"){
                textfield = new Form("TextField");
                back = new Command("Back", Command.OK, 0);
                submitText = new Command("Submit", Command.OK, 0);
                
                text = new TextField("Name", "Type here", 30, TextField.ANY);
                
                textfield.addCommand(back);
                textfield.addCommand(submitText);
                textfield.setCommandListener(this);
                display.setCurrent(textfield);    
            }
            
            if(cg.getString(cg.getSelectedIndex()) == "Date"){
                display = Display.getDisplay(this);
                date = new Form("Date");
                back = new Command("Back", Command.OK, 0);
                
                Date today = new Date(System.currentTimeMillis());
                DateField datefield = new DateField("", DateField.DATE_TIME);
                datefield.setDate(today);
                
                date.append(datefield);
                date.addCommand(back);
                date.setCommandListener(this);
                display.setCurrent(date);
            }
        }
    }
}
