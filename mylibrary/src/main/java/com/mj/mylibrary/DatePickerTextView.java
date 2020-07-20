package com.mj.mylibrary;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.widget.AppCompatTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerTextView extends AppCompatTextView {
    public DatePickerTextView(Context context) {
        super(context);
        init(context);
    }

    DatePickerListener listener;

    public void setDatePickerListener(DatePickerListener listener) {
        this.listener = listener;
    }

    public DatePickerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DatePickerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    void init(final Context context) {

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentDate = Calendar.getInstance();
                if (date != null && !getText().toString().trim().isEmpty()) {
                    currentDate.setTime(date);
                }
                DatePickerDialog dtp = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.YEAR, y);
                        c.set(Calendar.MONTH, m);
                        c.set(Calendar.DAY_OF_MONTH, d);
                        Date date = c.getTime();
                        setText(date);
                        if (listener != null) {
                            listener.onDateSet(date);
                        }
                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
                dtp.show();
            }
        });
    }

    Date date;

    public void setText(Date date) {

        DatePickerTextView.this.setText(new SimpleDateFormat("dd MMM yyyy").format(date));
        this.date = date;
    }
    //No. 69069 2 No.

}
