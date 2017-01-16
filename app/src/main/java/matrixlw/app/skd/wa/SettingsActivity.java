package matrixlw.app.skd.wa;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.enrico.colorpicker.colorDialog;

import static matrixlw.app.skd.wa.SettingsActivity.SettingsFragment.background_color;
import static matrixlw.app.skd.wa.SettingsActivity.SettingsFragment.text_color;

/**
 * Created by sapan on 1/12/2017.
 */

public class SettingsActivity extends AppCompatActivity implements colorDialog.ColorSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set the Preference default value
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);


        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();


    }

    @Override
    public void onColorSelection(DialogFragment dialogFragment, @ColorInt int selectedColor) {

        int tag;

        // get tag number from fragment
        tag = Integer.valueOf(dialogFragment.getTag());
        switch (tag) {

            case 1:

                //Set the picker dialog's color
                colorDialog.setPickerColor(SettingsActivity.this, 1, selectedColor);

                //set custom preference summary
                colorDialog.setColorPreferenceSummary(background_color, selectedColor, SettingsActivity.this, getResources());


                break;

            case 2:

                //Set the picker dialog's color
                colorDialog.setPickerColor(SettingsActivity.this, 2, selectedColor);

                //set custom preference summary
                colorDialog.setColorPreferenceSummary(text_color, selectedColor, SettingsActivity.this, getResources());



                break;
        }
        }


    /*
    *Setting fragment
     */
    public static class SettingsFragment extends PreferenceFragment {

        static Preference background_color, text_color;

        Context mContext;

        //retrieve AppCompatActivity

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            mContext=context;
            Log.d("Attaced",mContext.toString());
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           final AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            Log.d("Attaced act",appCompatActivity.toString());
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);

            //find the preference
            background_color = findPreference("background_color");
            text_color = findPreference("text_color");

            //get preferences colors
            int color = colorDialog.getPickerColor(getActivity(), 1);

            //set preferences colors
            colorDialog.setColorPreferenceSummary(background_color, color, getActivity(), getResources());

            //set the event listner for the color
            background_color.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override

                public boolean onPreferenceClick(Preference preference) {

                    colorDialog.showColorPicker(appCompatActivity, 1);

                    return false;
                }
            });

            //pref text_color
            //get preferences colors
            int color2 = colorDialog.getPickerColor(getActivity(), 2);

            //set preferences colors
            colorDialog.setColorPreferenceSummary(text_color, color2, getActivity(), getResources());

            //set the event listner for the color
            text_color.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override

                public boolean onPreferenceClick(Preference preference) {

                    colorDialog.showColorPicker(appCompatActivity,2);

                    return false;
                }
            });
        }
    }

}



