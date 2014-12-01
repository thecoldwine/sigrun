package sigrun.common;

import java.lang.reflect.Field;

public class ReportWrapper {
    private Field field;
    private int startPosition;
    private int endPosition;
    private String name;

    public ReportWrapper(Field field, int startPosition, int endPosition, String name) {
        this.field = field;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
