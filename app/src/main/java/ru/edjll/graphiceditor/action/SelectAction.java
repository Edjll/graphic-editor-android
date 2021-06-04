package ru.edjll.graphiceditor.action;

public enum SelectAction {
    CHANGE_START((figure, startX, startY, endX, endY) -> figure.changeTopLeft(endX, endY)),
    CHANGE_END((figure, startX, startY, endX, endY) -> figure.changeBottomRight(endX, endY)),
    MOVE((figure, startX, startY, endX, endY) -> figure.offset(endX - startX, endY - startY));

    private final SelectActionHandler handler;

    SelectAction(SelectActionHandler handler) {
        this.handler = handler;
    }

    public SelectActionHandler getHandler() {
        return handler;
    }
}
