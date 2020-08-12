package com.example.sensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyCompassView extends View {
    private Paint area;
    private float arc = 0;

    // constructor
    public MyCompassView(Context context) {
        super(context);
        init();
    }

    // vẽ lại la bàn với góc mới
    public void updateData(float position) {
        arc = position;
        invalidate();
    }

    // vẽ lại hình ảnh la bàn ứng với số liệu mới
    protected void onDraw(Canvas c) {
        int xPoint = getMeasuredWidth() / 2;
        int yPoint = getMeasuredHeight() / 2;
        float radius = (float) (Math.max(xPoint, yPoint) * 0.6);
        c.drawCircle(xPoint, yPoint, radius, area);
        float x = (float) (xPoint +  radius * Math.sin((double) (-arc) / 180 * Math.PI));
        float y = (float) (xPoint -  radius * Math.cos((double) (-arc) / 180 * Math.PI));
        c.drawLine(xPoint, yPoint, x, y, area);
        c.drawText(String.valueOf(arc), xPoint, yPoint, area);
    }
    // hàm vẽ lại hình ảnh của view
    private void init() {
        area = new Paint();
        area.setAntiAlias(true);
        area.setColor(Color.RED);
        area.setStrokeWidth(3);
        area.setStyle(Paint.Style.STROKE);
        area.setTextSize(30);
    }
}
