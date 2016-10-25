package com.vitech.sandbox.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class DrawingView extends View {
    private static final int BLACK_COLOR = 0xFF000000;

    private Context context;

    private Path drawPath;
    private Paint drawPaint, canvasPaint, boarderPaint;

    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    int w, h;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();

        this.context = context;
    }

    private void setup() {
        drawPath = new Path();
        drawPaint = new Paint();

        drawPaint.setAntiAlias(true);
        drawPaint.setColor(BLACK_COLOR);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);

        boarderPaint = new Paint();
        boarderPaint.setColor(0xF0FF00FF);
        boarderPaint.setStrokeWidth(2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                displayMSG("down", touchX, touchY);
                                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                displayMSG("up", touchX, touchY);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    private void displayMSG(String title, float touchX, float touchY) {
        String msg = String.format("%s: (%f, %f). range: (%d, %d)", title, touchX, touchY, w, h);
        Log.d("Drawing", msg);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBorder(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        this.w = w;
        this.h = h;
        Toast.makeText(context, String.format("sizeChanged: size:(%d, %d)", w, h), Toast.LENGTH_SHORT).show();
    }

    private void drawBorder(Canvas canvas) {
        float w1 = w - 1;
        float h1 = h - 1;
        canvas.drawLine(1F, 1F, 1F, h1, boarderPaint);
        canvas.drawLine(1F, h1, w1, h1, boarderPaint);
        canvas.drawLine(w1, h1, w1, 0F, boarderPaint);
        canvas.drawLine(w1, 1F, 1F, 1F, boarderPaint);
    }
}
