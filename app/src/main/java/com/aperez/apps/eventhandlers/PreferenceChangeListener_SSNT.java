package com.aperez.apps.eventhandlers;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.widget.Toast;

import com.aperez.apps.androidfunwithflags.MainActivity_SSNT;
import com.aperez.apps.androidfunwithflags.R;

import java.util.Set;

public class PreferenceChangeListener_SSNT implements OnSharedPreferenceChangeListener {
    private MainActivity_SSNT mainActivitySSNT;

    public PreferenceChangeListener_SSNT(MainActivity_SSNT mainActivitySSNT) {
        this.mainActivitySSNT = mainActivitySSNT;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.mainActivitySSNT.setPreferencesChanged(true);

        if (key.equals(this.mainActivitySSNT.getREGIONS())) {
            this.mainActivitySSNT.getQuizViewModel().setGuessRows(sharedPreferences.getString(
                    MainActivity_SSNT.CHOICES, null));
            this.mainActivitySSNT.getQuizFragment().resetQuiz();
        } else if (key.equals(this.mainActivitySSNT.getCHOICES())) {
            Set<String> regions = sharedPreferences.getStringSet(this.mainActivitySSNT.getREGIONS(),
                    null);
            if (regions != null && regions.size() > 0) {
                this.mainActivitySSNT.getQuizViewModel().setRegionsSet(regions);
                this.mainActivitySSNT.getQuizFragment().resetQuiz();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                regions.add(this.mainActivitySSNT.getString(R.string.default_region));
                editor.putStringSet(this.mainActivitySSNT.getREGIONS(), regions);
                editor.apply();
                Toast.makeText(this.mainActivitySSNT, R.string.default_region_message,
                        Toast.LENGTH_LONG).show();
            }
        }

        Toast.makeText(this.mainActivitySSNT, R.string.restarting_quiz,
                Toast.LENGTH_SHORT).show();
    }
}
