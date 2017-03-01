package com.example.peter.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Created by peter on 03.02.2017.
 */

public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private  Button mTimeButton;
    private CheckBox mSolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mCrime = new Crime();
     /*   UUID crimeId = (UUID) getActivity().getIntent()
                .getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);*/
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
// Здесь намеренно оставлено пустое место
            }
            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mCrime.setTitle(c.toString());
            }
            @Override
            public void afterTextChanged(Editable c) {
// И здесь тоже
            }
        });
        mDateButton = (Button)v.findViewById(R.id.crime_date);
       String dt = DateFormat.format("EEEE, dd MMMM yyyy", mCrime.getDate()).toString();


       // mDateButton.setText(dt);
        updateDate();
       // mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
               // DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mCrime.getDate());

                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mTimeButton= (Button)v.findViewById(R.id.crime_time
        );

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  FragmentManager manager = getFragmentManager();
                // DatePickerFragment dialog = new DatePickerFragment();
               //TimePickerFragment dialog = TimePickerFragment
                      //  .newInstance(mCrime.getDate());
              //  Intent intent = new Intent(getActivity(), TimeActivity.class);
                Intent intent =TimeActivity.newIntent(getActivity(),mCrime.getDate());
                startActivityForResult(intent,REQUEST_TIME);
               // dialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
               // dialog.show(manager, DIALOG_DATE);
            }
        });
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean
                    isChecked) {
// Назначение флага раскрытия преступления
                mSolvedCheckBox.setChecked(isChecked);
                mCrime.setSolved(isChecked);
            }
        });





        return v;


    }
    //Заменяем в дате время
 private  Date subDate(Date valD,Date valT){
     Calendar calendar = Calendar.getInstance();
     calendar.setTime(valD);
     int year = calendar.get(Calendar.YEAR);
     int month = calendar.get(Calendar.MONTH);
     int day = calendar.get(Calendar.DAY_OF_MONTH);

     calendar.setTime(valT);
     int hours=calendar.get(Calendar.HOUR_OF_DAY);
     int minuts=calendar.get(Calendar.MINUTE);

     return new GregorianCalendar(year, month, day,hours,minuts).getTime();

 }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);


            mCrime.setDate(subDate(date,mCrime.getDate()));
            updateDate();
        }
        if (requestCode == REQUEST_TIME) {
            Date date = (Date) data
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);

            mCrime.setDate(subDate(mCrime.getDate(),date));
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }

    public void returnResult() {
        getActivity().setResult(Activity.RESULT_OK, null);
    }
}

