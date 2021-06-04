package ru.edjll.graphiceditor.toolbar;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import ru.edjll.graphiceditor.R;
import ru.edjll.graphiceditor.figure.Figure;
import ru.edjll.graphiceditor.figure.Line;
import ru.edjll.graphiceditor.watcher.figure.FigureSubscriber;

public class LineToolbar implements FigureSubscriber {

    private final EditText editTextX1;
    private final EditText editTextY1;
    private final EditText editTextX2;
    private final EditText editTextY2;
    private final LinearLayout layoutLine;
    private final LinearLayout layoutFill;
    private final LinearLayout layoutCoordinates;
    private final Button buttonDelete;

    public LineToolbar(LinearLayout toolbar) {
        editTextX1 = toolbar.findViewById(R.id.etX1);
        editTextX2 = toolbar.findViewById(R.id.etX2);
        editTextY1 = toolbar.findViewById(R.id.etY1);
        editTextY2 = toolbar.findViewById(R.id.etY2);
        layoutLine = toolbar.findViewById(R.id.lLine);
        layoutFill = toolbar.findViewById(R.id.fillLayout);
        layoutCoordinates = toolbar.findViewById(R.id.lCoordinates);
        buttonDelete = toolbar.findViewById(R.id.bDel);
    }

    @Override
    public void perform(Figure figure) {
        if (figure == null) {
            layoutCoordinates.setVisibility(View.INVISIBLE);
            layoutLine.setVisibility(View.GONE);
            buttonDelete.setVisibility(View.INVISIBLE);
        } else {
            Line line = (Line) figure;
            layoutLine.setVisibility(View.VISIBLE);
            layoutFill.setVisibility(View.INVISIBLE);
            buttonDelete.setVisibility(View.VISIBLE);
            layoutCoordinates.setVisibility(View.VISIBLE);
            editTextX1.setText(String.valueOf(line.getX1()));
            editTextY1.setText(String.valueOf(line.getY1()));
            editTextX2.setText(String.valueOf(line.getX2()));
            editTextY2.setText(String.valueOf(line.getY2()));
        }
    }
}
