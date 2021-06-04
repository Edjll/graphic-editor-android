package ru.edjll.graphiceditor.figure;

import android.graphics.Paint;

import androidx.annotation.Nullable;

public abstract class AbstractFigure implements Figure {

    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    protected Paint strokeBrush;
    protected Paint fillBrush;

    public AbstractFigure(int startX, int startY, int endX, int endY, Paint strokeBrush, Paint fillBrush) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.strokeBrush = new Paint(strokeBrush);
        this.fillBrush = fillBrush != null ? new Paint(fillBrush) : null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result += this.startX * 42;
        result += this.startY * 42;
        result += this.endX * 42;
        result += this.endY * 42;
        return result;
    }
}
