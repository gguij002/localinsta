package com.gery.localinsta.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by gguij002 on 6/28/17.
 */

public class ScreenUtils {

    public static int getScreenWidthInPx() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeightInPx() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getViewHeight(View view) {
        if (view == null) {
            return 0;
        }

        WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        view.measure(size.x, size.y);
        return view.getMeasuredHeight();
    }
}
