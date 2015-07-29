package com.uestc.mymoa.common.view;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.uestc.mymoa.R;

/**
 * Created by chao on 2015/7/29.
 */
public class InputDialog extends AlertDialog {
    private EditText inputEdit;
    public AlertDialog.Builder buider;

    public InputDialog(Context context) {
        super(context);
        buider = new Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_input, null);
        inputEdit = (EditText) view.findViewById(R.id.inputEdit);
        buider.setView(view);
    }

    public void setInputEditHint(String hint){
        inputEdit.setHint(hint);
    }

    public String getInputEditText(){
        return inputEdit.getText().toString();
    }
}
