package com.example.snakexenzia;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class Food {
    private final int blockSize;
    private final int screenWidth, screenHeight;
    private final Paint paint;
    private Point location;
    private final Random random = new Random();

    public Food(int blockSize, int screenWidth, int screenHeight) {
        this.blockSize = blockSize;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        paint = new Paint();
        paint.setColor(Color.RED);
        spawn();
    }

    public void spawn() {
        int x = random.nextInt(screenWidth / blockSize) * blockSize;
        int y = random.nextInt(screenHeight / blockSize) * blockSize;
        location = new Point(x, y);
    }

    public void draw(Canvas canvas, Paint paint) {
        if (location != null) {
            canvas.drawRect(location.x, location.y, location.x + blockSize, location.y + blockSize, this.paint);
        }
    }

    public Point getLocation() {
        return location;
    }
}
