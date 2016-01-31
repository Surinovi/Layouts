package com.ab2016.neval.layouts;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView tv;
    private ImageView imv1, imv2, imv3;
    private ValueAnimator colorAnim;

    private int imgNum = 1;
    private LinearLayout layout;

    // ARGB (Alpha, Red, Green and Blue)
    // If alpha value is 00 it means 100% transparent.
    // A alpha value of FF corresponds to opaque.
    private static final int RED = 0xffFF0000;
    private static final int BLUE = 0xff0000FF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hiding status bar using code
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        imv1 = (ImageView) findViewById(R.id.imv1);
        imv2 = (ImageView) findViewById(R.id.imv2);
        imv3 = (ImageView) findViewById(R.id.imv3);

        tv = (TextView) findViewById(R.id.tv1);

        layout = (LinearLayout) findViewById(R.id.LinearLayout1);

        // Code for TextView text color animation
        //colorAnim = ObjectAnimator.ofInt(tv, "backgroundColor",RED, BLUE);
        colorAnim = ObjectAnimator.ofInt(tv, "textColor", RED, BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arrow_left:
                if (imgNum == 1)
                    makeAndShowDialogBox("Already at the first image");
                else {
                    imgNum--;
                    imv1.setVisibility(View.INVISIBLE);
                    imv2.setVisibility(View.INVISIBLE);
                    imv3.setVisibility(View.INVISIBLE);

                    if (imgNum % 3 == 0)
                        imv3.setVisibility(View.VISIBLE);
                    else if (imgNum % 3 == 1)
                        imv1.setVisibility(View.VISIBLE);
                    else if (imgNum % 3 == 2)
                        imv2.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.arrow_right:

                imgNum++;

                imv1.setVisibility(View.INVISIBLE);
                imv2.setVisibility(View.INVISIBLE);
                imv3.setVisibility(View.INVISIBLE);

                if (imgNum % 3 == 0)
                    imv3.setVisibility(View.VISIBLE);
                else if (imgNum % 3 == 1)
                    imv1.setVisibility(View.VISIBLE);
                else if (imgNum % 3 == 2)
                    imv2.setVisibility(View.VISIBLE);

                break;
            case R.id.btn1:
                changeBackgroundColor();
                break;
        }

    }

    public void changeBackgroundColor() {
        int rand1, rand2, rand3;

        rand1 = (int) (Math.random() * 255);
        rand2 = (int) (Math.random() * 255);
        rand3 = (int) (Math.random() * 255);

        layout.setBackgroundColor(Color.rgb(rand1, rand2, rand3));
    }

    private void makeAndShowDialogBox(String message) {

        AlertDialog.Builder myDialogBox = new AlertDialog.Builder(this);

        // set message, title, and icon
        myDialogBox.setTitle("Warning");
        myDialogBox.setMessage(message);
        myDialogBox.setIcon(R.drawable.godfather);

        // Set three option buttons
        myDialogBox.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // whatever should be done when answering "YES" goes
                        // here

                    }
                });

        // myDialogBox.setNegativeButton("NO",
        // new DialogInterface.OnClickListener() {
        // public void onClick(DialogInterface dialog, int whichButton) {
        // // whatever should be done when answering "NO" goes here
        //
        // }
        // });

        // myDialogBox.setNeutralButton("Cancel",
        // new DialogInterface.OnClickListener() {
        // public void onClick(DialogInterface dialog, int whichButton) {
        // // whatever should be done when answering "NO" goes here
        //
        // }
        // });

        myDialogBox.create();
        myDialogBox.show();
    }
}
