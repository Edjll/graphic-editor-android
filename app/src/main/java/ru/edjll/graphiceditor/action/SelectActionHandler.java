package ru.edjll.graphiceditor.action;

import ru.edjll.graphiceditor.figure.Figure;

@FunctionalInterface
public interface SelectActionHandler {

    void apply(Figure figure, int startX, int startY, int endX, int endY);
}
