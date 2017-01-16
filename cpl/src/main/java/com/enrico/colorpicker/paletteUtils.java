package com.enrico.colorpicker;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.Preference;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

class paletteUtils {

    private static void createCircularBitmap(Activity activity, Resources resources, ImageView imageView, String tag, int colorViewColor) {

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(SpToPixels(activity, 50), SpToPixels(activity, 50), conf);
        int color = Color.parseColor(tag);

        imageView.setImageDrawable(createRoundedBitmapDrawableWithBorder(activity, bmp, color, colorViewColor, resources));
    }

    static void createCircularPreferenceBitmap(Preference preference, Activity activity, Resources resources, int color, int viewColor) {

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(SpToPixels(activity, 50), SpToPixels(activity, 50), conf);

        preference.setIcon(createRoundedBitmapDrawableWithBorder(activity, bmp, color, viewColor, resources));

    }

    private static RoundedBitmapDrawable createRoundedBitmapDrawableWithBorder(Activity activity, Bitmap bitmap, int tagColor, int viewColor, Resources mResources) {

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int borderWidthHalf = 10; // In pixels

        // Calculate the bitmap radius
        int bitmapRadius = Math.min(bitmapWidth, bitmapHeight) / 2;

        int bitmapSquareWidth = Math.min(bitmapWidth, bitmapHeight);

        int bitmapDim = bitmapSquareWidth + borderWidthHalf;

        // Initializing a new empty bitmap.
        Bitmap roundedBitmap = Bitmap.createBitmap(bitmapDim, bitmapDim, Bitmap.Config.ARGB_8888);

        // Initialize a new Canvas to draw empty bitmap
        Canvas canvas = new Canvas(roundedBitmap);

        // Draw a solid color to canvas
        canvas.drawColor(tagColor);

        // Calculation to draw bitmap at the circular bitmap center position
        int x = borderWidthHalf + bitmapSquareWidth - bitmapWidth;
        int y = borderWidthHalf + bitmapSquareWidth - bitmapHeight;

        // Draw the bitmap to canvas
        // Bitmap will draw its center to circular bitmap center by keeping border spaces
        canvas.drawBitmap(bitmap, x, y, null);

        // Initializing a new Paint instance to draw circular border
        final Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeWidth(borderWidthHalf);

        // Set paint color according to view color to make it always visible
        double darkness = 1 - (0.299 * Color.red(viewColor) + 0.587 * Color.green(viewColor) + 0.114 * Color.blue(viewColor)) / 255;

        if (darkness < 0.5) {

            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    borderPaint.setColor(colorUtils.opaqueColor(Color.DKGRAY));

                }
            });

        } else {

            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    borderPaint.setColor(colorUtils.opaqueColor(Color.LTGRAY));


                }
            });
        }

        // drawCircle(float cx, float cy, float radius, Paint paint)
        // Draw the specified circle using the specified paint.
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getWidth() / 2, bitmapDim / 2, borderPaint);

        // Create a new RoundedBitmapDrawable
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mResources, roundedBitmap);

        // Set the corner radius of the bitmap drawable
        roundedBitmapDrawable.setCornerRadius(bitmapRadius);

        roundedBitmapDrawable.setCircular(true);

        roundedBitmapDrawable.setAntiAlias(true);

        // Return the RoundedBitmapDrawable
        return roundedBitmapDrawable;
    }

    private static int SpToPixels(Context context, int width) {

        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        int density = Math.round(scaledDensity);
        return width * density;
    }

    static void initializeMaterialPalette(final Activity activity, Resources resources, final View colorView, final TextView hashtext, final TextView hashtag, final SeekBar alphatize, final EditText editAA, final EditText editAlpha, final TextView rgb, final EditText editHEX, final EditText editR, final EditText editG, final EditText editB, ImageView... imageViews) {

        for (ImageView circles : imageViews) {

            final String tag = circles.getTag().toString();
            final String desc = circles.getContentDescription().toString();
            final int color = Color.parseColor(tag);

            createCircularBitmap(activity, resources, circles, tag, colorUtils.getColorViewColor(colorView));

            circles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    alphatize.setProgress(255);
                    viewUtils.updateColorView(activity, colorView, hashtext, hashtag, editAA, editAlpha, rgb, editHEX, editR, editG, editB, color);
                    viewUtils.makeToast(activity, desc + ": " + tag.toUpperCase());

                }
            });
        }
    }
}