package com.enrico.colorpicker;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

class rgbTextWatcher implements TextWatcher {

    private Activity activity;
    private EditText fromRGB;
    private SeekBar fromSeekbar;

    rgbTextWatcher(Activity a, final EditText RorGorB, final SeekBar ForSorT) {

        this.activity = a;
        this.fromRGB = RorGorB;
        this.fromSeekbar = ForSorT;
    }

    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before,
                              int count) {

        int RorGorBval;

        editsUtils.keepCursorToRight(fromRGB);

        try {

            RorGorBval = Integer.parseInt(s.toString());

            seekbarUtils.updateSeekBarValue(fromSeekbar, RorGorBval);

        } catch (IllegalArgumentException e) {
            viewUtils.makeToast(activity, activity.getResources().getString(R.string.rgb_error));

        }
    }

    public void afterTextChanged(Editable s) {

    }

}