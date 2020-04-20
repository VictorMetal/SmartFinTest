package com.example.smartfintest.util;

import android.content.Context;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

public class Utils {
    public static int calculateNoOfColumns(@Nullable Context context, int columnWidth) {
        if (context == null) return 1;
        float columnWidthDp = convertPixelsToDp(context, columnWidth);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (screenWidthDp / columnWidthDp + 0.5) + 1;
    }

    public static float convertPixelsToDp(Context context, int pixel) {
        return pixel * context.getResources().getDisplayMetrics().density;
    }
}
