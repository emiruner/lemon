package lemon.core;

import org.apache.commons.lang.StringUtils;

public class LObjectBase implements LObject {
    private final int type;
    private LObject[] values;

    public LObjectBase(int type, int size) {
        this.type = type;

        this.values = new LObject[size];

        for(int i = 0; i < size; ++i) {
            values[i] = Constants.NIL;
        }
    }

    public LObject at(int idx) {
        if(values.length <= idx) {
            return Constants.NIL;
        }

        return values[idx];
    }

    public void atPut(int idx, LObject value) {
        if(values.length <= idx) {
            LObject[] newValues = new LObject[2 * idx];

            System.arraycopy(values, 0, newValues, 0, values.length);

            for(int i = values.length; i < newValues.length; ++i) {
                newValues[i] = Constants.NIL;
            }

            values = newValues;
        }

        values[idx] = value;
    }

    @Override
    public String toString() {
        return "(" + StringUtils.join(values, " ") + ")";
    }

    @Override
    public int getType() {
        return type;
    }
}
