package lemon.core;

import java.util.Iterator;

public class Pair extends LObjectBase implements Iterable<LObject> {
    public static int TYPE = 3;

    public Pair(LObject head) {
        this(head, Constants.NIL);
    }

    public Pair(LObject head, LObject tail) {
        super(TYPE, 2);

        setHead(head);
        setTail(tail);
    }

    public LObject getHead() {
        return at(0);
    }

    public void setHead(LObject head) {
        atPut(0, head);
    }

    public LObject getTail() {
        return at(1);
    }

    public void setTail(LObject tail) {
        atPut(1, tail);
    }

    public void append(LObject o) {
        Pair current = this;

        while (!(current.getTail() instanceof Undefined)) {
            current = (Pair) current.getTail();
        }

        current.setTail(new Pair(o));
    }

    @Override
    public int getType() {
        return TYPE;
    }

    private static class PairIterator implements Iterator<LObject> {
        private Object current;

        private PairIterator(LObject start) {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current instanceof Pair;
        }

        @Override
        public LObject next() {
            LObject result = ((Pair) current).getHead();
            current = ((Pair) current).getTail();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<LObject> iterator() {
        return new PairIterator(this);
    }

    @Override
    public String toString() {
        if (getTail() instanceof Undefined) {
            return "(" + getHead() + ")";
        }

        if (!(getTail() instanceof Pair)) {
            return "(" + getHead() + " . " + getTail() + ")";
        }

        String str = "(" + getHead();

        LObject currentTail = getTail();

        while (!(currentTail instanceof Undefined)) {
            if (currentTail instanceof Pair) {
                str += " " + ((Pair) currentTail).getHead();
                currentTail = ((Pair) currentTail).getTail();
            } else {
                str += " . " + currentTail;
                break;
            }
        }

        return str + ")";
    }

    public static LObject list(LObject... values) {
        LObject current = Constants.NIL;

        for (int i = (values.length - 1); i >= 0; --i) {
            current = new Pair(values[i], current);
        }

        return current;
    }

    public static Iterable<LObject> iterable(final LObject nilOrPair) {
        return new Iterable<LObject>() {
            public Iterator<LObject> iterator() {
                return new PairIterator(nilOrPair);
            }
        };
    }

    public static LObject first(LObject nilOrPair) {
        if (nilOrPair instanceof Undefined) {
            throw new RuntimeException("cannot access element");
        }

        return ((Pair) nilOrPair).getHead();
    }

    public static LObject second(LObject nilOrPair) {
        if (nilOrPair instanceof Undefined) {
            throw new RuntimeException("cannot access element");
        }

        return first(((Pair) nilOrPair).getTail());
    }

    public static LObject third(LObject nilOrPair) {
        if (nilOrPair instanceof Undefined) {
            throw new RuntimeException("cannot access element");
        }

        return second(((Pair) nilOrPair).getTail());
    }

    public static LObject rest(LObject nilOrPair) {
        if (nilOrPair instanceof Undefined) {
            throw new RuntimeException("expecting list found nil");
        }

        return ((Pair) nilOrPair).getTail();
    }

    public static int size(LObject nilOrPair) {
        int count = 0;

        while (!(nilOrPair instanceof Undefined)) {
            ++count;
            nilOrPair = ((Pair) nilOrPair).getTail();
        }

        return count;
    }
}
