package ru.edjll.graphiceditor.toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import ru.edjll.graphiceditor.R;
import ru.edjll.graphiceditor.figure.Circle;
import ru.edjll.graphiceditor.figure.Figure;
import ru.edjll.graphiceditor.watcher.figure.FigureSubscriber;

public class CircleToolbar implements FigureSubscriber {

    private final EditText editTextX1;
    private final EditText editTextY1;
    private final EditText editTextRadius;
    private final LinearLayout layoutCircle;
    private final LinearLayout layoutFill;
    private final LinearLayout layoutCoordinates;
    private final Button buttonDelete;

    public CircleToolbar(LinearLayout toolbar) {
        editTextX1 = toolbar.findViewById(R.id.etX1);
        editTextY1 = toolbar.findViewById(R.id.etY1);
        editTextRadius = toolbar.findViewById(R.id.etR);
        layoutCircle = toolbar.findViewById(R.id.lCircle);
        layoutFill = toolbar.findViewById(R.id.fillLayout);
        layoutCoordinates = toolbar.findViewById(R.id.lCoordinates);
        buttonDelete = toolbar.findViewById(R.id.bDel);
    }

    @Override
    public void perform(Figure figure) {
        if (figure == null) {
            layoutCoordinates.setVisibility(View.INVISIBLE);
            layoutCircle.setVisibility(View.GONE);
            layoutFill.setVisibility(View.INVISIBLE);
            buttonDelete.setVisibility(View.INVISIBLE);
        } else {
            Circle circle = (Circle) figure;
            layoutCircle.setVisibility(View.VISIBLE);
            layoutFill.setVisibility(View.VISIBLE);
            buttonDelete.setVisibility(View.VISIBLE);
            layoutCoordinates.setVisibility(View.VISIBLE);
            editTextX1.setText(String.valueOf(circle.getX1()));
            editTextY1.setText(String.valueOf(circle.getY1()));
            editTextRadius.setText(String.valueOf(circle.getRadius()));
        }
    }
}

