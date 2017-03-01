package com.example.peter.criminalintent;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.Date;
import java.util.UUID;

/**
 * Created by peter on 28.02.2017.
 */

public class TimeActivity extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_TM =
            "com.example.peter.criminalintent.crime_tm";
    @Override
    protected Fragment createFragment() {
        Date dt=(Date) getIntent()
                .getSerializableExtra(EXTRA_CRIME_TM);
        return TimePickerFragment.newInstance(dt);
    }

    public static Intent newIntent(Context packageContext, Date date) {
        Intent intent = new Intent(packageContext, TimeActivity.class);
        intent.putExtra(EXTRA_CRIME_TM, date);
        return intent;
    }
}
