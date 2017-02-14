package com.example.peter.criminalintent;

import android.support.v4.app.Fragment;



/**
 * Created by peter on 13.02.2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
