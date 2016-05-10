package com.example.myapp;

import android.app.*;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("test");
        Canvas canvas = new Canvas();
        Paint paint = new Paint();
        paint.setARGB(255, 0, 0, 0);
        paint.setAntiAlias(true);
        canvas.drawText("TEST!", 1,5, paint);
        setContentView(R.layout.main);
        try {
            me.beepbeat.Main.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        canvas.drawText("TEST2!", 1,10, paint);
    }


}
