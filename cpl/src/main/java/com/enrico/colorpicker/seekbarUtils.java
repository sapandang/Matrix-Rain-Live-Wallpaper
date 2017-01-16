package com.enrico.colorpicker;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

class seekbarUtils {


    //color seekbars
    private static void initializeSeekBarsColors(SeekBar... seekBars) {

        for (SeekBar argb : seekBars) {

            final String tag = argb.getTag().toString();
            final int color = Color.parseColor(tag);

            argb.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            argb.getThumb().setColorFilter(color, PorterDuff.Mode.SRC_IN);

        }
    }

    //color seekbars
    private static void initializeAlphaSeekBarColor(Activity activity, View colorView, final SeekBar alphatize) {

        int color = colorUtils.getColorViewColor(colorView);

        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;

        if (darkness < 0.5) {

            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    alphatize.getProgressDrawable().setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN);
                    alphatize.getThumb().setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN);
                }
            });

        } else {

            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    alphatize.getProgressDrawable().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
                    alphatize.getThumb().setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_IN);
                }
            });
        }
    }

    //initialize seekbars
    static void initializeSeekBars(final Activity activity, final TextWatcher aaTextWatcher, final TextWatcher hexTextWatcher, final TextWatcher aTextWatcher, final TextWatcher rTextWatcher, final TextWatcher gTextWatcher, final TextWatcher bTextWatcher, final int alpha, final SeekBar alphatize, final SeekBar first, final SeekBar second, final SeekBar third, final View colorView, final TextView hashtext, final TextView hashtag, final TextView rgb, final EditText editHEX, final EditText editAA, final EditText editAlpha, final EditText editR, final EditText editG, final EditText editB) {

        viewUtils.updateAlphaEdits(activity, alphatize, editAA, editAlpha, alpha);

        initializeSeekBarsColors(first, second, third);

        initializeAlphaSeekBarColor(activity, colorView, alphatize);

        alphatize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;
            int RGB;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                progress = i;
                RGB = android.graphics.Color.argb(progress, first.getProgress(), second.getProgress(), third.getProgress());
                viewUtils.previewColor(activity, colorView, RGB);
                viewUtils.updateAlphaEdits(activity, alphatize, editAA, editAlpha, progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                editsUtils.disableEditText(editAlpha, editAA, aaTextWatcher, aTextWatcher);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editsUtils.enableEditText(editAlpha, editAA, aaTextWatcher, aTextWatcher);

            }
        });

        first.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;
            int RGB;
            String updatedValue;

            @Override

            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {

                progress = progressValue;
                updatedValue = Integer.toString(progressValue);
                RGB = android.graphics.Color.argb(alphatize.getProgress(), progress, second.getProgress(), third.getProgress());
                viewUtils.updateColorView(activity, colorView, hashtext, hashtag, editAA, editAlpha, rgb, editHEX, editR, editG, editB, RGB);
                viewUtils.updateRGBtexts(activity, updatedValue, editR);

            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {

                editsUtils.disableEditText(editHEX, editR, rTextWatcher, hexTextWatcher);

            }

            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {

                editsUtils.enableEditText(editHEX, editR, rTextWatcher, hexTextWatcher);


            }

        });

        second.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;
            int RGB;
            String updatedValue;


            @Override

            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {

                progress = progressValue;
                updatedValue = Integer.toString(progressValue);
                RGB = android.graphics.Color.argb(alphatize.getProgress(), first.getProgress(), progress, third.getProgress());
                viewUtils.updateColorView(activity, colorView, hashtext, hashtag, editAA, editAlpha, rgb, editHEX, editR, editG, editB, RGB);
                viewUtils.updateRGBtexts(activity, updatedValue, editG);

            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {

                editsUtils.disableEditText(editHEX, editG, gTextWatcher, hexTextWatcher);


            }

            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {

                editsUtils.enableEditText(editHEX, editG, gTextWatcher, hexTextWatcher);


            }

        });

        third.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;
            int RGB;
            String updatedValue;


            @Override

            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {

                progress = progressValue;
                updatedValue = Integer.toString(progressValue);
                RGB = android.graphics.Color.argb(alphatize.getProgress(), first.getProgress(), second.getProgress(), progress);

                viewUtils.updateColorView(activity, colorView, hashtext, hashtag, editAA, editAlpha, rgb, editHEX, editR, editG, editB, RGB);
                viewUtils.updateRGBtexts(activity, updatedValue, editB);

            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {
                editsUtils.disableEditText(editHEX, editB, bTextWatcher, hexTextWatcher);


            }

            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {

                editsUtils.enableEditText(editHEX, editB, bTextWatcher, hexTextWatcher);

            }
        });
    }

    static int updateSeekBarProgress(String RorGorB) {

        return Integer.parseInt(RorGorB);
    }

    static void updateSeekBarValue(final SeekBar RorGorB, final int value) {

        RorGorB.setProgress(value);
    }
}