/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileapplication3;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author vishg
 */
public class Midlet extends MIDlet implements CommandListener, ItemStateListener {
        
    private Display display;
    private Form form;
    private Form form2;
    private Form form3;
    private Command show;
    private Command exit;
    private Command back;
    private Command image;
    private ChoiceGroup cg;
    
    
    public void startApp() {
        display = Display.getDisplay(this);
        show = new Command("Show", Command.OK, 0);
        exit = new Command("Exit", Command.EXIT, 0);
        form = new Form("Fruit Choice");
        
        cg = new ChoiceGroup("Preferences", Choice.POPUP);
        cg.append("Apple", null);
        cg.append("Kiwi", null);
        cg.append("Banana", null);
        cg.append("Cherries", null);
        
        form.addCommand(show);
        form.addCommand(exit);
        form.append(cg);
        form.setCommandListener(this);
        form.setItemStateListener(this);
        
        
        display.setCurrent(form);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        
    }
    
    public void commandAction(Command c, Displayable d){
        if(c==show){
            showImage();
//            showselecteditems();
        }
        
        if(c==exit){
            destroyApp(true);
            notifyDestroyed();
        }
        
        if(c==back){
            display.setCurrent(form);
        }
        
    }

    public void itemStateChanged(Item item) {
        if(item==cg){
//            System.out.println("You have selected "+cg.getString(cg.getSelectedIndex())+".");
        }
    }
    
    public void showselecteditems(){
        display = Display.getDisplay(this);
        back = new Command("Back", Command.BACK, 0);
        image = new Command("Image", Command.OK, 0);
        form2 = new Form("Show Item");
        form2.addCommand(back);
        form2.addCommand(image);
        form2.append(cg.getString(cg.getSelectedIndex()));
        form2.setCommandListener(this);
        display.setCurrent(form2);
    }
    
    public void showImage(){
        display = Display.getDisplay(this);
        back = new Command("Back", Command.BACK, 0);
        form3 = new Form("Image");
        form3.addCommand(back);
        form3.setCommandListener(this);
        Image img = null;
        try{
            System.out.println(cg.getString(cg.getSelectedIndex())+".png");
            img = Image.createImage(cg.getString(cg.getSelectedIndex())+".png");
            form3.append(new ImageItem(cg.getString(cg.getSelectedIndex()), img, ImageItem.LAYOUT_VCENTER, cg.getString(cg.getSelectedIndex())));
            display.setCurrent(form3);
        }catch (Exception e) {
            System.out.println(e);
            form3.append("Selected item is "+cg.getString(cg.getSelectedIndex())+", cannot display image.");
        }
            display.setCurrent(form3);
        
    }
    
}