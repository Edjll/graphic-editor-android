package ru.edjll.graphiceditor.action;

import android.graphics.Canvas;
import android.graphics.Paint;

import ru.edjll.graphiceditor.DrawCanvas;
import ru.edjll.graphiceditor.figure.Figure;

public interface ActionHandler {

    void start(DrawCanvas drawCanvas, int x, int y);

    void move(Figure figure, int x, int y);

    void end(DrawCanvas drawCanvas, int x, int y);

    void draw(DrawCanvas drawCanvas, Canvas canvas);
}
