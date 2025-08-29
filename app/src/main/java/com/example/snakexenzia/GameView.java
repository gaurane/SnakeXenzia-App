package com.example.snakexenzia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    private final int blockSize = 50;
    private final int updateDelay = 300;

    private final Snake snake;
    private final Food food;
    private final Paint paint = new Paint();
    private final Handler handler = new Handler();
    private Point direction = new Point(1, 0);

    private float downX, downY;

    public GameView(Context context) {
        super(context);
        setBackgroundColor(Color.BLACK); // Set black background
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        snake = new Snake(blockSize, screenWidth, screenHeight);
        food = new Food(blockSize, screenWidth, screenHeight);
        handler.postDelayed(gameRunnable, updateDelay);
    }

    private final Runnable gameRunnable = new Runnable() {
        @Override
        public void run() {
            if (!snake.checkCollision(direction)) {
                snake.move(direction);
                if (snake.getHead().equals(food.getLocation())) {
                    snake.grow();
                    food.spawn();
                }
                invalidate();
                handler.postDelayed(this, updateDelay);
            } else {
                if (getContext() instanceof GameActivity) {
                    ((GameActivity) getContext()).gameOver();
                }
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK); // 👈 black background
        food.draw(canvas, paint);
        snake.draw(canvas, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float deltaX = event.getX() - downX;
                float deltaY = event.getY() - downY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    direction = deltaX > 0 ? new Point(1, 0) : new Point(-1, 0);
                } else {
                    direction = deltaY > 0 ? new Point(0, 1) : new Point(0, -1);
                }
                break;
        }
        return true;
    }
}
