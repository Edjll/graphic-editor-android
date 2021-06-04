package ru.edjll.graphiceditor.figure;

import android.graphics.Canvas;
import android.graphics.Paint;

import ru.edjll.graphiceditor.watcher.figure.FigureWatcher;

public interface Figure {

    void draw(Canvas canvas);

    void changeStart(int x1, int y1);
    void changeStartX(int x1);
    void changeStartY(int y1);

    void changeTopLeft(int x, int y);
    void changeTopLeftX(int x);
    void changeTopLeftY(int y);

    void changeBottomRight(int x, int y);
    void changeBottomRightX(int x);
    void changeBottomRightY(int y);

    void changeEnd(int x2, int y2);

    boolean contains(int x, int y);

    void offset(int dx, int dy);

    void changeStrokeBrushColor(int color);
    void changeStrokeBrushWidth(int width);

    void changeFillBrushColor(int color);

    int getStrokeBrushColor();
    int getStrokeBrushWidth();

    int getFillBrushColor();

    int getTopLeftX();
    int getTopLeftY();
    int getBottomRightX();
    int getBottomRightY();

    int getX1();
    int getY1();

    FigureWatcher getFigureWatcher();
}
