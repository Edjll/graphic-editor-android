package ru.edjll.graphiceditor.toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import ru.edjll.graphiceditor.R;
import ru.edjll.graphiceditor.figure.Figure;
import ru.edjll.graphiceditor.figure.Line;
import ru.edjll.graphiceditor.figure.Rectangle;
import ru.edjll.graphiceditor.watcher.figure.FigureSubscriber;

public class RectangleToolbar implements FigureSubscriber {

    private final EditText editTextX1;
    private final EditText editTextY1;
    private final EditText editTextWidth;
    private final EditText editTextHeight;
    private final LinearLayout layoutRectangle;
    private final LinearLayout layoutFill;
    private final LinearLayout layoutCoordinates;
    private final Button buttonDelete;

    public RectangleToolbar(LinearLayout toolbar) {
        editTextX1 = toolbar.findViewById(R.id.etX1);
        editTextY1 = toolbar.findViewById(R.id.etY1);
        editTextWidth = toolbar.findViewById(R.id.etW);
        editTextHeight = toolbar.findViewById(R.id.etH);
        layoutRectangle = toolbar.findViewById(R.id.lRectangle);
        layoutFill = toolbar.findViewById(R.id.fillLayout);
        layoutCoordinates = toolbar.findViewById(R.id.lCoordinates);
        buttonDelete = toolbar.findViewById(R.id.bDel);
    }

    @Override
    public void perform(Figure figure) {
        if (figure == null) {
            layoutCoordinates.setVisibility(View.INVISIBLE);
            layoutRectangle.setVisibility(View.GONE);
            layoutFill.setVisibility(View.INVISIBLE);
            buttonDelete.setVisibility(View.INVISIBLE);
        } else {
            Rectangle rectangle = (Rectangle) figure;
            layoutRectangle.setVisibility(View.VISIBLE);
            layoutFill.setVisibility(View.VISIBLE);
            buttonDelete.setVisibility(View.VISIBLE);
            layoutCoordinates.setVisibility(View.VISIBLE);
            editTextX1.setText(String.valueOf(rectangle.getX1()));
            editTextY1.setText(String.valueOf(rectangle.getY1()));
            editTextWidth.setText(String.valueOf(rectangle.getWidth()));
            editTextHeight.setText(String.valueOf(rectangle.getHeight()));
        }
    }
}

