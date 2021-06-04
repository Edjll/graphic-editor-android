package ru.edjll.graphiceditor.action;

public enum Action {
    SELECT(new SelectHandler()),
    DRAW_LINE(new DrawLineHandler()),
    DRAW_RECTANGLE(new DrawRectangleHandler()),
    DRAW_CIRCLE(new DrawCircleHandler());

    private final ActionHandler actionHandler;

    Action(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public ActionHandler getActionHandler() {
        return actionHandler;
    }
}
