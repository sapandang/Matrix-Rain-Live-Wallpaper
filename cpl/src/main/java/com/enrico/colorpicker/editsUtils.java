package com.enrico.colorpicker;

import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

class editsUtils {

    //maintain cursor to right when typing in a edittext
    static void keepCursorToRight(EditText editText) {

        if (editText.getText().length() > 0) {
            editText.setSelection(editText.getText().length());
        }
    }

    //clear edittext on touch
    static void initializeEditTextTouchListeners(EditText... editTexts) {

        for (final EditText edt : editTexts) {
            edt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    edt.getText().clear();
                }
            });
        }
    }

    //initialize all text watchers
    static void initializeEditTextChangeListeners(TextWatcher aaTextWatcher, TextWatcher hexTextWatcher, TextWatcher aTextWatcher, TextWatcher rTextWatcher, TextWatcher gTextWatcher, TextWatcher bTextWatcher, final EditText editAA, final EditText editAlpha, final EditText editHEX, final EditText editR, final EditText editG, final EditText editB) {

        editAA.addTextChangedListener(aaTextWatcher);
        editHEX.addTextChangedListener(hexTextWatcher);
        editAlpha.addTextChangedListener(aTextWatcher);
        editR.addTextChangedListener(rTextWatcher);
        editG.addTextChangedListener(gTextWatcher);
        editB.addTextChangedListener(bTextWatcher);

    }

    //disable edittext when using seebars tracking
    static void disableEditText(EditText editHEX, EditText someRGB, TextWatcher someRGBWatcher, TextWatcher hexWatcher) {
        someRGB.removeTextChangedListener(someRGBWatcher);
        someRGB.setEnabled(false);
        editHEX.removeTextChangedListener(hexWatcher);
        editHEX.setEnabled(false);
    }

    //enable edittext when seekbar stops tracking
    static void enableEditText(EditText editHEX, EditText someRGB, TextWatcher someRGBWatcher, TextWatcher hexWatcher) {
        someRGB.addTextChangedListener(someRGBWatcher);
        someRGB.setEnabled(true);
        editHEX.addTextChangedListener(hexWatcher);
        editHEX.setEnabled(true);
    }
}
