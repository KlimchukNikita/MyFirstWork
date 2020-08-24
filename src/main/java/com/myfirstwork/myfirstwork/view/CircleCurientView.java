package com.myfirstwork.myfirstwork.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleCurientView extends View {
    private int size;
    private int width;
    private int scale;
    private int item;
    private Context context;
    private Canvas canvas;
    private DisplayMetrics displayMetrics;

    public CircleCurientView(Context context){
        super(context);
        this.context=context;
        displayMetrics=context.getResources().getDisplayMetrics();
        width=displayMetrics.widthPixels/2;
        scale= (int) displayMetrics.scaledDensity;
        //setCoordinate();
    }
    public CircleCurientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        displayMetrics=context.getResources().getDisplayMetrics();
        width=displayMetrics.widthPixels/2;
        scale= (int) displayMetrics.scaledDensity;
      //  setCoordinate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas=canvas;
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//            canvas.drawCircle((width), 12, 10, paint);
//            canvas.drawCircle((width-50),12,10,paint);
//            canvas.drawCircle((width+50),12,10,paint);
        printItemCircle();
    }
    @SuppressLint("WrongCall")
    public void setItem(int item){
        this.item=item;
        onDraw(canvas);
    }
    public void printItemCircle(){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        switch (item){
            case 0:
                paint.setColor(Color.argb(255,200,20,20));
                canvas.drawCircle((width-50),12,10,paint);
                paint.setColor(Color.argb(255,255,255,255));
                canvas.drawCircle((width), 12, 10, paint);
                canvas.drawCircle((width+50),12,10,paint);
                break;
            case 1:
                paint.setColor(Color.argb(255,200,20,20));
                canvas.drawCircle((width), 12, 10, paint);
                paint.setColor(Color.argb(255,255,255,255));
                canvas.drawCircle((width-50),12,10,paint);
                canvas.drawCircle((width+50),12,10,paint);
                break;
            case 2:
                paint.setColor(Color.argb(255,200,20,20));
                canvas.drawCircle((width+50),12,10,paint);
                paint.setColor(Color.argb(200,255,255,255));
                canvas.drawCircle((width), 12, 10, paint);
                canvas.drawCircle((width-50),12,10,paint);

                break;
        }

    }
}
