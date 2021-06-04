package ru.edjll.graphiceditor;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.concurrent.ExecutionException;

import ru.edjll.graphiceditor.action.Action;
import ru.edjll.graphiceditor.dialog.FillColorPicker;
import ru.edjll.graphiceditor.dialog.StrokeColorPicker;
import ru.edjll.graphiceditor.task.SaveTask;
import ru.edjll.graphiceditor.watcher.text.TextWatcherImpl;

public class MainActivity extends AppCompatActivity {

    private Action action = Action.DRAW_LINE;
    private DialogFragment strokeColorPicker;
    private DialogFragment fillColorPicker;
    private DrawCanvas drawCanvas;
    private View strokeBrushColor;
    private View fillBrushColor;
    private LinearLayout fillLayout;
    private EditText etStrokeWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.main_layout);
        drawCanvas = new DrawCanvas(this);
        linearLayout.addView(drawCanvas);
        ((RadioButton) findViewById(R.id.rbLine)).setChecked(true);
        strokeBrushColor = findViewById(R.id.strokeBrushColor);
        fillBrushColor = findViewById(R.id.fillBrushColor);
        fillLayout = findViewById(R.id.fillLayout);
        etStrokeWidth = findViewById(R.id.etStrokeWidth);

        etStrokeWidth.setText(String.valueOf(drawCanvas.getStrokeBrushWidth()));
        etStrokeWidth.addTextChangedListener(new TextWatcherImpl((width) -> this.drawCanvas.changeStrokeBrushWidth(width)));

        strokeColorPicker = new StrokeColorPicker(this);
        fillColorPicker = new FillColorPicker(this);
    }

    public void changeStrokeBrushWidth(int width) {
        etStrokeWidth.setText(String.valueOf(width));
    }

    public void changeStrokeBrushColor(int color) {
        drawCanvas.changeStrokeBrushColor(color);
        strokeBrushColor.setBackgroundResource(R.drawable.rounded_corners);
        ((GradientDrawable)strokeBrushColor.getBackground()).setColor(color);
    }

    public void changeFillBrushColor(int color) {
        drawCanvas.changeFillBrushColor(color);
        fillBrushColor.setBackgroundResource(R.drawable.rounded_corners);
        ((GradientDrawable)fillBrushColor.getBackground()).setColor(color);
    }

    public void showStrokeColorPicker(View view) {
        strokeColorPicker.show(getSupportFragmentManager(), "strokeColorPicker");
    }

    public void showFillColorPicker(View view) {
        fillColorPicker.show(getSupportFragmentManager(), "fillColorPicker");
    }

    public int getStrokeBrushColor() {
        return this.drawCanvas.getStrokeBrush().getColor();
    }

    public int getFillBrushColor() {
        return this.drawCanvas.getFillBrush().getColor();
    }

    public void removeFigure(View view) {
        this.drawCanvas.removeFigure();
    }

    public Action getAction() {
        return action;
    }

    public void changeAction(View view) {
        RadioButton radioButton = (RadioButton) view;
        switch (radioButton.getId()) {
            case R.id.rbSelect:
                action = Action.SELECT;
                fillLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.rbLine:
                action = Action.DRAW_LINE;
                fillLayout.setVisibility(View.INVISIBLE);
                drawCanvas.setFigure(null);
                break;
            case R.id.rbRectangle:
                action = Action.DRAW_RECTANGLE;
                fillLayout.setVisibility(View.VISIBLE);
                drawCanvas.setFigure(null);
                break;
            case R.id.rbCircle:
                action = Action.DRAW_CIRCLE;
                fillLayout.setVisibility(View.VISIBLE);
                drawCanvas.setFigure(null);
                break;
        }
    }

    public void save(View view) throws ExecutionException, InterruptedException {
        this.sendSms(new SaveTask().execute(drawCanvas.getImage()).get());
    }

    public void sendSms(String id) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"));
        intent.putExtra("sms_body", "Смотри что я нарисовал(а) http://192.168.1.9:8085/" + id + "/image");
        startActivity(intent);
    }
}