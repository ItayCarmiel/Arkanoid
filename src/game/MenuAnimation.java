package game;
import java.awt.Color;
import java.util.ArrayList;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import object.Block;

/**
 * ass 07.
 * @author Itay Carmiel 208464198 <itaycar875@gmail.com>
 * @param <T> - type
 */
public class MenuAnimation<T> implements Menu<T>, Animation {
    private ArrayList<String> keysList;
    private ArrayList<String> messagesList;
    private ArrayList<T> returnValue;
    private T status;
    private boolean stop;
    private KeyboardSensor keyboard;
    private ArrayList<Menu<T>> returnValueMenu;
    private ArrayList<String> menuKeysList;
    private ArrayList<String> menuMessagesList;
    private Menu<T> menuStatus;
    private String title;
    private Color colorMenu;
    private Block background;
    /**
     * constructor.
     * @param keyboard the keyboard sensor
     * @param title the title of the menu
     */
    public MenuAnimation(KeyboardSensor keyboard, String title) {
        this.keysList = new ArrayList<String>();
        this.messagesList = new ArrayList<String>();
        this.returnValue = new ArrayList<T>();
        this.stop = false;
        this.keyboard = keyboard;
        this.status = null;
        this.menuStatus = null;
        this.menuKeysList = new ArrayList<String>();
        this.menuMessagesList = new ArrayList<String>();
        this.returnValueMenu = new ArrayList<Menu<T>>();
        this.title = title;
    }
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keysList.add(key);
        this.messagesList.add(message);
        this.returnValue.add(returnVal);
    }

    @Override
    public T getTaskStatus() {
        return this.status;
    }

    @Override
    public Menu<T> getMenuStatus() {
        return this.menuStatus;
    }

    @Override
    public String whatToRun() {
        if (this.status == null && this.menuStatus != null) {
            return "m";
        } else {
            return "t";
        }
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        int i = 1;
        colorMenu = new Color(200, 100, 200);
        ColorBackGround cb = new ColorBackGround(colorMenu);
        cb.drawOn(d);
        d.setColor(Color.blue);
        d.drawText(400 - (title.length() * 20) / 2, 100, title, 32);
        d.setColor(java.awt.Color.RED);
        d.drawText(400 - (title.length() * 20) / 2 + 3, 100, title, 32);
        d.setColor(Color.blue);
        if (this.keysList.size() == 1 && this.menuKeysList.size() == 0) {
            d.drawText(280, 200,
                    "(" + this.keysList.get(0) + "):", 32);
            d.drawText(320, 200,
                    this.messagesList.get(0), 32);
        }
        if (this.keysList.size() == 0 && this.menuKeysList.size() == 1) {
            d.drawText(280, 200,
                    "(" + this.menuKeysList.get(0) + "):", 32);
            d.drawText(320, 200,
                    this.menuMessagesList.get(0), 32);
        } else {
            // print menus
            for (String tempKey : this.menuKeysList) {
                d.drawText(280, (d.getHeight() - 100)
                                / (this.keysList.size() + this.menuKeysList.size()) * i,
                        "(" + tempKey + "):     ", 32);
                d.drawText(320, (d.getHeight() - 100)
                                / (this.messagesList.size()
                                + this.menuMessagesList.size()) * i,
                        this.menuMessagesList.get(i - 1), 32);
                i++;
            }
        }
        // print tasks
        int j = 1;
        for (String tempKey : this.keysList) {
            d.drawText(280, (d.getHeight() - 100)
                            / (this.keysList.size() + this.menuKeysList.size()) * i,
                    "(" + tempKey + "):", 32);
            d.drawText(350, (d.getHeight() - 95)
                            / (this.messagesList.size()
                            + this.menuMessagesList.size()) * i,
                    this.messagesList.get(j - 1), 32);
            i++;
            j++;
        }
        // check if a task key is pressed
        i = 0;
        for (String tempKey : this.keysList) {
            if (this.keyboard.isPressed(tempKey)) {
                this.stop = true;
                this.status = this.returnValue.get(i);
                return;
            }
            i++;
        }
        //check if a sub menu key is pressed
        i = 0;
        for (String tempKey : this.menuKeysList) {
            if (this.keyboard.isPressed(tempKey)) {
                this.stop = true;
                this.menuStatus = this.returnValueMenu.get(i);
                return;
            }
            i++;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
