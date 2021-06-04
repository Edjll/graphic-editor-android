package ru.edjll.graphiceditor.watcher.figure;

import java.util.Arrays;
import java.util.List;

import ru.edjll.graphiceditor.figure.Figure;

public enum FigureWatcher {
    LINE, RECTANGLE, CIRCLE;

    private List<FigureSubscriber> figureSubscribers;

    public void setFigureSubscribers(FigureSubscriber... figureSubscribers) {
        this.figureSubscribers = Arrays.asList(figureSubscribers);
    }

    public void changed(Figure figure) {
        figureSubscribers.forEach((subscriber) -> subscriber.perform(figure));
    }
}
