package com.doriel.remotecontrol.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Joystick extends View {
    public interface JoystickListener {
        void onMove(float x, float y);
    }

    private static final float POINTER_SIZE_RATIO = 0.15f;
    private static final float BACKGROUND_SIZE_RATIO = 0.75f;

    private final Paint pointerPaint;
    private final Paint backgroundPaint;
    private float pointerRadius;
    private float backgroundRadius;
    private int pointerX = 0;
    private int pointerY = 0;
    private int centerX = 0;
    private int centerY = 0;

    private JoystickListener callback;

    public Joystick(Context context) {
        this(context, null);
    }

    public Joystick(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public Joystick(Context context, AttributeSet attrs) {
        super(context, attrs);

        pointerPaint = new Paint();
        pointerPaint.setAntiAlias(true);
        pointerPaint.setColor(Color.LTGRAY);
        pointerPaint.setStyle(Paint.Style.FILL);

        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(0xff99cc00);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, backgroundRadius, backgroundPaint); // background
        canvas.drawCircle(pointerX, pointerY, pointerRadius, pointerPaint); // pointer
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        centerX = pointerX = w / 2;
        centerY = pointerY = w / 2;

        int d = Math.min(w, h);
        backgroundRadius = (float)d / 2 * BACKGROUND_SIZE_RATIO;
        pointerRadius = (float)d / 2 * POINTER_SIZE_RATIO;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // set new pointer coordinates
        pointerY = (int) event.getY();
        pointerX = (int) event.getX();

        double abs = Math.sqrt(Math.pow(pointerX - centerX, 2) + Math.pow(pointerY - centerY, 2)); // c^2 = a^2 + b^2
        if (abs > backgroundRadius) {
            // the pointer is outside the circle, limit to border
            pointerX = (int) ((pointerX - centerX) * backgroundRadius / abs + centerX);
            pointerY = (int) ((pointerY - centerY) * backgroundRadius / abs + centerY);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // re-center the pointer
            pointerX = centerX;
            pointerY = centerY;
            triggerOnMove();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            triggerOnMove();
        }

        invalidate(); // force re-draw
        return true;
    }

    private void triggerOnMove() {
        if (callback != null) {
            float x = (pointerX - centerX) / backgroundRadius;
            float y = (centerY - pointerY) / backgroundRadius;
            callback.onMove(x, y);
        }
    }

    public void setOnMoveListener(JoystickListener l) {
        callback = l;
    }
}
