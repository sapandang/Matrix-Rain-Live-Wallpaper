package com.enrico.colorpicker;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

class viewUtils {

    //apply edittext colors
    private static void applyEditTextColor(final Activity activity, final int color, final EditText... editTexts) {

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                for (EditText editText : editTexts) {

                    editText.setTextColor(color);

                }

            }
        });
    }

    //apply textviews colors
    private static void applyTextColor(final Activity activity, final int color, final TextView... textViews) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                for (TextView formats : textViews) {

                    formats.setTextColor(color);
                }

            }

        });
    }

    //update the view
    static void updateColorView(final Activity activity, final View colorView, final TextView hashtext, final TextView hashtag, final EditText editAA, final EditText editAlpha, final TextView rgb, final EditText editHEX, final EditText editR, final EditText editG, final EditText editB, final int RGB) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                colorView.setBackgroundColor(RGB);
                applyTextColor(activity, colorDialog.getComplementaryColor(RGB), hashtext, hashtag, editAA, editAlpha, rgb);
                applyEditTextColor(activity, colorDialog.shiftColor(colorDialog.getComplementaryColor(RGB), 0.9f), editHEX, editR, editG, editB);
                editHEX.setText(colorUtils.getColorValues(RGB).toUpperCase());
                // saveColorUtils.saveColor(activity, RGB);

            }
        });
    }

    //update rgb alpha value
    static void updateRGBAlpha(final Activity activity, final SeekBar alphatize, final EditText editAlpha, final int progress) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                String updatedValue = Integer.toString(progress);
                editAlpha.setText(updatedValue.toUpperCase());
                alphatize.setProgress(progress);
                // saveColorUtils.saveAlpha(activity, progress);

            }

        });
    }

    //update alpha values
    static void updateAlphaEdits(final Activity activity, final SeekBar alphatize, final EditText editAA, final EditText editAlpha, final int progress) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                String updatedValue = Integer.toString(progress);
                editAA.setText(Integer.toHexString(progress).toUpperCase());
                editAlpha.setText(updatedValue.toUpperCase());
                alphatize.setProgress(progress);
                // saveColorUtils.saveAlpha(activity, progress);

            }

        });
    }

    //preview color
    static void previewColor(final Activity activity, final View colorView, final int RGB) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                colorView.setBackgroundColor(RGB);
            }
        });
    }

    //update color on resume dialog
    static void updateColorOnResume(final Activity activity, final View colorView, final TextView hashtext, final TextView hashtag, final EditText editAA, final EditText editAlpha, final TextView rgb, final EditText editHEX, final EditText editR, final EditText editG, final EditText editB, final String erre, final String gi, final String bi, final int RGB) {

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                colorView.setBackgroundColor(RGB);
                applyTextColor(activity, colorDialog.getComplementaryColor(RGB), hashtext, hashtag, editAA, editAlpha, rgb);
                applyEditTextColor(activity, colorDialog.shiftColor(colorDialog.getComplementaryColor(RGB), 0.9f), editHEX, editR, editG, editB);
                editR.setText(erre.toUpperCase());
                editG.setText(gi.toUpperCase());
                editB.setText(bi.toUpperCase());
                editHEX.setText(colorUtils.getColorValues(RGB).toUpperCase());

            }
        });

    }

    //update rgb texts
    static void updateRGBtexts(final Activity activity, final String progressValue, final EditText RorGorB) {

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                RorGorB.setText(progressValue.toUpperCase());

            }
        });

    }

    //update the shit
    static void updateShitView(final Activity activity, final View colorView, final TextView hashtext, final TextView hashtag, final EditText editAA, final EditText editAlpha, final TextView rgb, final EditText editHEX, final EditText editR, final EditText editG, final EditText editB, final SeekBar first, final SeekBar second, final SeekBar third, final int RGB) {

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                colorView.setBackgroundColor(RGB);
                applyTextColor(activity, colorDialog.getComplementaryColor(RGB), hashtext, hashtag, editAA, editAlpha, rgb);
                applyEditTextColor(activity, colorDialog.shiftColor(colorDialog.getComplementaryColor(RGB), 0.9f), editHEX, editR, editG, editB);
                viewUtils.updateRGBtexts(activity, colorUtils.getR(RGB), editR);
                viewUtils.updateRGBtexts(activity, colorUtils.getG(RGB), editG);
                viewUtils.updateRGBtexts(activity, colorUtils.getB(RGB), editB);
                first.setProgress(seekbarUtils.updateSeekBarProgress(colorUtils.getR(RGB)));
                second.setProgress(seekbarUtils.updateSeekBarProgress(colorUtils.getG(RGB)));
                third.setProgress(seekbarUtils.updateSeekBarProgress(colorUtils.getB(RGB)));

            }
        });
    }

    //make toast
    static void makeToast(Activity activity, String text) {

        Toast.makeText(activity, text, Toast.LENGTH_SHORT)
                .show();

    }
}