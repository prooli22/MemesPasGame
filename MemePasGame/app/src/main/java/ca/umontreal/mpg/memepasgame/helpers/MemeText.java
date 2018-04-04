package ca.umontreal.mpg.memepasgame.helpers;

import android.widget.EditText;

/**
 * Created by provosto on 18-04-04.
 */

public class MemeText {

    public EditText editText;
    public int xpos;
    public int ypos;
    public int textSize;

    public MemeText(EditText et){
        editText = et;
        xpos = 0;
        ypos = 0;
        textSize = 20;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}
