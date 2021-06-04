package ru.edjll.graphiceditor.watcher.figure;

import ru.edjll.graphiceditor.figure.Figure;

public interface FigureSubscriber {

    void perform(Figure figure);
}
