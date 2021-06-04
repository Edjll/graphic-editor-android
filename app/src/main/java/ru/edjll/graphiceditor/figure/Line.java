package ru.edjll.graphiceditor.figure;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.Nullable;

import lombok.Data;
import ru.edjll.graphiceditor.watcher.figure.FigureWatcher;

@Data
public class Line extends AbstractFigure {

    public Line(int startX, int startY, Paint strokeBrush, Paint fillBrush) {
        super(startX, startY, startX, startY, strokeBrush, fillBrush);
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(startX, startY, endX, endY, strokeBrush);
    }

    @Override
    public void changeStart(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        FigureWatcher.LINE.changed(this);
    }

    @Override
    public void changeEnd(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
        FigureWatcher.LINE.changed(this);
    }

    @Override
    public boolean contains(int x, int y) {
        return Math.abs(((float) (startY - endY)) / (startX - endX) * (x - startX) + startY - y) < 20 + this.getStrokeBrushWidth();
    }

    @Override
    public int getTopLeftX() {
        return this.startX;
    }

    @Override
    public int getTopLeftY() {
        return this.startY;
    }

    @Override
    public int getBottomRightX() {
        return this.endX;
    }

    @Override
    public int getBottomRightY() {
        return this.endY;
    }

    @Override
    public void offset(int dx, int dy) {
        startX += dx;
        endX += dx;
        startY += dy;
        endY += dy;
        FigureWatcher.LINE.changed(this);
    }

    @Override
    public void changeStrokeBrushColor(int color) {
        strokeBrush.setColor(color);
        FigureWatcher.LINE.changed(this);
    }

    @Override
    public void changeFillBrushColor(int color) {
        fillBrush.setColor(color);
        FigureWatcher.LINE.changed(this);
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
        FigureWatcher.LINE.changed(this);
    }

    @Override
    public int getX1() {
        return this.startX;
    }

    @Override
    public int getY1() {
        return this.startY;
    }

    public int getX2() {
        return this.endX;
    }

    public int getY2() {
        return this.endY;
    }

    @Override
    public void changeStartX(int startX) {
        if (this.startX != startX) {
            this.startX = startX;
            FigureWatcher.LINE.changed(this);
        }
    }

    @Override
    public void changeStartY(int startY) {
        if (this.startY != startY) {
            this.startY = startY;
            FigureWatcher.LINE.changed(this);
        }
    }

    public void changeEndX(int endX) {
        if (this.endX != endX) {
            this.endX = endX;
            FigureWatcher.LINE.changed(this);
        }
    }

    public void changeEndY(int endY) {
        if (this.endY != endY) {
            this.endY = endY;
            FigureWatcher.LINE.changed(this);
        }
    }

    @Override
    public int getStrokeBrushWidth() {
        return (int) this.strokeBrush.getStrokeWidth();
    }

    @Override
    public FigureWatcher getFigureWatcher() {
        return FigureWatcher.LINE;
    }

    @Override
    public void changeTopLeft(int x, int y) {
        this.changeStart(x, y);
    }

    @Override
    public void changeTopLeftX(int x) {
        this.changeStartX(x);
    }

    @Override
    public void changeTopLeftY(int y) {
        this.changeStartY(y);
    }

    @Override
    public void changeBottomRight(int x, int y) {
        this.changeEnd(x, y);
    }

    @Override
    public void changeBottomRightX(int x) {
        this.changeEndX(x);
    }

    @Override
    public void changeBottomRightY(int y) {
        this.changeEndY(y);
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
