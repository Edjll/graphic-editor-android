package ru.edjll.graphiceditor.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madrapps.pikolo.ColorPicker;
import com.madrapps.pikolo.RGBColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;

import ru.edjll.graphiceditor.MainActivity;
import ru.edjll.graphiceditor.R;

public class FillColorPicker extends DialogFragment {

    private final MainActivity mainActivity;
    private ColorPicker colorPicker;

    public FillColorPicker(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (colorPicker != null) {
            colorPicker.setColor(this.mainActivity.getFillBrushColor());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stroke_color_picker, null);
        this.colorPicker = view.findViewById(R.id.colorPicker);
        colorPicker.setColor(this.mainActivity.getFillBrushColor());
        this.colorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {
                mainActivity.changeFillBrushColor(color);
            }
        });
        return view;
    }
}