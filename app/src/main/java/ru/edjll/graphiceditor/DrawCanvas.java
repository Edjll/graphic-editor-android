package ru.edjll.graphiceditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ru.edjll.graphiceditor.figure.Circle;
import ru.edjll.graphiceditor.figure.Figure;
import ru.edjll.graphiceditor.figure.Line;
import ru.edjll.graphiceditor.figure.Rectangle;
import ru.edjll.graphiceditor.toolbar.CircleToolbar;
import ru.edjll.graphiceditor.toolbar.LineToolbar;
import ru.edjll.graphiceditor.toolbar.RectangleToolbar;
import ru.edjll.graphiceditor.watcher.figure.FigureWatcher;
import ru.edjll.graphiceditor.watcher.text.TextWatcherImpl;

public class DrawCanvas extends View {

    private Paint strokeBrush = new Paint();
    private Paint fillBrush = new Paint();
    private Paint selectBrush = new Paint();
    private int eventX;
    private int eventY;
    private final List<Figure> figures = new ArrayList<>();
    private Figure figure;
    private MainActivity mainActivity;
    private Paint backgroundBrush;

    public DrawCanvas(Context context) {
        super(context);
        mainActivity = (MainActivity) context;
        strokeBrush.setColor(Color.BLUE);
        strokeBrush.setStrokeWidth(5f);
        strokeBrush.setStrokeJoin(Paint.Join.ROUND);
        strokeBrush.setStrokeCap(Paint.Cap.ROUND);
        strokeBrush.setStyle(Paint.Style.STROKE);

        backgroundBrush = new Paint();
        backgroundBrush.setColor(Color.WHITE);

        fillBrush.setColor(Color.BLUE);
        fillBrush.setStrokeWidth(2f);
        fillBrush.setStrokeJoin(Paint.Join.ROUND);
        fillBrush.setStrokeCap(Paint.Cap.ROUND);
        fillBrush.setStyle(Paint.Style.FILL);

        selectBrush.setColor(Color.BLACK);
        selectBrush.setStrokeWidth(2f);
        selectBrush.setStrokeJoin(Paint.Join.ROUND);
        selectBrush.setStrokeCap(Paint.Cap.ROUND);
        selectBrush.setPathEffect(new DashPathEffect(new float[]{10f, 20f}, 0f));
        selectBrush.setStyle(Paint.Style.STROKE);

        EditText etX1 = mainActivity.findViewById(R.id.etX1);
        EditText etX2 = mainActivity.findViewById(R.id.etX2);
        EditText etY1 = mainActivity.findViewById(R.id.etY1);
        EditText etY2 = mainActivity.findViewById(R.id.etY2);
        EditText etR = mainActivity.findViewById(R.id.etR);
        EditText etW = mainActivity.findViewById(R.id.etW);
        EditText etH = mainActivity.findViewById(R.id.etH);

        etX1.setText(String.valueOf(0));
        etX1.addTextChangedListener(new TextWatcherImpl((x1) -> {
            this.getFigure().changeTopLeftX(x1);
            invalidate();
        }));

        etX2.setText(String.valueOf(0));
        etX2.addTextChangedListener(new TextWatcherImpl((x2) -> {
            ((Line) this.getFigure()).changeEndX(x2);
            invalidate();
        }));

        etY1.setText(String.valueOf(0));
        etY1.addTextChangedListener(new TextWatcherImpl((y1) -> {
            this.getFigure().changeTopLeftY(y1);
            invalidate();
        }));

        etY2.setText(String.valueOf(0));
        etY2.addTextChangedListener(new TextWatcherImpl((y2) -> {
            ((Line) this.getFigure()).changeEndY(y2);
            invalidate();
        }));

        etR.setText(String.valueOf(0));
        etR.addTextChangedListener(new TextWatcherImpl((r) -> {
            ((Circle) this.getFigure()).setRadius(r);
            invalidate();
        }));

        etW.setText(String.valueOf(0));
        etW.addTextChangedListener(new TextWatcherImpl((w) -> {
            ((Rectangle) this.getFigure()).setWidth(w);
            invalidate();
        }));

        etH.setText(String.valueOf(0));
        etH.addTextChangedListener(new TextWatcherImpl((h) -> {
            ((Rectangle) this.getFigure()).setHeight(h);
            invalidate();
        }));

        LinearLayout toolbar = mainActivity.findViewById(R.id.toolbar);

        FigureWatcher.LINE.setFigureSubscribers(new LineToolbar(toolbar));
        FigureWatcher.CIRCLE.setFigureSubscribers(new CircleToolbar(toolbar));
        FigureWatcher.RECTANGLE.setFigureSubscribers(new RectangleToolbar(toolbar));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Figure figure : figures) {
            figure.draw(canvas);
        }

        mainActivity.getAction().getActionHandler().draw(this, canvas);
    }

    public Figure getFigure(int x, int y) {
        for (int i = figures.size() - 1; i >= 0; i--) {
            if (figures.get(i).contains(x, y)) {
                return figures.get(i);
            }
        }
        return null;
    }

    public Bitmap getImage() {
        Bitmap bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), backgroundBrush);
        draw(canvas);
        return bitmap;
    }

    public Figure getFigure() {
        return figure;
    }

    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    public void setFigure(Figure figure) {
        if (this.figure != null && this.figure != figure) {
            this.figure.getFigureWatcher().changed(null);
        }
        this.figure = figure;
        if (this.figure != null) {
            mainActivity.changeStrokeBrushWidth(this.figure.getStrokeBrushWidth());
            mainActivity.changeStrokeBrushColor(this.figure.getStrokeBrushColor());
            mainActivity.changeFillBrushColor(this.figure.getFillBrushColor());
        }
        invalidate();
    }

    public void removeFigure() {
        this.figures.remove(this.figure);
        this.setFigure(null);
    }

    public Paint getStrokeBrush() {
        return strokeBrush;
    }

    public Paint getFillBrush() {
        return fillBrush;
    }

    public void changeStrokeBrushColor(int color) {
        if (this.figure != null) {
            this.figure.changeStrokeBrushColor(color);
            invalidate();
        }
        this.strokeBrush.setColor(color);
    }

    public void changeFillBrushColor(int color) {
        this.fillBrush.setColor(color);
        if (this.figure != null) {
            this.figure.changeFillBrushColor(color);
            invalidate();
        }
    }

    public void changeStrokeBrushWidth(int width) {
        this.strokeBrush.setStrokeWidth(width);
        if (this.figure != null) {
            this.figure.changeStrokeBrushWidth(width);
            invalidate();
        }
    }

    public int getStrokeBrushWidth() {
        return (int) this.strokeBrush.getStrokeWidth();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        eventX = (int) event.getX();
        eventY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mainActivity.getAction().getActionHandler().start(this, eventX, eventY);
                break;
            case MotionEvent.ACTION_MOVE:
                mainActivity.getAction().getActionHandler().move(this.figure, eventX, eventY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mainActivity.getAction().getActionHandler().end(this, eventX, eventY);
                break;
        }

        return true;
    }
}
