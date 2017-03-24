package com.example.peter.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;



/**
 * Created by peter on 13.02.2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
 protected Fragment createFragment() {
        return CrimeListFragment.newInstance(getIntent().getBooleanExtra(CrimeListFragment
                .SAVED_SUBTITLE_VISIBLE, false));
    }
       public static Intent newIntent(Context packageContext, boolean showSubtitle) {
        Intent intent = new Intent(packageContext, CrimeListActivity.class);
        intent.putExtra(CrimeListFragment.SAVED_SUBTITLE_VISIBLE, showSubtitle);
        return intent;
    }
}
