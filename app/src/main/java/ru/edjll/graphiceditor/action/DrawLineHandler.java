package ru.edjll.graphiceditor.action;

import android.graphics.Canvas;
import android.graphics.Paint;

import ru.edjll.graphiceditor.DrawCanvas;
import ru.edjll.graphiceditor.figure.Figure;
import ru.edjll.graphiceditor.figure.Line;
import ru.edjll.graphiceditor.watcher.figure.FigureWatcher;

public class DrawLineHandler implements ActionHandler {

    @Override
    public void start(DrawCanvas drawCanvas, int x, int y) {
        drawCanvas.setFigure(new Line(x, y, drawCanvas.getStrokeBrush(), drawCanvas.getFillBrush()));
    }

    @Override
    public void move(Figure figure, int x, int y) {
        figure.changeEnd(x, y);
    }

    @Override
    public void end(DrawCanvas drawCanvas, int x, int y) {
        drawCanvas.addFigure(drawCanvas.getFigure());
        drawCanvas.setFigure(null);
    }

    @Override
    public void draw(DrawCanvas drawCanvas, Canvas canvas) {
        if (drawCanvas.getFigure() != null) {
            drawCanvas.getFigure().draw(canvas);
        }
    }
}
