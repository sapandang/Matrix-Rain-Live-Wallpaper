package com.enrico.colorpicker;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

class aaTextWatcher implements TextWatcher {

    private Activity activity;
    private SeekBar alphatize;
    private EditText editAA;
    private EditText editAlpha;

    aaTextWatcher(Activity a, SeekBar alpha, EditText aa, EditText edtalpha) {

        this.activity = a;
        this.alphatize = alpha;
        this.editAA = aa;
        this.editAlpha = edtalpha;
    }

    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before,
                              int count) {

        int alpha;

        editsUtils.keepCursorToRight(editAA);

        try {

            alpha = Integer.decode("0x" + s.toString());

            viewUtils.updateRGBAlpha(activity, alphatize, editAlpha, alpha);


        } catch (IllegalArgumentException e) {

            viewUtils.makeToast(activity, activity.getResources().getString(R.string.aahex_error));

        }
    }

    public void afterTextChanged(Editable s) {

    }
}