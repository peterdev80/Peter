package com.example.peter.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



/**
 * Created by peter on 27.02.2017.
 */

public class TimePickerFragment extends DialogFragment {
    private static final String ARG_DATE = "date";
    private Button mokbtn;
    private TimePicker mTimePickerPicker;
    public static final String EXTRA_TIME =
            "com.example.android.criminalintent.time";
 public  static  TimePickerFragment newInstance(Date date) {
     Bundle args = new Bundle();
     args.putSerializable(ARG_DATE, date);
     TimePickerFragment fragment = new TimePickerFragment();
     fragment.setArguments(args);
     return fragment;
 }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // TimePickerFragment fragment = new TimePickerFragment();
    Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours=calendar.get(Calendar.HOUR);
        int minuts=calendar.get(Calendar.MINUTE);
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.timepicker_fragment, null);
        mTimePickerPicker =(TimePicker) v.findViewById(R.id.time_dialog);
        mTimePickerPicker.setHour(hours);
        mTimePickerPicker.setMinute(minuts);
        mokbtn=(Button)v.findViewById(R.id.okbtn);
        mokbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Hour = mTimePickerPicker.getHour();
                int Min = mTimePickerPicker.getMinute();

                Date date = new GregorianCalendar(2000,1,1,Hour,Min).
                        getTime();
                sendForActivity(date);

            }
        });

return v;



    }

  /*  @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours=calendar.get(Calendar.HOUR);
        int minuts=calendar.get(Calendar.MINUTE);
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.timepicker_fragment, null);
        mTimePickerPicker =(TimePicker) v.findViewById(R.id.time_dialog);
        mTimePickerPicker.setHour(hours);
        mTimePickerPicker.setMinute(minuts);


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                //.setPositiveButton(android.R.string.ok, null)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int Hour = mTimePickerPicker.getHour();
                                int Min = mTimePickerPicker.getMinute();

                                Date date = new GregorianCalendar(2000,1,1,Hour,Min).
                                        getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                .create();
    }*/
private  void sendForActivity(Date date)
{
    Intent intent = new Intent();
    intent.putExtra(EXTRA_TIME, date);
    getActivity().setResult(Activity.RESULT_OK, intent);
}
    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);
        getTargetRequestCode();
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);


    }




}
