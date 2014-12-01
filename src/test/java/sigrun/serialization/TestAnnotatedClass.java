package sigrun.serialization;

import sigrun.common.Reportable;

import java.util.Arrays;

public class TestAnnotatedClass implements SerializableData {
    @Override
    public int lengthInBytes() {
        return 20;
    }

    @Reportable(value = "Integer Field", startPosition = 0, endPosition = 4)
    private int intField;

    @Reportable(value = "Short Field", startPosition = 4, endPosition = 6)
    private short shortField;

    @Reportable(value = "Byte A Field", startPosition = 10, endPosition = 20)
    private byte[] array = new byte[10];

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public short getShortField() {
        return shortField;
    }

    public void setShortField(short shortField) {
        this.shortField = shortField;
    }

    public byte[] getArray() {
        return array;
    }

    public void setArray(byte[] array) {
        this.array = array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestAnnotatedClass that = (TestAnnotatedClass) o;

        if (intField != that.intField) return false;
        if (shortField != that.shortField) return false;
        if (!Arrays.equals(array, that.array)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = intField;
        result = 31 * result + (int) shortField;
        result = 31 * result + (array != null ? Arrays.hashCode(array) : 0);
        return result;
    }
}
