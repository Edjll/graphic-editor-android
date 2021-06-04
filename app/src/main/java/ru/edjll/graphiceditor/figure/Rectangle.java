package ru.edjll.graphiceditor.figure;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.Nullable;

import lombok.Data;
import ru.edjll.graphiceditor.watcher.figure.FigureWatcher;

@Data
public class Rectangle extends AbstractFigure {

    public Rectangle(int startX, int startY, Paint strokeBrush, Paint fillBrush) {
        super(startX, startY, startX, startY, strokeBrush, fillBrush);
    }

    @Override
    public void draw(Canvas canvas) {
        if (fillBrush != null)
            canvas.drawRect(startX, startY, endX, endY, fillBrush);
        canvas.drawRect(startX, startY, endX, endY, strokeBrush);
    }

    @Override
    public void changeStart(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        FigureWatcher.RECTANGLE.changed(this);
    }

    @Override
    public void changeEnd(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
        FigureWatcher.RECTANGLE.changed(this);
    }

    public void setCoordinates(int x1, int y1, int x2, int y2) {
        this.startX = x1;
        this.startY = y1;
        this.endX = x2;
        this.endY = y2;
    }

    @Override
    public boolean contains(int x, int y) {
        return x >= getTopLeftX() && x <= getBottomRightX() && y >= getTopLeftY() && y <= getBottomRightY();
    }

    @Override
    public int getTopLeftX() {
        return Math.min(this.startX, this.endX);
    }

    @Override
    public int getTopLeftY() {
        return Math.min(this.startY, this.endY);
    }

    @Override
    public int getBottomRightX() {
        return Math.max(this.startX, this.endX);
    }

    @Override
    public int getBottomRightY() {
        return Math.max(this.startY, this.endY);
    }

    @Override
    public void offset(int dx, int dy) {
        if (dx != 0 && dy != 0) {
            startX += dx;
            startY += dy;
            endX += dx;
            endY += dy;
            FigureWatcher.RECTANGLE.changed(this);
        }
    }

    @Override
    public void changeStrokeBrushColor(int color) {
        strokeBrush.setColor(color);
    }

    @Override
    public void changeFillBrushColor(int color) {
        fillBrush.setColor(color);
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
    }

    @Override
    public int getX1() {
        return getTopLeftX();
    }

    @Override
    public int getY1() {
        return getTopLeftY();
    }

    @Override
    public void changeStartX(int startX) {
        if (this.startX != startX) {
            this.startX = startX;
        }
    }

    @Override
    public void changeStartY(int startY) {
        if (this.startY != startY) {
            this.startY = startY;
        }
    }

    @Override
    public int getStrokeBrushWidth() {
        return (int) this.strokeBrush.getStrokeWidth();
    }

    public int getWidth() {
        return Math.abs(this.startX - this.endX);
    }

    public int getHeight() {
        return Math.abs(this.startX - this.endX);
    }

    public void setWidth(int width) {
        if (this.getWidth() != width) {
            if (this.startX > this.endX) {
                this.endX = this.startX - width;
            } else {
                this.endX = this.startX + width;
            }
        }
    }

    public void setHeight(int height) {
        if (this.getHeight() != height) {
            if (this.startY > this.endY) {
                this.endY = this.startY - height;
            } else {
                this.endY = this.startY + height;
            }
        }
    }

    @Override
    public FigureWatcher getFigureWatcher() {
        return FigureWatcher.RECTANGLE;
    }

    @Override
    public void changeTopLeft(int x, int y) {
        this.changeTopLeftX(x);
        this.changeTopLeftY(y);
        FigureWatcher.RECTANGLE.changed(this);
    }

    @Override
    public void changeTopLeftX(int x) {
        if (this.startX > this.endX) {
            this.endX = x;
        } else {
            this.startX = x;
        }
    }

    @Override
    public void changeTopLeftY(int y) {
        if (this.startY > this.endY) {
            this.endY = y;
        } else {
            this.startY = y;
        }
    }

    @Override
    public void changeBottomRight(int x, int y) {
        this.changeBottomRightX(x);
        this.changeBottomRightY(y);
        FigureWatcher.RECTANGLE.changed(this);
    }

    @Override
    public void changeBottomRightX(int x) {
        if (this.startX > this.endX) {
            this.startX = x;
        } else {
            this.endX = x;
        }
    }

    @Override
    public void changeBottomRightY(int y) {
        if (this.startY > this.endY) {
            this.startY = y;
        } else {
            this.endY = y;
        }
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
