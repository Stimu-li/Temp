package com.example.mad_lab_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class DrawingView extends View {
    private final Paint paint;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        // Set black background
        canvas.drawColor(Color.BLACK);

        // Draw a blue filled circle
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(150, 150, 50, paint); // (x=150, y=150)

        // Draw a red outlined rectangle
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(300, 100, 400, 300, paint); // (left=300, top=100, right=400, bottom=300)

        // Draw a smaller blue outlined rectangle
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(100, 400, 200, 500, paint); // (left=100, top=400, right=200, bottom=500)

        // Draw a yellow horizontal line
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(300, 400, 500, 400, paint); // (startX=300, startY=400, stopX=500, stopY=400)

        // Draw a yellow vertical line
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(600, 100, 600, 300, paint); // (startX=600, startY=100, stopX=600, stopY=300)

        // Draw a smaller blue outlined circle
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(450, 450, 40, paint); // (x=450, y=450)
    }
}
