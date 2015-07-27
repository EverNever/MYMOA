package com.uestc.mymoa;

/**
 * Created by HeGang on 2015/7/27.
 */

import java.util.Calendar;



import android.app.Activity;

import android.app.DatePickerDialog;

import android.app.Dialog;

import android.os.Bundle;

import android.os.Handler;

import android.os.Message;

import android.view.View;

import android.widget.Button;

import android.widget.DatePicker;

import android.widget.EditText;



/**

 * 日期控件的简单使用

 *

 * @author adminelco

 * @time 2011-07-20 15:42:24

 *

 */

public class SelectActivity extends Activity {

    private EditText showDate = null;

    private Button pickDate = null;

    private static final int DATE_DIALOG_ID = 1;

    private static final int SHOW_DATAPICK = 0;

    private int mYear;

    private int mMonth;

    private int mDay;



    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.date);

        showDate = (EditText) findViewById(R.id.showDate);

        pickDate = (Button) findViewById(R.id.but_showDate);

        pickDate.setOnClickListener(new DateButtonOnClickListener());

        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);

        mMonth = c.get(Calendar.MONTH);

        mDay = c.get(Calendar.DAY_OF_MONTH);

        setDateTime();

    }



    private void setDateTime() {

        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);

        mMonth = c.get(Calendar.MONTH);

        mDay = c.get(Calendar.DAY_OF_MONTH);



        updateDisplay();

    }



    /**

     * 更新日期

     */

    private void updateDisplay() {

        showDate.setText(new StringBuilder().append(mYear).append(

                (mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append(

                (mDay < 10) ? "0" + mDay : mDay));

    }



    /**

     * 日期控件的事件

     */

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,

                              int dayOfMonth) {

            mYear = year;

            mMonth = monthOfYear;

            mDay = dayOfMonth;

            updateDisplay();

        }

    };



    /**

     * 选择日期Button的事件处理

     *

     * @author Raul

     *

     */

    class DateButtonOnClickListener implements

            android.view.View.OnClickListener {

        @Override

        public void onClick(View v) {

            Message msg = new Message();

            if (pickDate.equals((Button) v)) {

                msg.what = SelectActivity.SHOW_DATAPICK;

            }
            SelectActivity.this.saleHandler.sendMessage(msg);

        }

    }



    @Override

    protected Dialog onCreateDialog(int id) {

        switch (id) {

            case DATE_DIALOG_ID:

                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,

                        mDay);

        }

        return null;

    }



    @Override

    protected void onPrepareDialog(int id, Dialog dialog) {

        switch (id) {

            case DATE_DIALOG_ID:

                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);

                break;

        }

    }



    /**

     * 处理日期控件的Handler

     */

    Handler saleHandler = new Handler() {

        @Override

        public void handleMessage(Message msg) {

            switch (msg.what) {

                case SelectActivity.SHOW_DATAPICK:

                    showDialog(DATE_DIALOG_ID);

                    break;

            }

        }

    };

}