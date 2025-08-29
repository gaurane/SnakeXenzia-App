package com.example.snakexenzia;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private final Deque<Point> body;
    private final int blockSize;
    private final int screenWidth, screenHeight;
    private final Paint paint;

    public Snake(int blockSize, int screenWidth, int screenHeight) {
        this.blockSize = blockSize;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        body = new LinkedList<>();
        body.add(new Point(blockSize * 5, blockSize * 5));
        paint = new Paint();
        paint.setColor(Color.GREEN);
    }

    public void move(Point direction) {
        Point head = new Point(body.peekFirst());
        head.offset(direction.x * blockSize, direction.y * blockSize);
        body.addFirst(head);
        body.removeLast();
    }

    public void grow() {
        Point tail = body.getLast();
        body.addLast(new Point(tail));
    }

    public boolean checkCollision(Point direction) {
        Point newHead = new Point(body.peekFirst());
        newHead.offset(direction.x * blockSize, direction.y * blockSize);

        if (newHead.x < 0 || newHead.y < 0 || newHead.x >= screenWidth || newHead.y >= screenHeight)
            return true;

        return body.contains(newHead);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Point p : body) {
            canvas.drawRect(p.x, p.y, p.x + blockSize, p.y + blockSize, this.paint);
        }
    }

    public Point getHead() {
        return body.peekFirst();
    }
}
