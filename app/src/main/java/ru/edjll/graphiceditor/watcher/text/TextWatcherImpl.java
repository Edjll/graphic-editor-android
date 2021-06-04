package ru.edjll.graphiceditor.watcher.text;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.function.Consumer;

import ru.edjll.graphiceditor.MainActivity;

public class TextWatcherImpl implements TextWatcher {

    private final Consumer<Integer> consumer;

    public TextWatcherImpl(Consumer<Integer> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 0) {
            s.append('0');
        } else if (s.length() > 1 && s.charAt(0) == '0') {
            s.delete(0, 1);
        }
        consumer.accept(Integer.parseInt(s.toString()));
    }
}
