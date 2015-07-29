package com.uestc.mymoa.common.view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.uestc.mymoa.R;

/**
 * 自定义的加载对话框
 *
 * @author chao
 */
public class LoadingDialog extends Dialog {
    private LayoutParams lp;
    private TextView text;

    public LoadingDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_loading, null);
        text = (TextView) view.findViewById(R.id.loadingText);
        setContentView(view);

        lp = getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);
    }

    public void setLoadingText(String text) {
        this.text.setText(text);
    }

}
