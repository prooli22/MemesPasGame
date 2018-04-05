package ca.umontreal.mpg.memepasgame.helpers;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by provosto on 18-04-04.
 */

public class MemeText {

    public EditText editText;
    public TextView textView;

    public MemeText(EditText et, TextView tv){
        editText = et;
        textView = tv;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
