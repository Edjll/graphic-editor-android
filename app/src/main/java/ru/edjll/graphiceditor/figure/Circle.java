package ru.edjll.graphiceditor.figure;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.Nullable;

import ru.edjll.graphiceditor.watcher.figure.FigureWatcher;

public class Circle extends AbstractFigure {

    private int radius;

    public Circle(int startX, int startY, Paint strokeBrush, Paint fillBrush) {
        super(startX, startY, startX, startY, strokeBrush, fillBrush);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(startX, startY, radius, fillBrush);
        canvas.drawCircle(startX, startY, radius, strokeBrush);
    }

    public int getRadius() {
        return this.radius;
    }

    @Override
    public void changeStart(int startX, int startY) {
        if (startX > startY) {
            this.radius = Math.abs(this.startX - startX);
        } else {
            this.radius = Math.abs(this.startY - startY);
        }
        FigureWatcher.CIRCLE.changed(this);
    }

    @Override
    public void changeEnd(int x2, int y2) {
        if (x2 > y2) {
            this.radius = Math.abs(this.startX - x2);
        } else {
            this.radius = Math.abs(this.startY - y2);
        }
        FigureWatcher.CIRCLE.changed(this);
    }

    @Override
    public boolean contains(int x, int y) {
        return Math.sqrt((x - this.startX) * (x - this.startX) + (y - this.startY) * (y - this.startY)) <= radius;
    }

    @Override
    public int getTopLeftX() {
        return this.startX - radius;
    }

    @Override
    public int getTopLeftY() {
        return this.startY - radius;
    }

    @Override
    public int getBottomRightX() {
        return this.startX + radius;
    }

    @Override
    public int getBottomRightY() {
        return this.startY + radius;
    }

    @Override
    public int getX1() {
        return this.startX;
    }

    @Override
    public int getY1() {
        return this.startY;
    }

    @Override
    public void offset(int dx, int dy) {
        startX += dx;
        startY += dy;
        FigureWatcher.CIRCLE.changed(this);
    }

    @Override
    public void changeStrokeBrushColor(int color) {
        strokeBrush.setColor(color);
        FigureWatcher.CIRCLE.changed(this);
    }

    @Override
    public void changeFillBrushColor(int color) {
        fillBrush.setColor(color);
        FigureWatcher.CIRCLE.changed(this);
    }

    @Override
    public int getStrokeBrushColor() {
        return this.strokeBrush.getColor();
    }

    @Override
    public int getFillBrushColor() {
        return this.fillBrush.getColor();
    }

    @Override
    public void changeStrokeBrushWidth(int width) {
        this.strokeBrush.setStrokeWidth(width);
        FigureWatcher.CIRCLE.changed(this);
    }

    @Override
    public void changeStartX(int startX) {
        if (this.startX != startX) {
            this.startX = startX;
            FigureWatcher.CIRCLE.changed(this);
        }
    }

    @Override
    public void changeStartY(int startY) {
        if (this.startY != startY) {
            this.startY = startY;
            FigureWatcher.CIRCLE.changed(this);
        }
    }

    @Override
    public int getStrokeBrushWidth() {
        return (int) this.strokeBrush.getStrokeWidth();
    }

    public void setRadius(int radius) {
        if (this.radius != radius) {
            this.radius = radius;
            FigureWatcher.CIRCLE.changed(this);
        }
    }

    @Override
    public FigureWatcher getFigureWatcher() {
        return FigureWatcher.CIRCLE;
    }

    @Override
    public void changeTopLeft(int x, int y) {
        this.changeEnd(x, y);
        FigureWatcher.CIRCLE.changed(this);
    }

    @Override
    public void changeTopLeftX(int x) {
        this.startX = x;
    }

    @Override
    public void changeTopLeftY(int y) {
        this.startY = y;
    }

    @Override
    public void changeBottomRight(int x, int y) {
        this.changeEnd(x, y);
        FigureWatcher.CIRCLE.changed(this);

    }

    @Override
    public void changeBottomRightX(int x) {

    }

    @Override
    public void changeBottomRightY(int y) {

    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!obj.getClass().equals(this.getClass())) return false;
        AbstractFigure figure = (AbstractFigure) obj;
        return this.startX == figure.startX
                && this.startY == figure.startY
                && this.endX == figure.endX
                && this.endY == figure.endY;
    }
}
