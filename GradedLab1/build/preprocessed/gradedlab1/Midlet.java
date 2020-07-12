/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gradedlab1;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.TextField.*;


/**
 * @author vishg
 */
public class Midlet extends MIDlet implements CommandListener {
    private Display display;
    private Form mainform;
    private Form operation;
    private Command exit;
    private Command select;
    private Command result;
    private Command back;
    private ChoiceGroup cg;
    private TextField num1;
    private TextField num2;
    private TextField res;

    public void startApp() {
        display = Display.getDisplay(this);
        mainform = new Form("Main Form");
        exit = new Command("Exit", Command.EXIT, 0);
        select = new Command("Select", Command.OK, 0);
        
        cg = new ChoiceGroup("Operation", Choice.EXCLUSIVE);
        cg.append("Add (x+y)", null);
        cg.append("Subtract (x-y)", null);
        cg.append("Multiply (x*y)", null);
        cg.append("Divide (x/y)", null);
        
        mainform.append(cg);
        mainform.addCommand(exit);
        mainform.addCommand(select);
        mainform.setCommandListener(this);
        display.setCurrent(mainform);
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
            display.setCurrent(mainform);
        }
        
        if(c==select){
            display = Display.getDisplay(this);
            operation = new Form("Operation");
            back = new Command ("Back", Command.BACK, 0);
            result = new Command ("Result", Command.OK, 0);
            
            num1 = new TextField("Num1", "x", 10, TextField.ANY);
            num2 = new TextField("Num2", "y", 10, TextField.ANY);
            res = new TextField("Result", "", 30, TextField.ANY);
            
            operation.append(num1);
            operation.append(num2);
            operation.addCommand(back);
            operation.addCommand(result);
            operation.setCommandListener(this);
            display.setCurrent(operation);
        }
        
        if(c==result){
                        
            if(cg.getString(cg.getSelectedIndex()) == "Add (x+y)"){
                System.out.println("Add selected");
                float answer = Float.parseFloat(num1.getString()) + Float.parseFloat(num2.getString());
                
                res.setString(""+(answer));
                operation.append(res);
                
            }
            if(cg.getString(cg.getSelectedIndex()) == "Subtract (x-y)"){
//                System.out.println("Add selected");
                float answer = Float.parseFloat(num1.getString()) - Float.parseFloat(num2.getString());
                
                res.setString(""+(answer));
                operation.append(res);
                
            }
            if(cg.getString(cg.getSelectedIndex()) == "Multiply (x*y)"){
//                System.out.println("Add selected");
                float answer = Float.parseFloat(num1.getString()) * Float.parseFloat(num2.getString());
                
                res.setString(""+(answer));
                operation.append(res);
                
            }
            if(cg.getString(cg.getSelectedIndex()) == "Divide (x/y)"){
//                System.out.println("Add selected");
                if(Float.parseFloat(num2.getString()) == 0){
                    res.setString("Cannot divide by 0");
                }
                else{
                    float answer = Float.parseFloat(num1.getString()) + Float.parseFloat(num2.getString());
                    res.setString(""+(answer));
                }
                operation.append(res);
                
            }
        }
    }
}
/*
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
*/