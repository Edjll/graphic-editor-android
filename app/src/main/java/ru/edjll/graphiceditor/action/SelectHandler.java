package ru.edjll.graphiceditor.action;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

import ru.edjll.graphiceditor.DrawCanvas;
import ru.edjll.graphiceditor.figure.Figure;
import ru.edjll.graphiceditor.figure.Rectangle;

public class SelectHandler implements ActionHandler {

    private int x;
    private int y;
    private final Paint brush;
    private final Paint figureBrush;
    private final Rectangle start;
    private final Rectangle end;
    private SelectAction action = SelectAction.MOVE;

    public SelectHandler() {
        brush = new Paint();
        brush.setColor(Color.BLACK);
        brush.setStrokeWidth(5f);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeCap(Paint.Cap.ROUND);
        brush.setPathEffect(new DashPathEffect(new float[] {10f, 20f}, 0f));
        brush.setStyle(Paint.Style.STROKE);

        figureBrush = new Paint();
        figureBrush.setColor(Color.BLACK);
        figureBrush.setStrokeWidth(5f);
        figureBrush.setStrokeJoin(Paint.Join.ROUND);
        figureBrush.setStrokeCap(Paint.Cap.ROUND);
        figureBrush.setStyle(Paint.Style.STROKE);

        start = new Rectangle(0, 0, figureBrush, null);
        end = new Rectangle(0, 0, figureBrush, null);
    }

    @Override
    public void start(DrawCanvas drawCanvas, int x, int y) {
        this.x = x;
        this.y = y;
        if (drawCanvas.getFigure() != null) {
            if (start.contains(x, y)) {
                this.action = SelectAction.CHANGE_START;
            } else if (end.contains(x, y)) {
                this.action = SelectAction.CHANGE_END;
            } else if (drawCanvas.getFigure().contains(x, y)) {
                this.action = SelectAction.MOVE;
            } else {
                drawCanvas.setFigure(drawCanvas.getFigure(x, y));
                if (drawCanvas.getFigure() != null) {
                    drawCanvas.getFigure().getFigureWatcher().changed(drawCanvas.getFigure());
                }
                drawCanvas.invalidate();
            }
        } else {
            drawCanvas.setFigure(drawCanvas.getFigure(x, y));
            if (drawCanvas.getFigure() != null) {
                drawCanvas.getFigure().getFigureWatcher().changed(drawCanvas.getFigure());
            }
            drawCanvas.invalidate();
        }
    }

    @Override
    public void move(Figure figure, int x, int y) {
        if (figure != null) {
            this.action.getHandler().apply(figure, this.x, this.y, x, y);
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public void end(DrawCanvas drawCanvas, int x, int y) {
    }

    @Override
    public void draw(DrawCanvas drawCanvas, Canvas canvas) {
        if (drawCanvas.getFigure() != null) {
            drawCanvas.getFigure().draw(canvas);
            canvas.drawRect(
                    drawCanvas.getFigure().getTopLeftX() - drawCanvas.getFigure().getStrokeBrushWidth() - 5,
                    drawCanvas.getFigure().getTopLeftY() - drawCanvas.getFigure().getStrokeBrushWidth() - 5,
                    drawCanvas.getFigure().getBottomRightX() + drawCanvas.getFigure().getStrokeBrushWidth() + 5,
                    drawCanvas.getFigure().getBottomRightY() + drawCanvas.getFigure().getStrokeBrushWidth() + 5, brush
            );

            start.setCoordinates(
                    drawCanvas.getFigure().getTopLeftX() - drawCanvas.getFigure().getStrokeBrushWidth() - 20,
                    drawCanvas.getFigure().getTopLeftY() - drawCanvas.getFigure().getStrokeBrushWidth() - 20,
                    drawCanvas.getFigure().getTopLeftX() - drawCanvas.getFigure().getStrokeBrushWidth(),
                    drawCanvas.getFigure().getTopLeftY() - drawCanvas.getFigure().getStrokeBrushWidth()
            );
            start.draw(canvas);

            end.setCoordinates(
                    drawCanvas.getFigure().getBottomRightX() + drawCanvas.getFigure().getStrokeBrushWidth(),
                    drawCanvas.getFigure().getBottomRightY() + drawCanvas.getFigure().getStrokeBrushWidth(),
                    drawCanvas.getFigure().getBottomRightX() + drawCanvas.getFigure().getStrokeBrushWidth() + 20,
                    drawCanvas.getFigure().getBottomRightY() + drawCanvas.getFigure().getStrokeBrushWidth() + 20
            );
            end.draw(canvas);
        }
    }
}
