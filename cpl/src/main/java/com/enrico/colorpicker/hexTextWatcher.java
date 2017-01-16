package com.enrico.colorpicker;

import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

class hexTextWatcher implements TextWatcher {

    private Activity activity;
    private View colorView;
    private TextView hashtext;
    private TextView hashtag;
    private EditText editAA;
    private EditText editAlpha;
    private TextView rgb;
    private EditText editHEX;
    private EditText editR;
    private EditText editG;
    private EditText editB;
    private SeekBar first;
    private SeekBar second;
    private SeekBar third;

    hexTextWatcher(Activity a, final View cw, final TextView ht, final TextView htg, final EditText aa, final EditText alpha, final TextView erregibi, final EditText hex, final EditText r, final EditText g, final EditText b, final SeekBar one, final SeekBar two, final SeekBar three) {

        this.activity = a;
        this.colorView = cw;
        this.hashtext = ht;
        this.hashtag = htg;
        this.editAA = aa;
        this.editAlpha = alpha;
        this.rgb = erregibi;
        this.editHEX = hex;
        this.editR = r;
        this.editG = g;
        this.editB = b;
        this.first = one;
        this.second = two;
        this.third = three;
    }

    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before,
                              int count) {
        int color;

        try {
            color = Color.parseColor("#" + s.toString());

            viewUtils.updateShitView(activity, colorView, hashtext, hashtag, editAA, editAlpha, rgb, editHEX, editR, editG, editB, first, second, third, color);

        } catch (IllegalArgumentException e) {

            viewUtils.makeToast(activity, activity.getResources().getString(R.string.hex_error));

        }
    }

    public void afterTextChanged(Editable s) {

    }
}